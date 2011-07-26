/*
 * @(#)PerceptionDataException.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.service;

import com.qm.bb6.perception.security.PerceptionSecurityException;

/**
 * Thrown when Perception returns invalid data<br><br><br>
 *<br><br>
 *	1 to 99 - security errors<br><br>
 *<br>
 *         1 - Security header required but missing<br><br>
 *         2 - Client ID missing or blank<br><br>
 *         3 - Client ID invalid<br><br>
 *         4 - Client has insufficient rights<br><br>
 *         5 - Checksum missing or blank<br><br>
 *         6 - Checksum invalid<br><br>
 *         7 - Trust header required but missing<br><br>
 *         8 - Trust header has wrong format<br><br>
 *         9 - Encoding missing or blank<br><br>
 *         10 - Encoding not recognized<br><br>
 *         11 - Digest missing or blank<br><br>
 *         12 - Signature missing or blank<br><br>
 *         13 - Invalid signature<br><br>
 *         14 - Invalid digest<br><br>
 *  100 to 999 - parameter errors<br><br>
 *          101 - Participant ID missing or blank<br><br>
 *          102 - Participant ID invalid - 0 or cannot be converted to an integer<br><br>
 *          201 - Group ID missing or blank<br><br>
 *          202 - Group ID invalid - 0 or cannot be converted to an integer<br><br>
 *          301 - Assessment ID missing or blank<br><br>
 *          302 - Assessment ID invalid - 0 or cannot be converted to an integer<br><br>
 *          303 - Assessment ID invalid - must be 16 characters<br><br>
 *          401 - Schedule ID missing or blank<br><br>
 *          402 - Schedule ID invalid - 0 or cannot be converted to an integer<br><br>
 *          501 - Result ID missing or blank<br><br>
 *          502 - Result ID invalid - 0 or cannot be converted to an integer<br><br>
 *          601 - Notification URL missing or blank<br><br>
 *          602 - Administrator name missing or blank<br><br>
 *          603 - Participant name missing or blank.<br><br>
 *          701 - Administrator ID missing or blank<br><br>
 *          702 - Administrator ID invalid - 0 or cannot be converted to an integer<br><br>
 *          703 - Profile name missing or blank<br><br>
 *          704 - Profile ID missing or blank<br><br>
 *          705 - Profile ID invalid - 0 or cannot be converted to an integer<br><br>
 *          801 - Question ID missing or blank<br>
 *          802 - Question ID invalid - 0 or cannot be converted to an integer<br>
 *          803 - Question ID invalid - must be 16 characters<br>
 *  1000 to 1999 - operation errors<br>
 *          1101 - Cannot find participant<br>
 *          1102 - Cannot create participant<br>
 *          1103 - Cannot modify participant<br>
 *          1104 - Cannot delete participant<br>
 *          1105 - Cannot lock participant record<br>
 *          1106 - Cannot create participant. Participant exists.<br>
 *          1107 - Participant is not a member of the group.<br>
 *          1201 - Cannot find group<br>
 *          1202 - Cannot create group<br>
 *          1203 - Cannot modify group<br>
 *          1204 - Cannot delete group<br>
 *          1205 - Cannot lock group record<br>
 *          1206 - Cannot find group membership<br>
 *          1207 - Cannot create group membership<br>
 *          1208 - Cannot modify group membership<br>
 *          1209 - Cannot delete group membership<br>
 *          1210 - Cannot find group ownership<br>
 *          1211 - Cannot create group ownership<br>
 *          1212 - Cannot modify group ownership<br>
 *          1213 - Cannot delete group ownership<br>
 *          1214 - Cannot create group. Group exists<br>
 *          1301 - Cannot find assessment<br>
 *          1302 - Cannot create assessment<br>
 *          1303 - Cannot modify assessment<br>
 *          1302 - Cannot delete assessment<br>
 *          1304 - Cannot lock assessment record<br>
 *          1401 - Cannot find schedule<br>
 *          1402 - Cannot create schedule<br>
 *          1403 - Cannot modify schedule<br>
 *          1404 - Cannot delete schedule<br>
 *          1405 - Cannot lock schedule record<br>
 *          1501 - Cannot find result<br>
 *          1502 - Cannot create result<br>
 *          1503 - Cannot modify result<br>
 *          1502 - Cannot delete result<br>
 *          1504 - Cannot lock result record<br>
 *          1511 - Cannot find assessment result<br>
 *          1512 - Cannot create assessment result<br>
 *          1513 - Cannot modify assessment result<br>
 *          1514 - Cannot delete assessment result<br>
 *          1521 - Cannot find question result        <br>
 *          1522 - Cannot create question result<br>
 *          1523 - Cannot modify question result<br>
 *          1524 - Cannot delete question result<br>
 *          1531 - Cannot find outcome        <br>
 *          1532 - Cannot create outcome<br>
 *          1533 - Cannot modify outcome<br>
 *          1534 - Cannot delete outcome<br>
 *          1541 - Cannot find answer        <br>
 *          1542 - Cannot create answer <br>
 *          1543 - Cannot modify answer <br>
 *          1544 - Cannot delete answer<br>
 *          1551 - Cannot find feedback        <br>
 *          1552 - Cannot create feedback  <br>
 *          1553 - Cannot modify feedback <br>
 *          1554 - Cannot delete feedback <br>
 *          1561 - Cannot find topic score        <br>
 *          1562 - Cannot create topic score  <br>
 *          1563 - Cannot modify topic score <br>
 *          1564 - Cannot delete topic score <br>
 *          1571 - Cannot find comment        <br>
 *          1572 - Cannot create comment        <br>
 *          1573 - Cannot modify comment        <br>
 *          1574 - Cannot delete comment        <br>
 *          1581 - Cannot find topic <br>
 *          1582 - Cannot create topic <br>
 *          1583 - Cannot modify topic <br>
 *          1584 - Cannot delete topic <br>
 *          1601 - Cannot find administrator<br>
 *          1602 - Cannot create administrator<br>
 *          1603 - Cannot modify administrator<br>
 *          1604 - Cannot delete administrator<br>
 *          1605 - Cannot lock administrator record<br>
 *          1606 - Cannot create administrator. Administrator Exists<br>
 *          1607 - Cannot find profile<br>
 *          1608 - Profile has the ability to use QMWISe<br>
 *          1701- Cannot find question.<br>
 *          1701- Cannot create question. <br>
 *          1701- Cannot modify question.<br>
 *          1701- 	Cannot delete question.<br>
 *          1801- Insufficient questions of the specified types are available to construct the assessment.<br>
 *          1802- Expiry Date missing or blank.<br>
 *          1803- Invalid database connection string.<br>
 *  2000 to 2999 - configuration errors<br>
 *          2001 - SoapLogFilePath not specified<br>
 *          2002 - SoapLogFilePath invalid<br>
 *          2003 - SoapLogLevel not specified<br>
 *          2004 - SoapLogLevel invalid<br>
 *          2011 - TraceLogFilePath not specified<br>
 *          2012 - TraceLogFilePath invalid<br>
 *          2013 - TraceLogLevel not specified<br>
 *          2014 - TraceLogLevel invalid<br>
 *          2015 - SecurityDatabase not specified or invalid<br>
 *          2016 - AssessmentDatabase not specified or invalid<br>
 *          2017 - AnswerDatabase not specified or invalid<br>
 *          2018 - ServerKey not specified or invalid<br>
 *          2019 - ExpireTime not specified or invalid<br>
 *          2020 - AssessmentURL not specified or invalid<br>
 *          2021 - ListURL not specified or invalid<br>
 *          2022 - AdminURL not specified or invalid<br>
 *          2023 - ReportURL not specified or invalid<br>
 *          2024 - ReportTemplate not specified or invalid<br>
 *          2025 - Invalid trusted key<br>
 *          2026 - QuestionDatabase not specified or invalid<br>
 * <br>
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class PerceptionDataException extends Exception {
	
	public static final int PARTICIPANT_NOT_FOUND = 1101;
	public static final int GROUP_NOT_FOUND = 1201;
	public static final int PARTICIPANT_GROUP_MEMBERSHIP_NOT_FOUND = 1107;
	public static final int GROUP_MEMBERSHIP_NOT_FOUND = 1206;
	public static final int GROUP_OWNERSHIP_NOT_FOUND = 1210;
	
	public static final int ASSESSMENT_NOT_FOUND = 1301;
	public static final int SCHEDULE_NOT_FOUND = 1401;
	public static final int RESULT_NOT_FOUND = 1501;

	public static final int ASSESSMENT_RESULT_NOT_FOUND = 1301;

	public static final int ADMINISTRATOR_NOT_FOUND = 1601;
	public static final int PROFILE_NOT_FOUND = 1607;
	
	private static int[] objectNotFoundCodes = new int[]{ 	PARTICIPANT_NOT_FOUND, GROUP_NOT_FOUND, PARTICIPANT_GROUP_MEMBERSHIP_NOT_FOUND,
															GROUP_MEMBERSHIP_NOT_FOUND, GROUP_OWNERSHIP_NOT_FOUND, ASSESSMENT_NOT_FOUND,
															SCHEDULE_NOT_FOUND, RESULT_NOT_FOUND, ASSESSMENT_RESULT_NOT_FOUND,
															ADMINISTRATOR_NOT_FOUND, PROFILE_NOT_FOUND };
	
	private int code = 0; //  not specified
	
	public PerceptionDataException(){
		super();
	}

	public PerceptionDataException(String message){
		super(message);
	}

	public int getCode(){
		return code;
	}
	
	public static PerceptionDataException getInstance(int code){
		return getInstance( code, null );
	}

	public static PerceptionDataException getInstance(int code, String message){
		
		String newClass = null; // default
		if( code >= 1 && code < 100 ){
			newClass = "com.qm.bb6.perception.security.PerceptionSecurityException";
		}else if( isObjectNotFound(code) ){
			newClass = "com.qm.bb6.perception.service.ObjectNotFoundException";
		}
		
		if( newClass != null ){
			try{
				Class expectionClass = Class.forName( newClass );
				PerceptionDataException exception;
				if( expectionClass != null ){
					exception = (PerceptionDataException) expectionClass.getConstructor( new Class[]{String.class} ).newInstance( new Object[]{ message} );	
				}else{
					exception = (PerceptionDataException) expectionClass.newInstance();
				}
				exception.code = code;
				return exception;
			}catch(Throwable t){
				// failed to create, ignore programming error
			}
		}
		
		PerceptionDataException exception = new PerceptionDataException(message);
		exception.code = code;
		return exception;
	}

	private static boolean isObjectNotFound( int code ){
		for( int i=0; i< objectNotFoundCodes.length; i++ ){
			if( objectNotFoundCodes[i] == code )
				return true;
		}
		return false;
	}
}
