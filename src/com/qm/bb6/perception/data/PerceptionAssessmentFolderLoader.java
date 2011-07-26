/*
 * PerceptionAssessmentFolderLoader.java
 *
 * Created on February 15, 2007, 8:11 PM
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
public interface PerceptionAssessmentFolderLoader {
	
	PerceptionAssessmentFolder getAssessmentFolderById( String folder_id ) throws PerceptionDataException;
	
	PerceptionAssessmentFolder getAssessmentFolderByNameAndParentId( String folder_name, String parent_id ) throws PerceptionDataException;
	
}
