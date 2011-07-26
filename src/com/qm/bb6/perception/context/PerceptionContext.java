/*
 * @(#)PerceptionContext.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.context;

import java.util.*;

import com.qm.bb6.perception.data.*;
import com.qm.bb6.perception.service.*;
import com.qm.bb6.perception.config.*;
import com.qm.bb6.perception.security.AccessDeniedException;
import com.qm.bb6.perception.util.BbUtils;

import blackboard.data.course.*;
import blackboard.data.user.User;
import blackboard.platform.context.Context;


/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class PerceptionContext {
		
	private PerceptionGroup group; // == bb course
	private PerceptionUser user; // == bb user
	private List enrolments; // == perception group memberships
	
	public static final String INSTRUCTOR_PROFILE_NAME = "Blackboard Instructor";
	public static final String TEACHING_ASSISTANT_PROFILE_NAME = "Blackboard TA";
	
	private PerceptionContext() {
		// force GetInstance()
	}
	
	
	public PerceptionGroup getPerceptionGroup(){
		return group;
	}
	
	public PerceptionUser getPerceptionUser(){
		return user;
	}
	
	public static PerceptionContext getInstance( Context ctx ) throws PerceptionDataException {
		if( ctx != null && ctx.getUser() != null && ctx.getCourse() != null && ctx.getUser().getSystemRole() != User.SystemRole.GUEST){
			
			PerceptionLog log = PerceptionServiceManager.getPerceptionLog();
			// validate bb enrolment
			CourseMembership cm = ctx.getCourseMembership();
			User bbUser = ctx.getUser();
			boolean isSystemAdmin = false;
			boolean isCourseAdmin = false;
			
			if( cm != null && cm.getRole() != CourseMembership.Role.GUEST ){
				// valid role
				// NOTE: no prevision here for Graders and course builders, do this elsewhere
				if( cm.getRole() != CourseMembership.Role.STUDENT )
					isCourseAdmin = true;
			}else if( bbUser.getSystemRole() == User.SystemRole.SYSTEM_ADMIN ){
				isSystemAdmin = true;
			}else{
				// GUEST
				return null;
			}
			
			
			PerceptionSettings settings = PerceptionSettings.loadFromCache();
			PerceptionContext pContext = new PerceptionContext();
			// load group for bb course
			String course_id;
			if( settings.getUseExternalCourseId() ){
				course_id = ctx.getCourse().getBatchUid();
			}else{
				course_id = ctx.getCourse().getCourseId();
			}
			
			try{
				pContext.group = PerceptionServiceManager.getPerceptionGroupLoader().getGroupByName( course_id );
			}catch(ObjectNotFoundException e){ // none
				pContext.group = null;
			}

			// load user
			String user_id;
			if( settings.getUseExternalUserId() ){
				user_id = bbUser.getBatchUid();
			}else{
				user_id = bbUser.getUserName();
			}

			try{
				if( !isCourseAdmin ){
					pContext.user = PerceptionServiceManager.getPerceptionUserLoader().getParticipantByName( user_id );
					if( pContext.group != null ){
						pContext.enrolments = ((PerceptionParticipant) pContext.user).getGroupMemberships();
					}
				}else{
					pContext.user = PerceptionServiceManager.getPerceptionUserLoader().getAdministratorByName( user_id );
					if( pContext.group != null ){
						// load current enrolments
						List groups = PerceptionServiceManager.getPerceptionGroupLoader().getGroupsByAdministratorId( pContext.user.getUserId() );
						if( groups != null ){
							pContext.enrolments = new ArrayList();
							Iterator iterator = groups.iterator();
							while( iterator.hasNext() ){
								PerceptionGroup group = (PerceptionGroup) iterator.next();
								PerceptionGroupMembership enrolment = new PerceptionGroupMembership();
								enrolment.setUserId( pContext.user.getUserId() );
								enrolment.setGroupId( group.getGroupId() );
								enrolment.setIsAdmin( true );
								pContext.enrolments.add( enrolment );
							}
						}
					}
				}
			}catch(ObjectNotFoundException e){ // none
				pContext.user = null;
			}
					
			if( settings.getIsSynchronizationEnabled() ){
				
				if( pContext.user == null && (!isSystemAdmin) ){ // do not create user account for bb sys admins no enrolled onto course
					pContext.user = createUser( bbUser, cm.getRole(), user_id, isCourseAdmin );
				}
				if( pContext.group == null ){
					pContext.group = createGroup( ctx.getCourse(), course_id );
					if( pContext.user != null ){
						createGroupMembership( pContext.group, pContext.user );
					}
				}else if( pContext.user != null ){
					// check enrolment for course exists
					boolean isEnrolled = false;
					if( pContext.enrolments != null ){
						Iterator iterator = pContext.enrolments.iterator();
						while( iterator.hasNext() ){
							PerceptionGroupMembership enrolment = (PerceptionGroupMembership) iterator.next();
							if( enrolment.getGroupId().equals( pContext.group.getGroupId()) ){
								// enrolled
								isEnrolled = true;
								break;
							}
						}
					}
					if( !isEnrolled ){
						createGroupMembership( pContext.group, pContext.user );
					}
					
				}
				
				// added Feb, 2007
				// authoring rights 
				if( settings.getCreateAuthoringRights() && isCourseAdmin ){
					
					// load course settings
					// NOTE: course settings used to store topic ID and folder ID
					// this is VERY BAD, but methods don't exist in Perception
					// please alter later
					CourseSettings courseSettings = CourseSettings.load( ctx.getCourse() );
					// check topic exists
					// added Feb, 2007
					// Authoring rights
					// create topic
					PerceptionTopic topic;
					if( BbUtils.isNullOrEmpty(courseSettings.getTopicID()) ){
						// load
					//	try{
					//		topic = PerceptionServiceManager.getPerceptionTopicLoader().getTopicByNameAndParentId( removeInvalidFolderCharacters(course_id), null );
					//	}catch( ObjectNotFoundException obnfe ){
							// create
							try{
								topic = createTopic( ctx.getCourse(), removeInvalidFolderCharacters(course_id) );
								courseSettings.setTopicID( topic.getTopicId() );
							} catch( PerceptionDataException pde ){
								topic = null;
								log.logDebug( "failed to create topic, may already exist" );
							}
					//	}
					}else{
						topic = new PerceptionTopic();
						topic.setTopicId( courseSettings.getTopicID() );
					}

					if( topic != null ){
						// create/amend rights
						try{
							createTopicMembership( topic, (PerceptionAdministrator) pContext.user );
						}catch( PerceptionDataException pde ){
							log.logError( "failed to grant topic rights", pde );
						}
					}
					
					PerceptionAssessmentFolder folder;
					if( BbUtils.isNullOrEmpty(courseSettings.getFolderID()) ){
						// load
					//	try{
					//		folder = PerceptionServiceManager.getPerceptionAssessmentFolderLoader().getAssessmentFolderByNameAndParentId( removeInvalidFolderCharacters(course_id), null );
					//	}catch( ObjectNotFoundException obnfe ){
							// create
							try{
								folder = createFolder( ctx.getCourse(), removeInvalidFolderCharacters(course_id) );
								courseSettings.setFolderID( folder.getAssessmentFolderId() );
							} catch( PerceptionDataException pde ){
								folder = null;
								log.logDebug( "failed to create folder, may already exist" );
							}
					//	}
					}else{
						folder = new PerceptionAssessmentFolder();
						folder.setAssessmentFolderId( courseSettings.getFolderID() );
					}

					if( folder != null ){
						// create/amend rights
						try{
							createFolderMembership( folder, (PerceptionAdministrator) pContext.user );
						}catch( PerceptionDataException pde ){
							log.logError( "failed to grant folder rights", pde );
						}
					}
					try{
						courseSettings.persist();
					}catch( Exception e ){
						log.logError( "failed to save course settings - cannot save folder/topic IDs", e );
					}
				}
			}
			return pContext;
		}else if( ctx == null || ctx.getUser() == null || ctx.getUser().getSystemRole() == User.SystemRole.GUEST ){
			throw new BlackboardUserNotFoundException();
		}else{ // if( ctx.getCourse() == null ){
			throw new BlackboardCourseNotFoundException();
		}
	}	
	
	public static PerceptionUser createUser( User user, CourseMembership.Role role, boolean useExternalUserId, boolean isCourseAdmin ) throws PerceptionDataException {
		String user_id;
		if( useExternalUserId ){
			user_id = user.getBatchUid();
		}else{
			user_id = user.getUserName();
		}
		return createUser( user, role, user_id, isCourseAdmin );
		
	}

	public static PerceptionUser createUser( User user, CourseMembership.Role role, String user_id, boolean isCourseAdmin ) throws PerceptionDataException {
		
		PerceptionUser perceptionUser;
		if( isCourseAdmin )
			perceptionUser = new PerceptionAdministrator();
		else
			perceptionUser = new PerceptionParticipant();
			
		perceptionUser.setUserName( user_id );	
		
		perceptionUser.setAuthenticateExt( 0 ); // TODO
		perceptionUser.setDateRegistration( user.getCreatedDate() );
		perceptionUser.setDepartment( user.getDepartment() );
		// remove characters not allowed by Perception - should be remove by QMWISe
		perceptionUser.setFirstName(removeInvalidCharacters(user.getGivenName()));
		perceptionUser.setLastName(removeInvalidCharacters(user.getFamilyName()));
		perceptionUser.setMiddleName(removeInvalidCharacters(user.getMiddleName()));
		//perceptionUser.setLastName( user.getFamilyName() );
		//perceptionUser.setMiddleName( user.getMiddleName() );
		//perceptionUser.setFirstName( user.getGivenName() );
		if( user.getGender() == User.Gender.MALE )
			perceptionUser.setGender( "Male" );
		else if( user.getGender() == User.Gender.FEMALE )
			perceptionUser.setGender( "Female" );
		perceptionUser.setOrganizationName( user.getCompany() );
		// limit password to 20 chars - very bad but that's all Perception accepts
		// added Feb 13th, 2007
		// check bb password is 32 characters
		if( BbUtils.isNullOrEmpty(user.getPassword()) || user.getPassword().length() != 32 ){
			perceptionUser.setPassword( getRandomPassword().substring(0, 20) ); // limit to 20 chars for Perception
		}else{
			perceptionUser.setPassword( user.getPassword().substring(0, 20) ); // limit to 20 chars for Perception
		}
		//perceptionUser.setPassword( user.getPassword() );

	//	if( user.getShowEmailInfo() )
			perceptionUser.setPrimaryEmail( user.getEmailAddress() );
		
		if( user.getIsInfoPublic() ){
			if( user.getShowAddressInfo() ){
				perceptionUser.setPrimaryAddress1( user.getStreet1() );
				perceptionUser.setPrimaryAddress2( user.getStreet2() );
				perceptionUser.setPrimaryCity( user.getCity() );
				perceptionUser.setPrimaryCountry( user.getCountry() );
				perceptionUser.setPrimaryState( user.getState() );
				perceptionUser.setPrimaryZIP( user.getZipCode() );
			}
				
			if( user.getShowAddContactInfo() ){
				perceptionUser.setPrimaryFax( user.getBusinessFax() != null ? user.getBusinessFax() : user.getHomeFax() );
				if( user.getBusinessPhone1() != null ){
					perceptionUser.setPrimaryPhone( user.getBusinessPhone1() );
				}else if( user.getHomePhone1() != null ){
					perceptionUser.setPrimaryPhone( user.getHomePhone1() );
				}else{
					perceptionUser.setPrimaryPhone( user.getMobilePhone() );
				}
			}
		}
		if( role == CourseMembership.Role.INSTRUCTOR ){
			perceptionUser.setProfileName( INSTRUCTOR_PROFILE_NAME );
		}else if( role == CourseMembership.Role.TEACHING_ASSISTANT ){
			perceptionUser.setProfileName( TEACHING_ASSISTANT_PROFILE_NAME );
		}
		//perceptionUser.setSalutation( user.getS);
		perceptionUser.setTitle( user.getTitle() );
		perceptionUser.setURL( user.getWebPage() );
		perceptionUser.setUseCorrespondence( 0 ); // TODO

		PerceptionServiceManager.getPerceptionUserPersister().persist( perceptionUser );
		return perceptionUser;
	}
	
	private static PerceptionGroup createGroup( Course course, String course_id ) throws PerceptionDataException {
		
		PerceptionGroup perceptionGroup = new PerceptionGroup();

		perceptionGroup.setGroupName( course_id );
		
		perceptionGroup.setDescription( course.getTitle() );
		perceptionGroup.setParentId( "0" ); // needed

		PerceptionServiceManager.getPerceptionGroupPersister().persist( perceptionGroup );
		return perceptionGroup;

	}
	
	private static void createGroupMembership( PerceptionGroup perceptionGroup, PerceptionUser perceptionUser ) throws PerceptionDataException {
				
		PerceptionGroupMembership enrolment = new PerceptionGroupMembership();
		enrolment.setGroupId( perceptionGroup.getGroupId() );
		enrolment.setUserId( perceptionUser.getUserId() );
		enrolment.setIsAdmin( perceptionUser.getRole() == PerceptionUser.PERCEPTION_ADMINISTRATOR );
		PerceptionServiceManager.getPerceptionGroupMembershipPersister().persist( enrolment );

	}
	
	private static PerceptionTopic createTopic( Course course, String course_id ) throws PerceptionDataException {
		PerceptionTopic topic = new PerceptionTopic();
		topic.setTopicName( course_id );
		topic.setDescription( course.getTitle() );
		topic.setParentId( PerceptionTopic.ROOT_TOPIC ); // root
		PerceptionServiceManager.getPerceptionTopicPersister().persist( topic );
		return topic;
	}

	private static PerceptionAssessmentFolder createFolder( Course course, String course_id ) throws PerceptionDataException {
		PerceptionAssessmentFolder folder = new PerceptionAssessmentFolder();
		folder.setAssessmentFolderName( course_id );
		folder.setDescription( course.getTitle() );
		folder.setParentId( PerceptionTopic.ROOT_TOPIC ); // root
		PerceptionServiceManager.getPerceptionAssessmentFolderPersister().persist( folder );
		return folder;
	}
	
	private static void createTopicMembership( PerceptionTopic topic, PerceptionAdministrator administrator ) throws PerceptionDataException {
				
		PerceptionTopicMembership enrolment = new PerceptionTopicMembership();
		enrolment.setTopicId( topic.getTopicId() );
		enrolment.setAdministratorId( administrator.getUserId() );
		enrolment.setPermissions( PerceptionPermission.FULL_ACCESS );
		PerceptionServiceManager.getPerceptionTopicMembershipPersister().persist( enrolment );

	}	

	public static void deleteTopicMembership( String topicID, PerceptionAdministrator administrator ) throws PerceptionDataException {
				
		PerceptionTopicMembership enrolment = new PerceptionTopicMembership();
		enrolment.setTopicId( topicID );
		enrolment.setAdministratorId( administrator.getUserId() );
		enrolment.setPermissions( PerceptionPermission.NO_ACCESS );
		PerceptionServiceManager.getPerceptionTopicMembershipPersister().delete( enrolment );
	}	

	private static void createFolderMembership( PerceptionAssessmentFolder folder, PerceptionAdministrator administrator ) throws PerceptionDataException {
				
		PerceptionAssessmentFolderMembership enrolment = new PerceptionAssessmentFolderMembership();
		enrolment.setAdministratorId( administrator.getUserId() );
		enrolment.setPermissions( PerceptionPermission.FULL_ACCESS );
		enrolment.setFolderId( folder.getAssessmentFolderId() );
		PerceptionServiceManager.getPerceptionAssessmentFolderMembershipPersister().persist( enrolment );

	}	

	public static void deleteFolderMembership( String folderID, PerceptionAdministrator administrator ) throws PerceptionDataException {
				
		PerceptionAssessmentFolderMembership enrolment = new PerceptionAssessmentFolderMembership();
		enrolment.setAdministratorId( administrator.getUserId() );
		enrolment.setPermissions( PerceptionPermission.NO_ACCESS );
		enrolment.setFolderId( folderID );
		PerceptionServiceManager.getPerceptionAssessmentFolderMembershipPersister().delete( enrolment );

	}	
							

	
	// removes " and & characters from a string - as these are not acceptable in some Perception fields
	public static String removeInvalidCharacters( String source ){
		String newstr = new String(source);
		newstr = newstr.replaceAll("\\\"", "");
		newstr = newstr.replaceAll("\\&", "");
		return newstr;
	}
	
	public static String removeInvalidFolderCharacters( String source ){
		String newstr = new String(source);
		newstr = newstr.replaceAll("[\\<\\>\\#\\|\\\\]", "");
		return newstr;
	}
	
	// added Feb 13th, 2007
	/*
	 * Generates a random password for Bb users that do not have a valid password
	 */
	public static String getRandomPassword(){
		
		byte[] secureRandom = new byte[16];
		try{
			java.security.SecureRandom.getInstance("SHA1PRNG").nextBytes(secureRandom);
		}catch(Exception e){
			(new java.util.Random()).nextBytes(secureRandom);
		}
		String password = encode( secureRandom );
		return password;
	}
	
    public static String encode(/*const*/ byte[] data){
    	return encode( data, 0, data.length );
    }

    public static String encode(/*const*/ byte[] data, int off, int len)
    {
        char[]	ch;
        int	i;

        // Convert bytes to hex digits
        ch = new char[data.length*2];
        i = 0;

        while (len-- > 0)
        {
            int		b;
            int		d;

            // Convert next byte into a hex digit pair
            b = data[off++] & 0xFF;

            d = b >> 4;
            d = (d < 0xA ? d+'0' : d-0xA+'A');
            ch[i++] = (char) d;

            d = b & 0xF;
            d = (d < 0xA ? d+'0' : d-0xA+'A');
            ch[i++] = (char) d;
        }

        return (new String(ch));
    }
}
