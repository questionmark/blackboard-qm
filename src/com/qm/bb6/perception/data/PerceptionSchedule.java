/*
 * @(#)PerceptionSchedule.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.data;

import java.util.*;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class PerceptionSchedule {

	private String schedule_Id = null;
	private int session_MId = 0;
	private int session_LId = 0;
	private String session_Name = null;
	private String participant_Id = null;
	private String group_Id = null;
	private String schedule_name = null;
	private boolean restrict_times = false;
	private Calendar schedule_Starts = null;
	private Calendar schedule_Stops = null;
	private boolean restrict_Attempts = false;
	private int max_attempts = 0;
	private int is_monitored = 0;
	private String assessment_Id = null;
	private boolean schedule_Participants = false;
	
	private static Calendar beginningOfTime;
	private static Calendar endOfTime;
	private static Calendar defaultMinimumDate;
	
	static {
		
		beginningOfTime = Calendar.getInstance();
		endOfTime = Calendar.getInstance();
		beginningOfTime.setTimeInMillis( 978307200000L );
		endOfTime.setTimeInMillis( 1767225599999L );
		
		defaultMinimumDate = Calendar.getInstance();
		defaultMinimumDate.setTimeInMillis( 0L ); // 1970, Jan 1 - Perception defaults to 1/1/1900.
	}
	

	public String getScheduleId(){
		return schedule_Id;
	}

	public int getSessionMId(){
		return session_MId;
	}

	public int getSessionLId(){
		return session_LId;
	}

	public String getSessionName(){
		return session_Name;
	}

	public String getParticipantId(){
		return participant_Id;
	}

	public int getPersistableParticipantId(){
		return (( getParticipantId() == null || getParticipantId().trim().length() == 0) ? 0 : Integer.parseInt(getParticipantId()));
	}
	
	public String getGroupId(){
		return group_Id;
	}

	public String getScheduleName(){
		return schedule_name;
	}

	public boolean getRestrictTimes(){
		if( restrict_times && (getScheduleStops() != null || getScheduleStarts() != null) )
			return true;
		return false;
	}

	public boolean getPersistableRestrictTimes(){
		return getRestrictTimes();
	}

	public Calendar getScheduleStarts(){
		return schedule_Starts;
	}

	public Calendar getPersistableScheduleStarts(){
		if( getScheduleStarts() == null ){
			return beginningOfTime;
		}else{
			return getScheduleStarts();
		}
	}

	public Calendar getScheduleStops(){
		return schedule_Stops;
	}

	public Calendar getPersistableScheduleStops(){
		if( getScheduleStops() == null ){
			return endOfTime;
		}else{
			return getScheduleStops();
		}
	}

	public boolean getRestrictAttempts(){
		if( getPersistableParticipantId() == 0 )
			return false;
		else
			return restrict_Attempts;
	}

	public int getMaxAttempts(){
		return max_attempts;
	}

	public int getPersistableMaxAttempts(){
		if( getMaxAttempts() <= 0 ){
			return 1;
		}else{
			return getMaxAttempts();
		}
	}

	public int getIsMonitored(){
		return is_monitored;
	}

	public String getAssessmentId(){
		return assessment_Id;
	}
	
	public boolean getScheduleParticipants(){
		return schedule_Participants;
	}
	
	public boolean isGroupSchedule(){
		return ( this.getPersistableParticipantId() == 0 );
	}
	
	public boolean isParticipantSchedule(){
		return !isGroupSchedule();
	}
	
	public void setScheduleId( String value ){
		schedule_Id = value;
	}

	public void setSessionMId( int value ){
		session_MId = value;
	}

	public void setSessionLId( int value ){
		session_LId = value;
	}

	public void setSessionName( String value ){
		session_Name = value;
	}

	public void setParticipantId( String value ){
		participant_Id = value;
	}

	public void setPersistableParticipantId( int value ){
		if( value == 0 )
			setParticipantId(null);
		else
			setParticipantId( String.valueOf(value) );
	}

	public void setGroupId( String value ){
		group_Id = value;
	}

	public void setScheduleName( String value ){
		schedule_name = value;
	}

	public void setRestrictTimes( boolean value ){
		restrict_times = value;
	}

	public void setPersistableRestrictTimes( boolean value ){
		setRestrictTimes( value );
	}

	public void setScheduleStarts( Calendar value ){
		schedule_Starts = value;
	}

	public void setPersistableScheduleStarts( Calendar value ){
		if( value == null || value.after( defaultMinimumDate ) ){
			setScheduleStarts(value);
		}else{
			setScheduleStarts(null);
		}
	}

	public void setScheduleStops( Calendar value ){
		schedule_Stops = value;
	}

	public void setPersistableScheduleStops( Calendar value ){
		if( value == null || (value.after( defaultMinimumDate ) && value.before( defaultMinimumDate )) ){
			setScheduleStops(value);
		}else{
			setScheduleStops(null);
		}
	}

	public void setRestrictAttempts( boolean value ){
		restrict_Attempts = value;
	}

	public void setMaxAttempts( int value ){
		max_attempts = value;
	}

	public void setPersistableMaxAttempts( int value ){
		setMaxAttempts( value );
	}

	public void setIsMonitored( int value ){
		is_monitored = value;
	}

	public void setAssessmentId( String value ){
		assessment_Id = value;
	}

	public void setScheduleParticipants( boolean value ){
		schedule_Participants = value;
	}

}