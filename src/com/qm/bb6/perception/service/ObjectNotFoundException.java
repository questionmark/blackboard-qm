/*
 * @(#)ObjectNotFoundException.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.service;

/**
 * Thrown when a Perception object is not found
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class ObjectNotFoundException extends PerceptionDataException {
	
	public ObjectNotFoundException(){
		super();
	}

	public ObjectNotFoundException(String message){
		super(message);
	}

}
