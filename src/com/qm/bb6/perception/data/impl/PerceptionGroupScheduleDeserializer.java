/*
 * @(#)PerceptionGroupScheduleDeserializer.java 1.0.1 Sep 1 2005
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
 * ONLY TO BE USED FOR INSERTS
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class PerceptionGroupScheduleDeserializer extends PerceptionScheduleDeserializer {
		
	private static PerceptionSerializerMap parameterMap = PerceptionScheduleDeserializer.getScheduleParameterMap();
	
	static {
			
		parameterMap.put( "Schedule_Participants", "ScheduleParticipants", Boolean.class );
	}
	
	public static PerceptionSerializerMap getGroupScheduleParameterMap(){
		return parameterMap;
	}

	public PerceptionSerializerMap getParameterMap(){
		return parameterMap;
	}
	
	public Class getSerializedClass(){
		return PerceptionSchedule.class;
	}
		
}

