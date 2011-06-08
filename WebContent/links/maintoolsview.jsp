<%@ page language="java" pageEncoding="UTF-8"
	import="
		java.util.*,
		java.text.*,
		java.net.*,
		blackboard.platform.*,
		blackboard.base.*,
		blackboard.platform.session.*,
		blackboard.data.user.*,
		blackboard.platform.persistence.PersistenceServiceFactory,
		blackboard.persist.* ,
		blackboard.persist.user.*,
		blackboard.data.course.*,
		blackboard.persist.course.*,
		org.apache.axis.*,
		org.apache.commons.lang.StringEscapeUtils,
		java.rmi.RemoteException,
		javax.xml.namespace.QName,
		com.questionmark.*,
		com.questionmark.QMWISe.*
	"%>

<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ taglib uri="/bbData" prefix="bbData"%>
<%@ taglib uri="/bbNG" prefix="bbNG"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String schedule_name = request.getParameter("schedule_name");	
%>


<bbNG:learningSystemPage ctxId="ctx"
	title="Questionmark Perception connector" onLoad="disable_set_access()">
	<bbNG:pageHeader>
		
		<bbNG:breadcrumbBar environment="COURSE" isContent="false">
			<bbNG:breadcrumb>QUESTIONMARK PERCEPTION TOOLS VIEW</bbNG:breadcrumb>
		</bbNG:breadcrumbBar>
			
		<bbNG:pageTitleBar iconUrl='<%=path+"/images/qm.gif"%>'
			title="Questionmark Perception connector" />
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
				<p><%=qe.getMessage()%></p>
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
		//synchronization: No synching code needed for Tools view.
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
		
		} catch(Exception e) {
			QMWiseException qe = new QMWiseException(e);
			if(qe.getQMErrorCode() == 1201) {
				//group doesn't exist -- force sync
				System.out.println("Perception: course " + courseId + 
					": Perception group doesn't exist -- forcing synchronization");
				UserSynchronizer us = new UserSynchronizer();
				try {
					us.synchronizeCourse(courseId);
					configReader.setCourseSyncDate();
					//get fresh group					
					perceptiongroupid = new Integer(qmwise.getStub().getGroupByName(
						course.getBatchUid()).getGroup_ID()).intValue();
						
				} catch (Exception ne) {
					System.out.println("Perception: course " + courseId + ": synchronization failed: " + ne.getMessage());
					%>
					<h1>Error synchronising course users with Perception</h1>
						<p><%=StringEscapeUtils.escapeHtml(ne.getMessage())%></p>					
	
					<%
				//return;
				} 
			} 
			else {
					%>
					<h1>Error retrieving course group from Perception</h1>
						<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>	
					<%
					//return;
				
			}   //end of if(qe.getQMErrorCode...
		} //end of large catch

		//-----------------------------------------------------------------------
		//view (still) specific to current user, i.e. Student can only Take assessments
		// and Staff can "Test Assessments"
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
		
		if (courseSettings.getProperty("hide_schedules","0").equals("1")) { 
			 
			%>
				<p><i>There are no schedules available from this page.</i></p>
			<% 			
		}
		else
		{	
			if(crsMembership.getRole() == CourseMembership.Role.INSTRUCTOR
					|| crsMembership.getRole() == CourseMembership.Role.TEACHING_ASSISTANT) 
				{
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
			

	<h1 id="Schedules">Schedules</h1>
	<%
		ScheduleV42[] schedulesarray = null;
		try {				
			schedulesarray = qmwise.getStub().getScheduleListByParticipantV42(new Integer(UserSynchronizer.getPhantomUserId()).intValue());
		} catch (Exception e){			
				if( e instanceof QMWiseException ){
					QMWiseException qe = new QMWiseException(e);
					if(qe.getQMErrorCode() == 1301){
						String assessmentErrorOutput = "Perception: course " + courseId + 
						": Error getting group schedule list. Cause: Assessment not found, check whether assessment exists in Perception."
						+ " For more information please check Perception server logs - QMWISe trace log. Message: "
						+ qe.getMessage();
						System.out.println(assessmentErrorOutput);					
						%>
							<h1>Error getting group schedule list, assessment missing!</h1>
							<p><%=StringEscapeUtils.escapeHtml(assessmentErrorOutput)%></p>
						<%						
					} else {
						String qmErrorOutput = "Perception: course " + courseId + ": Error getting group schedule list. Cause: " + qe.getMessage();
						
						System.out.println(qmErrorOutput);
						%>
							<h1>Error getting group schedule list, QMWISe error!</h1>
							<p><%=StringEscapeUtils.escapeHtml(qmErrorOutput)%></p>
						<%
					}
				} else {
					String errorMessage = e.getMessage();
					System.out.println("Unknown Exception returned: details: " + e.getMessage());
					%><p>Error getting schedules, unknown exception</p>
						<p><%=StringEscapeUtils.escapeHtml(errorMessage)%></p>	
					<%						
				
				}
				// Return disabled to allow for the page to continue loading.
				//return;
			
			}

			Vector<ScheduleV42> schedules = new Vector<ScheduleV42>();

			for(int i = 0; i < schedulesarray.length; i++) {
				if(schedulesarray[i].getGroup_ID() == perceptiongroupid)
					schedules.add(schedulesarray[i]);
			}

			ScheduleV42[] zeroschedulesarray = null;
			try {
				zeroschedulesarray = qmwise.getStub().getScheduleListByParticipantV42(0);
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
					<h1>Error getting zero user schedule list</h1>
					<p><%=StringEscapeUtils.escapeHtml(e.getMessage())%></p>
	
				<%
				//return;
			}

			for(int i = 0; i < zeroschedulesarray.length; i++)
				if(zeroschedulesarray[i].getGroup_ID() == perceptiongroupid)
					schedules.add(zeroschedulesarray[i]);

			String[] scheduleurls = new String[schedules.size()];
			boolean[] schedulesactive = new boolean[schedules.size()];


			for(int i = 0; i < schedules.size(); i++) {
				try {
					scheduleurls[i] = qmwise.getStub().getAccessAssessment(
						schedules.get(i).getAssessment_ID(),
						sessionUser.getUserName(),
						"", //participant details
						"" //group name
					);
				} catch(Exception e) {
					QMWiseException qe = new QMWiseException(e);
					if(qe.getQMErrorCode() == 1301){
						String assessmentErrorOutput = "Perception: course " + courseId + 
						": Error getting group schedule list. Cause: Assessment not found, check whether assessment exists in Perception."
						+ " For more information please check Perception server logs - QMWISe trace log. Message: "
						+ qe.getMessage();
						System.out.println(assessmentErrorOutput);
						
						//Set the schedule url to blank and check for blank url later on to decide whether schedule is active or broken.						
						scheduleurls[i] = "ASSESSMENT_ERROR: Assessment not found, check whether assessment exists in Perception";
												
					}
					else {
						
						//Set the schedule url to blank and check for blank url later on to decide whether schedule is active or broken.						
						scheduleurls[i] = "ERROR: " + qe.getMessage().substring(0, 40);						
						
					}
				}


				Long schedule_start = schedules.get(i).readSchedule_Starts_asCalendar().getTime().getTime();
				Long schedule_stop = schedules.get(i).readSchedule_Stops_asCalendar().getTime().getTime();
				Long now = new Date().getTime();
				if (schedules.get(i).isRestrict_Times() && (
					schedule_start > now //not started yet
					|| schedule_stop >= 0 && schedule_stop < now //stop time is set (not 0001 AD) && already finished
				)) {
					schedulesactive[i] = false;
				} else {
					schedulesactive[i] = true;
				}
			}

			%>
			
			
		<table border="2" cellpadding="1">
		<bbNG:jsBlock>
			<script type="text/javascript">
					function showhideScheduleURL(box,rowID) {
						var row = document.getElementById(rowID); 
						row.style.display = box.checked? "table-row":"none";
						}
			</script>
		</bbNG:jsBlock>
		<tr>
			
			<th>Schedule name</th>
			<th>Maximum attempts</th>
			<th>Start datetime</th>
			<th>End datetime</th>
			<th>Active?</th>
			<th>Try Out</th>
			<th>Show URL</th>
		</tr>
		<%
				for(int i = 0; i < schedules.size(); i++) {
					String idStr="scheduleURL_"+Integer.toString(i);
					if(schedules.get(i) == null) continue;
					if(schedule_name != null && schedule_name.length() > 0){
						if(!schedules.get(i).getSchedule_Name().equals(schedule_name)) continue;	
					}
					
					%>
					<tr>
	
						<td><%=schedules.get(i).getSchedule_Name()%></td>
						<td><%=schedules.get(i).isRestrict_Attempts() ? schedules.get(i).getMax_Attempts() : "no limit"%></td>
						<td><%=!schedules.get(i).isRestrict_Times() ? "None" : schedules.get(i).readSchedule_Starts_asCalendar().getTime().toString()%></td>
						<td><%=!schedules.get(i).isRestrict_Times() ? "None" : schedules.get(i).readSchedule_Stops_asCalendar().getTime().toString()%></td>
						<td><%=schedulesactive[i] ? "active" : "inactive"%></td>
						
						<% 
						
						if (scheduleurls[i].contains("ASSESSMENT_ERROR")) {						
							%>
								<td bgcolor="yellow"><i>Schedule Disabled: <%=StringEscapeUtils.escapeHtml(scheduleurls[i])%> </i></td>
							<% 
							
						} else if(scheduleurls[i].contains("ERROR")){
							%>
								<td bgcolor="yellow"><i>Schedule Disabled: <%=StringEscapeUtils.escapeHtml(scheduleurls[i])%> </i></td>
							<% 
						}
						else {
							%>							
								<td><a href="<%=StringEscapeUtils.escapeHtml(scheduleurls[i])%>" target="_blank">Test assessment</a></td>
							<% 
						} 
						
						%>
						
						<td><input type="checkbox" name="switchBox"
							onClick="showhideScheduleURL(this,'<%=idStr%>')" /></td>
						<!--<td><%=schedules.get(i).getGroup_ID()%></td>-->
					</tr>
					<tr id='<%=idStr%>' style="display: none;">
						<td><i>URL:</i></td>
						<td colspan="6"><code><%=basePath+"links/main.jsp?course_id="+courseId+"&amp;schedule_name="+StringEscapeUtils.escapeHtml(URLEncoder.encode(schedules.get(i).getSchedule_Name(),"UTF-8"))%></code></td>
					</tr>
					<% 
				} 
				%>
	</table>
	
	<bbUI:spacer height="20" />

			<%
					//-----------------------------------------------------------------------
					// No Schedule-authoring form in tools view: code removed.
					//-----------------------------------------------------------------------
				
				
				} // End of Staff View!!
				
				else 
				{
					//-----------------------------------------------------------------------
					//Student
					//-----------------------------------------------------------------------

					//get Perception user id
					int perceptionuserid = 0;
					try {
						perceptionuserid = new Integer(qmwise.getStub().getParticipantByName(
							sessionUser.getUserName()).getParticipant_ID()).intValue();
					} catch(Exception e) {
						QMWiseException qe = new QMWiseException(e);
						%>
							<h1>Error retrieving participant from Perception</h1>
								<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>							
			
						<%
						//return;
					}

					ScheduleV42[] schedulesarray = null;
					try {
						schedulesarray = qmwise.getStub().getScheduleListByParticipantV42(perceptionuserid);
					} catch(Exception e) {
						QMWiseException qe = new QMWiseException(e);
						%>
							<h1>Error getting participant schedule list</h1>
								<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>							
			
						<% 
						//return;
					}

					Vector<ScheduleV42> schedules = new Vector<ScheduleV42>();

					for(int i = 0; i < schedulesarray.length; i++) {
						if(schedulesarray[i].getGroup_ID() == perceptiongroupid)
							schedules.add(schedulesarray[i]);
					}

					ScheduleV42[] zeroschedulesarray = null;
					try {
						zeroschedulesarray = qmwise.getStub().getScheduleListByParticipantV42(0);
					} catch(Exception e) {
						QMWiseException qe = new QMWiseException(e);
						%>
							<h1>Error getting zero user schedule list</h1>
								<p><%=StringEscapeUtils.escapeHtml(qe.getMessage())%></p>							
			
						<%
						//return;
					}

					for(int i = 0; i < zeroschedulesarray.length; i++) {
						if(zeroschedulesarray[i].getGroup_ID() == perceptiongroupid)
							schedules.add(zeroschedulesarray[i]);
					}

					String[] scheduleurls = new String[schedules.size()];
					boolean[] schedulesactive = new boolean[schedules.size()];
					
					if ((schedule_name == null || schedule_name.length()==0) && 
						courseSettings.getProperty("hide_schedules","0").equals("1")) {
						schedule_name="HIDE_ALL_SCHEDULES";
					}
					
					for(int i = 0; i < schedules.size(); i++) {
						//if the schedule is currently active get a URL to 
						//launch it
						//haven't coded any timezone handling in here, so this 
						//may be dodgy
						Long schedule_start = schedules.get(i).readSchedule_Starts_asCalendar().getTime().getTime();
						Long schedule_stop = schedules.get(i).readSchedule_Stops_asCalendar().getTime().getTime();
						Long now = new Date().getTime();
						if(
							schedules.get(i).isRestrict_Times()
							&& (
								schedule_start > now //not started yet
								|| schedule_stop >= 0 && schedule_stop < now 
								//stop time is set (not 0001 AD) && already finished
							)
						) {
							schedulesactive[i] = false;
							scheduleurls[i] = null;
						} else {
							schedulesactive[i] = true;
							try {
								Parameter[] parameters = {
									new Parameter("bb_schedulename", schedules.get(i).getSchedule_Name()),
									new Parameter("bb_scheduleid", new Integer(
										schedules.get(i).getSchedule_ID()).toString()),
									new Parameter("bb_courseid", course.getBatchUid())
								};
								try {
									
									//Try to trigger a missing assessment exception using the following
									String assessmentTest = qmwise.getStub().getAccessAssessment(
											schedules.get(i).getAssessment_ID(),
											sessionUser.getUserName(),
											"", //participant details
											"" //group name
											);
									
									//If the above fails the exception code will treat it as a qmwise assessment error
									// and disable the schedule for the participant..
									
									//If not then the participant gets a 'valid' perception assessment url as normal!
									
									scheduleurls[i] = qmwise.getStub().getAccessScheduleNotify(
										new Integer(schedules.get(i).getSchedule_ID()).toString(),
										sessionUser.getUserName(),
										request.getScheme() + "://" + request.getServerName() 
											+ request.getContextPath() + "/links/callback.jsp",	
												"blackboard.pip", parameters);
									
								} catch(Exception ne) {
									//this method hasn't been programmed well 
									//and doesn't have unique error codes for 
									//each kind of exception. we avoided the 
									//"schedule hasn't started or already 
									//finished" one by checking that first, so 
									//we can hopefully assume that this 
									//exception is saying "no attempts left" - wrong
									
									//Let's handle this correctly:
									QMWiseException qe = new QMWiseException(ne);
									
									//If assessment missing error, then..
									
									if (qe.getQMErrorCode() == 1301) {
										schedules.get(i).setSchedule_Name(schedules.get(i).getSchedule_Name() + " QMWISE_ASSESSMENT_ERROR ");
										scheduleurls[i] = "QMWISE_ASSESSMENT_ERROR";
									}
									else {
										schedules.get(i).setSchedule_Name(schedules.get(i).getSchedule_Name() + " QMWISE ERROR ");
										scheduleurls[i] = "ERROR";
									}		
									
									schedulesactive[i] = false;
		
									// No notification needed to give student. No error message displayed.
									
								}
							} catch(Exception e) {								
								scheduleurls[i] = "ERROR";
								schedulesactive[i] = false;
								
								// No notification needed to give student. No error message displayed.
								
								
								}
							
						}
					}

			%>
			
				<table border="2" cellpadding="1">
					<tr>
						<!--<th>Assessment ID</th>-->
						<th>Schedule name</th>
						<th>Remaining attempts</th>
						<th>Start datetime</th>
						<th>End datetime</th>
						<th>Actions</th>
						<!--<th>Group</th>-->
					</tr>
			<%
					for(int i = 0; i < schedules.size(); i++) {
						if(schedules.get(i) == null) continue;
						if(schedulesactive[i] == false) continue;
			%>
					<tr>
						<!--<td><%=schedules.get(i).getAssessment_ID()%></td>-->						
						
						<td><%=schedules.get(i).getSchedule_Name()%></td>								
												
						<td><%=schedules.get(i).isRestrict_Attempts() ? schedules.get(i).getMax_Attempts() 
							: "no limit"%></td>
						<td><%=!schedules.get(i).isRestrict_Times() ? "None" 
							: schedules.get(i).readSchedule_Starts_asCalendar().getTime().toString()%></td>
						<td><%=!schedules.get(i).isRestrict_Times() ? "None" 
							: schedules.get(i).readSchedule_Stops_asCalendar().getTime().toString()%></td>
						<td>
			
			<% 			if(schedulesactive[i]) { 
			%> 	
			
							<form><input type="button" value="Take assessment"
								onclick="window.open('<%=scheduleurls[i]%>');">
							</form>
							
			<%	 		}
			%> 
				
						</td>
						<!--<td><%=schedules.get(i).getGroup_ID()%></td>-->
					</tr>
			<% 		} //end of for loop
			
			%>
				</table>
			<%
				} //end of Student view if-else statement
				
		}	//end of hidden schedules query if else
	%>			
			

	
</bbNG:learningSystemPage>
