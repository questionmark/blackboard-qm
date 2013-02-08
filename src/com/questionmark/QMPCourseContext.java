package com.questionmark;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.ListIterator;
import java.util.Vector;

import java.security.SecureRandom;
import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.holders.StringHolder;

import org.apache.commons.lang.StringEscapeUtils;

import com.questionmark.QMWISe.Administrator;
import com.questionmark.QMWISe.Assessment;
import com.questionmark.QMWISe.AssessmentFolder;
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
import blackboard.persist.user.UserDbLoader;
import blackboard.platform.context.Context;
import blackboard.platform.plugin.PlugInException;

public class QMPCourseContext extends QMPContext {

	// constants for Blackboard role
	public static final int BLACKBOARD_NOBODY=0;
	public static final int BLACKBOARD_STUDENT=1;
	public static final int BLACKBOARD_TA=2;
	public static final String BLACKBOARD_TA_PROFILE="BLACKBOARD TA";
	public static final int BLACKBOARD_INSTRUCTOR=3;
	public static final String BLACKBOARD_INSTRUCTOR_PROFILE="BLACKBOARD INSTRUCTOR";
	
	// constants for Perception role
	public static final int PERCEPTION_NOBODY=0;
	public static final int PERCEPTION_PARTICIPANT=1;
	public static final int PERCEPTION_ADMINISTRATOR=2;
	
	// defined as static as can be slow to start
	protected static SecureRandom random = new SecureRandom(); 
	public String courseId = null;
	public Id courseIdObject = null;
	public CourseSettings courseSettings = null;
	public CourseDbLoader courseLoader = null;
	public CourseMembershipDbLoader crsMembershipLoader = null;
	public Course course = null;
	public String userNameField = null; // if set overrides security checks!
	public User courseUser = null;
	public CourseMembership crsMembership = null;
	public CourseMembership.Role userRole=CourseMembership.Role.GUEST;
	public String groupID=null;
	public String folderID=null;
	public boolean isAdministrator=false;
	public boolean profileCheck=true;
	public boolean taProfile=false;
	public boolean instructorProfile=false;
	public String userID=null;
	Administrator userAdministratorInfo = null;
	Participant userParticipantInfo = null;
	ScheduleV42[] limitedSchedules = null;
	ScheduleV42[] groupSchedules = null;
	public Vector<ScheduleInfo> scheduleInfo=null;
	
	public QMPCourseContext(HttpServletRequest request, Context ctx, String userNameField) {
		super(request,ctx);
		if (failTitle!=null)
			return;
		if (LoadCourseInfo()) {
			this.userNameField=userNameField;
			try {
				SetCourseUser(null);
			} catch (PersistenceException e) {
				Fail("Unexpected PersistenceException",e.getMessage());
			}
		}
	}
	
	
	public QMPCourseContext(HttpServletRequest request, Context ctx) {
		super(request, ctx);
		if (failTitle!=null)
			return;
		if (LoadCourseInfo()) {
			try {
				SetCourseUser(null);
				if (GetBlackboardRole()==BLACKBOARD_NOBODY)
					Fail("Course Role","You do not have permission to see this page.");				
			} catch (PersistenceException e) {
				Fail("Unexpected PersistenceException",e.getMessage());
			}
		}
	}

	
	public boolean LoadCourseInfo() {
		try {
			courseLoader = (CourseDbLoader) bbPm.getLoader(CourseDbLoader.TYPE);
			if (user==null) {
				// a call back - not an authenticated session, uses group name not external id
				courseId = request.getParameter("bb_courseid");
				if (courseId!=null) {
					course = courseLoader.loadByBatchUid(courseId);
					courseIdObject=course.getId();
					courseId=courseIdObject.toExternalString();
				}
			}
			else {
				courseId = request.getParameter("course_id");
				if (courseId!=null) {
					courseIdObject = bbPm.generateId(Course.DATA_TYPE, courseId);
					course = courseLoader.loadById(courseIdObject);
				}
			}
			FindPhantomUserId();
			if(course != null) {
				// load the courseSettings file...
				courseSettings = new CourseSettings(courseId);
				//groupID=courseSettings.getProperty("groupid");
				groupID=PropertiesBean.idCache.get("groupid."+courseId);
				//folderID=courseSettings.getProperty("folderid");
				folderID=PropertiesBean.idCache.get("folderid."+courseId);
				// get the membership data to determine the User's Role
				crsMembershipLoader = (CourseMembershipDbLoader) bbPm.getLoader(CourseMembershipDbLoader.TYPE);
				return true;
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
		return false;
	}
	
	
	public void UpdateSettings(HttpServletRequest request) {
		if (isAdministrator) {
			Boolean hideSchedules = request.getParameter("hide_schedules") != null;
			courseSettings.setProperty("hide_schedules",hideSchedules?"1":"0");
			courseSettings.saveSettingsFile();
		} else {
			Fail("Course Role","You do not have permission to see this page.");
		}
	}
	
	
	public String EnterpriseManagerLink() {
		QMWise q=null;
		try {
			q=QMWise.connect();
			if (Synchronize() && isAdministrator) {
				if (pb.getProperty("perception.singlesignon") != null)
					return q.stub.getAccessAdministrator(courseUser.getUserName());
				else
					Fail("Enterprise Manager","Login to Enterprise Manager has been disabled by the site administrator.");
			} else {
				Fail("Course Role","You do not have permission to see this page.");
			}
		} catch (RemoteException e) {
			QMWiseException qe=new QMWiseException(e);
			FailQMWISe(qe);
		} finally {
			QMWise.close(q);
		}
		return null;
	}
	
	public void SetCourseUser(CourseMembership newMembership) throws PersistenceException {
		try {
			userID=null;
			isAdministrator=false;
			if (newMembership==null) {
				if (user==null) {
					if (userNameField==null)
						throw new KeyNotFoundException("no authenticated user");
					String userName=request.getParameter("Participant_Name");
					if (userName==null)
						throw new KeyNotFoundException("expected "+userNameField);
					UserDbLoader userdbloader = (UserDbLoader) bbPm.getLoader(UserDbLoader.TYPE);
					courseUser=userdbloader.loadByUserName(userName);
					crsMembership=crsMembershipLoader.loadByCourseAndUserId(courseIdObject, courseUser.getId());
				} else {
					courseUser=user;
					Id sessionUserId = courseUser.getId();
					crsMembership = crsMembershipLoader.loadByCourseAndUserId(courseIdObject, sessionUserId);					
				}
			} else {
				courseUser=newMembership.getUser();
				crsMembership = newMembership;
			}
			userRole=crsMembership.getRole();
			if (GetBlackboardRole()==BLACKBOARD_NOBODY)
				userRole=CourseMembership.Role.GUEST;
			isAdministrator=(GetPerceptionRole()==PERCEPTION_ADMINISTRATOR);
		} catch (KeyNotFoundException e) {
			// There is no membership record.
			userRole=CourseMembership.Role.GUEST;
			Fail("Course Role","No role found for this user in the course "+e.getMessage());
		}		
	}
	
	
	public int GetBlackboardRole()
	{
		if (userRole.equals(CourseMembership.Role.INSTRUCTOR))
			return BLACKBOARD_INSTRUCTOR;
		else if (userRole.equals(CourseMembership.Role.TEACHING_ASSISTANT))
			return BLACKBOARD_TA;
		else if (userRole.equals(CourseMembership.Role.STUDENT))
			return BLACKBOARD_STUDENT;
		else
			return BLACKBOARD_NOBODY;
	}
	
	
	public int GetPerceptionRole()
	{
		switch (GetBlackboardRole()) {
		case BLACKBOARD_TA:
		case BLACKBOARD_INSTRUCTOR:
			return PERCEPTION_ADMINISTRATOR;
		case BLACKBOARD_STUDENT:
			return PERCEPTION_PARTICIPANT;
		default:
			return PERCEPTION_NOBODY;
		}
	}
	
	
	public String GetPerceptionProfile() {
		switch (GetBlackboardRole()) {
		case BLACKBOARD_TA:
			return BLACKBOARD_TA_PROFILE;
		case BLACKBOARD_INSTRUCTOR:
			return BLACKBOARD_INSTRUCTOR_PROFILE;
		default:
			return null;
		}
	}

	public void FindPerceptionGroupID() throws QMWiseException {
		//get Perception group id, make it if it doesn't exist yet
		if (groupID == null) {
			QMWise q=null;
			try {
				q=QMWise.connect();
				com.questionmark.QMWISe.Group group = q.stub.getGroupByName(course.getBatchUid());
				groupID = group.getGroup_ID();
				// Check that the phantom user is in the group
				com.questionmark.QMWISe.Group[] groupList=q.stub.getParticipantGroupList(phantomID);
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
					q.stub.addGroupParticipantList(groupID,userIDList);
				}
				// Cache the group ID for future synchronization on this course
				//courseSettings.setProperty("groupid", groupID);
				//courseSettings.saveSettingsFile();
				PropertiesBean.idCache.put("groupid."+courseId, groupID);
			} catch(RemoteException e) {
				QMWiseException qe = new QMWiseException(e);
				if(qe.getQMErrorCode() == 1201) {
					groupID=null;
				} else {
					throw qe;
				}
			} finally {
				QMWise.close(q);
			}
		}
	}

	
	public void CreatePerceptionGroup() throws QMWiseException {
		//group doesn't exist -- make it
		QMWise q=null;
		try {
			q=QMWise.connect();
			com.questionmark.QMWISe.Group newgroup = new com.questionmark.QMWISe.Group();
			newgroup.setParent_ID("0");
			newgroup.setGroup_Name(course.getBatchUid());
			newgroup.setDescription(course.getTitle());
			groupID = q.stub.createGroup(newgroup);
		} catch(RemoteException e) {
			throw new QMWiseException(e);
		} finally {
			QMWise.close(q);
		}
	}

	
	public void FindPerceptionFolderID() throws QMWiseException {
		//get Perception group id, make it if it doesn't exist yet
		if (folderID != null && folderID.length()==0 && pb.getProperty("perception.syncfolders")!=null)
			// sync got turned on? have another go...
			folderID=null;
		if (folderID == null) {
			QMWise q=null;
			try {
				q=QMWise.connect();
				String username=pb.getProperty("perception.username");
				String adminID=userID;
				if (username!=null) {
					// if possible use a powerful user
					Administrator admin=q.stub.getAdministratorByName(username);
					adminID=admin.getAdministrator_ID();
				}
				AssessmentTreeItem[] items=q.stub.getAssessmentTreeByAdministrator(adminID, "0", 0);
				for (AssessmentTreeItem item: items) {
					if (item.getType()==0 && item.getName().equalsIgnoreCase(course.getBatchUid())) {
						folderID=item.getID();
						// Cache the folder ID for future synchronization on this course
						// courseSettings.setProperty("folderid", folderID);
						// courseSettings.saveSettingsFile();
						PropertiesBean.idCache.put("folderid."+courseId, folderID);
						break;
					}
				}
			} catch(RemoteException e) {
				QMWiseException qe = new QMWiseException(e);
				throw qe;
			} finally {
				QMWise.close(q);
			}
		}
	}

	
	public void CreatePerceptionFolder() throws QMWiseException {
		// folder doesn't exist -- make it
		QMWise q=null;
		try {
			q=QMWise.connect();
			AssessmentFolder folder = new AssessmentFolder();
			folder.setParent_ID("0");
			folder.setID("");
			folder.setName(course.getBatchUid());
			folder.setDescription(course.getTitle());
			folderID = q.stub.createAssessmentFolder(folder);
		} catch(RemoteException e) {
			QMWiseException qe = new QMWiseException(e);
			if (qe.getQMErrorCode()==1802) {
				// This means that the folder is there, but we can't know it's ID
				Fail("Connector Configuration Error","QMWISe user does not have permission to see all assessments");
			} else
				throw qe;
		} finally {
			QMWise.close(q);
		}
	}

	
	public void FindPerceptionUserID() throws QMWiseException {
		if (userID == null) {
			QMWise q=null;
			try {
				q=QMWise.connect();
				if (isAdministrator) {
					try {
						userAdministratorInfo=q.stub.getAdministratorByName(courseUser.getUserName());
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
				} else if (GetPerceptionRole()==PERCEPTION_PARTICIPANT) {
					try {
						userParticipantInfo=q.stub.getParticipantByName(courseUser.getUserName());
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
			} finally {
				QMWise.close(q);
			}
		}
	}
	
	
	public void CheckProfiles() throws QMWiseException {
		if (profileCheck) {
			QMWise q=null;
			try {
				q=QMWise.connect();
				//make sure admin profiles exist in Perception 
				//(bug in QMWise causes createAdministrator call 
				//with a non-existent profile name to fail with 
				//a success message)
				String[] profiles = q.stub.getProfileNameList();
				for(int k = 0; k < profiles.length; k++) {
					if(profiles[k].equals(BLACKBOARD_TA_PROFILE)) {
						taProfile=true;
					} else if (profiles[k].equals(BLACKBOARD_INSTRUCTOR_PROFILE)) {
						instructorProfile=true;
					}
				}
			} catch(RemoteException e) {
				throw new QMWiseException(e);
			} finally {
				QMWise.close(q);
			}
			profileCheck=false;
		}
	}
	
	
	public void CreatePerceptionUser() throws QMWiseException {
		// person doesn't exist -- create them
		QMWise q=null;
		try {
			q=QMWise.connect();
			if (isAdministrator) {
				String profile;
				CheckProfiles();
				if (GetBlackboardRole()==BLACKBOARD_INSTRUCTOR) {
					if (! instructorProfile) {
						throw new QMWiseException("Error creating administrator: the profile BLACKBOARD INSTRUCTOR does not exist in Perception");
					}
					profile=BLACKBOARD_INSTRUCTOR_PROFILE;
				} else { // must be BLACKBOARD_TA
					if (! taProfile ) {
						throw new QMWiseException("Error creating administrator: the profile BLACKBOARD TA does not exist in Perception");
					}
					profile=BLACKBOARD_TA_PROFILE;
				}
				try {
					//build new user
					Administrator newuser = new Administrator();
					String email=courseUser.getEmailAddress();
					newuser.setAdministrator_ID("0");
					newuser.setAdministrator_Name(courseUser.getUserName());					
					newuser.setPassword(QMPCourseContext.RandomString(20));
					newuser.setProfile_Name(profile);
					if (email!=null) {
						newuser.setEmail(email);
					}
					//make new user
					userID = q.stub.createAdministrator(newuser);
					AddToGroup();
				} catch(RemoteException e) {
					throw new QMWiseException(e);
				}			
			} else if (GetPerceptionRole()==PERCEPTION_PARTICIPANT) {
				try {
					Participant newuser = new Participant();							
					//Clean out special characters by replacing them with acceptable ones (By Perception)
					String userFirstName = replaceSpecChars(courseUser.getGivenName());
					String userLastName = replaceSpecChars(courseUser.getFamilyName());	
					newuser.setFirst_Name(userFirstName);
					newuser.setLast_Name(userLastName); 							
					newuser.setParticipant_Name(courseUser.getUserName());
					newuser.setPassword(QMPCourseContext.RandomString(20));
					newuser.setPrimary_Email(courseUser.getEmailAddress());
					userID = q.stub.createParticipant(newuser);
					AddToGroup();
				} catch(RemoteException e) {
					QMWiseException qe = new QMWiseException(e);
					if(qe.getQMErrorCode()==4002)
						Log("Illegal character not handled for user "+courseUser.getUserName());
					throw qe;
				}
			}
		} finally {
			QMWise.close(q);
		}
	}
	
	
	public void UpdatePerceptionUser() throws QMWiseException {
		// person exists -- compare and update information as necessary
		boolean update=false;
		// we cannot update administrator information
		if (GetPerceptionRole()==PERCEPTION_PARTICIPANT) {
			QMWise q=null;
			try {
				q=QMWise.connect();
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
					q.stub.setParticipant(userParticipantInfo);
			} catch(RemoteException e) {
				QMWiseException qe = new QMWiseException(e);
				if(qe.getQMErrorCode()==4002)
					Log("Illegal character not handled for user "+courseUser.getUserName());
				throw qe;
			} finally {
				QMWise.close(q);
			}			
		}
	}
	
	
	public boolean IsGroupMember() throws QMWiseException {
		boolean found=false;
		QMWise q=null;
		try {
			q=QMWise.connect();
			com.questionmark.QMWISe.Group[] groupList;
			if (isAdministrator)
				groupList=q.stub.getAdministratorGroupList(userID);
			else if (GetPerceptionRole()==PERCEPTION_PARTICIPANT)
				groupList=q.stub.getParticipantGroupList(userID);
			else
				return false;
			int iGroup;
			for(iGroup = 0; iGroup < groupList.length; iGroup++) {
				if(groupList[iGroup].getGroup_ID().equals(groupID)) {
					found=true;
					break;
				}
			}
		} catch (RemoteException e) {
			throw new QMWiseException(e);
		} finally {
			QMWise.close(q);
		}
		return found;
	}
	
	
	public void AddToGroup() throws QMWiseException {
		String[] userIDList=new String[1];
		userIDList[0]=userID;
		QMWise q=null;
		try {
			q=QMWise.connect();
			if (isAdministrator) {
				q.stub.addGroupAdministratorList(groupID, userIDList);
			} else if (GetPerceptionRole()==PERCEPTION_PARTICIPANT) {
				q.stub.addGroupParticipantList(groupID,userIDList);
				//get schedules from phantom user
				ScheduleV42[] schedulesarray;
				schedulesarray = q.stub.getScheduleListByParticipantV42(new Integer(phantomID).intValue());
				for(int i = 0; i < schedulesarray.length; i++) {
					ScheduleV42 schedule=schedulesarray[i];
					if(schedule.getGroup_ID() == new Integer(groupID).intValue()) {
						schedule.setParticipant_ID(new Integer(userID).intValue());					
						q.stub.createScheduleParticipantV42(schedule);
					}
				}
			}
		} catch (RemoteException e) {
			// groupID could have gone bad here; clear it from the cache
			PropertiesBean.idCache.remove("groupid."+courseId);
			throw new QMWiseException(e);
		} finally {
			QMWise.close(q);
		}
	}
	
	
	public void AddToFolder() throws QMWiseException {
		QMWise q=null;
		try {
			q=QMWise.connect();
			if (isAdministrator && folderID!=null && folderID.length()>0) {
				StringHolder permissions=new StringHolder();
				if (GetBlackboardRole()==BLACKBOARD_INSTRUCTOR)
					permissions.value="3";
				else
					permissions.value="1";
				q.stub.assignAdministratorToAssessmentFolder(userID, folderID, permissions);
			}
		} catch (RemoteException e) {
			// folderID could have gone bad here; clear it from the cache
			PropertiesBean.idCache.remove("folderid."+courseId);
			throw new QMWiseException(e);
		} finally {
			QMWise.close(q);
		}
	}
	
	
	public Boolean Synchronize() throws QMWiseException {
		return Synchronize(false);
	}
	
	public Boolean Synchronize(boolean forceFolder) throws QMWiseException {
		boolean syncUsers=(pb.getProperty("perception.syncusers")!=null);
		boolean syncMembers=(pb.getProperty("perception.syncmembers")!=null);
		boolean syncFolders=(pb.getProperty("perception.syncfolders")!=null);
		FindPerceptionGroupID();
		if (groupID == null) {
			if (pb.getProperty("perception.syncgroups")==null) {
				FailAccess("This tool is not enabled for use with this course (no corresponding group in Perception)");
				return false;
			}
			CreatePerceptionGroup();
		}
		FindPerceptionFolderID();
		if (folderID == null) {
			if (syncFolders) {
				CreatePerceptionFolder();
				forceFolder=true;
			} else {
				// save an empty string to prevent the check each time
				folderID="";
				// courseSettings.setProperty("folderid", folderID);
				// courseSettings.saveSettingsFile();
				PropertiesBean.idCache.put("folderid."+courseId, folderID);
			}
		}
		if (GetPerceptionRole()!=PERCEPTION_NOBODY) {
			FindPerceptionUserID();
			Log("Found userID "+userID+" ("+courseUser.getUserName()+")");
			if (userID == null) {
				if (!syncUsers) {
					FailAccess("This tool is not available to you (no corresponding user in Perception)");
					return false;
				}
				CreatePerceptionUser();
				forceFolder=true;
			} else {
				if (syncUsers)
					UpdatePerceptionUser();
				if (!IsGroupMember()) {
					Log("userID="+userID+" is not a member of group "+groupID+" ("+course.getBatchUid()+")");
					if (!syncMembers) {
						FailAccess("This tool is not available to you in this course (no group membership in Perception)");
						return false;
					}
					AddToGroup();
					forceFolder=true;
				}
			}
			if (syncFolders && forceFolder)
				AddToFolder();
			Log("userID="+userID+" is (now) a member of group "+groupID+" ("+course.getBatchUid()+")");
		}
		return true;
	}

	
	public String ForceSynchronization() {
		StringBuilder sb = new StringBuilder(4096); // arbitrary 4K chunk
		boolean syncmembers=(pb.getProperty("perception.syncmembers")!=null);
		boolean syncfolders=(pb.getProperty("perception.syncfolders")!=null && folderID!=null && folderID.length()>0);
		QMWise q=null;
		try {
			q=QMWise.connect();
			if (isAdministrator) {
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
						} else if (GetPerceptionRole()==PERCEPTION_PARTICIPANT) {
							//sb.append(courseUser.getUserName()+" is a participant on the course\n");
							participantHash.put(courseUser.getUserName(), courseUser);
						} else
							continue;
						if (!Synchronize(true)) {
							sb.append(courseUser.getUserName()+": "+failTitle+"; "+failMsg+"\n");
							failTitle=null;
							failMsg=null;
						}
					}
					// All users are synched into Perception now; return to the current user
					SetCourseUser(null);
					if (syncmembers) {
						// now weed out people from the Perception group who are not in the hashmaps
						Participant pMembers[];
						Vector<String> killList=new Vector<String>();
						pMembers=q.stub.getParticipantListByGroup(groupID);
						for(int i = 0; i < pMembers.length; i++) {
							Participant p=pMembers[i];
							if (p.getParticipant_ID().equals(phantomID) || participantHash.containsKey(p.getParticipant_Name()))
								continue;
							killList.add(p.getParticipant_ID());
							sb.append(p.getParticipant_Name()+": participant marked for removal\n");
							ScheduleV42[] schedulesarray;
							schedulesarray = q.stub.getScheduleListByParticipantV42(new Integer(p.getParticipant_ID()).intValue());
							for(int j = 0; j < schedulesarray.length; j++) {
								ScheduleV42 schedule=schedulesarray[j];
								if(schedule.getGroup_ID() == new Integer(groupID).intValue()) {
									try {
										q.stub.deleteSchedule(new Integer(schedule.getSchedule_ID()).toString());
									} catch (RemoteException e) {
										sb.append(p.getParticipant_Name()+": failed to remove schedule "+schedule.getSchedule_Name()+"\n");
									}
								}
							}
						}
						if (killList.size()>0) {
							String[] killArray = (String[])killList.toArray(new String[killList.size()]);
							q.stub.deleteGroupParticipantList(groupID,killArray);
							sb.append("Removed "+new Integer(killList.size()).toString()+" participants from Perception group\n");
						}
					}
					Administrator aMembers[];
					Vector<String> killList=new Vector<String>();
					aMembers=q.stub.getAdministratorListByGroup(groupID);
					for(int i = 0; i < aMembers.length; i++) {
						Administrator a=aMembers[i];
						if (adminHash.containsKey(a.getAdministrator_Name()))
							continue;
						killList.add(a.getAdministrator_ID());
						if (syncmembers)
							sb.append(a.getAdministrator_Name()+": administrator marked for removal\n");
						if (syncfolders) {
							StringHolder permissions=new StringHolder();
							permissions.value="0";
							q.stub.assignAdministratorToAssessmentFolder(a.getAdministrator_ID(), folderID, permissions);
						}
					}
					if (syncmembers) {
						if (killList.size()>0) {
							String[] killArray = (String[])killList.toArray(new String[killList.size()]);
							q.stub.deleteGroupAdministratorList(groupID, killArray);
							sb.append("Removed "+new Integer(killList.size()).toString()+" administrators from Perception group\n");						
						}					
					}
					courseSettings.setProperty("lastsync", new Date().toString());
					courseSettings.saveSettingsFile();
				} catch (PersistenceException e) {
					sb.append("Synchronization aborted: "+e.getMessage());
				} catch (RemoteException e) {
					QMWiseException qe=new QMWiseException(e);
					sb.append("Synchronization aborted due to QMWISe error: "+qe.getMessage()+"\n");
				}
			} else {
				Fail("Course Administration","Your role is not authorized to view this page");
			}
		} catch (QMWiseException e) {
			sb.append("Synchronization aborted due to QMWISe error: "+e.getMessage()+"\n");
		} finally {
			QMWise.close(q);
		}
		sb.append("Perception: course " + course.getBatchUid() + ": synchronization complete!\n");
		return sb.toString();
	}

	
//	@SuppressWarnings("unchecked")
//	public Assessment[] GetAssessments() throws QMWiseException {
//		QMWise q=null;
//		try {
//			q=QMWise.connect();
//			Assessment[] assessments = null;
//			if (isAdministrator) {
//				try {				
//					assessments = q.stub.getAssessmentListByAdministrator(userID);
//				} catch (RemoteException e) {
//					throw new QMWiseException(e);
//				}			
//			}
//			Arrays.sort(assessments, new AssessmentComparator());
//			return assessments;
//		} finally {
//			QMWise.close(q);
//		}
//	}
	
	
	public SelectAssessmentItem[] GetAssessmentList() throws QMWiseException {
		Vector<SelectAssessmentItem> selectItems=new Vector<SelectAssessmentItem>();
		SelectAssessmentItem[] result=null;
		QMWise q=null;
		try {
			q=QMWise.connect();
			Assessment[] assessments = null;
			assessments=q.stub.getAssessmentListByAdministrator(userID);
			for (Assessment item: assessments) {
				SelectAssessmentItem sItem=new SelectAssessmentItem(item.getSession_Name(),item.getAssessment_ID());
				selectItems.add(sItem);
			}
		} catch (RemoteException e) {
			throw new QMWiseException(e);
		} finally {
			QMWise.close(q);
		}
		result=(SelectAssessmentItem[])selectItems.toArray(new SelectAssessmentItem[selectItems.size()]);
		Arrays.sort(result,new SelectAssessmentItem.SortComparator());
		return result;
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
		QMWise q=null;
		try {
			q=QMWise.connect();
			AssessmentTreeItem[] treeItems = null;
			treeItems=q.stub.getAssessmentTreeByAdministrator(userID, folderID, 1);
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
		} finally {
			QMWise.close(q);
		}
		result=(SelectAssessmentItem[])selectItems.toArray(new SelectAssessmentItem[selectItems.size()]);
		Arrays.sort(result,new SelectAssessmentItem.SortComparator());
		return result;
	}

	
	public Vector<ScheduleV42> GroupSchedules(String filter) throws QMWiseException {
		return GroupSchedules(filter,null);
	}

	
	public Vector<ScheduleV42> GroupSchedules(String filter, Id contentID) throws QMWiseException {
		Vector<ScheduleV42> schedules = new Vector<ScheduleV42>();
		QMWise q=null;
		try {
			q=QMWise.connect();
			String idFilter=null;
			if (contentID!=null)
				idFilter=contentID.toExternalString();
			// Assumption:
			// number of courses x number of schedules is less than...
			// max(number of students in a course) x number of limited attempt schedules in that course
			// Either way we would prefer a method that also filtered by group
			if (limitedSchedules==null) {
				if (isAdministrator) {
					limitedSchedules = q.stub.getScheduleListByParticipantV42(new Integer(phantomID).intValue());
				} else if (GetPerceptionRole()==PERCEPTION_PARTICIPANT) {
					limitedSchedules = q.stub.getScheduleListByParticipantV42(new Integer(userID).intValue());
				} else {
					limitedSchedules = new ScheduleV42[0];
				}
			}
			// Not sure how well documented using 0 is here
			// returns approx (number of courses x number of group schedules) records
			if (groupSchedules==null)
				groupSchedules=q.stub.getScheduleListByParticipantV42(0);
			if (idFilter!=null) {
				// First pass; match against the contentID magic prefix
				for(int i = 0; i < limitedSchedules.length; i++) {
					if(limitedSchedules[i].getGroup_ID() == new Integer(groupID).intValue() &&
							QMPContentItem.ExtractContentId(limitedSchedules[i].getSchedule_Name()).equals(idFilter))
						schedules.add(limitedSchedules[i]);
				}				
				for(int i = 0; i < groupSchedules.length; i++) {
					if(groupSchedules[i].getGroup_ID() == new Integer(groupID).intValue() &&
							QMPContentItem.ExtractContentId(groupSchedules[i].getSchedule_Name()).equals(idFilter))
						schedules.add(groupSchedules[i]);
				}
			}
			if (schedules.size()==0) {
				// We didn't find any schedules, match the whole name (or everything) on a second pass
				for(int i = 0; i < limitedSchedules.length; i++) {
					if(limitedSchedules[i].getGroup_ID() == new Integer(groupID).intValue())
						if (filter == null || filter.equals(limitedSchedules[i].getSchedule_Name()))
							schedules.add(limitedSchedules[i]);
				}
				for(int i = 0; i < groupSchedules.length; i++) {
					if(groupSchedules[i].getGroup_ID() == new Integer(groupID).intValue())
						if (filter == null || filter.equals(groupSchedules[i].getSchedule_Name()))
							schedules.add(groupSchedules[i]);
				}
			}
		} catch (RemoteException e) {
			throw new QMWiseException(e);
		} finally {
			QMWise.close(q);
		}
		return schedules;
	}
	
	
	public void GetScheduleInfo(Vector<ScheduleV42> schedules) {
		GetScheduleInfo(schedules,null);
	}
	
		
	public void GetScheduleInfo(Vector<ScheduleV42> schedules, String contentName) {
		scheduleInfo = new Vector<ScheduleInfo>();
		for(int i = 0; i < schedules.size(); i++)
			scheduleInfo.add(new ScheduleInfo(this,schedules.get(i),contentName,isAdministrator));
		Collections.sort(scheduleInfo,new ScheduleInfo.SortComparator());
	}

	public void FailQMWISe(QMWiseException e) {
		Fail("Communication Error with Perception Server",e.getMessage());
	}
	
	public void FailCourse() {
		Fail("Course Not Found","There was no course associated with this request");
	}

	public void FailAccess(String msg) {
		String link=pb.getProperty("perception.accesslink");
		msg=StringEscapeUtils.escapeHtml(msg);
		if (link!=null && link.length()>0)
			msg=msg+"  <a href=\""+StringEscapeUtils.escapeHtml(link)+"\">More information...</a>";
		FailRaw("Connector Disabled",msg);
	}

	public static String RandomString(int nChars) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<nChars;i++) {
			sb.append(Integer.toString(QMPCourseContext.random.nextInt(32),32));
		}
		return sb.toString();
	}


}
