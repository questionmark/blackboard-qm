/*
 * @(#)AttemptsSorter71Impl.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.util;

import blackboard.data.gradebook.*;
import blackboard.data.gradebook.impl.*;
import blackboard.persist.*;

import com.qm.bb6.perception.config.PerceptionSettings;

public class AttemptsSorter71Impl implements AttemptsSorter {
	
	/**
	 * Orders a list of attempts to set the first, best or last as the selected attempt
	 * @see com.qm.bb6.perception.config.CourseSettings
	 */
	public void sortAttempts( Outcome outcome, int selectionType ) throws PersistenceException {
		
		OutcomeDefinition outcomeDefinition = outcome.getOutcomeDefinition();
		
		if( selectionType == PerceptionSettings.BEST_RESULT ){
			outcomeDefinition.setAggregationModel( OutcomeDefinition.AggregationModel.HIGHEST );
		}else if( selectionType == PerceptionSettings.FIRST_RESULT ){
			outcomeDefinition.setAggregationModel( OutcomeDefinition.AggregationModel.FIRST );
		}else if( selectionType == PerceptionSettings.LAST_RESULT ){
			outcomeDefinition.setAggregationModel( OutcomeDefinition.AggregationModel.LAST );
		}else if( selectionType == PerceptionSettings.WORST_RESULT ){
			outcomeDefinition.setAggregationModel( OutcomeDefinition.AggregationModel.LOWEST );
		}else if( selectionType == PerceptionSettings.AVERAGE_RESULT ){
			outcomeDefinition.setAggregationModel( OutcomeDefinition.AggregationModel.AVERAGE );
		}			
		
		try{
			BbUtils.getOutcomeDefinitionDbPersister().persist(outcomeDefinition);
		}catch( blackboard.data.ValidationException e ){
			throw new PersistenceException(e);
		}
	}
	
	public boolean isAverageDisplayed( OutcomeDefinition outcomeDef ){
		if( outcomeDef != null ){
			return OutcomeDefinition.AggregationModel.AVERAGE == outcomeDef.getAggregationModel();
		}
		return false;
	}
}
