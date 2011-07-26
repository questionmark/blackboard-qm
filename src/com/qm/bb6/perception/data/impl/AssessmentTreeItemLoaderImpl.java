/*
 * @(#)PerceptionAssessmentLoaderImpl.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
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
public class AssessmentTreeItemLoaderImpl extends PerceptionSOAPRequestor implements AssessmentTreeItemLoader {
	
	public static final String GET_ASSESSMENT_TREE_BY_ADMINISTRATOR_ID = "GetAssessmentTreeByAdministrator";
	
	public List getAssessmentTreeByAdministratorId( String administrator_id, boolean integrationOnly ) throws PerceptionDataException{
		return getAssessmentTreeByAdministratorId( administrator_id, null, integrationOnly );
	}

	public List getAssessmentTreeByAdministratorId( String administrator_id, String parent_id, boolean integrationOnly ) throws PerceptionDataException{
		SOAPMappingRegistry smr = getListMappingRegistry();
		if( parent_id == null )
			parent_id = "0";
		Vector bodyEntries = new Vector();
		bodyEntries.add( new Parameter( AssessmentTreeDeserializer.ADMINISTRATOR_ID_PARAM, String.class, administrator_id, null ) );
		bodyEntries.add( new Parameter( AssessmentTreeDeserializer.PARENT_ID_PARAM, String.class, parent_id, null ) );
		Integer integrationOnlyValue = new Integer((integrationOnly ? 1 : 0));
		bodyEntries.add( new Parameter( AssessmentTreeDeserializer.ONLY_RUN_FROM_INTEGRATION_PARAM, Integer.class, integrationOnlyValue, null ) );
		addIDMapping( smr, AssessmentTreeDeserializer.ADMINISTRATOR_ID_PARAM );
		addIDMapping( smr, AssessmentTreeDeserializer.PARENT_ID_PARAM );
		addIDMapping( smr, AssessmentTreeDeserializer.ONLY_RUN_FROM_INTEGRATION_PARAM, Integer.class );
		return (List) load( GET_ASSESSMENT_TREE_BY_ADMINISTRATOR_ID, bodyEntries, smr );
	}
	
	private SOAPMappingRegistry getListMappingRegistry(){
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		this.addListMapping( smr, AssessmentTreeDeserializer.LIST_TAG_NAME, new AssessmentTreeDeserializer() );
		return smr;
	}
}
