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
		com.questionmark.QMWISe.*
	"%>

<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ taglib uri="/bbData" prefix="bbData"%>

<bbData:context id="ctx">
	<bbUI:docTemplate title="Questionmark Perception Tool">

	<% QMPControlPanel panel=new QMPControlPanel(request,ctx); %>
	<%
		if (panel.failTitle == null) {
			if (panel.isAdministrator) {
			%>
			<bbUI:actionBar>
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
				if (panel.courseSettings.getProperty("hide_schedules","0").equals("1")) {
					%>
					<h2>Schedules are Hidden</h2>
					<p>Schedules have been hidden from students on this page.  This setting is controlled
					in the Questionmark control panel for this course.</p>
					<%				
				} else {
					%>
					<p><em>Warning:</em> you are currently logged in with a Perception administrator role.
					You are not subject to schedule restrictions and may use the "Start" link to try
					out the assessment.</p>
					<%
				}
			}
			if (! panel.courseSettings.getProperty("hide_schedules","0").equals("1")) {
				%>
				<bbUI:list collection="<%=panel.scheduleInfo %>" objectId="s"
					className="com.questionmark.ScheduleInfo" collectionLabel="Scheduled Assessments">
					<!-- emptyMsg="There are no assessments scheduled for this course" showAll="true"  -->
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
	</bbUI:docTemplate>
</bbData:context>

