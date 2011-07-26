/*
 * PerceptionAssessmentFolderPersister.java
 *
 * Created on February 15, 2007, 8:38 PM
 *
 * Copyright 2006 VLE Genius Ltd. All rights reserved.
 */

package com.qm.bb6.perception.data;

import com.qm.bb6.perception.service.*;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public interface PerceptionAssessmentFolderPersister {
	
	void persist( PerceptionAssessmentFolder folder ) throws PerceptionDataException;
	
	void delete( PerceptionAssessmentFolder folder ) throws PerceptionDataException;
	
}
