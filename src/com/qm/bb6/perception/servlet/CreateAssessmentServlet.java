/*
 * @(#)CreateAssessmentServlet.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.servlet;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import blackboard.base.*;
import blackboard.data.content.Content;
import blackboard.data.course.*;
import blackboard.data.gradebook.*;
import blackboard.data.user.User;
import blackboard.data.ValidationException;
import blackboard.persist.*;
import blackboard.persist.navigation.CourseTocDbLoader;
import blackboard.platform.context.Context;

import com.qm.bb6.perception.config.*;
import com.qm.bb6.perception.config.impl.SimpleContentSettings;
import com.qm.bb6.perception.context.*;
import com.qm.bb6.perception.data.*;
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
public class CreateAssessmentServlet extends SynchronisationServlet {
	
	public static final String PERCEPTION_CONTENT_HANDLER = "qm/assessment-link";
	public static final String PERCEPTION_ID_PARAM = "perception_id";
	public static final String BODY_TYPE_PARAM = "bodyType";
	public static final String COMMENTS_PARAM = "comments";
	public static final String AVAILABLE_PARAM = "available";
	public static final String ALLOW_MULTIPLE_PARAM = "allowMultipleAttempts";
	public static final String ATTEMPTS_PARAM = "attemptsAllowed";
	public static final String START_DATE_PARAM = "contentStart";
	public static final String END_DATE_PARAM = "contentEnd";

 	public static final String HTML_TYPE = "HTML";
 	public static final String PLAIN_TYPE = "PLAIN";
	public static final String SMART_TYPE = "SMART";
	
	public static final String PERCEPTION_CONTENT_PARAM = "perceptionContext";
 	public static final String CONTENT_SETTINGS_PARAM = "contentSettings";
	public static final String PERCEPTION_ASSESSMENT_PARAM = "perceptionAssessment";
	
	// added for 4.3
	public static final String CREATE_GRADEBOOK_COLUMN_PARAM = "createGradebookColumn";
	public static final String ATTEMPT_TYPE_PARAM = "attemptType";
	public static final String UPDATE_EXISTING_ATTEMPTS_PARAM = "updateExistingAttempts";
	public static final String GRADEBOOK_COLUMN_CREATED_PARAM = "gradeBookColumnCreated";
	

			
	public boolean doContent(HttpServletRequest request, HttpServletResponse response, Context ctx) throws ServletException, IOException{
		
		Locale locale = (Locale) request.getAttribute( LOCALE_PARAM );
		PerceptionLog log = PerceptionServiceManager.getPerceptionLog();
		PrintWriter clientWriter = response.getWriter();
		PerceptionSettings settings = PerceptionSettings.loadFromCache();

		if( settings.canCreateContent( ctx ) ){
			
			// load perception context - errors sent back normally
			PerceptionContext perceptionContext;
			try{
			
				if( !isAssessmentSelected(request, ctx) ){
					

					perceptionContext = PerceptionContext.getInstance( ctx );

					// load Perception content settings
					ContentSettings contentSettings = SimpleContentSettings.load( new Content() );
					request.setAttribute( CONTENT_SETTINGS_PARAM, contentSettings);
						
					// send add assessment page
					request.setAttribute( PERCEPTION_CONTENT_PARAM, perceptionContext );

					// new content, so we need to select the assessment first				
					getServletConfig().getServletContext().getRequestDispatcher("/jsp/select_assessment.jsp").forward(request, response);

				}else if( !isUpdate(request) ){
					
					perceptionContext = PerceptionContext.getInstance( ctx );

					Content content = ctx.getContent();
					ContentSettings contentSettings;
					PerceptionAssessment assessment;
					
					if( content != null && (!content.getIsFolder()) ){
						
						// load Perception content settings
						contentSettings = SimpleContentSettings.load( content );
						request.setAttribute( CONTENT_SETTINGS_PARAM, contentSettings);
						// check assessment exists, if edit
						// if assessment doesn't exist then throw an error. User can delete content item or leave if it's a 
						// temp. Perception error
						if( contentSettings.getAssessmentId() != null ){
							// load assessment from Perception
							try{
								// get loader
								PerceptionAssessmentLoader assessmentLoader = PerceptionServiceManager.getPerceptionAssessmentLoader();
								assessment = assessmentLoader.getAssessmentById( contentSettings.getAssessmentId() );
								request.setAttribute( PERCEPTION_ASSESSMENT_PARAM, assessment );							
							}catch(ObjectNotFoundException onfe){
								return this.sendError(request, response, getSafeCourseString("assessment-not-found.title", locale), 
																					getSafeCourseString("assessment-not-found.message", locale));
							}
						}else{
							return this.sendError(request, response, getSafeCourseString("assessment-not-found.title", locale), 
																				getSafeCourseString("assessment-not-found.message", locale));
							
						}
					}else{
						// load Perception content settings
						contentSettings = SimpleContentSettings.load( new Content() );
						request.setAttribute( CONTENT_SETTINGS_PARAM, contentSettings);
						
						//load selected assessment
						String perception_id = request.getParameter( PERCEPTION_ID_PARAM );
						assessment = PerceptionServiceManager.getPerceptionAssessmentLoader().getAssessmentById( perception_id );
						request.setAttribute( PERCEPTION_ASSESSMENT_PARAM, assessment );
						
					}			
					
					// added for 4.3
					// check to see if a lineItem has already been created
					Lineitem lineItem = AddGradeBookEntryServlet.getLineitemByCourseNameAndId(log, ctx.getCourseId(), assessment.getSessionName(), assessment.getAssessmentId());
					if( lineItem == null )
						request.setAttribute( GRADEBOOK_COLUMN_CREATED_PARAM, Boolean.FALSE );
					else
						request.setAttribute( GRADEBOOK_COLUMN_CREATED_PARAM, Boolean.TRUE );
					
					// send add assessment page
					request.setAttribute( PERCEPTION_CONTENT_PARAM, perceptionContext );

					getServletConfig().getServletContext().getRequestDispatcher("/jsp/add_assessment.jsp").forward(request, response);

				}else{
		
					// sync page - errors send back as Javascript
					try{
					
						// start page off
						int stepCount = 0;
						startLoadingPage( request, response, 11 );
						
						writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("loading-current-context.message", locale));

						Content content = ctx.getContent();
						Content parent = null;
						Course course = ctx.getCourse();
						Lineitem lineItem = null;
						boolean isNew = true;
						
						if( content.getIsFolder() ){ // folder not a content item
							parent = content;
							content = null;
						}else{
							// no need to load parent
						}


						perceptionContext = PerceptionContext.getInstance( ctx );

						// fetch loaders and persisters
						PerceptionUserLoader userLoader = PerceptionServiceManager.getPerceptionUserLoader();
						PerceptionGroupMembershipPersister membershipPersister = PerceptionServiceManager.getPerceptionGroupMembershipPersister();
				
	
						PerceptionAssessment assessment;
						writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("create-assessment-process-loading.context", locale));
						String perception_id = request.getParameter( PERCEPTION_ID_PARAM );
						assessment = PerceptionServiceManager.getPerceptionAssessmentLoader().getAssessmentById( perception_id );
					
						// current group
						PerceptionGroup group = perceptionContext.getPerceptionGroup();
					
						
		
						if( content == null ){
							// create new content item
							isNew = true;
							content = new Content();
							content.setCourseId( ctx.getCourseId() );
							content.setParentId( parent.getId() );
							content.setPosition( getPosition(parent.getId()) );
						}else{
							isNew = false; // edit
						}
			
						// load existing or default
						// note: MUST be loaded before setting the content body
						ContentSettings contentSettings = SimpleContentSettings.load( content );
						
						// added for 4.3
						CourseSettings courseSettings = CourseSettings.load( course );
						BbList courseUsers = null; // moved
						BbList courseStudents = null; // moved
						
						String comments = request.getParameter( COMMENTS_PARAM );
						boolean allowMultipleAttempts = WebUtils.readBoolean( request.getParameter( ALLOW_MULTIPLE_PARAM ), true); // ALLOW_MILTIPLE should be HAS_LIMITED_ATTEMPTS
						int attemptsAllowed;
						if( allowMultipleAttempts ){
							attemptsAllowed = WebUtils.readInt( request.getParameter( ATTEMPTS_PARAM ), 1);
							if( attemptsAllowed < 1 || attemptsAllowed > 100 )
								attemptsAllowed = 1;
						}else{
							attemptsAllowed = 0;
						}
							
						content.setAllowGuests( false );
						content.setAllowObservers( false );
						assessment.setAttemptsAllowed( attemptsAllowed );
						FormattedText.Type bodyType;
						if( HTML_TYPE.equalsIgnoreCase( request.getParameter(BODY_TYPE_PARAM) ) ){
							bodyType = FormattedText.Type.HTML;
						}else if( SMART_TYPE.equalsIgnoreCase( request.getParameter(BODY_TYPE_PARAM)  ) ){
							bodyType = FormattedText.Type.SMART_TEXT;
						}else{
							bodyType = FormattedText.Type.PLAIN_TEXT;
						}
						content.setBody( new FormattedText(comments, bodyType) );
						content.setContentHandler( PERCEPTION_CONTENT_HANDLER );
						content.setIsAvailable( WebUtils.readBoolean( request.getParameter( AVAILABLE_PARAM ), true) );
						content.setIsFolder( false );
						content.setIsFromCartridge( false );
						content.setIsLesson( false );
						content.setIsSequential( false );
						content.setIsTracked( false );
						content.setLaunchInNewWindow( true );
						
						content.setRenderType( Content.RenderType.URL );
						content.setTitle( assessment.getSessionName() );
					//	content.setTitleColor();
						StringBuffer externalAssessmentUrl = new StringBuffer();
						externalAssessmentUrl.append( BbUtils.getCleverUrl( "LaunchAssessment", ctx.getCourse(), null ) );
						externalAssessmentUrl.append( "&" + LaunchAssessmentServlet.ASSESSMENT_ID_PARAM );
						externalAssessmentUrl.append( "=" + WebUtils.parseQueryValue( assessment.getAssessmentId() ));
						content.setUrl( externalAssessmentUrl.toString() );
						
						String restrict_end = request.getParameter("restrict_end"); // bb val
						if( "on".equals(restrict_end) || "1".equals(restrict_end) ){
							content.setEndDate( readDate(request.getParameter(END_DATE_PARAM), content.getEndDate()) );
						}else{
							content.setEndDate( null );
						}
						
						String restrict_start = request.getParameter("restrict_start"); // bb val
						if( BbUtils.isVersion(6,1) || "on".equals(restrict_start) || "1".equals(restrict_start) ){
							content.setStartDate( readDate(request.getParameter(START_DATE_PARAM), content.getStartDate()) );
						}else{
							content.setStartDate( null );
						}
						
						if( content.getEndDate() != null && content.getStartDate() != null && content.getEndDate().before(content.getStartDate()) ){
							// invalid dates
							writeBackErrorUpdate( clientWriter, getSafeCourseString("invalid-schedule-dates.title", locale), getSafeCourseString("invalid-schedule-dates.message", locale));
							return true;
						}
						
						// fetch the schedule loader
						PerceptionScheduleLoader scheduleLoader = PerceptionServiceManager.getPerceptionScheduleLoader();
						
						// fetch the schedule persister
						PerceptionSchedulePersister schedulePersister = PerceptionServiceManager.getPerceptionSchedulePersister();
												
						List groupParticipants = null;
						boolean reloadGroupParticipants = false;
						
						if( allowMultipleAttempts ){
							// add new students
						
							// load all users in course
							courseUsers = BbUtils.getCourseMembershipDbLoader().loadByCourseId( ctx.getCourseId(), null, true ); // load User object too
							
							// filter to get course students
							courseStudents = courseUsers.getFilteredSubList( new CourseMembershipRoleFilter(CourseMembership.Role.STUDENT) );
							
							// load group participants - smaller than load all users and will be much faster with subsequent runs.
							writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("full-synchronization-process-loading.participants", locale));
							groupParticipants = userLoader.getParticipantsByGroupId( group.getGroupId() );
	
							BbList usersToCheck;
							if( groupParticipants != null && groupParticipants.size() > 0 ){
								usersToCheck = courseStudents.getFilteredSubList( new ParticipantsNotEnrolledFilter(groupParticipants, settings.getUseExternalUserId()));
							}else{
								usersToCheck = courseStudents;
							}
				
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
								writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("full-synchronization-process-loading.addmembers", locale));
								membershipPersister.addParticipants( newGroupMemberships );
								
								//log.logDebug( "Added " + newGroupMemberships.size() + " users to group, reloading group participants" );
								//
								// added Mar 27th 
								// reload group memberships to take account of newly added participants
								//groupParticipants = userLoader.getParticipantsByGroupId( group.getGroupId() );
								
								reloadGroupParticipants = true;
								
							}else{
								++stepCount;
							}
						}
						
						if( allowMultipleAttempts ){
							// create an empty List to store the new schdeules in
							List participantSchedules = new Vector();
							
							if( reloadGroupParticipants || groupParticipants == null ){
								// load group participants - smaller than load all users and will be much faster with subsequent runs.
								writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("full-synchronization-process-loading.participants", locale));
								groupParticipants = userLoader.getParticipantsByGroupId( group.getGroupId() );						
							}
							
							if( !isNew ){
								
								// get group schedule to delete
								String schedule_id = contentSettings.getScheduleId(); // single group based schedule
								if( schedule_id != null ){
									try{
										schedulePersister.deleteByScheduleId( schedule_id );
									}catch( ObjectNotFoundException obfe ){
										// ignore	
									}catch( Exception e ){
										// ignore
										log.logError( "Failed to remove group schedule", e );
									}
								}
								contentSettings.setScheduleId( null );
								contentSettings.setMaxAttempts( attemptsAllowed );
													
								
								// need to load existing schedules
								String[] participantScheduleIds = contentSettings.getParticipantScheduleIds();
								if( participantScheduleIds != null ){
									for( int i=0; i<participantScheduleIds.length; i++ ){
										try{
											PerceptionSchedule schedule = scheduleLoader.getScheduleById( participantScheduleIds[i] );
											participantSchedules.add( schedule );
										}catch(ObjectNotFoundException onfe){
											// shedule deleted from Perception manually?
											log.logDebug( "Schedule removed from Perception manually: " + participantScheduleIds[i] );
										}
									}
								}
								// create new schedules for participants who don't have one
								for( Iterator userIterator = groupParticipants.iterator(); userIterator.hasNext(); ){
									PerceptionUser participant = (PerceptionUser) userIterator.next();
									
									boolean found = false;
									for( Iterator scheduleIterator = participantSchedules.iterator(); scheduleIterator.hasNext(); ){
										PerceptionSchedule schedule = (PerceptionSchedule) scheduleIterator.next();
										if( participant.getUserId().equalsIgnoreCase( schedule.getParticipantId()) ){ // match
											found = true;
											break;
										}else{
											log.logDebug( "userId=" + participant.getUserId() + ", schedule user=" + schedule.getParticipantId() );
										}
									}
									
									if( !found ){
										PerceptionSchedule schedule = new PerceptionSchedule();
										schedule.setParticipantId( participant.getUserId() );
										participantSchedules.add( schedule );
									}
									
								}
								
								
		
							}else{
								for( Iterator userIterator = groupParticipants.iterator(); userIterator.hasNext(); ){
									PerceptionUser participant = (PerceptionUser) userIterator.next();
									PerceptionSchedule schedule = new PerceptionSchedule();
									schedule.setParticipantId( participant.getUserId() );
									participantSchedules.add( schedule );
									
								}
								++stepCount;
							}
							
							List newParticipantScheduleIds = new Vector();
							log.logDebug( "Persisting schedules for  " + participantSchedules.size() + " users" );
							writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("create-assessment-process-loading.addschedules", locale));
							
							for( Iterator scheduleIterator = participantSchedules.iterator(); scheduleIterator.hasNext(); ){	
								PerceptionSchedule schedule = (PerceptionSchedule) scheduleIterator.next();
								schedule.setAssessmentId( assessment.getAssessmentId() );
								schedule.setGroupId( perceptionContext.getPerceptionGroup().getGroupId() );
							
								schedule.setMaxAttempts( attemptsAllowed );
								schedule.setRestrictAttempts( true );						
								
								schedule.setScheduleStarts( content.getStartDate() );
								schedule.setScheduleStops( content.getEndDate() );
								
								if( content.getEndDate() != null || content.getStartDate() != null ){
									schedule.setRestrictTimes( true );
								}else{
									schedule.setRestrictTimes( false );
								}
								schedule.setScheduleName( assessment.getSessionName() );
								try{
									schedulePersister.persist( schedule );
									// add id to content settings
									if( schedule.getParticipantId() != null ){ // participant schedule
										newParticipantScheduleIds.add( schedule.getScheduleId() );
									}
								}catch( PerceptionDataException pde ){
									log.logError( "Error saving schedule", pde );
								}
								
							}
	
							if( newParticipantScheduleIds != null && newParticipantScheduleIds.size() > 0 ){
								String[] participantScheduleIds = (String[]) newParticipantScheduleIds.toArray( (Object[]) Array.newInstance(String.class,newParticipantScheduleIds.size() ));
								contentSettings.setParticipantScheduleIds( participantScheduleIds );
							}else{
								
							}

	
						}else{
							// create a single group schedule
							
							PerceptionSchedule schedule;
							
							if( isNew ){
								
								log.logDebug( "Creating new schedule" );
								schedule = new PerceptionSchedule();
								stepCount+=2;
							}else{
								
								log.logDebug( "Loading existing schedule" );
								writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("create-assessment-process-loading.schedule", locale));
								// load schedule 
								try{
									
									schedule = scheduleLoader.getScheduleById( contentSettings.getScheduleId() );
								}catch( Exception e ){
									log.logError( "Failed to load group schedule", e );
									schedule = new PerceptionSchedule();
								}
								// get participant schedules to delete
								String[] participantSchedules = contentSettings.getParticipantScheduleIds();
								
								writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("create-assessment-process-loading.removingschedules", locale));
								if( participantSchedules != null ){
									for( int i=0; i<participantSchedules.length; i++ ){
										try{
											schedulePersister.deleteByScheduleId( participantSchedules[i] );
										}catch( ObjectNotFoundException obfe ){
											// ignore	
										}catch( Exception e ){
											// ignore
											log.logError( "Failed to remove participant schedule", e );
										}
									}
									contentSettings.setParticipantScheduleIds( null );
								}

							}
							
							schedule.setParticipantId( null );
							schedule.setAssessmentId( assessment.getAssessmentId() );
							schedule.setGroupId( perceptionContext.getPerceptionGroup().getGroupId() );
						
							schedule.setMaxAttempts( 0 );
							schedule.setRestrictAttempts( false );
	
							schedule.setScheduleStarts( content.getStartDate() );
							schedule.setScheduleStops( content.getEndDate() );
							
							if( content.getEndDate() != null || content.getStartDate() != null ){
								schedule.setRestrictTimes( true );
							}
							schedule.setScheduleName( assessment.getSessionName() );
							log.logDebug( "Persisting schedule for group" );
							writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("create-assessment-process-loading.addgroupschedule", locale));
							schedulePersister.persist( schedule );
							contentSettings.setScheduleId( schedule.getScheduleId() );
							
						}									
									
							
						// store the Peception assessment id
						contentSettings.setAssessmentId( assessment.getAssessmentId() );
	
						// save content
						log.logDebug( "Persisting content" );
						writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("create-assessment-process-loading.addcontent", locale));
						BbUtils.getContentDbPersister().persist( content );
						
						// save content settings
						log.logDebug( "Persisting content settings" );
						writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("create-assessment-process-loading.addsettings", locale));
						contentSettings.persist();
						
						// added for 4.3
				
						boolean updateExistingAttempts = false;
						int existingAttemptType = courseSettings.getAssessmentAttemptType( assessment.getAssessmentId() );
						int attemptType = WebUtils.readInt( request.getParameter( ATTEMPT_TYPE_PARAM ), existingAttemptType );
						//log.logDebug( "existingAttemptType=" + existingAttemptType + ", attemptType=" + attemptType );

						if( attemptType != existingAttemptType ){
							// type has change
							courseSettings.setAssessmentAttemptType( assessment.getAssessmentId(), attemptType );	
							// save course settings
							courseSettings.persist();								
							updateExistingAttempts = WebUtils.readBoolean( request.getParameter( UPDATE_EXISTING_ATTEMPTS_PARAM ), updateExistingAttempts );									
						}
						//log.logDebug( "updateExistingAttempts=" + updateExistingAttempts );
								
						// create gradebook column
						boolean createGradebookColumn = WebUtils.readBoolean( request.getParameter( CREATE_GRADEBOOK_COLUMN_PARAM ), false );
						if( createGradebookColumn && lineItem == null ){
							// check to see if one already exists
							lineItem = AddGradeBookEntryServlet.getLineitemByCourseNameAndId(log, course.getId(), assessment.getSessionName(), assessment.getAssessmentId());
							if( lineItem == null ){
								log.logDebug( "Creating gradebook column" );
								writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("create-assessment-process-loading.createlineitem", locale));
								AddGradeBookEntryServlet.createNewLineitem( log, course.getId(), assessment.getAssessmentId(),assessment.getSessionName(),settings.getCanStudentsSeeScores());
							}						
						}else if( updateExistingAttempts ){
									
							log.logDebug( "Updating existing entries" );
							if( lineItem == null )
								lineItem = AddGradeBookEntryServlet.getLineitemByCourseNameAndId(log, course.getId(), assessment.getSessionName(), assessment.getAssessmentId());
							if( lineItem != null ){
								writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("create-assessment-process-loading.updategradebook", locale));
								String userMessage = getSafeCourseString("create-assessment-process-loading.updategradefor", locale);
								attemptType = courseSettings.getAssessmentAttemptType(assessment.getAssessmentId());
								
								if( courseStudents == null ){
									// load all users in course
									courseUsers = BbUtils.getCourseMembershipDbLoader().loadByCourseId( ctx.getCourseId(), null, true ); // load User object too
							
									// filter to get course students
									courseStudents = courseUsers.getFilteredSubList( new CourseMembershipRoleFilter(CourseMembership.Role.STUDENT) );
								}

								for( Iterator studentsIterator = courseStudents.iterator(); studentsIterator.hasNext(); ){
									try{
										CourseMembership studentEnrolment = (CourseMembership) studentsIterator.next();
										User student = studentEnrolment.getUser();
										Score score = BbUtils.getScoreDbLoader().loadByCourseMembershipIdAndLineitemId( studentEnrolment.getId(), lineItem.getId() );
										if( score == null )
											continue; // no score to update
										writeScriptUpdate( clientWriter, stepCount, userMessage + " " + student.getFamilyName() + ", " + student.getGivenName() );
										log.logDebug( userMessage + " " + student.getFamilyName() + ", " + student.getGivenName() );
										BbUtils.sortAttempts( score.getOutcome(), attemptType );
										
										// IMPORTANT - save score object
										BbUtils.getScoreDbPersister().persist( score );
									}catch( KeyNotFoundException e ){
										log.logDebug( e.getMessage() );
									}catch( Exception e ){
										log.logError( "Unable to modify gradebook score", e );
									}
								}										
							}else{
								writeScriptUpdate( clientWriter, ++stepCount, getSafeCourseString("create-assessment-process-loading.gradebookempty", locale));
							}

						}else{
							++stepCount;
						}
				
					}catch( PersistenceException e ){
						log.logError( "Failed to create assessment link: " + ctx.getCourseId(), e );
						writeErrorUpdate( clientWriter, getSafeSystemString("error.title", locale), getSafeSystemString("error.message", locale) );
					//	return sendDefaultError(request, response);
					}catch( ValidationException e ){
						log.logError( "Failed to create assessment link: " + ctx.getCourseId(), e );
						writeErrorUpdate( clientWriter, getSafeSystemString("error.title", locale), getSafeSystemString("error.message", locale) );
					//	return sendDefaultError(request, response);
					}catch( PerceptionNotFoundException e ){
						writeErrorUpdate( clientWriter, getSafeCourseString("perception-not-found.title", locale), getSafeCourseString("perception-not-found.message", locale));
					}catch( PerceptionSecurityException e ){
						writeErrorUpdate( clientWriter, getSafeSystemString("access-denied.title", locale), getSafeSystemString("access-denied.message", locale));
					}catch( PerceptionDataException e ){
						
						// can include object not founds
	
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
			
					}catch( Exception e ){
						
						// let user know there's a bug	
						log.logError( "Failed to create assessment link", e );
						writeErrorUpdate( clientWriter, getSafeSystemString("error.title", locale), getSafeSystemString("error.message", locale) );
					}

					writeComplete( clientWriter, getSafeCourseString("full-synchronization-process-complete.title", locale), getSafeCourseString("full-synchronization-process-complete.message", locale) );
	
				}
			}catch( PerceptionNotFoundException e ){
				return this.sendError(request, response, getSafeCourseString("perception-not-found.title", locale), getSafeCourseString("perception-not-found.message", locale));
			}catch( PerceptionSecurityException e ){
				return this.sendError(request, response, getSafeSystemString("access-denied.title", locale), getSafeSystemString("access-denied.message", locale));
			}catch( PerceptionDataException e ){ // TODO
				return sendPerceptionDataException(request, response, e, null);
			}
		}else{
			return this.sendError(request, response, getSafeSystemString("access-denied.title", locale), getSafeSystemString("access-denied.message", locale));
		}
		return true;
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

	/**
	 * @param request 
	 * @param ctx   the bb context
	 * @return true if the user has already chosen an assessment to link to
	 */
	protected boolean isAssessmentSelected( HttpServletRequest request, Context ctx ){ 
	
		if( ctx.getContent() == null || (!ctx.getContent().getIsFolder()) ) // yes, return true if content is null, so we can throw an error elsewhere
			return true;
		
		String perception_id = request.getParameter( PERCEPTION_ID_PARAM ); 
		if( perception_id != null && perception_id.trim().length() > 0 )
			return true;
			
		return false;
	}
	

	private static int getPosition( Id folder_id ){
		try{
			BbList contentList = BbUtils.getContentDbLoader().loadChildren( folder_id );
			return contentList.size();
		}catch(Exception e){
			// help
			e.printStackTrace();
			return 0;
		}
	}
	
	public static Calendar readDate(String str, Calendar defaultCalendar){
		try{
			// 2005-06-09 09:35:31
			int year = Integer.parseInt(str.substring(0, 4));
			int month = Integer.parseInt(str.substring(5, 7));
			int day = Integer.parseInt(str.substring(8, 10));
			int hour = Integer.parseInt(str.substring(11, 13));
			int minute = Integer.parseInt(str.substring(14, 16));
			int second = Integer.parseInt(str.substring(17, 19));
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, day, hour, minute, second);
			return cal;
		}catch(Exception e){
			return defaultCalendar;
		}
	}
	

}
