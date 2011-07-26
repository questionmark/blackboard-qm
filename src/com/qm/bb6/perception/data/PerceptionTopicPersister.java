/*
 * PerceptionTopicPersister.java
 *
 * Created on February 16, 2007, 10:40 AM
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
public interface PerceptionTopicPersister {
	
	void persist( PerceptionTopic topic ) throws PerceptionDataException;
	
	void delete( PerceptionTopic topic ) throws PerceptionDataException;
	
}