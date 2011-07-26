/*
 * @(#)SimpleContentSettings.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.config.impl;

import blackboard.base.FormattedText;
import blackboard.data.ValidationException;
import blackboard.data.announcement.Announcement;
import blackboard.data.content.Content;
import blackboard.persist.*;
import blackboard.platform.plugin.*;
import blackboard.portal.data.*;
import blackboard.portal.servlet.PortalUtil;

import com.qm.bb6.perception.config.ContentSettings;
import com.qm.bb6.perception.config.CourseSettings;
import com.qm.bb6.perception.config.PerceptionSettings;
import com.qm.bb6.perception.util.*;

import java.util.Calendar;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class SimpleContentSettings implements ContentSettings {

 	private static final String HIDDEN_START_LINE = "<!--CONTENT_PARAMS:do not edit";
 	private static final String HIDDEN_END_LINE = "// -->";

	private static final String SMART_TEXT_BODY_TYPE = "SMART_TEXT";
	private static final String PLAIN_TEXT_BODY_TYPE = "PLAIN_TEXT";
	private static final String HTML_BODY_TYPE = "HTML";

	// added 4.4
	public static final String ANNOUNCEMENT_ID_PARAM = "announcement_id";
	public static final String BB_STORED_SCHEDULE_NAME = "bbStoredScheduleName";
	

	
	private Content content = null;
	private String assessment_id = null;
	private String schedule_id = null;
	private String[] schedule_ids = null;
	private int max_attempts = 0;

	private Announcement announcement = null;
	private boolean can_StudentsSeeScores = false;
	private boolean can_StudentsSeeReport = false;
	private int result_type = PerceptionSettings.PERCENTAGE;
	private int window_target = PerceptionSettings.NEW_WINDOW;
	private boolean updateAnnouncement = false;
	private String bbStoredScheduleName = null;
	
	private boolean displayAttemptsInformation = false;
	
	private SimpleContentSettings(){
		/// try to load defaults
		try{
			PerceptionSettings systemSettings = PerceptionSettings.loadFromCache();
			setWindowTarget( systemSettings.getDefaultAssessmentTarget() );
			setResultType( systemSettings.getDefaultGradeBookResultType() );
			
		}catch(Throwable t2){
			// ignore
		}
	}
	
	public Id getContentId(){
		return content.getId();
	}

	public String getAssessmentId(){
		return assessment_id;
	}

	public String getScheduleId(){
		return schedule_id;
	}

	public String[] getParticipantScheduleIds(){
		return schedule_ids; // not implemented
	}

	public int getMaxAttempts(){
		return max_attempts;
	}

	public boolean getAreAttemptsLimited(){
		return (getMaxAttempts() > 0);
	}

	public void setAssessmentId( String value ){
		assessment_id = value;
	}

	public void setScheduleId( String value ){
		schedule_id = value;
	}
	
	public void setParticipantScheduleIds( String[] values ){
		schedule_ids = values; // assumes they don't contain commas
	}

	public void setMaxAttempts( int attempts ){
		max_attempts = attempts;
	}
	
	// added 4.4
	public String getAnnouncementTitle(){
		if( announcement != null )
			return announcement.getTitle();
		return null;
	}
	
	public String getAnnouncementText(){
		if( announcement != null )
			return announcement.getBody().getText();
		return "";
	}
	
	public void setAnnouncementTitle( String value ){
		if( announcement == null )
			announcement = createAnnouncement();
		announcement.setTitle( value );

	}
	
	public void setAnnouncementText( String value ){
		if( announcement == null )
			announcement = createAnnouncement();
		FormattedText body = new FormattedText( value, announcement.getBody().getType() );	
		announcement.setBody( body );
	}
	
	public Id getAnnouncementCreatorId(){
		return announcement.getCreatorUserId();
	}
	
	public Calendar getAnnouncementStartDate(){
		return announcement.getRestrictionStartDate( );
	}
	
	public Calendar getAnnouncementEndDate(){
		return announcement.getRestrictionEndDate();
	}
	
	public void setAnnouncementStartDate( Calendar start ){
		
		if( (start == null || start.before(Calendar.getInstance())) && (announcement.getRestrictionEndDate() == null || announcement.getIsPermanent()) ){
			announcement.setIsPermanent( true );
		}else{
			announcement.setIsPermanent( false );
		}
		announcement.setRestrictionEndDate( start );
	}
	
	public void setAnnouncementEndDate( Calendar end ){
		
		if( end == null && (announcement.getRestrictionStartDate() == null || announcement.getRestrictionStartDate().before(Calendar.getInstance())) ){
			announcement.setRestrictionEndDate( end );
			announcement.setIsPermanent( true );
		}else{
			announcement.setRestrictionEndDate( end );
			announcement.setIsPermanent( false );
		}
	}
	
	public void setAnnouncementCreatorId( Id user_id ){
		if( announcement == null )
			announcement = createAnnouncement();
		announcement.setCreatorUserId( user_id );
	}
	
	public boolean getCanStudentsSeeScores(){
		return can_StudentsSeeScores;
	}
	
	public boolean getCanStudentsSeeReport(){
		return can_StudentsSeeReport;
	}

	public int getResultType(){
		return result_type;
	}

	public int getWindowTarget(){
		return window_target;
	}

	public void setCanStudentsSeeScores( boolean value ){
		can_StudentsSeeScores = value;
	}
	
	public void setCanStudentsSeeReport( boolean value ){
		can_StudentsSeeReport = value;
	}

	public void setResultType( int value ){
		result_type = value;
	}

	public void setWindowTarget( int value ){
		window_target = value;
	}
	
	
	public boolean getUpdateAnnouncement(){
		return this.updateAnnouncement;
	}
	
	public void setUpdateAnnouncement( boolean value ){
		this.updateAnnouncement = value;
	}
	
	
	public String getBbStoredScheduleName(){
		return bbStoredScheduleName;
	}
	
	public void setBbStoredScheduleName( String value ){
		bbStoredScheduleName = value;
	}
	
	// end of 4.4 stuff
	

	public static ContentSettings load( CourseSettings courseSettings, Content content ) throws PersistenceException {
		SimpleContentSettings settings = new SimpleContentSettings();
		settings.content = content;
		Id content_id = null;
		try{
			
			// load defaults
			settings.setCanStudentsSeeScores( courseSettings.getCanStudentsSeeScores() );
			settings.setCanStudentsSeeReport( courseSettings.getCanStudentsSeeReport() );
			
    		String body = content.getBody().getText();
	    	if( body != null ){
	    		String[] lines = body.split( "\n" );
	    		boolean parse = false;
	    		for( int i=0; i<lines.length; i++ ){
	    			String line = lines[i].trim();
	    			
	    			if( line.startsWith(HIDDEN_END_LINE) ){
	    				parse = false;
	    				break;
	    			}else if( parse ){
	    				int index = line.indexOf("=");
	    				if( index > 0 ){
	    					String param = line.substring( 0, index );
	    					String value = line.substring( index + 1 );
	    					
	    					// System.out.println( param + "=" + value );
	    					
		    			//	if( param.equalsIgnoreCase(HIDDEN_ATTEMPTS_PROP) ){
		    				//	assessment.setAttemptsAllowed( Integer.parseInt(value) );
		    			//	}else 
		    				if( value.trim().length() == 0 ){
		    					// ignore value, use default
		    				}else if( param.equalsIgnoreCase(CONTENT_ID_PARAM) ){
		    					content_id = readId( value, Content.DATA_TYPE, null );
		    				}else if( param.equalsIgnoreCase(SCHEDULE_ID_PARAM) ){
		    					settings.setScheduleId( value );
		    				}else if( param.equalsIgnoreCase(ASSESSMENT_ID_PARAM) ){
		    					settings.setAssessmentId( value );
		    				}else if( param.equalsIgnoreCase(MAX_ATTEMPTS_PARAM) ){
		    					settings.setMaxAttempts( Integer.parseInt(value) );
		    				}else if( param.equalsIgnoreCase(PARTICIPANT_SCHEDULE_IDS_PARAM) ){
		    					settings.setParticipantScheduleIds( value.split(",") );
		    				}else if( param.equalsIgnoreCase(ANNOUNCEMENT_ID_PARAM) ){
		    					settings.announcement = loadAnnouncement(value);
		    				}else if( param.equalsIgnoreCase(CAN_STUDENTS_SEE_SCORES_PARAM) ){
		    					settings.setCanStudentsSeeScores( WebUtils.readBoolean(value, settings.getCanStudentsSeeScores() ) );
		    				}else if( param.equalsIgnoreCase(CAN_STUDENTS_SEE_SCORES_PARAM) ){
		    					settings.setCanStudentsSeeReport( WebUtils.readBoolean(value, settings.getCanStudentsSeeReport() ) );
		    				}else if( param.equalsIgnoreCase(RESULT_TYPE_PARAM) ){
								settings.setResultType( WebUtils.readInt(value, settings.getResultType() ) );
		    				}else if( param.equalsIgnoreCase(WINDOW_TARGET_PARAM) ){
		    					settings.setWindowTarget( WebUtils.readInt(value, settings.getWindowTarget() ) );
		    				}else if( param.equalsIgnoreCase(BB_STORED_SCHEDULE_NAME) ){
		    					settings.setBbStoredScheduleName( value );
		    				}
	    				}
	    			}else if( line.startsWith(HIDDEN_START_LINE) ){
	    				parse = true;
	    			}
	    		}
		    	if( !BbUtils.isMatchingId( content_id, content.getId() ) ){
		    		// not the same content item, probably a copy so delete schedule
		    		settings.setScheduleId( null );
		    		settings.setParticipantScheduleIds( null );
		    	}
	    	}else{
	    		// no items set yet
	    		// leave as nulls
	    	}
	    }catch(PersistenceException t){
			throw t;
		}catch(Throwable t){
	    	// ignore, user has messed with values
	    	t.printStackTrace();
	    	
	    	settings.setScheduleId( null );
	    	settings.setParticipantScheduleIds( null );
	    }
	    
		// remove details from content body
		String contentBodyText = content.getBody().getText();
		String comments;
		int start = contentBodyText.indexOf( HIDDEN_START_LINE );
		int end = contentBodyText.indexOf( HIDDEN_END_LINE );
		if( start >= 0 && end >= 0 ){
			comments = contentBodyText.substring(0, start);
			comments += contentBodyText.substring( end + HIDDEN_END_LINE.length() );
		}else{
			comments = contentBodyText;
		}
		if( content.getBody().getType() != FormattedText.Type.HTML ){
			comments = WebUtils.deparseHtmlValue(comments);
		}
		FormattedText newBody = new FormattedText( comments, FormattedText.Type.HTML );
		//System.out.println("Setting body to: " + comments);
		content.setBody( newBody );
				
			
		return settings;
	}

	public void persist() throws PersistenceException, ValidationException{

		// save announcement
		if( announcement != null && !BbUtils.isNullOrEmpty(announcement.getBody().getText()) ){
			if( getUpdateAnnouncement() )
				announcement.setId( Id.UNSET_ID );
			BbUtils.getAnnouncementDbPersister().persist( announcement );
		}
		
		StringBuffer body = new StringBuffer();
		body.append( HIDDEN_START_LINE + "\n" );
//		body.append( HIDDEN_ATTEMPTS_PROP + "=" + assessment.getAttemptsAllowed() + "\n" );
		body.append( CONTENT_ID_PARAM + "=" + content.getId().toExternalString() + "\n" );
		if( content.getBody().getType() == FormattedText.Type.HTML ){
			body.append( BODY_TYPE_PARAM + "=" + HTML_BODY_TYPE + "\n" );
		}else if( content.getBody().getType() == FormattedText.Type.SMART_TEXT ){
			body.append( BODY_TYPE_PARAM + "=" + SMART_TEXT_BODY_TYPE + "\n" );
		}else{
			body.append( BODY_TYPE_PARAM + "=" + PLAIN_TEXT_BODY_TYPE + "\n" );
		}
		body.append( ASSESSMENT_ID_PARAM + "=" + getAssessmentId() + "\n" );
		body.append( CAN_STUDENTS_SEE_SCORES_PARAM + "=" + getCanStudentsSeeScores() + "\n" );
		body.append( CAN_STUDENTS_SEE_REPORT_PARAM + "=" + getCanStudentsSeeReport() + "\n" );
		body.append( RESULT_TYPE_PARAM + "=" + getResultType() + "\n" );
		body.append( WINDOW_TARGET_PARAM + "=" + getWindowTarget() + "\n" );
		if( !BbUtils.isNullOrEmpty(getBbStoredScheduleName()) ){
			body.append( BB_STORED_SCHEDULE_NAME + "=" + getBbStoredScheduleName() + "\n" );
		}
		if( getScheduleId() != null ){
			body.append( SCHEDULE_ID_PARAM + "=" + getScheduleId() + "\n" );
		}
		if( announcement != null )
			body.append( ANNOUNCEMENT_ID_PARAM + "=" + announcement.getId().toExternalString() + "\n" );
		body.append( MAX_ATTEMPTS_PARAM + "=" + getMaxAttempts() + "\n" );
		String[] scheduleIdArray = getParticipantScheduleIds();
		if( scheduleIdArray != null && scheduleIdArray.length > 0 ){
			StringBuffer sBuf = new StringBuffer();
			for( int i=0; i < scheduleIdArray.length; i++ ){
				sBuf.append( scheduleIdArray[i] );	
				if( i != scheduleIdArray.length - 1 ) // not last
					sBuf.append(",");	
			}
			body.append( PARTICIPANT_SCHEDULE_IDS_PARAM + "=" + sBuf.toString() + "\n" );
		}
		body.append( HIDDEN_END_LINE + "\n" );
		String contentBodyText = content.getBody().getText();
		String comments;
		int start = contentBodyText.indexOf( HIDDEN_START_LINE );
		int end = contentBodyText.indexOf( HIDDEN_END_LINE );
		if( start >= 0 && end >= 0 ){
			comments = contentBodyText.substring(0, start);
			comments += contentBodyText.substring( end + HIDDEN_END_LINE.length() + 1 );
		}else{
			comments = contentBodyText;
		}
			
		if( content.getBody().getType() == FormattedText.Type.HTML ){
			body.append( comments );
			//body.append( stripHtmlHeaders(comments) );
		}else{
			body.append( WebUtils.parseHtmlValue(comments) );
		}
		FormattedText newBody = new FormattedText( body.toString(), FormattedText.Type.HTML );
		content.setBody( newBody );
		// save the content object to store the params
		BbUtils.getContentDbPersister().persist( content );
				
	}

	private static Id readId( Object obj, DataType type, Id defaultValue ){
		try{
			return BbUtils.getBbPersistenceManager().generateId( type, (String) obj );
		}catch(Exception e){
			return defaultValue;
		}
	}
	
	private static Announcement loadAnnouncement( String id_str ) throws PersistenceException {
		Id id = readId( id_str, Announcement.DATA_TYPE, null );
		if( id != null ){
			try{
				Announcement announcement = BbUtils.getAnnouncementDbLoader().loadById( id );
				return announcement;
			}catch(KeyNotFoundException e){
				// announcement deleted
			}
		}
		return null;
	}
	
	private Announcement createAnnouncement(){
		Announcement announcement = new Announcement();
		FormattedText body = new FormattedText( "", FormattedText.Type.PLAIN_TEXT ); 
		announcement.setBody( body );
		announcement.setCourseId( content.getCourseId() );
		return announcement;
	}
	
	private static String stripHtmlHeaders( String source ){
		try{
			if( !BbUtils.isNullOrEmpty(source) ){
				String shortHtml = new String( source );
				String lcShortHtml = shortHtml.toLowerCase();
				int indexOfBodyTag = lcShortHtml.indexOf( "<body>" );
				int indexOfBodyEndTag = lcShortHtml.indexOf( "</body>" );
				if( indexOfBodyTag > 0 && indexOfBodyEndTag > 0 ){
					shortHtml = shortHtml.substring( indexOfBodyTag + 6, indexOfBodyEndTag );
					return shortHtml;
				}	
			}
		}catch(Exception e){
			System.out.println( "Perception: Failed to convert HTML string: " + e.getMessage() ); 
		}
		return source;
		
	}
	
	

}