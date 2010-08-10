<!-- 
 Filename		: content/removeproc.jsp
 Description	: Runs when deleting a schedule from control panel view: 

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


<bbNG:learningSystemPage ctxId="ctx">


<% 
	//Declare members.
	
	QMWise qmwise = null;
	int schedule_id = 0; 
	boolean schedule_deleted = false;
	
	String schedule_name, perception_group_name;
	
	//Get context path.
	String path = request.getContextPath();
	
	//Construct base url.
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
		
		
		
		//Get schedule name from request object reference to start the deletion process
		
			
	schedule_name = request.getParameter("schedule_name");
	perception_group_name = request.getParameter("schedule_group_name");
	
	
	String groupIDString = request.getParameter("schedule_group_id");
	
	int perception_group_ID = Integer.parseInt(groupIDString);
	
	// Retrieve the course identifier from the delete form
	String courseId = request.getParameter("course_id");

	if(courseId == null) {
		%>
		
		
		<bbNG:receipt type="FAIL" title="Oops! No course ID was found, terminated script. Please remove this schedule manually from Perception.">
					No course ID was given with the request
		</bbNG:receipt>
		
		
		<%
		return;
	}
	
	//URL to go back to once the schedule is successfully deleted.	
	String recallurl = "main.jsp?course_id=" + courseId + "#Schedules";

	//create a ConfigFileReader, to check whether this course needs 
	//to sync its members and to show date last synchronized
	ConfigFileReader configReader = new ConfigFileReader(courseId);
	//load the courseSettings file too...
	CourseSettings courseSettings = new CourseSettings(courseId);
	
	//connect to QMWise

	try {
		qmwise = new QMWise();
	} catch(Exception e) {
		QMWiseException qe = new QMWiseException(e);
		%>

			<h1>Error connecting to Perception server</h1>
			<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>	
	
		<%
		//return;
	}

	//Retrieve the Db persistence manager from the persistence service
	BbPersistenceManager bbPm = PersistenceServiceFactory.getInstance().getDbPersistenceManager();

	// Generate a persistence framework course Id to be used for 
	// loading the course
	Id courseIdObject = bbPm.generateId(Course.DATA_TYPE, courseId);

	CourseDbLoader courseLoader = (CourseDbLoader) bbPm.getLoader(CourseDbLoader.TYPE);
	Course course = courseLoader.loadById(courseIdObject);

	PropertiesBean pb = new PropertiesBean();

	
	//----------------------No syncing during deletion-------------------------------------
					

	//Get a User instance via user context
	User sessionUser = ctx.getUser();
	Id sessionUserId = sessionUser.getId();
	
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
						String assessmentErrorOutput = "Perception: course " + courseId + 
						": Error getting group schedule list. Cause: Assessment not found, check whether assessment exists in Perception."
						+ " For more information please check Perception server logs - QMWISe trace log. Message: "
						+ qe.getMessage();
						System.out.println(assessmentErrorOutput);					
						%>
							<h1>Error getting group schedule list, assessment missing!</h1>
							<p><%=StringEscapeUtils.escapeHtml(assessmentErrorOutput)%></p>
						<%						
					} else {
						String qmErrorOutput = "Perception: course " + courseId + ": Error getting group schedule list. Cause: " + qe.getMessage();
						
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
				//Delete schedule for 'bb-phantom' user - but make sure delete for only this group!
				if(schedule.getGroup_ID() == perception_group_ID 
					&& schedule.getSchedule_Name().equals(schedule_name)){						
					try{
						//Once found delete straigthaway, Deletes the bb-phantom Schedule for this Course.						
						qmwise.getStub().deleteScheduleV42(schedule.getSchedule_ID());
						//Announce deletion for logs.
						System.out.println("Deleted schedule for 'bb-phantom' user, Schedule Name: " + schedule.getSchedule_Name()
								+ " ID: " + schedule.getSchedule_ID());						
					}  catch (Exception e) {
						QMWiseException qe = new QMWiseException(e);
						//This schedule has to be deleted, else synchronisation will put it back in!
						//So if qmwise call fails, terminate this script.
						System.out.println("Error deleting schedule: " + schedule.getSchedule_Name() + "\n" + qe.getMessage());
						System.out.println("Terminating remove script...Please manually delete Enerprise Manager ");						
						return;
					}
					
					schedule_deleted = true; //Set flag for deleting lineitem.
				}
			}

			//Now to delete participant schedules..
			
			//Start by getting a schedule list by group from QMWISe
			
			ScheduleV42[] groupScheduleArray = null;
			
			try {
				
				groupScheduleArray = qmwise.getStub().getScheduleListByGroupV42(perception_group_ID);
				
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
							
							schedule_deleted = true;
							

							%> 
								<bbNG:receipt type="SUCCESS" title="Success" recallUrl="<%=recallurl%>">
									The schedule "<%=schedule_name%>" was successfully deleted!
								</bbNG:receipt>
							<%		
						}
						
					} catch (Exception e) {
						QMWiseException qe = new QMWiseException(e);
						System.out.println("Error deleting schedules: " + qe.getMessage() + 
								"\nPlease log into Enterprise Manager to ensure schedules are properly deleted");
						%>
							<h1>Error deleting schedule <%=schedule_name%></h1>
							 <p>Please log into Enterprise Manager to ensure schedules are properly deleted.</p>
							 <br/>
							<%=StringEscapeUtils.escapeHtml(qe.getMessage())%>
							
						<%
						return;
					}
			}
			
			else {
				
				String errorMsg = "Error, no schedules could be found by this name: " 
						+ schedule_name + " on the Perception database. Please check your Perception error logs.";
				
				System.out.println(errorMsg);
				%> 
					<h1>Error deleting schedule!</h1>
					<br/>
					<%=errorMsg%>
					
				<%
				//return;
			}	
			
	}
	

%>	


</bbNG:learningSystemPage>

</bbNG:jspBlock>
