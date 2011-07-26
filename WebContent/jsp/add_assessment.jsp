<%

/*

	Package:	Perception
	Version: 	1.0.1
	Blackboard:	6.1
	Author:		Matt Elton
	Copyright 2005 VLE Genius. All Rights Reserved
	
*/

%>

<%@ page import="java.io.UnsupportedEncodingException,
				 java.util.*,
				 blackboard.data.content.Content,
				 blackboard.data.course.*,
				 blackboard.data.user.User,
				 blackboard.persist.Id,
				 blackboard.platform.context.Context,
				 com.qm.bb6.perception.context.PerceptionContext,
				 com.qm.bb6.perception.data.*,
				 com.qm.bb6.perception.servlet.*,
				 com.qm.bb6.perception.service.*,
				 com.qm.bb6.perception.config.*,
				 com.qm.bb6.perception.config.impl.SimpleContentSettings,
				 com.qm.bb6.perception.util.*" %>

<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ taglib uri="/bbData" prefix="bbData"%>
<%@ taglib uri="/perception" prefix="perception"%>
<%!

	public static final String PARENT_ID_PARAMETER = "parent_id";
	public static final String UP_ID_PARAMETER = "up_id";
	
	String getFolderUrl( String queryString, String folder_id, String parent_id, String[] up_ids ) throws UnsupportedEncodingException{
	
		StringBuffer urlBuffer = new StringBuffer();
		urlBuffer.append( BbUtils.getFullPath("CreateAssessment") + "?" );
		queryString = queryString.replaceAll( "[\\&]?" + PARENT_ID_PARAMETER + "=[^\\&]+", "&" ); 
		queryString = queryString.replaceAll( "[\\&]?" + UP_ID_PARAMETER + "=[^\\&]+", "&" ); 
		queryString += "&" + PARENT_ID_PARAMETER + "=" + WebUtils.parseQueryValue( folder_id );
		if( parent_id != null ){
			String up_ids_string = "";
			if( up_ids != null ){
				for( int i=0; i < up_ids.length; i++ ){
					up_ids_string += up_ids[i] + ",";
				}
			}
			up_ids_string += parent_id;
			queryString += "&" + UP_ID_PARAMETER + "=" + WebUtils.parseQueryValue( up_ids_string );
		}else{
			String up_ids_string = "";
			if( up_ids != null ){
				for( int i=0; i < up_ids.length; i++ ){
					if( folder_id.equals( up_ids[i] ) ){
						// stop here
						break;
					}else{
						up_ids_string += up_ids[i] + ",";
					}
				}
			}
			// chop
			if( up_ids_string.length() != 0 ){
				up_ids_string = up_ids_string.substring(0, up_ids_string.length() - 1);
				queryString += "&" + UP_ID_PARAMETER + "=" + WebUtils.parseQueryValue( up_ids_string );
			}
		}			
		queryString = queryString.replaceAll("[\\&]+", "&");
		if( queryString.startsWith("&") ){
			queryString = queryString.replaceFirst("&","?");
		}
		urlBuffer.append( queryString );
		return urlBuffer.toString();
		
	}


	boolean areDatesDifferent( Calendar one, Calendar two ){
		if( one == null || two == null )
			return false; // ignore
		one.set( Calendar.MILLISECOND, 0 );
		two.set( Calendar.MILLISECOND, 0 );
		
		return (one.before( two ) || one.after( two ));
	}
		
	String parseScriptString(String str){
		if(str == null)
			return "";
		String tmp = str.replaceAll("'", "\\'"); 
		return tmp;
	}


%>
<bbUI:docTemplate title="Questionmark">
<%

	
	Context bbContext = (Context) request.getAttribute( AuthenticatedServlet.CONTEXT_PARAM );
	Locale locale = (Locale) request.getAttribute( AuthenticatedServlet.LOCALE_PARAM );
	User user = bbContext.getUser();
	Course course = bbContext.getCourse();
	CourseMembership enrolment = bbContext.getCourseMembership();
	
	PerceptionContext perceptionContext = (PerceptionContext) request.getAttribute( CreateAssessmentServlet.PERCEPTION_CONTENT_PARAM );
	PerceptionUser administrator = perceptionContext.getPerceptionUser();

	Content content = bbContext.getContent();
	
	ContentSettings contentSettings;
	// load Assessment from request
	PerceptionAssessment assessment = (PerceptionAssessment) request.getAttribute( CreateAssessmentServlet.PERCEPTION_ASSESSMENT_PARAM );
	
	// added for 4.3
	boolean gradeBookColumnCreated = ((Boolean) request.getAttribute( CreateAssessmentServlet.GRADEBOOK_COLUMN_CREATED_PARAM )).booleanValue();
	
	boolean newContent = false;
	if( content.getIsFolder() ){
		Content parent = content;
		content = new Content();
		content.setParentId( parent.getId() );
		newContent = true;
		
		// load Perception content settings
		contentSettings = SimpleContentSettings.load( content );
		
	}else{
		// load Perception content settings from request
		contentSettings = (ContentSettings) request.getAttribute( CreateAssessmentServlet.CONTENT_SETTINGS_PARAM );
	}
		
		
	// load course specific perception settings
	CourseSettings settings = CourseSettings.load( course );
	
	
	// the maximum number of attempts
	boolean hasLimitedAttempts = false;
	int maxAttempts = 1;
	Calendar startDate = content.getStartDate();
	Calendar endDate = content.getEndDate();
	Calendar scheduleStart = null;
	Calendar scheduleEnd = null;
	
	// load Perception schedule
	if( contentSettings.getScheduleId() != null ){
		// fetch the schedule loader
		PerceptionScheduleLoader scheduleLoader = PerceptionServiceManager.getPerceptionScheduleLoader();
		// load schedule from Perception
		try{
			PerceptionSchedule schedule = scheduleLoader.getScheduleById( contentSettings.getScheduleId() );
			hasLimitedAttempts = false; //schedule.getRestrictAttempts();
			maxAttempts = schedule.getMaxAttempts();
			scheduleStart = schedule.getScheduleStarts(); // might be different with bb times
			scheduleEnd = schedule.getScheduleStops();

		}catch(ObjectNotFoundException onfe){
			// schedule not found on Perception, remove link
			contentSettings.setScheduleId( null );
			contentSettings.persist();
		}
	}else{
		// used cached/default settings as schedules are not group based
		hasLimitedAttempts = contentSettings.getAreAttemptsLimited();
		if( hasLimitedAttempts )
			maxAttempts = contentSettings.getMaxAttempts();
		else
			maxAttempts = 1;
	}
		
	
	String lastUrl = (String) request.getAttribute( "recallUrl" );
	String recallUrl = BbUtils.getCleverUrl("CourseAdmin", course, lastUrl);
	
	// get page title
	String title = BbUtils.getCourseString( "add-assessment.title", locale );
	

	%>		
	<script language="JavaScript">
	<!--
	
			var hasLimitedAttempts = <%= hasLimitedAttempts %>;
			
			function TestNumberRange(object_value, min_value, max_value){
				// check minimum
				if (min_value != null) {
					if (object_value < min_value) {
						return false;
					}
				}

				// check maximum
				if (max_value != null) {
					if (object_value > max_value) {
						return false;
					}
				}
				//All tests passed, so...
				return true;
			}

			function IsNumeric(object_value)
			{
				//Returns true if value is a number or is NULL
				//otherwise returns false	

				if (object_value.length == 0) {
					return true;
				}

				//	Returns true if value is a number defined as
				//	having an optional leading + or -.
				// 	having at most 1 decimal point.
				//	otherwise containing only the characters 0-9.
				var start_format = " .+-0123456789";
				var number_format = " .0123456789";
				var check_char;
				var decimal = false;
				var trailing_blank = false;
				var digits = false;

				//The first character can be + - .  blank or a digit.
				check_char = start_format.indexOf(object_value.charAt(0))
				//Was it a decimal?
				if (check_char == 1) {
					decimal = true;
				} else if (check_char < 1) {
					return false;
				}

				//Remaining characters can be only . or a digit, but only one decimal.
				for (var i = 1; i < object_value.length; i++)
				{
					check_char = number_format.indexOf(object_value.charAt(i))
					if (check_char < 0) {
						return false;
					} else if (check_char == 1) {
						if (decimal) {		// Second decimal.
							return false;
						} else {
							decimal = true;
						}
					} else if (check_char == 0)	{
						if (decimal || digits) {
							trailing_blank = true;
						}
						// ignore leading blanks
					} else if (trailing_blank) {
						return false;
					} else {
						digits = true;
					}
				}	
				//All tests passed, so...
				return true
			}

			function IsInteger(object_value){
				if (object_value.length == 0){
					return true;
				}

				var decimal_format = ".";
				var check_char;

				//The first character can be + -  blank or a digit.
				check_char = object_value.indexOf(decimal_format)

				//Was it a decimal?
				if (check_char < 1) {
					return IsNumeric(object_value);
				} else {
					return false;
				}
			}
			
			function validateForm() {
				
				var thisform = document.addForm;
				var attempts = thisform.elements['<%= CreateAssessmentServlet.ATTEMPTS_PARAM %>'].value;

				if( !( IsInteger(attempts) && TestNumberRange(attempts, 1, 100)) ){
					alert('<%= BbUtils.getSafeCourseString( "too-many-attempts.message", locale ) %>');
					return false;
				}
				
				<%
				 
				if( hasLimitedAttempts ){ // from content
				
					%>
					if( hasLimitedAttempts ){ // from javascript

						var message = '<%= parseScriptString(BbUtils.getSafeCourseString( "attempts-warning.message", locale )) %>';

						if( !window.confirm(message) ){
							return false;
						}
					}
					<%
				}
				
				%>
								
				return true;

			}
		
		
	// -->
	</script>
	
	<bbUI:coursePage course="<%= course %>">
		<bbUI:breadcrumbBar environment="CTRL_PANEL" handle="control_panel">
			<bbUI:breadcrumb><%= title %></bbUI:breadcrumb>
		</bbUI:breadcrumbBar>
		<bbUI:titleBar iconUrl="<%= BbUtils.getFullPath("images/qm.gif") %>"> &nbsp; <%= title %></bbUI:titleBar>
		<br>
		<form name="addForm" method="POST" action="<%= BbUtils.getFullPath("CreateAssessment") %>" onSubmit="return validateForm()">
			<input type="hidden" name="<%= AuthenticatedServlet.UPDATE_PARAM %>" value="true">
			<input type="hidden" name="course_id" value="<%= course.getId().toExternalString() %>">
			<%
			if( !newContent ){
				%><input type="hidden" name="content_id" value="<%= content.getId().toExternalString() %>"><%
			}else{
				%><input type="hidden" name="content_id" value="<%= content.getParentId().toExternalString() %>"><%
			}
			%>
			<bbUI:step title="<%= BbUtils.getCourseString( "select-assessment.title", locale ) %>">
				<bbUI:dataElement label="<%= BbUtils.getCourseString( "select-assessment.label", locale ) %>">
					<input type="hidden" name="<%= CreateAssessmentServlet.PERCEPTION_ID_PARAM %>" value="<%= WebUtils.parseFormValue(assessment.getAssessmentId()) %>">
					<b><%= WebUtils.parseHtmlValue( assessment.getSessionName() ) %></b>
				</bbUI:dataElement>
			</bbUI:step>
			<bbUI:step title="<%= BbUtils.getCourseString( "select-assessment-options.title", locale ) %>">
				<bbUI:dataElement label="<%= BbUtils.getCourseString( "select-assessment-comments.label", locale ) %>">
					<textarea cols="50"  rows="4" name="<%= CreateAssessmentServlet.COMMENTS_PARAM %>"
						><%= content.getBody().getText() %></textarea>
				</bbUI:dataElement>
				<bbUI:dataElement label="<%= BbUtils.getCourseString( "select-assessment-available.label", locale ) %>">
					<%= BbUtils.getCourseString( "select-assessment-available.yes", locale ) %> 
						<input type="radio" name="<%= CreateAssessmentServlet.AVAILABLE_PARAM %>" value="true" <%= WebUtils.getChecked(content.getIsAvailable()) %>>
					<%= BbUtils.getCourseString( "select-assessment-available.no", locale ) %> 
						<input type="radio" name="<%= CreateAssessmentServlet.AVAILABLE_PARAM %>" value="false" <%= WebUtils.getChecked(!content.getIsAvailable()) %>>
				</bbUI:dataElement>
				<bbUI:dataElement label="<%= BbUtils.getCourseString( "select-assessment-dates.label", locale ) %>">
					<bbUI:dateAvailability
						formName="addForm"
						startCaption="<%= BbUtils.getCourseString( "select-assessment-dates-startcaption", locale ) %>"
						endCaption="<%= BbUtils.getCourseString( "select-assessment-dates-endcaption", locale ) %>"
						startDateField="<%= CreateAssessmentServlet.START_DATE_PARAM %>"
						endDateField="<%= CreateAssessmentServlet.END_DATE_PARAM %>"
						startDate="<%= (startDate != null ? startDate.getTime() : null)  %>"
						endDate="<%= (endDate != null ? endDate.getTime() : null) %>"
						/>
					<%
					
					if( areDatesDifferent( startDate, scheduleStart ) ||  areDatesDifferent( endDate, scheduleEnd ) ){
						// Perception dates do not match Bb dates, so give warning
						%>
						<bbUI:instructions>
							<font color="#FF0000">
								<%= BbUtils.getCourseString( "perception-schedule-does-not-match.message", locale ) %>
							</font>
						</bbUI:instructions>
						<%
					}
					
					%>
				</bbUI:dataElement>
				<bbUI:dataElement label="<%= BbUtils.getCourseString( "select-assessment-allowattempts.label", locale ) %>">
					<%= BbUtils.getCourseString( "select-assessment-available.yes", locale ) %> 
						<input type="radio" name="<%= CreateAssessmentServlet.ALLOW_MULTIPLE_PARAM %>" value="true" 
						onClick="javascript:setMaxAttempts(false);" <%= WebUtils.getChecked(hasLimitedAttempts) %>>
					<%= BbUtils.getCourseString( "select-assessment-available.no", locale ) %> 
						<input type="radio" name="<%= CreateAssessmentServlet.ALLOW_MULTIPLE_PARAM %>" value="false" 
						onClick="javascript:setMaxAttempts(true);" <%= WebUtils.getChecked(!hasLimitedAttempts) %>>
				</bbUI:dataElement>
				<bbUI:instructions>
					<%= BbUtils.getCourseString( "select-assessment-attempts.instructions", locale ) %>
				</bbUI:instructions>
				<bbUI:dataElement label="<%= BbUtils.getCourseString( "select-assessment-attempts.label", locale ) %>">
					<input type="text" name="<%= CreateAssessmentServlet.ATTEMPTS_PARAM %>" value="<%= maxAttempts %>" size=3>
				</bbUI:dataElement>
			</bbUI:step>
			<bbUI:step title="<%= BbUtils.getCourseString( "select-gradebookoptions.title", locale ) %>">
				<%
				if( !gradeBookColumnCreated ){
					%>
					<bbUI:dataElement label="<%= BbUtils.getCourseString( "select-gradebookoptions-createnow.label", locale ) %>">
						<%= BbUtils.getCourseString( "select-gradebookoptions-createnow.yes", locale ) %> 
							<input type="radio" name="<%= CreateAssessmentServlet.CREATE_GRADEBOOK_COLUMN_PARAM %>" value="true">
						<%= BbUtils.getCourseString( "select-gradebookoptions-createnow.no", locale ) %> 
							<input type="radio" name="<%= CreateAssessmentServlet.CREATE_GRADEBOOK_COLUMN_PARAM %>" value="false" CHECKED>
					</bbUI:dataElement>
					<%
				}
				
				%>
				<bbUI:dataElement label="<%= BbUtils.getCourseString( "select-gradebookoptions-attempt.label", locale ) %>">
					<%
					
						// 4.3 - set defaults in undefined
						int existingAttemptType = settings.getAssessmentAttemptType( assessment.getAssessmentId() );
						
					%>
					<select name="<%= CreateAssessmentServlet.ATTEMPT_TYPE_PARAM %>">
						<option value="<%= PerceptionSettings.FIRST_RESULT %>" <%= WebUtils.getSelected(existingAttemptType == PerceptionSettings.FIRST_RESULT) %>>
							<%= BbUtils.getCourseString( "course-config-first-result.name", locale ) %>
						</option>
						<option value="<%= PerceptionSettings.LAST_RESULT %>" <%= WebUtils.getSelected(existingAttemptType == PerceptionSettings.LAST_RESULT) %>>
							<%= BbUtils.getCourseString( "course-config-last-result.name", locale ) %>
						</option>
						<option value="<%= PerceptionSettings.BEST_RESULT %>" <%= WebUtils.getSelected(existingAttemptType == PerceptionSettings.BEST_RESULT) %>>
							<%= BbUtils.getCourseString( "course-config-best-result.name", locale ) %>
						</option>
						<option value="<%= PerceptionSettings.WORST_RESULT %>" <%= WebUtils.getSelected(existingAttemptType == PerceptionSettings.WORST_RESULT) %>>
							<%= BbUtils.getCourseString( "course-config-worst-result.name", locale ) %>
						</option>
						<%
						if( BbUtils.isAtLeastVersion(7,1) ){
							%>
							<option value="<%= PerceptionSettings.AVERAGE_RESULT %>" <%= WebUtils.getSelected(existingAttemptType == PerceptionSettings.AVERAGE_RESULT) %>>
								<%= BbUtils.getCourseString( "course-config-average-result.name", locale ) %>
							</option>
							<%
						}
						%>
					</select>

				</bbUI:dataElement>
				<%
				if( gradeBookColumnCreated ){
					%>
					<bbUI:instructions>
						<%= BbUtils.getCourseString( "select-gradebookoptions-attempt.instructions", locale ) %>
					</bbUI:instructions>
					<bbUI:dataElement label="<%= BbUtils.getCourseString( "select-gradebookoptions-updateexisting.label", locale ) %>">
						<%= BbUtils.getCourseString( "select-gradebookoptions-updateexisting.yes", locale ) %> 
							<input type="radio" name="<%= CreateAssessmentServlet.UPDATE_EXISTING_ATTEMPTS_PARAM %>" value="true" >
						<%= BbUtils.getCourseString( "select-gradebookoptions-updateexisting.no", locale ) %> 
							<input type="radio" name="<%= CreateAssessmentServlet.UPDATE_EXISTING_ATTEMPTS_PARAM %>" value="false" CHECKED>
					</bbUI:dataElement>
					<%
				}
				%>
			</bbUI:step>
			<bbUI:stepSubmit/>
		</form>

		<perception:licenseInfo />
		
		<SCRIPT LANGUAGE="javascript">
		<!--

			function setMaxAttempts( newValue ){
				hasLimitedAttempts = !newValue;
				document.addForm.elements['<%= CreateAssessmentServlet.ATTEMPTS_PARAM %>'].disabled = newValue;
			}

			setMaxAttempts( !hasLimitedAttempts );

		//  -->
		</SCRIPT>

	</bbUI:coursePage>
</bbUI:docTemplate>
