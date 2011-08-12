/*
 * @(#)PerceptionSettings.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.questionmark.legacy;

import blackboard.base.*;
import blackboard.data.ValidationException;
import blackboard.data.course.*;
import blackboard.data.user.User;
import blackboard.persist.*;
import blackboard.platform.context.Context;
import blackboard.platform.plugin.*;
import blackboard.portal.data.*;
import blackboard.portal.servlet.PortalUtil;

import java.util.*;


/**
 * Settings holder for all general bridge settings
 * such as user permissions, perception url,
 * passwords etc
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class PerceptionSettings extends ConfigSettings {

	public static final String PERCEPTION_HOST_PARAM = "perceptionHost";
	public static final String QMWISE_URL_PARAM = "QMWISe_Url";
	public static final String PERCEPTION_URL_PARAM = "Perception_Url";
	public static final String SESSION_URL_PARAM = "sessionDll";
	public static final String CLIENT_ID_PARAM = "clientID";
	public static final String CLIENT_PASSWORD_PARAM = "clientPassword";
	public static final String PIP_SECRET_KEY_PARAM = "pipSecretKey";
	public static final String TRUSTED_SECRET_KEY_PARAM = "trustedSecretKey";
	public static final String LOGGING_LEVEL_PARAM = "loggingLevel";
	public static final String USE_CHECKSUM_PARAM = "useChecksum";
	public static final String IS_SYNCHRONIZATION_ENABLED_PARAM = "isSynchronizationEnabled";
	public static final String USE_EXTERNAL_USER_ID_PARAM = "useExternalUserId";
	public static final String USE_EXTERNAL_COURSE_ID_PARAM = "useExternalCourseId";
	public static final String GRADE_BOOK_RESULTS_SELECTION_PARAM = "gradeBookResultsSelection";
	public static final String CAN_STUDENTS_SEE_SCORES_PARAM = "canStudentsSeeScores";
	public static final String CAN_STUDENTS_SEE_REPORT_PARAM = "canStudentsSeeReport";
	public static final String INSTRUCTORS_CAN_TAKE_TEST_PARAM = "canInstructorsTakeTests";
	public static final String CREATE_AUTHORING_RIGHTS_PARAM = "createAuthoringRights";
	public static final String DEFAULT_GRADE_BOOK_RESULT_TYPE_PARAM = "defaultGradeBookResultType";
	public static final String DEFAULT_ASSESSMENT_TARGET_PARAM = "defaultAssessmentTarget";
	public static final String USE_BB_WYSIWYG_PARAM = "use_BbWYSIWYG";
	public static final String USE_ANNOUNCEMENTS_PARAM = "use_announcements";
	
	public static final int FIRST_RESULT = 0;
	public static final int LAST_RESULT = 1;
	public static final int BEST_RESULT = 2;
	public static final int WORST_RESULT = 3;
	public static final int AVERAGE_RESULT = 4;
	
	//added for 4.4	
	public static final int SCORE = 0;
	public static final int PERCENTAGE = 1;
	public static final int NO_RESULT = 2;	

	public static final int NEW_WINDOW = 0;	
	public static final int BARLESS_WINDOW = 1;	
	public static final int CURRENT_FRAME = 2;	

	private String perceptionHost = "http://perception.myuni.edu";
	private String QMWISe_Url = "/QMWISe4/QMWISe.asmx";
	private String Perception_Url = "/q4/perception.dll";
	private String sessionDll = "/q4/session.dll";
	private String clientID = "";
	private String clientPassword = "";
	private String pipSecretKey = "";
	private String trustedSecretKey = "";
	private int loggingLevel = 0;
	private boolean useChecksum = true;
	private boolean isSynchronizationEnabled = true;
	private boolean useExternalUserId = false;
	private boolean useExternalCourseId = false;
	private int gradeBookResultsSelection = LAST_RESULT;
	private boolean canStudentsSeeScores = false;
	private boolean canStudentsSeeReport = false;
	private boolean canInstructorsTakeTests = false;
	private boolean createAuthoringRights = false;
	private int defaultGradeBookResultType = PERCENTAGE;
	private int defaultAssessmentTarget = NEW_WINDOW;
	private boolean useBbWYSIWYG = true;
	private boolean use_announcements = true;
	
	private static Boolean mutex = Boolean.TRUE;

	
	// cache for faster loader, persister usage
	private static PerceptionSettings cachedSettings = null;

	public PerceptionSettings() {
		//TODO
	}

	public String getQMWISeUrl(){
		return QMWISe_Url;
	}
	public String getFullQMWISeUrl(){
		return getFullUrl(getQMWISeUrl());
	}

	public String getPerceptionHost(){
		return perceptionHost;
	}

	public String getPerceptionUrl(){
		return Perception_Url;
	}

	public String getPerceptionFullUrl(){
		return getFullUrl(getPerceptionUrl());
	}

	public String getSessionUrl(){
		return sessionDll;
	}

	public String getSessionFullUrl(){
		return getFullUrl(getSessionUrl());
	}

	public String getClientID(){
		return clientID;
	}
	
	public String getClientPassword(){
		return this.clientPassword;
	}

	public String getPIPSecretKey(){
		return pipSecretKey;
	}

	public String getTrustedSecretKey(){
		return trustedSecretKey;
	}

	public int getLoggingLevel(){
		return loggingLevel;
	}

	public boolean getUseChecksum(){
		return useChecksum;
	}

	public boolean getIsSynchronizationEnabled(){
		return isSynchronizationEnabled;
	}

	public boolean getUseExternalUserId(){
		return useExternalUserId;
	}

	public boolean getUseExternalCourseId(){
		return useExternalCourseId;
	}

	public int getGradeBookResultsSelection(){
		return gradeBookResultsSelection;
	}

	public boolean getCanStudentsSeeScores(){
		return canStudentsSeeScores;
	}

	public boolean getCanStudentsSeeReport(){
		return canStudentsSeeReport;
	}

	public boolean getCanInstructorsTakeTests(){
		return canInstructorsTakeTests;
	}

	public boolean getCreateAuthoringRights(){
		return createAuthoringRights;
	}
	
	public void setQMWISeUrl( String value ){
		if( value != null )
			QMWISe_Url = value;
	}

	public void setPerceptionHost( String value ){
		if( value != null )
			perceptionHost = value;
	}

	public void setPerceptionUrl( String value ){
		if( value != null )
			Perception_Url = value;
	}

	public void setSessionUrl( String value ){
		if( value != null )
			sessionDll = value;
	}

	public void setClientID( String value ){
		if( value != null )
			clientID = value;
	}
	
	public void setClientPassword( String value ){
		if( value != null )
			this.clientPassword = value;
	}

	public void setPIPSecretKey( String value ){
		if( value != null )
			pipSecretKey = value;
	}

	public void setTrustedSecretKey( String value ){
		if( value != null )
			trustedSecretKey = value;
	}

	public void setLoggingLevel( int value ){
		loggingLevel = value;
	}

	public void setUseChecksum( boolean value ){
		useChecksum = value;
	}

	public void setIsSynchronizationEnabled( boolean value ){
		isSynchronizationEnabled = value;
	}

	public void setUseExternalUserId( boolean value ){
		useExternalUserId = value;
	}

	public void setUseExternalCourseId( boolean value ){
		useExternalCourseId = value;
	}

	public void setGradeBookResultsSelection( int value ){
		gradeBookResultsSelection = value;
	}

	public void setCanStudentsSeeScores( boolean value ){
		canStudentsSeeScores = value;
	}

	public void setCanStudentsSeeReport( boolean value ){
		canStudentsSeeReport = value;
	}
	
	public void setCanInstructorsTakeTests( boolean value ){
		canInstructorsTakeTests = value;
	}

	public void setCreateAuthoringRights( boolean value ){
		createAuthoringRights = value;
	}
	
	public int getDefaultGradeBookResultType(){
		return defaultGradeBookResultType;
	}

	public int getDefaultAssessmentTarget(){
		return defaultAssessmentTarget;
	}

	public void setDefaultGradeBookResultType( int value ){
		defaultGradeBookResultType = value;
	}

	public void setDefaultAssessmentTarget( int value ){
		defaultAssessmentTarget = value;
	}
	
	public boolean getUseBbWYSIWYG(){
		return useBbWYSIWYG;
	}

	public void setUseBbWYSIWYG( boolean value ){
		useBbWYSIWYG = value;
	}
	
	public boolean getUseAnnouncements(){
		return use_announcements;
	}

	public void setUseAnnouncements( boolean value ){
		use_announcements = value;
	}

	public static PerceptionSettings load(){
		PerceptionSettings settings = new PerceptionSettings();
		try{

			PortalExtraInfo pei =  getPortalExtraInfo();
			ExtraInfo ei = pei.getExtraInfo();

			settings.setQMWISeUrl( readString( ei, QMWISE_URL_PARAM, settings.getQMWISeUrl() ));
			settings.setPerceptionHost( readString( ei, PERCEPTION_HOST_PARAM, settings.getPerceptionHost() ));
			settings.setPerceptionUrl( readString( ei, PERCEPTION_URL_PARAM, settings.getPerceptionUrl() ));
			settings.setSessionUrl( readString( ei, SESSION_URL_PARAM, settings.getSessionUrl() ));
			settings.setClientID( readString( ei, CLIENT_ID_PARAM, settings.getClientID() ));
			settings.setClientPassword( readString( ei, CLIENT_PASSWORD_PARAM, settings.getClientPassword() ));
			settings.setPIPSecretKey( readString( ei, PIP_SECRET_KEY_PARAM, settings.getPIPSecretKey() ));
			settings.setTrustedSecretKey( readString( ei, TRUSTED_SECRET_KEY_PARAM, settings.getTrustedSecretKey() ));
			settings.setLoggingLevel( readInt( ei, LOGGING_LEVEL_PARAM, settings.getLoggingLevel() ));
			settings.setUseChecksum( readBoolean( ei, USE_CHECKSUM_PARAM, settings.getUseChecksum() ));
			settings.setIsSynchronizationEnabled( readBoolean( ei, IS_SYNCHRONIZATION_ENABLED_PARAM, settings.getIsSynchronizationEnabled() ));
			settings.setUseExternalUserId( readBoolean( ei, USE_EXTERNAL_USER_ID_PARAM, settings.getUseExternalUserId() ));
			settings.setUseExternalCourseId( readBoolean( ei, USE_EXTERNAL_COURSE_ID_PARAM, settings.getUseExternalCourseId() ));
			settings.setGradeBookResultsSelection( readInt( ei, GRADE_BOOK_RESULTS_SELECTION_PARAM, settings.getGradeBookResultsSelection() ));
			settings.setCanStudentsSeeScores( readBoolean( ei, CAN_STUDENTS_SEE_SCORES_PARAM, settings.getCanStudentsSeeScores() ));
			settings.setCanStudentsSeeReport( readBoolean( ei, CAN_STUDENTS_SEE_REPORT_PARAM, settings.getCanStudentsSeeReport() ));
			settings.setCanInstructorsTakeTests( readBoolean( ei, INSTRUCTORS_CAN_TAKE_TEST_PARAM, settings.getCanInstructorsTakeTests() ));
			settings.setCreateAuthoringRights( readBoolean( ei, CREATE_AUTHORING_RIGHTS_PARAM, settings.getCreateAuthoringRights() ));
			settings.setDefaultGradeBookResultType( readInt( ei, DEFAULT_GRADE_BOOK_RESULT_TYPE_PARAM, settings.getDefaultGradeBookResultType() ));
			settings.setDefaultAssessmentTarget( readInt( ei, DEFAULT_ASSESSMENT_TARGET_PARAM, settings.getDefaultAssessmentTarget() ));
			settings.setUseBbWYSIWYG( readBoolean( ei, USE_BB_WYSIWYG_PARAM, settings.getUseBbWYSIWYG() ));
			settings.setUseAnnouncements( readBoolean( ei, USE_ANNOUNCEMENTS_PARAM, settings.getUseAnnouncements() ));

		}catch(Throwable t){

		}
		
		// set to cache
		synchronized( mutex ){
			cachedSettings = settings;
		}

		return settings;
	}

	/**
	 * Loads a cached copy of the settings is available, or forces a new load if null
	 * @return  The settings
	 */
	public static PerceptionSettings loadFromCache(){
		if( cachedSettings == null )
			load();
		return cachedSettings;
	}

	public void persist() throws PersistenceException, ValidationException{
		PortalExtraInfo pei = getPortalExtraInfo();
		ExtraInfo ei = pei.getExtraInfo();
		setString( ei, QMWISE_URL_PARAM, getQMWISeUrl());
		setString( ei, PERCEPTION_HOST_PARAM, getPerceptionHost());
		setString( ei, PERCEPTION_URL_PARAM, getPerceptionUrl());
		setString( ei, SESSION_URL_PARAM, getSessionUrl());
		setString( ei, CLIENT_ID_PARAM, getClientID());
		setString( ei, CLIENT_PASSWORD_PARAM, getClientPassword());
		setString( ei, PIP_SECRET_KEY_PARAM, getPIPSecretKey());
		setString( ei, TRUSTED_SECRET_KEY_PARAM, getTrustedSecretKey());
		setInt( ei, LOGGING_LEVEL_PARAM, getLoggingLevel());
		setBoolean( ei, USE_CHECKSUM_PARAM, getUseChecksum());
		setBoolean( ei, IS_SYNCHRONIZATION_ENABLED_PARAM, getIsSynchronizationEnabled());
		setBoolean( ei, USE_EXTERNAL_USER_ID_PARAM, getUseExternalUserId());
		setBoolean( ei, USE_EXTERNAL_COURSE_ID_PARAM, getUseExternalCourseId());
		setInt( ei, GRADE_BOOK_RESULTS_SELECTION_PARAM, getGradeBookResultsSelection());
		setBoolean( ei, CAN_STUDENTS_SEE_SCORES_PARAM, getCanStudentsSeeScores());
		setBoolean( ei, CAN_STUDENTS_SEE_REPORT_PARAM, getCanStudentsSeeReport());
		setBoolean( ei, INSTRUCTORS_CAN_TAKE_TEST_PARAM, getCanInstructorsTakeTests());
		setBoolean( ei, CREATE_AUTHORING_RIGHTS_PARAM, getCreateAuthoringRights());
		setBoolean( ei, USE_BB_WYSIWYG_PARAM, getUseBbWYSIWYG());
		setBoolean( ei, USE_ANNOUNCEMENTS_PARAM, getUseAnnouncements());
		
		PortalUtil.savePortalExtraInfo( pei );
		synchronized( mutex ){
			oldStyle = false;
			cachedSettings = this;
		}
	}

	private static PortalExtraInfo getPortalExtraInfo() throws PersistenceException, ValidationException{
		return PortalUtil.loadPortalExtraInfo(null, null, "qm/PerceptionBridge/SystemConfig");
	}
	
	protected String getFullUrl( String path ){

		if( isFullUrl(path) )
			return path;
		else if( path != null ){
			
			String fullUrl = new String(perceptionHost + (path.startsWith("/") ? path : "/" + path));
			return fullUrl;
		}else{
			return perceptionHost;
		}
	}
	
	
	protected boolean isFullUrl( String path ){
		if( path != null && (path.toLowerCase().startsWith("http://") || path.toLowerCase().startsWith("https://")) )
			return true;
		return false;
	}



	// non persisted settings
	// but added here so that they could be easily in the future
	
	private boolean isSystemAdmin( Context ctx ){
		if( ctx.getUser().getSystemRole() == User.SystemRole.SYSTEM_ADMIN ){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean hasCourseRole( Context ctx ){
		if( ctx.getCourseMembership() == null || ctx.getCourseMembership().getRole() == CourseMembership.Role.GUEST )
			return false;
		return true;
	}

	private boolean roleCheck( Context ctx, CourseMembership.Role[] roles ){
		if( !hasCourseRole(ctx) ){
			return false;
		}	
		if( roles != null && roles.length > 0 ){
			
			CourseMembership.Role role = ctx.getCourseMembership().getRole();
			
			for( int i=0; i<roles.length; i++ ){
				if( role == roles[i] ){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * @param ctx   the bb context
	 * @return true if the user can create course contents, else
	 */
	public boolean canCreateContent( Context ctx ){ 
		return roleCheck( ctx, new CourseMembership.Role[]{ CourseMembership.Role.INSTRUCTOR, CourseMembership.Role.TEACHING_ASSISTANT} );
	}
	
	

	/**
	 * @param ctx   the bb context
	 * @return true if the user can read student reports
	 */
	public boolean canClearAttempts( Context ctx ){ 
		if( isSystemAdmin(ctx) )
			return true;
		return roleCheck( ctx, new CourseMembership.Role[]{ CourseMembership.Role.INSTRUCTOR, CourseMembership.Role.TEACHING_ASSISTANT} );
	}
	
	public boolean canAdministerCourse( Context ctx ){
		if( isSystemAdmin(ctx) )
			return true;
		return roleCheck( ctx, new CourseMembership.Role[]{ CourseMembership.Role.INSTRUCTOR} );
	}

	public boolean canAddCourseMenuLink( Context ctx ){
		return canAdministerCourse( ctx );
	}

	/**
	 * @param ctx   the bb context
	 * @return true if the user can read student reports
	 */
	public boolean canAccessCoachingReports( Context ctx ){ 
		return roleCheck( ctx, new CourseMembership.Role[]{ CourseMembership.Role.INSTRUCTOR, CourseMembership.Role.TEACHING_ASSISTANT} );
	}
	
	/**
	 * @param ctx   the bb context
	 * @return true if the user can create course contents, else
	 */
	public boolean canRemoveContent( Context ctx ){ 
		if( isSystemAdmin(ctx) )
			return true;
		CourseMembership.Role role = ctx.getCourseMembership().getRole();
		return roleCheck( ctx, new CourseMembership.Role[]{ 	CourseMembership.Role.INSTRUCTOR, 
																CourseMembership.Role.TEACHING_ASSISTANT, 
																CourseMembership.Role.COURSE_BUILDER} );
	}
	
	public boolean canLaunchEnterpriseManager( Context ctx ){
		return canCreateContent( ctx );
	}
}
