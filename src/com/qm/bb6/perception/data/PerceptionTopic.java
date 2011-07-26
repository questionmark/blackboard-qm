/*
 * PerceptionTopic.java
 *
 * Created on February 15, 2007, 10:44 AM
 *
 * Copyright 2006 VLE Genius Ltd. All rights reserved.
 */

package com.qm.bb6.perception.data;
/**
 *
 * @author Matt Elton
 */
public class PerceptionTopic {
	
	public static final String ROOT_TOPIC = "0";
	
	private String topic_id = null;
	private String parent_id = null;
	private String topic_name = null;
	private String description = null;

	public PerceptionTopic() {
		//TODO
	}

	public String getTopicId(){
		return topic_id;
	}

	public String getParentId(){
		return parent_id;
	}

	public String getTopicName(){
		return topic_name;
	}

	public String getDescription(){
		return description;
	}

	public void setTopicId( String value ){
		topic_id = value;
	}

	public void setParentId( String value ){
		parent_id = value;
	}

	public void setTopicName( String value ){
		topic_name = value;
	}

	public void setDescription( String value ){
		description = value;
	}
	
	
}
