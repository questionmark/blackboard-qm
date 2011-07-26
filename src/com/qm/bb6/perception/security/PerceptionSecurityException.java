/*
 * @(#)PerceptionSecurityException.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.security;

import com.qm.bb6.perception.service.PerceptionDataException;

/**
 * A security exception
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class PerceptionSecurityException extends PerceptionDataException {
	
	public PerceptionSecurityException(){
		super();
	}

	public PerceptionSecurityException(String message){
		super(message);
	}

}
