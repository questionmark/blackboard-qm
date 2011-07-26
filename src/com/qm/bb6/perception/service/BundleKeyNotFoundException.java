/*
 * @(#)BundleKeyNotFoundException.java	java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.service;

/**
 * Service holder for all bridge services
 * such as logging
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class BundleKeyNotFoundException extends Exception {
		
	private Throwable t;
	
	public BundleKeyNotFoundException(){
		super();
	}

	public BundleKeyNotFoundException(Throwable t){
		super(t.getMessage());
		this.t = t;
	}

	public Throwable getCause(){
		return t;
	}
}