/*
 * @(#)PerceptionGroup.java 1.0.1 Sep 1 2005
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
public class PerceptionGroup {

	private String group_id = null;
	private String parent_id = null;
	private String group_name = null;
	private String description = null;

	public PerceptionGroup() {
		//TODO
	}

	public String getGroupId(){
		return group_id;
	}

	public String getParentId(){
		return parent_id;
	}

	public String getGroupName(){
		return group_name;
	}

	public String getDescription(){
		return description;
	}

	public void setGroupId( String value ){
		group_id = value;
	}

	public void setParentId( String value ){
		parent_id = value;
	}

	public void setGroupName( String value ){
		group_name = value;
	}

	public void setDescription( String value ){
		description = value;
	}



}