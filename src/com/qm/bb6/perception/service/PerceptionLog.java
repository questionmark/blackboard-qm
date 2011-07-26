/*
 * @(#)PerceptionLog.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.service;

/**
 * Logging service for Perception
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public interface PerceptionLog {

	public static final int NORMAL_LOGGING = 0;
	public static final int DEBUG_LOGGING = 1;
	public static final int NO_LOGGING = 2;
	
	/*
	 * Logs a typical message
	 * @param message   message to log
	 */
	void log( String message );
	
	/*
	 * Logs a debug message if debugging is on
	 * @param message   message to log
	 */
	void logDebug( String message );
	
	/*
	 * Logs an error
	 * @param message   message to log
	 */
	void logError( String message );
	
	/*
	 * Logs a fatal error
	 * @param message   message to log
	 */
	void logFatal( String message );

	/*
	 * Logs a debug message if debugging is on
	 * @param message   message to log
	 * @param an exception that caused the problem
	 */
	void logDebug( String message, Throwable t );
	
	/*
	 * Logs an error
	 * @param message   message to log
	 * @param an exception that caused the problem
	 */
	void logError( String message, Throwable t );
	
	/*
	 * Logs a fatal error
	 * @param message   message to log
	 * @param an exception that caused the problem
	 */
	void logFatal( String message, Throwable t );

	/*
	 * @return   the current log mode (normal, none or debug)
	 */
	int getLogMode();
	
	/*
	 * Sets the logging mode
	 * @param mode 
	 * @throws java.lang.IllegalArgumentException   if the mode is not valid
	 */
	void setLogMode( int mode );
}
