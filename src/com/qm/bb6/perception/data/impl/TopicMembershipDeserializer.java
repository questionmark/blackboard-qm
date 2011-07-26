/*
 * TopicMembershipDeserializer.java
 *
 * Created on February 16, 2007, 10:58 AM
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
public class TopicMembershipDeserializer extends PerceptionListDeserializer {
		
	public static final String TAG_NAME = "TopicMembership";
	
	public static final String ADMINISTRATOR_ID_PARAM = "Administrator_ID";
	public static final String PERMISSIONS_PARAM = "Permissions";
	public static final String TOPIC_ID_PARAM = "Topic_ID";

	private static PerceptionSerializerMap parameterMap = new PerceptionSerializerMap();
	
	static {
			
		parameterMap.put( ADMINISTRATOR_ID_PARAM, "AdministratorId", String.class );
        parameterMap.put( PERMISSIONS_PARAM, "Permissions", Integer.class, true, true );
        parameterMap.put( TOPIC_ID_PARAM, "TopicId", String.class );
	}
	
	
	public static PerceptionSerializerMap getTopicMembershipParameterMap(){
		return parameterMap;
	}

	public PerceptionSerializerMap getParameterMap(){
		return parameterMap;
	}
	
	public Class getSerializedClass(){
		return PerceptionTopicMembership.class;
	}

	public String getElementTagName(){
		return TAG_NAME;
	}
			
}

