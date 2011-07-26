/*
 * @(#)WebServiceServlet.java 1.0.1 Sep 1 2005
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
 * Base servlet used to authenticate requests coming from Perception
 * - should validate the requests and extensions should perform
 * actions such as modifying the Bb gradebook
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public abstract class WebServiceServlet extends HttpServlet {
	
	public static final int SUCCESS = 100;
	public static final int ERROR = 101;
	public static final int ACCESS_DENIED = 102;
	
	public static final String CHECKSUM_PARAM = "ACCESS";
	private static final String PARAMETER_NAMES_ATTR = "parameterNames";

	
	/**
	 *	Handles the individual UI process. No need to authenticate or set session variables in here
	 * @param request  
	 * @param response
	 * @param ctx  the blackboard Context (contains User, Course, Session info)
	 * @throws javax.servlet.ServletException
	 * @throws java.io.IOException
	 */
	abstract void doContent(HttpServletRequest request, HttpServletResponse response, Context ctx, PerceptionSettings settings, PerceptionLog log) throws WebServiceException;

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
		
		ContextManager ctxMgr = null;
		Context ctx = null;
		
		// set default headers
		response.setHeader("Pragma", "no-cache");
		response.addDateHeader("Expires", System.currentTimeMillis());
		if( isHTML() )
			response.setContentType("text/html");
				
	    try{

			
	    	// attept to fetch Bb Context manager
		    ctxMgr = (ContextManager) BbServiceManager.lookupService(ContextManager.class);
		    // set Bb Context to current request
		    ctx = ctxMgr.setContext(request);
		    		
			// load general Perception Bridge settings
			PerceptionSettings settings = PerceptionSettings.load();
			
			Vector parameterNames = new Vector();
			try{
				StringBuffer contentBuffer = new StringBuffer();
				// log content if request
				if( "POST".equalsIgnoreCase(request.getMethod()) ){
					BufferedReader in = new BufferedReader( new InputStreamReader(request.getInputStream()) );
					for( String line = in.readLine(); line != null; line = in.readLine() ){
						contentBuffer.append( line );
					}
					String[] unencodedParamPairs = contentBuffer.toString().split( "&" );
					for( int i=0; i < unencodedParamPairs.length; i++ ){
						String[] pair = unencodedParamPairs[i].split("=");
						String paramName = WebUtils.unencode(pair[0]);
						if( pair.length > 1 ){
							String paramValue = WebUtils.unencode(pair[1]);
							// can't store as request params, so add as attr
							request.setAttribute( paramName, paramValue );
							parameterNames.add( paramName );
							//log.logDebug(paramName + "=" + paramValue);
						}
					}
					
				}
			}catch(IOException ioe ){
				log.logDebug( "IOException reading request inputstream" );
			}
			request.setAttribute( PARAMETER_NAMES_ATTR, parameterNames );

			if( settings.getUseChecksum() && (! authenticatedRequest( request, settings, log )) )
				throw new WebServiceException( ACCESS_DENIED, "Could not authenticate" );
			
			doContent(request, response, ctx, settings, log);
			sendSuccess(request, response);
		}catch(WebServiceException wse){
			logParams( request, log );
			sendError( request, response, wse );
		}catch(Throwable t){
			logParams( request, log );
			// unknown error
			log.logError( "Unknown error", t );

			try{
				sendError(request, response, new WebServiceException( ERROR, t.getMessage() ));
			}catch(IllegalStateException ise){
				// response committed
			}
		} finally {
			// close context
		    if(ctxMgr != null)
		    	ctxMgr.releaseContext();
		}
	}

	
	protected boolean sendError(HttpServletRequest request, HttpServletResponse response, WebServiceException wse) throws ServletException, IOException{
	    response.getWriter().print( wse.getCode() + ": " + wse.getMessage() );
	    return true;
	}

	protected boolean sendSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    response.getWriter().print( SUCCESS + ": Success" );
	    return true;
	}
		
	protected boolean isHTML(){
		return true;
	}


	protected boolean authenticatedRequest( HttpServletRequest request, PerceptionSettings settings, PerceptionLog log ) 
																									throws WebServiceException, UnsupportedEncodingException{
		
		String checksum = getRequestParameter( request, CHECKSUM_PARAM );
		if( checksum != null && checksum.length() > 0 ){
			
			String local = getLocalCheckString( request, settings.getPIPSecretKey() );
			String localMd5 = "";
			int localChecksum = -1;
			
			if( SecurityUtils.isChecksumMd5(checksum) ){
				// try to authenticate using md5
				localMd5 = SecurityUtils.getMD5Checksum( local );
				if( localMd5.equalsIgnoreCase( checksum ) )
					return true;
				
			}
			
			if( SecurityUtils.isChecksumASCII(checksum) ){
				try{
						// try to authenticate using ASCII
					int asciiChecksum = Integer.parseInt( checksum );
					localChecksum = SecurityUtils.getASCIIChecksum( local );
					if( localChecksum == asciiChecksum )
						return true;
				}catch( NonASCIIStringException e ){
					localChecksum = -1;
					log.logError( "Import values contain non ascii characters", e );
				}
			}
			log.logDebug( "Unable to validate login" );
			log.logDebug( "LocalString=" + local );
			log.logDebug( "LocalMd5=" + localMd5 );
			log.logDebug( "LocalChecksum=" + localChecksum );
			log.logDebug( "PassedChecksum=" + checksum );
			log.logDebug( "RemoteHost=" + request.getRemoteAddr() );
		}else{
			log.logDebug( "Unable to validate login, no checksum passed" );
		}
		return false;
	}
	
	/**
	 * Guess the order of the local check string - Perception didn't seem to know themselves
	 * - fingers crossed
	 * @param request
	 * @param key   the secret key
	 * @return the string to md5 in raw format
	 * @throws com.qm.bb6.perception.service.WebServiceException
	 */
	protected String getLocalCheckString( HttpServletRequest request, String key ) throws WebServiceException, UnsupportedEncodingException {
		StringBuffer checkString = new StringBuffer();
		for( Enumeration enumeration = getParameterNames(request); enumeration.hasMoreElements(); ){
			String paramName = (String) enumeration.nextElement();
			if( !paramName.equalsIgnoreCase( CHECKSUM_PARAM ) ){
			//	checkString.append( getRequestParameter(request,paramName) );
				// need encoded values
				checkString.append( encode(getRequestParameter(request,paramName)) );
			}
		}
		if( key != null )
			checkString.append( key );
		return checkString.toString();
	}	
		
	protected static Calendar parseDateAndTime( String date, String time, TimeZone timeZone ) throws DateFormatException{
		try{
			String[] dateParts = date.split(" ");
			int day = Integer.parseInt( dateParts[0] );
			int month;
			if( dateParts[1].equalsIgnoreCase( "January") )
				month = 0;
			else if( dateParts[1].equalsIgnoreCase( "February") )
				month = 1;
			else if( dateParts[1].equalsIgnoreCase( "March") )
				month = 2;
			else if( dateParts[1].equalsIgnoreCase( "April") )
				month = 3;
			else if( dateParts[1].equalsIgnoreCase( "May") )
				month = 4;
			else if( dateParts[1].equalsIgnoreCase( "June") )
				month = 5;
			else if( dateParts[1].equalsIgnoreCase( "July") )
				month = 6;
			else if( dateParts[1].equalsIgnoreCase( "August") )
				month = 7;
			else if( dateParts[1].equalsIgnoreCase( "September") )
				month = 8;
			else if( dateParts[1].equalsIgnoreCase( "October") )
				month = 9;
			else if( dateParts[1].equalsIgnoreCase( "November") )
				month = 10;
			else if( dateParts[1].equalsIgnoreCase( "December") )
				month = 11;
			else
				throw new DateFormatException();
			int year = Integer.parseInt( dateParts[2] );
			
			String[] timeParts = time.split(":");
			int hour = Integer.parseInt( timeParts[0] );
			int minute = Integer.parseInt( timeParts[1] );
			
			Calendar dateTime = timeZone == null ? Calendar.getInstance() : Calendar.getInstance( timeZone );
			dateTime.set( year, month, day, hour, minute, 0 );
			return dateTime;
		}catch( Exception e ){
			throw new DateFormatException();
		}
	}
	
	private static void logParams( HttpServletRequest request, PerceptionLog log ){
		
		for(Enumeration e=request.getHeaderNames(); e.hasMoreElements();){
			String paramName = (String) e.nextElement();
			String value = request.getHeader( paramName );
			if( value != null ){
				log.logDebug( paramName + "=" + value );
			}else{
				log.logDebug( paramName + "=" + "" );
			}
		}
		for(Enumeration e=request.getParameterNames(); e.hasMoreElements();){
			String paramName = (String) e.nextElement();
			String[] values = request.getParameterValues( paramName );
			if( values != null && values.length > 0 ){
				for( int i=0; i<values.length; i++){
					log.logDebug( paramName + "=" + values[i] );
				}
			}else{
				log.logDebug( paramName + "=" + "" );
			}
		}
		
		try{
			// log content if request
			if( "POST".equalsIgnoreCase(request.getMethod()) ){
				BufferedReader in = new BufferedReader( new InputStreamReader(request.getInputStream()) );
				for( String line = in.readLine(); line != null; line = in.readLine() ){
					log.logDebug( line );
				}
				
			}
		}catch(IOException ioe ){
			log.logDebug( "IOException reading request inputstream" );
		}
		
	}
	
	/**
	 * Solves the bug in Perception where input parameters are not read
	 * @return value or null
	 */
	protected String getRequestParameter( HttpServletRequest request, String name ){
		try{
			String properValue = request.getParameter( name );
			if( properValue != null )
				return properValue;
			return (String) request.getAttribute( name );
		}catch(Exception e){ // null and class cast
			return null;
		}
	}

	protected Enumeration getParameterNames( HttpServletRequest request ){
		if( request.getParameterNames().hasMoreElements() ){
			return request.getParameterNames();
		}else{
			return ((Vector) request.getAttribute( PARAMETER_NAMES_ATTR )).elements();
		}
	}
	
	/**
	 * Perception uses it's own non standard (standard being UTF-8) encoding
	 */
	private String encode( String str ) throws UnsupportedEncodingException{
		String utf8Str = WebUtils.parseQueryValue( str );
		// now encode '+' (space) '_', '*', '.' and '-'
		utf8Str = utf8Str.replaceAll("\\+", "%20");
		utf8Str = utf8Str.replaceAll("\\_", "%5F");
		utf8Str = utf8Str.replaceAll("\\*", "%2A");
		utf8Str = utf8Str.replaceAll("\\.", "%2E");
		utf8Str = utf8Str.replaceAll("\\-", "%2D");
		return utf8Str;	
	}
	
	protected static String stripUnnessaryPrepend( String src ){
		// bug fix - Perception sends COURSE_ID:COURSE_ID
		if( src != null && src.length() > 2 && (src.indexOf(":") >= 0) ){
			// get the middle position in the string
			int midPos = (int) (src.length() / 2); 
			// get the left half of the string
			String left = src.substring( 0, midPos );
			// get the right half of the string
			String right = src.substring( midPos+1 );

			if( left.equals( right ) ){
				return left;
			}else{
				return right;
			}
		}
		return src;
	}
	
}
