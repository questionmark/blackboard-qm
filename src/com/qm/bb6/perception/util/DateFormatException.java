/*
 * @(#)DateFormatException.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.util;

/**
 * Thrown when Perception passes a date the bridge doesn't understand
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class DateFormatException extends Exception {
	
	public DateFormatException(){
		super();
	}

	public DateFormatException(String message){
		super(message);
	}

}
