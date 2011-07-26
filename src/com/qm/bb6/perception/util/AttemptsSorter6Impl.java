/*
 * @(#)AttemptsSorter6Impl.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.util;

import java.util.*;

import blackboard.base.*;
import blackboard.data.gradebook.*;
import blackboard.data.gradebook.impl.*;
import blackboard.persist.*;
import blackboard.persist.gradebook.*;
import blackboard.persist.gradebook.impl.*;

import com.qm.bb6.perception.config.PerceptionSettings;

public class AttemptsSorter6Impl implements AttemptsSorter {
	
	/**
	 * Orders a list of attempts to set the first, best or last as the selected attempt
	 * @see com.qm.bb6.perception.config.CourseSettings
	 */
	public void sortAttempts( Outcome outcome, int selectionType ) throws PersistenceException {
		
		//System.out.println( "Sorting attempts..." );
		
		// get attempts from Outcome
		Attempt[] attempts = outcome.getAttempts();
		
		if( attempts == null || attempts.length == 1 ){
			// nothing to do
		}else if( selectionType == PerceptionSettings.AVERAGE_RESULT ){
			// not implemented
		}else{

			int position = 0;
			float bestScore = (float) 0.0;
			Calendar bestAttemptDate = null;
			for( int i=0; i < attempts.length; i++ ){

				if( selectionType == PerceptionSettings.BEST_RESULT ){
					//System.out.println( "Getting best result (" + attempts[ i ].getScore() + ", " +  bestScore + ")");
					if( attempts[ i ].getScore() >= bestScore ){ // take latest if same
						bestScore = attempts[ i ].getScore();
						position = i;
					}
				}else if( selectionType == PerceptionSettings.FIRST_RESULT ){
					// do nothing
					//System.out.println( "Getting first result (" + attempts[ i ].getAttemptedDate() + ", " +  bestAttemptDate + ")");
					if( bestAttemptDate == null || attempts[ i ].getAttemptedDate().before(bestAttemptDate) ){
						bestAttemptDate = attempts[ i ].getAttemptedDate();
						position = i;
					}
				}else if( selectionType == PerceptionSettings.LAST_RESULT ){
					//System.out.println( "Getting last result (" + attempts[ i ].getAttemptedDate() + ", " +  bestAttemptDate + ")");
					if( bestAttemptDate == null || attempts[ i ].getAttemptedDate().after(bestAttemptDate) ){
						bestAttemptDate = attempts[ i ].getAttemptedDate();
						position = i;
					}
				}else if( selectionType == PerceptionSettings.WORST_RESULT ){
					//System.out.println( "Getting worst result (" + attempts[ i ].getScore() + ", " +  bestScore + ")");
					if( i == 0 || attempts[ i ].getScore() <= bestScore ) { // take latest if same
						bestScore = attempts[ i ].getScore();
						position = i;
					}
				}else if( selectionType == PerceptionSettings.AVERAGE_RESULT ){
					//System.out.println( "Getting average result (" + attempts[ i ].getAttemptedDate() + ", " +  bestAttemptDate + ")");
					// TODO
				}
			}	
			
			if( position != attempts.length -1 && attempts[ position ].getId() != null && attempts[ position ].getId() != Id.UNSET_ID ){ // ie not last
				// clone attempt
				Attempt attempt = outcome.createAttempt();
				attempt.setAttemptedDate( attempts[ position ].getAttemptedDate() );
				attempt.setDateCreated( attempts[ position ].getDateCreated() );
				attempt.setDateModified( Calendar.getInstance() ); // use system locale,
				attempt.setGrade( attempts[ position ].getGrade() );
				attempt.setInstructorComments( attempts[ position ].getInstructorComments() );
				attempt.setInstructorNotes( attempts[ position ].getInstructorNotes() );
				attempt.setLinkRefId( attempts[ position ].getLinkRefId() );
				attempt.setOutcomeId( attempts[ position ].getOutcomeId() );
				attempt.setResultObjectId( attempts[ position ].getResultObjectId() );
				attempt.setScore( attempts[ position ].getScore() );
				attempt.setStatus( attempts[ position ].getStatus() );
				attempt.setStudentComments( attempts[ position ].getStudentComments() );
				// add attempt to outcome as new
				outcome.addAttempt( attempt );
				// remove the original attempt
				
				// bb Bug - remove doesn't work
				outcome.removeAttempt( position );
				BbUtils.getAttemptDbPersister( BbUtils.getBbPersistenceManager() ).deleteById( attempts[ position ].getId() );
				
			}
		} 
		
	}
	
	public boolean isAverageDisplayed( OutcomeDefinition outcomeDef ){
		return false;
	}
}
