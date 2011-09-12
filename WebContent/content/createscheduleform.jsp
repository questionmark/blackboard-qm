<!-- 
////////////////////////////

// Filename		: content/createscheduleform.jsp
// Description	: Part of Questionmark Perception Connector, this file is
// 				  responsible for allowing the course instructor to create
//				  a new scheduled assessment as a content item.
////////////////////////////
-->

<%@ page
	language="java"
	pageEncoding="UTF-8"
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
		blackboard.persist.content.*,
		blackboard.persist.content.ContentDbLoader,
		blackboard.persist.Id,		
		blackboard.persist.course.*,
		org.apache.axis.*,
		org.apache.commons.lang.StringEscapeUtils,		
		java.rmi.RemoteException,
		javax.xml.namespace.QName,
		com.questionmark.*,
		com.questionmark.*,
		com.questionmark.QMWISe.*"
%>
<%@ taglib uri="/bbUI" prefix="bbUI" %>
<%@ taglib uri="/bbData" prefix="bbData" %>

<bbData:context id="ctx">
	<%
	QMPContentCreator cc=new QMPContentCreator(request,ctx,response);
	cc.NewForm();
	DateFormat minFormat = new SimpleDateFormat("mm");
	DateFormat hourFormat = new SimpleDateFormat("HH");
	%>
	<%
	if (cc.failTitle == null) {
	%>
	<bbUI:docTemplateHead title="Questionmark Perception connector" />
	
	<bbUI:docTemplateBody onLoad="disable_set_access()">
	
		<bbUI:titleBar iconUrl='<%=cc.path+"/images/qm.gif"%>'>
			Questionmark Perception Assessment
		</bbUI:titleBar>
		
		<bbUI:breadcrumbBar environment="COURSE" isContent="true">
			<bbUI:breadcrumb>Create Assessment Schedule</bbUI:breadcrumb>
		</bbUI:breadcrumbBar>		
	
		<bbUI:actionBar>
			<%
				if(cc.pb.getProperty("perception.singlesignon") != null) {
			%>
			<bbUI:actionItem title="Log in to Enterprise Manager" href='<%=cc.path+"/links/enterprisemanager.jsp?course_id="+cc.courseId %>'
				imgUrl='<%=cc.path+"/images/link.gif" %>' target="_blank"/>
			<%
				}
			%>
		</bbUI:actionBar>

		<h1 id="Scheduleform">Schedule an Assessment</h1>
		<%
		if (cc.selectAssessmentList.length == 0) {
			%>
			<p>You do not have permission to schedule any assessments in Perception.</p>
			<%
		} else { 
			%>
			<script type="text/javascript">
					function disable_set_access() {
						if(document.getElementById('set_access_period')) {
							var disabled = !document.getElementById('set_access_period').checked;
							
							document.getElementById('start_hour').disabled = disabled;
							document.getElementById('start_minute').disabled = disabled;
							
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

			<form name="schedule_assessment" action='<%=cc.path+"/content/createscheduleproc.jsp"%>' method="post">
				<bbUI:step number="1" title="Enter Information">
					<bbUI:dataElement required="true" label="Schedule name">
						<input type="text" name="schedule"  /><br />
						<br />
						The schedule name must be unique if results are to be stored in the Grade Center
					</bbUI:dataElement>	
					<bbUI:dataElement label="Schedule description">	
						<textarea  cols="40" rows="3" title="Additional Comments" onfocus="this.value='';this.onfocus=null;" 
								name="schedule_text_area" id="addComments" >
						</textarea>		
						<br />
						Enter a short description for this Content item, N.B. Plaintext only.		
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
							for(int i = 0; i < cc.selectAssessmentList.length; i++) { 
								%>
							<%=cc.selectAssessmentList[i].toString() %>
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
							name="set_access_period" value="true" onclick="disable_set_access()"/>
							<br/>
					</bbUI:dataElement>
					<bbUI:dataElement label="Start date">
						<bbUI:datePicker startDate="<%= cc.contentItem.startdate%>" formName="schedule_assessment" startCaption="Start" startDateField="start" />
					</bbUI:dataElement>
					<bbUI:dataElement label="Start time (24-hour HH:MM)">
						<input type="text" id="start_hour" name="start_hour" size="2" disabled value="<%=hourFormat.format(cc.contentItem.startdate.getTime()) %>" /> :
						<input type="text" id="start_minute" name="start_minute" size="2" disabled value="<%=minFormat.format(cc.contentItem.startdate.getTime()) %>" />
					</bbUI:dataElement>
					<bbUI:dataElement label="End date">
						<bbUI:datePicker startDate="<%=cc.contentItem.enddate%>" formName="schedule_assessment" startCaption="End" startDateField="end" />
					</bbUI:dataElement>
					<bbUI:dataElement label="End time (24-hour HH:MM)">
						<input type="text" id="end_hour" name="end_hour" size="2" disabled value="<%=hourFormat.format(cc.contentItem.enddate.getTime()) %>" /> :
						<input type="text" id="end_minute" name="end_minute" size="2" disabled value="<%=minFormat.format(cc.contentItem.enddate.getTime()) %>" />
					</bbUI:dataElement>
					<bbUI:dataElement label="Permit Users to View this Content?">
						<input type="radio" name="available" value="true" checked="checked" /> Yes
						<input type="radio" name="available" value="false" /> No
					</bbUI:dataElement>
				</bbUI:step> 
								
				<input type="hidden" name="group" value="<%=cc.course.getBatchUid()%>" />
				<input type="hidden" name="course_id" value="<%=cc.courseId%>" />
				<input type="hidden" name="parent_id" value="<%=cc.contentItem.parentId.toExternalString()%>"/>	
				
				<bbUI:stepSubmit title="Submit" number="2" />							
			</form>
		<%
			}
		%>
	</bbUI:docTemplateBody>
	<%
	} else {
	%>

	<bbUI:receipt type="FAIL" title="<%=cc.failTitle %>">
		<%=cc.failMsg %>
	</bbUI:receipt>

	<%
	} //End of other view
	%>
</bbData:context>