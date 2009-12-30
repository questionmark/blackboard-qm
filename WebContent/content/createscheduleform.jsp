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
		java.rmi.RemoteException,
		javax.xml.namespace.QName,
		com.questionmark.*,
		com.questionmark.*,
		com.questionmark.QMWISe.*"
%>
<%@ taglib uri="/bbUI" prefix="bbUI" %>
<%@ taglib uri="/bbData" prefix="bbData" %>

<%
	if(!PlugInUtil.authorizeForCourseControlPanelContent(request, response))
		return;
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//the parent id of content object created will be this content id from this context!
	String parent_id = request.getParameter("content_id");	
	String course_id = request.getParameter("course_id");
%>

<bbData:context id="ctx">

	<bbUI:docTemplateHead title="Questionmark Perception connector" />
	
	<bbUI:docTemplateBody onLoad="disable_set_access()">
	
		<bbUI:titleBar iconUrl='<%=path+"/images/qm.gif"%>'>
			Questionmark Perception Connector - Content Item
		</bbUI:titleBar>
		
		<bbUI:breadcrumbBar environment="COURSE" isContent="true">
		
			<bbUI:breadcrumb>QUESTIONMARK PERCEPTION CREATE SCHEDULE</bbUI:breadcrumb>
		
		</bbUI:breadcrumbBar>		
		
		<!--put in variables to display right here, for debugging	
		
			<p>
		
		<%
			out.println("Content id is in fact the parent id: " +  parent_id);		
			out.println("Course id: " + course_id);
		%>
		
		</p>
		-->	
			
		<%
			//to sync its members and to show date last synchronized
			ConfigFileReader configReader = new ConfigFileReader(course_id);
			//load the courseSettings file too...
			
			// not defined in qm bb8 api
			// CourseSettings courseSettings = new CourseSettings(course_id);
			
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
			
			//Retrieve the Db persistence manager from the persistence service
			BbPersistenceManager bbPm = PersistenceServiceFactory.getInstance().getDbPersistenceManager();
	
			// Generate a persistence framework course Id to be used for 
			// loading the course
			Id courseIdObject = bbPm.generateId(Course.DATA_TYPE, course_id);
	
			CourseDbLoader courseLoader = (CourseDbLoader) bbPm.getLoader(CourseDbLoader.TYPE);
			Course course = courseLoader.loadById(courseIdObject);
	
			PropertiesBean pb = new PropertiesBean();
	
			//-----------------------------------------------------------------------
			//No synchronization feature available in content view.
			//-----------------------------------------------------------------------
			
			//get Perception group id
			int perceptiongroupid;
			try {
				perceptiongroupid = new Integer(qmwise.getStub().getGroupByName(course.getBatchUid()).getGroup_ID()).intValue();
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				if(qe.getQMErrorCode() == 1201) 
				{
					//group doesn't exist -- force sync
					System.out.println("Perception: course " + course_id + ": Perception group doesn't exist -- forcing synchronization");
					UserSynchronizer us = new UserSynchronizer();
					try {
						us.synchronizeCourse(course_id);
						configReader.setCourseSyncDate();
						//get fresh group
						perceptiongroupid = new Integer(qmwise.getStub().getGroupByName(course.getBatchUid()).getGroup_ID()).intValue();
					} catch (Exception ne) {
						System.out.println("Perception: course " + course_id + ": synchronization failed: " + ne.getMessage());
		
		%>
		
		<bbUI:receipt type="FAIL" title="Error synchronizing course users with Perception">
			<%=ne.getMessage()%>
		</bbUI:receipt>
		
		<%
						return;
					}
				} else {
				
		%>
		
		<bbUI:receipt type="FAIL" title="Error retrieving course group from Perception">
			<%=qe.getMessage()%>
		</bbUI:receipt>
		
		<%
					return;
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
				//Administrator or TA : Allowed to use single sign on, and create schedules, 
				// Student: Will not be able to do anything this if-else is created for redundancy,
				// If students see this page they will be told to go away.s
				//-----------------------------------------------------------------------

		%>

		<div id="actionbar" class="actionBar clearfix editmode">
			<ul id="nav" class="nav clearfix">
		<%
			if(pb.getProperty("perception.singlesignon") != null) {
		%>
				<li class="mainButton">
					<a href='<%=path+"/links/enterprisemanager.jsp"%>' target="_blank">Log in to Enterprise Manager</a>
				</li>
		<%
			}
		%>
			</ul>
		</div>

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

			String adminid;
			try {
				adminid = qmwise.getStub().getAdministratorByName(sessionUser.getUserName()).getAdministrator_ID();
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
		%>
		<bbUI:receipt type="FAIL" title="Error getting Perception administrator ID">
			<%=qe.getMessage()%>
		</bbUI:receipt>
		<%
				return;
			}

			Assessment[] assessments;
			try {
				assessments = qmwise.getStub().getAssessmentListByAdministrator(adminid);
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
		%>
		
		<bbUI:receipt type="FAIL" title="Error getting list of available assessments">
			<%=qe.getMessage()%>
		</bbUI:receipt>
		
		<%
				return;
			}

			//sort assessments by Session_Name
			Arrays.sort(assessments, new AssessmentComparator());
			
			if(assessments.length == 0) { 
		%>		
		
		<p>There are no assessments defined in Perception so you cannot schedule an assessment.</p>
		
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

		<form name="schedule_assessment" action='<%=path+"/content/createscheduleproc.jsp"%>' method="post">
			
			<input type="hidden" name="course_id" value="<%=request.getParameter("course_id")%>"/>
			<input type="hidden" name="parent_id" value="<%=request.getParameter("content_id")%>"/>					
			
			
			<bbUI:step title="Enter Information" number="1">					
				<bbUI:dataElement label="Schedule name">
					<input type="text" name="schedule_name"  /><br />
					The schedule name must be unique if results are to be stored in the gradebook
				</bbUI:dataElement>
				<bbUI:dataElement label="Store results in gradebook?">
					<select name="use_gradebook">
						<option value="percent" selected="selected">as percentage scores</option>
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
							} 
							%>
					</select>
				</bbUI:dataElement>
				<bbUI:dataElement label="Limit attempts?">
					<input type="checkbox" id="limit_attempts" name="limit_attempts" value="true" onclick="disable_limit_attempts()" />
					<input type="text" id="limit" name="limit" size="4" disabled value="1" />
				</bbUI:dataElement>
				<bbUI:dataElement label="Create schedule for each group participant?">
					<input type="checkbox" id="per_participant" name="per_participant" value="true" onclick="set_limit_attempts_hidden()" />
					<input type="hidden" id="per_participant_hidden" name="per_participant_hidden" value="0" />
				</bbUI:dataElement>
				<bbUI:dataElement label="Set access period?">
					<input type="checkbox" id="set_access_period" name="set_access_period" value="true" onclick="disable_set_access()" />
					<bbUI:dataElement label="Start date">
						<bbUI:datePicker startDate="<%=startdate%>" formName="schedule_assessment" startCaption="Start" startDateField="start" />
					</bbUI:dataElement>
					<bbUI:dataElement label="Start time (24-hour HH:MM)">
						<input type="text" id="start_hour" name="start_hour" size="2" disabled value="09" /> :
						<input type="text" id="start_minute" name="start_minute" size="2" disabled value="00" />
					</bbUI:dataElement>
					<bbUI:dataElement label="End date">
						<bbUI:datePicker startDate="<%=enddate%>" formName="schedule_assessment" startCaption="End" startDateField="end" />
					</bbUI:dataElement>
					<bbUI:dataElement label="End time (24-hour HH:MM)">
						<input type="text" id="end_hour" name="end_hour" size="2" disabled value="17" /> :
						<input type="text" id="end_minute" name="end_minute" size="2" disabled value="00" />
					</bbUI:dataElement>
				</bbUI:dataElement>
				<input type="hidden" name="group" value="<%=course.getBatchUid()%>" />
				<input type="hidden" name="course_id" value="<%=course_id%>" />
			</bbUI:step>
			<bbUI:stepSubmit title="Submit" number="2"/>
		</form>
			
		<%	
			}  //end else assessment length null check.

			} // end if staff 
			
			else {  //if student.
			//-----------------------------------------------------------------------
			// if by some chance you are not admin or instructor but are able to create content item.
			//-----------------------------------------------------------------------
		%>
		<bbUI:receipt type="FAIL" title="Insufficient rights - Incorrect role">
			We are sorry but you don't have sufficient rights to be able to schedule an assessment, please login as course instructor or administrator.
		</bbUI:receipt>
		<%
				return;
			}
		%>		
	
	</bbUI:docTemplateBody>


</bbData:context>