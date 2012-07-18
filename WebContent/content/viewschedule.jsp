<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		org.apache.commons.lang.StringEscapeUtils,
		java.rmi.RemoteException,
		javax.xml.namespace.QName,
		com.questionmark.*,
		com.questionmark.QMWISe.*"	

	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="/bbData" prefix="bbData" %>
<%@ taglib uri="/bbNG" prefix="bbNG"%>


<bbNG:learningSystemPage ctxId="ctx">
	<% QMPContentView cv=new QMPContentView(request,ctx); %>
	<bbNG:pageHeader>
		<bbNG:pageTitleBar iconUrl='<%=cv.path+"/images/qm.gif"%>'
			title='<%=StringEscapeUtils.escapeHtml(cv.title)%>' />
		<bbNG:breadcrumbBar environment="COURSE" isContent="true">
			<bbNG:breadcrumb>Schedule Details</bbNG:breadcrumb>
		</bbNG:breadcrumbBar>
	</bbNG:pageHeader>	

		<%
		if (cv.failTitle == null) {
			if (cv.isAdministrator) {
				%>
				<bbNG:actionControlBar showWhenEmpty="true">	
				<%
				if(cv.pb.getProperty("perception.singlesignon") != null) {
					%>
					<bbNG:actionButton url='<%=cv.path+"/links/enterprisemanager.jsp?course_id="+cv.courseId %>' 
						title="Log in to Enterprise Manager" target="_blank"/>
					<%
				}
				%>
				</bbNG:actionControlBar>

				<p><em>Warning:</em> you are currently logged in with a Perception administrator role.
				You are not subject to schedule restrictions and may use the "Start" link to try
				out the assessment.</p>
				<%
			}
			%>
			<bbNG:inventoryList collection="<%=cv.scheduleInfo %>" objectVar="s"
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
			if (cv.isAdministrator) {
				if (cv.contentItem.gradebookScore.equals("no")) {
			%>
			<p>The results of this assessment will not be stored in the grade center.</p>
			<%
				} else {
			%>
			<p>The <%=cv.contentItem.gradebookScoreType.toLowerCase()%> result of this assessment will be stored in the grade center.</p>
			<%
				}
			}
		} else {
		%>
		<bbNG:receipt type="FAIL" title="<%=cv.failTitle %>">
			<%=cv.failMsg %>
		</bbNG:receipt>
		<%
	} //End of other view
%>
</bbNG:learningSystemPage>
	
