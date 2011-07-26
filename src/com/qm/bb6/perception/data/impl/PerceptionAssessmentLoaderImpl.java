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

import com.qm.bb6.perception.data.*;
import com.qm.bb6.perception.service.*;
import com.qm.bb6.perception.security.AccessDeniedException;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class PerceptionAssessmentLoaderImpl extends PerceptionSOAPRequestor implements PerceptionAssessmentLoader{
	
	public static final String GET_ASSESSMENT_BY_ID = "GetAssessment";
	public static final String GET_ASSESSMENTS_BY_GROUP_ID = "GetAssessmentListByGroup";
	public static final String GET_ASSESSMENTS_BY_ADMINISTRATOR_ID = "GetAssessmentListByAdministrator";
	public static final String GET_ALL_ASSESSMENTS = "GetAssessmentList";
	
	public PerceptionAssessment getAssessmentById( String assessment_id ) throws PerceptionDataException {
		return (PerceptionAssessment) loadById( GET_ASSESSMENT_BY_ID, AssessmentDeserializer.ASSESSMENT_ID_PARAM, assessment_id, getMappingRegistry() );
	}
	
	public List getAllAssessments() throws PerceptionDataException {
		return (List) load( GET_ALL_ASSESSMENTS, getListMappingRegistry() );
	}

	public List getAssessmentsByGroupId( String group_id ) throws PerceptionDataException {
		return (List) loadById( GET_ASSESSMENTS_BY_GROUP_ID, GroupDeserializer.GROUP_ID_PARAM, group_id, getListMappingRegistry()  );
	}

	public List getAssessmentsByAdministratorId( String administrator_id ) throws PerceptionDataException {
		return (List) loadById( GET_ASSESSMENTS_BY_ADMINISTRATOR_ID, AdministratorDeserializer.ADMINISTRATOR_ID_PARAM, administrator_id, getListMappingRegistry()  );
	}
	
	private SOAPMappingRegistry getMappingRegistry(){
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		addResponseMapping( smr, AssessmentDeserializer.TAG_NAME, new AssessmentDeserializer(), PerceptionAssessment.class );
		return smr;
	}

	private SOAPMappingRegistry getListMappingRegistry(){
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		this.addListMapping( smr, AssessmentListDeserializer.LIST_TAG_NAME, new AssessmentListDeserializer() );
		return smr;
	}
}
