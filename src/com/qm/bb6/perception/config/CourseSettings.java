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
public class CourseSettings extends ConfigSettings {
	
	public static final String GRADEBOOK_RESULTS_PARAM 					= "gradeBookResultsSelection";
	public static final String CAN_STUDENTS_SEE_SCORES_PARAM 			= "canStudentsSeeScores";
	public static final String CAN_STUDENTS_SEE_REPORT_PARAM 			= "canStudentsSeeReport";
	public static final String TOPIC_ID_PARAM							= "topicId";
	public static final String FOLDER_ID_PARAM							= "folderId";
	public static final String TOPIC_PARENT_ID_PARAM					= "topicParentId";
	public static final String FOLDER_PARENT_ID_PARAM					= "folderParentId";
	

	private int gradeBookResultsSelection = PerceptionSettings.LAST_RESULT;
	private boolean canStudentsSeeScores = true;
	private boolean canStudentsSeeReport = false;
	private String folderId = null;
	private String topicId = null;
	private String topicParentId = null;
	private String folderParentId = null;
	
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

	// added 4.4
	public String getTopicParentID(){
		return topicParentId;
	}
	
	public String getFolderParentID(){
		return folderParentId;
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

	// added 4.4
	public void setTopicParentID(String value){
		this.topicParentId = value;
	}
	
	public void setFolderParentID(String value){
		this.folderParentId = value;
	}
	
	// added 4.3
	public void setAssessmentAttemptType( String assessment_id, int type ){
		if( !BbUtils.isNullOrEmpty(assessment_id) )
			attemptTypes.put( ATTEMPT_TYPE_SS + assessment_id, new Integer(type) );
	}

	
	public static CourseSettings load(Course course){
		CourseSettings settings = new CourseSettings();
		try{
			
			PortalExtraInfo pei =  getPortalExtraInfo(course);
			ExtraInfo ei = pei.getExtraInfo();
			settings.setCanStudentsSeeScores( readBoolean( ei, CAN_STUDENTS_SEE_SCORES_PARAM, settings.getCanStudentsSeeScores() ));
			settings.setCanStudentsSeeReport( readBoolean( ei, CAN_STUDENTS_SEE_REPORT_PARAM, settings.getCanStudentsSeeReport() ));
			settings.setGradeBookResultsSelection( readInt( ei, GRADEBOOK_RESULTS_PARAM, settings.getGradeBookResultsSelection() ));
			settings.setTopicID( readString( ei, TOPIC_ID_PARAM, settings.getTopicID() ));
			settings.setFolderID( readString( ei, FOLDER_ID_PARAM, settings.getFolderID() ));
			settings.setTopicParentID( readString( ei, TOPIC_PARENT_ID_PARAM, settings.getTopicParentID() ));
			settings.setFolderParentID( readString( ei, FOLDER_PARENT_ID_PARAM, settings.getFolderParentID() ));
			settings.course = course;
			
			// added for 4.3 
			// get getKeys method and find all content settings matches for
			// this course. Note: these match the Perception assessment, NOT a bb content_id
			Method getKeysMethod = ExtraInfo.class.getMethod("getKeys", new Class[]{});
			Object keys = getKeysMethod.invoke( ei, new Object[]{} );
				
			if( keys instanceof Enumeration ){
				for(Enumeration e = (Enumeration) keys; e.hasMoreElements();){
					String name = (String) e.nextElement();
					int o =  readInt(ei, name, -1);
					if( 0 > -1 && name.startsWith(ATTEMPT_TYPE_SS) )
						settings.attemptTypes.put( name, new Integer(o) );
				}
			}else{
				for(Iterator iterator = ((Set) keys).iterator(); iterator.hasNext();){
					String name = (String) iterator.next();			
					int o =  readInt(ei, name, -1);
					if( 0 > -1 && name.startsWith(ATTEMPT_TYPE_SS) )
						settings.attemptTypes.put( name, new Integer(o) );
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
		setBoolean( ei, CAN_STUDENTS_SEE_SCORES_PARAM, getCanStudentsSeeScores());
		setBoolean( ei, CAN_STUDENTS_SEE_REPORT_PARAM, getCanStudentsSeeReport());
		setInt( ei, GRADEBOOK_RESULTS_PARAM, getGradeBookResultsSelection());
		setString( ei, TOPIC_ID_PARAM, getTopicID() );
		setString( ei, FOLDER_ID_PARAM, getFolderID() );
		setString( ei, TOPIC_PARENT_ID_PARAM, getTopicParentID() );
		setString( ei, FOLDER_PARENT_ID_PARAM, getFolderParentID() );
		//pei.persist();
		
		// added for 4.3
		// save all the content settings that can't be stroed in the Content object
		for(Enumeration e = (Enumeration) attemptTypes.keys(); e.hasMoreElements();){
			String name = (String) e.nextElement();
			setInt( ei,  name, ((Integer) attemptTypes.get(name)).intValue() );
		}
			
		PortalUtil.savePortalExtraInfo( pei );
	}	

	protected static PortalExtraInfo getPortalExtraInfo(Course course) throws PersistenceException, ValidationException{
		return PortalUtil.loadPortalExtraInfo(null, null, "qm/PerceptionBridge/CourseSettings:" + course.getId().toExternalString());
	}
	
	

}