/*
 * @(#)PerceptionNotFoundException.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.service;

/**
 * Thrown when Perception cannot be contacted
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class PerceptionNotFoundException extends PerceptionDataException {
	
	public PerceptionNotFoundException(){
		super();
	}

	public PerceptionNotFoundException(String message){
		super(message);
	}

}
