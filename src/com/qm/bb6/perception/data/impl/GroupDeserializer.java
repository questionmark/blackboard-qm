/*
 * @(#)GroupDeserializer.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
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
public class GroupDeserializer extends PerceptionDeserializer {
		
	public static final String TAG_NAME = "Group";

	public static final String GROUP_ID_PARAM = "Group_ID";
	public static final String GET_GROUP_BY_NAME = "Group_Name";
	
	private static PerceptionSerializerMap parameterMap = new PerceptionSerializerMap();
	
	static {
			
		parameterMap.put( GROUP_ID_PARAM, "GroupId", String.class );
		parameterMap.put( "Parent_ID", "ParentId", String.class );
        parameterMap.put( GET_GROUP_BY_NAME, "GroupName" , String.class );
        parameterMap.put( "Description", "Description", String.class );
	}
	
	
	public static PerceptionSerializerMap getGroupParameterMap(){
		return parameterMap;
	}

	public PerceptionSerializerMap getParameterMap(){
		return parameterMap;
	}
	
	public Class getSerializedClass(){
		return PerceptionGroup.class;
	}
		
}
