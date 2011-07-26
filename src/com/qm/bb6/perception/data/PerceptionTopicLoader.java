/*
 * PerceptionTopicLoader.java
 *
 * Created on February 16, 2007, 11:10 AM
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
public interface PerceptionTopicLoader {
	
	PerceptionTopic getTopicById( String topic_id ) throws PerceptionDataException;
	
	PerceptionTopic getTopicByNameAndParentId( String topic_name, String parent_id ) throws PerceptionDataException;
	
}

