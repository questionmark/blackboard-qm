/*
 * PerceptionAssessmentFolderPersisterImpl.java
 *
 * Created on February 15, 2007, 8:39 PM
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
public class PerceptionAssessmentFolderPersisterImpl extends PerceptionSOAPRequestor implements PerceptionAssessmentFolderPersister {
	
	public static final String CREATE_FOLDER = "CreateAssessmentFolder";
	public static final String UPDATE_FOLDER = "SetAssessmentFolder";
	public static final String DELETE_FOLDER_BY_ID = "DeleteAssessmentFolder";

	public void persist( PerceptionAssessmentFolder folder ) throws PerceptionDataException{
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		PerceptionObjectSerializer serializer = new PerceptionObjectSerializer( AssessmentFolderDeserializer.getAssessmentFolderParameterMap());
		addRequestMapping( smr, AssessmentFolderDeserializer.TAG_NAME, serializer, PerceptionAssessmentFolder.class );
	//	if( folder.getParentId() == null )
	//		folder.setParentId("0");
		if( folder.getAssessmentFolderId() == null ){
			String id = insert( CREATE_FOLDER, AssessmentFolderDeserializer.TAG_NAME, AssessmentFolderDeserializer.FOLDER_ID_PARAM, folder, smr);
			folder.setAssessmentFolderId( id );
		}else{
			update( UPDATE_FOLDER, AssessmentFolderDeserializer.TAG_NAME, folder, smr);
		}
	}
	
	public void delete( PerceptionAssessmentFolder folder ) throws PerceptionDataException{
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		deleteById( DELETE_FOLDER_BY_ID, AssessmentFolderDeserializer.FOLDER_ID_PARAM, folder.getAssessmentFolderId(), smr );
		
	}
	

}
