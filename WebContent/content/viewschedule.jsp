<!-- 
// Filename		: content/viewschedule.jsp
// Description	: To view an existing Questionmark Schedule content item.
//				: Features separate views for staff and students,i.e. user
//				: 'role' specific options.				
-->


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
		blackboard.data.content.*,
		blackboard.persist.content.*,
		blackboard.persist.content.ContentDbLoader,
		blackboard.persist.Id,		
		blackboard.persist.course.*,
		blackboard.platform.persistence.PersistenceServiceFactory,
		org.apache.axis.*,
		org.apache.commons.lang.StringEscapeUtils,
		java.rmi.RemoteException,
		javax.xml.namespace.QName,
		com.questionmark.*,
		com.questionmark.QMWISe.*"	

	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="/bbUI" prefix="bbUI" %> 
<%@ taglib uri="/bbData" prefix="bbData" %>
<%@ taglib uri="/bbNG" prefix="bbNG"%>


<bbNG:learningSystemPage ctxId="ctx">

	<%	
	
	String course_id = request.getParameter("course_id");
	String content_id = request.getParameter("content_id");
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	//Get a User , course membership details, and Course instance via context
	User sessionUser = ctx.getUser();
	Id sessionUserId = sessionUser.getId();
	Course courseCtx = ctx.getCourse();	
	CourseMembership cMembership = ctx.getCourseMembership(); // may be null if a SysAdmin!	
	

	Id contentId = Id.generateId(Content.DATA_TYPE, content_id);
	ContentDbLoader courseDocumentLoader = ContentDbLoader.Default.getInstance();
	Content courseDoc = courseDocumentLoader.loadById( contentId ); 

	// can now query this...
	String parent_id = courseDoc.getParentId().toExternalString();
			
	String schedule_name = courseDoc.getTitle();
	
	String schedule_description = courseDoc.getBody().getText();
	
	%>

	<bbNG:pageHeader instructions="<%=schedule_description%>"> 
	<bbNG:pageTitleBar iconUrl='<%=path+"/images/qm.gif"%>'
			title="<%=schedule_name%>" />
		<bbNG:breadcrumbBar environment="COURSE" isContent="true">
			<bbNG:breadcrumb><%=schedule_name%></bbNG:breadcrumb>
		</bbNG:breadcrumbBar>
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
		//synchronization: No Participant synching code needed for Tools view.
		//-----------------------------------------------------------------------
		
		
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
					System.out.print(force_sync_result);
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
		
		//----------------------End of sync block-----------------------------------------------
		
						

		//-----------------------------------------------------------------------
		//view (still) specific to current user, i.e. Student can only Take assessments
		// and Staff can "Test Assessments"
		//-----------------------------------------------------------------------

		
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
						//Suppressing synch related html on this page. Synch errors should show up on the course tools view.
						
					}
				} else {
					String errorMessage = e.getMessage();
					System.out.println("Unknown Exception returned: details: " + e.getMessage());
					//Suppressing synch related html on this page. Synch errors should show up on the course tools view.					
				
				}
				// Return disabled to allow for the page to continue loading.
				//return;
				
			} //End of catch block

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
						
						schedules.get(i).setSchedule_Name(schedules.get(i).getSchedule_Name() 
								+ " - ERROR.");	
						
						scheduleurls[i] = "";
						//Suppress html being printed as part of exception, not needed as the schedule will be highlighted if
						//there is an error.					
					}
					else {						
					
						//Suppress html being printed as part of exception, not needed as the schedule will be highlighted if
						//there is an error.					
					}
				}

				int schedule_id = schedules.get(i).getSchedule_ID(); //Can get schedule id to enable deletion	
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
		<tr>
			<!--<th>Assessment ID</th>-->
			<th>Schedule name</th>
			<th>Maximum attempts</th>
			<th>Start datetime</th>
			<th>End datetime</th>
			<th>Active?</th>
			<th>Try Out</th>
			<!--<th>Group</th>-->
		</tr>
			<%
			
			/*
				WARNING: WE JUST WANT ONE SCHEDULE HERE!
				
				This is what is different in the content item view. Want to see 
				just the schedule created through the content item creation form.
			*/
			
				for(int i = 0; i < schedules.size(); i++) {
					String idStr="scheduleURL_"+Integer.toString(i);
					if(schedules.get(i) == null) continue;
					if(schedule_name!=null && schedule_name.length()>0 && 
							!schedule_name.equals(schedules.get(i).getSchedule_Name())) continue;						
										
									
			%>
					<tr>
						<!--<td><%=schedules.get(i).getAssessment_ID()%></td>-->
						
						<% 
							if ( schedules.get(i).getSchedule_Name().contains("ERROR") ) { 
							%>
								<td bgcolor="yellow"><%=schedules.get(i).getSchedule_Name()%>
							 		<i> : Missing Assessment in Perception, Schedule Disabled</i></td>
							<% 
							} else {
								%>
									<td><%=schedules.get(i).getSchedule_Name()%></td>
								<% 
							}
						%>						
						
						<td><%=schedules.get(i).isRestrict_Attempts() ? schedules.get(i).getMax_Attempts() : "no limit"%></td>
						<td><%=!schedules.get(i).isRestrict_Times() ? "None" : schedules.get(i).readSchedule_Starts_asCalendar().getTime().toString()%></td>
						<td><%=!schedules.get(i).isRestrict_Times() ? "None" : schedules.get(i).readSchedule_Stops_asCalendar().getTime().toString()%></td>
						<td><%=schedulesactive[i] ? "active" : "inactive"%></td>
						
						<% 
						
						if (scheduleurls[i].length() == 0) {						
							%>
								<td bgcolor="yellow"><i>Schedule Disabled</i></td>
							<% 
							
						} else {
							%>							
								<td><a href="<%=StringEscapeUtils.escapeHtml(scheduleurls[i])%>" target="_blank">Test assessment</a></td>
							<% 
						} 
						
						%>
						
						<!--<td><%=schedules.get(i).getGroup_ID()%></td>-->
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


					for (int i = 0; i < schedules.size(); i++) {
						//if the schedule is currently active get a URL to 
						//launch it
						//haven't coded any timezone handling in here, so this 
						//may be dodgy
						Long schedule_start = schedules.get(i)
								.readSchedule_Starts_asCalendar().getTime()
								.getTime();
						Long schedule_stop = schedules.get(i)
								.readSchedule_Stops_asCalendar().getTime()
								.getTime();
						Long now = new Date().getTime();
						if (schedules.get(i).isRestrict_Times()
								&& (schedule_start > now //not started yet
								|| schedule_stop >= 0
										&& schedule_stop < now
								//stop time is set (not 0001 AD) && already finished
								)) {
							schedulesactive[i] = false;
							scheduleurls[i] = null;
						} else {
							schedulesactive[i] = true;
							try {
								Parameter[] parameters = {
										new Parameter("bb_schedulename",
												schedules.get(i)
														.getSchedule_Name()),
										new Parameter("bb_scheduleid",
												new Integer(schedules
														.get(i)
														.getSchedule_ID())
														.toString()),
										new Parameter("bb_courseid", course
												.getBatchUid()) };
								try {

									//Try to trigger a missing assessment exception using the following
									String assessmentTest = qmwise
											.getStub()
											.getAccessAssessment(
													schedules
															.get(i)
															.getAssessment_ID(),
													sessionUser
															.getUserName(),
													"", //participant details
													"" //group name
											);

									//If the above fails the exception code will treat it as a qmwise assessment error
									// and disable the schedule for the participant..

									//If not then the participant gets a 'valid' perception assessment url as normal!

									scheduleurls[i] = qmwise
											.getStub()
											.getAccessScheduleNotify(
													new Integer(
															schedules
																	.get(i)
																	.getSchedule_ID())
															.toString(),
													sessionUser
															.getUserName(),
													request.getScheme()
															+ "://"
															+ request
																	.getServerName()
															+ request
																	.getContextPath()
															+ "/links/callback.jsp",
													"blackboard.pip",
													parameters);

								} catch (Exception ne) {
									//this method hasn't been programmed well 
									//and doesn't have unique error codes for 
									//each kind of exception. we avoided the 
									//"schedule hasn't started or already 
									//finished" one by checking that first, so 
									//we can hopefully assume that this 
									//exception is saying "no attempts left" - wrong

									//Let's handle this correctly:
									QMWiseException qe = new QMWiseException(
											ne);

									//If assessment missing error, then..

									if (qe.getQMErrorCode() == 1301) {
										schedules
												.get(i)
												.setSchedule_Name(
														schedules
																.get(i)
																.getSchedule_Name()
																+ " QMWISE_ASSESSMENT_ERROR ");
										scheduleurls[i] = "QMWISE_ASSESSMENT_ERROR";
									} else {
										schedules.get(i).setSchedule_Name(
												schedules.get(i)
														.getSchedule_Name()
														+ " QMWISE ERROR ");
										scheduleurls[i] = "ERROR";
									}

									schedulesactive[i] = false;

									// No notification needed to give student. No error message displayed.

								}
							} catch (Exception e) {
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
						
						//IMPORTANT: Just want to show the student the schedule which matches the participant selected schedule:
							
						if(schedule_name!=null && schedule_name.length()>0 && 
								!schedule_name.equals(schedules.get(i).getSchedule_Name())) continue;
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
			
			<% 			//Only display active schedules with valid url's
			
						if(schedulesactive[i]) { 
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
			

	%>			
			

	
</bbNG:learningSystemPage>
	
