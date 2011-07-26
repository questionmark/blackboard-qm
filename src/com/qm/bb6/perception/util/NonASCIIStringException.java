/*
 * @(#)NonASCIIStringException.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.util;

/**
 * Thrown when a value passed to an ASCII function is not ASCII format
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class NonASCIIStringException extends Exception {
	
	public NonASCIIStringException(){
		super();
	}

	public NonASCIIStringException(String message){
		super(message);
	}

}
