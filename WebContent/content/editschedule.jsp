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
	
    
<%@ taglib uri="/bbUI" prefix="bbUI" %>
<%@ taglib uri="/bbData" prefix="bbData" %>

<bbData:context id="ctx">
	<%
	QMPContentCreator cc=new QMPContentCreator(request,ctx,response);
	cc.EditForm();
	DateFormat minFormat = new SimpleDateFormat("mm");
	DateFormat hourFormat = new SimpleDateFormat("HH");
	if (cc.failTitle == null) {
	%>
	<bbUI:docTemplateHead title="Questionmark Perception connector" />
		
	<bbUI:docTemplateBody onLoad="disable_set_access()">
		<bbUI:coursePage>
			<bbUI:courseTitleBar>Questionmark Perception Assessment</bbUI:courseTitleBar>
	
			<bbUI:breadcrumbBar environment="COURSE" isContent="true">
				<bbUI:breadcrumb>Edit Schedule Details</bbUI:breadcrumb>
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
						set_limit_attempts_hidden();
					}
				}
				function set_limit_attempts_hidden() {
					document.getElementById('per_participant_hidden').value = document.getElementById('per_participant').checked ? "1" : "0";
				}
				
				function validate_required(field,alerttxt)
				{
				with (field)
				  {
				  if (value==null||value=="")
				    {
				    alert(alerttxt);return false;
				    }
				  else
				    {
				    return true;
				    }
				  }
				}
	
				function validate_length(field,length,alerttxt)
				{
				with (field)
				  {
				  if (length == 50)
				    {
				    alert(alerttxt);return false;
				    }
				  else
				    {
				    return true;
				    }
				  }
				}
				
				function validate_form(thisform)
				{
				with (thisform)
				  {
				  var new_schedule_name = document.getElementById("new_schedule_name");		
				  var name_length = new_schedule_name.attributes.length;				  
				  if (validate_required(new_schedule_name,"Name cannot be empty")==false)
				  {new_schedule_name.focus();return false;}
				  
				  }
	
				  if (validate_length(new_schedule_name, name_length, "Name cannot be longer than 50 characters") == false)
				  {
				  {new_schedule_name.focus();return false;}
				  
				  } 		
				  
				}
			</script>
	
		<%
			if (cc.contentItem.schedules.size()>1) {
				%>
				<p><strong>Warning:</strong>: multiple schedules match this content item in Perception.  Only
				the first schedule can be edited.</p>
				<%
			}
			
			%>
		<form name="edit_schedule" action='<%=cc.path+"/content/editscheduleproc.jsp"%>' method="post" onsubmit="return validate_form(this)"  >
			<bbUI:step title="Edit Name" number="1">
				<bbUI:instructions>Please enter a new name for this schedule</bbUI:instructions>				
				<bbUI:dataElement required="true" label="Schedule name">
					<input type="text" name="new_schedule_name" value="<%=StringEscapeUtils.escapeHtml(cc.contentItem.name)%>"  /><br />
							<br />
				</bbUI:dataElement>			
				<bbUI:dataElement label="Schedule description">	
					<textarea cols="40" rows="3" title="Additional Comments" name="schedule_text_area"
						id="addComments" ><%=StringEscapeUtils.escapeHtml(cc.contentItem.description)%></textarea>		
					<br />
					Enter a short description for this Content item, N.B. plain text only.		
				</bbUI:dataElement>
				<%
				if (cc.contentItem.gradebookScore.equals("no")) {
				%>
				<p>The results of this assessment will not be stored in the grade center.</p>
				<%
				} else {
				%>
				<p>The <%=cc.contentItem.gradebookScoreType.toLowerCase()%> result of this assessment will be stored in the gradebook.
				Renaming this schedule will also rename the associated column in the grade center.</p>
				<%
				}
				%>
			</bbUI:step>				
			<%
			if(cc.contentItem.limitAttempts){
				%>
				<bbUI:step title="Edit Attempts" number="2">
					<bbUI:dataElement label="Reset maximum attempts?">
						<bbUI:instructions>Reset schedule attempts for <strong>All</strong>  participants</bbUI:instructions>
						<input type="checkbox" id="limit_attempts" name="limit_attempts"
							value="true" onclick="disable_limit_attempts()" />
						<input type="text" id="limit" name="limit" size="4" disabled
							value="<%=cc.contentItem.maxAttempts%>" />
					</bbUI:dataElement>
				</bbUI:step>	
				<%
			}				
			%>
			<bbUI:step title="Set / Change Access Period" number="3">
				<bbUI:instructions>Set or modify this schedule's availability</bbUI:instructions>
				<bbUI:dataElement label="Set / Change access period?">							
					<input type="checkbox" id="set_access_period" name="set_access_period" value="true" onclick="disable_set_access()"/>
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
				<bbUI:dataElement label="Permit Users to View this Content">
					<input type="radio" name="available" value="true" checked="checked" /> Yes
					<input type="radio" name="available" value="false" /> No
				</bbUI:dataElement>
			</bbUI:step>
			<input type="hidden" name="group_name" value="<%=cc.course.getBatchUid()%>"/>
			<input type="hidden" name="content_id" value="<%=cc.contentItem.contentId.toExternalString()%>"/>
			<input type="hidden" name="parent_id" value="<%=cc.contentItem.parentId.toExternalString()%>"/>
			<input type="hidden" name="course_id" value="<%=cc.courseId%>" />
			<input type="hidden" name="group_id" value="<%=cc.groupID%>"/>
			
			<bbUI:stepSubmit number="4" title="Submit" instructions="Click the Submit button to save your changes"/>
				
		</form>
		</bbUI:coursePage>
	</bbUI:docTemplateBody>
		<%
	} else {
		%>
	<bbUI:docTemplate title="Questionmark Perception Assessment">
		<bbUI:coursePage>
			<bbUI:receipt type="FAIL" title="<%=cc.failTitle %>">
				<%=cc.failMsg %>
			</bbUI:receipt>
		</bbUI:coursePage>
	</bbUI:docTemplate>
	<%
	} //End of other view
	%>
</bbData:context>
			


