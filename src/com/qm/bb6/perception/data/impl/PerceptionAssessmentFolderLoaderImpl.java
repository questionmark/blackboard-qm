/*
 * PerceptionAssessmentFolderLoaderImpl.java
 *
 * Created on February 15, 2007, 8:13 PM
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
public class PerceptionAssessmentFolderLoaderImpl extends PerceptionSOAPRequestor implements PerceptionAssessmentFolderLoader{
	
	public static final String GET_FOLDER_BY_ID = "GetAssessmentFolder";
	public static final String GET_FOLDER_BY_NAME_AND_PARENT_ID = "GetAssessmentFolderByNameAndParentId";
	
	public PerceptionAssessmentFolder getAssessmentFolderById( String folder_id ) throws PerceptionDataException {
		return (PerceptionAssessmentFolder) loadById( GET_FOLDER_BY_ID, AssessmentFolderDeserializer.FOLDER_ID_PARAM, folder_id, getMappingRegistry() );
	}

	public PerceptionAssessmentFolder getAssessmentFolderByNameAndParentId( String name, String parent_id ) throws PerceptionDataException {
		SOAPMappingRegistry smr = getMappingRegistry();
		if( parent_id == null )
			parent_id = "0";
		Vector bodyEntries = new Vector();
		bodyEntries.add( new Parameter( AssessmentFolderDeserializer.FOLDER_NAME_PARAM, String.class, name, null ) );
		bodyEntries.add( new Parameter( AssessmentFolderDeserializer.PARENT_ID_PARAM, String.class, parent_id, null ) );
		addIDMapping( smr, AssessmentFolderDeserializer.FOLDER_NAME_PARAM );
		addIDMapping( smr, AssessmentFolderDeserializer.PARENT_ID_PARAM );
		return (PerceptionAssessmentFolder) load( GET_FOLDER_BY_NAME_AND_PARENT_ID, bodyEntries, smr );
	}
	
	private SOAPMappingRegistry getMappingRegistry(){
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		addResponseMapping( smr, AssessmentFolderDeserializer.TAG_NAME, new AssessmentFolderDeserializer(), PerceptionAssessmentFolder.class );
		return smr;
	}


}
