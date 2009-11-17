<!-- 
// Filename		: content/view.jsp
// Description	: To view the created content item.
-->


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="
		java.util.*,
		java.text.*,
		java.net.*,
		blackboard.base.*,
		blackboard.platform.*,
		blackboard.platform.session.*,
		blackboard.platform.persistence.*,
		blackboard.platform.plugin.*,
		blackboard.platform.security.authentication.*,
		blackboard.data.user.*,
		blackboard.persist.* ,
		blackboard.persist.user.*,
		blackboard.data.course.*,
		blackboard.data.content.*,
		blackboard.persist.content.*,
		blackboard.persist.content.ContentDbLoader,
		blackboard.persist.Id,		
		blackboard.persist.course.*,
		blackboard.platform.persistence.PersistenceServiceFactory,
		org.apache.axis.*,
		java.rmi.RemoteException,
		javax.xml.namespace.QName,
		com.questionmark.*,
		com.questionmark.QMWISe.*"	

	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="/bbUI" prefix="bbUI" %> 
<%@ taglib uri="/bbData" prefix="bbData" %>
<%@ taglib uri="/bbNG" prefix="bbNG"%>


<bbNG:learningSystemPage ctxId="contentCtx">

	<%	
	
	String course_id = request.getParameter("course_id");
	String content_id = request.getParameter("content_id");


	
	String path = request.getContextPath();
	

	//Get a User , course membership details, and Course instance via context
	User sessionUser = contentCtx.getUser();
	Id sessionUserId = sessionUser.getId();
	Course courseCtx = contentCtx.getCourse();	
	CourseMembership cMembership = contentCtx.getCourseMembership(); // may be null if a SysAdmin!	
	

	Id contentId = Id.generateId(Content.DATA_TYPE, content_id);
	ContentDbLoader courseDocumentLoader = ContentDbLoader.Default.getInstance();
	Content courseDoc = courseDocumentLoader.loadById( contentId ); 

	// can now query this...
	String parent_id = courseDoc.getParentId().toExternalString();
			
	String schedule_name = courseDoc.getTitle();
	
	%>

	<bbNG:pageHeader instructions="This is the content view page."> 
		<bbNG:breadcrumbBar environment="COURSE" isContent="true">
			<bbNG:breadcrumb><%=schedule_name%></bbNG:breadcrumb>
		</bbNG:breadcrumbBar>
		<bbNG:pageTitleBar iconUrl="../images/qm.gif" showTitleBar="true"
			title="<%=schedule_name%>" />
	</bbNG:pageHeader>		
		
	<p>
		<%out.println("Schedule name from form: " + schedule_name); 
			out.println("Parent id: " + parent_id);		
		%>
	</p>
	
	
	<%

	//to sync its members and to show date last synchronized
	ConfigFileReader configReader = new ConfigFileReader(course_id);
	//load the courseSettings file too...
	CourseSettings courseSettings = new CourseSettings(course_id);
	
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
	Id courseIdObject = bbPm.generateId(Course.DATA_TYPE, course_id);

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
			System.out.println("Perception: course " + course_id + ": it's been more than the sync period since last sync -- syncing users");
			UserSynchronizer us = new UserSynchronizer();
			try {
				us.synchronizeCourse(course_id);
				configReader.setCourseSyncDate();
			} catch (Exception e) {
				System.out.println("Perception: course " + course_id + ": synchronization failed: " + e.getMessage());
				%>
				<bbUI:receipt type="FAIL" title="Error synchronizing course users with Perception">
					<%=e.getMessage()%>
				</bbUI:receipt>
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
			System.out.println("Perception: course " + course_id + ": Perception group doesn't exist -- forcing synchronization");
			UserSynchronizer us = new UserSynchronizer();
			try {
				us.synchronizeCourse(course_id);
				configReader.setCourseSyncDate();
				//get fresh group
				perceptiongroupid = new Integer(qmwise.getStub().getGroupByName(course.getBatchUid()).getGroup_ID()).intValue();
			} catch (Exception ne) {
				System.out.println("Perception: course " + course_id + ": synchronization failed: " + ne.getMessage());
				%>
				<bbUI:receipt type="FAIL" title="Error synchronizing course users with Perception">
					<%=ne.getMessage()%>
				</bbUI:receipt>
				<%
				return;
			}
		} else {
			%>
			<bbUI:receipt type="FAIL" title="Error retrieving course group from Perception">
				<%=qe.getMessage()%>
			</bbUI:receipt>
			<%
			return;
		}
	}

	//-----------------------------------------------------------------------
	//view specific to current user
	//-----------------------------------------------------------------------

	
	// get the membership data to determine the User's Role
	CourseMembershipDbLoader crsMembershipLoader = (CourseMembershipDbLoader) bbPm.getLoader(CourseMembershipDbLoader.TYPE);
	CourseMembership crsMembership = null;

	try {
		crsMembership = crsMembershipLoader.loadByCourseAndUserId(courseIdObject, sessionUserId);
	} catch (KeyNotFoundException e) {
		// There is no membership record.
		%>
		<bbUI:receipt type="FAIL" title="You don't have a role on this course">
			<%=e.getMessage()%>
		</bbUI:receipt>
		<%
		return;
	} catch (PersistenceException pe) {
		// There is no membership record.
		%>
		<bbUI:receipt type="FAIL" title="Error loading the current user">
			<%=pe.getMessage()%>
		</bbUI:receipt>
		<%
		return;
	}

	if(crsMembership.getRole() == CourseMembership.Role.INSTRUCTOR
		|| crsMembership.getRole() == CourseMembership.Role.TEACHING_ASSISTANT) {
		//-----------------------------------------------------------------------
		//Administrator or TA
		//-----------------------------------------------------------------------

		%>
		<div id="actionbar" class="actionBar clearfix editmode">
			<ul id="nav" class="nav clearfix">
				<li class="mainButton" >
					<a href='<%=path+"/links/forcesync.jsp?course_id="+course_id%>'>Synchronize users now</a>
				</li>
				<li class="mainButton" >
					<a href='<%=path+"/links/viewresults.jsp?course_id="+course_id%>'>View results</a>
				</li>
				<%
				if(pb.getProperty("perception.singlesignon") != null) {
					%>
					<li class="mainButton" >
						<a href='<%=path+"/links/enterprisemanager.jsp"%>' target="_blank">Log in to Enterprise Manager</a>
					</li>
					<%
				}
				%>
			</ul>
		</div>

		<h1 id="Syncdetails">Synchronization details</h1>
		<p>Users of this course were last synchronized <%=new Date(configReader.getCourseSyncDate()).toString()%></p>
		<bbUI:spacer height="20" />

		<h1 id="CourseSettings">Course Settings</h1>			
		<form name="course_settings" action='<%=path+"/links/coursesettings.jsp"%>' method="post">
			<bbUI:step title="Enter Information" number="1">
				<bbUI:dataElement label="Hide all schedules from students?">						 
					<% if (courseSettings.getProperty("hide_schedules","0").equals("1")) { %>
						<input type="checkbox" id="hide_schedules" name="hide_schedules" value="true" checked="checked" />
					<% } 
					else {
					%>
						<input type="checkbox" id="hide_schedules" name="hide_schedules" value="false" />
					<% } %>
					<p><i>Hidden schedules can still be accessed using the schedule's URL available below.</i></p>
				</bbUI:dataElement>
				<input type="hidden" name="course_id" value="<%=course_id%>" />
			</bbUI:step>
			<bbUI:stepSubmit title="Submit" number="2" />
		</form>
		
		<h1 id="Schedules">Schedules</h1>
		<%
		ScheduleV42[] schedulesarray;
		try {
			schedulesarray = qmwise.getStub().getScheduleListByParticipantV42(new Integer(UserSynchronizer.getPhantomUserId()).intValue());
		} catch(Exception e) {
			QMWiseException qe = new QMWiseException(e);
			%>
			<bbUI:receipt type="FAIL" title="Error getting group schedule list">
				<%=qe.getMessage()%>
			</bbUI:receipt>
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
			<bbUI:receipt type="FAIL" title="Error getting zero user schedule list">
				<%=qe.getMessage()%>
			</bbUI:receipt>
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
				<bbUI:receipt type="FAIL" title="Error getting assessment URL">
					<%=qe.getMessage()%>
				</bbUI:receipt>
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
		<table>
			<script  type="text/javascript">
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
					<td><a href="<%=scheduleurls[i]%>" target="_blank">Test assessment</a></td>
					<td><input type="checkbox" name="switchBox" onClick="showhideScheduleURL(this,'<%=idStr%>')" /></td>
					<!--<td><%=schedules.get(i).getGroup_ID()%></td>-->
				</tr>
				<tr id='<%=idStr%>' style="display:none;">
					<td><i>URL:</i></td>
					<td colspan="6"><code><%=path+"links/main.jsp?course_id="+course_id+"&amp;schedule_name="+URLEncoder.encode(schedules.get(i).getSchedule_Name())%></code></td>
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
			<bbUI:receipt type="FAIL" title="Error getting Perception administrator ID">
				<%=qe.getMessage()%>
			</bbUI:receipt>
			<%
			return;
		}

		Assessment[] assessments;
		try {
			assessments = qmwise.getStub().getAssessmentListByAdministrator(adminid);
		} catch(Exception e) {
			QMWiseException qe = new QMWiseException(e);
			%>
			<bbUI:receipt type="FAIL" title="Error getting list of available assessments">
				<%=qe.getMessage()%>
			</bbUI:receipt>
			<%
			return;
		}

		if(assessments.length == 0) { %>
			<p>There are no assessments defined in Perception so you cannot schedule an assessment.</p>
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

			<form name="schedule_assessment" action='<%=path+"/content/content_schedule_assessment.jsp"%>' method="post">
				
				<input type="hidden" name="course_id" value="<%=request.getParameter("course_id")%>"/>
				<input type="hidden" name="parent_id" value="<%=request.getParameter("content_id")%>"/>					
				
				
				<bbUI:step title="Enter Information" number="1">					
					<bbUI:dataElement label="Schedule name">
						<input type="text" name="schedule_name" /><br />
						The schedule name must be unique if results are to be stored in the gradebook
					</bbUI:dataElement>
					<bbUI:dataElement label="Store results in gradebook?">
						<select name="use_gradebook">
							<option value="percent" selected="selected">as percentage scores</option>
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
						<input type="checkbox" id="limit_attempts" name="limit_attempts" value="true" onclick="disable_limit_attempts()" />
						<input type="text" id="limit" name="limit" size="4" disabled value="1" />
					</bbUI:dataElement>
					<bbUI:dataElement label="Create schedule for each group participant?">
						<input type="checkbox" id="per_participant" name="per_participant" value="true" onclick="set_limit_attempts_hidden()" />
						<input type="hidden" id="per_participant_hidden" name="per_participant_hidden" value="0" />
					</bbUI:dataElement>
					<bbUI:dataElement label="Set access period?">
						<input type="checkbox" id="set_access_period" name="set_access_period" value="true" onclick="disable_set_access()" />
						<bbUI:dataElement label="Start date">
							<bbUI:datePicker startDate="<%=startdate%>" formName="schedule_assessment" startCaption="Start" startDateField="start" />
						</bbUI:dataElement>
						<bbUI:dataElement label="Start time (24-hour HH:MM)">
							<input type="text" id="start_hour" name="start_hour" size="2" disabled value="09" /> :
							<input type="text" id="start_minute" name="start_minute" size="2" disabled value="00" />
						</bbUI:dataElement>
						<bbUI:dataElement label="End date">
							<bbUI:datePicker startDate="<%=enddate%>" formName="schedule_assessment" startCaption="End" startDateField="end" />
						</bbUI:dataElement>
						<bbUI:dataElement label="End time (24-hour HH:MM)">
							<input type="text" id="end_hour" name="end_hour" size="2" disabled value="17" /> :
							<input type="text" id="end_minute" name="end_minute" size="2" disabled value="00" />
						</bbUI:dataElement>
					</bbUI:dataElement>
					<input type="hidden" name="group" value="<%=course.getBatchUid()%>" />
					<input type="hidden" name="course_id" value="<%=course_id%>" />
				</bbUI:step>
				<bbUI:stepSubmit title="Submit" number="2" />
			</form>
		<% }

	} else {
		//-----------------------------------------------------------------------
		//Student
		//-----------------------------------------------------------------------
		%><p>Hello Student, you must be <%=contentCtx.getUser().getGivenName()%> , </p>
		<%
		//get Perception user id
		int perceptionuserid;
		try {
			perceptionuserid = new Integer(qmwise.getStub().getParticipantByName(sessionUser.getUserName()).getParticipant_ID()).intValue();
		} catch(Exception e) {
			QMWiseException qe = new QMWiseException(e);
			%>
			<bbUI:receipt type="FAIL" title="Error retrieving participant from Perception">
				<%=qe.getMessage()%>
			</bbUI:receipt>
			<%
			return;
		}

		ScheduleV42[] schedulesarray;
		try {
			schedulesarray = qmwise.getStub().getScheduleListByParticipantV42(perceptionuserid);
		} catch(Exception e) {
			QMWiseException qe = new QMWiseException(e);
			%>
			<bbUI:receipt type="FAIL" title="Error getting participant schedule list">
				<%=qe.getMessage()%>
			</bbUI:receipt>
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
			<bbUI:receipt type="FAIL" title="Error getting zero user schedule list">
				<%=qe.getMessage()%>
			</bbUI:receipt>
			<%
			return;
		}

		for(int i = 0; i < zeroschedulesarray.length; i++) {
			if(zeroschedulesarray[i].getGroup_ID() == perceptiongroupid)
				schedules.add(zeroschedulesarray[i]);
		}

		String[] scheduleurls = new String[schedules.size()];
		boolean[] schedulesactive = new boolean[schedules.size()];
		
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
					<bbUI:receipt type="FAIL" title="Error getting assessment URL">
						<%=qe.getMessage()%>
					</bbUI:receipt>
					<%
					return;
				}
			}
		}

		%>
		<p>These are the assessments that are scheduled for you, if the list is empty there are no
		assessments scheduled at the current time.</p>
		<table>
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
				if(!schedule_name.equals(schedules.get(i).getSchedule_Name())) continue;
				%>
				<tr>
					<!--<td><%=schedules.get(i).getAssessment_ID()%></td>-->
					<td><%=schedules.get(i).getSchedule_Name()%></td>
					<td><%=schedules.get(i).isRestrict_Attempts() ? schedules.get(i).getMax_Attempts() : "no limit"%></td>
					<td><%=!schedules.get(i).isRestrict_Times() ? "None" : schedules.get(i).getSchedule_Starts().getTime().toString()%></td>
					<td><%=!schedules.get(i).isRestrict_Times() ? "None" : schedules.get(i).getSchedule_Stops().getTime().toString()%></td>
					<td><% if(schedulesactive[i]) { %>
						<% if(scheduleurls[i] == null) { %>
							No attempts remaining
						<% } else { %>
							<form>
								<input type="button" value="Take assessment" onclick="window.open('<%=scheduleurls[i]%>');">
							</form>
						<% } %>
					<% } else { %>
						inactive
					<% } %></td>
					<!--<td><%=schedules.get(i).getGroup_ID()%></td>-->
				</tr>
			<% } %>
		</table>
		<%
	}
	
	
%>		
	


	


</bbNG:learningSystemPage>
