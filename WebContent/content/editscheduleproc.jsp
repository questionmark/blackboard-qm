<!-- 
 Filename		: content/editscheduleproc.jsp
 Description	: Runs when the user submits changes in editschedule form.

-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	import="java.util.*,
	java.text.*,
	blackboard.platform.*,
	blackboard.platform.context.Context,
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
	blackboard.data.ValidationException,
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

<%@ taglib uri="/bbUI" prefix="bbUI" %>
<%@ taglib uri="/bbData" prefix="bbData" %>
<%@ taglib uri="/bbNG" prefix="bbNG" %>



<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";


	//Authenticate for use.	
	if (!PlugInUtil.authorizeForCourseControlPanel(request, response)) {
		//If user is not authorised to see this page then return error page.
		%>
			


<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!">
						<em>You are not authorised to view this page</em>
			</bbNG:receipt>
		<%

	}	
	
	//Retrieve the Db persistence manager from the persistence service
	BbPersistenceManager bbPm = PersistenceServiceFactory.getInstance()
			.getDbPersistenceManager();

	//load course by short course name to get its Blackboard ID
	CourseDbLoader courseLoader = (CourseDbLoader) bbPm
			.getLoader(CourseDbLoader.TYPE);
	
	Course course = null;
	User user = null, sessionUser = null;
	Content contentItem = null;
	Id sessionUserId = null;
	CourseMembershipDbLoader crsMembershipLoader = null;
	CourseMembership crsMembership = null;
	LineitemDbPersister lineitemdbpersister = null;
	LineitemDbLoader lineitemdbloader = null;
	List<Lineitem> lineitems = null;
	PropertiesBean pb = null;
	
%>


<%!	//Method declaration hence the '!'

	public Content getCourseDocFromContentId(String _content_ID) throws PersistenceException {
		
		Content courseDoc = null;

		try{
			//Now use content ID string to get the course document, i.e. Content Item object.
			Id contentId = Id.generateId(Content.DATA_TYPE, _content_ID);
			
			ContentDbLoader courseDocumentLoader = ContentDbLoader.Default.getInstance();
			courseDoc = courseDocumentLoader.loadById(contentId);
			
		}
		catch (PersistenceException pe){
			System.out.println("Internal Error, Blackboard Persistence Exception: " + pe.getMessage() +
					"\nUnable to load Course Document");
			throw pe;
		}

		return courseDoc;
	}

	public void persistContentItemChanges(Content courseDoc) throws PersistenceException, ValidationException {
		try{
			ContentDbPersister persister = ContentDbPersister.Default.getInstance();
			persister.persist(courseDoc);
			
		}
		catch (PersistenceException pe){
			System.out.println("Internal Error, Blackboard Persistence Exception: " + pe.getMessage() +
					"\nUnable to save changes to Content item");
			throw pe;
		}
		catch (ValidationException ve){
			System.out.println("Internal Error, Blackboard Validation Exception: " + ve.getMessage() +
				"\nUnable to validate changes to Content item");
			throw ve;
		}
	
		
	}
	
	public void persistLineitemChanges(Lineitem lineitem, BbPersistenceManager bbPm) 
		throws PersistenceException, ValidationException
	{
		try{		
			LineitemDbPersister lineitemdbpersister = (LineitemDbPersister) bbPm
				.getPersister(LineitemDbPersister.TYPE);
			lineitemdbpersister.persist(lineitem);
		}
		catch(PersistenceException pe){
			System.out.println("Internal Error, Blackboard Persistence Exception: " + pe.getMessage() +
				"\nUnable to save changes to Gradebook Line item");
			throw pe;
		}
		catch (ValidationException ve){
			System.out.println("Internal Error, Blackboard Validation Exception: " + ve.getMessage() +
				"\nUnable to validate changes to gradebook lineitem");
			throw ve;
		}		
	}
%>


<bbNG:learningSystemPage ctxId="ctx" title="Questionmark Perception connector">

<% 

//Declare members.
	
	QMWise qmwise = null;
		
	String content_id = "", parent_id = "",
		courseId = "", perception_group_name = "";
	
	Id courseIdObject = null;
	
	int schedule_id = 0; 
	int perception_group_ID = 0;

	boolean limited_attempts = false,
	//boolean to set limit attempts to true from incoming request.
	restrict_attempts = true;
	boolean limited_schedule_access = false, 
	//boolean to set accessperiod from incoming request.
	setAccessPeriod = false,
	//Variable to help confirm when the schedule has been updated in Perception
	scheduleUpdated = false;
	
	int schedule_max_attempts = 0;
	int new_sched_max_attempts = 0;
	
	String old_schedule_name = "", new_schedule_name = "",
		new_sched_description = "";
	

	//Initialise bb-phantom schedule object
	ScheduleV42 bbSchedule = null;
	//Initialise New String array to store all the schedule ID's to be edited
	Vector<ScheduleV42> editGroupSchedules = new Vector<ScheduleV42>();	
	
	//initialise dates and times to store data from REQUEST object	
	Calendar startCal = Calendar.getInstance();
	Calendar endCal = Calendar.getInstance();
	


	//------------------Get New Information from edit request:--------------------------------------------

	//The following information cannot be null before the script continues onwards:
	
	try {
		
		new_schedule_name = request.getParameter("schedule");
		
		
		//Important! Need to get group information before continuing. Group ID needed from request call.
		perception_group_ID = new Integer(request.getParameter("group_id")).intValue();
		
		//Get Perception group name / Blackboard Course ID
		
		perception_group_name = request.getParameter("group_name");
		
		
		//Get the course ID from the request object
		courseId = request.getParameter("course_id");
		

		//Get the content ID from the edit form request, so that we can find out schedule information 
		//from the content item id itself.
		
		content_id = request.getParameter("content_id");
		
		//Get parent id for the return url once the editing is finished.
		parent_id = request.getParameter("parent_id");
		
		// check whether the "reset maximum attempts?" box has been ticked
		restrict_attempts = request.getParameter("limit_attempts") != null;
		
		if (restrict_attempts) {
			
			//Need some validation:
			if(!request.getParameter("limit").matches(("[1-9][0-9]*"))){
				%>				
					<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!" buttonAlt="OK">
						<em>Attempt limit must be a positive integer!</em>
						<br />
						 Please click ok to try again.
					</bbNG:receipt>
				<%	
			}
		
			//if valid, then set the flag up on the class variable
			
			limited_attempts = true;			
			
			//get the attempts value from the form:
			
			new_sched_max_attempts = new Integer(request.getParameter("limit")).intValue();
				
			//from here on in if the number of attempts is still zero assume unlimited attempts.
			
		}
		
		
		//Get new dates:
		
		// check whether the "Set / Change Access period" box has been ticked
		setAccessPeriod = request.getParameter("set_access_period") != null;
		
		//if box is ticked, then get the new date and time information
		
		if (setAccessPeriod) {
			//Set the class var to true
			limited_schedule_access = true;
			
			String regexhour = "[0-1][0-9]|2[0-3]";
			String regexmin = "[0-5][0-9]";
			//check times make sense
			if (!(request.getParameter("start_hour").matches(regexhour)
					&& request.getParameter("start_minute")
							.matches(regexmin)
					&& request.getParameter("end_hour")
							.matches(regexhour) && request
					.getParameter("end_minute").matches(
							regexmin))) 
			{
				%>				
					<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!" buttonAlt="OK">
						<em>Times must be in 24-hour HH:MM format</em>
						<br />
						 Please click ok to try again.
					</bbNG:receipt>
				<%		
			}

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd' 0:0:00'");

			startCal.setTime(df.parse(request
					.getParameter("start_0")));
			startCal.set(Calendar.HOUR_OF_DAY, new Integer(
					request.getParameter("start_hour"))
					.intValue());
			startCal.set(Calendar.MINUTE, new Integer(request
					.getParameter("start_minute")).intValue());

			endCal.setTime(df.parse(request
					.getParameter("end_1")));
			endCal.set(Calendar.HOUR_OF_DAY, new Integer(
					request.getParameter("end_hour"))
					.intValue());
			endCal.set(Calendar.MINUTE, new Integer(request
					.getParameter("end_minute")).intValue());

			if (endCal.before(startCal)
					|| endCal.equals(startCal))
			{
				%>				
					<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!" buttonAlt="OK">
						<em>The end date must be after the start date</em>
						<br />
						 Please click ok to try again.
					</bbNG:receipt>
				<%	
			}

		}	
		
		
	} catch(NullPointerException npe){
		
		System.out.println("Perception: course " + courseId + ": edit schedule failed: " + npe.getMessage());
		
		%>
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!">
				<em>Internal error getting schedule information, cannot continue </em>
				<br />
				Please edit this schedule in Questionmark Perception Enterprise Manager.
				<br />
				Please refer to the following for more information, or contact your Blackboard System administrator.
				<br />
				<%=StringEscapeUtils.escapeHtml(npe.getMessage())%>
			</bbNG:receipt>
		<%	
	}

	new_sched_description = request.getParameter("schedule_text_area");
	
	
	if (new_sched_description.length() > 4000){
		
		%>			
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!" buttonAlt="OK">
				<em>Schedule description is too long!</em>
				 Cannot exceed more than 4000 characters. Please click ok to try again
			</bbNG:receipt>
		<%
	}
	
	if(courseId == null || courseId.length() == 0) {
		%>
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule">
				<p>No course ID was found.</p>
			</bbNG:receipt>
		<%
		
	}
	
	if(content_id == null || content_id.length() == 0) {
		%>
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule">
				<p>No content ID was found. Cannot continue without content ID.</p>
			</bbNG:receipt>
		<%
	}
	



	
	//---------------------------End of getting request objects-----------------------------------------
	
	
	/*Getting schedule and group information depending on content item edit request or from control panel.*/
	
	//get the schedule name if the edit request came from Content item.
	if(ctx.hasContentContext()){	
		try{
			contentItem = getCourseDocFromContentId(content_id);
			//Get schedule name from context object of a content item.
			old_schedule_name = contentItem.getTitle();
			//stop if the schedule name is empty.
			if(old_schedule_name.length() == 0){
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
			
			
		} catch (PersistenceException pe){
			System.out.println("Persistence Exception:Error getting content item information from content context, terminating edit script.");
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
		} catch(NullPointerException npe){
			System.out.println("Nullpointer exception: Error getting content item information from content context, terminating edit script.");
			//Stop the script for content item deletion due to internal error.
			%>
				<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!">
					<em>Error getting content item information from content context</em>
					<br />
					Please refer to the following for more information, or contact your Blackboard System administrator.
					<br />
					<p><%=StringEscapeUtils.escapeHtml(npe.getMessage())%></p>
				</bbNG:receipt>
			<%			
			
		}

	}
	
	else {
		
		//unexpected error! can only work within content item context!
		%>
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!">
				<em>Error getting content item information from content context</em>
				<br />
				Please refer to the following for more information, or contact your Blackboard System administrator.
			</bbNG:receipt>
		<%
		
	}
	
//Retrieve course information from Blackboard Database persistence service
	
	try {
	
		//Retrieve the Db persistence manager from the persistence service
		bbPm = PersistenceServiceFactory.getInstance().getDbPersistenceManager();
		
		//load course by short course name to get its Blackboard ID
		courseLoader = (CourseDbLoader) bbPm.getLoader(CourseDbLoader.TYPE);
		
		// Generate a persistence framework course Id to be used for loading the course
		courseIdObject = bbPm.generateId(Course.DATA_TYPE, courseId);
		
		course = courseLoader.loadById(courseIdObject);
		
		//Get a User instance via user context
		sessionUser = ctx.getUser();
		sessionUserId = sessionUser.getId();
		
		// get the membership data to determine the User's Role
		crsMembershipLoader = (CourseMembershipDbLoader) bbPm.getLoader(CourseMembershipDbLoader.TYPE);
	
		crsMembership = crsMembershipLoader.loadByCourseAndUserId(courseIdObject, sessionUserId);
		
		//Get the gradebook information for this content item:
		
		lineitemdbloader = (LineitemDbLoader) 
			bbPm.getLoader(LineitemDbLoader.TYPE);
		
		lineitems = lineitemdbloader.loadByCourseIdAndLineitemName(courseIdObject,
				old_schedule_name);
		
		pb = new PropertiesBean();
		
		//load contentdbloader and lineitem loader here as well.
	
	} catch (KeyNotFoundException knfe){
		%>
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!">
				<em>Error getting Blackboard Course Details.</em>
				<br />
				Please edit this schedule using Questionmark Perception Enterprise Manager.
				<p><%=StringEscapeUtils.escapeHtml(knfe.getMessage())%></p>
			</bbNG:receipt>
		<%
	} catch (PersistenceException pe){
		%>
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!">
				<em>Internal error, cannot carry out edit.</em>
				<br />
				Please edit this schedule using Questionmark Perception Enterprise Manager.
				<p><%=StringEscapeUtils.escapeHtml(pe.getMessage())%></p>
			</bbNG:receipt>
		<%
	} catch (NullPointerException npe){
		%>
			<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!">
				<em>Internal error, cannot carry out edit.</em>
				<br />
				Please edit this schedule using Questionmark Perception Enterprise Manager.
				<p><%=StringEscapeUtils.escapeHtml(npe.getMessage())%></p>
			</bbNG:receipt>
		<%		
		
	}
	
	if(crsMembership.getRole() == CourseMembership.Role.INSTRUCTOR
			|| crsMembership.getRole() == CourseMembership.Role.TEACHING_ASSISTANT) {
	

	//-----------------------------------------------------------------------
	//Administrator or TA
	//-----------------------------------------------------------------------

		//Now to get participant schedules..
		
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
		
		//Start by getting a schedule list by group from QMWISe
		
		ScheduleV42[] groupScheduleArray = null;
		
		try {
			
			groupScheduleArray = qmwise.getStub().getScheduleListByGroupV42(perception_group_ID);
			
		} catch(QMWiseException qe) {					
			System.out.println("Error getting list of schedules for this group: " + qe.getMessage());
			%>
				<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Cannot edit schedule">
					<p>
						<em>Error editing schedule <b><%=old_schedule_name%></b></em>
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
				if(!old_schedule_name.equals(schedule.getSchedule_Name())) continue;
				//Set id of schedule to string array to pass onto qmwise later.	
				editGroupSchedules.add(schedule);
				
				//contain all of the schedules i am interested in..
				
				if( schedule.getParticipant_ID() == 0 || 
						schedule.getParticipant_ID() == 
							new Integer(UserSynchronizer.getPhantomUserId()).intValue() )  {
					//got a group schedule
					bbSchedule = schedule;
				}
			}
			
		}
		
		if(bbSchedule == null){
			//break and stop script, could not find schedule.
			System.out.println("Could not find this schedule in group schedules: " + old_schedule_name);
			%>
				<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Cannot edit schedule">
					<p>
						<em>Error editing schedule <b><%=old_schedule_name%></b></em>
						<br />
						<em>Could not find a schedule by this name on the Perception repository.</em>
						<br />
						For more information on this error, please refer to your Blackboard tomcat logs.
						<br />
						Or contact your Blackboard Administrator for further assistance.
						<br />
							<b>Please log into Enterprise Manager to edit the above schedule for all participants </b>
						 <br />							
					</p>
				</bbNG:receipt>
			<%
		}
		
		
		try{
			//Time to update schedule details on Perception via QMWISe:
			
			for(ScheduleV42 schedule: editGroupSchedules){
			
				//Check and update name
				
				if(!new_schedule_name.equals(bbSchedule.getSchedule_Name())){
					schedule.setSchedule_Name(new_schedule_name);
				}
				
				//Get and Set Maximum attempts
				
				//If the edit attempts box is ticked, assume that the schedule 
				//has restricted attempts 
				
				if(limited_attempts && 
						new_sched_max_attempts != bbSchedule.getMax_Attempts()){
					//If new attempts are different as well, make the change!
					schedule.setMax_Attempts(new_sched_max_attempts);	
						
				}
				
				//If the set / change access box is ticked, check previous dates, 
				//and update accordingly
				
				if(limited_schedule_access){
					
					boolean changeDate = true;
					//Go ahead with the change unless the following holds:
					if(bbSchedule.isRestrict_Times()){			
						if(startCal.equals(bbSchedule.readSchedule_Starts_asCalendar())
								&& endCal.equals(bbSchedule.readSchedule_Stops_asCalendar()))
							{
								//Then the user has ticked the box but hasn't changed the dates, 
								changeDate = false;
							}					
					}
					//All is good, change the dates via qmwise.
					if(changeDate){
						schedule.updateSchedule_Starts_fromCalendar(startCal);
						schedule.updateSchedule_Stops_fromCalendar(endCal);
					}					
				}
				
				//Push the above schedule changes through to Perception via QMWISe:
				qmwise.getStub().setScheduleV42(schedule);
				
			}// end of for loop.
			
			//If all went well without exceptions, 
			scheduleUpdated = true;
		
		} catch(QMWiseException qe){
			System.out.println("Error changing schedules for this schedule name: " + old_schedule_name + "\n" + 
					qe.getMessage());
			scheduleUpdated = false;
			%>
				<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule">
					<p>
						<em>Error editing schedule <b><%=old_schedule_name%></b></em>
						<br />
						<em>QMWISe Error occurred  while updating the above schedule.</em>
						<br />
						For more information on this error, please refer to your Blackboard tomcat logs.
						<br />
						Or contact your Blackboard Administrator for further assistance.
						<br/>
							<b>Please log into Enterprise Manager to edit the above schedule for all participants </b>
						 <br/>
													
					</p>
					<%=StringEscapeUtils.escapeHtml(qe.getMessage())%>		
				</bbNG:receipt>
			<%
			
		}
		
		//Now to make changes on Blackboard, only if changes on perception are confirmed.

		if(scheduleUpdated){
			try {				
				//Change content name
				contentItem.setTitle(new_schedule_name);
				
				//Set new description of content item.
				//Escape any html that the user types. Only allowing plaintext.
				FormattedText text = new FormattedText(StringEscapeUtils.escapeHtml(new_sched_description), FormattedText.Type.DEFAULT);						
										
				contentItem.setBody(text);
				
				//Have to now persist these changes to the content item on the bb database:				
				persistContentItemChanges(contentItem);
				
				//Update the gradebook with the new schedule name	
				
				if((lineitems != null) && (!lineitems.isEmpty())){
					for (Lineitem lineitem: lineitems){
						lineitem.setName(new_schedule_name);
						persistLineitemChanges(lineitem, bbPm);
					}
				}
				
				//Once done annnounce the good news		
				
				//deal with OK click action	
				String okUrl = "/webapps/blackboard/content/listContentEditable.jsp?content_id="
						+ parent_id
						+ "&amp;course_id="
						+ courseId
						+ "&amp;mode=quick";

				%> 
					<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="SUCCESS" title="Edit Successful" 
						recallUrl="<%=okUrl%>">
						The schedule "<%=StringEscapeUtils.escapeHtml(old_schedule_name)%>" has been successfully edited in Perception.
					</bbNG:receipt>
				<%	
				
				
			} catch (PersistenceException pe){
				System.out.println("Persistence Exception: Error saving updated content item information in Blackboard, terminating edit script.");
				//Stop the script for content item editing due to internal error.
				%>
					<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!">
						<em>Error saving updated content item information in Blackboard</em>
						<br />
						Please refer to the following for more information, or contact your Blackboard System administrator.
						<br />
						<p><%=StringEscapeUtils.escapeHtml(pe.getMessage())%></p>
					</bbNG:receipt>
				<%			
			} catch(ValidationException ve){
				System.out.println("Validation Exception: Error validating updated content item information in Blackboard, terminating edit script.");
				//Stop the script for content item editing due to internal error.
				%>
					<bbNG:receipt iconUrl='<%=path+"/images/qm.gif"%>' type="FAIL" title="Error editing schedule!">
						<em>Error validating updated content item information in Blackboard</em>
						<br />
						Please refer to the following for more information, or contact your Blackboard System administrator.
						<br />
						<p><%=StringEscapeUtils.escapeHtml(ve.getMessage())%></p>
					</bbNG:receipt>
				<%				
			}

		}

			
	}

%>



</bbNG:learningSystemPage>

