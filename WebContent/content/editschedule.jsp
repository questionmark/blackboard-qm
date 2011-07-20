<!-- 
 Filename		: content/editschedule.jsp
 Description	: Runs when editing a schedule.

-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	import="java.util.*,
	java.text.*,
	blackboard.platform.*,
	blackboard.platform.context.Context,
	blackboard.platform.session.*,
	blackboard.platform.persistence.*,
	blackboard.platform.plugin.*,
	blackboard.platform.security.authentication.*,
	blackboard.persist.*,
	blackboard.persist.user.*,
	blackboard.persist.course.*,
	blackboard.persist.content.*,
	blackboard.persist.content.ContentDbLoader,
	blackboard.persist.Id,blackboard.data.course.*,
	blackboard.data.user.*,
	blackboard.data.content.*,
	blackboard.base.*,
	blackboard.data.gradebook.*,
	blackboard.persist.gradebook.*,
	org.apache.axis.*,
	org.apache.commons.lang.StringEscapeUtils,
	java.rmi.RemoteException,
	javax.xml.namespace.QName,
	com.questionmark.*,
	com.questionmark.QMWISe.*"
	pageEncoding="ISO-8859-1"
%>
	
    
<%@ taglib uri="/bbNG" prefix="bbNG" %>
<%@ taglib uri="/bbData" prefix="bbData" %>
<%@ taglib uri="/bbUI" prefix="bbUI" %>

<bbNG:learningSystemPage ctxId="ctx">
	<% QMPContentEdit cv=new QMPContentEdit(request,ctx,response); %>
	<bbNG:pageHeader>
		<bbNG:pageTitleBar iconUrl='<%=cv.path+"/images/qm.gif"%>'
			title='<%=StringEscapeUtils.escapeHtml(cv.schedule_name)%>' />
		<bbNG:breadcrumbBar environment="COURSE" isContent="true">
			<bbNG:breadcrumb><%=StringEscapeUtils.escapeHtml(cv.schedule_name)%></bbNG:breadcrumb>
		</bbNG:breadcrumbBar>
	</bbNG:pageHeader>	

	<%
	if (cv.failTitle == null) {
		boolean limited_attempts = false;
		int schedule_max_attempts = 0;
		
		boolean limited_schedule_access = false;
		Calendar schedule_start_date = Calendar.getInstance();
		schedule_start_date.set(Calendar.HOUR_OF_DAY,9);
		schedule_start_date.set(Calendar.MINUTE,0);
		schedule_start_date.set(Calendar.SECOND,0);		
		Calendar schedule_end_date = Calendar.getInstance();
		schedule_start_date.set(Calendar.HOUR_OF_DAY,17);
		schedule_start_date.set(Calendar.MINUTE,0);
		schedule_start_date.set(Calendar.SECOND,0);
		schedule_end_date.add(Calendar.DAY_OF_MONTH, 7);

		if (cv.nSchedules>1) {
			%>
			<p><strong>Warning:</strong>: multiple schedules match this content item in Perception.  Only
			the first schedule can be edited.</p>
			<%
		}
		
		if(cv.schedule.isRestrict_Attempts()){
			limited_attempts = true;		
			schedule_max_attempts = cv.schedule.getMax_Attempts();
		}
		if(cv.schedule.isRestrict_Times()){
			limited_schedule_access = true;
			schedule_start_date = cv.schedule.readSchedule_Starts_asCalendar();
			schedule_end_date = cv.schedule.readSchedule_Stops_asCalendar();
									
		}
		%>
		<form name="edit_schedule" action='<%=cv.path+"/content/editscheduleproc.jsp"%>' method="post" onsubmit="return validate_form(this)"  >
			<bbNG:dataCollection>	
				<bbNG:stepGroup active="true" title="Edit Schedule options">		
					<bbNG:step title="Edit Name" hideNumber="true" instructions="Please enter a new name for this schedule">					
						<bbNG:dataElement isRequired="true" label="Schedule name">
							<bbNG:textElement id="new_schedule_name" name="new_schedule_name" isRequired="true" maxLength="50" helpText="Maximum 50 characters allowed"
								value="<%=StringEscapeUtils.escapeHtml(cv.schedule_name)%>" />
						</bbNG:dataElement>												
						<bbNG:dataElement label="Schedule description">	
							<textarea cols="40" rows="3" title="Additional Comments" name="schedule_text_area"
								id="addComments" ><%=StringEscapeUtils.escapeHtml(cv.schedule_description)%></textarea>		
							<br />
							Enter a short description for this Content item, N.B. Plaintext only.		
						</bbNG:dataElement>				
					</bbNG:step>				
					<%
					if(limited_attempts){
						%>
						<bbNG:step title="Edit Attempts" hideNumber="true"
							instructions="Reset schedule attempts for <strong>All</strong>  participants">
							<bbNG:dataElement label="Reset maximum attempts?">
								<input type="checkbox" id="limit_attempts" name="limit_attempts"
									value="true" onclick="disable_limit_attempts()" />
								<input type="text" id="limit" name="limit" size="4" disabled
									value="<%=schedule_max_attempts%>" />
							</bbNG:dataElement>
						</bbNG:step>	
						<%
					}				
					%>
					<bbNG:step title="Set / Change Access Period" hideNumber="true"
						instructions="Set or modify this schedule's availability">
						<bbNG:dataElement label="Set / Change access period?" isSubElement="true" subElementType="NESTED_LIST">							
							<input type="checkbox" id="set_access_period" name="set_access_period" value="true" />
							<br/>
							<br/>
							<bbNG:dataElement label="Start date">
								<bbNG:datePicker baseFieldName="scheduleStart" dateTimeValue="<%= schedule_start_date%>" showDate="true" showTime="true"/>
							</bbNG:dataElement>
							<br/>
							<bbNG:dataElement label="End date">
								<bbNG:datePicker baseFieldName="scheduleEnd" dateTimeValue="<%= schedule_end_date%>" showDate="true" showTime="true"/>
							</bbNG:dataElement>
						</bbNG:dataElement>
					</bbNG:step>
					<input type="hidden" name="group_name" value="<%=cv.course.getBatchUid()%>"/>
					<input type="hidden" name="content_id" value="<%=cv.content_id%>"/>
					<input type="hidden" name="parent_id" value="<%=cv.parent_id%>"/>
					<input type="hidden" name="course_id" value="<%=cv.courseId%>" />
					<input type="hidden" name="group_id" value="<%=cv.groupID%>"/>
				
				<bbNG:stepSubmit hideNumber="true" title="Submit" instructions="Click the Submit button to save your changes"></bbNG:stepSubmit>
					
			</bbNG:stepGroup>
		</bbNG:dataCollection>
	</form>
		
		<%
	} else {
		%>
		<bbNG:receipt type="FAIL" title="<%=StringEscapeUtils.escapeHtml(cv.failTitle) %>">
			<%=StringEscapeUtils.escapeHtml(cv.failText) %>
		</bbNG:receipt>
		<%
	} //End of other view
	%>
</bbNG:learningSystemPage>
			


