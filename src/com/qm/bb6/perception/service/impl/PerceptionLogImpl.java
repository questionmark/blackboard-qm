/*
 * @(#)PerceptionLogImpl.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.service.impl;

import com.qm.bb6.perception.service.PerceptionLog;

/**
 * Default logging service for Perception
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class PerceptionLogImpl implements PerceptionLog {

	private static final String PERCEPTION_LOG_START = "Questionmark: ";
	private static final String PERCEPTION_ERROR_START = "Questionmark Error!: ";
	private static final String PERCEPTION_FATAL_START = "Questionmark FATAL!: ";
	
	private int logMode = NORMAL_LOGGING;
	
	/**
	 * @param debugMode   set to true to log debug messages
	 */
	public PerceptionLogImpl( int mode ){
		setLogMode(mode);
	}
	
	public void log( String message ){
		if( getLogMode() != NO_LOGGING )
			System.out.println( PERCEPTION_LOG_START + message );
	}
	
	public void logDebug( String message ){
		if( getLogMode() == DEBUG_LOGGING )
			log( message );
	}
	
	public void logError( String message ){
		if( getLogMode() != NO_LOGGING )
			System.out.println( PERCEPTION_ERROR_START + message );
	}
	
	public void logFatal( String message ){
		System.out.println( PERCEPTION_FATAL_START + message ); // always log
	}

	public void logDebug( String message, Throwable t ){
		if( getLogMode() == DEBUG_LOGGING ){
			log( message );
			t.printStackTrace();
		}
		
	}
	
	public void logError( String message, Throwable t ){
		if( getLogMode() != NO_LOGGING ){
			logError( message );
			t.printStackTrace();
		}
	}
	
	public void logFatal( String message, Throwable t ){
		logFatal( message );
		t.printStackTrace();
	}
	
	/**
	* @return the log mode eg DEBUG_LOGGING
	*/
	public int getLogMode(){
		return logMode;
	}
	
	public void setLogMode( int mode ){
		if( mode == NO_LOGGING || mode == NORMAL_LOGGING || mode == DEBUG_LOGGING )
			logMode = mode;
		else
			throw new IllegalArgumentException();
		
	}
}
