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

<bbNG:learningSystemPage ctxId="ctx">
	<%
	QMPContentCreator cc=new QMPContentCreator(request,ctx,response);
	cc.EditForm();
	%>
	<bbNG:pageHeader>
		<bbNG:pageTitleBar iconUrl='<%=cc.path+"/images/qm.gif"%>'
			title='<%=StringEscapeUtils.escapeHtml(cc.title)%>' />
		<bbNG:breadcrumbBar environment="COURSE" isContent="true">
			<bbNG:breadcrumb>Edit Schedule Details</bbNG:breadcrumb>
		</bbNG:breadcrumbBar>
	</bbNG:pageHeader>	
	<bbNG:jsBlock>
		<script type="text/javascript">
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
	</bbNG:jsBlock>

	<%
	if (cc.failTitle == null) {	
		if (cc.contentItem.schedules.size()>1) {
			%>
			<p><strong>Warning:</strong>: multiple schedules match this content item in Perception.  Only
			the first schedule can be edited.</p>
			<%
		}
		
		%>
	<form name="edit_schedule" action='<%=cc.path+"/content/editscheduleproc.jsp"%>' method="post" onsubmit="return validate_form(this)"  >
		<bbNG:dataCollection>	
			<bbNG:stepGroup active="true" title="Edit Schedule options">		
				<bbNG:step title="Edit Name" hideNumber="true" instructions="Please enter a new name for this schedule">					
					<bbNG:dataElement isRequired="true" label="Schedule name">
						<bbNG:textElement id="new_schedule_name" name="new_schedule_name" isRequired="true" maxLength="30" helpText="Maximum 30 characters allowed"
							value="<%=StringEscapeUtils.escapeHtml(cc.contentItem.name)%>" />
					</bbNG:dataElement>												
					<bbNG:dataElement label="Schedule description">	
						<textarea cols="40" rows="3" title="Additional Comments" name="schedule_text_area"
							id="addComments" ><%=StringEscapeUtils.escapeHtml(cc.contentItem.description)%></textarea>		
						<br />
						Enter a short description for this Content item, N.B. plain text only.		
					</bbNG:dataElement>
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
				</bbNG:step>				
				<%
				if(cc.contentItem.limitAttempts){
					%>
					<bbNG:step title="Edit Attempts" hideNumber="true"
						instructions="Reset schedule attempts for <strong>All</strong>  participants">
						<bbNG:dataElement label="Reset maximum attempts?">
							<input type="checkbox" id="limit_attempts" name="limit_attempts"
								value="true" onclick="disable_limit_attempts()" />
							<input type="text" id="limit" name="limit" size="4" disabled
								value="<%=cc.contentItem.maxAttempts%>" />
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
							<bbNG:datePicker baseFieldName="scheduleStart" dateTimeValue="<%= cc.contentItem.startdate%>" showDate="true" showTime="true"/>
						</bbNG:dataElement>
						<br/>
						<bbNG:dataElement label="End date">
							<bbNG:datePicker baseFieldName="scheduleEnd" dateTimeValue="<%= cc.contentItem.enddate%>" showDate="true" showTime="true"/>
						</bbNG:dataElement>
					</bbNG:dataElement>
					<bbNG:dataElement label="Permit Users to View this Content">
						<input type="radio" name="available" value="true" <%=cc.contentItem.available?"checked=\"checked\"":"" %> /> Yes
						<input type="radio" name="available" value="false" <%=cc.contentItem.available?"":"checked=\"checked\"" %> /> No
					</bbNG:dataElement>
				</bbNG:step>
				<input type="hidden" name="group_name" value="<%=cc.course.getBatchUid()%>"/>
				<input type="hidden" name="content_id" value="<%=cc.contentItem.contentId.toExternalString()%>"/>
				<input type="hidden" name="parent_id" value="<%=cc.contentItem.parentId.toExternalString()%>"/>
				<input type="hidden" name="course_id" value="<%=cc.courseId%>" />
				<input type="hidden" name="group_id" value="<%=cc.groupID%>"/>
				
				<bbNG:stepSubmit hideNumber="true" title="Submit" instructions="Click the Submit button to save your changes"></bbNG:stepSubmit>
					
			</bbNG:stepGroup>
		</bbNG:dataCollection>
	</form>
		
		<%
	} else {
		%>
	<bbNG:receipt type="FAIL" title="<%=cc.failTitle %>">
		<%=cc.failMsg %>
	</bbNG:receipt>
	<%
	} //End of other view
	%>
</bbNG:learningSystemPage>
			


