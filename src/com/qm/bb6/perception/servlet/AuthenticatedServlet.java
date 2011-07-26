/*
 * @(#)AuthenticatedServlet.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.servlet;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import blackboard.base.BbList;
import blackboard.data.course.*;
import blackboard.data.user.User;
import blackboard.platform.BbServiceManager;
import blackboard.persist.*;
import blackboard.platform.context.*;

import com.qm.bb6.perception.config.*;
import com.qm.bb6.perception.util.*;
import com.qm.bb6.perception.security.*;
import com.qm.bb6.perception.service.*;

/**
 * Base Servlet for all authenticated Perception processes
 *	This class cannot be instantiated, this only handles
 *	generic components such as Bb authentication and logging
 *  This also include general functions used to send receipts
 *  and error messages
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public abstract class AuthenticatedServlet extends HttpServlet {
	
	public static final String CONTEXT_PARAM = "bbContext";
	public static final String UPDATE_PARAM = "update";
	public static final String LOCALE_PARAM = "currentLocale";

	// Set path to JSP UI elements
	private String receiptJsp = "/jsp/receipt.jsp";
	
	/**
	 *	Handles the individual UI process. No need to authenticate or set session variables in here
	 * @param request  
	 * @param response
	 * @param ctx  the blackboard Context (contains User, Course, Session info)
	 * @throws javax.servlet.ServletException
	 * @throws java.io.IOException
	 */
	abstract boolean doContent(HttpServletRequest request, HttpServletResponse response, Context ctx) throws ServletException, IOException;

	/**
	 *	Handles GET requests by forwarding to #doPost
	 * @param request  
	 * @param response
	 * @throws javax.servlet.ServletException
	 * @throws java.io.IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	/**
	 *	Handles ALL GET and POST requests
	 * 
	 * Authenticates user to Bb, sets values onto the request object and calls #doContent
	 * @param request  
	 * @param response
	 * @throws javax.servlet.ServletException
	 * @throws java.io.IOException
	 */
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		// fetch preception log
		PerceptionLog log = PerceptionServiceManager.getPerceptionLog();
		
		boolean sendErrorsToClient = false;
		
		ContextManager ctxMgr = null;
		Context ctx = null;
		Locale locale = null; // use default
		boolean responseCommitted = false;
		
		// set default headers
		response.setHeader("Pragma", "no-cache");
		response.addDateHeader("Expires", System.currentTimeMillis());
		if( isHTML() )
			response.setContentType("text/html");
		
		// Read recallUrl from request. May not have been set by Bb.
		String recallUrl = request.getParameter("recallUrl");
				
	    try{

			// attempt to fetch current system Locale	
			locale = BbUtils.getLocale( request );

	    	// attept to fetch Bb Context manager
		    ctxMgr = (ContextManager) BbServiceManager.lookupService(ContextManager.class);
		    // set Bb Context to current request
		    ctx = ctxMgr.setContext(request);
		    if (ctx == null || ctx.getUser() == null) {
		    	// context not found - redirect to login page
				BbUtils.sendLoginRedirect( request,response );
	  			responseCommitted = true;
				throw new ServletException("Error: You are not logged in!");
			}

			// attempt to fetch current user Locale		
			locale = BbUtils.getLocale( request, ctx );
			request.setAttribute( LOCALE_PARAM, locale );
			
			// load general Perception Bridge settings
			PerceptionSettings settings = PerceptionSettings.load();
			// add the settings to the request, so that they can be read
			// by JSP and other classes without reloading
			request.setAttribute( PerceptionSettings.class.getName(), settings);

			// check privileges 
			// if SYSTEM_ADMIN always return true
			if( ctx.getUser().getSystemRole() == User.SystemRole.SYSTEM_ADMIN ){
				// always has access
				sendErrorsToClient = true;
			}else if( requiresAdminPrivileges() ){
				if( BbUtils.hasSystemAdminPrivileges( settings, ctx ) ){
					// always has access
				}else{
					throw new AccessDeniedException();
				}	
			}else if( requiresControlPanelPrivileges() ){
				if( BbUtils.hasControlPanelPrivileges( settings, ctx ) ){
					// always has access
				}else{
					throw new AccessDeniedException();
				}
			}else if( requiresCoursePrivileges() ){
				if( BbUtils.hasCoursePrivileges( settings, ctx ) ){
					// always has access
				}else{
					throw new AccessDeniedException();
				}
			}else{
				// ok
			}
			
			// if recallUrl is null, fetch default
			if(recallUrl == null || recallUrl.trim().length() == 0){
				recallUrl = getDefaultRecallUrl( request, ctx );
			}

			// add recallUrl to request	
			request.setAttribute("recallUrl", recallUrl);

			// add bb context to request
			request.setAttribute( CONTEXT_PARAM, ctx );
			
			// call content function
			responseCommitted = doContent(request, response, ctx);
		
		}catch(AccessDeniedException ade){
			sendError(request, response, getSafeSystemString("access-denied.title", locale), getSafeSystemString("access-denied.message", locale));
		}catch(Exception e){
			// unknown error
			log.logError( "Unknown error", e );

			if( sendErrorsToClient && !responseCommitted ){
				request.setAttribute( "exception", e );
				getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
				
			}else if( !responseCommitted ){ // handle error
				try{
					sendDefaultError(request, response);
				}catch(IllegalStateException ise){
					// response committed
				}
			}
		} finally {
			// close context
		    if(ctxMgr != null)
		    	ctxMgr.releaseContext();
		}
	}

	protected boolean sendDefaultError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Locale locale = (Locale) request.getAttribute( LOCALE_PARAM );
		return sendError(request, response, getSafeSystemString("error.title", locale), getSafeSystemString("error.message", locale));
	}
	
	protected boolean sendError(HttpServletRequest request, HttpServletResponse response, String title, String message) throws ServletException, IOException{
	    return sendReceipt(request, response, title, message, null, false);
	}

	protected boolean sendSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    return sendReceipt(request, response, null, null, null, true);
	}

	protected boolean sendSuccess(HttpServletRequest request, HttpServletResponse response, String title, String message) throws ServletException, IOException{
	    return sendReceipt(request, response, title, message, null, true);
	}

	protected boolean sendError(HttpServletRequest request, HttpServletResponse response, String title, String message, String recallUrl) throws ServletException, IOException{
	    return sendReceipt(request, response, title, message, recallUrl, false);
	}

	protected boolean sendSuccess(HttpServletRequest request, HttpServletResponse response, String title, String message, String recallUrl) throws ServletException, IOException{
	    return sendReceipt(request, response, title, message, recallUrl, true);
	}

	protected boolean sendReceipt(HttpServletRequest request, HttpServletResponse response, String title, String message, String recallUrl, boolean success) throws ServletException, IOException{
    	if( success )
	    	request.setAttribute("type", "SUCCESS");
	    else
	    	request.setAttribute("type", "FAIL");
	    	
		if( recallUrl == null || recallUrl.trim().length() == 0){
			recallUrl = request.getParameter("recallUrl");
			if( recallUrl != null && recallUrl.trim().length() > 0){
				request.setAttribute("recallUrl", recallUrl);
			}
		}else{
			request.setAttribute("recallUrl", recallUrl);
		}
	    request.setAttribute("title", title);
	    request.setAttribute("message", message);
	    getServletConfig().getServletContext().getRequestDispatcher(receiptJsp).forward(request, response);
	    return true;
	}

	protected boolean sendPerceptionDataException(HttpServletRequest request, HttpServletResponse response, PerceptionDataException e, String recallUrl) 
																														throws ServletException, IOException{
		Locale locale = (Locale) request.getAttribute( LOCALE_PARAM );
		ResourceBundle bundle = PerceptionServiceManager.getErrorCodeResourceBundle( locale ); // use Locale later
		
		String title, message;
		try{
			title = bundle.getString( "error.title" );
			message = bundle.getString( String.valueOf(e.getCode()) );
		}catch( MissingResourceException bknfe ){
			title = "Error";
			message = e.getMessage();
		}
	    return sendReceipt(request, response, title, message, recallUrl, false );
	}
		
	public static boolean isUpdate(HttpServletRequest request){
		if( request != null )
			return WebUtils.readBoolean( request.getParameter( UPDATE_PARAM ), false );
		return false;
	}
			
	protected abstract boolean requiresAdminPrivileges();

	protected abstract boolean requiresControlPanelPrivileges();

	protected abstract boolean requiresCoursePrivileges();

	protected boolean isHTML(){
		return true;
	}



	/**
	 * Gets a safe system bundle string for the given context.
	 * returns key if not found
	 * @param key   string key name
	 * @param ctx   the bb context
	 */
	protected static String getSafeSystemString( String key, Locale locale ) {
		return BbUtils.getSafeSystemString( key, locale );
	}

	/**
	 * Gets a safe course bundle string for the given context.
	 * returns key if not found
	 * @param key   string key name
	 * @param ctx   the bb context
	 */
	protected static String getSafeCourseString( String key, Locale locale ) {
		return BbUtils.getSafeCourseString( key, locale );
	}
	
	protected String getDefaultRecallUrl( HttpServletRequest request, Context ctx ){
		String recallUrl;
		if( requiresAdminPrivileges() )
			recallUrl = "/bin/admin/admin_main.pl";	
		else if( requiresControlPanelPrivileges() ){
			if( ctx.getContent() != null ){ // inside a content folder - NB: content_id is never null
				StringBuffer contentUrl = new StringBuffer();
				contentUrl.append( "/bin/common/content.pl?action=LIST&render_type=EDITABLE" );
				contentUrl.append( "&course_id=" + ctx.getCourseId().toExternalString() );
				contentUrl.append( "&content_id=" + ctx.getContentId().toExternalString());
				recallUrl = contentUrl.toString();
			}else{
				recallUrl = "/bin/common/control_panel.pl?course_id=" + ctx.getCourseId().toExternalString();
			}
		}else if( requiresCoursePrivileges() )
			recallUrl = "/bin/common/course.pl?course_id=" + ctx.getCourseId().toExternalString();	
		else
			recallUrl = null;
		return recallUrl;
	}
}
