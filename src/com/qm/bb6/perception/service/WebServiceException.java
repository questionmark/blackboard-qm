/*
 * @(#)WebServiceException.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.service;

/**
 * Thrown when Perception makes in invalid request from Bb
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class WebServiceException extends Exception {
	
	private int code = 0;
	
	public WebServiceException(int code, String message){
		super(message);
		this.code = code;
	}

	public int getCode(){
		return code;
	} 
	
	public String toString(){
		return this.getClass().getName() + ": (" + getCode() + ", " + this.getMessage() + ")";
	}
}
