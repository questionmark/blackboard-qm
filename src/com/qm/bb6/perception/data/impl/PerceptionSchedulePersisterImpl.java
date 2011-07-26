/*
 * @(#)PerceptionSchedulePersisterImpl.java 1.0.1 Sep 1 2005
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
public class PerceptionSchedulePersisterImpl extends PerceptionSOAPRequestor implements PerceptionSchedulePersister {
	
	public static final String CREATE_PARTICIPANT_SCHEDULE = "CreateScheduleParticipant";
	public static final String CREATE_GROUP_SCHEDULE = "CreateScheduleGroup";
	public static final String UPDATE_SCHEDULE = "SetSchedule";
	public static final String DELETE_SCHEDULE_BY_ID = "DeleteSchedule";


	public void persist( PerceptionSchedule schedule ) throws PerceptionDataException{
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		if( schedule.getScheduleId() == null ){
			String tagName;
			String idParameter;
			String methodName;
			if( schedule.isGroupSchedule() ){
				tagName = PerceptionGroupScheduleDeserializer.TAG_NAME;
				idParameter = PerceptionGroupScheduleDeserializer.SCHEDULE_ID_PARAM;
				methodName = CREATE_GROUP_SCHEDULE;
				PerceptionObjectSerializer serializer = new PerceptionObjectSerializer( PerceptionGroupScheduleDeserializer.getGroupScheduleParameterMap() );
				addRequestMapping( smr, PerceptionGroupScheduleDeserializer.TAG_NAME, serializer, PerceptionSchedule.class );
			}else{
				tagName = PerceptionScheduleDeserializer.TAG_NAME;
				idParameter = PerceptionScheduleDeserializer.SCHEDULE_ID_PARAM;
				methodName = CREATE_PARTICIPANT_SCHEDULE;
				PerceptionObjectSerializer serializer = new PerceptionObjectSerializer( PerceptionScheduleDeserializer.getScheduleParameterMap() );
				addRequestMapping( smr, PerceptionScheduleDeserializer.TAG_NAME, serializer, PerceptionSchedule.class );
			}
			String id = insert( methodName, tagName, idParameter, schedule, smr);
			schedule.setScheduleId( id );
		}else{
			PerceptionObjectSerializer serializer = new PerceptionObjectSerializer( PerceptionScheduleDeserializer.getScheduleParameterMap() );
			addRequestMapping( smr, PerceptionScheduleDeserializer.TAG_NAME, serializer, PerceptionSchedule.class );
			update( UPDATE_SCHEDULE, PerceptionScheduleDeserializer.TAG_NAME, schedule, smr);
		}
	}
	
	public void delete( PerceptionSchedule schedule ) throws PerceptionDataException{
		deleteByScheduleId( schedule.getScheduleId() );		
	}

	public void deleteByScheduleId( String schedule_id ) throws PerceptionDataException{
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		deleteById( DELETE_SCHEDULE_BY_ID, PerceptionScheduleDeserializer.SCHEDULE_ID_PARAM, schedule_id, smr );
	}

}
