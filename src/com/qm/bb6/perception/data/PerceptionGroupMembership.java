/*
 * @(#)PerceptionGroupMembership.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.data;


/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class PerceptionGroupMembership {

	private String group_id = null;
	private String user_id = null;
	private boolean is_admin = true;

	public PerceptionGroupMembership() {
		//TODO
	}

	public String getGroupId(){
		return group_id;
	}

	public String getUserId(){
		return user_id;
	}

	public boolean getIsAdmin(){
		return is_admin;
	}

	public void setGroupId( String value ){
		group_id = value;
	}

	public void setUserId( String value ){
		user_id = value;
	}

	public void setIsAdmin( boolean value ){
		is_admin = value;
	}



}