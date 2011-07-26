/*
 * PerceptionTopicMembership.java
 *
 * Created on February 16, 2007, 10:53 AM
 *
 * Copyright 2006 VLE Genius Ltd. All rights reserved.
 */

package com.qm.bb6.perception.data;

/**
 *
 * @author Matt Elton
 */
public class PerceptionTopicMembership extends PerceptionPermission {
	
	private String administrator_id = null;
	private String topic_id = null;
	
	public String getAdministratorId(){
		return this.administrator_id;
	}
	
	public String getTopicId(){
		return this.topic_id;
	}
	
	public void setAdministratorId( String value ){
		this.administrator_id = value;
	}
	
	public void setTopicId( String value ){
		this.topic_id = value;
	}
	
}

