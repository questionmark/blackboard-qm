/*
 * @(#)FullSynchronisationServlet.java 1.0.1 Sep 1 2005
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
import blackboard.data.user.User;
import blackboard.persist.*;
import blackboard.platform.context.Context;

import com.qm.bb6.perception.config.*;
import com.qm.bb6.perception.context.*;
import com.qm.bb6.perception.data.*;
import com.qm.bb6.perception.util.*;
import com.qm.bb6.perception.security.*;
import com.qm.bb6.perception.service.*;

/**
 * <UL>
 *	<LI>Creates a group in Perception for the course, if one does not exist already</LI>
 *	<LI>Puts the instructor name in Perception associated with the group/course and with a standard profile in Perception, if not there already</LI>
 *	<LI>Creates participant records for each student in the course in Perception if not there already, and associates them with the group</LI>
 *	<LI>Removes participants that are no longer in the Blackboard course from the Perception group</LI>
 * </UL>
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class FullSynchronisationServlet extends SynchronisationServlet {
			
	public boolean doContent(HttpServletRequest request, HttpServletResponse response, Context ctx) throws ServletException, IOException{
		
		Locale locale = (Locale) request.getAttribute( LOCALE_PARAM );
	
		// load system settings
		PerceptionSettings settings = PerceptionSettings.loadFromCache();
		PerceptionLog log = PerceptionServiceManager.getPerceptionLog();
		PrintWriter clientWriter = response.getWriter();
		
		try{
			
			// start page off
			int stepCount = 0;
			int steps = 9;
			if( settings.getCreateAuthoringRights() )
				steps++;
			
			startLoadingPage( request, response, steps );

			if( !settings.canAdministerCourse( ctx ) ){
				throw new PerceptionSecurityException( "Does not have access to run full sync" );
			}
			
			if( !settings.getIsSynchronizationEnabled() ){
				return this.sendSynchronisationDisabled( request, response, locale );			
			}
			
			
			// fetch loaders and persisters
			PerceptionUserLoader userLoader = PerceptionServiceManager.getPerceptionUserLoader();
			PerceptionScheduleLoader scheduleLoader = PerceptionServiceManager.getPerceptionScheduleLoader();
			PerceptionSchedulePersister schedulePersister = PerceptionServiceManager.getPerceptionSchedulePersister();
			PerceptionGroupMembershipPersister membershipPersister = PerceptionServiceManager.getPerceptionGroupMembershipPersister();
			
			// creates group, user and enrols instructor 
			writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("loading-current-context.message", locale));
			PerceptionContext perceptionContext = PerceptionContext.getInstance( ctx );
			
			PerceptionGroup group = perceptionContext.getPerceptionGroup();
			
			// load all users in Bb course
			writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("full-synchronization-process-loading.bbusers", locale));
			BbList courseUsers = BbUtils.getCourseMembershipDbLoader().loadByCourseId( ctx.getCourseId(), null, true ); // heavy load to get users
			
			// filter to get a list of students only
			BbList courseStudents = courseUsers.getFilteredSubList( new CourseMembershipRoleFilter(CourseMembership.Role.STUDENT) );
			
			// filter to get a list of other instructors
			BbList courseInstructors = courseUsers.getFilteredSubList( new CourseMembershipRoleFilter(CourseMembership.Role.INSTRUCTOR) );
	
			// filter to get a list of other instructors
			BbList courseTAs = courseUsers.getFilteredSubList( new CourseMembershipRoleFilter(CourseMembership.Role.TEACHING_ASSISTANT) );
	
			// load group participants - smaller than load all users and will be much faster with subsequent runs.
			writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("full-synchronization-process-loading.participants", locale));
			List groupParticipants = userLoader.getParticipantsByGroupId( group.getGroupId() );
	
			// load group administrators - might need to remove an administrator
			writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("full-synchronization-process-loading.administrators", locale));
			List groupAdministrators = userLoader.getAdministratorsByGroupId( group.getGroupId() );
			
			// PARTICIPANT SYNC
			
			List groupParticipantsToRemove;
			BbList usersToCheck;
			if( groupParticipants != null && groupParticipants.size() > 0 ){
				usersToCheck = courseStudents.getFilteredSubList( new ParticipantsNotEnrolledFilter(groupParticipants, settings.getUseExternalUserId()));
				groupParticipantsToRemove = filterGroupParticipantsToRemove( groupParticipants, courseStudents, settings.getUseExternalUserId() );
			}else{
				usersToCheck = courseStudents;
				groupParticipantsToRemove = null;
			}
			
			// send debug info
			StringBuffer logBuffer = new StringBuffer();
			logBuffer.append( "Starting course synchronisation.....\n" );
			logBuffer.append( "\t" + "Course Id: " + ctx.getCourse().getCourseId() + "\n" );
			logBuffer.append( "\t" + "Users: " + courseUsers.size() + "\n" );
			logBuffer.append( "\t" + "Students: " + courseStudents.size() + "\n" );
			logBuffer.append( "\t" + "Instructors: " + courseInstructors.size() + "\n" );
			logBuffer.append( "\t" + "TAs: " + courseTAs.size() + "\n" );
			logBuffer.append( "\t" + "Participants: " + groupParticipants.size() + "\n" );		
			logBuffer.append( "\t" + "Administrator: " + groupAdministrators.size() + "\n" );			
			log.logDebug( logBuffer.toString() );
			
			// declare list for new group memberships to add later
			List newGroupMemberships = new ArrayList();
			
			// check if participant exists, if not then create them
			writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("full-synchronization-process-loading.particpantschk", locale));
			String newUserStr = getSafeCourseString("full-synchronization-process-loading.addparticpant", locale);
			for( Iterator i = usersToCheck.iterator(); i.hasNext(); ){
				CourseMembership cm = (CourseMembership) i.next();
				User user = cm.getUser();
				PerceptionUser participant;
				try{
					if( settings.getUseExternalUserId() )
						participant = userLoader.getParticipantByName( user.getBatchUid() );
					else
						participant = userLoader.getParticipantByName( user.getUserName() );
				}catch( ObjectNotFoundException e ){
					// particpant not found, so create an new one
					log.logDebug( "Creating participant account for: " + user.getUserName() );
					writeScriptUpdate( clientWriter, stepCount, newUserStr + " " + user.getUserName() );
					participant = PerceptionContext.createUser( user, cm.getRole(), settings.getUseExternalUserId(), false );
				}
				// create new enrolment record and add to list for saving later
				PerceptionGroupMembership groupMembership = new PerceptionGroupMembership();
				groupMembership.setGroupId( group.getGroupId() );
				groupMembership.setUserId( participant.getUserId() );
				groupMembership.setIsAdmin( false);
				newGroupMemberships.add( groupMembership );
			}
			
			// save new group participants
			if( newGroupMemberships.size() > 0 ){
				log.logDebug( "Adding " + newGroupMemberships.size() + " users to group" );
				writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("full-synchronization-process-loading.addmembers", locale));
				membershipPersister.addParticipants( newGroupMemberships );
			}else{
				++stepCount;
			}
			
			// remove participants no longer on the course
			if( groupParticipantsToRemove != null && groupParticipantsToRemove.size() > 0 ){
				log.logDebug( "Removing " + groupParticipantsToRemove.size() + " users from group" );
				writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("full-synchronization-process-loading.removemembers", locale));
				List groupMembershipsToRemove = getGroupMembershipsToRemove( groupParticipantsToRemove, group.getGroupId(), false );
				membershipPersister.deleteParticipants( groupMembershipsToRemove );
				// TODO: do we also want to remove the participant from Perception if no longer a bb user?
				
				++stepCount;
				String removeSchedulesForTxt = getSafeCourseString("full-synchronization-process-loading.removeschedules", locale);
				
				// remove schedules for removed participants
				for( Iterator participantIterator = groupParticipantsToRemove.iterator(); participantIterator.hasNext(); ){
					
					PerceptionParticipant participant = (PerceptionParticipant) participantIterator.next();
					List participantScheduleList = scheduleLoader.getSchedulesByParticipantId( participant.getUserId() );
					if( participantScheduleList != null && participantScheduleList.size() > 0 ){
						writeScriptUpdate( clientWriter, stepCount, removeSchedulesForTxt + " " + participant.getUserName() );
						for( Iterator scheduleIterator = participantScheduleList.iterator(); scheduleIterator.hasNext(); ){
							try{
								PerceptionSchedule schedule = (PerceptionSchedule) scheduleIterator.next();
								if( schedule.isParticipantSchedule() && schedule.getGroupId().equalsIgnoreCase( group.getGroupId() ) ){
									// redundant schedule
									schedulePersister.deleteByScheduleId( schedule.getScheduleId() );
								}
							}catch(Exception e){
								log.logError( "Failed to remove redundant schedule, non fatal", e );
							}
						}
					}
				}
			

			}else{
				++stepCount;
				++stepCount;
			}
					
			// ADMINISTRATOR SYNC
			
			// find admins to remove (no need to add)
			List groupAdministratorsToRemove;
			if( groupAdministrators != null && groupAdministrators.size() > 0 ){
				groupAdministratorsToRemove = filterGroupAdministratorsToRemove( groupAdministrators, courseInstructors, settings.getUseExternalUserId() );
				groupAdministratorsToRemove = filterGroupAdministratorsToRemove( groupAdministratorsToRemove, courseTAs, settings.getUseExternalUserId() );
			}else{
				groupAdministratorsToRemove = null;
			}
			
			// remove admins no longer on the course
			if( groupAdministratorsToRemove != null && groupAdministratorsToRemove.size() > 0 ){
				log.logDebug( "Removing " + groupAdministratorsToRemove.size() + " admins from group" );
				writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("full-synchronization-process-loading.removeadmins", locale));
				List groupMembershipsToRemove = getGroupMembershipsToRemove( groupAdministratorsToRemove, group.getGroupId(), true );
				membershipPersister.deleteAdministrators( groupMembershipsToRemove );
				// TODO: do we also want to remove the admin from Perception if no longer a bb user? (NB: no QMWISe function for this)

				// added Feb, 2007
				// authoring rights 
				if( settings.getCreateAuthoringRights() ){
					
					writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("full-synchronization-process-loading.removeauthoringrights", locale));
					// load course settings
					// NOTE: course settings used to store topic ID and folder ID
					// this is VERY BAD, but methods don't exist in Perception
					// please alter later
					CourseSettings courseSettings = CourseSettings.load( ctx.getCourse() );
					if( !BbUtils.isNullOrEmpty(courseSettings.getTopicID()) ){
						for( Iterator i = groupAdministratorsToRemove.iterator(); i.hasNext(); ){
							PerceptionAdministrator administrator = (PerceptionAdministrator) i.next();
							try{
								PerceptionContext.deleteTopicMembership(courseSettings.getTopicID(), administrator );
							}catch( PerceptionDataException pde ){
								log.logDebug( "Failed to remove topic membership", pde );
							}
						}
					}
					if( !BbUtils.isNullOrEmpty(courseSettings.getFolderID()) ){
						for( Iterator i = groupAdministratorsToRemove.iterator(); i.hasNext(); ){
							PerceptionAdministrator administrator = (PerceptionAdministrator) i.next();
							try{
								PerceptionContext.deleteFolderMembership(courseSettings.getFolderID(), administrator );
							}catch( PerceptionDataException pde ){
								log.logDebug( "Failed to remove folder membership", pde );
							}
						}
					}
				}
					
			}else{
				++stepCount;
				if( settings.getCreateAuthoringRights() ){
					++stepCount;
				}
			}
				
			
			// NO NEED TO CREATE SCHEDULES, THIS IS DONE BY FAST SYNC
			
			writeComplete( clientWriter, getSafeCourseString("full-synchronization-process-complete.title", locale), getSafeCourseString("full-synchronization-process-complete.message", locale) );
		
		}catch( PersistenceException e ){
			log.logError( "Failed to sync course: " + ctx.getCourseId(), e );
			writeErrorUpdate( clientWriter, getSafeSystemString("error.title", locale), getSafeSystemString("error.message", locale) );
		//	return sendDefaultError(request, response);
		}catch( PerceptionNotFoundException e ){
			writeErrorUpdate( clientWriter, getSafeCourseString("perception-not-found.title", locale), getSafeCourseString("perception-not-found.message", locale));
		}catch( PerceptionSecurityException e ){
			writeErrorUpdate( clientWriter, getSafeSystemString("access-denied.title", locale), getSafeSystemString("access-denied.message", locale));
		}catch( PerceptionDataException e ){
			log.logError( "Failed to sync course: " + ctx.getCourseId(), e );
		//	return sendPerceptionDataException(request, response, e, null);
			ResourceBundle bundle = PerceptionServiceManager.getErrorCodeResourceBundle( locale ); // use Locale later
			
			String title, message;
			try{
				title = bundle.getString( "error.title" );
				message = bundle.getString( String.valueOf(e.getCode()) );
			}catch( MissingResourceException bknfe ){
				title = "Error";
				message = e.getMessage();
			}
			writeErrorUpdate( clientWriter, title, message );

		}

		// start page off
		endLoadingPage( request, response );
		
		return true; // sendSuccess( request, response );
	}
			
	protected boolean requiresAdminPrivileges(){
		return false;
	}

	protected boolean requiresCoursePrivileges(){
		return true;
	}

	protected boolean requiresControlPanelPrivileges(){
		return true;
	}

	private List filterGroupParticipantsToRemove( List groupParticipants, BbList courseStudents, boolean useExternal ){
		
		List groupParticipantsToRemove = new ArrayList();
		for( Iterator participantIterator = groupParticipants.iterator(); participantIterator.hasNext(); ){
			PerceptionUser participant = (PerceptionUser) participantIterator.next();
			boolean containedInSet = false;
			for( Iterator studentIterator = courseStudents.iterator(); studentIterator.hasNext(); ){
				CourseMembership cm = (CourseMembership) studentIterator.next();
				User bbUser = cm.getUser();
				if( useExternal && participant.getUserName().equalsIgnoreCase( bbUser.getBatchUid() ) ){
					containedInSet = true;
					break;
				}else if( (!useExternal) && participant.getUserName().equalsIgnoreCase( bbUser.getUserName() ) ){
					containedInSet = true;
					break;
				}
			}
			if( !containedInSet ){
				groupParticipantsToRemove.add( participant );
			}		
		}
		return groupParticipantsToRemove;
	}

	private List filterGroupAdministratorsToRemove( List groupAdministrators, BbList courseInstructors, boolean useExternal ){
		
		List groupAdministratorsToRemove = new ArrayList();
		for( Iterator administratorIterator = groupAdministrators.iterator(); administratorIterator.hasNext(); ){
			PerceptionUser administrator = (PerceptionUser) administratorIterator.next();
			boolean containedInSet = false;
			for( Iterator teacherIterator = courseInstructors.iterator(); teacherIterator.hasNext(); ){
				CourseMembership cm = (CourseMembership) teacherIterator.next();
				User bbUser = cm.getUser();
				if( useExternal && administrator.getUserName().equalsIgnoreCase( bbUser.getBatchUid() ) ){
					containedInSet = true;
					break;
				}else if( (!useExternal) && administrator.getUserName().equalsIgnoreCase( bbUser.getUserName() ) ){
					containedInSet = true;
					break;
				}
			}
			if( !containedInSet ){
				groupAdministratorsToRemove.add( administrator );
			}		
		}
		return groupAdministratorsToRemove;
	}
	
	private List getGroupMembershipsToRemove( List groupParticipantsToRemove, String group_id, boolean isAdmin ){
		
		List groupMembershipsToRemove = new ArrayList();
		for( Iterator participantIterator = groupParticipantsToRemove.iterator(); participantIterator.hasNext(); ){
			PerceptionUser user = (PerceptionUser) participantIterator.next();
			PerceptionGroupMembership groupMembership = new PerceptionGroupMembership();
			groupMembership.setGroupId( group_id );
			groupMembership.setIsAdmin( isAdmin );
			groupMembership.setUserId( user.getUserId() );
			groupMembershipsToRemove.add( groupMembership );
	
		}
		return groupMembershipsToRemove;
		
	}

	private List filterSchedulesToRemove( List groupAdministrators, BbList courseInstructors, boolean useExternal ){
		
		List groupAdministratorsToRemove = new ArrayList();
		for( Iterator administratorIterator = groupAdministrators.iterator(); administratorIterator.hasNext(); ){
			PerceptionUser administrator = (PerceptionUser) administratorIterator.next();
			boolean containedInSet = false;
			for( Iterator teacherIterator = courseInstructors.iterator(); teacherIterator.hasNext(); ){
				CourseMembership cm = (CourseMembership) teacherIterator.next();
				User bbUser = cm.getUser();
				if( useExternal && administrator.getUserName().equalsIgnoreCase( bbUser.getBatchUid() ) ){
					containedInSet = true;
					break;
				}else if( (!useExternal) && administrator.getUserName().equalsIgnoreCase( bbUser.getUserName() ) ){
					containedInSet = true;
					break;
				}
			}
			if( !containedInSet ){
				groupAdministratorsToRemove.add( administrator );
			}		
		}
		return groupAdministratorsToRemove;
	}
	

}
