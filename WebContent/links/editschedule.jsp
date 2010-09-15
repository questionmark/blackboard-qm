<!-- 
 Filename		: content/editschedule.jsp
 Description	: Runs when editing a schedule.

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
<%@ taglib uri="/bbUI" prefix="bbUI" %>


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

	public Content getCourseDocFromContext(Context _ctx) throws PersistenceException {
		
		Content courseDoc;
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
			courseDoc = courseDocumentLoader.loadById(contentId);
			
		}
		catch (PersistenceException pe){
			System.out.println("Internal Error, Blackboard Persistence Exception: " + pe.getMessage() + "\nUnable to load Course Document");
			throw pe;
		}
		
		//if all is well, return schedule name for deletion.
		return courseDoc;
	}
%>



<bbNG:learningSystemPage ctxId="ctx">
<% 
//Declare members.
	
	QMWise qmwise = null;
	int schedule_id = 0; 
	int perception_group_ID = 0;

	boolean limited_attempts = false;
	boolean limited_schedule_access = false;
	
	int schedule_max_attempts = 0;
	
	String schedule_name = "", schedule_description = "", perception_group_name = "";
	//Initialised bb-phantom schedule object
	ScheduleV42 bbSchedule = null;
	//Initialise New String array to store all the schedule ID's to be edited
	Vector<String> editGroupSchedules = new Vector<String>();
	
	//Dates and times
	
	Calendar schedule_start_date = Calendar.getInstance();
	
	Calendar schedule_end_date = Calendar.getInstance();
	
	schedule_end_date.add(Calendar.DAY_OF_MONTH, 7);

	int schedule_start_hour = 9, schedule_start_minute = 00,
	schedule_stop_hour = 17, schedule_stop_minute = 00;
	
	
	
	
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
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule">
						<p>No course ID was found.</p>
			</bbNG:receipt>
		<%
		
	}


	/*Getting schedule and group information depending on content item edit request or from control panel.*/
	
	//get the schedule name if the edit request came from Content item.
	if(ctx.hasContentContext()){	
		try{
			Content contentItem = getCourseDocFromContext(ctx);
			//Get schedule name from context object of a content item.
			schedule_name = contentItem.getTitle();
			schedule_description = contentItem.getBody().getText();
			
		}
		catch (PersistenceException pe){
			System.out.println("Error getting content item information from content context, terminating edit script.");
			//Stop the script for content item deletion due to internal error.
			%>
				<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!">
					<em>Error getting content item information from content context</em>
					<br />
					Please refer to the following for more information, or contact your Blackboard System administrator.
					<br />
					<p><%=StringEscapeUtils.escapeHtml(pe.getMessage())%></p>
				</bbNG:receipt>
			<%
		}

	}
	//else the request to edit came from  control panel view, get the schedule name and group name from the request object.
	else {
		schedule_name = request.getParameter("schedule_name");
	}
	
	if(schedule_name.length() == 0){
		System.out.println("Schedule name cannot be empty, terminating edit...");
		%> 
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule" >
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
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!">
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
			
			System.out.println("Perception: course " + courseId + ": edit schedule failed: " + npe.getMessage());
			
			%>
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!">
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
				System.out.println("Perception: course " + courseId + ": Perception group doesn't exist, terminating edit schedule script..");			
			} 				
			//unknown qmwise exception, give details, stop script.
			System.out.println("Perception: course " + courseId + ": Error retrieving course group from Perception, terminating edit schedule script..");
			%>
				<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!">
					<em>Error retrieving course group from Perception.</em>
					<br />
					Please refer to the following for more information, or contact your Blackboard System administrator.
					<br />
					<%=StringEscapeUtils.escapeHtml(qe.getMessage())%>
				</bbNG:receipt>
			<%
			
		}

	//----------------------No syncing during editing-------------------------------------
	

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
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!">
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
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!">
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
					//Get back an array of schedules for the bb-phantom user
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
							<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Cannot edit schedule">
								<p>
									<em>Error editing schedule <b><%=schedule_name%></b></em>
									<br />
									For more information on this error, please refer to your Blackboard tomcat logs.
									<br />
									Or contact your Blackboard Administrator for further assistance.
									<br/>
										<b>Please log into Enterprise Manager to edit this schedule.</b>
									 <br/>
									<%=StringEscapeUtils.escapeHtml(qmErrorOutput)%>									
								</p>
							</bbNG:receipt>	
						<%	
					
				} //end of catch block.
			
				
				
				//Pass through the array returned by getting all schedules for bb-phantom, find the one 
				//that shares the name with this item, and save this schedule as 'bbSchedule'
				//We will then use the details of this schedule to populate the fields in the edit schedule form 
				//such as Maximum attempts and Start / End Dates. 
				
				for(ScheduleV42 schedule: schedulesarray){
					//Grab the schedule for 'bb-phantom' user - but make sure to grab it for only this group!
					if(schedule.getGroup_ID() == perception_group_ID 
						&& schedule.getSchedule_Name().equals(schedule_name)){						
						try{
							//Once found save the bb-phantom Schedule as 'bbSchedule' to get schedule details.
							//N.B there should be only ONE schedule for bb-phantom user for each schedule which is unique for this group.
							//Assign this object to bb-phantom schedule object.
							bbSchedule = schedule;							
							//Log this for debugging.
							System.out.println("Perception: Found schedule for 'bb-phantom' user, Schedule Name: " + bbSchedule.getSchedule_Name()
									+ " ID: " + bbSchedule.getSchedule_ID());						
						}  catch (NullPointerException npe) {
							//If the schedulesarray from qmwise call is still null then catch this:
							//So if qmwise call fails, terminate this script, that is to say we don't have a phantom schedule, hence we cannot go any further.
							System.out.println("Perception: Error editing schedule for: " + schedule_name + "\n" + "Could not find bb-phantom schedule\n" + npe.getMessage());
							System.out.println("Perception: Terminating edit script...Please manually edit this schedule in Enerprise Manager ");
							%> 
								<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Cannot edit schedule">
									<p>
										<em>Error editing schedule <b><%=schedule_name%></b></em>
										<br />
										Could not find the schedule for bb-phantom user.
										<br />
										For more information on this error, please refer to your Blackboard tomcat logs.
										<br />
										Or contact your Blackboard Administrator for further assistance.
										<br/>
											<b>Please log into Enterprise Manager to edit this schedule.</b>
										 <br/>
										<%=StringEscapeUtils.escapeHtml(npe.getMessage())%>									
									</p>
								</bbNG:receipt>
							<%						
						}
					}
				}

				//Now to get participant schedules..
				
				//Start by getting a schedule list by group from QMWISe
				
				ScheduleV42[] groupScheduleArray = null;
				
				try {
					
					groupScheduleArray = qmwise.getStub().getScheduleListByGroupV42(perception_group_ID);
					
				} catch(QMWiseException qe) {					
					System.out.println("Error getting list of schedules for this group: " + qe.getMessage());
					%>
						<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Cannot edit schedule">
							<p>
								<em>Error editing schedule <b><%=schedule_name%></b></em>
								<br />
								<em>QMWISe Error occurred  getting list of schedules for this group</em>
								<br />
								For more information on this error, please refer to your Blackboard tomcat logs.
								<br />
								Or contact your Blackboard Administrator for further assistance.
								<br/>
									<b>Please log into Enterprise Manager to edit the above schedule for all participants </b>
								 <br/>
								<%=StringEscapeUtils.escapeHtml(qe.getMessage())%>									
							</p>
						</bbNG:receipt>							
		
					<%
					
				}
				
				//Null check
								
				//If group schedule array is still null from the above call then assume this schedule has unlimited attempts 
				//and therefore the attempts element should not appear on the page

				if( !(groupScheduleArray == null) && (groupScheduleArray.length > 0) ){
					
					//Now loop through the group schedule list and identify schedules with the same name as the content item, 
					//Add them to an array of schedule id's 'editGroupSchedules'
					
					String editScheduleID = null;					
				
					//Populate editGroupSchedules array with Id's of schedules to be deleted from groupScheduleArray
									
					for(ScheduleV42 schedule: groupScheduleArray){
						//If we dont' have a valid schedule name then the delete schedule list will be empty, which will be handled
						//further down this script.
						if(!schedule_name.equals(schedule.getSchedule_Name())) continue;
						//Set id of schedule to string array to pass onto qmwise later.	
						editScheduleID = Integer.toString(schedule.getSchedule_ID());
						editGroupSchedules.add(editScheduleID);
					}
					
				}
				//else:
				//No Group schedules for individual participants!

				
				/**Get schedule details to display on the following edit form:
				*/
				
				//Get Dates
				
				if(bbSchedule.isRestrict_Times()){
					
					limited_schedule_access = true;
					
					schedule_start_date = bbSchedule.readSchedule_Starts_asCalendar();
					schedule_end_date = bbSchedule.readSchedule_Stops_asCalendar();
						
					//Get Times (24-hour HH:MM)
					
					schedule_start_hour = schedule_start_date.getTime().getHours();
					schedule_start_minute = schedule_start_date.getTime().getMinutes();
					
					schedule_stop_hour = schedule_end_date.getTime().getHours();
					schedule_stop_minute = schedule_end_date.getTime().getMinutes();
				}
				//else will take the default values set during initialisation.
				

				
				//Get Maximum attempts
				
				if(bbSchedule.isRestrict_Attempts()){
					limited_attempts = true;		
					schedule_max_attempts = bbSchedule.getMax_Attempts();
				}
				
				
				
		} else {
			System.out.println("Insufficient rights - Incorrect role in the system. ");
			//-----------------------------------------------------------------------
			// if by some chance you are not admin or instructor but are able to edit content item.
			//-----------------------------------------------------------------------
			%>
				<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Cannot edit schedule">
					<p>
						<em>Insufficient rights - Incorrect role</em>							
					</p>
					<p>
						We are sorry but you don't have sufficient rights to be able to schedule an assessment, please login as course instructor or administrator.
					</p>	
				</bbNG:receipt>							

			<%
			
		}
	
%>
	<bbNG:jsBlock>
		<script type="text/javascript">
						function disable_set_access() {
							if(document.getElementById('set_access_period')) {
								var disabled = !document.getElementById('set_access_period').checked;
								document.getElementById('dp_start_0_start_date').disabled = disabled;
								document.getElementById('start_hour').disabled = disabled;
								document.getElementById('start_minute').disabled = disabled;
								document.getElementById('dp_end_1_start_date').disabled = disabled;
								document.getElementById('end_hour').disabled = disabled;
								document.getElementById('end_minute').disabled = disabled;
							}
						}
						function disable_limit_attempts() {
							if(document.getElementById('limit_attempts')) {
								var checked = document.getElementById('limit_attempts').checked;
								document.getElementById('limit').disabled = !checked;
								set_limit_attempts_hidden();
							}
						}
						function set_limit_attempts_hidden() {
							document.getElementById('per_participant_hidden').value = document.getElementById('per_participant').checked ? "1" : "0";
						}
		</script>
	</bbNG:jsBlock>
	
	<form name="edit_schedule" action='<%=path+"/links/editscheduleproc.jsp"%>' method="post">
	
		<bbNG:dataCollection>
		
			<bbNG:stepGroup active="true" title="Edit Schedule options">
			
				<bbNG:step title="Edit Name" hideNumber="true" 
					instructions="Please enter a new name for this schedule">
					
					<bbNG:dataElement isRequired="true" label="Schedule name">
						<input type="text" name="schedule" width="" value="<%=StringEscapeUtils.escapeHtml(schedule_name)%>"/>
						<br />
						The schedule name must be unique if results are to be stored in the Grade Center
					</bbNG:dataElement>	
					
					<bbNG:dataElement label="Schedule description">	
						<textarea  cols="40" rows="3" title="Additional Comments" onfocus="this.value='';this.onfocus=null;" 
							name="schedule_text_area" id="addComments" ><%=StringEscapeUtils.escapeHtml(schedule_description)%>
						</textarea>		
						<br />
						Enter a short description for this Content item, N.B. Plaintext only.		
					</bbNG:dataElement>				
					
				</bbNG:step>
				
				<bbNG:step title="Edit Attempts" hideNumber="true"
					instructions="Reset schedule attempts for <strong>All</strong>  participants">
					
					<bbNG:dataElement label="Reset maximum attempts?">
						<input type="checkbox" id="limit_attempts" name="limit_attempts"
							value="true" onclick="disable_limit_attempts()" />
						<input type="text" id="limit" name="limit" size="4" disabled
							value="<%=schedule_max_attempts%>" />
					</bbNG:dataElement>
					
					
				</bbNG:step>
				
				<bbNG:step title="Set / Change Access Period" hideNumber="true"
					instructions="Set or modify this schedule's availability">
					
							<bbNG:dataElement label="Set / Change access period?">							
								<input type="checkbox" id="set_access_period"
									name="set_access_period" value="true" onclick="disable_set_access()" />
									<br/>
									<br/>
								<bbNG:dataElement label="Start date">
									<bbUI:datePicker  startDate="<%=schedule_start_date%>"
										formName="edit_schedule" 
										startDateField="start" datePickerIndex="0" />
								</bbNG:dataElement>
								<bbNG:dataElement label="Start time (24-hour HH:MM)">
									<input type="text" id="start_hour" name="start_hour" size="2"
										disabled value="<%=schedule_start_hour%>" /> :
													<input type="text" id="start_minute" name="start_minute"
										size="2" disabled value="<%=schedule_start_minute%>" />
								</bbNG:dataElement>
								<br/>
								<bbNG:dataElement label="End date">
									<bbUI:datePicker startDate="<%=schedule_end_date%>"
										formName="edit_schedule" 
										startDateField="end" datePickerIndex="1"/>
								</bbNG:dataElement>
								<bbNG:dataElement label="End time (24-hour HH:MM)">
									<input type="text" id="end_hour" name="end_hour" size="2" disabled
										value="<%=schedule_stop_hour%>" /> :
													<input type="text" id="end_minute" name="end_minute" size="2"
										disabled value="<%=schedule_stop_minute%>" />
								</bbNG:dataElement>
							</bbNG:dataElement>
				</bbNG:step>
				
				<bbNG:stepSubmit hideNumber="true" title="Submit" instructions="Click the Submit button to save your changes"></bbNG:stepSubmit>
					
			</bbNG:stepGroup>
			
		
		</bbNG:dataCollection>
	</form>


</bbNG:learningSystemPage>

</bbNG:jspBlock>

