/*
 * @(#)LaunchAssessmentServlet.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.servlet;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import blackboard.data.course.CourseMembership;
import blackboard.platform.context.Context;


import com.qm.bb6.perception.config.*;
import com.qm.bb6.perception.context.*;
import com.qm.bb6.perception.util.*;


/**
 * Logs the user into Perception
 *
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class LaunchAssessmentServlet extends PerceptionLoginServlet {
	
	public static final String ASSESSMENT_ID_PARAM = "AID";
	
			
	protected Map getPerceptionParameterMap( PerceptionSettings settings, HttpServletRequest request, Context ctx ){
	
		// get super map
		Map paramMap = super.getPerceptionParameterMap( settings, request, ctx);
		
		String assessment_id = request.getParameter( ASSESSMENT_ID_PARAM );
		paramMap.put( ASSESSMENT_ID_PARAM, assessment_id);
		
		// only add notify url if user is student
		if( ctx.getCourseMembership() != null ){
            if( ctx.getCourseMembership().getRole() == CourseMembership.Role.STUDENT ) {
                // nothing to do
            }else{
                if( settings.getUseExternalCourseId() ){
                    paramMap.put( "GROUP", ctx.getCourse().getBatchUid() );
                }else{
                    paramMap.put( "GROUP", ctx.getCourse().getCourseId() );
                }
            }
        }
			
		return paramMap;
	}
	
	// override super function
	protected String getLoginUrl( PerceptionSettings settings, HttpServletRequest request, Context ctx ){
		// if student go to student view
		// all others, including guest, grader and builder go to the EM
		// - Perception will reject users as they won't have access
		if( ctx.getCourseMembership() != null && ctx.getCourseMembership().getRole() == CourseMembership.Role.STUDENT )
			return settings.getPerceptionFullUrl();
		else
			return settings.getSessionFullUrl();		
	}	
	
	protected boolean launchEMIfPossible( PerceptionSettings settings, HttpServletRequest request, Context ctx ){
		if( ctx.getCourseMembership() != null && settings.getCanInstructorsTakeTests() ){
			return false;
		}
		return true;
	}
	
}
