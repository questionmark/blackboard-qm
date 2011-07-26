/*
 * @(#)AttemptsServlet.java 1.0.1 Sep 1 2005
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

import blackboard.data.course.*;
import blackboard.data.gradebook.impl.*;
import blackboard.persist.*;
import blackboard.platform.context.Context;

import com.qm.bb6.perception.config.*;
import com.qm.bb6.perception.util.*;

/**
 * Displays attempts for an individual student
 *
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class AttemptsServlet extends AuthenticatedServlet {
	
	// Bb input params
	public static String ENROLMENT_ID_PARAM = "courseMembershipId";
	public static String OUTCOME_DEF_ID_PARAM = "outcomeDefinitionId";

	// request attrs
	public static String ENROLMENT_PARAM = "studentEnrolment";
	public static String OUTCOME_DEF_PARAM = "outcomeDef";
	
	public boolean doContent(HttpServletRequest request, HttpServletResponse response, Context ctx) throws ServletException, IOException{
		
		Locale locale = (Locale) request.getAttribute( LOCALE_PARAM );
		if( ctx.getCourseMembership().getRole() == CourseMembership.Role.STUDENT ){
			
			CourseSettings courseSettings = CourseSettings.load( ctx.getCourse() );
			if( courseSettings.getCanStudentsSeeReport() ){
				// redirect to coaching report
	
				StringBuffer reportUrl = new StringBuffer();
				reportUrl.append( BbUtils.getFullPath( "LaunchCoachingReport" ) );
				reportUrl.append("?" + request.getQueryString() );
				response.sendRedirect( reportUrl.toString() );
				return true;
			}else{
				// coaching reports are disabled for this course
				return sendError( request, response, getSafeCourseString("coaching-reports-disabled.title", locale),
															getSafeCourseString("coaching-reports-disabled.message", locale));
			}
			
		}else if( isUpdate(request) ){
			
			// nothing to do here

		}else{
			
			// get the bbPM
			BbPersistenceManager bbPM = BbUtils.getBbPersistenceManager();
			
			// should have a course membership id in the query
			CourseMembership enrolment;
			try{
				// lets attempt to load the user's enrolment and add it to the request
				String enrolmentIdStr = request.getParameter( ENROLMENT_ID_PARAM );
				Id enrolment_id = bbPM.generateId( CourseMembership.DATA_TYPE, enrolmentIdStr );
				enrolment = BbUtils.getCourseMembershipDbLoader().loadById( enrolment_id, null, true );
				if( enrolment == null || enrolment.getRole() == CourseMembership.Role.GUEST )
					throw new NullPointerException(); // cannot be null or guest
			}catch(Exception e){
				// enrolment probably doesn't exist or disabled
				return sendError( request, response, getSafeCourseString("attempts-enrolment-not-found.title", locale), getSafeCourseString("attempts-enrolment-not-found.message", locale));
			}
			
			if( !BbUtils.isMatchingId( enrolment.getCourseId(), ctx.getCourseId() ) ){
				// enrolment belongs to another course - bad instructor
				return sendError( request, response, getSafeCourseString("attempts-course-not-match.title", locale), getSafeCourseString("attempts-course-not-match.message", locale));				
			}
			
			// now attempt to load outcome def from querystring
			OutcomeDefinition outcomeDef;
			try{
				String outcomeDefIdStr = request.getParameter( OUTCOME_DEF_ID_PARAM );
				Id outcomedef_id = bbPM.generateId( OutcomeDefinition.DATA_TYPE, outcomeDefIdStr );
				outcomeDef = BbUtils.getOutcomeDefinitionDbLoader( bbPM ).loadById( outcomedef_id );
				if( outcomeDef == null )
					throw new NullPointerException();
				
				// add the outcomeDef to the request
				request.setAttribute( OUTCOME_DEF_PARAM, outcomeDef );
				
			}catch(Exception e){
				// outcome def not found. non critical, just display all attempts for all assessments instead
				outcomeDef = null;
			}
			
			// add the enrolment to the request
			request.setAttribute( ENROLMENT_PARAM, enrolment );
			// send course admin page
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/attempts.jsp").forward(request, response);
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
	
	/**
	 * Overrides base method to return users to the gradebook and not the control panel
	 */
	protected String getDefaultRecallUrl( HttpServletRequest request, Context ctx ){
		
		if( ctx.getCourseMembership().getRole() == CourseMembership.Role.STUDENT ){
			String recallUrl = "javascript:history.go(-1);";
			return recallUrl;
		}else{
			String recallUrl = "/webapps/gradebook/do/instructor/viewSpreadsheet?course_id=" + ctx.getCourseId().toExternalString();
			return recallUrl;			
		}
	}

}
