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
import blackboard.data.content.Content;
import blackboard.persist.*;
import blackboard.platform.plugin.*;
import blackboard.portal.data.*;
import blackboard.portal.servlet.PortalUtil;

import com.qm.bb6.perception.config.ContentSettings;
import com.qm.bb6.perception.util.*;

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
	
	private Content content = null;
	private String assessment_id = null;
	private String schedule_id = null;
	private String[] schedule_ids = null;
	private int max_attempts = 0;

	public SimpleContentSettings() {
		//TODO
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
	
	public static ContentSettings load( Content content ){
		SimpleContentSettings settings = new SimpleContentSettings();
		settings.content = content;
		Id content_id = null;
		try{
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
	    					
	    				//	System.out.println( param + "=" + value );
	    					
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
		System.out.println("Setting body to: " + comments);
		content.setBody( newBody );
				
			
		return settings;
	}

	public void persist() throws PersistenceException, ValidationException{
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
		if( getScheduleId() != null ){
			body.append( SCHEDULE_ID_PARAM + "=" + getScheduleId() + "\n" );
		}
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

}