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
				 blackboard.data.course.*,
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
<%!

boolean hasFullRights( User user, CourseMembership enrolment ){
	if( user == null )
		return false;
	if( user.getSystemRole() == User.SystemRole.SYSTEM_ADMIN )
		return true;
	if( enrolment != null && enrolment.getRole() == CourseMembership.Role.INSTRUCTOR )
		return true;
	return false;
}

%>
<bbUI:docTemplate title="Questionmark">
<%

	Context bbContext = (Context) request.getAttribute( AuthenticatedServlet.CONTEXT_PARAM );
	Locale locale = (Locale) request.getAttribute( AuthenticatedServlet.LOCALE_PARAM );
	User user = bbContext.getUser();
	Course course = bbContext.getCourse();
	CourseMembership enrolment = bbContext.getCourseMembership();

	// load course specific perception settings
	CourseSettings settings = CourseSettings.load( course );
	
	String lastUrl = (String) request.getAttribute( "recallUrl" );
	String recallUrl = BbUtils.getCleverUrl("CourseAdmin", course, lastUrl);
	
	// get locale strings
	String title = BbUtils.getCourseString( "course-config.title", locale );
	String qmLinkText = BbUtils.getCourseString( "qm-admin-link.title", locale );
	String addCourseLinkText = BbUtils.getCourseString( "add-course-link.title", locale );
	String addFullSynchronisationText = BbUtils.getCourseString( "full-synchronisation-link.title", locale );
	
	boolean openQuestionmarkInNewWindow = true;
	String targetWin = openQuestionmarkInNewWindow ? "PerceptionWin" : "_self";
	String lauchRecallUrl = openQuestionmarkInNewWindow ? "javascript:window.close();" : recallUrl;
	
	%>		

	<bbUI:coursePage course="<%= course %>">
		<bbUI:breadcrumbBar environment="CTRL_PANEL" handle="control_panel">
			<bbUI:breadcrumb><%= title %></bbUI:breadcrumb>
		</bbUI:breadcrumbBar>
		<bbUI:titleBar iconUrl="<%= BbUtils.getFullPath("images/qmnew.gif") %>"> &nbsp; <%= title %></bbUI:titleBar>
		<bbUI:actionBar maxItems="3" >
			<bbUI:actionItem 	href="<%= BbUtils.getCleverUrl("LaunchEnterpriseManager", course, lauchRecallUrl) + "\\\" target=\\\"" + targetWin + "\\\"" %>" 
								imgUrl="<%= BbUtils.getFullPath("images/web_16.gif") %>" 
								title="<%= qmLinkText %>"/>
			<%
				if( hasFullRights(user,enrolment) && (!CourseAdminServlet.doesCourseMenuItemExist(course.getId())) && (CourseAdminServlet.isStudentLinkEnabled(course.getServiceLevelType())) ){
					%><bbUI:actionItem 	href="<%= BbUtils.getCleverUrl("AddCourseMenuLink", course, recallUrl) %>" 
										imgUrl="<%= BbUtils.getFullPath("images/foldr_16.gif") %>" 
										title="<%= addCourseLinkText %>"/><%
				}
			%>
			<%
				if( hasFullRights(user,enrolment) ){
					%><bbUI:actionItem 	href="<%= BbUtils.getCleverUrl("FullSynchronisation", course, recallUrl) %>" 
										imgUrl="<%= BbUtils.getFullPath("images/foldr_16.gif") %>" 
										title="<%= addFullSynchronisationText %>"/><%
				}
			%>
		</bbUI:actionBar>
		<br>

		<%
			if( hasFullRights(user,enrolment) ){
				%>
				<form name="adminForm" method="POST" action="<%= BbUtils.getFullPath("CourseAdmin") %>">
					<input type="hidden" name="<%= AuthenticatedServlet.UPDATE_PARAM %>" value="true">
					<input type="hidden" name="course_id" value="<%= bbContext.getCourseId().toExternalString() %>">
					<%
					if( lastUrl != null ){
						%><input type="hidden" name="recallUrl" value="<%= WebUtils.parseFormValue(lastUrl) %>"><%
					}
					%>
					<bbUI:step title="<%= BbUtils.getCourseString( "course-config-settings.title", locale ) %>">
						<bbUI:instructions>
							<%= BbUtils.getCourseString( "course-config-select-result.instructions", locale ) %>
						</bbUI:instructions>
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
					<bbUI:stepSubmit cancelUrl="<%= lastUrl %>" />
				</form>
				<%
			}
			
		%>

		<perception:licenseInfo />

	</bbUI:coursePage>
</bbUI:docTemplate>
