/*
 * @(#)AssessmentTreeDeserializer.java 1.0.1 Sep 1 2005
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
public class AssessmentTreeDeserializer extends PerceptionListDeserializer {
		
	public static final String LIST_TAG_NAME = "AssessmentTreeItemList";
	public static final String TAG_NAME = "AssessmentTreeItem";
	
	public static final String ADMINISTRATOR_ID_PARAM = AdministratorDeserializer.ADMINISTRATOR_ID_PARAM;
	public static final String PARENT_ID_PARAM = "Parent_ID";
	public static final String ONLY_RUN_FROM_INTEGRATION_PARAM = "OnlyRunFromIntegration";
	

	private static PerceptionSerializerMap parameterMap = new PerceptionSerializerMap();
	
	static {
			
		parameterMap.put( PARENT_ID_PARAM, "ParentId", String.class );
        parameterMap.put( "Type", "Type", Integer.class, false, false );
        parameterMap.put( "Name", "Name", String.class );
        parameterMap.put( "ID", "ItemId", String.class );
	}
	
	
	public static PerceptionSerializerMap getAssessmentTreeItemParameterMap(){
		return parameterMap;
	}

	public PerceptionSerializerMap getParameterMap(){
		return parameterMap;
	}
	
	public Class getSerializedClass(){
		return AssessmentTreeItem.class;
	}

	public String getElementTagName(){
		return TAG_NAME;
	}
			
}

