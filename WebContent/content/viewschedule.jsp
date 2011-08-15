<!-- 
// Filename		: content/viewschedule.jsp
// Description	: To view an existing Questionmark Schedule content item.
//				: Features separate views for staff and students,i.e. user
//				: 'role' specific options.				
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
		org.apache.commons.lang.StringEscapeUtils,		
		java.rmi.RemoteException,
		javax.xml.namespace.QName,
		com.questionmark.*,
		com.questionmark.QMWISe.*"	

	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="/bbData" prefix="bbData" %>
<%@ taglib uri="/bbUI" prefix="bbUI"%>


<bbData:context id="ctx">
	<% QMPContentView cv=new QMPContentView(request,ctx); %>
	<bbUI:docTemplate title='<%=StringEscapeUtils.escapeHtml(cv.title)%>'>
	
	<bbUI:titleBar iconUrl='<%=cv.path+"/images/qm.gif"%>'>
		<%=StringEscapeUtils.escapeHtml(cv.title)%>
	</bbUI:titleBar>
	
	<bbUI:breadcrumbBar environment="COURSE" isContent="true">
		<bbUI:breadcrumb>Schedule Details</bbUI:breadcrumb>
	</bbUI:breadcrumbBar>

		<%
		if (cv.failTitle == null) {
			if (cv.isAdministrator) {
				%>
			<bbUI:actionBar>
				<%
					if(cv.pb.getProperty("perception.singlesignon") != null) {
				%>
				<bbUI:actionItem title="Log in to Enterprise Manager" href='<%=cv.path+"/links/enterprisemanager.jsp?course_id="+cv.courseId %>'
					imgUrl='<%=cv.path+"/images/link.gif" %>' target="_blank"/>
				<%
					}
				%>
			</bbUI:actionBar>

				<p><em>Warning:</em> you are currently logged in with a Perception administrator role.
				You are not subject to schedule restrictions and may use the "Start" link to try
				out the assessment.</p>
				<%
			}
			%>
			<bbUI:list collection="<%=cv.scheduleInfo %>" objectId="s"
				className="com.questionmark.ScheduleInfo" collectionLabel="Scheduled Assessments">
				<!-- emptyMsg="There are no assessments scheduled for this course" showAll="true" -->
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
		<bbUI:receipt type="FAIL" title="<%=cv.failTitle %>">
			<%=cv.failMsg %>
		</bbUI:receipt>
		<%
	} //End of other view
%>
	</bbUI:docTemplate>
</bbData:context>
