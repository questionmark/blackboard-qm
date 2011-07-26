/*
 * @(#)AttemptsSorter.java 1.0.1 Sep 18 2006
 *
 * Copyright 2006 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.util;

import blackboard.data.gradebook.impl.*;
import blackboard.persist.PersistenceException;

public interface AttemptsSorter {
	
	void sortAttempts( Outcome outcome, int selectionType ) throws PersistenceException;
	
	boolean isAverageDisplayed( OutcomeDefinition outcomeDef );
}
