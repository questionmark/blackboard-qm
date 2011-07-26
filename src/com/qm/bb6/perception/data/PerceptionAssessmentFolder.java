/*
 * PerceptionAssessmentFolder.java
 *
 * Created on February 15, 2007, 10:48 AM
 *
 * Copyright 2006 VLE Genius Ltd. All rights reserved.
 */

package com.qm.bb6.perception.data;
/**
 *
 * @author Matt Elton
 */
public class PerceptionAssessmentFolder {
	
	private String folder_id = null;
	private String parent_id = null;
	private String folder_name = null;
	private String description = null;

	public PerceptionAssessmentFolder() {
		//TODO
	}

	public String getAssessmentFolderId(){
		return folder_id;
	}

	public String getParentId(){
		return parent_id;
	}

	public String getAssessmentFolderName(){
		return folder_name;
	}

	public String getDescription(){
		return description;
	}

	public void setAssessmentFolderId( String value ){
		folder_id = value;
	}

	public void setParentId( String value ){
		parent_id = value;
	}

	public void setAssessmentFolderName( String value ){
		folder_name = value;
	}

	public void setDescription( String value ){
		description = value;
	}
	
	
}
