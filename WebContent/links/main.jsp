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

<bbNG:learningSystemPage ctxId="ctx" title="Questionmark Perception connector">

	<% QMPControlPanel panel=new QMPControlPanel(request,ctx); %>
	<bbNG:pageHeader>
		<bbNG:pageTitleBar iconUrl='<%=panel.path+"/images/qm.gif"%>'
			title='<%=StringEscapeUtils.escapeHtml(panel.panelTitle)%>' />
	</bbNG:pageHeader>	

	<%
		if (panel.failTitle == null) {

			if (panel.isAdministrator && !panel.linkView) {
				boolean sync_users=(panel.pb.getProperty("perception.syncusers")!=null);
				boolean sync_members=(panel.pb.getProperty("perception.syncmembers")!=null);
	%>

	<bbNG:actionControlBar showWhenEmpty="true">	
		<%
				if (sync_users || sync_members) {
		%>
		<bbNG:actionButton  url='<%=panel.path+"/links/forcesync.jsp?course_id="+panel.courseId%>' title="Synchronize Now"/>
		<%
				}
		%>
		<bbNG:actionButton url='<%=panel.path+"/links/viewresults.jsp?course_id="+panel.courseId%>' title="View results"/>		
		<%
				if(panel.pb.getProperty("perception.singlesignon") != null) {
		%>
		<bbNG:actionButton url='<%=panel.path+"/links/enterprisemanager.jsp"%>' 
			title="Log in to Enterprise Manager" target="_blank"/>
		<%
				}
		%>	
	</bbNG:actionControlBar>

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
		<bbNG:dataCollection>
			<bbNG:stepGroup active="true" title="Course Settings">
				<bbNG:step title="Enter Information" hideNumber="true">
					<bbNG:dataElement label="Hide schedules from students in Course Tool view?" >							
					<input type="checkbox" id="hide_schedules" name="hide_schedules" value="Yes" <%=panel.courseSettings.getProperty("hide_schedules","0").equals("1")?"checked":""%> />
					<br/>
					<i>Use this option if you are creating schedules using content items to prevent students from seeing the schedule list in the Course Tool view of the connector. <br/>
					Hidden schedules can still be accessed individually using the schedule's URL Link below.</i>
					</bbNG:dataElement>
				</bbNG:step>
				<input type="hidden" name="course_id" value="<%=panel.courseId%>" />
				<bbNG:stepSubmit hideNumber="true" title="Submit" instructions="Save changes to course settings" />
			</bbNG:stepGroup>
		</bbNG:dataCollection>
	 </form>
	
	<h1 id="Schedules">Schedules</h1>
	
	<bbNG:inventoryList collection="<%=panel.scheduleInfo %>" objectVar="s"
		className="com.questionmark.ScheduleInfo" description="Scheduled Assessments"
		emptyMsg="No assessments scheduled" showAll="true">
		<bbNG:listElement name="schedule_name" label="Schedule Name" isRowHeader="true">
			<%=s.DisplayName() %>
		</bbNG:listElement>
		<bbNG:listElement name="max_attempts" label="Maximum attempts">
			<%=s.schedule.isRestrict_Attempts() ? s.schedule.getMax_Attempts() : "no limit" %>
		</bbNG:listElement>
		<bbNG:listElement name="start_time" label="Start Time">
			<%=s.schedule.isRestrict_Times() ? s.schedule.readSchedule_Starts_asCalendar().getTime().toString() : "" %>
		</bbNG:listElement>
		<bbNG:listElement name="end_time" label="End Time">
			<%=s.schedule.isRestrict_Times() ? s.schedule.readSchedule_Stops_asCalendar().getTime().toString() : "" %>
		</bbNG:listElement>
		<bbNG:listElement name="active" label="Active?">
			<%=s.active ? "active" : "inactive" %>
		</bbNG:listElement>
		<bbNG:listElement name="tryout" label="Launch">
			<% if (s.launchURL!=null) {
				%><a href="<%=s.launchURL%>" target="_blank">Try out</a><%
			} else {
				%>Unavailable<%
			}
			%>
		</bbNG:listElement>
		<bbNG:listElement name="url" label="URL">
			<a href="<%=panel.basePath+"links/main.jsp?course_id="+panel.courseId+"&amp;schedule_name=" + 
				StringEscapeUtils.escapeHtml(URLEncoder.encode(s.schedule.getSchedule_Name(),"UTF-8"))%>">Link</a>
		</bbNG:listElement>
	</bbNG:inventoryList>
	
	<h1 id="Scheduleform">Schedule an Assessment</h1>
	<%

			Calendar startdate = Calendar.getInstance();
			Calendar enddate = Calendar.getInstance();
			enddate.add(Calendar.DAY_OF_MONTH, 7);			
			if (panel.assessmentList.length == 0) {
	%>
	<p>You do not have permission to schedule any assessments in Perception.</p>

	<%		} else { 
	%>
	<bbNG:jsBlock>
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
	</bbNG:jsBlock>

	<form name="schedule_assessment" action='<%=panel.path+"/links/scheduleassessment.jsp"%>' method="post">
		<bbNG:dataCollection>			
			<bbNG:step title="Enter Information">
				<bbNG:dataElement isRequired="true" label="Schedule name">
					<bbNG:textElement name="schedule" isRequired="true" maxLength="50" helpText="Maximum 50 characters allowed"/>
					<br />
					The schedule name must be unique if results are to be stored in the Grade Center
				</bbNG:dataElement>	
				<bbNG:dataElement label="Store results in Grade Center?">
					<select name="use_gradebook">
						<option value="percent" selected="selected">as percentage scores</option>
						<option value="point">as point scores</option>
						<option value="no">do not store results in Grade Center</option>
					</select>
				</bbNG:dataElement>						
				<bbNG:dataElement label="Select result to display in Grade Center">
					<select name="result_type">
						<option value="FIRST">First</option>				
						<option value="BEST" selected="selected">Best</option>
						<option value="WORST">Worst</option>
						<option value="LAST">Last</option>
					</select>
				</bbNG:dataElement>
				<bbNG:dataElement label="Assessment name">
					<select name="assessment">
						<% 
						for(int i = 0; i < panel.selectAssessmentList.length; i++) { 
							%>
						<%=panel.selectAssessmentList[i].toString() %>
						<%
						} %>
					</select>
				</bbNG:dataElement>
				<bbNG:dataElement label="Limit attempts?">
					<input type="checkbox" id="limit_attempts" name="limit_attempts"
						value="true" onclick="disable_limit_attempts()" />
					<input type="text" id="limit" name="limit" size="4" disabled
						value="1" />
				</bbNG:dataElement>
				<bbNG:dataElement label="Create schedule for each group participant?">
					<input type="checkbox" id="per_participant" name="per_participant"
						value="true" onclick="set_limit_attempts_hidden()" />
					<input type="hidden" id="per_participant_hidden"
						name="per_participant_hidden" value="0" />
				</bbNG:dataElement>
				<bbNG:dataElement label="Set access period?" isSubElement="true" subElementType="NESTED_LIST">							
					<input type="checkbox" id="set_access_period"
						name="set_access_period" value="true" />
						<br/>
						<br/>							
					<bbNG:dataElement label="Start date">
						<bbNG:datePicker baseFieldName="scheduleStart" dateTimeValue="<%= startdate%>" showDate="true" showTime="true"/>
					</bbNG:dataElement>
					<br/>
					<bbNG:dataElement label="End date">
						<bbNG:datePicker baseFieldName="scheduleEnd" dateTimeValue="<%= enddate%>" showDate="true" showTime="true"/>
					</bbNG:dataElement>
				</bbNG:dataElement>

			</bbNG:step> 
							
			<input type="hidden" name="group" value="<%=panel.course.getBatchUid()%>" />
			<input type="hidden" name="course_id" value="<%=panel.courseId%>" />
			<bbNG:stepSubmit title="Submit"/>
						
		</bbNG:dataCollection>
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
	<bbNG:inventoryList collection="<%=panel.scheduleInfo %>" objectVar="s"
		className="com.questionmark.ScheduleInfo" description="Scheduled Assessments"
		emptyMsg="No matching assessment could be found" showAll="true">
		<bbNG:listElement name="schedule_name" label="Schedule Name" isRowHeader="true">
			<%=s.DisplayName() %>
		</bbNG:listElement>
		<bbNG:listElement name="max_attempts" label="Attempts Remaining">
			<%=s.schedule.isRestrict_Attempts() ? s.schedule.getMax_Attempts() : "no limit" %>
		</bbNG:listElement>
		<bbNG:listElement name="start_time" label="Start Time">
			<%=s.schedule.isRestrict_Times() ? s.schedule.readSchedule_Starts_asCalendar().getTime().toString() : "" %>
		</bbNG:listElement>
		<bbNG:listElement name="end_time" label="End Time">
			<%=s.schedule.isRestrict_Times() ? s.schedule.readSchedule_Stops_asCalendar().getTime().toString() : "" %>
		</bbNG:listElement>
		<bbNG:listElement name="active" label="Active?">
			<%=s.active ? "active" : "inactive" %>
		</bbNG:listElement>
		<bbNG:listElement name="launch" label="Launch">
			<% if (s.launchURL!=null) {
				%><a href="<%=s.launchURL%>" target="_blank">Start the Test</a><%
			} else {
				%>Unavailable<%
			}
			%>
		</bbNG:listElement>
	</bbNG:inventoryList>

	<%			
			}
		} else {
	%>

	<bbNG:receipt type="FAIL" title="<%=StringEscapeUtils.escapeHtml(panel.failTitle) %>">
		<%=StringEscapeUtils.escapeHtml(panel.failText) %>
	</bbNG:receipt>

	<%
		} //End of other view
	%>
</bbNG:learningSystemPage>


