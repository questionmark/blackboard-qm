/*
 * @(#)CourseSettings.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.config;

import blackboard.base.*;
import blackboard.data.ValidationException;
import blackboard.data.course.*;
import blackboard.data.user.User;
import blackboard.persist.*;
import blackboard.platform.plugin.*;
import blackboard.portal.data.*;
import blackboard.portal.servlet.PortalUtil;

import java.lang.reflect.Method;
import java.util.*;

import com.qm.bb6.perception.util.BbUtils;


/**
 * Settings holder for all general course settings
 * set by the course instructor
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class CourseSettings {
	
	public static final String GRADEBOOK_RESULTS_PARAM 					= "gradeBookResultsSelection";
	public static final String CAN_STUDENTS_SEE_SCORES_PARAM 			= "canStudentsSeeScores";
	public static final String CAN_STUDENTS_SEE_REPORT_PARAM 			= "canStudentsSeeReport";
	public static final String TOPIC_ID_PARAM							= "topicId";
	public static final String FOLDER_ID_PARAM							= "folderId";
	

	private int gradeBookResultsSelection = PerceptionSettings.LAST_RESULT;
	private boolean canStudentsSeeScores = true;
	private boolean canStudentsSeeReport = false;
	private String folderId = null;
	private String topicId = null;
	
	// 4.3
	private Hashtable attemptTypes = new Hashtable();
	private static String ATTEMPT_TYPE_SS = "ATTEMPT_TYPE_SS:";
	
	private Course course;
	
	private CourseSettings(){
		/// try to load defaults
		try{
			PerceptionSettings systemSettings = PerceptionSettings.loadFromCache();
			setGradeBookResultsSelection( systemSettings.getGradeBookResultsSelection() );
			setCanStudentsSeeReport( systemSettings.getCanStudentsSeeReport() );
			setCanStudentsSeeScores( systemSettings.getCanStudentsSeeScores() );
			
		}catch(Throwable t2){
			// ignore
		}

	}

	public boolean getCanStudentsSeeScores(){
		return canStudentsSeeScores;
	}

	public boolean getCanStudentsSeeReport(){
		return canStudentsSeeReport;
	}

	public int getGradeBookResultsSelection(){
		return gradeBookResultsSelection;
	}
	
	// added Feb, 2007
	public String getTopicID(){
		return topicId;
	}
	
	public String getFolderID(){
		return folderId;
	}
	
	// added 4.3
	public int getAssessmentAttemptType( String assessment_id ){
		try{
			Integer integer = (Integer) attemptTypes.get( ATTEMPT_TYPE_SS + assessment_id );
			return integer.intValue();
		}catch(Exception e){
			return getGradeBookResultsSelection(); // default
		}
	}
	

	public void setCanStudentsSeeScores(boolean b){
		this.canStudentsSeeScores = b;
	}

	public void setCanStudentsSeeReport(boolean b){
		this.canStudentsSeeReport = b;
	}

	public void setGradeBookResultsSelection(int i){
		this.gradeBookResultsSelection = i;
	}
	
	// added Feb, 2007
	public void setTopicID(String value){
		this.topicId = value;
	}
	
	public void setFolderID(String value){
		this.folderId = value;
	}
	
	// added 4.3
	public void setAssessmentAttemptType( String assessment_id, int type ){
		if( !BbUtils.isNullOrEmpty(assessment_id) )
			attemptTypes.put( ATTEMPT_TYPE_SS + assessment_id, new Integer(type) );
	}

	private static boolean getBoolean(Object obj, boolean defaultValue){
		try{
			return ((Boolean) obj).booleanValue();
		}catch(Exception e){
		//	e.printStackTrace();
			return defaultValue;
		}
	}

	private static int getInt(Object obj, int defaultValue){
		try{
			return ((Integer) obj).intValue();
		}catch(Exception e){
		//	e.printStackTrace();
			return defaultValue;
		}
	}
	
	private static String getString(Object obj, String defaultValue){
		try{
			String value = (String) obj;
			if( BbUtils.isNullOrEmpty(value) )
				return null;
			return value;
		}catch(Exception e){
		//	e.printStackTrace();
			return defaultValue;
		}
	}
	
	public static CourseSettings load(Course course){
		CourseSettings settings = new CourseSettings();
		try{
			
			PortalExtraInfo pei =  getPortalExtraInfo(course);
			ExtraInfo ei = pei.getExtraInfo();
			settings.setCanStudentsSeeScores( getBoolean( ei.getObjectValue(CAN_STUDENTS_SEE_SCORES_PARAM), settings.getCanStudentsSeeScores() ));
			settings.setCanStudentsSeeReport( getBoolean( ei.getObjectValue(CAN_STUDENTS_SEE_REPORT_PARAM), settings.getCanStudentsSeeReport() ));
			settings.setGradeBookResultsSelection( getInt( ei.getObjectValue(GRADEBOOK_RESULTS_PARAM), settings.getGradeBookResultsSelection() ));
			settings.setTopicID( getString( ei.getObjectValue(TOPIC_ID_PARAM), settings.getTopicID() ));
			settings.setFolderID( getString( ei.getObjectValue(FOLDER_ID_PARAM), settings.getFolderID() ));
			settings.course = course;
			
			// added for 4.3 
			// get getKeys method and find all content settings matches for
			// this course. Note: these match the Perception assessment, NOT a bb content_id
			Method getKeysMethod = ExtraInfo.class.getMethod("getKeys", new Class[]{});
			Object keys = getKeysMethod.invoke( ei, new Object[]{} );
				
			if( keys instanceof Enumeration ){
				for(Enumeration e = (Enumeration) keys; e.hasMoreElements();){
					String name = (String) e.nextElement();
					Object o =  ei.getObjectValue(name);
					if( name.startsWith(ATTEMPT_TYPE_SS) )
						settings.attemptTypes.put( name, o );
				}
			}else{
				for(Iterator iterator = ((Set) keys).iterator(); iterator.hasNext();){
					String name = (String) iterator.next();
					Object o =  ei.getObjectValue(name);
					if( name.startsWith(ATTEMPT_TYPE_SS) )
						settings.attemptTypes.put( name, o );
				}
			}		
			
			

		}catch(Throwable t){
			t.printStackTrace();
		}
		
		return settings;
	}
		
	public void persist() throws PersistenceException, ValidationException{
		PortalExtraInfo pei = getPortalExtraInfo(course);
		ExtraInfo ei = pei.getExtraInfo();
		ei.setObjectValue(CAN_STUDENTS_SEE_SCORES_PARAM, new Boolean(getCanStudentsSeeScores()));
		ei.setObjectValue(CAN_STUDENTS_SEE_REPORT_PARAM, new Boolean(getCanStudentsSeeReport()));
		ei.setObjectValue(GRADEBOOK_RESULTS_PARAM, new Integer(getGradeBookResultsSelection()));
		setString( ei, TOPIC_ID_PARAM, getTopicID() );
		setString( ei, FOLDER_ID_PARAM, getFolderID() );
		//pei.persist();
		
		// added for 4.3
		// save all the content settings that can't be stroed in the Content object
		for(Enumeration e = (Enumeration) attemptTypes.keys(); e.hasMoreElements();){
			String name = (String) e.nextElement();
			ei.setObjectValue( name, attemptTypes.get(name) );
		}
			
		PortalUtil.savePortalExtraInfo( pei );
	}	

	protected static PortalExtraInfo getPortalExtraInfo(Course course) throws PersistenceException, ValidationException{
		return PortalUtil.loadPortalExtraInfo(null, null, "qm/PerceptionBridge/CourseSettings:" + course.getId().toExternalString());
	}
	
	private void setString( ExtraInfo ei, String param, String value ){
		String value2write;
		if( value == null ){
			value2write = "";
		}else{
			value2write = new String(value);
		}
		ei.setObjectValue(param, value2write);
	}

}