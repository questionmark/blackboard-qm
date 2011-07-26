/*
 * PerceptionTopicLoaderImpl.java
 *
 * Created on February 16, 2007, 11:12 AM
 *
 * Copyright 2006 VLE Genius Ltd. All rights reserved.
 */

package com.qm.bb6.perception.data.impl;

import java.util.*;

import org.apache.soap.encoding.SOAPMappingRegistry;
import org.apache.soap.rpc.Parameter;

import com.qm.bb6.perception.data.*;
import com.qm.bb6.perception.service.*;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
	public class PerceptionTopicLoaderImpl extends PerceptionSOAPRequestor implements PerceptionTopicLoader{
	
	public static final String GET_TOPIC_BY_ID = "GetTopic";
	public static final String GET_TOPIC_BY_NAME_AND_PARENT_ID = "GetTopicByNameAndParentId";
	
	public PerceptionTopic getTopicById( String topic_id ) throws PerceptionDataException {
		return (PerceptionTopic) loadById( GET_TOPIC_BY_ID, TopicDeserializer.TOPIC_ID_PARAM, topic_id, getMappingRegistry() );
	}

	public PerceptionTopic getTopicByNameAndParentId( String name, String parent_id ) throws PerceptionDataException {
		SOAPMappingRegistry smr = getMappingRegistry();
		if( parent_id == null )
			parent_id = "0";
		Vector bodyEntries = new Vector();
		bodyEntries.add( new Parameter( TopicDeserializer.TOPIC_NAME_PARAM, String.class, name, null ) );
		bodyEntries.add( new Parameter( TopicDeserializer.PARENT_ID_PARAM, String.class, parent_id, null ) );
		addIDMapping( smr, TopicDeserializer.TOPIC_NAME_PARAM );
		addIDMapping( smr, TopicDeserializer.PARENT_ID_PARAM );
		return (PerceptionTopic) load( GET_TOPIC_BY_NAME_AND_PARENT_ID, bodyEntries, smr );
	}
	
	private SOAPMappingRegistry getMappingRegistry(){
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		addResponseMapping( smr, TopicDeserializer.TAG_NAME, new TopicDeserializer(), PerceptionTopic.class );
		return smr;
	}


}

