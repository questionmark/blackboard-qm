<%@ page
	language="java"
	pageEncoding="UTF-8"
	import="
		java.util.*,
		java.text.*,
		blackboard.platform.*,
		blackboard.base.*,
		blackboard.platform.session.*,
		blackboard.data.user.*,
		blackboard.persist.* ,
		blackboard.persist.user.*,
		blackboard.data.course.*,
		blackboard.persist.course.*,
		org.apache.axis.*,
		java.rmi.RemoteException,
		javax.xml.namespace.QName,
		com.questionmark.*,
		com.questionmark.QMWISe.*
	"
%>

<%@ taglib uri="/bbUI" prefix="bbUI" %> 
<%@ taglib uri="/bbData" prefix="bbData" %>
<%@ taglib uri="/bbNG" prefix="bbNG" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<bbNG:learningSystemPage ctxId="ctx" title="Questionmark Perception connector" onLoad="disable_set_access()">
	<bbNG:pageHeader>
		<bbNG:pageTitleBar iconUrl='<%=path+"/images/qm.gif"%>' title="Questionmark Perception connector"/>
	</bbNG:pageHeader>

	<%
		// Retrieve the course identifier from the URL
		String courseId = request.getParameter("course_id");

		if(courseId == null) {
			%>
			<bbUI:receipt type="FAIL" title="No course ID was given">
				No course ID was given with the request
			</bbUI:receipt>
			<%
			return;
		}

		//create a ConfigFileReader, to check whether this course needs 
		//to sync its members and to show date last synchronized
		ConfigFileReader configReader = new ConfigFileReader(courseId);

		//connect to QMWise
		QMWise qmwise;
		try {
			qmwise = new QMWise();
		} catch(Exception e) {
			QMWiseException qe = new QMWiseException(e);
			%>
			<bbUI:receipt type="FAIL" title="Error connecting to Perception server">
				<%=qe.getMessage()%>
			</bbUI:receipt>
			<%
			return;
		}

		//-----------------------------------------------------------------------
		//synchronization
		//-----------------------------------------------------------------------

		//read in synchronization period
		PropertiesBean pb = new PropertiesBean();
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
					<bbUI:receipt type="FAIL" title="Error synchronizing course users with Perception">
						<%=e.getMessage()%>
					</bbUI:receipt>
					<%
					return;
				}
			}
		}

		//Retrieve the Db persistence manager from the persistence 
		//service
		BbPersistenceManager bbPm = BbServiceManager.getPersistenceService().getDbPersistenceManager();
		
		// Generate a persistence framework course Id to be used for 
		// loading the course
		Id courseIdObject = bbPm.generateId(Course.COURSE_DATA_TYPE, courseId);

		CourseDbLoader courseLoader = (CourseDbLoader) bbPm.getLoader(CourseDbLoader.TYPE);
		Course course = courseLoader.loadById(courseIdObject);

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
					<li class="mainButton" nowrap="nowrap">
						<a href='<%=path+"/links/forcesync.jsp?course_id="+courseId%>'>Synchronize users now</a>
					</li>
					<%
					if(pb.getProperty("perception.singlesignon") != null) {
						%>
						<li class="mainButton" nowrap="nowrap">
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
				if(
					schedule_start > now //not started yet
					|| schedule_stop >= 0 && schedule_stop < now //stop time is set (not 0001 AD) && already finished
				) {
					schedulesactive[i] = false;
				} else {
					schedulesactive[i] = true;
				}
			}

			%>
			<table>
				<tr>
					<!--<th>Assessment ID</th>-->
					<!--<th>Schedule name</th> Requires QMWISe fix -->
					<th>Maximum attempts</th>
					<th>Start datetime</th>
					<th>End datetime</th>
					<th>Active?</th>
					<th>URL</th>
					<!--<th>Group</th>-->
				</tr>
				<%
				for(int i = 0; i < schedules.size(); i++) {
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
						<!--<td><%=schedules.get(i).getGroup_ID()%></td>-->
					</tr>
				<% } %>
			</table>
			<bbUI:spacer height="20" />

			<%
			//-----------------------------------------------------------------------
			// Results
			//-----------------------------------------------------------------------
			Result[] results;
			try {
				results = qmwise.getStub().getResultListByGroup(course.getBatchUid());
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
				<bbUI:receipt type="FAIL" title="Error getting results list">
					<%=qe.getMessage()%>
				</bbUI:receipt>
				<%
				return;
			}
			//sort results by date
			Arrays.sort(results, new ResultComparator());

			//get report for each result
			String[] reports = new String[results.length];
			try {
				for(int i = 0; i < results.length; i++) {
					reports[i] = qmwise.getStub().getAccessReport(results[i].getResult_ID());
				}
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
				<bbUI:receipt type="FAIL" title="Error getting coaching report">
					<%=qe.getMessage()%>
				</bbUI:receipt>
				<%
				return;
			}

			//date format
			DateFormat pdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

			int resultsPerPage;
			if(request.getParameter("resultsPerPage") != null)
				resultsPerPage = new Integer(request.getParameter("resultsPerPage")).intValue();
			else
				resultsPerPage = 10;
			
			if(resultsPerPage < 10) resultsPerPage = 10;
			%>
			<h1 id="Results">Results</h1>
			<% if (results.length == 0) { %>
				<p>There are not yet results for this course.</p>
			<% } else { %>
				<form action='<%=path+"/links/main.jsp#Results"%>' method="GET" >
					<input type="hidden" name="course_id" value="<%=courseId%>" />
					Results to show per page: <input type="text" size="4" name="resultsPerPage" value="<%=resultsPerPage %>" />
					<input type="submit" value="Update table" />
				</form>
				<table>
					<tr>
						<!--<th>Assessment ID</th>-->
						<th>Schedule Name</th>
						<th>Participant</th>
						<th>Score</th>
						<th>Time taken</th>
						<th>Started</th>
						<th>Finished</th>
						<th>Report</th>
					</tr>
					<%
					int listStart;
					
					if(request.getParameter("resultPage") != null)
						listStart = (new Integer(request.getParameter("resultPage")) -1 ) * new Integer(resultsPerPage);
					else
						listStart = 0;
					if(listStart < 0)
						listStart = 0;
					
					for(int i = listStart; i < results.length && i < listStart+resultsPerPage; i++) {
						Date started = null;
						Date finished = null;
						try {
							started = pdf.parse(results[i].getWhen_Started());
							if(!results[i].isStill_Going()) {
								finished = pdf.parse(results[i].getWhen_Finished());
							}
						} catch(ParseException e) {
							%>
							<bbUI:receipt type="FAIL" title="Error parsing date from Perception">
								<%=e.getMessage()%>
							</bbUI:receipt>
							<%
							return;
						}
						%>
						<tr>
							<!--<td><%=results[i].getAssessment_ID()%></td>-->
							<!--<td><%=results[i].getSchedule_Name()%></td> Requires QMWISe fix-->
							<td><%=results[i].getParticipant() + " (" + results[i].getSpecial_1() + " " + results[i].getSpecial_2() + ")"%></td>
							<td><%=!results[i].isStill_Going() ? results[i].getTotal_Score() + "/" + results[i].getMax_Score() + " (" + results[i].getPercentage_Score() + "%)" : ""%></td>
							<td><%=!results[i].isStill_Going() ? results[i].getTime_Taken() + "s" : ""%></td>
							<td><%=started.toString()%></td>
							<td><%=!results[i].isStill_Going() ? finished.toString() : "Unfinished"%></td>
							<td><a href="<%=reports[i]%>" target="_blank">View report</a></td>
						</tr>
					<% } %>
				</table>
				<%
				
				int numPages = new Double(Math.ceil(results.length/new Double(resultsPerPage))).intValue();
				
				out.println("<p>Page: ");
				
				for(int i = 1; i <= numPages; i++) {
					if(request.getParameter("resultPage") != null && new Integer(request.getParameter("resultPage")).intValue() == i) {
						out.println("<strong>" + i + "</strong> ");
					} else {
						out.println("<a href=\""+path+"/links/main.jsp?course_id=" + courseId + 
							"&amp;resultPage=" + i +
							"&amp;resultsPerPage=" + resultsPerPage +
							"#Results\">" + i + "</a> ");
					}
				}
				
				out.println("</p>");
			
				%>
			<% } //fi results %>
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
				<p>There are no assessments defined in Perception so you cannot schedule an assessment.<p>
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

				<form name="schedule_assessment" action='<%=path+"/links/scheduleassessment.jsp"%>' method="post">
					<bbUI:step title="Enter Information" number="1">
						<bbUI:dataElement label="Schedule name">
							<input type="text" name="schedule" /><br />
							The schedule name must be unique if results are to be stored in the gradebook
						</bbUI:dataElement>
						<bbUI:dataElement label="Store results in gradebook?">
							<input type="checkbox" id="use_gradebook" name="use_gradebook" value="true" checked="checked">
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
						<input type="hidden" name="course_id" value="<%=courseId%>" />
					</bbUI:step>
					<bbUI:stepSubmit title="Submit" number="2" />
				</form>
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
