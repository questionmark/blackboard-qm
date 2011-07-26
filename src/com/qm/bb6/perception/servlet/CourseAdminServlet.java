/*
 * @(#)CourseAdminServlet.java 1.0.1 Sep 1 2005
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

import blackboard.base.*;
import blackboard.data.course.*;
import blackboard.data.gradebook.*;
import blackboard.data.navigation.*;
import blackboard.data.user.User;
import blackboard.persist.*;
import blackboard.persist.gradebook.*;
import blackboard.persist.navigation.CourseTocDbLoader;
import blackboard.persist.navigation.NavigationItemDbLoader;
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
public class CourseAdminServlet extends AuthenticatedServlet {
	
	public static class PerceptionLineitemFilter extends ListFilter {
		
		private String attemptsUrl = BbUtils.getFullPath("Attempts");
			
		protected boolean passesFilter( Object object ){
			if(object == null || !(object instanceof Lineitem))
				return false;
			else {
				
				// check if attempts url is Perception attempts url
				Lineitem lineItem = (Lineitem) object;
				if( attemptsUrl.equalsIgnoreCase( lineItem.getAttemptHandlerUrl() ) )
					return true;
			}
			return false;
		}
		
	}
	
	public static final String TOC_LABEL = "Questionmark";
	public static final String TOC_HANDLE = "qm-perceptionBridgeCourseStudent-nav-1";
	
	public boolean doContent(HttpServletRequest request, HttpServletResponse response, Context ctx) throws ServletException, IOException{
		
		Locale locale = (Locale) request.getAttribute( LOCALE_PARAM );
		PerceptionSettings perceptionSettings = PerceptionSettings.loadFromCache();
		PerceptionLog log = PerceptionServiceManager.getPerceptionLog();
		
		if( isUpdate(request) ){
			
			if( perceptionSettings.canAdministerCourse( ctx ) ){
				
				// values posted so update config
	
				CourseSettings settings = CourseSettings.load( ctx.getCourse() );
	
				boolean canStudentsSeeReport = settings.getCanStudentsSeeReport();
				boolean canStudentsSeeScores = settings.getCanStudentsSeeScores();
				
				settings.setCanStudentsSeeReport( WebUtils.readBoolean(request.getParameter(CourseSettings.CAN_STUDENTS_SEE_REPORT_PARAM), settings.getCanStudentsSeeReport()));
				settings.setCanStudentsSeeScores( WebUtils.readBoolean(request.getParameter(CourseSettings.CAN_STUDENTS_SEE_SCORES_PARAM), settings.getCanStudentsSeeScores()));
				settings.setGradeBookResultsSelection( WebUtils.readInt(request.getParameter(CourseSettings.GRADEBOOK_RESULTS_PARAM), settings.getGradeBookResultsSelection()));
				try{
					settings.persist();
				}catch(Exception e){
					log.logDebug( "Error saving course settings", e );
					throw new ServletException("Failed to save course configuration details", e);
				}
				
				if( canStudentsSeeScores != settings.getCanStudentsSeeScores() ){
					try{
						
						log.logDebug( "Updating line items, setting availability to: " + settings.getCanStudentsSeeScores() );
						// load all lineitems for this course
						BbList lineItemList = BbUtils.getLineitemDbLoader().loadByCourseId( ctx.getCourseId() );
						LineitemDbPersister persister = BbUtils.getLineitemDbPersister();
						// filter out non perception lineitems
						lineItemList = lineItemList.getFilteredSubList( new PerceptionLineitemFilter() );
						if( lineItemList != null && lineItemList.size() > 0 ){
							log.logDebug( "Found " + lineItemList.size() + " perception lineitems" );						
							for( Iterator iterator = lineItemList.iterator(); iterator.hasNext(); ){
								Lineitem lineItem = (Lineitem) iterator.next();
								lineItem.setIsAvailable( settings.getCanStudentsSeeScores() );
								try{
									BbUtils.getLineitemDbPersister().persist( lineItem );
								}catch( Exception persistError ){
									log.logError( "Unable to save lineitem", persistError );
								}
							}
						}
					}catch(Exception e){
						log.logDebug( "Failed to load line items", e );
						// none found in db
					}
				}

				return sendSuccess(request, response);
			}else{
				return this.sendError(request, response, getSafeSystemString("access-denied.title", locale), getSafeSystemString("access-denied.message", locale));
			}

		}else{
			// send course admin page
			getServletConfig().getServletContext().getRequestDispatcher("/jsp/course_admin.jsp").forward(request, response);
		}
		return true;
	}
			
	protected boolean requiresAdminPrivileges(){
		return false;
	}

	protected boolean requiresControlPanelPrivileges(){
		return true;
	}

	protected boolean requiresCoursePrivileges(){
		return true;
	}

	/**
	 * @param course_id
	 * @return true if the menu item exists or false
	 * @throws blackboard.persist.PersistenceException    error reading from db
	 */
	public static boolean doesCourseMenuItemExist(Id course_id) throws PersistenceException{
		try{
			CourseTocDbLoader loader = CourseTocDbLoader.Default.getInstance();
			BbList courseTocs = loader.loadByCourseId( course_id );
			if( courseTocs != null ){
				Iterator iterator = courseTocs.iterator();
				while( iterator.hasNext() ){
					CourseToc toc = (CourseToc) iterator.next();
					if( toc.getInternalHandle().equals( TOC_HANDLE ) )
						return true;
				}
			}
			return false;
			
		}catch(KeyNotFoundException e){
			// assume not
			return false;
		}
	}
	
	/**
	 * @param course_id
	 * @return true if the menu item exists or false
	 * @throws blackboard.persist.PersistenceException    error reading from db
	 */
	public static boolean isStudentLinkEnabled( Course.ServiceLevel courseType ) throws PersistenceException{
		try{
			NavigationItemDbLoader loader = NavigationItemDbLoader.Default.getInstance();
			NavigationItem navItem = loader.loadByInternalHandle( TOC_HANDLE );
			if( navItem == null )
				return false;
			if( courseType == Course.ServiceLevel.COMMUNITY ){
				return navItem.getIsEnabledMask().getValue( Mask.NAVITEM_ORG );
			}else{
				return navItem.getIsEnabledMask().getValue( Mask.NAVITEM_COURSE );
			}
			
		}catch(KeyNotFoundException e){
			// assume not
			return false;
		}
	}
}
