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

<bbData:context id="ctx">
	<% QMPControlPanel panel=new QMPControlPanel(request,ctx); %>
	<bbUI:docTemplate title='<%=StringEscapeUtils.escapeHtml(panel.panelTitle)%>'>
		<bbUI:coursePage>
		<%
			if (panel.failTitle == null) {
	
				if (panel.isAdministrator && !panel.linkView) {
					boolean sync_users=(panel.pb.getProperty("perception.syncusers")!=null);
					boolean sync_members=(panel.pb.getProperty("perception.syncmembers")!=null);
		%>	
			<bbUI:breadcrumbBar environment="COURSE" isContent="true">
				<bbUI:breadcrumb>Questionmark Control Panel</bbUI:breadcrumb>
			</bbUI:breadcrumbBar>		
	
			<bbUI:actionBar>
				<%
					if (sync_users || sync_members) {
				%>
				<bbUI:actionItem title="Synchronize Now" href='<%=panel.path+"/links/forcesync.jsp?course_id="+panel.courseId%>'
					imgUrl='<%=panel.path+"/images/sync.gif" %>'/>
				<%
					}
				%>
				<bbUI:actionItem title="View results" href='<%=panel.path+"/links/viewresults.jsp?course_id="+panel.courseId%>'
					imgUrl='<%=panel.path+"/images/results.gif" %>'/>
				<%
					if(panel.pb.getProperty("perception.singlesignon") != null) {
				%>
				<bbUI:actionItem title="Log in to Enterprise Manager" href='<%=panel.path+"/links/enterprisemanager.jsp?course_id="+panel.courseId %>'
					imgUrl='<%=panel.path+"/images/link.gif" %>' target="_blank"/>
				<%
					}
				%>
			</bbUI:actionBar>
			<%
					if (sync_users || sync_members) {
			%>
		<h1 id="Syncdetails">Synchronization</h1>
			<%
						if (panel.courseSettings.getProperty("lastsync")==null) {
			%>
			<p>This course has never been fully synchronized.<br/></p>
			<%
						} else {
			%>
			<p>This course was last fully synchronized: <%=panel.courseSettings.getProperty("lastsync") %><br/></p>
			<%
						}
			%>
			<p>You can force a full synchronization using the "Synchronize Now" button above.</p>
			<%
					}
			%>
	
		<h1>Course Settings</h1>
	
		<form name="course_settings" action='<%=panel.path+"/links/coursesettings.jsp"%>' method="post">
			<bbUI:step number="1" title="Course Settings">
				<bbUI:dataElement label="Hide schedules from students in Course Tool view?" >							
				<input type="checkbox" id="hide_schedules" name="hide_schedules" value="Yes" <%=panel.courseSettings.getProperty("hide_schedules","0").equals("1")?"checked":""%> />
				<br/>
				<i>Use this option if you are creating schedules using content items to prevent students from seeing the schedule list in the Course Tool view of the connector. <br/>
				Hidden schedules can still be accessed individually using the schedule's URL Link below.</i>
				</bbUI:dataElement>
			</bbUI:step>
			<input type="hidden" name="course_id" value="<%=panel.courseId%>" />
			<bbUI:stepSubmit number="2" title="Submit" instructions="Save changes to course settings" />
		 </form>
		
		<h1 id="Schedules">Assessment Schedules</h1>
		
		<p>This list does <em>not</em> show schedules associated with content items, these schedules
		are visible to students in the connectors course tool view unless <em>Hide schedules...</em>
		has been selected above.</p>
			
		<bbUI:list collection="<%=panel.scheduleInfo %>" objectId="s"
			className="com.questionmark.ScheduleInfo" collectionLabel="Scheduled Assessments">
			<!--  emptyMsg="No assessments scheduled" showAll="true"  -->
			<bbUI:listElement name="schedule" label="Schedule Name">
				<%=s.DisplayName() %>
			</bbUI:listElement>
			<bbUI:listElement name="max_attempts" label="Maximum attempts">
				<%=s.schedule.isRestrict_Attempts() ? s.schedule.getMax_Attempts() : "no limit" %>
			</bbUI:listElement>
			<bbUI:listElement name="start_time" label="Start Time">
				<%=s.schedule.isRestrict_Times() ? s.schedule.readSchedule_Starts_asCalendar().getTime().toString() : "" %>
			</bbUI:listElement>
			<bbUI:listElement name="end_time" label="End Time">
				<%=s.schedule.isRestrict_Times() ? s.schedule.readSchedule_Stops_asCalendar().getTime().toString() : "" %>
			</bbUI:listElement>
			<bbUI:listElement name="active" label="Active?">
				<%=s.active ? "active" : "inactive" %>
			</bbUI:listElement>
			<bbUI:listElement name="tryout" label="Launch">
				<% if (s.launchURL!=null) {
					%><a href="<%=s.launchURL%>" target="_blank">Try out</a><%
				} else {
					%>Unavailable<%
				}
				%>
			</bbUI:listElement>
			<bbUI:listElement name="url" label="URL">
				<a href="<%=panel.basePath+"links/main.jsp?course_id="+panel.courseId+"&amp;schedule_name=" + 
					StringEscapeUtils.escapeHtml(URLEncoder.encode(s.schedule.getSchedule_Name(),"UTF-8"))%>">Link</a>
			</bbUI:listElement>
		</bbUI:list>
		
		<h1 id="Scheduleform">Schedule an Assessment</h1>
		<%
	
				Calendar startdate = Calendar.getInstance();
				Calendar enddate = Calendar.getInstance();
				enddate.add(Calendar.DAY_OF_MONTH, 7);			
				DateFormat minFormat = new SimpleDateFormat("mm");
				DateFormat hourFormat = new SimpleDateFormat("HH");
				if (panel.selectAssessmentList.length == 0) {
		%>
		<p>You do not have permission to schedule any assessments in Perception.</p>
	
		<%		} else { 
		%>
		<script type="text/javascript">
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
	
		<form name="schedule_assessment" action='<%=panel.path+"/links/scheduleassessment.jsp"%>' method="post">
			<bbUI:step title="Enter Information">
				<bbUI:dataElement required="true" label="Schedule name">
					<input type="text" name="schedule"  /><br />
					The schedule name must be unique if results are to be stored in the Grade Center
				</bbUI:dataElement>	
				<bbUI:dataElement label="Store results in Grade Center?">
					<select name="use_gradebook">
						<option value="percent" selected="selected">as percentage scores</option>
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
				<bbUI:dataElement label="Select assessment to schedule">
					<select name="assessment">
						<% 
						for(int i = 0; i < panel.selectAssessmentList.length; i++) { 
							%>
						<%=panel.selectAssessmentList[i].toString() %>
						<%
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
					<input type="checkbox" id="set_access_period"
						name="set_access_period" value="true" />
						<br/>							
				</bbUI:dataElement>
					<bbUI:dataElement label="Start date">
						<bbUI:datePicker startDate="<%= startdate%>" formName="schedule_assessment" startCaption="Start" startDateField="start" />
					</bbUI:dataElement>
					<bbUI:dataElement label="Start time (24-hour HH:MM)">
						<input type="text" id="start_hour" name="start_hour" size="2" disabled value="<%=hourFormat.format(startdate.getTime()) %>" /> :
						<input type="text" id="start_minute" name="start_minute" size="2" disabled value="<%=minFormat.format(startdate.getTime()) %>" />
					</bbUI:dataElement>
					<bbUI:dataElement label="End date">
						<bbUI:datePicker startDate="<%=enddate%>" formName="schedule_assessment" startCaption="End" startDateField="end" />
					</bbUI:dataElement>
					<bbUI:dataElement label="End time (24-hour HH:MM)">
						<input type="text" id="end_hour" name="end_hour" size="2" disabled value="<%=hourFormat.format(enddate.getTime()) %>" /> :
						<input type="text" id="end_minute" name="end_minute" size="2" disabled value="<%=minFormat.format(enddate.getTime()) %>" />
					</bbUI:dataElement>
			</bbUI:step> 
							
			<input type="hidden" name="group" value="<%=panel.course.getBatchUid()%>" />
			<input type="hidden" name="course_id" value="<%=panel.courseId%>" />
			<bbUI:stepSubmit title="Submit"/>						
		</form>
	
		<%
				}
			} else { // if (panel.isAdministrator && !panel.linkView)
		%>
		<h1>Assessment Link</h1>
	
		<% 
				if (panel.isAdministrator) {
		%>
		<p><em>Warning:</em> you are currently logged in with a Perception administrator role.
		You are not subject to schedule restrictions and may use the "Start" link to try
		out the assessment.</p>
		<%
				}
		%>
		<bbUI:list collection="<%=panel.scheduleInfo %>" objectId="s"
			className="com.questionmark.ScheduleInfo" collectionLabel="Scheduled Assessments">
			<!--  emptyMsg="No matching assessment could be found" showAll="true"  -->
			<bbUI:listElement name="schedule_name" label="Schedule Name">
				<%=s.DisplayName() %>
			</bbUI:listElement>
			<bbUI:listElement name="max_attempts" label="Attempts Remaining">
				<%=s.schedule.isRestrict_Attempts() ? s.schedule.getMax_Attempts() : "no limit" %>
			</bbUI:listElement>
			<bbUI:listElement name="start_time" label="Start Time">
				<%=s.schedule.isRestrict_Times() ? s.schedule.readSchedule_Starts_asCalendar().getTime().toString() : "" %>
			</bbUI:listElement>
			<bbUI:listElement name="end_time" label="End Time">
				<%=s.schedule.isRestrict_Times() ? s.schedule.readSchedule_Stops_asCalendar().getTime().toString() : "" %>
			</bbUI:listElement>
			<bbUI:listElement name="active" label="Active?">
				<%=s.active ? "active" : "inactive" %>
			</bbUI:listElement>
			<bbUI:listElement name="launch" label="Launch">
				<% if (s.launchURL!=null) {
					%><a href="<%=s.launchURL%>" target="_blank">Start the Test</a><%
				} else {
					%>Unavailable<%
				}
				%>
			</bbUI:listElement>
		</bbUI:list>
	
		<%			
				}
			} else {
		%>
	
		<bbUI:receipt type="FAIL" title="<%=panel.failTitle %>">
			<%=panel.failMsg %>
		</bbUI:receipt>
	
		<%
			} //End of other view
		%>
		</bbUI:coursePage>
	</bbUI:docTemplate>
</bbData:context>

