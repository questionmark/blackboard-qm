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
<%@ taglib uri="/bbNG" prefix="bbNG"%>

<bbNG:learningSystemPage ctxId="ctx" title="Questionmark Perception">

	<% QMPControlPanel panel=new QMPControlPanel(request,ctx); %>
	<bbNG:pageHeader>
		<bbNG:pageTitleBar iconUrl='<%=panel.path+"/images/qm.gif"%>'
			title='Questionmark Perception Tool' />
	</bbNG:pageHeader>	

	<%
		if (panel.failTitle == null) {
			if (panel.isAdministrator) {
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
				<bbNG:inventoryList collection="<%=panel.scheduleInfo %>" objectVar="s"
					className="com.questionmark.ScheduleInfo" description="Scheduled Assessments"
					emptyMsg="There are no assessments scheduled for this course" showAll="true">
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
			<bbNG:receipt type="FAIL" title="<%=panel.failTitle %>">
				<%=panel.failMsg %>
			</bbNG:receipt>
			<%
		} //End of other view
	%>

</bbNG:learningSystemPage>
