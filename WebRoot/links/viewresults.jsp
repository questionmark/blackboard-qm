<%@ page
	language="java"
	pageEncoding="UTF-8"
	import="
		java.util.*,
		java.text.*,
		blackboard.platform.*,
		blackboard.base.*,
		blackboard.platform.session.*,
		blackboard.data.user.*,
		blackboard.persist.* ,
		blackboard.persist.user.*,
		blackboard.data.course.*,
		blackboard.persist.course.*,
		org.apache.axis.*,
		java.rmi.RemoteException,
		javax.xml.namespace.QName,
		com.questionmark.*,
		com.questionmark.QMWISe.*
	"
%>

<%@ taglib uri="/bbUI" prefix="bbUI" %> 
<%@ taglib uri="/bbData" prefix="bbData" %>
<%@ taglib uri="/bbNG" prefix="bbNG" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<bbNG:learningSystemPage ctxId="ctx" title="Questionmark Perception connector" onLoad="disable_set_access()">
	<bbNG:pageHeader>
		<bbNG:pageTitleBar iconUrl='<%=path+"/images/qm.gif"%>' title="Questionmark Perception connector"/>
	</bbNG:pageHeader>

	<%
		// Retrieve the course identifier from the URL
		String courseId = request.getParameter("course_id");

		if(courseId == null) {
			%>
			<bbUI:receipt type="FAIL" title="No course ID was given">
				No course ID was given with the request
			</bbUI:receipt>
			<%
			return;
		}

		//connect to QMWise
		QMWise qmwise;
		try {
			qmwise = new QMWise();
		} catch(Exception e) {
			QMWiseException qe = new QMWiseException(e);
			%>
			<bbUI:receipt type="FAIL" title="Error connecting to Perception server">
				<%=qe.getMessage()%>
			</bbUI:receipt>
			<%
			return;
		}

		//Retrieve the Db persistence manager from the persistence 
		//service
		BbPersistenceManager bbPm = BbServiceManager.getPersistenceService().getDbPersistenceManager();

		// Generate a persistence framework course Id to be used for 
		// loading the course
		Id courseIdObject = bbPm.generateId(Course.COURSE_DATA_TYPE, courseId);

		CourseDbLoader courseLoader = (CourseDbLoader) bbPm.getLoader(CourseDbLoader.TYPE);
		Course course = courseLoader.loadById(courseIdObject);

		PropertiesBean pb = new PropertiesBean();

		//-----------------------------------------------------------------------
		//view specific to current user
		//-----------------------------------------------------------------------

		//Get a User instance via user context
		User sessionUser = ctx.getUser();
		Id sessionUserId = sessionUser.getId();
		
		// get the membership data to determine the User's Role
		CourseMembershipDbLoader crsMembershipLoader = (CourseMembershipDbLoader) bbPm.getLoader(CourseMembershipDbLoader.TYPE);
		CourseMembership crsMembership = null;

		try {
			crsMembership = crsMembershipLoader.loadByCourseAndUserId(courseIdObject, sessionUserId);
		} catch (KeyNotFoundException e) {
			// There is no membership record.
			%>
			<bbUI:receipt type="FAIL" title="You don't have a role on this course">
				<%=e.getMessage()%>
			</bbUI:receipt>
			<%
			return;
		} catch (PersistenceException pe) {
			// There is no membership record.
			%>
			<bbUI:receipt type="FAIL" title="Error loading the current user">
				<%=pe.getMessage()%>
			</bbUI:receipt>
			<%
			return;
		}

		if(crsMembership.getRole() == CourseMembership.Role.INSTRUCTOR
			|| crsMembership.getRole() == CourseMembership.Role.TEACHING_ASSISTANT) {
			//-----------------------------------------------------------------------
			//Administrator or TA
			//-----------------------------------------------------------------------

			%>
			<div id="actionbar" class="actionBar clearfix editmode">
				<ul id="nav" class="nav clearfix">
					<li class="mainButton" nowrap="nowrap">
						<a href='<%=path+"/links/main.jsp?course_id="+courseId%>'>View schedules</a>
					</li>
					<%
					if(pb.getProperty("perception.singlesignon") != null) {
						%>
						<li class="mainButton" nowrap="nowrap">
							<a href='<%=path+"/links/enterprisemanager.jsp"%>' target="_blank">Log in to Enterprise Manager</a>
						</li>
						<%
					}
					%>
				</ul>
			</div>

			<%
			//-----------------------------------------------------------------------
			// Results
			//-----------------------------------------------------------------------
			Result[] results;
			try {
				results = qmwise.getStub().getResultListByGroup(course.getBatchUid());
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
				<bbUI:receipt type="FAIL" title="Error getting results list">
					<%=qe.getMessage()%>
				</bbUI:receipt>
				<%
				return;
			}
			//sort results by date
			Arrays.sort(results, new ResultComparator());

			//get report for each result
			String[] reports = new String[results.length];
			try {
				for(int i = 0; i < results.length; i++) {
					reports[i] = qmwise.getStub().getAccessReport(results[i].getResult_ID());
				}
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
				<bbUI:receipt type="FAIL" title="Error getting coaching report">
					<%=qe.getMessage()%>
				</bbUI:receipt>
				<%
				return;
			}

			//date format
			DateFormat pdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

			int resultsPerPage;
			if(request.getParameter("resultsPerPage") != null)
				resultsPerPage = new Integer(request.getParameter("resultsPerPage")).intValue();
			else
				resultsPerPage = 10;
			
			if(resultsPerPage < 10) resultsPerPage = 10;
			%>
			<h1 id="Results">Results</h1>
			<% if (results.length == 0) { %>
				<p>There are not yet results for this course.</p>
			<% } else { %>
				<form action='<%=path+"/links/main.jsp#Results"%>' method="GET" >
					<input type="hidden" name="course_id" value="<%=courseId%>" />
					Results to show per page: <input type="text" size="4" name="resultsPerPage" value="<%=resultsPerPage %>" />
					<input type="submit" value="Update table" />
				</form>
				<table>
					<tr>
						<!--<th>Assessment ID</th>-->
						<!--<th>Schedule Name</th> requires QMWISe fix -->
						<th>Participant</th>
						<th>Score</th>
						<th>Time taken</th>
						<th>Started</th>
						<th>Finished</th>
						<th>Report</th>
					</tr>
					<%
					int listStart;
					
					if(request.getParameter("resultPage") != null)
						listStart = (new Integer(request.getParameter("resultPage")) -1 ) * new Integer(resultsPerPage);
					else
						listStart = 0;
					if(listStart < 0)
						listStart = 0;
					
					for(int i = listStart; i < results.length && i < listStart+resultsPerPage; i++) {
						Date started = null;
						Date finished = null;
						try {
							started = pdf.parse(results[i].getWhen_Started());
							if(!results[i].isStill_Going()) {
								finished = pdf.parse(results[i].getWhen_Finished());
							}
						} catch(ParseException e) {
							%>
							<bbUI:receipt type="FAIL" title="Error parsing date from Perception">
								<%=e.getMessage()%>
							</bbUI:receipt>
							<%
							return;
						}
						%>
						<tr>
							<!--<td><%=results[i].getAssessment_ID()%></td>-->
							<!--<td><%=results[i].getSchedule_Name()%></td> Requires QMWISe fix-->
							<td><%=results[i].getParticipant() + " (" + results[i].getSpecial_1() + " " + results[i].getSpecial_2() + ")"%></td>
							<td><%=!results[i].isStill_Going() ? results[i].getTotal_Score() + "/" + results[i].getMax_Score() + " (" + results[i].getPercentage_Score() + "%)" : ""%></td>
							<td><%=!results[i].isStill_Going() ? results[i].getTime_Taken() + "s" : ""%></td>
							<td><%=started.toString()%></td>
							<td><%=!results[i].isStill_Going() ? finished.toString() : "Unfinished"%></td>
							<td><a href="<%=reports[i]%>" target="_blank">View report</a></td>
						</tr>
					<% } %>
				</table>
				<%
				
				int numPages = new Double(Math.ceil(results.length/new Double(resultsPerPage))).intValue();
				
				out.println("<p>Page: ");
				
				for(int i = 1; i <= numPages; i++) {
					if(request.getParameter("resultPage") != null && new Integer(request.getParameter("resultPage")).intValue() == i) {
						out.println("<strong>" + i + "</strong> ");
					} else {
						out.println("<a href=\""+path+"/links/main.jsp?course_id=" + courseId + 
							"&amp;resultPage=" + i +
							"&amp;resultsPerPage=" + resultsPerPage +
							"#Results\">" + i + "</a> ");
					}
				}
				
				out.println("</p>");
			
				%>
			<% } //fi results %>
			<bbUI:spacer height="20" />


		<% } else {
			//-----------------------------------------------------------------------
			//Student
			//-----------------------------------------------------------------------

			//get Perception user id
			int perceptionuserid;
			try {
				perceptionuserid = new Integer(qmwise.getStub().getParticipantByName(sessionUser.getUserName()).getParticipant_ID()).intValue();
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
				<bbUI:receipt type="FAIL" title="Error retrieving participant from Perception">
					<%=qe.getMessage()%>
				</bbUI:receipt>
				<%
				return;
			}
			%>
			<div id="actionbar" class="actionBar clearfix editmode">
				<ul id="nav" class="nav clearfix">
					<li class="mainButton" nowrap="nowrap">
						<a href='<%=path+"/links/main.jsp?course_id="+courseId%>'>Return to schedules</a>
					</li>
				</ul>
			</div>

			<p>Results information is not available to Students using this tool.</p>			
			<%
		}
	%>
</bbNG:learningSystemPage>
