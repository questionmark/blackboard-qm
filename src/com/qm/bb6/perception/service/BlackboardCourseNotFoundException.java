/*
 * @(#)BlackboardCourseNotFoundException.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.service;

/**
 * Thrown when a Blackboard course is not found and is required
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class BlackboardCourseNotFoundException extends PerceptionDataException {
	
	public BlackboardCourseNotFoundException(){
		super();
	}

	public BlackboardCourseNotFoundException(String message){
		super(message);
	}

}
