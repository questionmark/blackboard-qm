<!-- 
////////////////////////////

// Filename		: content/createscheduleform.jsp
// Description	: Part of Questionmark Perception Connector, creation of schedule from content item.
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
		blackboard.base.FormattedText.Type,
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
		com.questionmark.QMWISe.*"
		
%>

<%@ taglib uri="/bbData" prefix="bbData" %>
<%@ taglib uri="/bbNG" prefix="bbNG"%>

<bbNG:learningSystemPage ctxId="ctx" title="Questionmark Perception connector" onLoad="disable_set_access()">
	<%
	QMPContentCreator cc=new QMPContentCreator(request,ctx,response);
	cc.NewForm();
	%>
	<bbNG:pageHeader>
		<bbNG:breadcrumbBar environment="COURSE" isContent="true">
			<bbNG:breadcrumb>Create Assessment Schedule</bbNG:breadcrumb>
		</bbNG:breadcrumbBar>
		<bbNG:pageTitleBar iconUrl='<%=cc.path+"/images/qm.gif"%>' showTitleBar="true"
			title="Questionmark Perception Assessment" />
	</bbNG:pageHeader>		
	
	<%
	if (cc.failTitle == null) {
	%>
		<bbNG:actionControlBar showWhenEmpty="true">	
		<%
		if(cc.pb.getProperty("perception.singlesignon") != null) {
		%>
			<bbNG:actionButton url='<%=cc.path+"/links/enterprisemanager.jsp?course_id="+cc.courseId %>' 
				title="Log in to Enterprise Manager" target="_blank"/>
		<%
		}
		%>
		</bbNG:actionControlBar>

		<h1 id="Scheduleform">Schedule an Assessment</h1>
		<%
		if (cc.selectAssessmentList.length == 0) {
			%>
			<p>You do not have permission to schedule any assessments in Perception.</p>
			<%
		} else { 
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

			<form name="schedule_assessment" action='<%=cc.path+"/content/createscheduleproc.jsp"%>' method="post">
				<bbNG:dataCollection>			
					<bbNG:step title="Enter Information">
						<bbNG:dataElement isRequired="true" label="Schedule name">
							<bbNG:textElement name="schedule" isRequired="true" maxLength="30" helpText="Maximum 30 characters allowed"/>
							<br />
							The schedule name must be unique if results are to be stored in the Grade Center
						</bbNG:dataElement>	
						<bbNG:dataElement label="Schedule description">	
							<textarea  cols="40" rows="3" title="Additional Comments" onfocus="this.value='';this.onfocus=null;" 
									name="schedule_text_area" id="addComments" >
							</textarea>		
							<br />
							Enter a short description for this Content item, N.B. Plaintext only.		
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
						<bbNG:dataElement label="Select assessment to schedule">
							<select name="assessment">
								<% 
								for(int i = 0; i < cc.selectAssessmentList.length; i++) { 
									%>
								<%=cc.selectAssessmentList[i].toString() %>
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
								<bbNG:datePicker baseFieldName="scheduleStart" dateTimeValue="<%= cc.contentItem.startdate%>" showDate="true" showTime="true"/>
							</bbNG:dataElement>
							<br/>
							<bbNG:dataElement label="End date">
								<bbNG:datePicker baseFieldName="scheduleEnd" dateTimeValue="<%= cc.contentItem.enddate%>" showDate="true" showTime="true"/>
							</bbNG:dataElement>
						</bbNG:dataElement>
						<bbNG:dataElement label="Permit Users to View this Content?">
							<input type="radio" name="available" value="true" checked="checked" /> Yes
							<input type="radio" name="available" value="false" /> No
						</bbNG:dataElement>
					</bbNG:step> 
									
					<input type="hidden" name="group" value="<%=cc.course.getBatchUid()%>" />
					<input type="hidden" name="course_id" value="<%=cc.courseId%>" />
					<input type="hidden" name="parent_id" value="<%=cc.contentItem.parentId.toExternalString()%>"/>	
					
					<bbNG:stepSubmit title="Submit"/>
								
				</bbNG:dataCollection>
			</form>
	<%
		}
	} else {
	%>

	<bbNG:receipt type="FAIL" title="<%=cc.failTitle %>">
		<%=cc.failMsg %>
	</bbNG:receipt>

	<%
	} //End of other view
	%>

</bbNG:learningSystemPage>
