/*
 * @(#)GroupScheduleDeserializer.java 1.0.1 Sep 1 2005
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
public class GroupScheduleDeserializer extends PerceptionScheduleDeserializer {
		
	public static final String TAG_NAME = "Schedule";
	
	public static final String SCHEDULE_ID_PARAM = "Schedule_ID";
	public static final String PARTICIPANT_ID_PARAM = ParticipantDeserializer.PARTICIPANT_ID_PARAM;
	public static final String GROUP_ID_PARAM = GroupDeserializer.GROUP_ID_PARAM;
	public static final String ASSESSMENT_ID_PARAM = AssessmentDeserializer.ASSESSMENT_ID_PARAM;

	private static PerceptionSerializerMap parameterMap = new PerceptionSerializerMap();
	
	static {
			
		parameterMap.put( SCHEDULE_ID_PARAM, "ScheduleId", Integer.class );
		parameterMap.put( "Session_MID", "ScheduleMId", Integer.class, false, false );
        parameterMap.put( "Session_LID", "ScheduleLId", String.class, false, false );
        parameterMap.put( "Session_Name", "SessionName", String.class );
        parameterMap.put( PARTICIPANT_ID_PARAM, "ParticipantId", String.class );
        parameterMap.put( GROUP_ID_PARAM, "GroupId", String.class );
        parameterMap.put( "Schedule_Name", "ScheduleName", String.class );
        parameterMap.put( "Restrict_Times", "RestrictTimes", Boolean.class );
        parameterMap.put( "Schedule_Starts", "ScheduleStarts", Calendar.class );
        parameterMap.put( "Schedule_Stops", "ScheduleStops", Calendar.class );
		parameterMap.put( "Restrict_Attempts", "RestrictAttempts", Boolean.class );
		parameterMap.put( "Max_Attempts", "MaxAttempts", Integer.class );
		parameterMap.put( "Monitored", "IsMonitored", Integer.class );
		parameterMap.put( ASSESSMENT_ID_PARAM, "AssessmentId", String.class );
	}
	
	
	public static PerceptionSerializerMap getScheduleParameterMap(){
		return parameterMap;
	}

	public PerceptionSerializerMap getParameterMap(){
		return parameterMap;
	}
	
	public Class getSerializedClass(){
		return PerceptionSchedule.class;
	}
		
}

