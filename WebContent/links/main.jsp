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


<bbNG:learningSystemPage ctxId="ctx"
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
			return;
		}

		//create a ConfigFileReader, to check whether this course needs 
		//to sync its members and to show date last synchronized
		ConfigFileReader configReader = new ConfigFileReader(courseId);
		//load the courseSettings file too...
		CourseSettings courseSettings = new CourseSettings(courseId);
		
		//connect to QMWise
		QMWise qmwise;
		try {
			qmwise = new QMWise();
		} catch(Exception e) {
			QMWiseException qe = new QMWiseException(e);
			%>
	<bbNG:receipt type="FAIL" title="Error connecting to Perception server">
		<%=qe.getMessage()%>
	</bbNG:receipt>
	<%
			return;
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
		//synchronization
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
				try {
					us.synchronizeCourse(courseId);
					configReader.setCourseSyncDate();
				} catch (Exception e) {
					System.out.println("Perception: course " + courseId + ": synchronization failed: " + e.getMessage());
					%>
	<bbNG:receipt type="FAIL"
		title="Error synchronizing course users with Perception">
		<%=e.getMessage()%>
	</bbNG:receipt>
	<%
					return;
				}
			}
		}
		
		//get Perception group id
		int perceptiongroupid;
		try {
			perceptiongroupid = new Integer(qmwise.getStub().getGroupByName(course.getBatchUid()).getGroup_ID()).intValue();
		} catch(Exception e) {
			QMWiseException qe = new QMWiseException(e);
			if(qe.getQMErrorCode() == 1201) {
				//group doesn't exist -- force sync
				System.out.println("Perception: course " + courseId + ": Perception group doesn't exist -- forcing synchronization");
				UserSynchronizer us = new UserSynchronizer();
				try {
					us.synchronizeCourse(courseId);
					configReader.setCourseSyncDate();
					//get fresh group
					perceptiongroupid = new Integer(qmwise.getStub().getGroupByName(course.getBatchUid()).getGroup_ID()).intValue();
				} catch (Exception ne) {
					System.out.println("Perception: course " + courseId + ": synchronization failed: " + ne.getMessage());
					%>
	<bbNG:receipt type="FAIL"
		title="Error synchronizing course users with Perception">
		<%=ne.getMessage()%>
	</bbNG:receipt>
	<%
					return;
				}
			} else {
				%>
	<bbNG:receipt type="FAIL"
		title="Error retrieving course group from Perception">
		<%=qe.getMessage()%>
	</bbNG:receipt>
	<%
				return;
			}
		}

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
	<bbNG:receipt type="FAIL" title="You don't have a role on this course">
		<%=e.getMessage()%>
	</bbNG:receipt>
	<%
			return;
		} catch (PersistenceException pe) {
			// There is no membership record.
			%>
	<bbNG:receipt type="FAIL" title="Error loading the current user">
		<%=pe.getMessage()%>
	</bbNG:receipt>
	<%
			return;
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

	<h1 id="Syncdetails">Synchronization details</h1>
	<p>Users of this course were last synchronized <%=new Date(configReader.getCourseSyncDate()).toString()%></p>
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
			ScheduleV42[] schedulesarray;
			try {
				schedulesarray = qmwise.getStub().getScheduleListByParticipantV42(new Integer(UserSynchronizer.getPhantomUserId()).intValue());
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
	<bbNG:receipt type="FAIL" title="Error getting group schedule list">
		<%=qe.getMessage()%>
	</bbNG:receipt>
	<%
				return;
			}

			Vector<ScheduleV42> schedules = new Vector<ScheduleV42>();

			for(int i = 0; i < schedulesarray.length; i++) {
				if(schedulesarray[i].getGroup_ID() == perceptiongroupid)
					schedules.add(schedulesarray[i]);
			}

			ScheduleV42[] zeroschedulesarray;
			try {
				zeroschedulesarray = qmwise.getStub().getScheduleListByParticipantV42(0);
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
	<bbNG:receipt type="FAIL" title="Error getting zero user schedule list">
		<%=qe.getMessage()%>
	</bbNG:receipt>
	<%
				return;
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
					%>
	<bbNG:receipt type="FAIL" title="Error getting assessment URL">
		<%=qe.getMessage()%>
	</bbNG:receipt>
	<%
					return;
				}

				Long schedule_start = schedules.get(i).getSchedule_Starts().getTime().getTime();
				Long schedule_stop = schedules.get(i).getSchedule_Stops().getTime().getTime();
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
		<script type="text/javascript">
				function showhideScheduleURL(box,rowID) {
					var row = document.getElementById(rowID) 
					row.style.display = box.checked? "table-row":"none"
					}
				</script>
		<tr>
			<!--<th>Assessment ID</th>-->
			<th>Schedule name</th>
			<th>Maximum attempts</th>
			<th>Start datetime</th>
			<th>End datetime</th>
			<th>Active?</th>
			<th>Try Out</th>
			<th>Show URL</th>
			<!--<th>Group</th>-->
		</tr>
		<%
				for(int i = 0; i < schedules.size(); i++) {
					String idStr="scheduleURL_"+Integer.toString(i);
					if(schedules.get(i) == null) continue;
					%>
		<tr>
			<!--<td><%=schedules.get(i).getAssessment_ID()%></td>-->
			<td><%=schedules.get(i).getSchedule_Name()%></td>
			<td><%=schedules.get(i).isRestrict_Attempts() ? schedules.get(i).getMax_Attempts() : "no limit"%></td>
			<td><%=!schedules.get(i).isRestrict_Times() ? "None" : schedules.get(i).getSchedule_Starts().getTime().toString()%></td>
			<td><%=!schedules.get(i).isRestrict_Times() ? "None" : schedules.get(i).getSchedule_Stops().getTime().toString()%></td>
			<td><%=schedulesactive[i] ? "active" : "inactive"%></td>
			<td><a href="<%=scheduleurls[i]%>" target="_blank">Test
			assessment</a></td>
			<td><input type="checkbox" name="switchBox"
				onClick="showhideScheduleURL(this,'<%=idStr%>')" /></td>
			<!--<td><%=schedules.get(i).getGroup_ID()%></td>-->
		</tr>
		<tr id='<%=idStr%>' style="display: none;">
			<td><i>URL:</i></td>
			<td colspan="6"><code><%=basePath+"links/main.jsp?course_id="+courseId+"&amp;schedule_name="+URLEncoder.encode(schedules.get(i).getSchedule_Name())%></code></td>
		</tr>
		<% } %>
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

			String adminid;
			try {
				adminid = qmwise.getStub().getAdministratorByName(sessionUser.getUserName()).getAdministrator_ID();
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
	<bbNG:receipt type="FAIL"
		title="Error getting Perception administrator ID">
		<%=qe.getMessage()%>
	</bbNG:receipt>
	<%
				return;
			}

			Assessment[] assessments;
			try {
				assessments = qmwise.getStub().getAssessmentListByAdministrator(adminid);
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
	<bbNG:receipt type="FAIL"
		title="Error getting list of available assessments">
		<%=qe.getMessage()%>
	</bbNG:receipt>
	<%
				return;
			}

			if(assessments.length == 0) { %>
	<p>There are no assessments defined in Perception so you cannot
	schedule an assessment.</p>
	<% } else { %>
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

	<form name="schedule_assessment"
		action='<%=path+"/links/scheduleassessment.jsp"%>' method="post">
	<bbUI:step title="Enter Information" number="1">
		<bbUI:dataElement label="Schedule name">
			<input type="text" name="schedule" />
			<br />
							The schedule name must be unique if results are to be stored in the gradebook
						</bbUI:dataElement>
		<bbUI:dataElement label="Store results in gradebook?">
			<select name="use_gradebook">
				<option value="percent" selected="selected">as percentage
				scores</option>
				<option value="point">as point scores</option>
				<option value="no">do not store results in gradebook</option>
			</select>
		</bbUI:dataElement>
		<bbUI:dataElement label="Assessment name">
			<select name="assessment">
				<% for(int i = 0; i < assessments.length; i++) { %>
				<option value="<%=assessments[i].getAssessment_ID()%>"><%=assessments[i].getSession_Name()%></option>
				<% } %>
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
			int perceptionuserid;
			try {
				perceptionuserid = new Integer(qmwise.getStub().getParticipantByName(sessionUser.getUserName()).getParticipant_ID()).intValue();
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
	<bbNG:receipt type="FAIL"
		title="Error retrieving participant from Perception">
		<%=qe.getMessage()%>
	</bbNG:receipt>
	<%
				return;
			}

			ScheduleV42[] schedulesarray;
			try {
				schedulesarray = qmwise.getStub().getScheduleListByParticipantV42(perceptionuserid);
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
	<bbNG:receipt type="FAIL"
		title="Error getting participant schedule list">
		<%=qe.getMessage()%>
	</bbNG:receipt>
	<%
				return;
			}

			Vector<ScheduleV42> schedules = new Vector<ScheduleV42>();

			for(int i = 0; i < schedulesarray.length; i++) {
				if(schedulesarray[i].getGroup_ID() == perceptiongroupid)
					schedules.add(schedulesarray[i]);
			}

			ScheduleV42[] zeroschedulesarray;
			try {
				zeroschedulesarray = qmwise.getStub().getScheduleListByParticipantV42(0);
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
	<bbNG:receipt type="FAIL" title="Error getting zero user schedule list">
		<%=qe.getMessage()%>
	</bbNG:receipt>
	<%
				return;
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
				Long schedule_start = schedules.get(i).getSchedule_Starts().getTime().getTime();
				Long schedule_stop = schedules.get(i).getSchedule_Stops().getTime().getTime();
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
							new Parameter("bb_scheduleid", new Integer(schedules.get(i).getSchedule_ID()).toString())
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
					} catch(Exception e) {
						QMWiseException qe = new QMWiseException(e);
						%>
	<bbNG:receipt type="FAIL" title="Error getting assessment URL">
		<%=qe.getMessage()%>
	</bbNG:receipt>
	<%
						return;
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
			<!--<th>Group</th>-->
		</tr>
		<%
				for(int i = 0; i < schedules.size(); i++) {	
					if(schedules.get(i) == null) continue;												
					if(schedulesactive[i] == false) continue;
					if(schedule_name!=null && schedule_name.length()>0 && 
							!schedule_name.equals(schedules.get(i).getSchedule_Name())) continue;						
							//this is what is different in the content item view. Want to see 
							//just the schedule created through the content item creation form.					
					%>
		<tr>
			<!--<td><%=schedules.get(i).getAssessment_ID()%></td>-->
			<td><%=schedules.get(i).getSchedule_Name()%></td>
			<td><%=schedules.get(i).isRestrict_Attempts() ? schedules.get(i).getMax_Attempts() : "no limit"%></td>
			<td><%=!schedules.get(i).isRestrict_Times() ? "None" : schedules.get(i).getSchedule_Starts().getTime().toString()%></td>
			<td><%=!schedules.get(i).isRestrict_Times() ? "None" : schedules.get(i).getSchedule_Stops().getTime().toString()%></td>
			<td>
			<% if(schedulesactive[i]) { %> <% if(scheduleurls[i] == null) { %> No
			attempts remaining <% } else { %>
			<form><input type="button" value="Take assessment"
				onclick="window.open('<%=scheduleurls[i]%>');"></form>
			<% } %> <% } else { %> inactive <% } %>
			</td>
			<!--<td><%=schedules.get(i).getGroup_ID()%></td>-->
		</tr>
		<% } %>
	</table>
	<%
		}
	%>
</bbNG:learningSystemPage>
