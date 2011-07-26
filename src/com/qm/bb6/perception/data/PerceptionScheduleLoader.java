/*
 * @(#)PerceptionScheduleLoader.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.data;

import java.util.List;

import com.qm.bb6.perception.service.*;


/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public interface PerceptionScheduleLoader {
	
	PerceptionSchedule getScheduleById( String schedule_id ) throws PerceptionDataException;
	
	List getAllSchedules() throws PerceptionDataException;

	List getSchedulesByGroupId( String group_id ) throws PerceptionDataException;

	List getSchedulesByParticipantId( String participant_id ) throws PerceptionDataException;

	List getSchedulesByAssessmentId( String assessment_id ) throws PerceptionDataException;
	
}
