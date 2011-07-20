package com.questionmark;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.questionmark.QMWISe.Administrator;
import com.questionmark.QMWISe.Assessment;
import com.questionmark.QMWISe.Participant;
import com.questionmark.QMWISe.ScheduleV42;

import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.persist.Id;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.platform.context.Context;
import blackboard.platform.persistence.PersistenceServiceFactory;
import blackboard.platform.plugin.PlugInException;

public class QMPCourseContext extends QMPContext {

	public String courseId = null;
	public ConfigFileReader configReader = null;
	public CourseSettings courseSettings = null;
	public CourseDbLoader courseLoader = null;
	public CourseMembershipDbLoader crsMembershipLoader = null;
	public Course course = null;
	public CourseMembership crsMembership = null;
	public CourseMembership.Role userRole=CourseMembership.Role.GUEST;
	public String groupID=null;
	public boolean isAdministrator=false;
	public boolean profileCheck=true;
	public boolean taProfile=false;
	public boolean instructorProfile=false;
	public String userID=null;
	public Vector<ScheduleInfo> scheduleInfo=null;
	
	public QMPCourseContext(HttpServletRequest request, Context ctx) {
		super(request, ctx);
		courseId = request.getParameter("course_id");
		try {
			FindPhantomUserId();
			if(courseId != null) {
				configReader = new ConfigFileReader(courseId);
				// load the courseSettings file too...
				courseSettings = new CourseSettings(courseId);
				// Generate a persistence framework course Id to be used for loading the course
				Id courseIdObject = bbPm.generateId(Course.DATA_TYPE, courseId);
				courseLoader = (CourseDbLoader) bbPm.getLoader(CourseDbLoader.TYPE);
				course = courseLoader.loadById(courseIdObject);
				if (course != null) {
					groupID=courseSettings.getProperty("groupid");
					// get the membership data to determine the User's Role
					crsMembershipLoader = (CourseMembershipDbLoader) bbPm.getLoader(CourseMembershipDbLoader.TYPE);
					try {
						Id sessionUserId = user.getId();				
						crsMembership = crsMembershipLoader.loadByCourseAndUserId(courseIdObject, sessionUserId);
						userRole=crsMembership.getRole();
						if ( ! userRole.equals(CourseMembership.Role.INSTRUCTOR)
								&& ! userRole.equals(CourseMembership.Role.TEACHING_ASSISTANT)
								&& !userRole.equals(CourseMembership.Role.STUDENT)) {
							Fail("Course Role","You do not have permission to see this page.");
							userRole=CourseMembership.Role.GUEST;
						}
						isAdministrator=(userRole.equals(CourseMembership.Role.INSTRUCTOR) ||
								userRole.equals(CourseMembership.Role.TEACHING_ASSISTANT));
					} catch (KeyNotFoundException e) {
						// There is no membership record.
						Fail("Course Role","No role found for the current user in this course "+e.getMessage());
					}
				} else {
					FailCourse();
				}
			} else {
				FailCourse();
			}
		} catch (PlugInException e) {
			Fail("Unexpected PlugInException",e.getMessage());
		} catch (PersistenceException e) {
			Fail("Unexpected PersistenceException",e.getMessage());
		} catch (QMWiseException e) {
			FailQMWISe(e);
		}
	}

	
	public void FindPerceptionGroupID() throws QMWiseException {
		//get Perception group id, make it if it doesn't exist yet
		if (groupID == null && Connect()) {
			try {
				com.questionmark.QMWISe.Group group = stub.getGroupByName(course.getBatchUid());
				groupID = group.getGroup_ID();
				// Cache the group ID for future synchronization on this course
				courseSettings.setProperty("groupid", groupID);
				courseSettings.saveSettingsFile();
			} catch(RemoteException e) {
				QMWiseException qe = new QMWiseException(e);
				if(qe.getQMErrorCode() == 1201) {
					groupID=null;
				} else {
					throw qe;
				}
			}
		}
	}

	
	public void CreatePerceptionGroup() throws QMWiseException {
		//group doesn't exist -- make it
		try {
			com.questionmark.QMWISe.Group newgroup = new com.questionmark.QMWISe.Group();
			newgroup.setParent_ID("0");
			newgroup.setGroup_Name(course.getBatchUid());
			newgroup.setDescription(course.getTitle());
			groupID = stub.createGroup(newgroup);
		} catch(RemoteException e) {
			throw new QMWiseException(e);
		}
	}

	
	public void FindPerceptionUserID() throws QMWiseException {
		if (userID == null && Connect()) {
			if (isAdministrator) {
				try {
					userID = stub.getAdministratorByName(user.getUserName()).getAdministrator_ID();
				} catch(RemoteException e) {
					QMWiseException qe = new QMWiseException(e);
					if(qe.getQMErrorCode() == 1601) {
						//user doesn't exist
						userID=null;
					} else {
						throw qe;
					}
				}				
			} else {
				try {
					userID=stub.getParticipantByName(user.getUserName()).getParticipant_ID();
				} catch (RemoteException e) {
					QMWiseException qe = new QMWiseException(e);
					if(qe.getQMErrorCode() == 1101) {
						// user doesn't exist yet
						userID=null;
					} else {
						throw qe;
					}
				}
			}
		}
	}
	
	
	public void CheckProfiles() throws QMWiseException {
		if (profileCheck) {
			try {
				//make sure admin profiles exist in Perception 
				//(bug in QMWise causes createAdministrator call 
				//with a non-existent profile name to fail with 
				//a success message)
				String[] profiles = stub.getProfileNameList();
				for(int k = 0; k < profiles.length; k++) {
					if(profiles[k].equals("BLACKBOARD TA")) {
						taProfile=true;
					} else if (profiles[k].equals("BLACKBOARD INSTRUCTOR")) {
						instructorProfile=true;
					}
				}
			} catch(RemoteException e) {
				throw new QMWiseException(e);
			}
			profileCheck=false;
		}
	}
	
	
	public void CreatePerceptionUser() throws QMWiseException {
		// person doesn't exist -- create them
		if (isAdministrator) {
			String profile;
			CheckProfiles();
			if (userRole.equals(CourseMembership.Role.INSTRUCTOR)) {
				if (! instructorProfile) {
					throw new QMWiseException("Error creating administrator: the profile BLACKBOARD INSTRUCTOR does not exist in Perception");
				}
				profile="Blackboard Instructor";
			} else {
				if (! taProfile ) {
					throw new QMWiseException("Error creating administrator: the profile BLACKBOARD TA does not exist in Perception");
				}
				profile="Blackboard TA";
			}
			try {
				//build new user
				Administrator newuser = new Administrator();
				newuser.setAdministrator_ID("0");
				newuser.setAdministrator_Name(user.getUserName());
				newuser.setPassword(user.getPassword().substring(0, 20));
				newuser.setProfile_Name(profile);
				//make new user
				userID = stub.createAdministrator(newuser);
				AddToGroup();
			} catch(RemoteException e) {
				throw new QMWiseException(e);
			}			
		} else {
			try {
				Participant newuser = new Participant();							
				//Clean out special characters by replacing them with acceptable ones (By Perception)
				String userFirstName = replaceSpecChars(user.getGivenName());
				String userLastName = replaceSpecChars(user.getFamilyName());	
				newuser.setFirst_Name(userFirstName);
				newuser.setLast_Name(userLastName); 							
				newuser.setParticipant_Name(user.getUserName());
				newuser.setPassword(user.getPassword().substring(0, 20));
				userID = stub.createParticipant(newuser);
				AddToGroup();
			} catch(RemoteException e) {
				QMWiseException qe = new QMWiseException(e);
				if(qe.getQMErrorCode()==4002)
					System.out.println("Illegal character not handled");
				throw qe;
			}
			
		}
	}
	
	
	public boolean IsGroupMember() throws QMWiseException {
		boolean found=false;
		try {
			com.questionmark.QMWISe.Group[] groupList;
			if (isAdministrator)
				groupList=stub.getAdministratorGroupList(userID);
			else
				groupList=stub.getParticipantGroupList(userID);
			int iGroup;
			for(iGroup = 0; iGroup < groupList.length; iGroup++) {
				if(groupList[iGroup].getGroup_ID().equals(groupID)) {
					found=true;
					break;
				}
			}
		} catch (RemoteException e) {
			throw new QMWiseException(e);
		}
		return found;
	}
	
	
	public void AddToGroup() throws QMWiseException {
		String[] userIDList=new String[1];
		userIDList[0]=userID;
		try {
			if (isAdministrator) {
				stub.addGroupAdministratorList(groupID, userIDList);
			} else {
				stub.addGroupParticipantList(groupID,userIDList);
				//get schedules from phantom user
				ScheduleV42[] schedulesarray;
				schedulesarray = stub.getScheduleListByParticipantV42(new Integer(phantomID).intValue());
				for(int i = 0; i < schedulesarray.length; i++) {
					ScheduleV42 schedule=schedulesarray[i];
					if(schedule.getGroup_ID() == new Integer(groupID).intValue()) {
						schedule.setParticipant_ID(new Integer(userID).intValue());					
						stub.createScheduleParticipantV42(schedule);
					}
				}
			}
		} catch (RemoteException e) {
			throw new QMWiseException(e);
		}
	}
	
	
	public Boolean Synchronize() throws QMWiseException {
		boolean syncUsers=(pb.getProperty("perception.syncusers")!=null &&
				!courseSettings.getProperty("perception.syncusers","false").equals("false"));
		FindPerceptionGroupID();
		if (groupID == null) {
			if (pb.getProperty("perception.syncgroups")==null) {
				Fail("Connector Disabled","This tool is not enabled for use with this course (no corresponding group in Perception)");
				return false;
			}
			CreatePerceptionGroup();
		}
		FindPerceptionUserID();
		System.out.println("Found userID "+userID);
		if (userID == null) {
			if (!syncUsers) {
				Fail("Connector Disabled","This tool is not available to you (no corresponding user in Perception)");
				return false;
			}
			CreatePerceptionUser();
		} else if (!IsGroupMember()) {
			System.out.println("userID="+userID+" is not a member of group "+groupID);
			if (!syncUsers) {
					Fail("Connector Disabled","This tool is not available to you in this course (no group membership in Perception)");
					return false;
				}
			AddToGroup();
		}
		System.out.println("userID="+userID+" is (now) a member of group "+groupID);
		return true;
	}

	
	public String ForceSynchronization() {
		String result="";
		if (userRole.equals(CourseMembership.Role.INSTRUCTOR) || userRole.equals(CourseMembership.Role.TEACHING_ASSISTANT)) {
			System.out.println("Perception: course " + courseId + ": user synchronization forced");
			UserSynchronizer us = new UserSynchronizer();
			try {
				result = us.synchronizeCourse(courseId);
				configReader.setCourseSyncDate();
			} catch (Exception e) {
				System.out.println("Perception: course " + courseId + ": synchronization failed: " + e.getMessage());
				Fail("Synchronization Failure",e.getMessage());
			}
		} else {
			Fail("Course Administration","Your role is not authorized to view this page");
		}
		return result;
	}

	
	public Assessment[] GetAssessments() throws QMWiseException {
		Assessment[] assessments = null;
		if (isAdministrator) {
			try {				
				assessments = stub.getAssessmentListByAdministrator(userID);
			} catch (RemoteException e) {
				throw new QMWiseException(e);
			}			
		}
		Arrays.sort(assessments, new AssessmentComparator());
		return assessments;
	}
	
	
	public Vector<ScheduleV42> GroupSchedules(String filter) throws QMWiseException {
		Vector<ScheduleV42> schedules = new Vector<ScheduleV42>();
		try {
			// Assumption:
			// number of courses x number of schedules is less than...
			// max(number of students in a course) x number of limited attempt schedules in that course
			// Either way we would prefer a method that also filtered by group
			ScheduleV42[] limitedSchedules = null;
			if (isAdministrator) {
				limitedSchedules = stub.getScheduleListByParticipantV42(new Integer(phantomID).intValue());
			} else {
				limitedSchedules = stub.getScheduleListByParticipantV42(new Integer(userID).intValue());
			}
			for(int i = 0; i < limitedSchedules.length; i++) {
				if(limitedSchedules[i].getGroup_ID() == new Integer(groupID).intValue())
					if (filter == null || filter.equals(limitedSchedules[i].getSchedule_Name()))
						schedules.add(limitedSchedules[i]);
			}
			ScheduleV42[] groupSchedules = null;
			// Not sure how well documented using 0 is here
			// returns approx (number of courses x number of group schedules) records
			groupSchedules=stub.getScheduleListByParticipantV42(0);
			for(int i = 0; i < groupSchedules.length; i++)
				if(groupSchedules[i].getGroup_ID() == new Integer(groupID).intValue())
					if (filter == null || filter.equals(groupSchedules[i].getSchedule_Name()))
						schedules.add(groupSchedules[i]);
		} catch (RemoteException e) {
			throw new QMWiseException(e);
		}
		return schedules;
	}
	
	
	public void GetScheduleInfo(Vector<ScheduleV42> schedules) {
		scheduleInfo = new Vector<ScheduleInfo>();
		for(int i = 0; i < schedules.size(); i++) {
			scheduleInfo.add(new ScheduleInfo(this,schedules.get(i),isAdministrator));
		}
	}

	public void FailQMWISe(QMWiseException e) {
		Fail("Communication Error with Perception Server",e.getMessage());
	}
	
	public void FailCourse() {
		Fail("Course Not Found","There was no course associated with this request");
	}
	

}
