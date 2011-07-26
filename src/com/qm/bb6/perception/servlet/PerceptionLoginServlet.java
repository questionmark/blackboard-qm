/*
 * @(#)PerceptionLoginServlet.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.servlet;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import blackboard.data.course.*;
import blackboard.platform.context.Context;
import blackboard.platform.security.authentication.RDBMSAuthUtil;

import com.qm.bb6.perception.config.*;
import com.qm.bb6.perception.context.*;
import com.qm.bb6.perception.data.*;
import com.qm.bb6.perception.util.*;
import com.qm.bb6.perception.security.*;
import com.qm.bb6.perception.service.*;

/**
 * Logs the user into Perception
 *
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class PerceptionLoginServlet extends AuthenticatedServlet {
	
	public static final String CALL_PARAM = "CALL";
	public static final String COURSE_ID_PARAM = "COURSEID"; //"GROUP";
	public static final String USER_ID_PARAM = "USERID";
	public static final String EXPIRES_PARAM = "EXPIRES";
	public static final String ACCESS_PARAM = "ACCESS";
	public static final String NOTIFY_PARAM = "NOTIFY";
	
	public static final String WINDOW_PARAM = "win";
	public static final String SHOW_TOOLBARS_PARAM = "show_toolbars";
	public static final String TARGET_URL_PARAM = "target_url";
	
	//added 4.4
	public static final String CONTENT_ID_PARAM = "CONTENTID";
	
	
	public boolean doContent(HttpServletRequest request, HttpServletResponse response, Context ctx) throws ServletException, IOException{
		
		Locale locale = (Locale) request.getAttribute( LOCALE_PARAM );
		
		try{
			
			// sync if not in Perception
			PerceptionContext perceptionContext = PerceptionContext.getInstance( ctx );
			// get bridge settings
			PerceptionSettings settings = PerceptionSettings.loadFromCache();

			if( launchEMIfPossible(settings, request, ctx) && settings.canLaunchEnterpriseManager( ctx ) ){
				getServletConfig().getServletContext().getRequestDispatcher("/LaunchEnterpriseManager").forward(request, response);
				return true;
			}
			
			PerceptionUser participant = perceptionContext.getPerceptionUser();

			// get login url
			String baseLoginUrl = getLoginUrl( settings, request, ctx );
			
			// create buffers to append parameters onto
			StringBuffer loginUrl = new StringBuffer( baseLoginUrl );
			StringBuffer hashBuffer = new StringBuffer();
			
			// get parameter map for the this request - may have been overloaded
			Map parameterMap = getPerceptionParameterMap( settings, request, ctx );
			
			boolean startedQuery = (loginUrl.indexOf( "?" ) >= 0 );
			
			// Iterate through params
			for( Iterator iterator = parameterMap.keySet().iterator(); iterator.hasNext(); ){
				String paramName = iterator.next().toString();
				Object value = parameterMap.get( paramName );
				if( value != null ){
					String paramValue = value.toString();
					if( !startedQuery ){
						loginUrl.append( "?" );
						startedQuery = true;
					}else{
						loginUrl.append( "&" );
					}
					loginUrl.append( WebUtils.parseQueryValue(paramName) + "=" + WebUtils.parseQueryValue(paramValue) );
					hashBuffer.append( paramValue );
				}
					
			}
			
			// add secret key to hash buffer
			hashBuffer.append( settings.getPIPSecretKey() );
			
			// add checksum to querystring
			if( loginUrl.indexOf( "?" ) >= 0 ){ // don't do if no params
				
				String checksum = SecurityUtils.getMD5Checksum( hashBuffer.toString() ).toLowerCase(); // lower case is needed by Perception
				loginUrl.append( "&" + WebUtils.parseQueryValue(ACCESS_PARAM) + "=" + checksum );
				
			}
			
			int target = WebUtils.readInt( request.getParameter(WINDOW_PARAM), PerceptionSettings.CURRENT_FRAME );
			
			if( target == PerceptionSettings.NEW_WINDOW || target == PerceptionSettings.BARLESS_WINDOW ){
				request.setAttribute( SHOW_TOOLBARS_PARAM, new Boolean(target == PerceptionSettings.BARLESS_WINDOW ) );	
				request.setAttribute( TARGET_URL_PARAM, loginUrl.toString() );			
				getServletConfig().getServletContext().getRequestDispatcher("/jsp/window_launcher.jsp").forward(request, response);			
			}else{
				response.sendRedirect( loginUrl.toString() );
				//response.getWriter().println( loginUrl.toString() );
			}

		}catch( PerceptionNotFoundException e ){
			return this.sendError(request, response, getSafeCourseString("perception-not-found.title", locale), getSafeCourseString("perception-not-found.message", locale));
		}catch( PerceptionSecurityException e ){
			return this.sendError(request, response, getSafeSystemString("access-denied.title", locale), getSafeSystemString("access-denied.message", locale));
		}catch( BlackboardUserNotFoundException e ){ // TODO
			return this.sendError(request, response, getSafeSystemString("access-denied.title", locale), getSafeSystemString("access-denied.message", locale));
		}catch( BlackboardCourseNotFoundException e ){ // TODO
			return this.sendError(request, response, getSafeCourseString("course-not-found.title", locale), getSafeCourseString("course-not-found.message", locale));
		}catch( PerceptionDataException e ){ // TODO
			return sendPerceptionDataException(request, response, e, null);
		}
		return true;
	}
			

	protected boolean requiresAdminPrivileges(){
		return false;
	}

	protected boolean requiresControlPanelPrivileges(){
		return false;
	}

	protected boolean requiresCoursePrivileges(){
		return true;
	}
	
	// added for 4.3
	protected boolean launchEMIfPossible( PerceptionSettings settings, HttpServletRequest request, Context ctx ){
		return true;
	}
	
	protected Map getPerceptionParameterMap( PerceptionSettings settings, HttpServletRequest request, Context ctx ) throws java.io.UnsupportedEncodingException {
	
		Map paramMap = new HashMap();
		paramMap.put( CALL_PARAM, "blackboardconnector");
		if( settings.getUseExternalUserId() ){
			paramMap.put( USER_ID_PARAM, ctx.getUser().getBatchUid() );
		}else{
			paramMap.put( USER_ID_PARAM, ctx.getUser().getUserName() );
		}
		if( settings.getUseExternalCourseId() ){
			paramMap.put( COURSE_ID_PARAM, ctx.getCourse().getBatchUid() );
		}else{
			paramMap.put( COURSE_ID_PARAM, ctx.getCourse().getCourseId() );
		}
		paramMap.put( EXPIRES_PARAM, getExpiresAsString() );
	
		// only add notify url if user is student
		if( ctx.getCourseMembership() != null && ctx.getCourseMembership().getRole() == CourseMembership.Role.STUDENT ) {
			// create nofiy link
			StringBuffer notifyUrl = new StringBuffer();
			notifyUrl.append( request.getScheme() + "://" ); // http/https
			notifyUrl.append( request.getServerName() ); // perception.my.edu
			if( request.getServerPort() != 80 ){
				notifyUrl.append( ":" + request.getServerPort() );
			}
			notifyUrl.append( BbUtils.getFullPath( "AddGradeBookEntry" ) );
			
			if( !BbUtils.isNullOrEmpty(ctx.getContentId()) ){
				// added 4.4
				notifyUrl.append( "?" + CONTENT_ID_PARAM + "=" + ctx.getContentId().toExternalString() );
				paramMap.put( CONTENT_ID_PARAM, ctx.getCourse().getCourseId() );
				paramMap.put( EXPIRES_PARAM, getExpiresAsString() );
			}

			paramMap.put( NOTIFY_PARAM, notifyUrl.toString() );
        }else{
		//	System.out.println( "ctx.getCourseMembership()=" + (ctx.getCourseMembership() == null) );
		//	System.out.println( "ctx.getContentId()=" + (ctx.getContentId()) );
		//	System.out.println( "is student=" + (ctx.getCourseMembership().getRole() == CourseMembership.Role.STUDENT) );
		}
		
		return paramMap;
	}
	
	protected String getExpiresAsString(){
		try{
			SimpleDateFormat basicFormat = new SimpleDateFormat("dd:MM:yyyy:HH:mm");
			Calendar cal = Calendar.getInstance();
			cal.add( Calendar.HOUR, 12 );
			return basicFormat.format( cal.getTime() );
		}catch(Exception e){
			throw new IllegalArgumentException("Unable to format date into Perception format");
		}

	}
	
	protected String getLoginUrl( PerceptionSettings settings, HttpServletRequest request, Context ctx ){
		// let perception reject them
		return settings.getPerceptionFullUrl();		
	}
	
	// added 4.4
	protected boolean launchInNewWindow(){
		return false;
	}
	
	protected boolean toolBarFreeWindow(){
		return false;
	}
}
