<%@ page language="java" pageEncoding="UTF-8"
	import="
		java.util.*,
		java.text.*,
		java.net.*,
		blackboard.platform.*,
		blackboard.base.*,
		blackboard.platform.session.*,
		blackboard.data.user.*,
		blackboard.platform.persistence.PersistenceServiceFactory,
		blackboard.persist.* ,
		blackboard.persist.user.*,
		blackboard.data.course.*,
		blackboard.persist.course.*,
		org.apache.axis.*,
		org.apache.commons.lang.StringEscapeUtils,
		java.rmi.RemoteException,
		javax.xml.namespace.QName,
		com.questionmark.*,
		com.questionmark.QMWISe.*"
%>

<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ taglib uri="/bbData" prefix="bbData"%>
<%@ taglib uri="/bbNG" prefix="bbNG"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String schedule_name = request.getParameter("schedule_name");
%>



<%@page import="com.sun.mail.handlers.text_html"%>
<%@page import="org.apache.velocity.app.event.implement.EscapeHtmlReference"%><bbNG:learningSystemPage ctxId="ctx"
	title="Questionmark Perception connector" onLoad="disable_set_access()">
	<bbNG:pageHeader>
		<bbNG:pageTitleBar iconUrl='<%=path+"/images/qm.gif"%>'
			title="Questionmark Perception connector" />
	</bbNG:pageHeader>

	
	<%
		// Retrieve the course identifier from the URL
		String courseId = request.getParameter("course_id");

		if(courseId == null) {
	%>
	
	
	<bbNG:receipt type="FAIL" title="No course ID was given">
				No course ID was given with the request
	</bbNG:receipt>
	
	
	<%
		//	return;
		}

		//create a ConfigFileReader, to check whether this course needs 
		//to sync its members and to show date last synchronized
		ConfigFileReader configReader = new ConfigFileReader(courseId);
		//load the courseSettings file too...
		CourseSettings courseSettings = new CourseSettings(courseId);
		
		//connect to QMWise
		QMWise qmwise = null;
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

		//-----------------------------------------------------------------------
		// Participant synchronization
		//-----------------------------------------------------------------------

		//read in synchronization period
		String syncperiod = pb.getProperty("perception.syncperiod");
		String syncusers = pb.getProperty("perception.syncusers");
		if(syncperiod == null) syncperiod = "60";
		Long syncperiodms = new Long(syncperiod) * 60 * 1000;

		if (syncusers != null) {
			if(new Date().getTime() > configReader.getCourseSyncDate() + syncperiodms) {
				//synchronize course users
				System.out.println("Perception: course " + courseId + ": it's been more than the sync period since last sync -- syncing users");
				UserSynchronizer us = new UserSynchronizer();
				String result;
				try {
					result = us.synchronizeCourse(courseId);
					configReader.setCourseSyncDate();
					out.print(result);
				} catch (QMWiseException nqe ) {
					if(nqe.getQMErrorCode() == 4002) System.out.println("Illegal character still");
					
					%>
					<p>Illegal Character exception</p>
					<%
					
					System.out.println("Qmwise exception caught: course " + courseId + ": synchronization failed: " + nqe.getMessage());
					String output = "Qmwise exception caught: course " + courseId + ": synchronization failed: " + nqe.getMessage();
					
					%>					
					<h1>Participant Synchronisation failed!</h1>					
					
					<p>
						<%=StringEscapeUtils.escapeHtml(output)%>
					</p>
					
					<%
					//Do not want the page to crash!
					//return;
				}
				catch (Exception pe){
					System.out.println("Other exception caught: Course " + courseId + ": synchronisation failed: " + pe.getMessage());
					%>
					<p><%=StringEscapeUtils.escapeHtml(pe.getMessage())%></p>
					
					<%
					//return;
				}
			}
		}
		
		//-----------------------------------------------------------------------
		// Group synchronization
		//-----------------------------------------------------------------------
		
		//get Perception group id
		int perceptiongroupid = 0;
		try {
			
			perceptiongroupid = new Integer(qmwise.getStub().getGroupByName(course.getBatchUid()).getGroup_ID()).intValue();
			
		} catch(NullPointerException npe){
			
			System.out.println("Perception: course " + courseId + ": synchronization failed: " + npe.getMessage());
			%>
			<h1>Error retrieving course group from Perception, please ensure Connector is successfully 
			connected to Perception</h1>
				<p><%=StringEscapeUtils.escapeHtml(npe.getMessage())%></p>					

			<%
			//return;
			
		
		}  catch(Exception e) {
			
			QMWiseException qe = new QMWiseException(e);
			if(qe.getQMErrorCode() == 1201) {
				//group doesn't exist -- force sync
				System.out.println("Perception: course " + courseId + ": Perception group doesn't exist -- forcing synchronisation");
				UserSynchronizer us = new UserSynchronizer();
				String force_sync_result;
				try {
					force_sync_result = us.synchronizeCourse(courseId);
					configReader.setCourseSyncDate();
					out.print(force_sync_result);
					//get fresh group
					perceptiongroupid = new Integer(qmwise.getStub().getGroupByName(course.getBatchUid()).getGroup_ID()).intValue();
					
				} catch (QMWiseException nqe ) {		
					
					System.out.println("Qmwise exception caught: course " + courseId + ": synchronization failed: " + nqe.getMessage());
					String output = "Qmwise exception caught: course " + courseId + ": synchronization failed: " + nqe.getMessage();
					
					%>					
					<h1>Group Synchronisation failed!</h1>					
					
					<p>
						<%=StringEscapeUtils.escapeHtml(output)%>
					</p>
					
					<%
					//Do not want the page to crash!
					//return;
				} 
			} else {
				%>
					<h1>Error retrieving course group from Perception</h1>
						<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>
					
				<%
				//return;
			}
		}
		
		//----------------------End of sync block-----------------------------------------------
		
				
		
		
		//-----------------------------------------------------------------------
		//view specific to current user
		//-----------------------------------------------------------------------

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

			%>
			
	<bbNG:actionControlBar showWhenEmpty="true">	

		<bbNG:actionButton  url='<%=path+"/links/forcesync.jsp?course_id="+courseId%>' title="Synchronize
		users now"/>		

		<bbNG:actionButton url='<%=path+"/links/viewresults.jsp?course_id="+courseId%>' title="View
		results"/>		

	<%
		if(pb.getProperty("perception.singlesignon") != null) {
	%>
		<bbNG:actionButton url='<%=path+"/links/enterprisemanager.jsp"%>' 
			title="Log in to Enterprise Manager" target="_blank"/>
	<%
		}
	%>
	
	</bbNG:actionControlBar>

	<h1 id="Syncdetails">Synchronisation details</h1>
	<p>Users of this course were last synchronised <%=new Date(configReader.getCourseSyncDate()).toString()%></p>
	<bbUI:spacer height="20" />

	<h1 id="CourseSettings">Course Settings</h1>
	<form name="course_settings"
		action='<%=path+"/links/coursesettings.jsp"%>' method="post"><bbUI:step
		title="Enter Information" number="1">
		<bbUI:dataElement label="Hide schedules from students in Course Tool view?">
			<% if (courseSettings.getProperty("hide_schedules","0").equals("1")) { %>
			<input type="checkbox" id="hide_schedules" name="hide_schedules"
				value="true" checked="checked" />
			<% } 
						else {
						%>
			<input type="checkbox" id="hide_schedules" name="hide_schedules"
				value="false" />
			<% } %>
			<p><i>Use this option if you are creating schedules using content items to prevent students from seeing the schedule list in the Course Tool 
			view of the connector. <br/>
			Hidden schedules can still be accessed individually using the schedule's URL below.</i></p>
		</bbUI:dataElement>
		<input type="hidden" name="course_id" value="<%=courseId%>" />
	</bbUI:step> <bbUI:stepSubmit title="Submit" number="2" /></form>

	<h1 id="Schedules">Schedules</h1>
	<%
			ScheduleV42[] schedulesarray = null;
			try {				
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
						%>
							<h1>Error getting group schedule list, QMWISe error!</h1>
							<p><%=StringEscapeUtils.escapeHtml(qmErrorOutput)%></p>
						<%
					}
				} else {
					String errorMessage = e.getMessage();
					System.out.println("Unknown Exception returned: details: " + e.getMessage());
					%><p>Error getting schedules, unknown exception</p>
						<p><%=StringEscapeUtils.escapeHtml(errorMessage)%></p>	
					<%						
				
				}
				// Return disabled to allow for the page to continue loading.
				//return;
				
			}

			Vector<ScheduleV42> schedules = new Vector<ScheduleV42>();

			for(int i = 0; i < schedulesarray.length; i++) {
				if(schedulesarray[i].getGroup_ID() == perceptiongroupid)
					schedules.add(schedulesarray[i]);
			}

			ScheduleV42[] zeroschedulesarray = null;
			try {
				zeroschedulesarray = qmwise.getStub().getScheduleListByParticipantV42(0);
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
					<h1>Error getting zero user schedule list</h1>
					<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>
	
				<%
				//return;
			}

			for(int i = 0; i < zeroschedulesarray.length; i++)
				if(zeroschedulesarray[i].getGroup_ID() == perceptiongroupid)
					schedules.add(zeroschedulesarray[i]);

			String[] scheduleurls = new String[schedules.size()];
			boolean[] schedulesactive = new boolean[schedules.size()];

			for(int i = 0; i < schedules.size(); i++) {
				try {
					scheduleurls[i] = qmwise.getStub().getAccessAssessment(
						schedules.get(i).getAssessment_ID(),
						sessionUser.getUserName(),
						"", //participant details
						"" //group name
					);
				} catch(Exception e) {
					QMWiseException qe = new QMWiseException(e);
					if(qe.getQMErrorCode() == 1301){
						String assessmentErrorOutput = "Perception: course " + courseId + 
						": Error getting group schedule list. Cause: Assessment not found, check whether assessment exists in Perception."
						+ " For more information please check Perception server logs - QMWISe trace log. Message: "
						+ qe.getMessage();
						System.out.println(assessmentErrorOutput);
						
						schedules.get(i).setSchedule_Name(schedules.get(i).getSchedule_Name() 
								+ " - ERROR");	
						
						scheduleurls[i] = "";
						
						%>
							<h1>Error getting assessment URL, assessment missing!</h1>
							<p><%=StringEscapeUtils.escapeHtml(assessmentErrorOutput)%></p>
						<%						
					}
					else {						
					
						%>
							<h1>Error getting assessment URL</h1>
							<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>							
						<%
					//return;
					}
				}

				Long schedule_start = schedules.get(i).readSchedule_Starts_asCalendar().getTime().getTime();
				Long schedule_stop = schedules.get(i).readSchedule_Stops_asCalendar().getTime().getTime();
				Long now = new Date().getTime();
				if (schedules.get(i).isRestrict_Times() && (
					schedule_start > now //not started yet
					|| schedule_stop >= 0 && schedule_stop < now //stop time is set (not 0001 AD) && already finished
				)) {
					schedulesactive[i] = false;
				} else {
					schedulesactive[i] = true;
				}
			}

			%>
			
			
		<table border="2" cellpadding="1">
		<bbNG:jsBlock>
			<script type="text/javascript">
				var scheduleTable = {};
				
					function showhideScheduleURL(box,rowID) {
						var row = document.getElementById(rowID); 
						row.style.display = box.checked? "table-row":"none";
					}

					function deleteSchedule(scheduleName, scheduleRowID){
						var schedule = document.getElementById(scheduleName);
						var scheduleRow = document.getElementById(scheduleRowID);
						
						
					}
						
			</script>
		</bbNG:jsBlock>
		<tr>
			<!--<th>Assessment ID</th>-->
			<th>Schedule name</th>
			<th>Maximum attempts</th>
			<th>Start datetime</th>
			<th>End datetime</th>
			<th>Active?</th>
			<th>Try Out</th>
			<th>Show URL</th>
			<th>Delete Schedule</th>			
		</tr>
		<%
				for(int i = 0; i < schedules.size(); i++) {
					String idStr="scheduleURL_"+Integer.toString(i);
					if(schedules.get(i) == null) continue;
					if(schedule_name != null && schedule_name.length() > 0){
						if(!schedules.get(i).getSchedule_Name().equals(schedule_name)) continue;	
					}
					
					%>
					<tr id="scheduleRowID">
						<!--<td><%=schedules.get(i).getAssessment_ID()%></td>-->
						
						<% 
							if ( schedules.get(i).getSchedule_Name().contains("ERROR") ) { 
							%>
								<td bgcolor="yellow"><%=schedules.get(i).getSchedule_Name()%>
							 		<i> : Missing Assessment in Perception, Schedule Disabled</i></td>
							<% 
							} else {
								%>
									<td><%=schedules.get(i).getSchedule_Name()%></td>
								<% 
							}
						%>						
						
						<td><%=schedules.get(i).isRestrict_Attempts() ? schedules.get(i).getMax_Attempts() : "no limit"%></td>
						<td><%=!schedules.get(i).isRestrict_Times() ? "None" : schedules.get(i).readSchedule_Starts_asCalendar().getTime().toString()%></td>
						<td><%=!schedules.get(i).isRestrict_Times() ? "None" : schedules.get(i).readSchedule_Stops_asCalendar().getTime().toString()%></td>
						<td><%=schedulesactive[i] ? "active" : "inactive"%></td>
						
						<% 
						
						if (scheduleurls[i].length() == 0) {						
							%>
								<td bgcolor="yellow"><i>Schedule Disabled</i></td>
							<% 
							
						} else {
							%>							
								<td><a href="<%=StringEscapeUtils.escapeHtml(scheduleurls[i])%>" target="_blank">Test assessment</a></td>
							<% 
						} 
						
						%>
						<td>
							<input align="middle" type="checkbox" name="switchBox"
								onClick="showhideScheduleURL(this,'<%=idStr%>')" />
						</td>
												
						<td>
							<form id="deleteScheduleForm" 
								action='<%=path+"/links/removeproc.jsp"%>' method="post">
									
								<input type="hidden" name="schedule_name" 
								value="<%=schedules.get(i).getSchedule_Name()%>" />
							
								<bbNG:button id="deleteSchedButton" label="Delete" 
									onClick="submitDelete(deleteScheduleForm)" />								
							</form>
						</td>
					</tr>
					<tr id='<%=idStr%>' style="display: none;">
						<td><i>URL:</i></td>
						<td colspan="6"><code><%=basePath+"links/main.jsp?course_id="+courseId+"&amp;schedule_name="+StringEscapeUtils.escapeHtml(schedules.get(i).getSchedule_Name())%></code></td>
					</tr>
					<% 
				} 
				%>
	</table>

	
	<bbUI:spacer height="20" />
	<%
			//-----------------------------------------------------------------------
			// Schedule-authoring form
			//-----------------------------------------------------------------------
			%>
	<h1 id="Scheduleform">Schedule an Assessment</h1>
	<%

			Calendar startdate = Calendar.getInstance();
			Calendar enddate = Calendar.getInstance();
			enddate.add(Calendar.DAY_OF_MONTH, 7);

			String adminid = null;
			try {
				adminid = qmwise.getStub().getAdministratorByName(sessionUser.getUserName()).getAdministrator_ID();
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
					<h1>Error getting Perception administrator ID</h1>
						<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>
					
				<%
				//return;
			}

			Assessment[] assessments = null;
			try {
				assessments = qmwise.getStub().getAssessmentListByAdministrator(adminid);
			
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
					<h1>Error getting list of available assessments</h1>
						<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>
						
				<%
				//return;
			}

			//sort assessments by Session_Name
			Arrays.sort(assessments, new AssessmentComparator());

			if(assessments.length == 0) { %>
	<p>There are no assessments defined in Perception so you cannot
	schedule an assessment.</p>
	<% } else { %>
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
								document.getElementById('per_participant').checked = checked;
								document.getElementById('per_participant').disabled = checked;
								set_limit_attempts_hidden();
							}
						}
						function set_limit_attempts_hidden() {
							document.getElementById('per_participant_hidden').value = document.getElementById('per_participant').checked ? "1" : "0";
						}
		</script>
	</bbNG:jsBlock>

	<form name="schedule_assessment"
		action='<%=path+"/links/scheduleassessment.jsp"%>' method="post">
	<bbUI:step title="Enter Information" number="1">
		<bbUI:dataElement label="Schedule name">
			<input type="text" name="schedule" />
			<br />
			The schedule name must be unique if results are to be stored in the gradebook
		</bbUI:dataElement>
		<bbUI:dataElement label="Store results in Grade Center?">
			<select name="use_gradebook">
				<option value="percent" selected="selected">as percentage
				scores</option>
				<option value="point">as point scores</option>
				<option value="no">do not store results in Grade Center</option>
			</select>
		</bbUI:dataElement>
		<bbUI:dataElement label="Select result to display in Grade Center">
			<select name="result_type">
				<option value="FIRST">First</option>				
				<option value="BEST" selected="selected">Best</option>
				<option value="WORST">Worst</option>
				<option value="LAST">Last</option>
			</select>
		</bbUI:dataElement>		
		<bbUI:dataElement label="Assessment name">
			<select name="assessment">
				<% 
				String last_ID = "";
				for(int i = 0; i < assessments.length; i++) { 
					String next_ID = assessments[i].getAssessment_ID();
					if (!next_ID.equals(last_ID)){%>
				<option value="<%=next_ID%>"><%=assessments[i].getSession_Name()%></option>				
				<%		last_ID = next_ID;
					}					
				} %>
			</select>
		</bbUI:dataElement>
		<bbUI:dataElement label="Limit attempts?">
			<input type="checkbox" id="limit_attempts" name="limit_attempts"
				value="true" onclick="disable_limit_attempts()" />
			<input type="text" id="limit" name="limit" size="4" disabled
				value="1" />
		</bbUI:dataElement>
		<bbUI:dataElement label="Create schedule for each group participant?">
			<input type="checkbox" id="per_participant" name="per_participant"
				value="true" onclick="set_limit_attempts_hidden()" />
			<input type="hidden" id="per_participant_hidden"
				name="per_participant_hidden" value="0" />
		</bbUI:dataElement>
		<bbUI:dataElement label="Set access period?">
			<br />
			<input type="checkbox" id="set_access_period"
				name="set_access_period" value="true" onclick="disable_set_access()" />
			<bbUI:dataElement label="Start date">
				<bbUI:datePicker startDate="<%=startdate%>"
					formName="schedule_assessment" startCaption="Start"
					startDateField="start" />
			</bbUI:dataElement>
			<bbUI:dataElement label="Start time (24-hour HH:MM)">
				<input type="text" id="start_hour" name="start_hour" size="2"
					disabled value="09" /> :
								<input type="text" id="start_minute" name="start_minute"
					size="2" disabled value="00" />
			</bbUI:dataElement>
			<bbUI:dataElement label="End date">
				<bbUI:datePicker startDate="<%=enddate%>"
					formName="schedule_assessment" startCaption="End"
					startDateField="end" />
			</bbUI:dataElement>
			<bbUI:dataElement label="End time (24-hour HH:MM)">
				<input type="text" id="end_hour" name="end_hour" size="2" disabled
					value="17" /> :
								<input type="text" id="end_minute" name="end_minute" size="2"
					disabled value="00" />
			</bbUI:dataElement>
		</bbUI:dataElement>
		<input type="hidden" name="group" value="<%=course.getBatchUid()%>" />
		<input type="hidden" name="course_id" value="<%=courseId%>" />
	</bbUI:step> <bbUI:stepSubmit title="Submit" number="2" /></form>
	<% }

		} else {
			//-----------------------------------------------------------------------
			//Student
			//-----------------------------------------------------------------------

			//get Perception user id
			int perceptionuserid = 0;
			try {
				perceptionuserid = new Integer(qmwise.getStub().getParticipantByName(sessionUser.getUserName()).getParticipant_ID()).intValue();
			}catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
					<h1>Error retrieving participant from Perception</h1>
						<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>
					
	
				<%
				//return;
			}

			ScheduleV42[] schedulesarray = null;
			try {
				schedulesarray = qmwise.getStub().getScheduleListByParticipantV42(perceptionuserid);
			}  catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
					<h1>Error getting participant schedule list</h1>
						<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>
					
	
				<%
				//return;
			}

			Vector<ScheduleV42> schedules = new Vector<ScheduleV42>();

			for(int i = 0; i < schedulesarray.length; i++) {
				if(schedulesarray[i].getGroup_ID() == perceptiongroupid)
					schedules.add(schedulesarray[i]);
			}

			ScheduleV42[] zeroschedulesarray = null;
			try {
				zeroschedulesarray = qmwise.getStub().getScheduleListByParticipantV42(0);
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
					<h1>Error getting zero user schedule list</h1>
						<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>
					
	
				<%
				//return;
			}

			for(int i = 0; i < zeroschedulesarray.length; i++) {
				if(zeroschedulesarray[i].getGroup_ID() == perceptiongroupid)
					schedules.add(zeroschedulesarray[i]);
			}

			String[] scheduleurls = new String[schedules.size()];
			boolean[] schedulesactive = new boolean[schedules.size()];
			
			if ((schedule_name == null || schedule_name.length()==0) && 
				courseSettings.getProperty("hide_schedules","0").equals("1")) {
				schedule_name="HIDE_ALL_SCHEDULES";
			}
			
			for(int i = 0; i < schedules.size(); i++) {
				//if the schedule is currently active get a URL to 
				//launch it
				//haven't coded any timezone handling in here, so this 
				//may be dodgy
				Long schedule_start = schedules.get(i).readSchedule_Starts_asCalendar().getTime().getTime();
				Long schedule_stop = schedules.get(i).readSchedule_Stops_asCalendar().getTime().getTime();
				Long now = new Date().getTime();
				if(
					schedules.get(i).isRestrict_Times()
					&& (
						schedule_start > now //not started yet
						|| schedule_stop >= 0 && schedule_stop < now //stop time is set (not 0001 AD) && already finished
					)
				) {
					schedulesactive[i] = false;
					scheduleurls[i] = null;
				} else {
					schedulesactive[i] = true;
					try {
						Parameter[] parameters = {
							new Parameter("bb_schedulename", schedules.get(i).getSchedule_Name()),
							new Parameter("bb_scheduleid", new Integer(schedules.get(i).getSchedule_ID()).toString()),
							new Parameter("bb_courseid", course.getBatchUid())
						};
						try {
							scheduleurls[i] = qmwise.getStub().getAccessScheduleNotify(
								new Integer(schedules.get(i).getSchedule_ID()).toString(),
								sessionUser.getUserName(),
								request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/links/callback.jsp",
								"blackboard.pip",
								parameters
							);
						} catch(Exception ne) {
							//this method hasn't been programmed well 
							//and doesn't have unique error codes for 
							//each kind of exception. we avoided the 
							//"schedule hasn't started or already 
							//finished" one by checking that first, so 
							//we can hopefully assume that this 
							//exception is saying "no attempts left"
							scheduleurls[i] = null;
						}
					}  catch(Exception e) {
						QMWiseException qe = new QMWiseException(e);
						%>
							<h1>Error getting assessment URL</h1>
								<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>
							
			
						<%
						//return;
					}
				}
			}

	%>

	<table border="2" cellpadding="1">
		<tr>
			<!--<th>Assessment ID</th>-->
			<th>Schedule name</th>
			<th>Remaining attempts</th>
			<th>Start datetime</th>
			<th>End datetime</th>
			<th>Actions</th>

		</tr>
		<%
				for(int i = 0; i < schedules.size(); i++) {	
					if(schedules.get(i) == null) continue;												
					if(schedulesactive[i] == false) continue;	//showing only active to students			
					if(schedule_name != null && schedule_name.length() > 0){
						if(!schedules.get(i).getSchedule_Name().equals(schedule_name)) continue;	
					}
																	
		%>
		<tr>
			<!--<td><%=schedules.get(i).getAssessment_ID()%></td>-->
			<td><%=schedules.get(i).getSchedule_Name()%></td>
			<td><%=schedules.get(i).isRestrict_Attempts() ? schedules.get(i).getMax_Attempts() : "no limit"%></td>
			<td><%=!schedules.get(i).isRestrict_Times() ? "None" : schedules.get(i).readSchedule_Starts_asCalendar().getTime().toString()%></td>
			<td><%=!schedules.get(i).isRestrict_Times() ? "None" : schedules.get(i).readSchedule_Stops_asCalendar().getTime().toString()%></td>
			<td>
			<% if(schedulesactive[i]) { %> <% if(scheduleurls[i] == null) { %> No
			attempts remaining <% } else { %>
			<form><input type="button" value="Take assessment"
				onclick="window.open('<%=scheduleurls[i]%>');"></form>
			<% } %> <% } else { %> inactive <% } %>
			</td>

		</tr>
		<% } %>
	</table>
	<%
		}
	%>
	
</bbNG:learningSystemPage>
