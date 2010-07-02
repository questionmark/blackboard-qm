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
		com.questionmark.QMWISe.*"
%>
<%@ taglib uri="/bbUI" prefix="bbUI" %>
<%@ taglib uri="/bbData" prefix="bbData" %>
<%@taglib uri="/bbNG" prefix="bbNG"%>
<%
	if(!PlugInUtil.authorizeForCourseControlPanelContent(request, response))
		return;
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//the parent id of content object created will be this content id from this context!
	String parent_id = request.getParameter("content_id");	
	String courseId = request.getParameter("course_id");
%>


<bbNG:learningSystemPage ctxId="ctx" title="Questionmark Perception connector" onLoad="disable_set_access()">
	
	<bbNG:pageHeader>
		<bbNG:breadcrumbBar environment="COURSE" isContent="true">
			<bbNG:breadcrumb>QUESTIONMARK SCHEDULE AN ASSESSMENT</bbNG:breadcrumb>
		</bbNG:breadcrumbBar>
		<bbNG:pageTitleBar iconUrl="../images/qm.gif" showTitleBar="true"
			title="Questionmark Perception Connector - Content Item" />
	</bbNG:pageHeader>		
	

	<%
	//create a ConfigFileReader, to check whether this course needs 
	//to sync its members and to show date last synchronized
	ConfigFileReader configReader = new ConfigFileReader(courseId);
	//load the courseSettings file too...
	CourseSettings courseSettings = new CourseSettings(courseId);
	
	//connect to QMWise
	QMWise qmwise = null;
	try {
		qmwise = new QMWise();
	} catch(Exception e) {
		QMWiseException qe = new QMWiseException(e);
		%>

			<h1>Error connecting to Perception server</h1>
			<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>	
	
		<%
		//return;
	}

	//Retrieve the Db persistence manager from the persistence service
	BbPersistenceManager bbPm = PersistenceServiceFactory.getInstance().getDbPersistenceManager();

	// Generate a persistence framework course Id to be used for 
	// loading the course
	Id courseIdObject = bbPm.generateId(Course.DATA_TYPE, courseId);

	CourseDbLoader courseLoader = (CourseDbLoader) bbPm.getLoader(CourseDbLoader.TYPE);
	Course course = courseLoader.loadById(courseIdObject);

	PropertiesBean pb = new PropertiesBean();


	//-----------------------------------------------------------------------
	// Group synchronization
	//-----------------------------------------------------------------------
	
	//get Perception group id
	int perceptiongroupid = 0;
	try {
		
		perceptiongroupid = new Integer(qmwise.getStub().getGroupByName(course.getBatchUid()).getGroup_ID()).intValue();
		
	} catch(NullPointerException npe){
		
		System.out.println("Perception: course " + courseId + ": synchronization failed: " + npe.getMessage());
		%>
		<h1>Error retrieving course group from Perception, please ensure Connector is successfully 
		connected to Perception</h1>
			<p><%=StringEscapeUtils.escapeHtml(npe.getMessage())%></p>					

		<%
		return;
		
	
	}  catch(Exception e) {
		
		QMWiseException qe = new QMWiseException(e);
		if(qe.getQMErrorCode() == 1201) {
			//group doesn't exist -- force sync
			System.out.println("Perception: course " + courseId + ": Perception group doesn't exist -- forcing synchronisation");
			UserSynchronizer us = new UserSynchronizer();
			String force_sync_result;
			try {
				force_sync_result = us.synchronizeCourse(courseId);
				configReader.setCourseSyncDate();
				out.print(force_sync_result);
				//get fresh group
				perceptiongroupid = new Integer(qmwise.getStub().getGroupByName(course.getBatchUid()).getGroup_ID()).intValue();
				
			} catch (QMWiseException nqe ) {		
				
				System.out.println("Qmwise exception caught: course " + courseId + ": synchronization failed: " + nqe.getMessage());
				String output = "Qmwise exception caught: course " + courseId + ": synchronization failed: " + nqe.getMessage();
				
				%>					
				<h1>Group Synchronisation failed!</h1>					
				
				<p>
					<%=StringEscapeUtils.escapeHtml(output)%>
				</p>
				
				<%
				//Do not want the page to crash!
				//return;
			} 
		} else {
			%>
				<h1>Error retrieving course group from Perception</h1>
					<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>
				
			<%
			//return;
		}
	}

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
		<h1>You don't have a role on this course</h1>
			<p><%=StringEscapeUtils.escapeHtml(e.getMessage())%></p>
		<%
		//return;
	} catch (PersistenceException pe) {
		// There is no membership record.
		%>
		<h1>Error loading the current user</h1>
			<p><%=pe.getMessage()%></p>			
		<%
		//return;
	}

	if(crsMembership.getRole() == CourseMembership.Role.INSTRUCTOR
		|| crsMembership.getRole() == CourseMembership.Role.TEACHING_ASSISTANT) {
		//-----------------------------------------------------------------------
		//Administrator or TA
		//-----------------------------------------------------------------------

		%>
		
<bbNG:actionControlBar showWhenEmpty="true">	

<%
	if(pb.getProperty("perception.singlesignon") != null) {
%>
	<bbNG:actionButton url='<%=path+"/links/enterprisemanager.jsp"%>' 
		title="Log in to Enterprise Manager" target="_blank"/>
<%
	}
%>

</bbNG:actionControlBar>
	<bbUI:spacer height="10" />
	<%
			//-----------------------------------------------------------------------
			// Schedule-authoring form
			//-----------------------------------------------------------------------
			%>
	<h1 id="Scheduleform">Schedule an Assessment</h1>
	<%

			Calendar startdate = Calendar.getInstance();
			Calendar enddate = Calendar.getInstance();
			enddate.add(Calendar.DAY_OF_MONTH, 7);

			String adminid = null;
			try {
				adminid = qmwise.getStub().getAdministratorByName(sessionUser.getUserName()).getAdministrator_ID();
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
					<h1>Error getting Perception administrator ID</h1>
						<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>
					
				<%
				//return;
			}

			Assessment[] assessments = null;
			try {
				assessments = qmwise.getStub().getAssessmentListByAdministrator(adminid);
			
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
					<h1>Error getting list of available assessments</h1>
						<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>
						
				<%
				//return;
			}

			//sort assessments by Session_Name
			Arrays.sort(assessments, new AssessmentComparator());

			if(assessments.length == 0) { %>
	<p>There are no assessments defined in Perception so you cannot
	schedule an assessment.</p>
	<% } else { %>
	<bbNG:jsBlock>
		<script type="text/javascript">
						function disable_set_access() {
							if(document.getElementById('set_access_period')) {
								var disabled = !document.getElementById('set_access_period').checked;
								document.getElementById('dp_start_0_start_date').disabled = disabled;
								document.getElementById('start_hour').disabled = disabled;
								document.getElementById('start_minute').disabled = disabled;
								document.getElementById('dp_end_1_start_date').disabled = disabled;
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
	</bbNG:jsBlock>

				<form name="schedule_assessment" action='<%=path+"/content/createscheduleproc.jsp"%>' method="post">
					
					<input type="hidden" name="course_id" value="<%=request.getParameter("course_id")%>"/>
					<input type="hidden" name="parent_id" value="<%=request.getParameter("content_id")%>"/>	
									
					<bbUI:step title="Enter Information" number="1">
						<bbUI:dataElement label="Schedule name">
							<input type="text" name="schedule" />
							<br />
											The schedule name must be unique if results are to be stored in the gradebook
										</bbUI:dataElement>
						<bbUI:dataElement label="Store results in gradebook?">
							<select name="use_gradebook">
								<option value="percent" selected="selected">as percentage
								scores</option>
								<option value="point">as point scores</option>
								<option value="no">do not store results in gradebook</option>
							</select>
						</bbUI:dataElement>
						<bbUI:dataElement label="Assessment name">
							<select name="assessment">
								<% 
								String last_ID = "";
								for(int i = 0; i < assessments.length; i++) { 
									String next_ID = assessments[i].getAssessment_ID();
									if (!next_ID.equals(last_ID)){%>
								<option value="<%=next_ID%>"><%=assessments[i].getSession_Name()%></option>				
								<%		last_ID = next_ID;
									}					
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
								name="set_access_period" value="true" onclick="disable_set_access()" />
							<bbUI:dataElement label="Start date">
								<bbUI:datePicker startDate="<%=startdate%>"
									formName="schedule_assessment" startCaption="Start"
									startDateField="start" />
							</bbUI:dataElement>
							<bbUI:dataElement label="Start time (24-hour HH:MM)">
								<input type="text" id="start_hour" name="start_hour" size="2"
									disabled value="09" /> :
												<input type="text" id="start_minute" name="start_minute"
									size="2" disabled value="00" />
							</bbUI:dataElement>
							<bbUI:dataElement label="End date">
								<bbUI:datePicker startDate="<%=enddate%>"
									formName="schedule_assessment" startCaption="End"
									startDateField="end" />
							</bbUI:dataElement>
							<bbUI:dataElement label="End time (24-hour HH:MM)">
								<input type="text" id="end_hour" name="end_hour" size="2" disabled
									value="17" /> :
												<input type="text" id="end_minute" name="end_minute" size="2"
									disabled value="00" />
							</bbUI:dataElement>
						</bbUI:dataElement>
						<input type="hidden" name="group" value="<%=course.getBatchUid()%>" />
						<input type="hidden" name="course_id" value="<%=courseId%>" />
					</bbUI:step> <bbUI:stepSubmit title="Submit" number="2" /></form>
	<% }


		} else {
			//-----------------------------------------------------------------------
			// if by some chance you are not admin or instructor but are able to create content item.
			//-----------------------------------------------------------------------
			%>
			<h1>Insufficient rights - Incorrect role</h1>
				<p>We are sorry but you don't have sufficient rights to be able to schedule an assessment, 
					please login as course instructor or administrator.</p>			
			<%
			return;
		}
	%>		

</bbNG:learningSystemPage>
