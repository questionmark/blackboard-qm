<!-- 
 Filename		: content/deleteschedule.jsp
 Description	: Runs when deleting a schedule.

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



<%@page import="blackboard.platform.context.Context"%><bbNG:jspBlock>

<%	

		
	//Authenticate for use.	
	if (!PlugInUtil.authorizeForCourseControlPanel(request, response)) {
		//If user is not authorised to see this page then return error page.
		%>
			<bbNG:receipt type="FAIL" title="Error deleting schedule">
						<em>You are not authorised to view this page</em>
			</bbNG:receipt>
		<%

	}	

%>

<%!	//Method declaration hence the '!'
	public String getContentScheduleName(Context _ctx) throws PersistenceException {
		
		String schedule_name = "";
		//Get a User , course membership details, and Course instance via context
		User sessionUser = _ctx.getUser();
		Id sessionUserId = sessionUser.getId();
		Course courseCtx = _ctx.getCourse();	
		CourseMembership cMembership = _ctx.getCourseMembership(); // may be null if a SysAdmin!	
		
		//Get content ID string from within the context of the content item.
		String content_id = _ctx.getContentId().toExternalString();
		
		try{
			//Now use content ID string to get the course document, i.e. Content Item object.
			Id contentId = Id.generateId(Content.DATA_TYPE, content_id);
			
			ContentDbLoader courseDocumentLoader = ContentDbLoader.Default.getInstance();
			Content courseDoc = courseDocumentLoader.loadById(contentId); 
			
			//We now have the schedule name for this content item!
			schedule_name = courseDoc.getTitle();
		}
		catch (PersistenceException pe){
			System.out.println("Internal Error, Blackboard Persistence Exception: " + pe.getMessage() + "\nUnable to load Course Document");
			throw pe;
		}
		
		//if all is well, return schedule name for deletion.
		return schedule_name;
	}

%>

<bbNG:learningSystemPage ctxId="ctx">


<% 
	//Declare members.
	
	QMWise qmwise = null;
	int schedule_id = 0; 
	int perception_group_ID = 0;
	boolean schedule_deleted = false;
	String schedule_name = "", perception_group_name = "";
	
	
	//Get context path.
	String path = request.getContextPath();
	
	//Construct base url.
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
		
	//Get the course ID from the context object
	String courseId = ctx.getCourseId().toExternalString();
	
	if(courseId == null) {
		%>
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error deleting schedule">
						<p>No course ID was found. Please remove this schedule manually from Perception.</p>
			</bbNG:receipt>
		<%
		
	}
	
	//URL to go back to once the schedule is successfully deleted.	
	String recallurl = "main.jsp?course_id=" + courseId + "#Schedules";
	

	/*Getting schedule and group information depending on content item deletion request or from control panel.*/
	
	//get the schedule name if the delete request came from Content item.
	if(ctx.hasContentContext()){	
		try{
			//Get schedule name from context object of a content item.
			schedule_name = getContentScheduleName(ctx);
		}
		catch (PersistenceException pe){
			System.out.println("Error getting schedule name from content context, terminating delete script.");
			//Stop the script for content item deletion due to internal error.
			%>
				<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error deleting schedule!">
					<em>Error getting schedule name from content context</em>
					<br />
					Please refer to the following for more information, or contact your Blackboard System administrator.
					<br />
					<p><%=StringEscapeUtils.escapeHtml(pe.getMessage())%></p>
				</bbNG:receipt>
			<%
		}

	}
	//else the request to delete came from  control panel view, get the schedule name and group name from the request object.
	else {
		schedule_name = request.getParameter("schedule_name");
	}
	
	if(schedule_name.length() == 0){
		System.out.println("Schedule name cannot be empty, terminating deletion...");
		%> 
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error deleting schedule" recallUrl="<%=recallurl%>">
				<p>
					<em>Fatal internal error, unable to get Schedule name</em>
					<br />
					For more information on this error, please refer to your Blackboard tomcat logs.
					<br />
					Or contact your Blackboard Administrator for further assistance.
				</p>
			</bbNG:receipt>
		<%	
	}
	
	//connect to QMWise

	try {
		qmwise = new QMWise();
	} catch(Exception e) {
		QMWiseException qe = new QMWiseException(e);
		%>
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error deleting schedule!">
				<em>Error connecting to Perception server</em>
				<br />
				Please ensure Questionmark Perception Connector is successfully connected to Perception server.
				<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>
			</bbNG:receipt>
		<%
	}
	
	//Retrieve the Db persistence manager from the persistence service
	BbPersistenceManager bbPm = PersistenceServiceFactory.getInstance().getDbPersistenceManager();

	// Generate a persistence framework course Id to be used for loading the course
	Id courseIdObject = bbPm.generateId(Course.DATA_TYPE, courseId);

	CourseDbLoader courseLoader = (CourseDbLoader) bbPm.getLoader(CourseDbLoader.TYPE);
	Course course = courseLoader.loadById(courseIdObject);

	PropertiesBean pb = new PropertiesBean();
	
	
	//Important! Need to get group information before continuing. Group ID needed from QMWISe call.
	
		try {
			
			perception_group_ID = new Integer(qmwise.getStub().getGroupByName(course.getBatchUid()).getGroup_ID()).intValue();
			
		} catch(NullPointerException npe){
			
			System.out.println("Perception: course " + courseId + ": delete schedule failed: " + npe.getMessage());
			
			%>
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error deleting schedule!">
				<em>Error retrieving course group from Perception.</em>
				<br />
				Please ensure Questionmark Perception Connector is successfully connected to Perception server.
				<br />
				Please refer to the following for more information, or contact your Blackboard System administrator.
				<br />
				<%=StringEscapeUtils.escapeHtml(npe.getMessage())%>
			</bbNG:receipt>
			<%	
			
		} catch(QMWiseException qe) {
			if(qe.getQMErrorCode() == 1201) {
				//group doesn't exist -- problem -- stop script.
				System.out.println("Perception: course " + courseId + ": Perception group doesn't exist, terminating delete schedule script..");			
			} 				
			//unknown qmwise exception, give details, stop script.
			System.out.println("Perception: course " + courseId + ": Error retrieving course group from Perception, terminating delete schedule script..");
			%>
				<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error deleting schedule!">
					<em>Error retrieving course group from Perception.</em>
					<br />
					Please refer to the following for more information, or contact your Blackboard System administrator.
					<br />
					<%=StringEscapeUtils.escapeHtml(qe.getMessage())%>
				</bbNG:receipt>
			<%
			
		}
		
		
	//----------------------No syncing during deletion-------------------------------------
	

	//Get a User instance via user context
	User sessionUser = ctx.getUser();
	Id sessionUserId = sessionUser.getId();
	
	// get the membership data to determine the User's Role
	CourseMembershipDbLoader crsMembershipLoader = (CourseMembershipDbLoader) bbPm.getLoader(CourseMembershipDbLoader.TYPE);
	CourseMembership crsMembership = null;

	try {
		crsMembership = crsMembershipLoader.loadByCourseAndUserId(courseIdObject, sessionUserId);
	} catch (KeyNotFoundException knfe) {
		// There is no membership record.
		%>			
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error deleting schedule!">
				<em>You don't have a role on this course</em>
				<br />
				Please refer to the following for more information, or contact your Blackboard System administrator.
				<br />
				<%=StringEscapeUtils.escapeHtml(knfe.getMessage())%>
			</bbNG:receipt>
		<%
	} catch (PersistenceException pe) {
		// There is no membership record.
		%>
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error deleting schedule!">
				<em>Error loading the current user</em>
				<br />
				Please refer to the following for more information, or contact your Blackboard System administrator.
				<br />
				<%=StringEscapeUtils.escapeHtml(pe.getMessage())%>
			</bbNG:receipt>	
		<%
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
					
				} catch (QMWiseException qe){		
						String qmErrorOutput = "";
						if(qe.getQMErrorCode() == 1301){
							qmErrorOutput = "Perception: course " + courseId + 
							": Error getting schedule list. Cause: Assessment not found, check whether assessment exists in Perception."
							+ " For more information please check Perception server logs - QMWISe trace log. Message: "
							+ qe.getMessage();
							System.out.println(qmErrorOutput);										
						} else {
							qmErrorOutput = "Perception: course " + courseId + ": Error getting schedule list. Cause: " + qe.getMessage();
							System.out.println(qmErrorOutput);							
						}
						
						%>
							<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error deleting schedule" recallUrl="<%=recallurl%>">
								<p>
									<em>The schedule <b><%=schedule_name%></b> could not be deleted for all participants.</em>
									<br />
									For more information on this error, please refer to your Blackboard tomcat logs.
									<br />
									Or contact your Blackboard Administrator for further assistance.
									<br/>
										<b>Please log into Enterprise Manager to ensure schedules are properly deleted.</b>
									 <br/>
									<%=StringEscapeUtils.escapeHtml(qmErrorOutput)%>									
								</p>
							</bbNG:receipt>	
						<%	
					
				} //end of catch block.
			
				
				
				//Pass through the array returned by getting all schedules for bb-phantom, find the one 
				//that shares the name with this item, and delete it from Perception.
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
							System.out.println("Perception: Deleted schedule for 'bb-phantom' user, Schedule Name: " + schedule.getSchedule_Name()
									+ " ID: " + schedule.getSchedule_ID());						
						}  catch (QMWiseException qe) {
							//This schedule has to be deleted, else synchronisation will put it back in!
							//So if qmwise call fails, terminate this script.
							System.out.println("Perception: Error deleting schedule for: " + schedule.getSchedule_Name() + "\n" + qe.getMessage());
							System.out.println("Perception: Terminating remove script...Please manually delete Enerprise Manager ");
							%> 
								<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error deleting schedule" recallUrl="<%=recallurl%>">
									<p>
										<em>The schedule <b><%=schedule_name%></b> could not be deleted for all participants.</em>
										<br />
										
										<br />
										Or contact your Blackboard Administrator for further assistance.
										<br/>
										<%=StringEscapeUtils.escapeHtml(qe.getMessage())%>		
									</p>
								</bbNG:receipt>
							<%						
						}
						
						schedule_deleted = true; //Set flag for deleting lineitem.
					}
				}

				//Now to delete participant schedules..
				
				//Start by getting a schedule list by group from QMWISe
				
				ScheduleV42[] groupScheduleArray = null;
				
				try {
					
					groupScheduleArray = qmwise.getStub().getScheduleListByGroupV42(perception_group_ID);
					
				} catch(QMWiseException qe) {					
					System.out.println("Error getting list of schedules for this group: " + qe.getMessage());
					%>
						<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error deleting schedule" recallUrl="<%=recallurl%>">
							<p>
								<em>The schedule <b><%=schedule_name%></b> could not be deleted for all participants.</em>
								<br />
								<em>Error getting list of schedules for this group</em>
								<br />
								For more information on this error, please refer to your Blackboard tomcat logs.
								<br />
								Or contact your Blackboard Administrator for further assistance.
								<br/>
									<b>Please log into Enterprise Manager to ensure schedules are properly deleted.</b>
								 <br/>
								<%=StringEscapeUtils.escapeHtml(qe.getMessage())%>									
							</p>
						</bbNG:receipt>							
		
					<%
					
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
				Schedule[] responseScheduleArray = null;

				/**Empty array check, if the schedules list coming back is empty, 
				//	Error, no schedule found in Perception with the name, print schedule name,
					Deleting content item, please confirm whether this schedule exists on perception.
				*/
				
				//Null check.
				if(!deleteScheduleIdArray.isEmpty()){
					
						try {
							//QMWISe deleteScheduleList, returns an array of schedules which have been deleted
							String[] newArray = new String[deleteScheduleIdArray.size()];		
							
							//For the logs
							
							System.out.println("Found participant schedules in Questionmark Perception for the selected schedule, deleting...");
							
							responseScheduleArray = qmwise.getStub().deleteScheduleList(deleteScheduleIdArray.toArray(newArray));
							
						} catch (QMWiseException qe) {
							System.out.println("Error deleting schedules: " + qe.getMessage() + 
									"\nPlease log into Enterprise Manager to ensure schedules are properly deleted");
							%>
								<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error deleting schedule" recallUrl="<%=recallurl%>">
									<p>
										<em>The schedule <b><%=schedule_name%></b> could not be deleted for all participants.</em>
										<br />
										For more information on this error, please refer to your Blackboard tomcat logs.
										<br />
										Or contact your Blackboard Administrator for further assistance.
										<br/>
											<b>Please log into Enterprise Manager to ensure schedules are properly deleted.</b>
										 <br/>
										<%=StringEscapeUtils.escapeHtml(qe.getMessage())%>									
									</p>
								</bbNG:receipt>								
							<%
							
						}
						
						//if the responsearray is empty, nothing got deleted, even when QMWISe exception not thrown..
						if((responseScheduleArray == null) || (responseScheduleArray.length == 0)){
							System.out.println("Issues with deleting the following participant schedules:");
							for(String _deleteScheduleID: deleteScheduleIdArray){
								System.out.println(_deleteScheduleID + ";");
							}
							System.out.println("Please refer to the logs in Questionmark Perception or contact your Perception administrator to assist with the deletion process");

							%> 
								<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error deleting schedule" recallUrl="<%=recallurl%>">
									<p>
										<em>The schedule <b><%=schedule_name%></b> could not be deleted for all participants.</em>
										<br />
										To complete the deletion process, please log in to Enterprise Manager or contact your Perception administrator. 
									</p>
								</bbNG:receipt>
							<%		
							
								
						} else {
							
							for(int i = 0; i < responseScheduleArray.length; i++){
								System.out.println("Schedule deleted, Name: " + responseScheduleArray[i].getSchedule_Name()
										+ " ID: " + responseScheduleArray[i].getSchedule_ID());
							}
							
							schedule_deleted = true;
						
							%> 
								<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="SUCCESS" title="Success" recallUrl="<%=recallurl%>">
									The schedule "<%=schedule_name%>" was successfully deleted!
								</bbNG:receipt>
							<%							
						}						

				} else {
					
					String errorMsg = "Error, no schedules could be found by this name: " 
							+ schedule_name + " on the Perception database. Please check your Perception error logs.";
					
					System.out.println(errorMsg);
					%> 
						<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error deleting schedule" recallUrl="<%=recallurl%>">
							<p>
								<em>The schedule <b><%=schedule_name%></b> could not be deleted for all participants.</em>
								<br />
								For more information on this error, please refer to your Blackboard tomcat logs.
								<br />
								Or contact your Blackboard Administrator for further assistance.
								<br/>
								<%=StringEscapeUtils.escapeHtml(errorMsg)%>									
							</p>
						</bbNG:receipt>
					<%
					
				}	
				
		}
	
%>


</bbNG:learningSystemPage>

</bbNG:jspBlock>

