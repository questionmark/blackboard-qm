<%

/*

	Package:	Perception Bridge
	Version: 	1.0.1
	Blackboard:	6.1
	Author:		Matt Elton
	Copyright 2005 VLE Genius. All Rights Reserved
	
*/

%>

<%@ page import="java.util.*,
				 blackboard.data.user.User,
				 blackboard.persist.Id,
				 blackboard.platform.context.Context,
				 com.qm.bb6.perception.servlet.*,
				 com.qm.bb6.perception.service.*,
				 com.qm.bb6.perception.config.*,
				 com.qm.bb6.perception.util.*" %>

<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ taglib uri="/bbData" prefix="bbData"%>
<%@ taglib uri="/perception" prefix="perception"%>

<bbUI:docTemplate title="Questionmark">
<%

	Context bbContext = (Context) request.getAttribute( AuthenticatedServlet.CONTEXT_PARAM );
	Locale locale = (Locale) request.getAttribute( AuthenticatedServlet.LOCALE_PARAM );
	User user = bbContext.getUser();

	// load perception settings
	PerceptionSettings settings = (PerceptionSettings) request.getAttribute( PerceptionSettings.class.getName() );
	
	String lastUrl = (String) request.getAttribute( "recallUrl" );
	
	// get page title
	String title = BbUtils.getSystemString( "system-config.title", locale );

	boolean openQuestionmarkInNewWindow = true;
	String targetWin = openQuestionmarkInNewWindow ? "PerceptionWin" : "_self";
	String lauchRecallUrl = openQuestionmarkInNewWindow ? "javascript:window.close();" : "javascript:history.go(-1)";
	
	String testUrl = BbUtils.getFullPath("TestConnection") + "?recallUrl=" + WebUtils.parseQueryValue(lauchRecallUrl);
	String testLabel = BbUtils.getSystemString( "test-connection.label", locale );
	
	// added 4.4
	String onlineHelpUrl = BbUtils.getSystemString( "online-help.www", locale );
	String onlineHelpLabel = BbUtils.getSystemString( "online-help.label", locale );
	%>		
	
	<bbUI:breadcrumbBar handle="admin_plugin_manage">
		<bbUI:breadcrumb><%= title %></bbUI:breadcrumb>
	</bbUI:breadcrumbBar>
	<bbUI:titleBar iconUrl="<%= BbUtils.getFullPath("images/qmnew.gif") %>"> &nbsp; <%= title %></bbUI:titleBar>
	<bbUI:actionBar maxItems="3" >
		<bbUI:actionItem 	href="<%= testUrl + "\\\" target=\\\"" + targetWin + "\\\"" %>" 
							imgUrl="<%= BbUtils.getFullPath("images/prefs_16.gif") %>" 
							title="<%= testLabel %>"/>
		<bbUI:actionItem 	href="<%= onlineHelpUrl + "\\\" target=\\\"" + targetWin + "\\\"" %>" 
							imgUrl="<%= BbUtils.getFullPath("images/help_16.gif") %>" 
							title="<%= onlineHelpLabel %>"/>
	</bbUI:actionBar>
	<br>

	<form name="adminForm" method="POST" action="<%= BbUtils.getFullPath("SystemAdmin") %>">
		<input type="hidden" name="<%= AuthenticatedServlet.UPDATE_PARAM %>" value="true">
		<%
		
		if( lastUrl != null ){
			%><input type="hidden" name="recallUrl" value="<%= WebUtils.parseFormValue(lastUrl) %>"><%
		}
		
		%>
		<bbUI:step title="<%= BbUtils.getSystemString( "system-config-authentication.title", locale ) %>">
			<bbUI:dataElement label="<%= BbUtils.getSystemString( "system-config-set-host.title", locale ) %>">
				<input type="text" name="<%= PerceptionSettings.PERCEPTION_HOST_PARAM %>" value="<%= WebUtils.parseFormValue( settings.getPerceptionHost() ) %>" SIZE="50">
			</bbUI:dataElement>	
			<bbUI:instructions>
				<%= BbUtils.getSystemString( "system-config-set-host.instructions", locale ) %>
			</bbUI:instructions>
			<bbUI:dataElement label="<%= BbUtils.getSystemString( "system-config-set-qmwise.title", locale ) %>">
				<input type="text" name="<%= PerceptionSettings.QMWISE_URL_PARAM %>" value="<%= WebUtils.parseFormValue( settings.getQMWISeUrl() ) %>" SIZE="50">
			</bbUI:dataElement>	
			<bbUI:instructions>
				<%= BbUtils.getSystemString( "system-config-set-qmwise.instructions", locale ) %>
			</bbUI:instructions>
			<bbUI:dataElement label="<%= BbUtils.getSystemString( "system-config-set-perceptionurl.title", locale ) %>">
				<input type="text" name="<%= PerceptionSettings.PERCEPTION_URL_PARAM %>" value="<%= WebUtils.parseFormValue( settings.getPerceptionUrl() ) %>" SIZE="50">
			</bbUI:dataElement>	
			<bbUI:instructions>
				<%= BbUtils.getSystemString( "system-config-set-perceptionurl.instructions", locale ) %>
			</bbUI:instructions>
			<bbUI:dataElement label="<%= BbUtils.getSystemString( "system-config-set-sessionurl.title", locale ) %>">
				<input type="text" name="<%= PerceptionSettings.SESSION_URL_PARAM %>" value="<%= WebUtils.parseFormValue( settings.getSessionUrl() ) %>" SIZE="50">
			</bbUI:dataElement>	
			<bbUI:instructions>
				<%= BbUtils.getSystemString( "system-config-set-sessionurl.instructions", locale ) %>
			</bbUI:instructions>
			<bbUI:dataElement label="<%= BbUtils.getSystemString( "system-config-set-pipsecretkey.title", locale ) %>">
				<input type="password" name="<%= PerceptionSettings.PIP_SECRET_KEY_PARAM %>" value="<%= WebUtils.parseFormValue( settings.getPIPSecretKey() ) %>">
			</bbUI:dataElement>	
			<bbUI:instructions>
				<%= BbUtils.getSystemString( "system-config-set-pipsecretkey.instructions", locale ) %>
			</bbUI:instructions>
		</bbUI:step>
		<bbUI:step title="<%= BbUtils.getSystemString( "system-config-logging.title", locale ) %>">
			<bbUI:dataElement label="<%= BbUtils.getSystemString( "system-config-set-usechecksum.title", locale ) %>">
				<input type="radio" name="<%= PerceptionSettings.USE_CHECKSUM_PARAM %>" value="true" <%= WebUtils.getChecked(settings.getUseChecksum()) %>>
					<%= BbUtils.getSystemString( "system-config-set-usechecksum.yes", locale ) %>
				<input type="radio" name="<%= PerceptionSettings.USE_CHECKSUM_PARAM %>" value="false" <%= WebUtils.getChecked(!settings.getUseChecksum()) %>>
					<%= BbUtils.getSystemString( "system-config-set-usechecksum.no", locale ) %>
			</bbUI:dataElement>	
			<bbUI:instructions>
				<%= BbUtils.getSystemString( "system-config-set-usechecksum.instructions", locale ) %>
			</bbUI:instructions>
			<bbUI:dataElement label="<%= BbUtils.getSystemString( "system-config-set-logging.title", locale ) %>">
				<select name="<%= PerceptionSettings.LOGGING_LEVEL_PARAM %>">
					<option value="<%= PerceptionLog.NORMAL_LOGGING %>" <%= WebUtils.getSelected(settings.getLoggingLevel() == PerceptionLog.NORMAL_LOGGING) %>>
						<%= BbUtils.getSystemString( "system-config-logging-normal.label", locale ) %>
					</option>
					<option value="<%= PerceptionLog.DEBUG_LOGGING %>" <%= WebUtils.getSelected(settings.getLoggingLevel() == PerceptionLog.DEBUG_LOGGING) %>>
						<%= BbUtils.getSystemString( "system-config-logging-debug.label", locale ) %>
					</option>
					<option value="<%= PerceptionLog.NO_LOGGING %>" <%= WebUtils.getSelected(settings.getLoggingLevel() == PerceptionLog.NO_LOGGING) %>>
						<%= BbUtils.getSystemString( "system-config-logging-none.label", locale ) %>
					</option>
				</select>
			</bbUI:dataElement>
			<bbUI:instructions>
				<%= BbUtils.getSystemString( "system-config-set-logging.instructions", locale ) %>
			</bbUI:instructions>
		</bbUI:step>
		<bbUI:step title="<%= BbUtils.getSystemString( "system-config-synchronization.title", locale ) %>">
			<bbUI:dataElement label="<%= BbUtils.getSystemString( "system-config-synchronization-enable.title", locale ) %>">
				<input type="radio" name="<%= PerceptionSettings.IS_SYNCHRONIZATION_ENABLED_PARAM %>" value="true" <%= WebUtils.getChecked(settings.getIsSynchronizationEnabled()) %>>
					<%= BbUtils.getSystemString( "system-config-synchronization-enable.yes", locale ) %>
				<input type="radio" name="<%= PerceptionSettings.IS_SYNCHRONIZATION_ENABLED_PARAM %>" value="false" <%= WebUtils.getChecked(!settings.getIsSynchronizationEnabled()) %>>
					<%= BbUtils.getSystemString( "system-config-synchronization-enable.no", locale ) %>
			</bbUI:dataElement>	
			<bbUI:instructions>
				<%= BbUtils.getSystemString( "system-config-synchronization-enable.instructions", locale ) %>
			</bbUI:instructions>
			<bbUI:dataElement label="<%= BbUtils.getSystemString( "system-config-external-user-enable.title", locale ) %>">
				<input type="radio" name="<%= PerceptionSettings.USE_EXTERNAL_USER_ID_PARAM %>" value="true" <%= WebUtils.getChecked(settings.getUseExternalUserId()) %>>
					<%= BbUtils.getSystemString( "system-config-external-user-enable.yes", locale ) %>
				<input type="radio" name="<%= PerceptionSettings.USE_EXTERNAL_USER_ID_PARAM %>" value="false" <%= WebUtils.getChecked(!settings.getUseExternalUserId()) %>>
					<%= BbUtils.getSystemString( "system-config-external-user-enable.no", locale ) %>
			</bbUI:dataElement>	
			<bbUI:instructions>
				<%= BbUtils.getSystemString( "system-config-external-user-enable.instructions", locale ) %>
			</bbUI:instructions>
			<bbUI:dataElement label="<%= BbUtils.getSystemString( "system-config-external-course-enable.title", locale ) %>">
				<input type="radio" name="<%= PerceptionSettings.USE_EXTERNAL_COURSE_ID_PARAM %>" value="true" <%= WebUtils.getChecked(settings.getUseExternalCourseId()) %>>
					<%= BbUtils.getSystemString( "system-config-external-course-enable.yes", locale ) %>
				<input type="radio" name="<%= PerceptionSettings.USE_EXTERNAL_COURSE_ID_PARAM %>" value="false" <%= WebUtils.getChecked(!settings.getUseExternalCourseId()) %>>
					<%= BbUtils.getSystemString( "system-config-external-course-enable.no", locale ) %>
			</bbUI:dataElement>	
			<bbUI:instructions>
				<%= BbUtils.getSystemString( "system-config-external-course-enable.instructions", locale ) %>
			</bbUI:instructions>
			<bbUI:dataElement label="<%= BbUtils.getSystemString( "system-config-authoring-rights.title", locale ) %>">
				<input type="radio" name="<%= PerceptionSettings.CREATE_AUTHORING_RIGHTS_PARAM %>" value="true" <%= WebUtils.getChecked(settings.getCreateAuthoringRights()) %>>
					<%= BbUtils.getSystemString( "system-config-authoring-rights.yes", locale ) %>
				<input type="radio" name="<%= PerceptionSettings.CREATE_AUTHORING_RIGHTS_PARAM %>" value="false" <%= WebUtils.getChecked(!settings.getCreateAuthoringRights()) %>>
					<%= BbUtils.getSystemString( "system-config-authoring-rights.no", locale ) %>
			</bbUI:dataElement>	
			<bbUI:instructions>
				<%= BbUtils.getSystemString( "system-config-authoring-rights.instructions", locale ) %>
			</bbUI:instructions>
		</bbUI:step>
		<bbUI:step title="<%= BbUtils.getSystemString( "system-config-settings.title", locale ) %>">
			<bbUI:dataElement label="<%= BbUtils.getCourseString( "course-config-select-result.title", locale ) %>">
				<select name="<%= CourseSettings.GRADEBOOK_RESULTS_PARAM %>">
					<option value="<%= PerceptionSettings.FIRST_RESULT %>" <%= WebUtils.getSelected(settings.getGradeBookResultsSelection() == PerceptionSettings.FIRST_RESULT) %>>
						<%= BbUtils.getCourseString( "course-config-first-result.name", locale ) %>
					</option>
					<option value="<%= PerceptionSettings.LAST_RESULT %>" <%= WebUtils.getSelected(settings.getGradeBookResultsSelection() == PerceptionSettings.LAST_RESULT) %>>
						<%= BbUtils.getCourseString( "course-config-last-result.name", locale ) %>
					</option>
					<option value="<%= PerceptionSettings.BEST_RESULT %>" <%= WebUtils.getSelected(settings.getGradeBookResultsSelection() == PerceptionSettings.BEST_RESULT) %>>
						<%= BbUtils.getCourseString( "course-config-best-result.name", locale ) %>
					</option>
					<option value="<%= PerceptionSettings.WORST_RESULT %>" <%= WebUtils.getSelected(settings.getGradeBookResultsSelection() == PerceptionSettings.WORST_RESULT) %>>
						<%= BbUtils.getCourseString( "course-config-worst-result.name", locale ) %>
					</option>
					<%
						if( BbUtils.isAtLeastVersion(7,1) ){
							%>
							<option value="<%= PerceptionSettings.AVERAGE_RESULT %>" <%= WebUtils.getSelected(settings.getGradeBookResultsSelection() == PerceptionSettings.AVERAGE_RESULT) %>>
								<%= BbUtils.getCourseString( "course-config-average-result.name", locale ) %>
							</option>
							<%
						}
					%>
				</select>
			</bbUI:dataElement>
			<bbUI:instructions>
				<%= BbUtils.getSystemString( "system-config-select-result.instructions", locale ) %>
			</bbUI:instructions>
			<bbUI:dataElement label="<%= BbUtils.getCourseString( "course-config-show-grades.title", locale ) %>">
				<input type="radio" name="<%= CourseSettings.CAN_STUDENTS_SEE_SCORES_PARAM %>" value="true" <%= WebUtils.getChecked(settings.getCanStudentsSeeScores()) %>>
					<%= BbUtils.getCourseString( "course-config-show-grades.yes", locale ) %>
				<input type="radio" name="<%= CourseSettings.CAN_STUDENTS_SEE_SCORES_PARAM %>" value="false" <%= WebUtils.getChecked(!settings.getCanStudentsSeeScores()) %>>
					<%= BbUtils.getCourseString( "course-config-show-grades.no", locale ) %>
			</bbUI:dataElement>				
			<bbUI:dataElement label="<%= BbUtils.getCourseString( "course-config-show-report.title", locale ) %>">
				<input type="radio" name="<%= CourseSettings.CAN_STUDENTS_SEE_REPORT_PARAM %>" value="true" <%= WebUtils.getChecked(settings.getCanStudentsSeeReport()) %>>
					<%= BbUtils.getCourseString( "course-config-show-report.yes", locale ) %>
				<input type="radio" name="<%= CourseSettings.CAN_STUDENTS_SEE_REPORT_PARAM %>" value="false" <%= WebUtils.getChecked(!settings.getCanStudentsSeeReport()) %>>
					<%= BbUtils.getCourseString( "course-config-show-report.no", locale ) %>
			</bbUI:dataElement>						
		</bbUI:step>
		<bbUI:step title="<%= BbUtils.getSystemString( "system-config-operations.title", locale ) %>">
			<bbUI:dataElement label="<%= BbUtils.getSystemString( "system-config-set-instructorstaketest.title", locale ) %>">
				<input type="radio" name="<%= PerceptionSettings.INSTRUCTORS_CAN_TAKE_TEST_PARAM %>" value="true" <%= WebUtils.getChecked(settings.getCanInstructorsTakeTests()) %>>
					<%= BbUtils.getSystemString( "system-config-set-instructorstaketest.yes", locale ) %>
				<input type="radio" name="<%= PerceptionSettings.INSTRUCTORS_CAN_TAKE_TEST_PARAM %>" value="false" <%= WebUtils.getChecked(!settings.getCanInstructorsTakeTests()) %>>
					<%= BbUtils.getSystemString( "system-config-set-instructorstaketest.no", locale ) %>
			</bbUI:dataElement>	
			<bbUI:dataElement label="<%= BbUtils.getSystemString( "system-config-set-usewysiwyg.title", locale ) %>">
				<input type="radio" name="<%= PerceptionSettings.USE_BB_WYSIWYG_PARAM %>" value="true" <%= WebUtils.getChecked(settings.getUseBbWYSIWYG()) %>>
					<%= BbUtils.getSystemString( "system-config-set-usewysiwyg.yes", locale ) %>
				<input type="radio" name="<%= PerceptionSettings.USE_BB_WYSIWYG_PARAM %>" value="false" <%= WebUtils.getChecked(!settings.getUseBbWYSIWYG()) %>>
					<%= BbUtils.getSystemString( "system-config-set-usewysiwyg.no", locale ) %>
			</bbUI:dataElement>	
			<%
			if( false ){
				%>
				<bbUI:dataElement label="<%= BbUtils.getSystemString( "system-config-set-useannouncements.title", locale ) %>">
					<input type="radio" name="<%= PerceptionSettings.USE_BB_WYSIWYG_PARAM %>" value="true" <%= WebUtils.getChecked(settings.getUseAnnouncements()) %>>
						<%= BbUtils.getSystemString( "system-config-set-useannouncements.yes", locale ) %>
					<input type="radio" name="<%= PerceptionSettings.USE_BB_WYSIWYG_PARAM %>" value="false" <%= WebUtils.getChecked(!settings.getUseAnnouncements()) %>>
						<%= BbUtils.getSystemString( "system-config-set-useannouncements.no", locale ) %>
				</bbUI:dataElement>
				<%
			}
			%>
		</bbUI:step>
		<bbUI:stepSubmit/>
	</form>


	<perception:licenseInfo />


</bbUI:docTemplate>
