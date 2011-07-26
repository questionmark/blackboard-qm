/*
 * AssessmentFolderDeserializer.java
 *
 * Created on February 15, 2007, 8:23 PM
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
public class AssessmentFolderDeserializer extends PerceptionListDeserializer {
		
	public static final String LIST_TAG_NAME = "AssessmentFolderList";
	public static final String TAG_NAME = "AssessmentFolder";
	
	public static final String FOLDER_ID_PARAM = "Folder_ID";
	public static final String FOLDER_NAME_PARAM = "Name";
	public static final String PARENT_ID_PARAM = "Parent_ID";

	private static PerceptionSerializerMap parameterMap = new PerceptionSerializerMap();
	
	static {
			
		parameterMap.put( PARENT_ID_PARAM, "ParentId", String.class );
        parameterMap.put( "Description", "Description", String.class );
        parameterMap.put( FOLDER_NAME_PARAM, "AssessmentFolderName", String.class );
        parameterMap.put( FOLDER_ID_PARAM, "AssessmentFolderId", String.class );
	}
	
	
	public static PerceptionSerializerMap getAssessmentFolderParameterMap(){
		return parameterMap;
	}

	public PerceptionSerializerMap getParameterMap(){
		return parameterMap;
	}
	
	public Class getSerializedClass(){
		return PerceptionAssessmentFolder.class;
	}

	public String getElementTagName(){
		return TAG_NAME;
	}
			
}

