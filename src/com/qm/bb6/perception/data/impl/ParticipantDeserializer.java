/*
 * @(#)ParticipantDeserializer.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.data.impl;

import java.util.*;

import org.w3c.dom.Element;

import com.qm.bb6.perception.data.*;
import com.qm.bb6.perception.service.*;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class ParticipantDeserializer extends PerceptionDeserializer {
		
	public static final String TAG_NAME = "Participant";
	
	public static final String PARTICIPANT_ID_PARAM = "Participant_ID";
	public static final String PARTICIPANT_NAME_PARAM = "Participant_Name";

	private static PerceptionSerializerMap parameterMap = new PerceptionSerializerMap();
	
	static {
			
		parameterMap.put( PARTICIPANT_ID_PARAM, "UserId", String.class );
		parameterMap.put( PARTICIPANT_NAME_PARAM, "UserName", String.class );
        parameterMap.put( "Password", "Password" , String.class );
        parameterMap.put( "Authenticate_Ext", "AuthenticateExt", Integer.class, false, false );
        parameterMap.put( "First_Name", "FirstName", String.class );
        parameterMap.put( "Last_Name", "LastName", String.class );
        parameterMap.put( "Middle_Name", "MiddleName", String.class );
        parameterMap.put( "Use_Correspondence", "UseCorrespondence" , Integer.class,false, false );
        parameterMap.put( "Primary_Address_1", "PrimaryAddress1" , String.class );
        parameterMap.put( "Primary_Address_2", "PrimaryAddress2" , String.class );
        parameterMap.put( "Primary_City", "PrimaryCity" , String.class );
        parameterMap.put( "Primary_State", "PrimaryState" , String.class );
        parameterMap.put( "Primary_ZIP_Code", "PrimaryZIP" , String.class );
        parameterMap.put( "Primary_Country", "PrimaryCountry" , String.class );
        parameterMap.put( "Primary_Phone", "PrimaryPhone" , String.class );
        parameterMap.put( "Primary_Fax", "PrimaryFax" , String.class );
        parameterMap.put( "Primary_Email", "PrimaryEmail" , String.class );
        parameterMap.put( "Salutation", "Salutation" , String.class );
        parameterMap.put( "Organization_Name", "OrganizationName" , String.class );
        parameterMap.put( "Department", "Department" , String.class );
        parameterMap.put( "Title", "Title" , String.class );
        parameterMap.put( "Gender", "Gender" , String.class );
       	parameterMap.put( "Date_Registration", "DateRegistration" , Calendar.class );
      	parameterMap.put( "URL", "URL" , String.class );
      	
      	// group Id
      	parameterMap.put( "GroupIDList", "GroupMemberships" , Element.class, false, false );
      	
	}
	
	
	public static PerceptionSerializerMap getParticipantParameterMap(){
		return parameterMap;
	}

	public PerceptionSerializerMap getParameterMap(){
		return parameterMap;
	}
	
	public Class getSerializedClass(){
		return PerceptionParticipant.class;
	}
		
}

