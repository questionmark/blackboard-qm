/*
 * @(#)AddGradeBookEntryServlet.java 1.0.1 Sep 1 2005
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

import blackboard.base.BbList;
import blackboard.data.ValidationException;
import blackboard.data.course.*;
import blackboard.data.gradebook.*;
import blackboard.data.gradebook.impl.*;
import blackboard.data.user.User;
import blackboard.persist.*;
import blackboard.persist.navigation.*;
import blackboard.persist.gradebook.*;
import blackboard.persist.gradebook.impl.*;
import blackboard.platform.context.Context;

import com.qm.bb6.perception.config.*;
import com.qm.bb6.perception.config.impl.SimpleContentSettings;
import com.qm.bb6.perception.util.*;
import com.qm.bb6.perception.security.*;
import com.qm.bb6.perception.service.*;

/**
 * Creates gradebook entries for assessments created by Questionmark
 *
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class AddGradeBookEntryServlet extends WebServiceServlet {
	
	public static final int COURSE_NOT_FOUND = 200;
	public static final int USER_NOT_FOUND = 201;
	public static final int ENROLMENT_NOT_FOUND = 202;
	public static final int INVALID_SCORE = 203;



	public static String ASSESSMENT_ID_PARAM = "AID";
	public static String USER_ID_PARAM = "USERID";
	public static String COURSE_ID_PARAM = "COURSEID";
	public static String SCORE_PARAM = "SCORE";
	public static String ASSESSMENT_NAME_PARAM = "ANAME";
	public static String RESULT_ID_PARAM = "RESULTID";
	public static String DATE_PARAM = "DATE";
	public static String TIME_PARAM = "TIME";
	
	public static String DEFAULT_LINEITEM_TYPE = "Assignment";
	public static String DEFAULT_PERCENTAGE_TYPE = "Percentage";

	public void doContent(HttpServletRequest request, HttpServletResponse response, Context ctx, PerceptionSettings settings, PerceptionLog log) 
																												throws WebServiceException{
		
		Random random = new Random();
		String requestUid = String.valueOf( 1000 + random.nextInt(9000) );
		
		try{
			String dateStr = getRequestParameter( request,  DATE_PARAM );
			String timeStr =  getRequestParameter( request,  TIME_PARAM );
			Calendar dateAttempted = parseDateAndTime( dateStr, timeStr, null ); // TimeZone.getDefault();
			
			String asessment_id = getRequestParameter( request,  ASSESSMENT_ID_PARAM );
			String user_id = getRequestParameter( request,  USER_ID_PARAM );
			String course_id = getRequestParameter( request,  COURSE_ID_PARAM );
			// bug fix - Perception sends COURSE_ID:COURSE_ID
			if( course_id != null && course_id.length() > 2 && (course_id.indexOf(":") >= 0) ){
				// get the middle position in the string
				int midPos = (int) (course_id.length() / 2); 
				// get the left half of the string
				String left = course_id.substring( 0, midPos );
				// get the right half of the string
				String right = course_id.substring( midPos+1 );
				
				if( left.equals( right ) ){
					course_id = left;
				}
			}
			String scoreStr = getRequestParameter( request,  SCORE_PARAM );
			String asessmentName = getRequestParameter( request,  ASSESSMENT_NAME_PARAM );
			String result_id = getRequestParameter( request,  RESULT_ID_PARAM );
			float percentageScore = (float) 0.0;
			// parse score
			try{
				percentageScore = Float.parseFloat( scoreStr );
				if( percentageScore < 0 || percentageScore > 100 )
					throw new Exception();
			}catch(Exception e){
				throw new WebServiceException( INVALID_SCORE, "Passed score is not of type float");
			}
			
			Course course;
			User user;
			CourseMembership enrolment;
			CourseSettings courseSettings;
					
			// get the persistence manager
			BbPersistenceManager bbPM = BbUtils.getBbPersistenceManager();
			
			try{
				if( settings.getUseExternalCourseId() )
					course = BbUtils.getCourseDbLoader().loadByBatchUid( course_id );
				else
					course = BbUtils.getCourseDbLoader().loadByCourseId( course_id );
				if( course == null ) throw new KeyNotFoundException("course not found");
				
				// load settings
				courseSettings = CourseSettings.load( course );
				
		//	}catch( KeyNotFoundException e ){
			}catch( Exception e ){ // a null pointer is thrown by Bb if COURSE_ID is null for some reason
				throw new WebServiceException( COURSE_NOT_FOUND, "Course Not Found: " + course_id );
			}

			try{
				if( settings.getUseExternalUserId() )
					user = BbUtils.getUserDbLoader().loadByBatchUid( user_id );
				else
					user = BbUtils.getUserDbLoader().loadByUserName( user_id );
				if( user == null ) throw new KeyNotFoundException("user not found");
			}catch( KeyNotFoundException e ){
				throw new WebServiceException( USER_NOT_FOUND, "User Not Found: " +  user_id );
			}
			
			try{
				enrolment = BbUtils.getCourseMembershipDbLoader().loadByCourseAndUserId( course.getId(), user.getId() );
				if( enrolment == null ) throw new KeyNotFoundException("enrolment not found");
			}catch( KeyNotFoundException e ){
				throw new WebServiceException( ENROLMENT_NOT_FOUND, "Enrolment Not Found" );
			}
			
			
			if( !enrolment.getIsAvailable() )
				log.logDebug( requestUid + " Enrolment is not availble" );
			
			if( enrolment.getRole() == CourseMembership.Role.GUEST )
				log.logDebug( requestUid + " Enrolment has a role of GUEST" );
				
			Lineitem lineItem = getLineitemByCourseNameAndId( log, course.getId(), asessmentName, asessment_id );
				
			boolean loadScores = true;
			
			if( lineItem == null ){
				loadScores = false;
				lineItem = createNewLineitem( log, course.getId(), asessment_id, asessmentName, settings.getCanStudentsSeeScores() );
			}

				
			Score score;
			
			try{
				score = BbUtils.getScoreDbLoader().loadByCourseMembershipIdAndLineitemId( enrolment.getId(), lineItem.getId() );
				if( score == null )
					throw new Exception(); // create new
					
				// add new attempt
				Attempt attempt = new Attempt();
				attempt.setStatus(IAttempt.Status.COMPLETED);
				attempt.setAttemptedDate( dateAttempted );
				attempt.setLinkRefId( result_id );
				attempt.setScore( percentageScore );
				attempt.setOutcomeId( score.getId() );
				//attempt.setLinkRefId( ); //?
				//attempt.setResultObjectId( ); // ?
				score.getOutcome().addAttempt( attempt );
				// check attempts to find best, last, first
				BbUtils.sortAttempts( score.getOutcome(), courseSettings.getAssessmentAttemptType(asessment_id) );
				
			}catch(Exception e){
				// score not found
				score = null;
				// create new attempt
				score = new Score();
				score.setCourseMembershipId( enrolment.getId() );
				score.setLineitemId( lineItem.getId() );
				//score.setAttemptId( result_id, Score.AttemptLocation.EXTERNAL );
			//	log.logDebug("No. of Attempts = " + score.getOutcome().getAttemptCount());
				Attempt attempt;
				if( score.getOutcome().getAttemptCount() == 1 ){
					attempt = score.getOutcome().getAttempt(0);
				}else{
					attempt = score.getOutcome().createAttempt();
				}
				attempt.setStatus(IAttempt.Status.COMPLETED);
				attempt.setAttemptedDate( dateAttempted );
				attempt.setLinkRefId( result_id );
				attempt.setScore( percentageScore );
				log.logDebug("No. of Attempts = " + score.getOutcome().getAttemptCount());
			}	
			//	score.setGrade( scoreStr )

			
			try{
				BbUtils.getScoreDbPersister().persist( score );
			}catch(Exception saveException){
				log.logError( "Failed to save score", saveException );
				throw new WebServiceException( ERROR, "Failed to save score, possible bb problem");
			}

				
		}catch(Exception e){
			log.logError( "Error create gradebook entry", e );
			log.logDebug( request.getMethod() + ": " + request.getPathInfo() );
			log.logDebug( "Querystring: " + request.getQueryString() );
			log.logDebug( "Remote Host: " + request.getRemoteHost() );
			throw new WebServiceException( ERROR, e.getMessage() );
		}
		
	}
		
		
	protected static Lineitem getLineitemByCourseNameAndId( PerceptionLog log, Id course_id, String assessmentName, String assessment_id ) {
		try{
			
			log.logDebug( "Looking for an existing lineItem in " + course_id.toExternalString() + " called " + assessmentName );
			// check to see if the assessment already has a LineItem
			BbList lineItemList = BbUtils.getLineitemDbLoader().loadByCourseIdAndLineitemName( course_id, assessmentName );
			if( lineItemList != null && lineItemList.size() > 0 ){
				log.logDebug( "Found " + lineItemList.size() + " matches for " + assessmentName );
				Iterator iterator = lineItemList.iterator();
				while( iterator.hasNext() ){
					Lineitem lineItem = (Lineitem) iterator.next();
					log.logDebug( "Attempting to match " + assessment_id + " with " + lineItem.getAssessmentId() );
					if( assessment_id.equals( lineItem.getAssessmentId() )){ // cannot do case insenstive match
						return lineItem;
					} 
				}
			}
		}catch(Exception e){
			log.logError( "Failed to load line items", e );
			// none found in db
		}
		return null;
	
	}
	
	protected static Lineitem createNewLineitem( PerceptionLog log, Id course_id, String asessment_id, String asessmentName, boolean canStudentsSeeScores ) throws PersistenceException, ValidationException {
		
		// get the persistence manager
		BbPersistenceManager bbPM = BbUtils.getBbPersistenceManager();
		
		// create new lineItem
		// bug fix for 6.1 - requires gradebook.impl

		OutcomeDefinition outcomeDef = new OutcomeDefinition();
		OutcomeDefinitionCategory outcomeDefCat;

		// fetch loaders
		OutcomeDefinitionCategoryDbLoader categoryLoader = BbUtils.getOutcomeDefinitionCategoryDbLoader(bbPM);
		OutcomeDefinitionScaleDbLoader scaleLoader = BbUtils.getOutcomeDefinitionScaleDbLoader(bbPM);
		OutcomeDefinitionDbPersister defPersister = BbUtils.getOutcomeDefinitionDbPersister(bbPM);
		
		try {
			outcomeDefCat = categoryLoader.loadByCourseIdAndTitle( course_id, DEFAULT_LINEITEM_TYPE );
		}catch(Exception e2){
			// type not found
			outcomeDefCat = new OutcomeDefinitionCategory( DEFAULT_LINEITEM_TYPE );
			outcomeDefCat.setUserDefined(true);
			outcomeDefCat.setCourseId( course_id );
			try{
				BbUtils.getOutcomeDefinitionCategoryDbPersister(bbPM).persist( outcomeDefCat );
			}catch(Exception persistException){
				log.logError( "Failed to save OutcomeDefinitionCategory", persistException );
			}

		}
					
		// scales may be in different langs so check with default types
		// safe option is NUMERIC
		OutcomeDefinitionScale scale = null;
		
		try{
			
			scale = scaleLoader.loadByCourseIdAndTitle( course_id, DEFAULT_PERCENTAGE_TYPE );
			outcomeDef.setScale( scale );

		}catch(Exception ee){

			// try App Pack 3 version
			//log.logDebug("Unable to load default scale", ee);
			try{

				scale = scaleLoader.loadByCourseIdAndTitle( course_id, DEFAULT_PERCENTAGE_TYPE + ".title" );
				outcomeDef.setScale( scale );
				
			}catch(Exception eee){
				log.logDebug("Unable to load 6.3 style scale", ee);
				// flippin 'eck, just get first scale found

				List list = scaleLoader.loadByCourseId( course_id );
				Iterator iterator = list.iterator();
				if( iterator.hasNext() ){
					scale = (OutcomeDefinitionScale) iterator.next();
					outcomeDef.setScale( scale );
				}else{
					throw new PersistenceException( "FATAL ERROR: No OutcomeDefinitionScales to use" );
				}

			}
			
		}
		
		outcomeDef.setCategoryId( outcomeDefCat.getId() );

		// create new LineItem 
		Lineitem lineItem = new Lineitem( outcomeDef );
		
		lineItem.setAssessmentId( asessment_id, Lineitem.AssessmentLocation.EXTERNAL );
		lineItem.setName( asessmentName );
	//	lineItem.setType("Assignment"); // string name, created if does not exist
		lineItem.setIsAvailable( canStudentsSeeScores ); // allows students to see
		lineItem.setColumnOrder( getNextColumnOrder(course_id) );
		lineItem.setPointsPossible( 100 ); // 100%
		lineItem.setWeight((float) 0.0); // needs to be calculated. Bug - does not save weights if no previous weights have been set
		lineItem.setAttemptHandlerUrl( BbUtils.getFullPath("Attempts") ); // TODO, might need to add ids manually
		lineItem.setAnalysisHandlerUrl( null ); // TODO, might need to add ids manually
	//	lineItem.setDateAdded(); // default
	//	lineItem.setDateAdded( cal ); // specified date		
	//	lineItem.setDateChanged(); // default
	//	lineItem.setDateChanged( cal ); // specified date	

		lineItem.setCourseId( course_id ); // current course
		
		defPersister.persist( lineItem.getOutcomeDefinition() );
		return lineItem;

	}
		
	protected static int getNextColumnOrder( Id course_id ){
		try{
			// check to see if the assessment already has a LineItem
			BbList lineItemList = BbUtils.getLineitemDbLoader().loadByCourseId( course_id );
			int maxColumnOrder = 1;
			if( lineItemList != null && lineItemList.size() == 0 ){
				
				Iterator iterator = lineItemList.iterator();
				while( iterator.hasNext() ){
					Lineitem lineItem = (Lineitem) iterator.next();
					if( lineItem.getColumnOrder() > maxColumnOrder ){
						maxColumnOrder = lineItem.getColumnOrder();
					} 
				}
			}
			return maxColumnOrder + 1;
		}catch(Exception e){
			// none found in db
		}
		return 1;
	
	}		


}
