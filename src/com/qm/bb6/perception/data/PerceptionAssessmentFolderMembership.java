/*
 * PerceptionAssessmentFolderMembership.java
 *
 * Created on February 15, 2007, 8:45 PM
 *
 * Copyright 2006 VLE Genius Ltd. All rights reserved.
 */
package com.qm.bb6.perception.data;

/**
 *
 * @author Matt Elton
 */
public class PerceptionAssessmentFolderMembership extends PerceptionPermission {
	
	private String administrator_id = null;
	private String folder_id = null;
	
	public String getAdministratorId(){
		return this.administrator_id;
	}
	
	public String getFolderId(){
		return this.folder_id;
	}
	
	public void setAdministratorId( String value ){
		this.administrator_id = value;
	}
	
	public void setFolderId( String value ){
		this.folder_id = value;
	}
		
}
