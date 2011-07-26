/*
 * @(#)LaunchCoachingReportServlet.java 1.0.1 Sep 1 2005
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
import blackboard.data.gradebook.*;
import blackboard.data.gradebook.impl.*;
import blackboard.persist.*;
import blackboard.persist.gradebook.impl.*;
import blackboard.platform.context.Context;

import com.qm.bb6.perception.config.*;
import com.qm.bb6.perception.context.*;
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
public class LaunchCoachingReportServlet extends AuthenticatedServlet {
	
	public static final String ATTEMPT_ID_PARAM = "attempt_id";

	private static class AccessCoachingReportLoader extends PerceptionSOAPRequestor {
		
		public static final String GET_ACCESS_REPORT = "GetAccessReport";
		public static final String GET_ACCESS_URL = "URL";
		public static final String RESULT_ID_PARAM = "Result_ID";

		
		public String getAccessUrl( String result_id ) throws PerceptionDataException{
			SOAPMappingRegistry smr = new SOAPMappingRegistry();	
			addIDResponseMapping( smr, GET_ACCESS_URL );
			return (String) loadById( GET_ACCESS_REPORT, RESULT_ID_PARAM, result_id, smr );
	
		}
		
		protected int getTimeout( String methodName ){
			return 5000; // 5 seconds
		}
	}
	
	
	public boolean doContent(HttpServletRequest request, HttpServletResponse response, Context ctx) throws ServletException, IOException{
		
		Locale locale = (Locale) request.getAttribute( LOCALE_PARAM );
		PerceptionSettings settings = (PerceptionSettings) request.getAttribute( PerceptionSettings.class.getName() );
		PerceptionLog log = PerceptionServiceManager.getPerceptionLog();

		try{
			
			CourseSettings courseSettings = CourseSettings.load( ctx.getCourse() );
			
			// fetch the bbPM
			BbPersistenceManager bbPM = BbUtils.getBbPersistenceManager();
			
			String attempt_id_str = request.getParameter( ATTEMPT_ID_PARAM );
			Id attempt_id = bbPM.generateId( Attempt.DATA_TYPE, attempt_id_str );
			Attempt attempt = BbUtils.getAttemptDbLoader( bbPM ).loadById( attempt_id );
			Outcome outcome = BbUtils.getOutcomeDbLoader( bbPM ).loadById( attempt.getOutcomeId() );
			if( BbUtils.isMatchingId( ctx.getCourseMembership().getId(), outcome.getCourseMembershipId() )){
				// this attempt does belong to the current user
				if( courseSettings.getCanStudentsSeeReport() && isStudent(ctx) ){
					// student
				}else{
					throw new PerceptionSecurityException("Access to coaching reports has been disabled by your course instructor");
				}
			}else if( settings.canAccessCoachingReports( ctx ) ){
				// ta or instructor
			}else{
				throw new PerceptionSecurityException("Cannot access coaching reports for another user");
			}
			
			// load requested enrolment to check it belongs in the current course
			if( true ){
				CourseMembership enrolment = BbUtils.getCourseMembershipDbLoader().loadById( outcome.getCourseMembershipId() );
				if( !BbUtils.isMatchingId( ctx.getCourseId(), enrolment.getCourseId() )){
					// not an enrolment for this course - bad instructor
					throw new PerceptionSecurityException("Enrolment requested does not belong to the current course");
				}
			}
			
			AccessCoachingReportLoader urlLoader = new AccessCoachingReportLoader();
			String accessUrl = urlLoader.getAccessUrl( attempt.getLinkRefId() );
			
			if( accessUrl.matches( ".+\\://localhost/.+") ){
				return this.sendError(request, response, getSafeCourseString("hostname-is-localhost.title", locale), getSafeCourseString("hostname-is-localhost.message", locale));
			}else{
				log.logDebug( "AccessUrl=" + accessUrl );
			}
			response.sendRedirect( accessUrl );
			return true;
		}catch( PersistenceException e ){
			log.logDebug( "Unable to load attempt", e );
			return this.sendError(request, response, getSafeCourseString("attempt-not-found.title", locale), getSafeCourseString("attempt-not-found.message", locale));
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
		return false;
	}

	protected boolean requiresCoursePrivileges(){
		return true;
	}
		
	public static boolean isStudent( Context ctx ){
		CourseMembership.Role role = ctx.getCourseMembership().getRole();
		
		// TODO
		return ( role == CourseMembership.Role.STUDENT );
		
	}
}
