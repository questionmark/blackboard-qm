package com.questionmark;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Vector;

import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.persist.BbPersistenceManager;
import blackboard.persist.Id;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.platform.persistence.PersistenceServiceFactory;

import com.questionmark.QMWISe.Administrator;
import com.questionmark.QMWISe.Participant;
import com.questionmark.QMWISe.ScheduleV42;

public class UserSynchronizer {
	public UserSynchronizer() {
	}

	/**
	 * getPhantomUserId
	 * Get the ID of the phantom user
	 * The phantom user is a user who is a participant in every Blackboard 
	 * course group. This is necessary to ensure there is always at least one 
	 * participant who is scheduled for each test, allowing us to easily get a 
	 * list of all assessments scheduled for any given group, which in turn 
	 * allows us to add new schedules for new users.
	 * If the phantom user doesn't already exist, it is created as part of this 
	 * method.
	 */
	static public String getPhantomUserId() throws Exception {
		QMWise qmwise;
		String id;
		try {
			qmwise = new QMWise();
		} catch(Exception e) {
			throw e;
		}

		try {
			id = qmwise.getStub().getParticipantByName("bb-phantom").getParticipant_ID();
			//phantom exists -- return his id
			return id;
		} catch(Exception e) {
			QMWiseException qe = new QMWiseException(e);
			if(qe.getQMErrorCode() == 1101) {
				//user doesn't exist
				try {
					Participant newuser = new Participant();
					newuser.setFirst_Name("Phantom");
					newuser.setLast_Name("User");
					newuser.setParticipant_Name("bb-phantom");
					newuser.setPassword("bb-phantom");
					id = qmwise.getStub().createParticipant(newuser);
					//we have id of new phantom user -- return it
					return id;
				} catch(Exception ne) {
					QMWiseException nqe = new QMWiseException(e);
					throw new Exception("Error creating Perception user. " + nqe.getMessage());
				}
			} else {//exception but not "user doesn't exist"
				throw new Exception("Error getting Perception user. " + qe.getMessage());
			}
		}
	}


	public String synchronizeCourse(String courseId) throws Exception {

		QMWise qmwise;
		int added = 0;
		int removed = 0;
		try {
			qmwise = new QMWise();
		} catch(Exception e) {
			throw e;
		}

		//Retrieve the Db persistence manager from the persistence service
		
		BbPersistenceManager bbPm = PersistenceServiceFactory.getInstance().getDbPersistenceManager();

		// Generate a persistence framework course Id to be used for loading the 
		// course
		Id sessionCourseId = bbPm.generateId(Course.DATA_TYPE, courseId);

		// Load the course
		CourseDbLoader courseLoader = (CourseDbLoader) bbPm.getLoader(CourseDbLoader.TYPE);
		Course course = courseLoader.loadById(sessionCourseId);

		// retrieve the CourseMembership data for this course
		CourseMembershipDbLoader crsMembershipLoader = (CourseMembershipDbLoader) bbPm.getLoader(CourseMembershipDbLoader.TYPE);
		ArrayList allMembershipsList = crsMembershipLoader.loadByCourseId(sessionCourseId, null, true);
		ListIterator iterator = allMembershipsList.listIterator();

		//sort course members into admins and participants
		Vector<CourseMembership> bbadministrators = new Vector<CourseMembership>();
		Vector<CourseMembership> bbparticipants = new Vector<CourseMembership>();
		while(iterator.hasNext()) {
			CourseMembership membership = (CourseMembership) iterator.next();
			if(membership.getRole() == CourseMembership.Role.INSTRUCTOR
			|| membership.getRole() == CourseMembership.Role.TEACHING_ASSISTANT) {
				bbadministrators.add(membership);
			} else {
				bbparticipants.add(membership);
			}
		}

		//get Perception group id, make it if it doesn't exist yet
		String groupID;
		try {
			com.questionmark.QMWISe.Group group = qmwise.getStub().getGroupByName(course.getBatchUid());
			groupID = group.getGroup_ID();
		} catch(Exception e) {
			QMWiseException qe = new QMWiseException(e);
			if(qe.getQMErrorCode() == 1201) {
				//group doesn't exist -- make it
				try {
					com.questionmark.QMWISe.Group newgroup = new com.questionmark.QMWISe.Group();
					newgroup.setParent_ID("0");
					newgroup.setGroup_Name(course.getBatchUid());
					newgroup.setDescription(course.getTitle());
					groupID = qmwise.getStub().createGroup(newgroup);
				} catch(Exception ne) {
					QMWiseException nqe = new QMWiseException(ne);
					//We don't want a high level exception to be thrown to main..
					//throw new Exception("Error creating Perception group. " + nqe.getMessage());
					throw nqe;
				}
			} else {
				//throw new Exception("Error getting Perception group. " + qe.getMessage());
				throw qe;
			}
		}

		/*-------------------participants------------------------*/
		//get group participants from Perception
		Participant tempparticipants[];
		try {
			tempparticipants = qmwise.getStub().getParticipantListByGroup(groupID);
		} catch(Exception e) {
			QMWiseException qe = new QMWiseException(e);
			throw new Exception("Error getting Perception group participants. " + qe.getMessage());
		}
		Vector<Participant> qmpparticipants = new Vector<Participant>();
		for(int i = 0; i < tempparticipants.length; i++) {
			qmpparticipants.add(tempparticipants[i]);
		}

		Vector<String> participantstoadd = new Vector<String>();

		for(int i = 0; i < bbparticipants.size(); i++) {
			CourseMembership currentmembership = bbparticipants.get(i);

			boolean ingroup = false;
			int j;
			for(j = 0; j < qmpparticipants.size(); j++) {
				if(currentmembership.getUser().getUserName().equals(qmpparticipants.get(j).getParticipant_Name())) {
					ingroup = true;
					break;
				}
			}
			if(ingroup) {
				qmpparticipants.removeElementAt(j);
				//this leaves qmpparticipants as an array of users who were 
				//in the Perception group but should not be
			} else {
				String id;
				try {
					id = qmwise.getStub().getParticipantByName(currentmembership.getUser().getUserName()).getParticipant_ID();
					//we have id of existing user but who isn't in the group yet
				} catch(Exception e) {
					QMWiseException qe = new QMWiseException(e);
					if(qe.getQMErrorCode() == 1101) {
						//user doesn't exist
						try {
							Participant newuser = new Participant();							

							//Clean out special characters by replacing them with acceptable ones (By Perception)
							
							String userFirstName = replaceSpecChars(currentmembership.getUser().getGivenName());
							String userLastName = replaceSpecChars(currentmembership.getUser().getFamilyName());	
							
							//test:
							//System.out.println(userFirstName);	
							//System.out.println(userLastName);
							
							
							newuser.setFirst_Name(userFirstName);
							newuser.setLast_Name(userLastName); 							

							
							newuser.setParticipant_Name(currentmembership.getUser().getUserName());
							newuser.setPassword(currentmembership.getUser().getPassword().substring(0, 20));
							id = qmwise.getStub().createParticipant(newuser);
							//we have id of new user who needs to be added to 
							//the group
						} catch(Exception ne) {
							QMWiseException nqe = new QMWiseException(e);
							
							if(nqe.getQMErrorCode()==4002) System.out.println("Illegal character not handled");
							//throw new Exception("Error creating Perception user. " + nqe.getMessage());
							//reaches here..throws it back.
							throw nqe;
						}
					} else {//exception but not "user doesn't exist"
						//throw new Exception("Error getting Perception user. " + qe.getMessage());
						throw qe;
					}
				}
				participantstoadd.add(id);
			}
		}//done each participant of this course listed by Blackboard

		//determine whether phantom user is already in the group
		boolean hasphantom = false;
		for(int i = 0; i < qmpparticipants.size(); i++) {
			if(qmpparticipants.get(i).getParticipant_ID().equals(getPhantomUserId())) {
				hasphantom = true;
				break;
			}
		}

		//remove participants from Perception group who shouldn't be there
		try {
			if(hasphantom && qmpparticipants.size() > 1 || !hasphantom && qmpparticipants.size() > 0) {
				String[] participantstoremove = new String[qmpparticipants.size() - (hasphantom ? 1 : 0)];

				int pastphantom = 0;
				for(int i = 0; i < qmpparticipants.size(); i++) {
					//don't remove phantom user
					if(pastphantom == 0 && qmpparticipants.get(i).getParticipant_ID().equals(getPhantomUserId())) {
						pastphantom = 1;
						continue;
					}
					participantstoremove[i - pastphantom] = qmpparticipants.get(i).getParticipant_ID();
				}

				qmwise.getStub().deleteGroupParticipantList(groupID, participantstoremove);
				removed += participantstoremove.length;
			}
		} catch(Exception e) {
			QMWiseException qe = new QMWiseException(e);
			throw new Exception("Error removing Perception participants from group. " + qe.getMessage());
		}

		//add participants to Perception group who should be there
		try {
			if(!hasphantom || participantstoadd.size() > 0) {
				String[] participantstoaddarray = new String[participantstoadd.size() + (hasphantom ? 0 : 1)];

				if(participantstoadd.size() > 0) {
					//get schedules from phantom user
					ScheduleV42[] schedulesarray;
					try {
						schedulesarray = qmwise.getStub().getScheduleListByParticipantV42(new Integer(UserSynchronizer.getPhantomUserId()).intValue());
					} catch(Exception e) {
						QMWiseException qe = new QMWiseException(e);
						throw new Exception("Error getting group schedule list to add to new user. " + qe.getMessage());
					}

					Vector<ScheduleV42> schedules = new Vector<ScheduleV42>();

					for(int i = 0; i < schedulesarray.length; i++) {
						if(schedulesarray[i].getGroup_ID() == new Integer(groupID).intValue())
							schedules.add(schedulesarray[i]);
					}

					for(int i = 0; i < participantstoadd.size(); i++) {
						participantstoaddarray[i] = participantstoadd.get(i);
						try {
							for(int j = 0; j < schedules.size(); j++) {
								schedules.get(j).setParticipant_ID(new Integer(participantstoadd.get(i)).intValue());
								qmwise.getStub().createScheduleParticipantV42(schedules.get(j));
							}
						} catch(Exception e) {
							QMWiseException qe = new QMWiseException(e);
							throw new Exception("Error adding schedule for new course participant. " + qe.getMessage());
						}
					}
				}
				if(!hasphantom) {
					participantstoaddarray[participantstoaddarray.length - 1] = getPhantomUserId();
				}

				qmwise.getStub().addGroupParticipantList(groupID, participantstoaddarray);
				added += participantstoadd.size();
			}
		} catch(Exception e) {
			QMWiseException qe = new QMWiseException(e);
			throw new Exception("Error adding Perception participants to group. " + qe.getMessage());
		}

		/*-------------------administrators------------------------*/
		//get group administrators from Perception
		Administrator tempadministrators[];
		try {
			tempadministrators = qmwise.getStub().getAdministratorListByGroup(groupID);
		} catch(Exception e) {
			QMWiseException qe = new QMWiseException(e);
			throw new Exception("Error getting Perception group administrators. " + qe.getMessage());
		}
		Vector<Administrator> qmpadministrators = new Vector<Administrator>();
		for(int i = 0; i < tempadministrators.length; i++) {
			qmpadministrators.add(tempadministrators[i]);
		}

		Vector<String> administratorstoadd = new Vector<String>();

		for(int i = 0; i < bbadministrators.size(); i++) {
			CourseMembership currentmembership = bbadministrators.get(i);

			boolean ingroup = false;
			int j;
			for(j = 0; j < qmpadministrators.size(); j++) {
				if(currentmembership.getUser().getUserName().equals(qmpadministrators.get(j).getAdministrator_Name())) {
					ingroup = true;
					break;
				}
			}
			if(ingroup) {
				qmpadministrators.removeElementAt(j);
				//this leaves qmpadministrators as an array of users who 
				//were in the Perception group but should not be
			} else {
				String id;
				try {
					id = qmwise.getStub().getAdministratorByName(currentmembership.getUser().getUserName()).getAdministrator_ID();
					//we have id of existing user but who isn't in the group yet
				} catch(Exception e) {
					QMWiseException qe = new QMWiseException(e);
					if(qe.getQMErrorCode() == 1601) {
						//user doesn't exist
						try {
							//make sure admin profiles exist in Perception 
							//(bug in QMWise causes createAdministrator call 
							//with a non-existent profile name to fail with 
							//a success message)
							String[] profiles = qmwise.getStub().getProfileNameList();
							boolean found;

							//Blackboard TA
							found = false;
							for(int k = 0; k < profiles.length; k++) {
								if(profiles[k].equals("BLACKBOARD TA")) {
									found = true;
									continue;
								}
							}
							if(!found)
								throw new Exception("Error creating Perception admin user. The profile \"Blackboard TA\" does not exist on the Perception server -- this must be set up before using the Questionmark Perception connector. Consult the installation instructions.");

							//Blackboard Instructor
							found = false;
							for(int k = 0; k < profiles.length; k++) {
								if(profiles[k].equals("BLACKBOARD INSTRUCTOR")) {
									found = true;
									continue;
								}
							}
							if(!found)
								throw new Exception("Error creating Perception admin user. The profile \"Blackboard Instructor\" does not exist on the Perception server -- this must be set up before using the Questionmark Perception connector. Consult the installation instructions.");
						} catch(Exception ne) {
							QMWiseException nqe = new QMWiseException(ne);
							throw new Exception("Error getting Perception administrator profile list. " + nqe.getMessage());
						}

						try {
							//build new user
							Administrator newuser = new Administrator();
							newuser.setAdministrator_Name(currentmembership.getUser().getUserName());
							newuser.setPassword(currentmembership.getUser().getPassword().substring(0, 20));
							newuser.setProfile_Name(currentmembership.getRole() == CourseMembership.Role.INSTRUCTOR ? "Blackboard Instructor" : "Blackboard TA");

							//make new user
							id = qmwise.getStub().createAdministrator(newuser);
							//we have id of new user who needs to be added to 
							//the group
						} catch(Exception ne) {
							QMWiseException nqe = new QMWiseException(ne);
							throw new Exception("Error creating Perception admin user. " + nqe.getMessage());
						}
					} else {
						throw new Exception("Error getting Perception admin user. " + qe.getMessage());
					}
				}
				administratorstoadd.add(id);
			}
		}//done each administrator of this course listed by Blackboard

		//remove administrators from Perception group who shouldn't be there
		try {
			if(qmpadministrators.size() > 0) {
				String[] administratorstoremove = new String[qmpadministrators.size()];
				for(int i = 0; i < qmpadministrators.size(); i++) {
					administratorstoremove[i] = qmpadministrators.get(i).getAdministrator_ID();
				}
				qmwise.getStub().deleteGroupAdministratorList(groupID, administratorstoremove);
				removed += qmpadministrators.size();
			}
		} catch(Exception e) {
			QMWiseException qe = new QMWiseException(e);
			throw new Exception("Error removing Perception administrators from group. " + qe.getMessage());
		}

		//add administrators to Perception group who should be there
		try {
			if(administratorstoadd.size() > 0) {
				String[] administratorstoaddarray = new String[administratorstoadd.size()];
				for(int i = 0; i < administratorstoadd.size(); i++) {
					administratorstoaddarray[i] = administratorstoadd.get(i);
				}
				qmwise.getStub().addGroupAdministratorList(groupID, administratorstoaddarray);
				added += administratorstoadd.size();
			}
		} catch(Exception e) {
			QMWiseException qe = new QMWiseException(e);
			throw new Exception("Error adding Perception administrators to group. " + qe.getMessage());
		}

		return "Success: " + added + " user" + (added == 1 ? "" : "s") + " added and " + removed + " user" + (removed == 1 ? "" : "s") + " removed from Perception group";
		
	}

	//Static routine to help handle invalid special characters in the Blackboard user details, which would break Perception
	private static String replaceSpecChars (String replaceString){
		
		/*Search replace routine..remove special characters
		 * In summary:
		 * 		' " \ / all become space
		 * 		, | : all become ;
		 * 		£ goes to # - Perception won't allow £.
		 *		<> go to [] - BB doesn't allow these in names
		 * 		& goes to -
		*/	
		StringBuilder sb = new StringBuilder(replaceString.length());
		
        for (int i = 0; i < replaceString.length(); i++) 
        {        	        	
        	switch(replaceString.charAt(i)){
        	
	        	case '\'':
	        	case '\"':
	        	case '\\':
	        	case '/':
	        		sb.append(' ');
	        		break;
	        	
	        	case ',':
	        	case '|':
	        	case ':':
	        		sb.append(';');
	        		break;
	        	
	        	case '£':
	        		sb.append('#');
	        		break;
	        		
	        	case '<':
	        		sb.append('[');
	        		break;
	        	
	        	case '>':
	        		sb.append(']');
	        		break;
	        	
	        	case '&':
	        		sb.append('-');
	        		break;
	        	
	        	default:
	        		sb.append(replaceString.charAt(i));
	        		break;
        	}  
        } 
        
        return sb.toString();

		
	}
}
