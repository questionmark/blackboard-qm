/*
 * PerceptionAssessmentFolderMembershipPersister.java
 *
 * Created on February 15, 2007, 8:50 PM
 *
 * Copyright 2006 VLE Genius Ltd. All rights reserved.
 */

package com.qm.bb6.perception.data;

import com.qm.bb6.perception.service.*;
/**
 *
 * @author Matt Elton
 */
public interface PerceptionAssessmentFolderMembershipPersister {
	
	void persist( PerceptionAssessmentFolderMembership membership ) throws PerceptionDataException;
	
	void delete( PerceptionAssessmentFolderMembership membership ) throws PerceptionDataException;
	
}
