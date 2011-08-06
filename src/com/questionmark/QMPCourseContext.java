package com.questionmark;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.ListIterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.questionmark.QMWISe.Administrator;
import com.questionmark.QMWISe.Assessment;
import com.questionmark.QMWISe.AssessmentTreeItem;
import com.questionmark.QMWISe.Participant;
import com.questionmark.QMWISe.ScheduleV42;

import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.data.user.User;
import blackboard.persist.Id;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.platform.context.Context;
import blackboard.platform.plugin.PlugInException;

public class QMPCourseContext extends QMPContext {

	public String courseId = null;
	public Id courseIdObject = null;
	public CourseSettings courseSettings = null;
	public CourseDbLoader courseLoader = null;
	public CourseMembershipDbLoader crsMembershipLoader = null;
	public Course course = null;
	public User courseUser = null;
	public CourseMembership crsMembership = null;
	public CourseMembership.Role userRole=CourseMembership.Role.GUEST;
	public String groupID=null;
	public boolean isAdministrator=false;
	public boolean profileCheck=true;
	public boolean taProfile=false;
	public boolean instructorProfile=false;
	public String userID=null;
	Administrator userAdministratorInfo = null;
	Participant userParticipantInfo = null;
	public Vector<ScheduleInfo> scheduleInfo=null;
	
	public QMPCourseContext(HttpServletRequest request, Context ctx) {
		super(request, ctx);
		courseId = request.getParameter("course_id");
		try {
			FindPhantomUserId();
			if(courseId != null) {
				// load the courseSettings file...
				courseSettings = new CourseSettings(courseId);
				// Generate a persistence framework course Id to be used for loading the course
				courseIdObject = bbPm.generateId(Course.DATA_TYPE, courseId);
				courseLoader = (CourseDbLoader) bbPm.getLoader(CourseDbLoader.TYPE);
				course = courseLoader.loadById(courseIdObject);
				if (course != null) {
					groupID=courseSettings.getProperty("groupid");
					// get the membership data to determine the User's Role
					crsMembershipLoader = (CourseMembershipDbLoader) bbPm.getLoader(CourseMembershipDbLoader.TYPE);
					SetCourseUser(null);
					if (userRole.equals(CourseMembership.Role.GUEST))
						Fail("Course Role","You do not have permission to see this page.");
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

	
	public void SetCourseUser(CourseMembership newMembership) throws PersistenceException {
		try {
			if (newMembership==null) {
				courseUser=user;
				Id sessionUserId = courseUser.getId();
				crsMembership = crsMembershipLoader.loadByCourseAndUserId(courseIdObject, sessionUserId);
			} else {
				courseUser=newMembership.getUser();
				crsMembership = newMembership;
			}
			userID=null;
			userRole=crsMembership.getRole();
			if ( ! userRole.equals(CourseMembership.Role.INSTRUCTOR)
					&& ! userRole.equals(CourseMembership.Role.TEACHING_ASSISTANT)
					&& !userRole.equals(CourseMembership.Role.STUDENT)) {
				userRole=CourseMembership.Role.GUEST;
			}
			isAdministrator=(userRole.equals(CourseMembership.Role.INSTRUCTOR) ||
					userRole.equals(CourseMembership.Role.TEACHING_ASSISTANT));
		} catch (KeyNotFoundException e) {
			// There is no membership record.
			userRole=CourseMembership.Role.GUEST;
			Fail("Course Role","No role found for this user in the course "+e.getMessage());
		}		
	}
	

	public void FindPerceptionGroupID() throws QMWiseException {
		//get Perception group id, make it if it doesn't exist yet
		if (groupID == null && Connect()) {
			try {
				com.questionmark.QMWISe.Group group = stub.getGroupByName(course.getBatchUid());
				groupID = group.getGroup_ID();
				// Check that the phantom user is in the group
				com.questionmark.QMWISe.Group[] groupList=stub.getParticipantGroupList(phantomID);
				int iGroup;
				boolean found=false;
				for(iGroup = 0; iGroup < groupList.length; iGroup++) {
					if(groupList[iGroup].getGroup_ID().equals(groupID)) {
						found=true;
						break;
					}
				}
				if (!found) {
					String[] userIDList=new String[1];
					userIDList[0]=phantomID;
					stub.addGroupParticipantList(groupID,userIDList);
				}
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
					userAdministratorInfo=stub.getAdministratorByName(courseUser.getUserName());
					userID = userAdministratorInfo.getAdministrator_ID();
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
					userParticipantInfo=stub.getParticipantByName(courseUser.getUserName());
					userID=userParticipantInfo.getParticipant_ID();
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
				newuser.setAdministrator_Name(courseUser.getUserName());
				newuser.setPassword(courseUser.getPassword().substring(0, 20));
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
				String userFirstName = replaceSpecChars(courseUser.getGivenName());
				String userLastName = replaceSpecChars(courseUser.getFamilyName());	
				newuser.setFirst_Name(userFirstName);
				newuser.setLast_Name(userLastName); 							
				newuser.setParticipant_Name(courseUser.getUserName());
				newuser.setPassword(courseUser.getPassword().substring(0, 20));
				newuser.setPrimary_Email(courseUser.getEmailAddress());
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
	
	
	public void UpdatePerceptionUser() throws QMWiseException {
		// person exists -- compare and update information as necessary
		boolean update=false;
		// we cannot update administrator information
		if (!isAdministrator) {
			try {
				// Clean out special characters by replacing them with acceptable ones (By Perception)
				String userFirstName = replaceSpecChars(courseUser.getGivenName());
				String userLastName = replaceSpecChars(courseUser.getFamilyName());	
				if (!userParticipantInfo.getFirst_Name().equals(userFirstName)) {
					userParticipantInfo.setFirst_Name(userFirstName);
					update=true;
				}
				if (!userParticipantInfo.getLast_Name().equals(userLastName)) {
					userParticipantInfo.setLast_Name(userLastName);
					update=true;
				}
				String userEmail = courseUser.getEmailAddress();
				if (!userParticipantInfo.getPrimary_Email().equals(userEmail)) {
					userParticipantInfo.setPrimary_Email(userEmail);
					update=true;
				}
				if (update)
					stub.setParticipant(userParticipantInfo);
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
		boolean syncUsers=(pb.getProperty("perception.syncusers")!=null);
		boolean syncMembers=(pb.getProperty("perception.syncmembers")!=null);
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
		} else {
			UpdatePerceptionUser();
			if (!IsGroupMember()) {
				System.out.println("userID="+userID+" is not a member of group "+groupID);
				if (!syncMembers) {
					Fail("Connector Disabled","This tool is not available to you in this course (no group membership in Perception)");
					return false;
				}
				AddToGroup();
			}
		}
		System.out.println("userID="+userID+" is (now) a member of group "+groupID);
		return true;
	}

	
	public String ForceSynchronization() {
		StringBuilder sb = new StringBuilder(4096); // arbitrary 4K chunk
		if (userRole.equals(CourseMembership.Role.INSTRUCTOR) || userRole.equals(CourseMembership.Role.TEACHING_ASSISTANT)) {
			sb.append("Perception: course " + course.getBatchUid() + ": user synchronization forced\n");
			try {
				ArrayList<CourseMembership> allMembershipsList = crsMembershipLoader.loadByCourseId(courseIdObject, null, true);
				ListIterator<CourseMembership> iterator = allMembershipsList.listIterator();
				Hashtable<String,User> adminHash= new Hashtable<String,User>();
				Hashtable<String,User> participantHash= new Hashtable<String,User>();
				// sort course members into admins and participants as we go...
				while(iterator.hasNext()) {
					CourseMembership membership = (CourseMembership) iterator.next();
					SetCourseUser(membership);
					if (isAdministrator) {
						//sb.append(courseUser.getUserName()+" is an administrator on the course\n");
						adminHash.put(courseUser.getUserName(),courseUser);
					} else {
						//sb.append(courseUser.getUserName()+" is a participant on the course\n");
						participantHash.put(courseUser.getUserName(), courseUser);
					}
					if (!Synchronize()) {
						sb.append(courseUser.getUserName()+": "+failTitle+"; "+failText+"\n");
						failTitle=null;
						failText=null;
					}
				}
				// All users are synched into Perception now; return to the current user
				SetCourseUser(null);
				if (pb.getProperty("perception.syncmembers")!=null) {
					// now weed out people from the Perception group who are not in the hashmaps
					Participant pMembers[];
					Administrator aMembers[];
					Vector<String> killList=new Vector<String>();
					pMembers=stub.getParticipantListByGroup(groupID);
					for(int i = 0; i < pMembers.length; i++) {
						Participant p=pMembers[i];
						if (p.getParticipant_ID().equals(phantomID) || participantHash.containsKey(p.getParticipant_Name()))
							continue;
						killList.add(p.getParticipant_ID());
						sb.append(p.getParticipant_Name()+": participant marked for removal\n");
						ScheduleV42[] schedulesarray;
						schedulesarray = stub.getScheduleListByParticipantV42(new Integer(p.getParticipant_ID()).intValue());
						for(int j = 0; j < schedulesarray.length; j++) {
							ScheduleV42 schedule=schedulesarray[j];
							if(schedule.getGroup_ID() == new Integer(groupID).intValue()) {
								try {
									stub.deleteSchedule(new Integer(schedule.getSchedule_ID()).toString());
								} catch (RemoteException e) {
									sb.append(p.getParticipant_Name()+": failed to remove schedule "+schedule.getSchedule_Name()+"\n");
								}
							}
						}
					}
					if (killList.size()>0) {
						String[] killArray = (String[])killList.toArray(new String[killList.size()]);
						stub.deleteGroupParticipantList(groupID,killArray);
						sb.append("Removed "+new Integer(killList.size()).toString()+" participants from Perception group\n");
					}
					killList.clear();
					aMembers=stub.getAdministratorListByGroup(groupID);
					for(int i = 0; i < aMembers.length; i++) {
						Administrator a=aMembers[i];
						if (adminHash.containsKey(a.getAdministrator_Name()))
							continue;
						killList.add(a.getAdministrator_ID());
						sb.append(a.getAdministrator_Name()+": administrator marked for removal\n");
					}
					if (killList.size()>0) {
						String[] killArray = (String[])killList.toArray(new String[killList.size()]);
						stub.deleteGroupAdministratorList(groupID, killArray);
						sb.append("Removed "+new Integer(killList.size()).toString()+" administrators from Perception group\n");						
					}					
					courseSettings.setProperty("lastsync", new Date().toString());
					courseSettings.saveSettingsFile();
				}
			} catch (PersistenceException e) {
				sb.append("Synchronization aborted: "+e.getMessage());
			} catch (RemoteException e) {
				QMWiseException qe=new QMWiseException(e);
				sb.append("Synchronization aborted due to QMWISe error: "+qe.getMessage()+"\n");
			}
		} else {
			Fail("Course Administration","Your role is not authorized to view this page");
		}
		sb.append("Perception: course " + course.getBatchUid() + ": synchronization complete!\n");
		return sb.toString();
	}

	
	@SuppressWarnings("unchecked")
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
	
	
	public SelectAssessmentItem[] GetAssessmentTree(String folderID, String baseLabel) throws QMWiseException {
		/*
		 * This method appears odd at first, it rolls up a hierarchy into a set of individual
		 * assessment nodes interspersed with group nodes that contain a path representation
		 * of the containing folders and an array of child assessments.
		 */
		Vector<SelectAssessmentItem> selectItems=new Vector<SelectAssessmentItem>();
		SelectAssessmentItem[] result=null;
		if (folderID==null)
			folderID="0";
		try {
			AssessmentTreeItem[] treeItems = null;
			treeItems=stub.getAssessmentTreeByAdministrator(userID, folderID, 1);
			for (AssessmentTreeItem treeItem: treeItems) {
				if (treeItem.getType()==0) {
					// a folder
					String groupName=null;
					if (baseLabel==null)
						groupName=treeItem.getName();
					else
						groupName=baseLabel+"/"+treeItem.getName();
					SelectAssessmentItem[] children=GetAssessmentTree(treeItem.getID(),groupName);
					Vector<SelectAssessmentItem> groupChildren=new Vector<SelectAssessmentItem>();
					for (SelectAssessmentItem child: children) {
						if (child.assessmentID==null) {
							// an assessment folder just gets added straight to the parent
							selectItems.add(child);
						} else {
							// an assessment belongs in this 'group'
							groupChildren.add(child);
						}
					}
					if (groupChildren.size()>0) {
						SelectAssessmentItem sGroup=new SelectAssessmentItem(groupName,null);
						sGroup.children=(SelectAssessmentItem[])groupChildren.toArray(new SelectAssessmentItem[groupChildren.size()]);
						selectItems.add(sGroup);
						Arrays.sort(sGroup.children, new SelectAssessmentItem.SortComparator());
					}
				} else {
					// an assessment
					SelectAssessmentItem sItem=new SelectAssessmentItem(treeItem.getName(),treeItem.getID());
					selectItems.add(sItem);
				}
			}
		} catch (RemoteException e) {
			throw new QMWiseException(e);
		}
		result=(SelectAssessmentItem[])selectItems.toArray(new SelectAssessmentItem[selectItems.size()]);
		Arrays.sort(result,new SelectAssessmentItem.SortComparator());
		return result;
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
