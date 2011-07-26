/*
 * @(#)RemoveAssessmentServlet.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.servlet;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import blackboard.base.*;
import blackboard.data.content.Content;
import blackboard.data.course.*;
import blackboard.data.user.User;
import blackboard.data.ValidationException;
import blackboard.persist.*;
import blackboard.platform.BbServiceManager;
import blackboard.platform.context.*;

import com.qm.bb6.perception.config.*;
import com.qm.bb6.perception.config.impl.SimpleContentSettings;
import com.qm.bb6.perception.context.*;
import com.qm.bb6.perception.data.*;
import com.qm.bb6.perception.util.*;
import com.qm.bb6.perception.security.*;
import com.qm.bb6.perception.service.*;

/**
 * Removes schedules and settings for a given assessment link
 *
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class RemoveAssessmentServlet extends HttpServlet {
	
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
				
	    try{

			// attempt to fetch current system Locale	
			locale = BbUtils.getLocale( request );

	    	// attept to fetch Bb Context manager
		    ctxMgr = (ContextManager) BbServiceManager.lookupService(ContextManager.class);
		    // set Bb Context to current request
		    ctx = ctxMgr.setContext(request);
		    if (ctx == null || ctx.getUser() == null) {
		    	// context not found - redirect to login page
				throw new AccessDeniedException("Error: You are not logged in!");
			}

			// attempt to fetch current user Locale		
			locale = BbUtils.getLocale( request, ctx );
			
			// load general Perception Bridge settings
			PerceptionSettings settings = PerceptionSettings.load();

			// check privileges 
			// if SYSTEM_ADMIN always return true
			if( ctx.getUser().getSystemRole() == User.SystemRole.SYSTEM_ADMIN ){
				// always has access
				
			}else if( !BbUtils.hasControlPanelPrivileges( settings, ctx ) ){
				throw new AccessDeniedException();
			}else{
				// ok
			}
			
			log.logDebug( "Attempting to remove assessment schedules" );
			if( settings.canRemoveContent( ctx ) ){
			
				// load perception context - errors logged only
				PerceptionContext perceptionContext = PerceptionContext.getInstance( ctx );
	
				Content content = ctx.getContent();
						
				// load Perception course settings - added 4.4
				CourseSettings courseSettings = CourseSettings.load( ctx.getCourse() );
				
				// load Perception content settings
				ContentSettings contentSettings = SimpleContentSettings.load( courseSettings, content );
						
				// get schedule persister
				PerceptionSchedulePersister schedulePersister = PerceptionServiceManager.getPerceptionSchedulePersister();
				
				// get participant schedules to delete
				String[] participantSchedules = contentSettings.getParticipantScheduleIds();
				
				// NOTE: for added securiy we could load schedules and check the group, user keys before we try to delete them from Perception
				// content_id is validated within SimpleContentSettings
				int removedCount = 0;
				
				if( participantSchedules != null ){
					for( int i=0; i<participantSchedules.length; i++ ){
						try{
							schedulePersister.deleteByScheduleId( participantSchedules[i] );
							removedCount++;
						}catch( ObjectNotFoundException obfe ){
							// ignore	
						}catch( Exception e ){
							// ignore
							log.logError( "Failed to remove participant schedule", e );
						}
					}
					contentSettings.setParticipantScheduleIds( null );
				}
				
				String schedule_id = contentSettings.getScheduleId(); // single group based schedule
				if( schedule_id != null ){
					try{
						schedulePersister.deleteByScheduleId( schedule_id );
						contentSettings.setScheduleId( null );
						removedCount++;
					}catch( ObjectNotFoundException obfe ){
						// ignore	
						contentSettings.setScheduleId( null );
					}catch( Exception e ){
						// ignore
						log.logError( "Failed to remove group schedule", e );
					}
					
				}
				
				log.logDebug( "Removed " + removedCount + " schedules" );

				
				// delete content settings
			}

		
		}catch(AccessDeniedException ade){
			log.logError( "AccessDeniedException removing assessment", ade );
		}catch(Exception e){
			// unknown error
			log.logError( "Unknown error", e );
		} finally {
			// close context
		    if(ctxMgr != null)
		    	ctxMgr.releaseContext();
		}
	}
		



}
