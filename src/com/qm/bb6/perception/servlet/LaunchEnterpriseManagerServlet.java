/*
 * @(#)LaunchEnterpriseManagerServlet.java 1.0.1 Sep 1 2005
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

import com.qm.bb6.perception.config.*;
import com.qm.bb6.perception.context.*;
import com.qm.bb6.perception.data.impl.AdministratorDeserializer;
import com.qm.bb6.perception.util.*;
import com.qm.bb6.perception.security.*;
import com.qm.bb6.perception.service.*;

import org.apache.soap.encoding.SOAPMappingRegistry;
import org.apache.soap.rpc.Parameter;

/**
 * Logs the administrator into Perception Enterprise Manager
 *
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class LaunchEnterpriseManagerServlet extends AuthenticatedServlet {
	
	private static class AccessAdministratorLoader extends PerceptionSOAPRequestor {
		
		public static final String GET_ACCESS_ADMINISTRATOR = "GetAccessAdministrator";
		public static final String GET_ACCESS_URL = "URL";
		
		public String getAccesUrl( String administratorName ) throws PerceptionDataException{
			SOAPMappingRegistry smr = new SOAPMappingRegistry();	
			addIDResponseMapping( smr, GET_ACCESS_URL );
			return (String) loadById( GET_ACCESS_ADMINISTRATOR, AdministratorDeserializer.ADMINISTRATOR_NAME_PARAM, administratorName, smr );
	
		}
		
		protected int getTimeout( String methodName ){
			return 5000; // 5 seconds
		}
	}
	
	
	public boolean doContent(HttpServletRequest request, HttpServletResponse response, Context ctx) throws ServletException, IOException{
		
		Locale locale = (Locale) request.getAttribute( LOCALE_PARAM );
		PerceptionSettings settings = (PerceptionSettings) request.getAttribute( PerceptionSettings.class.getName() );
		PerceptionLog log = PerceptionServiceManager.getPerceptionLog();
		PerceptionContext perceptionContext;
		try{
			if( !settings.canLaunchEnterpriseManager( ctx ) )
				throw new PerceptionSecurityException();
				
			perceptionContext = PerceptionContext.getInstance( ctx );
			AccessAdministratorLoader urlLoader = new AccessAdministratorLoader();
			String accessUrl = urlLoader.getAccesUrl( perceptionContext.getPerceptionUser().getUserName() );
			
			if( accessUrl.matches( ".+\\://localhost/.+") ){
				return this.sendError(request, response, getSafeCourseString("hostname-is-localhost.title", locale), getSafeCourseString("hostname-is-localhost.message", locale));
			}else{
				log.logDebug( "AccessUrl=" + accessUrl );
			}
			response.sendRedirect( accessUrl );
			return true;
		}catch( PerceptionNotFoundException e ){
			log.logDebug( "Unable to launch EM", e );
			return this.sendError(request, response, getSafeCourseString("perception-not-found.title", locale), getSafeCourseString("perception-not-found.message", locale));
		}catch( PerceptionSecurityException e ){
			log.logDebug( "Unable to launch EM", e );
			return this.sendError(request, response, getSafeSystemString("access-denied.title", locale), getSafeSystemString("access-denied.message", locale));
		}catch( PerceptionDataException e ){ // TODO
			log.logDebug( "Unable to launch EM", e );
			return sendPerceptionDataException(request, response, e, null);
		}
	}
			

	protected boolean requiresAdminPrivileges(){
		return false;
	}

	protected boolean requiresControlPanelPrivileges(){
		return true;
	}

	protected boolean requiresCoursePrivileges(){
		return true;
	}
	
}
