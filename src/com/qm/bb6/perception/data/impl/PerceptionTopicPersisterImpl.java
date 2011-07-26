/*
 * PerceptionTopicPersisterImpl.java
 *
 * Created on February 16, 2007, 10:42 AM
 *
 * Copyright 2006 VLE Genius Ltd. All rights reserved.
 */

package com.qm.bb6.perception.data.impl;

import java.util.*;

import org.apache.soap.encoding.SOAPMappingRegistry;

import com.qm.bb6.perception.data.*;
import com.qm.bb6.perception.service.*;
import com.qm.bb6.perception.security.AccessDeniedException;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class PerceptionTopicPersisterImpl extends PerceptionSOAPRequestor implements PerceptionTopicPersister {
	
	public static final String CREATE_TOPIC = "CreateTopic";
	public static final String UPDATE_TOPIC = "SetTopic";
	public static final String DELETE_TOPIC_BY_ID = "DeleteTopic";

	public void persist( PerceptionTopic topic ) throws PerceptionDataException{
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		PerceptionObjectSerializer serializer = new PerceptionObjectSerializer( TopicDeserializer.getTopicParameterMap());
		addRequestMapping( smr, TopicDeserializer.TAG_NAME, serializer, PerceptionTopic.class );
	//	if( topic.getParentId() == null )
	//		topic.setParentId("0");
		if( topic.getTopicId() == null ){
			String id = insert( CREATE_TOPIC, TopicDeserializer.TAG_NAME, TopicDeserializer.TOPIC_ID_PARAM, topic, smr);
			topic.setTopicId( id );
		}else{
			update( UPDATE_TOPIC, TopicDeserializer.TAG_NAME, topic, smr);
		}
	}
	
	public void delete( PerceptionTopic topic ) throws PerceptionDataException{
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		deleteById( DELETE_TOPIC_BY_ID, TopicDeserializer.TOPIC_ID_PARAM, topic.getTopicId(), smr );
		
	}
	

}
