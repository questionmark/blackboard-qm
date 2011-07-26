/*
 * @(#)SystemAdminServlet.java 1.0.1 Sep 1 2005
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

import blackboard.data.course.*;
import blackboard.data.user.User;
import blackboard.persist.*;
import blackboard.platform.context.Context;

import com.qm.bb6.perception.config.*;
import com.qm.bb6.perception.util.*;
import com.qm.bb6.perception.security.*;
import com.qm.bb6.perception.service.*;

/**
 * Course admin servlet for course settings
 * Access available to instructor and TA (limited) only
 *
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class SystemAdminServlet extends AuthenticatedServlet {
	
	public boolean doContent(HttpServletRequest request, HttpServletResponse response, Context ctx) throws ServletException, IOException{
		
		Locale locale = (Locale) request.getAttribute( LOCALE_PARAM );
		if( isUpdate(request) ){
			
			// values posted so update config
			PerceptionLog log = PerceptionServiceManager.getPerceptionLog();

			PerceptionSettings settings = PerceptionSettings.load();

			settings.setPerceptionHost( request.getParameter(PerceptionSettings.PERCEPTION_HOST_PARAM));
			settings.setQMWISeUrl( request.getParameter(PerceptionSettings.QMWISE_URL_PARAM));
			settings.setPerceptionUrl( request.getParameter(PerceptionSettings.PERCEPTION_URL_PARAM));
			settings.setSessionUrl( request.getParameter(PerceptionSettings.SESSION_URL_PARAM));
	//		settings.setClientID( request.getParameter(PerceptionSettings.CLIENT_ID_PARAM));
	//		settings.setClientPassword( request.getParameter(PerceptionSettings.CLIENT_PASSWORD_PARAM));
			settings.setPIPSecretKey( request.getParameter(PerceptionSettings.PIP_SECRET_KEY_PARAM));
			settings.setTrustedSecretKey( settings.getPIPSecretKey() );
	//		settings.setTrustedSecretKey( request.getParameter(PerceptionSettings.TRUSTED_SECRET_KEY_PARAM));
			settings.setLoggingLevel( WebUtils.readInt(request.getParameter(PerceptionSettings.LOGGING_LEVEL_PARAM), settings.getLoggingLevel()));
			settings.setUseChecksum( WebUtils.readBoolean(request.getParameter(PerceptionSettings.USE_CHECKSUM_PARAM), settings.getUseChecksum()));
			settings.setIsSynchronizationEnabled( WebUtils.readBoolean(request.getParameter(PerceptionSettings.IS_SYNCHRONIZATION_ENABLED_PARAM), settings.getIsSynchronizationEnabled()));
			settings.setUseExternalUserId( WebUtils.readBoolean(request.getParameter(PerceptionSettings.USE_EXTERNAL_USER_ID_PARAM), settings.getUseExternalUserId()));
			settings.setUseExternalCourseId( WebUtils.readBoolean(request.getParameter(PerceptionSettings.USE_EXTERNAL_COURSE_ID_PARAM), settings.getUseExternalCourseId()));
			settings.setGradeBookResultsSelection( WebUtils.readInt(request.getParameter(PerceptionSettings.GRADE_BOOK_RESULTS_SELECTION_PARAM), settings.getGradeBookResultsSelection()));
			settings.setCanStudentsSeeScores( WebUtils.readBoolean(request.getParameter(PerceptionSettings.CAN_STUDENTS_SEE_SCORES_PARAM), settings.getCanStudentsSeeScores()));
			settings.setCanStudentsSeeReport( WebUtils.readBoolean(request.getParameter(PerceptionSettings.CAN_STUDENTS_SEE_REPORT_PARAM), settings.getCanStudentsSeeReport()));
			settings.setCanInstructorsTakeTests( WebUtils.readBoolean(request.getParameter(PerceptionSettings.INSTRUCTORS_CAN_TAKE_TEST_PARAM), settings.getCanInstructorsTakeTests()));
			settings.setCreateAuthoringRights( WebUtils.readBoolean(request.getParameter(PerceptionSettings.CREATE_AUTHORING_RIGHTS_PARAM), settings.getCreateAuthoringRights()));

			log.setLogMode( settings.getLoggingLevel() );
			
			try{
				settings.persist();
			}catch(Exception e){
				log.logDebug( "Error saving system settings", e );
				throw new ServletException("Failed to save system configuration details", e);
			}
			return sendSuccess(request, response);

		}else{
			// send course admin page
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/system_admin.jsp").forward(request, response);
		}
		return true;
	}
			
	protected boolean requiresAdminPrivileges(){
		return true;
	}

	protected boolean requiresControlPanelPrivileges(){
		return false;
	}

	protected boolean requiresCoursePrivileges(){
		return false;
	}


}
