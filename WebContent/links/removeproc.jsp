<!-- 
 Filename		: content/remove.jsp
 Description	: Runs when deleting a content item: 
 					- View schedule code to generate the schedules view and pick out the schedule name matching the content 
 						item name.
 					- Once the schedule is picked out store that schedule's id to be sent off to QMWISe delete method.
 					- Connect to qmwise again with delete schedule routine for that particular schedule's id.
					- Delete the schedule line item from BB Grade Center, if the column by the schedule name exists.
					- Continue with deletion of content item. The deletion happens anyway this script allows
						cleanup tasks to be taken care of.				  

-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	import="java.util.*,
	java.text.*,
	blackboard.platform.*,
	blackboard.platform.session.*,
	blackboard.platform.persistence.*,
	blackboard.platform.plugin.*,
	blackboard.platform.security.authentication.*,
	blackboard.persist.*,
	blackboard.persist.user.*,
	blackboard.persist.course.*,
	blackboard.persist.content.*,
	blackboard.persist.content.ContentDbLoader,
	blackboard.persist.Id,blackboard.data.course.*,
	blackboard.data.user.*,
	blackboard.data.content.*,
	blackboard.base.*,
	blackboard.data.gradebook.*,
	blackboard.persist.gradebook.*,
	org.apache.axis.*,
	org.apache.commons.lang.StringEscapeUtils,
	java.rmi.RemoteException,
	javax.xml.namespace.QName,
	com.questionmark.*,
	com.questionmark.QMWISe.*"
	pageEncoding="ISO-8859-1"
	%>
	
    
<%@ taglib uri="/bbNG" prefix="bbNG" %>
<%@ taglib uri="/bbData" prefix="bbData" %>

<bbNG:jspBlock>

<%	

		
	//Authenticate for use.	
	if (!PlugInUtil.authorizeForCourseControlPanel(request, response)) {
		//If user is not authorised to see this page then return blank page
		
		%>	<h1>You are not authorised to view this page</h1>			
		<%
		
		//Stop the script
		return;
		
	}	

%>


<bbNG:learningSystemPage ctxId="contentCtx">


<% 
	//Declare members.
	
	QMWise qmwise;
	int perceptiongroupid = 0, schedule_id; 
	
	String group_name;
	
	//Get context path.
	String path = request.getContextPath();
	
	//Construct base url.
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
	
	//Get a User , course membership details, and Course instance via context
	User sessionUser = contentCtx.getUser();
	Id sessionUserId = sessionUser.getId();
	Course courseCtx = contentCtx.getCourse();	
	CourseMembership cMembership = contentCtx.getCourseMembership(); // may be null if a SysAdmin!	
	
	//Get course id and content id from within the context of the content item.
	String course_id = contentCtx.getCourseId().toExternalString();

	
	//Get schedule name from request object reference to start the deletion process
	String schedule_name = request.getParameter("schedule_name");
	
	//Test 
	System.out.println("Coursedoc name - Schedule name is: " + schedule_name);
		

	//create a ConfigFileReader, to check whether this course needs 
	//to sync its members and to show date last synchronized
	ConfigFileReader configReader = new ConfigFileReader(course_id);
	//load the courseSettings file too...
	CourseSettings courseSettings = new CourseSettings(course_id);
		
	
	//connect to QMWise
	
	try {
		qmwise = new QMWise();
	} catch(Exception e) {
		QMWiseException qe = new QMWiseException(e);
		%>	
			<bbNG:receipt type="FAIL" title="Error connecting to Perception server">
				<%=StringEscapeUtils.escapeHtml(qe.getMessage())%>
			</bbNG:receipt>
		<%
		//Want this page to crash now!
		return;
	}

	//Retrieve the Db persistence manager from the persistence service
	BbPersistenceManager bbPm = PersistenceServiceFactory.getInstance().getDbPersistenceManager();

	// Generate a persistence framework course Id to be used for 
	// loading the course
	Id courseIdObject = bbPm.generateId(Course.DATA_TYPE, course_id);
	Course course = null;
	try{
		CourseDbLoader courseLoader = (CourseDbLoader) bbPm.getLoader(CourseDbLoader.TYPE);
		 course = courseLoader.loadById(courseIdObject);	
	} catch (KeyNotFoundException knfe){
		out.println("Exception Caught: " + knfe.getMessage() );
	}
	

	PropertiesBean pb = new PropertiesBean();
	
	
	//-----------------------------------------------------------------------
	//synchronization: No Participant synching code needed for Tools view.
	//-----------------------------------------------------------------------
	
	
	//-----------------------------------------------------------------------
	// Group synchronization
	//-----------------------------------------------------------------------
	
	//get Perception group id from delete schedule form.	
	
	try {
		
		perceptiongroupid = new Integer(qmwise.getStub().getGroupByName(request.getParameter("group_name")).getGroup_ID()).intValue();
		
	} catch(NullPointerException npe){
		
		System.out.println("Perception: course " + course_id + ": synchronization failed: " + npe.getMessage());
		%>
		<h1>Error retrieving course group from Perception, please ensure Connector is successfully 
		connected to Perception</h1>
			<p><%=StringEscapeUtils.escapeHtml(npe.getMessage())%></p>					

		<%
		return;
		
	
	}  catch(Exception e) {
		
		QMWiseException qe = new QMWiseException(e);
		if(qe.getQMErrorCode() == 1201) {
			//group doesn't exist -- force sync
			System.out.println("Perception: course " + course_id + ": Perception group doesn't exist -- forcing synchronisation");
			UserSynchronizer us = new UserSynchronizer();
			String force_sync_result;
			try {
				force_sync_result = us.synchronizeCourse(course_id);
				configReader.setCourseSyncDate();
				out.print(force_sync_result);
				//get fresh group
				perceptiongroupid = new Integer(qmwise.getStub().getGroupByName(course.getBatchUid()).getGroup_ID()).intValue();
				
			} catch (QMWiseException nqe ) {		
				
				System.out.println("Qmwise exception caught: course " + course_id + ": synchronization failed: " + nqe.getMessage());
				String output = "Qmwise exception caught: course " + course_id + ": synchronization failed: " + nqe.getMessage();
				
				%>				
				<!-- Trying this one out -->
				<bbNG:error exception="nqe"/>
				
				
				<bbNG:receipt type="FAIL" title="Group Synchronisation failed!">
					<%=StringEscapeUtils.escapeHtml(output)%>
				</bbNG:receipt>
				
				
				<%
				//We don't want the page to crash! Can continue without sync to be dealt with separately in course tools view.
				//return;
			} 
		} else {
			%>
				<bbNG:receipt type="FAIL" title="Error retrieving course group from Perception">
					<%=StringEscapeUtils.escapeHtml(qe.getMessage())%>
				</bbNG:receipt>
			<%
			//return;
		}
	}
	
		
	
	//----------------------End of sync block-----------------------------------------------
	
					

	//-----------------------------------------------------------------------
	//view (still) specific to current user, i.e. Student can only Take assessments
	// and Staff can "Test Assessments"
	//-----------------------------------------------------------------------

	
	// get the membership data to determine the User's Role
	CourseMembershipDbLoader crsMembershipLoader = (CourseMembershipDbLoader) bbPm.getLoader(CourseMembershipDbLoader.TYPE);
	CourseMembership crsMembership = null;


	try {
		crsMembership = crsMembershipLoader.loadByCourseAndUserId(courseIdObject, sessionUserId);
	} catch (KeyNotFoundException e) {
		// There is no membership record.
		%>			
		<h1>You don't have a role on this course</h1>
			<p><%=StringEscapeUtils.escapeHtml(e.getMessage())%></p>
		<%
		//return;
	} catch (PersistenceException pe) {
		// There is no membership record.
		%>
		<h1>Error loading the current user</h1>
			<p><%=pe.getMessage()%></p>			
		<%
		//return;
	}


	if(crsMembership.getRole() == CourseMembership.Role.INSTRUCTOR
		|| crsMembership.getRole() == CourseMembership.Role.TEACHING_ASSISTANT) {
	
		//-----------------------------------------------------------------------
		//Administrator or TA
		//-----------------------------------------------------------------------

			ScheduleV42[] schedulesarray = null;
			try {				
				//Get back an array of schedules
				schedulesarray = qmwise.getStub().getScheduleListByParticipantV42(new Integer(UserSynchronizer.getPhantomUserId()).intValue());
				
			} catch (Exception e){			
				if( e instanceof QMWiseException ){
					QMWiseException qe = new QMWiseException(e);
					if(qe.getQMErrorCode() == 1301){
						String assessmentErrorOutput = "Perception: course " + course_id + 
						": Error getting group schedule list. Cause: Assessment not found, check whether assessment exists in Perception."
						+ " For more information please check Perception server logs - QMWISe trace log. Message: "
						+ qe.getMessage();
						System.out.println(assessmentErrorOutput);					
						%>
							<h1>Error getting group schedule list, assessment missing!</h1>
							<p><%=StringEscapeUtils.escapeHtml(assessmentErrorOutput)%></p>
						<%						
					} else {
						String qmErrorOutput = "Perception: course " + course_id + ": Error getting group schedule list. Cause: " + qe.getMessage();
						
						System.out.println(qmErrorOutput);
						//Suppressing synch related html on this page. Synch errors should show up on the course tools view.
						
					}
				} else {
					String errorMessage = e.getMessage();
					System.out.println("Unknown Exception returned: details: " + e.getMessage());
					//Suppressing synch related html on this page. Synch errors should show up on the course tools view.					
				
				}
				// Return disabled to allow for the page to continue loading.
				//return;
				
			} //end of catch block.
		
			
			
			//Pass through the array returned by getting all schedules for bb-phantom, find the one 
			//that shares the name with this content item, and delete it from Perception.
			//This should stop a Race condition from developing, i.e. Synchronising will cease while the 
			//deletion takes place.
			
			for(ScheduleV42 schedule: schedulesarray){
				//Delete group schedule - but make sure delete for only this group!
				if(schedule.getGroup_ID() == perceptiongroupid 
					&& schedule.getSchedule_Name().equals(schedule_name)){						
					try{
						//Once found delete straigthaway, Deletes the Group Schedule for this Course.						
						qmwise.getStub().deleteScheduleV42(schedule.getSchedule_ID());
						//Announce deletion for logs.
						System.out.println("Group Schedule deleted, Name: " + schedule.getSchedule_Name()
								+ " ID: " + schedule.getSchedule_ID());						
					}  catch (Exception e) {
						QMWiseException qe = new QMWiseException(e);
						//This schedule has to be deleted, else synchronisation will put it back in!
						//So if qmwise call fails, terminate this script.
						System.out.println("Error deleting Group schedule: " + qe.getMessage());
						System.out.println("Terminating remove script...Please manually delete Enerprise Manager ");						
						return;
					}					
				}
			}

			//Now to delete participant schedules..
			
			//Start by getting a schedule list by group from QMWISe
			
			ScheduleV42[] groupScheduleArray = null;
			
			try {
				
				groupScheduleArray = qmwise.getStub().getScheduleListByGroupV42(perceptiongroupid);
				
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				System.out.println("Error getting list of schedules for this group: " + qe.getMessage());
				%>
					<h1>Error getting list of schedules for this group</h1>
					<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>
	
				<%
				//return;
			}
			
			//Now loop through the group schedule list and identify schedules with the same name as the content item, 
			//Add them to delete_schedules array, then pass it to QMWISE method deleteScheduleList as that array for deletion!
			//Robust: If deletion fails don't kill the script, log short messages on Blackboard logs for
			//BB admins with course name/group name and a simple message to fix the issue in Perception
			
			
			//New String array to store all the schedule ID's to be sent to qmwise for deletion.
			Vector<String> deleteScheduleIdArray = new Vector<String>();
			
			String deleteScheduleID = null;					
			
			//Populate deleteSchedulIdArray with Id's of schedules to be deleted from groupScheduleArray
			
			//Null check
			if(groupScheduleArray != null){
				for(ScheduleV42 schedule: groupScheduleArray){
					//If we dont' have a valid schedule name then the delete schedule list will be empty, which will be handled
					//further down this script.
					if(!schedule_name.equals(schedule.getSchedule_Name())) continue;
					//Set id of schedule to string array to pass onto qmwise later.	
					deleteScheduleID = Integer.toString(schedule.getSchedule_ID());
					deleteScheduleIdArray.add(deleteScheduleID);
				}
			}
			
			//We get a schedule array back as response, so initialise one..
			Schedule[] responseScheduleIdArray = null;

			/**Empty array check, if the schedules list coming back is empty, 
			//	Error, no schedule found in Perception with the name, print schedule name,
				Deleting content item, please confirm whether this schedule exists on perception.
			*/
			
			//Null check.
			if(!deleteScheduleIdArray.isEmpty()){
				
					try {
						//QMWISe deleteScheduleList, returns an array of schedules which have been deleted
						String[] newArray = new String[deleteScheduleIdArray.size()];						
						responseScheduleIdArray = qmwise.getStub().deleteScheduleList(deleteScheduleIdArray.toArray(newArray));
						
						//For the logs
						
						System.out.println("Found schedules in Questionmark Perception matching content item name, deleting...");
						
						if((responseScheduleIdArray != null) && (responseScheduleIdArray.length > 0)){
							for(int i = 0; i < responseScheduleIdArray.length; i++){
								System.out.println("Schedule deleted, Name: " + responseScheduleIdArray[i].getSchedule_Name()
										+ " ID: " + responseScheduleIdArray[i].getSchedule_ID());
							}
						}
						
					} catch (Exception e) {
							QMWiseException qe = new QMWiseException(e);
							System.out.println("Error deleting schedules: " + qe.getMessage() + 
									"\nPlease log into Enterprise Manager to ensure schedules are properly deleted");
							%>
								<bbNG:receipt type="FAIL" title="Error deleting schedule">
									<%=StringEscapeUtils.escapeHtml(qe.getMessage())%>
								</bbNG:receipt>
							<%
							return;
					}
			}
			
			else {
				System.out.println("Error, no schedules could be found by this name: " 
						+ schedule_name + " on the Perception database. Please check your Perception error logs.");
				%> 
					<bbNG:receipt type="FAIL" title="Error, no schedules found in this Course content item">
						<%=StringEscapeUtils.escapeHtml("Could not find schedule named: " + schedule_name + " on Perception database")%>
					</bbNG:receipt>
				<%
				return;
			}
					
	}
	

%>	




</bbNG:learningSystemPage>

</bbNG:jspBlock>