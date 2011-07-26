/*
 * @(#)PerceptionAssessment.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.data;

import java.util.Calendar;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class PerceptionAssessment {

	private String assessment_id = null;
	private String sessionName = null;
	private String author = null;
	private Calendar created = null;
	private String editor = null;
	private Calendar modified = null;
	private String version = null;
	private int revision = 0;
	private String description = null;
	private int attemptsAllowed = 0; // unlimited

	public PerceptionAssessment() {
		//TODO
	}

	public String getAssessmentId(){
		return assessment_id;
	}

	public String getSessionName(){
		return sessionName;
	}

	public String getAuthor(){
		return author;
	}

	public Calendar getCreated(){
		return created;
	}

	public String getEditor(){
		return editor;
	}

	public Calendar getModified(){
		return modified;
	}

	public String getVersion(){
		return version;
	}

	public int getRevision(){
		return revision;
	}

	public String getDescription(){
		return description;
	}

	public int getAttemptsAllowed(){
		return attemptsAllowed;
	}

	public void setAssessmentId( String value ){
		assessment_id = value;
	}

	public void setSessionName( String value ){
		sessionName = value;
	}

	public void setAuthor( String value ){
		author = value;
	}

	public void setCreated( Calendar value ){
		created = value;
	}

	public void setEditor( String value ){
		editor = value;
	}

	public void setModified( Calendar value ){
		modified = value;
	}

	public void setVersion( String value ){
		version = value;
	}

	public void setRevision( int value ){
		revision = value;
	}

	public void setDescription( String value ){
		description = value;
	}

	public void setAttemptsAllowed( int value ){
		attemptsAllowed = value;
	}

}