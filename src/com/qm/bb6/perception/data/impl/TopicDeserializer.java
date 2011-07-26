/*
 * TopicDeserializer.java
 *
 * Created on February 16, 2007, 10:44 AM
 *
 * Copyright 2006 VLE Genius Ltd. All rights reserved.
 */

package com.qm.bb6.perception.data.impl;

import java.util.*;

import com.qm.bb6.perception.data.*;
import com.qm.bb6.perception.service.*;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class TopicDeserializer extends PerceptionListDeserializer {
		
	public static final String TAG_NAME = "Topic";
	
	public static final String TOPIC_ID_PARAM = "Topic_ID";
	public static final String TOPIC_NAME_PARAM = "Topic_Name";
	public static final String PARENT_ID_PARAM = "Parent_ID";

	private static PerceptionSerializerMap parameterMap = new PerceptionSerializerMap();
	
	static {
			
		parameterMap.put( PARENT_ID_PARAM, "ParentId", String.class );
        parameterMap.put( "Topic_Description", "Description", String.class );
        parameterMap.put( TOPIC_NAME_PARAM, "TopicName", String.class );
        parameterMap.put( TOPIC_ID_PARAM, "TopicId", String.class );
	}
	
	
	public static PerceptionSerializerMap getTopicParameterMap(){
		return parameterMap;
	}

	public PerceptionSerializerMap getParameterMap(){
		return parameterMap;
	}
	
	public Class getSerializedClass(){
		return PerceptionTopic.class;
	}

	public String getElementTagName(){
		return TAG_NAME;
	}
			
}
