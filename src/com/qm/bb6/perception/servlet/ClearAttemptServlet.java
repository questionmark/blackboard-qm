/*
 * @(#)ClearAttemptServlet.java 1.0.1 Sep 1 2005
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
public class ClearAttemptServlet extends AuthenticatedServlet {
	
	public static final String ATTEMPT_ID_PARAM = "attempt_id";
	
	public boolean doContent(HttpServletRequest request, HttpServletResponse response, Context ctx) throws ServletException, IOException{
		
		Locale locale = (Locale) request.getAttribute( LOCALE_PARAM );
		PerceptionSettings settings = (PerceptionSettings) request.getAttribute( PerceptionSettings.class.getName() );
		PerceptionLog log = PerceptionServiceManager.getPerceptionLog();

		try{
			
			// fetch the bbPM
			BbPersistenceManager bbPM = BbUtils.getBbPersistenceManager();
			
			String attempt_id_str = request.getParameter( ATTEMPT_ID_PARAM );
			Id attempt_id = bbPM.generateId( Attempt.DATA_TYPE, attempt_id_str );
			Attempt attempt = BbUtils.getAttemptDbLoader( bbPM ).loadById( attempt_id );
			Outcome outcome = getOutcomeDbLoader( bbPM ).loadById( attempt.getOutcomeId() );
			if( !BbUtils.isMatchingId( ctx.getCourseMembership().getId(), outcome.getCourseMembershipId() )){
				// this attempt does belong to the current user
				if( settings.canClearAttempts( ctx ) ){
					// load requested enrolment to check it belongs in the current course
					CourseMembership enrolment = BbUtils.getCourseMembershipDbLoader().loadById( outcome.getCourseMembershipId() );
					if( !BbUtils.isMatchingId( ctx.getCourseId(), enrolment.getCourseId() )){
						// not an enrolment for this course - bad instructor
						throw new PerceptionSecurityException("Enrolment requested does not belong to the current course");
					}
				}else{
					throw new PerceptionSecurityException("Cannot access coaching reports for another user");
				}
			}
			
			// remove attempt from Outcome
			Attempt[] attempts = outcome.getAttempts();
			// cannot be null or empty
			for( int i = 0; i < attempts.length; i++ ){
				if( BbUtils.isMatchingId( attempts[i].getId(), attempt_id ) ){
					// remove
					outcome.removeAttempt( i );
					// bug in bb - we need to delete in manually
					BbUtils.getAttemptDbPersister( bbPM ).deleteById( attempts[i].getId() );
					break;
				}
			}
			// refresh
			attempts = outcome.getAttempts();
			if( attempts != null && attempts.length > 0 ){
			
				// need to load course settings
				CourseSettings courseSettings = CourseSettings.load( ctx.getCourse() );
				
				// check attempts to find best, last, first
				BbUtils.sortAttempts( outcome, courseSettings.getGradeBookResultsSelection() );
				
				// persist outcome
				getOutcomeDbPersister( bbPM ).persist( outcome );
				
			}
			
			String recallUrl = (String) request.getParameter( "recallUrl" );
			if( recallUrl == null ){
				// return to attempts servlet
				StringBuffer attemptsUrl = new StringBuffer();
				attemptsUrl.append( BbUtils.getFullPath( "Attempts" ) );
				attemptsUrl.append( "?course_id=" );
				attemptsUrl.append( WebUtils.parseQueryValue( ctx.getCourseId().toExternalString() ) );
				attemptsUrl.append( "&" + AttemptsServlet.OUTCOME_DEF_ID_PARAM + "=" );
				attemptsUrl.append( WebUtils.parseQueryValue( outcome.getOutcomeDefinitionId().toExternalString() ) );
				attemptsUrl.append( "&" + AttemptsServlet.ENROLMENT_ID_PARAM + "=" );
				attemptsUrl.append( WebUtils.parseQueryValue( outcome.getCourseMembershipId().toExternalString() ) );
				response.sendRedirect( attemptsUrl.toString() );
			}else{
				response.sendRedirect( recallUrl );
			}
			return true;
		}catch( Exception e ){
			log.logDebug( "Unable to remove attempt", e );
			return this.sendError(request, response, getSafeCourseString("attempt-not-found.title", locale), getSafeCourseString("attempt-not-found.message", locale));
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
	
    public static OutcomeDbLoader getOutcomeDbLoader(BbPersistenceManager bbPM) throws PersistenceException {
		return ((OutcomeDbLoader) bbPM.getLoader(OutcomeDbLoader.TYPE));
    }

    public static OutcomeDbPersister getOutcomeDbPersister(BbPersistenceManager bbPM) throws PersistenceException {
		return ((OutcomeDbPersister) bbPM.getPersister(OutcomeDbPersister.TYPE));
    }
	
}
