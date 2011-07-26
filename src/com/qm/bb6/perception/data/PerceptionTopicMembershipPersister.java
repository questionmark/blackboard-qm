/*
 * PerceptionTopicMembershipPersister.java
 *
 * Created on February 16, 2007, 10:55 AM
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
public interface PerceptionTopicMembershipPersister {
	
	void persist( PerceptionTopicMembership membership ) throws PerceptionDataException;
	
	void delete( PerceptionTopicMembership membership ) throws PerceptionDataException;
	
}