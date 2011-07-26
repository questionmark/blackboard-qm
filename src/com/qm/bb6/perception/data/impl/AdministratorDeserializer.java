/*
 * @(#)AdministratorDeserializer.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.data.impl;

import java.util.*;

import com.qm.bb6.perception.data.*;
import com.qm.bb6.perception.service.*;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class AdministratorDeserializer extends PerceptionDeserializer {
		
	public static final String TAG_NAME = "Administrator";
	
	public static final String ADMINISTRATOR_ID_PARAM = "Administrator_ID";
	public static final String ADMINISTRATOR_NAME_PARAM = "Administrator_Name";

	private static PerceptionSerializerMap parameterMap = new PerceptionSerializerMap();
	
	static {
			
		parameterMap.put( ADMINISTRATOR_ID_PARAM, "UserId", String.class );
		parameterMap.put( ADMINISTRATOR_NAME_PARAM, "UserName", String.class );
        parameterMap.put( "Password", "Password" , String.class );
        parameterMap.put( "Authenticate_Ext", "AuthenticateExt", Integer.class, false, false );
        parameterMap.put( "Email", "PrimaryEmail" , String.class );
       	parameterMap.put( "Profile_Name", "ProfileName" , String.class );
      	parameterMap.put( "URL", "URL" , String.class );
	}
	
	
	public static PerceptionSerializerMap getAdministratorParameterMap(){
		return parameterMap;
	}

	public PerceptionSerializerMap getParameterMap(){
		return parameterMap;
	}
	
	public Class getSerializedClass(){
		return PerceptionAdministrator.class;
	}
		
}

