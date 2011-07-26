/*
 * @(#)ContentSettings.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.config;

import blackboard.data.ValidationException;
import blackboard.data.content.Content;
import blackboard.persist.*;

import java.util.Calendar;


import com.qm.bb6.perception.config.impl.*;
import com.qm.bb6.perception.util.*;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public interface ContentSettings {

	public static final String CONTENT_ID_PARAM = "content_id";
	public static final String ASSESSMENT_ID_PARAM = "assessment_id";
	public static final String SCHEDULE_ID_PARAM = "schedule_id";
	public static final String BODY_TYPE_PARAM = "body_type";
	public static final String PARTICIPANT_SCHEDULE_IDS_PARAM = "participant_schedule_ids";
	public static final String MAX_ATTEMPTS_PARAM = "max_attempts";

	// added
	public static final String CAN_STUDENTS_SEE_SCORES_PARAM = "can_StudentsSeeScores";
	public static final String CAN_STUDENTS_SEE_REPORT_PARAM = "can_StudentsSeeReport";
	public static final String RESULT_TYPE_PARAM = "result_type";
	public static final String WINDOW_TARGET_PARAM = "window_target";
	
	public abstract Id getContentId();

	public abstract String getAssessmentId();

	public abstract String getScheduleId();

	public abstract String[] getParticipantScheduleIds();

	public abstract int getMaxAttempts();

	public abstract boolean getAreAttemptsLimited();

	public abstract void setAssessmentId( String value );

	public abstract void setScheduleId( String value );
	
	public abstract String getBbStoredScheduleName();
	
	public abstract void setBbStoredScheduleName( String value );

	public abstract void setParticipantScheduleIds( String[] values );

	public abstract void setMaxAttempts( int attempts );

	public abstract void persist() throws PersistenceException, ValidationException;
	
	// added 4.4
	
	public abstract boolean getCanStudentsSeeScores();
	
	public abstract boolean getCanStudentsSeeReport();

	public abstract int getResultType();

	public abstract int getWindowTarget();

	public abstract void setCanStudentsSeeScores( boolean value );
	
	public abstract void setCanStudentsSeeReport( boolean value );

	public abstract void setResultType( int value );

	public abstract void setWindowTarget( int value );

	
	public abstract String getAnnouncementTitle();
	
	public abstract String getAnnouncementText();
	
	public abstract boolean getUpdateAnnouncement();
	
	public abstract Id getAnnouncementCreatorId();
	
	public abstract void setAnnouncementTitle( String value );
	
	public abstract void setAnnouncementText( String value );
	
	public abstract void setUpdateAnnouncement( boolean value );
	
	public abstract Calendar getAnnouncementStartDate();
	
	public abstract Calendar getAnnouncementEndDate();
	
	public abstract void setAnnouncementStartDate( Calendar start );
	
	public abstract void setAnnouncementEndDate( Calendar end );
	
	public abstract void setAnnouncementCreatorId( Id user_id );

}