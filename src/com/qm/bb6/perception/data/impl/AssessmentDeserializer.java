/*
 * @(#)AssessmentDeserializer.java 1.0.1 Sep 1 2005
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
public class AssessmentDeserializer extends PerceptionDeserializer {
		
	public static final String TAG_NAME = "Assessment";
	
	public static final String ASSESSMENT_ID_PARAM = "Assessment_ID";
	public static final String ASSESSMENT_NAME_PARAM = "Session_Name";
	
	private static PerceptionSerializerMap parameterMap = new PerceptionSerializerMap();
	
	static {
			
		parameterMap.put( ASSESSMENT_ID_PARAM, "AssessmentId", String.class );
  	    parameterMap.put( "Revision", "Revision", Integer.class );
        parameterMap.put( ASSESSMENT_NAME_PARAM, "SessionName" , String.class );
        parameterMap.put( "Author", "Author", String.class );
    //    parameterMap.put( "Save_Answers", "Save_Answers" );
    //    parameterMap.put( "Save_Answer_Data", "Save_Answer_Data" );
    //    parameterMap.put( "Open_Session", "Open_Session" );
    //    parameterMap.put( "Session_Password", "Session_Password" );
    //    parameterMap.put( "Session_Timed", "Session_Timed" );
    //    parameterMap.put( "Time_Limit", "Time_Limit" );
    //    parameterMap.put( "Template_Name", "Template_Name" );
    //    parameterMap.put( "When_Feedback", "When_Feedback" );
    //    parameterMap.put( "End_Feedback", "End_Feedback" );
    //    parameterMap.put( "Exclude_Unscored", "Exclude_Unscored" );
        parameterMap.put( "Description", "Description", String.class );
    //    parameterMap.put( "Monitored", "Monitored" );
        parameterMap.put( "Editor", "Editor", String.class );
        parameterMap.put( "Version", "Version", String.class );
    //    parameterMap.put( "Permit_External_Call", "Permit_External_Call" );
        parameterMap.put( "Created_Date", "Created", Calendar.class );
        parameterMap.put( "Modified_Date", "Modified", Calendar.class );
	}
	
	
	public static PerceptionSerializerMap getAssessmentParameterMap(){
		return parameterMap;
	}

	public PerceptionSerializerMap getParameterMap(){
		return parameterMap;
	}
	
	public Class getSerializedClass(){
		return PerceptionAssessment.class;
	}
		
}
