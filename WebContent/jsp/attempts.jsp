<%

/*

	Package:	Perception Test
	Version: 	1.0.1
	Blackboard:	6.1
	Author:		Matt Elton
	Copyright 2005 VLE Genius. All Rights Reserved
	
*/

%>

<%@ page import="java.io.IOException,
				 java.net.URLEncoder,
				 java.text.SimpleDateFormat,
				 java.util.*,
				 javax.servlet.jsp.JspWriter,
				 blackboard.base.BbList,
				 blackboard.data.course.*,
				 blackboard.data.gradebook.*,
				 blackboard.data.gradebook.impl.*,
				 blackboard.persist.*,
 				 blackboard.persist.gradebook.*,
				 blackboard.persist.gradebook.impl.*,
				 blackboard.data.user.User,
				 blackboard.platform.context.Context,
				 com.qm.bb6.perception.context.PerceptionContext,
				 com.qm.bb6.perception.data.*,
				 com.qm.bb6.perception.servlet.*,
				 com.qm.bb6.perception.service.*,
				 com.qm.bb6.perception.config.*,
				 com.qm.bb6.perception.config.impl.SimpleContentSettings,
				 com.qm.bb6.perception.util.*" %>

<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ taglib uri="/perception" prefix="perception"%>
<%!

	class AttemptInfo {
	
		// the score object
		Score score;
		// the attempt object
		Attempt attempt;
		// whether the attempt is the chosen one
		boolean selected;
		// title of the assessment
		String title;
		// lineitem
		Id lineitem_id;
		
		AttemptInfo( Score score, Attempt attempt, boolean selected, String title, Id lineitem_id ){
			this.score = score;
			this.attempt = attempt;
			this.selected = selected;
			this.title = title;
			this.lineitem_id = lineitem_id;
		}
		
		Score getScore(){
			return this.score;
		}

		Attempt getAttempt(){
			return this.attempt;
		}

		boolean getSelected(){
			return this.selected;
		}

		String getTitle(){
			return this.title;
		}

		Id getLineitemId(){
			return this.lineitem_id;
		}
	}
	
	
	class AttemptInfoComparator implements Comparator {
		
		public static final int ASSESSMENT_NAME = 1;
		public static final int DATE_ATTEMPTED = 2;
		public static final int SCORE = 3;
		public static final int GRADE = 4;
		public static final int SELECTED = 5;
		
		private int type = DATE_ATTEMPTED;
		
		/**
		 * creates a new AttemptInfoComparator of a specified type
		 * @param type
		 * @throws IllegalArgumentException   if type is invalid
		 */
		public AttemptInfoComparator(int type) {
			this.type = type;
		}
			
		/**
		 * @return property key name
		 * @return label used for UI editing
		 */
		public int compare(Object cc1, Object cc2) throws ClassCastException{
			if(cc1 == null || cc2 == null)
				throw new ClassCastException("null value detected");
			if(cc1 instanceof AttemptInfo && cc2 instanceof AttemptInfo){
				AttemptInfo attempt1 = (AttemptInfo) cc1;
				AttemptInfo attempt2 = (AttemptInfo) cc2;
				int comparison;
				if( type == ASSESSMENT_NAME){
					comparison = compareStrings( attempt1.getTitle(), attempt2.getTitle() );
				}else if(type  == DATE_ATTEMPTED){
					comparison = compareDates( attempt1.getAttempt().getAttemptedDate(), attempt2.getAttempt().getAttemptedDate() );
				}else if(type  == SCORE){
					comparison = compareFloats( attempt1.getAttempt().getScore(), attempt2.getAttempt().getScore() );
				}else if(type  == GRADE){
					comparison = compareStrings( attempt1.getAttempt().getGrade(), attempt2.getAttempt().getGrade() );
				}else{ //SELECTED
					comparison = compareBooleans( attempt1.getSelected(), attempt2.getSelected() );
				}
				
				if( comparison == 0 ){
					if( type  != DATE_ATTEMPTED ){
						comparison = compareDates( attempt1.getAttempt().getAttemptedDate(), attempt2.getAttempt().getAttemptedDate() );
					}else{
						comparison = compareStrings( attempt1.getTitle(), attempt2.getTitle() );
					}
				}

				if( comparison == 0 ){
					if( type  != SCORE ){
						comparison = compareFloats( attempt1.getAttempt().getScore(), attempt2.getAttempt().getScore() );
					}
				}
				return comparison;
			}else{
				throw new ClassCastException("Both values must be of the same type");
			}
		}
	
						
		private int compareStrings( String str1, String str2 ){
			if( str1 == null )
				return 1;
			else if( str2 == null)
				return -1;
			return str1.compareTo(str2);
		}
		
		private int compareDates( Calendar cal1, Calendar cal2 ){
			if( cal1 == null )
				return 1;
			else if( cal2 == null)
				return -1;
			else if( cal1.before(cal2) )
				return -1;
			else if( cal1.after(cal2) )
				return 1;
			else
				return 0;
		}
	
		private int compareFloats( float cal1, float cal2 ){ // biggest first
			if( cal1 < cal2 )
				return 1;
			else if( cal2 < cal1 )
				return -1;
			else
				return 0;
		}
	
		private int compareBooleans( boolean cal1, boolean cal2 ){ // true first
			if( cal1 == cal2 )
				return 0;
			else if( cal1 )
				return -1;
			else
				return 1;
		}
	
	
		public boolean equals(Object comparator){
			return false;
		}
			
	}
%>
<%

try{

	Context bbContext = (Context) request.getAttribute( AuthenticatedServlet.CONTEXT_PARAM );
	// current user's Locale
	Locale locale = (Locale) request.getAttribute( AuthenticatedServlet.LOCALE_PARAM );
	// load perception settings
	PerceptionSettings settings = (PerceptionSettings) request.getAttribute( PerceptionSettings.class.getName() );

	// current bb course
	Course course = bbContext.getCourse();
	// selected options
	// selected user's enrolment
	CourseMembership studentEnrolment = (CourseMembership) request.getAttribute( AttemptsServlet.ENROLMENT_PARAM );
	User student = studentEnrolment.getUser();
	// select outcome def (LineItem) - may be null
	OutcomeDefinition outcomeDef = (OutcomeDefinition) request.getAttribute( AttemptsServlet.OUTCOME_DEF_PARAM );
	boolean isAverageDisplayed = BbUtils.isAverageDisplayed(outcomeDef);
	BbList lineItems = null;
	if( outcomeDef == null ){
		// load all lineItems so we can display the item titles
		lineItems = BbUtils.getLineitemDbLoader().loadByCourseId( course.getId() );
	}
	
	String lastUrl = (String) request.getAttribute( "recallUrl" );
	
	String title = BbUtils.getSafeCourseString( "attempts.title", locale );
	String displayMessage = BbUtils.getSafeCourseString( "attempts-display.message", locale );
	String display2Message = BbUtils.getSafeCourseString( "attempts-display2.message", locale );
	String attemptsFoundMessage = BbUtils.getSafeCourseString( "attempts-found.message", locale );
	String displayingRecordsMessage = BbUtils.getSafeCourseString( "attempts-records.message", locale );
	String noRecordsMessage = BbUtils.getSafeCourseString( "attempts-no-records.message", locale );
	
	String assessmentColumnLabel = BbUtils.getSafeCourseString( "attempts-assessment.label", locale );
	String attemptedColumnLabel = BbUtils.getSafeCourseString( "attempts-attempted.label", locale );
	String scoreColumnLabel = BbUtils.getSafeCourseString( "attempts-score.label", locale );
	String gradeColumnLabel = BbUtils.getSafeCourseString( "attempts-grade.label", locale );
	String selectedColumnLabel = BbUtils.getSafeCourseString( "attempts-selected.label", locale );
	String selectedColumnYes = BbUtils.getSafeCourseString( "attempts-selected.yes", locale );
	String selectedColumnNo = BbUtils.getSafeCourseString( "attempts-selected.no", locale );

	// declare comparators
	AttemptInfoComparator titleComparator = new AttemptInfoComparator(AttemptInfoComparator.ASSESSMENT_NAME);
	AttemptInfoComparator dateComparator = new AttemptInfoComparator(AttemptInfoComparator.DATE_ATTEMPTED);
	AttemptInfoComparator scoreComparator = new AttemptInfoComparator(AttemptInfoComparator.SCORE);
	AttemptInfoComparator gradeComparator = new AttemptInfoComparator(AttemptInfoComparator.GRADE);
	AttemptInfoComparator chosenComparator = new AttemptInfoComparator(AttemptInfoComparator.SELECTED);
	
	// attempt list is created by loading scores and fetching attempts held within
	BbList attemptList = new BbList();

	// load scores for a given course membership 
	BbList scoreList;
	try{
		if( outcomeDef != null ){
			// filter list of scores to show only the attempts for a selected assessment
			Score score = BbUtils.getScoreDbLoader().loadByCourseMembershipIdAndLineitemId( studentEnrolment.getId(), outcomeDef.getId() );
			scoreList = new BbList();
			scoreList.add( score );
		}else{
			scoreList = BbUtils.getScoreDbLoader().loadByCourseMembershipId( studentEnrolment.getId() );
		}
	}catch(blackboard.persist.KeyNotFoundException knfe){
		// ignore
		scoreList = new BbList();
	}

	if( scoreList != null && ! scoreList.isEmpty() ){
		// not get all attempts for each score and create an Attempt holder object for each
		for( Iterator scoreIterator = scoreList.iterator(); scoreIterator.hasNext(); ){

			Score score = (Score) scoreIterator.next();
			Attempt[] attempts = score.getOutcome().getAttempts();
			if( attempts != null ){
				for( int i=0; i < attempts.length; i++ ){
					boolean selected = ( i == attempts.length - 1 ); // true if last in array
					String assessmentName = "Unknown";
					Id lineitem_id = null;

					if( outcomeDef != null ){
						assessmentName = outcomeDef.getTitle();
						lineitem_id = outcomeDef.getId();
					}else{
						// get from list of lineItems
						for(Iterator iterator = lineItems.iterator(); iterator.hasNext(); ){
							Lineitem lineItem = (Lineitem) iterator.next();
							if( BbUtils.isMatchingId( score.getLineitemId(), lineItem.getId() ) ){
								// match
								lineitem_id = lineItem.getId();
								assessmentName = lineItem.getName();
								break;
							}
						}
					}
					AttemptInfo attemptInfo = new AttemptInfo( score, attempts[i], selected, assessmentName, lineitem_id );
					attemptList.add( attemptInfo );
				}
			}
		}

	}



	%>		
	<bbUI:docTemplate title="<%= title %>">
		<bbUI:coursePage course="<%= course %>">
			<bbUI:breadcrumbBar environment="CTRL_PANEL" handle="control_panel">
				<bbUI:breadcrumb><%= title %></bbUI:breadcrumb>
			</bbUI:breadcrumbBar>
			<bbUI:titleBar iconUrl="<%= BbUtils.getFullPath("images/qm.gif") %>"> &nbsp; <%= title %></bbUI:titleBar>
			<br>
			
			<p>
				<table border="0" cellpadding="0" cellspacing="2">
				<tr>
					<td align="right"><font class="label"><%= displayMessage %>:</td>
					<td>
						<font class="label">
							<%
							
							// create link to this page without a specified lineitem
							String fullUrl =  BbUtils.getFullPath("Attempts") + "?" + request.getQueryString();
							fullUrl = BbUtils.addQueryParam( fullUrl, AttemptsServlet.OUTCOME_DEF_ID_PARAM, "");
							
							%>
							<a href="<%= fullUrl %>">
								<%= WebUtils.parseHtmlValue( student.getFamilyName() ) %>,
								<%= WebUtils.parseHtmlValue( student.getGivenName() ) %>
							</a>
						</font>
					</td>
				</tr>
				<%
				if( outcomeDef != null ){
					%>
					<tr>
						<td align="right"><font class="label"><%= display2Message %>:</td>
						<td>
							<font class="label">
								<%= WebUtils.parseHtmlValue( outcomeDef.getTitle() ) %>
							</font>
						</td>
					</tr>
					<%
				}
				%>
				</table>
			</p>
			<%

			
	
			if( attemptList != null && ! attemptList.isEmpty() ){

				%>
				<p>
					<font class="label"><%= attemptList.size() %> <%= attemptsFoundMessage %></font><br>
					<%
					int startRecord = WebUtils.readInt( request.getParameter("startIndex"), 0 ) + 1;
					int endRecord = (startRecord + 19) > attemptList.size() ? attemptList.size() : startRecord + 19;
					%>
					<small><%= displayingRecordsMessage %> <%= startRecord %>-<%= endRecord %></small><br>
				</p>


				<bbUI:list 	collection="<%= attemptList %>" 
							collectionLabel="<%= BbUtils.getSafeCourseString( "attempts-collection.label", locale ) %>" 
							objectId="attemptInfo" 
							className="AttemptInfo" 
							sortUrl="<%= BbUtils.getFullPath( "Attempts" ) + BbUtils.getSortUrl( request ) %>"
							description="<%= BbUtils.getSafeCourseString( "attempts-collection.description", locale ) %>"
							>
						<%
						
						// fetch bb objects from holder
						Attempt attempt = attemptInfo.getAttempt();
						Score score = attemptInfo.getScore();
						String assessmentName = attemptInfo.getTitle();
						Id lineitem_id = attemptInfo.getLineitemId();
						
						%>
					<bbUI:listElement label="<%= assessmentColumnLabel %>" name="Assessment" comparator="<%= titleComparator %>">
						&nbsp;
						<%
						
						if( outcomeDef == null ){
							// create link to single assessment attempts
							String limitedUrl =  BbUtils.getFullPath("Attempts") + "?" + request.getQueryString();
							limitedUrl = BbUtils.addQueryParam( limitedUrl, AttemptsServlet.OUTCOME_DEF_ID_PARAM, lineitem_id.toExternalString() );
							
							%><a href="<%= limitedUrl %>"><%= assessmentName %></a><%
							
						}else{
						
							%><%= assessmentName %><%
						
						}
							
						%>						
					</bbUI:listElement>
					<bbUI:listElement label="<%= attemptedColumnLabel %>" name="Date Attempted" comparator="<%= dateComparator %>">
						&nbsp;
						<%= attempt.getAttemptedDate().getTime().toString() %>
					</bbUI:listElement>
					<bbUI:listElement label="<%= scoreColumnLabel %>" name="Score" comparator="<%= scoreComparator %>">
						&nbsp;
						<%= attempt.getScore() %>
					</bbUI:listElement>
					<bbUI:listElement label="<%= gradeColumnLabel %>" name="Grade" comparator="<%= gradeComparator %>">
						&nbsp;
						<%= attempt.getGrade() %>
					</bbUI:listElement>
					<bbUI:listElement label="<%= selectedColumnLabel %>" name="Selected" comparator="<%= chosenComparator %>">
						&nbsp;
						<%=  ((!isAverageDisplayed && attemptInfo.getSelected()) ? selectedColumnYes : selectedColumnNo) %>
					</bbUI:listElement>
					<bbUI:listElement label="" name="Functions" >
					<%

						StringBuffer clearAttemptUrl = new StringBuffer();
						clearAttemptUrl.append( BbUtils.getFullPath( "ClearAttempt" ) );
						clearAttemptUrl.append("?course_id=" + WebUtils.parseQueryValue(course.getId().toExternalString()));
						clearAttemptUrl.append("&" + ClearAttemptServlet.ATTEMPT_ID_PARAM + "=");
						clearAttemptUrl.append( WebUtils.parseQueryValue(attempt.getId().toExternalString()));
						%>
						<a href="<%= clearAttemptUrl.toString() %>"
							><%= BbUtils.getSafeCourseString( "clear-attempt.label", locale ) %></a>&nbsp;
						<%

						if( lineitem_id != null ){

							StringBuffer modifyUrl = new StringBuffer();
							modifyUrl.append("/webapps/gradebook/do/instructor/modifyGrade");
							modifyUrl.append("?course_id=" + WebUtils.parseQueryValue(course.getId().toExternalString()));
							modifyUrl.append("&recordSetSize=");
							modifyUrl.append("&pagedMode=");
							modifyUrl.append("&method=modify");
							modifyUrl.append("&courseMembershipId=" + WebUtils.parseQueryValue(studentEnrolment.getId().toExternalString()));
							modifyUrl.append("&outcomeDefinitionId=" + WebUtils.parseQueryValue(lineitem_id.toExternalString()));
							%>
							<a href="<%= modifyUrl.toString() %>"
								><%= BbUtils.getSafeCourseString( "attempts-modifygrade.label", locale ) %></a>&nbsp;
							<%
						}
						
						if( attempt.getLinkRefId() != null && settings.canAccessCoachingReports(bbContext) ){
						
							StringBuffer reportUrl = new StringBuffer();
							reportUrl.append( BbUtils.getFullPath( "LaunchCoachingReport" ) );
							reportUrl.append("?course_id=" + WebUtils.parseQueryValue(course.getId().toExternalString()));
							reportUrl.append("&" + LaunchCoachingReportServlet.ATTEMPT_ID_PARAM + "=");
							reportUrl.append( WebUtils.parseQueryValue(attempt.getId().toExternalString()));
							%>
							<a href="<%= reportUrl.toString() %>" target="_blank"
								><%= BbUtils.getSafeCourseString( "coaching-report-link.label", locale ) %></a>&nbsp;
							<%
						}
						
						%>
					</bbUI:listElement>
				</bbUI:list>
				<%

			}else {

				%><p><font color="#666666"><%= noRecordsMessage %></font></p><%
			}


			%>	
			<BR>
			<div align="right">
				<%
					if( lastUrl != null ){
						%><bbUI:button type="FORM_ACTION" alt="OK" name="ok" action="LINK" targetUrl="<%= lastUrl %>" /><%
					}else{
						%><bbUI:button type="FORM_ACTION" alt="OK" name="ok" action="LINK" /><%
					}
				%>
			</div>
			<br><br>
			<perception:licenseInfo />
			
		</bbUI:coursePage>
	</bbUI:docTemplate>
<%

}catch(Throwable t){
	t.printStackTrace( new java.io.PrintWriter( out ) );	
}

%>

