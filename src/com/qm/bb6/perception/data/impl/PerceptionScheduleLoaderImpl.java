/*
 * @(#)PerceptionScheduleLoaderImpl.java 1.0.1 Sep 1 2005
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

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class PerceptionScheduleLoaderImpl extends PerceptionSOAPRequestor implements PerceptionScheduleLoader {

	public static final String GET_SCHEDULE_BY_ID = "GetSchedule";
	public static final String GET_ALL_SCHEDULES = "GetScheduleList";
	public static final String GET_SCHEDULES_BY_GROUP_ID = "GetScheduleListByGroup";
	public static final String GET_SCHEDULES_BY_PARTICIPANT_ID = "GetScheduleListByParticipant";
	public static final String GET_SCHEDULES_BY_ASSESSMENT_ID = "GetScheduleListByAssessment";

	public PerceptionSchedule getScheduleById( String schedule_id ) throws PerceptionDataException{
		return (PerceptionSchedule) loadById( GET_SCHEDULE_BY_ID, PerceptionScheduleDeserializer.SCHEDULE_ID_PARAM, schedule_id, getMappingRegistry() );
	}
	
	public List getAllSchedules() throws PerceptionDataException{
		return (List) load( GET_ALL_SCHEDULES, getListMappingRegistry() );
	}
	
	public List getSchedulesByGroupId( String group_id ) throws PerceptionDataException{
		return (List) loadById( GET_SCHEDULES_BY_GROUP_ID, PerceptionScheduleDeserializer.GROUP_ID_PARAM, group_id, getListMappingRegistry() );
	}

	public List getSchedulesByParticipantId( String id ) throws PerceptionDataException{
		return (List) loadById( GET_SCHEDULES_BY_PARTICIPANT_ID, PerceptionScheduleDeserializer.PARTICIPANT_ID_PARAM, id, getListMappingRegistry() );
	}

	public List getSchedulesByAssessmentId( String assessment_id ) throws PerceptionDataException{
		return (List) loadById( GET_SCHEDULES_BY_ASSESSMENT_ID, PerceptionScheduleDeserializer.ASSESSMENT_ID_PARAM, assessment_id, getListMappingRegistry() );
	}
	
	private SOAPMappingRegistry getMappingRegistry(){
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		addResponseMapping( smr, PerceptionScheduleDeserializer.TAG_NAME, new PerceptionScheduleDeserializer(), PerceptionSchedule.class );
		return smr;
	}

	private SOAPMappingRegistry getListMappingRegistry(){
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		this.addListMapping( smr, PerceptionScheduleListDeserializer.LIST_TAG_NAME, new PerceptionScheduleListDeserializer() );
		return smr;
	}	


}
