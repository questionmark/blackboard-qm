/*
 * AssessmentFolderMembershipMembershipDeserializer.java
 *
 * Created on February 15, 2007, 9:59 PM
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
public class AssessmentFolderMembershipDeserializer extends PerceptionListDeserializer {
		
	public static final String LIST_TAG_NAME = "AssessmentFolderMembershipList";
	public static final String TAG_NAME = "AssessmentFolderMembership";
	
	public static final String ADMINISTRATOR_ID_PARAM = "Administrator_ID";
	public static final String PERMISSIONS_PARAM = "Permissions";
	public static final String FOLDER_ID_PARAM = "Folder_ID";

	private static PerceptionSerializerMap parameterMap = new PerceptionSerializerMap();
	
	static {
			
		parameterMap.put( ADMINISTRATOR_ID_PARAM, "AdministratorId", String.class );
        parameterMap.put( PERMISSIONS_PARAM, "Permissions", Integer.class, true, true );
        parameterMap.put( FOLDER_ID_PARAM, "FolderId", String.class );
	}
	
	
	public static PerceptionSerializerMap getAssessmentFolderMembershipParameterMap(){
		return parameterMap;
	}

	public PerceptionSerializerMap getParameterMap(){
		return parameterMap;
	}
	
	public Class getSerializedClass(){
		return PerceptionAssessmentFolderMembership.class;
	}

	public String getElementTagName(){
		return TAG_NAME;
	}
			
}
