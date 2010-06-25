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
	
	%>

	<bbNG:pageHeader instructions="View Schedule"> 
	<bbNG:pageTitleBar iconUrl='<%=path+"/images/qm.gif"%>'
			title="Questionmark Scheduled Assessment" />
		<bbNG:breadcrumbBar environment="COURSE" isContent="true">
			<bbNG:breadcrumb><%=schedule_name%></bbNG:breadcrumb>
		</bbNG:breadcrumbBar>
	</bbNG:pageHeader>		
	
	<!--put in variables to display right here, DEBUGGING CODE	
	<p>
		<%out.println("Schedule name from form: " + schedule_name); 
			out.println("Parent id: " + parent_id);		
		%>
	</p>
	-->	
	
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
		Id courseIdObject = bbPm.generateId(Course.DATA_TYPE, courseId);

		CourseDbLoader courseLoader = (CourseDbLoader) bbPm.getLoader(CourseDbLoader.TYPE);
		Course course = courseLoader.loadById(courseIdObject);

		PropertiesBean pb = new PropertiesBean();

		//-----------------------------------------------------------------------
		//synchronization: No synching code needed for Tools view.
		//-----------------------------------------------------------------------
		
		//get Perception group id
		int perceptiongroupid;
		try {
			perceptiongroupid = new Integer(qmwise.getStub().getGroupByName(course.getBatchUid()).getGroup_ID()).intValue();
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
	<bbUI:receipt type="FAIL"
		title="Error synchronizing course users with Perception">
		<%=ne.getMessage()%>
	</bbUI:receipt>
	<%
					return;
				}
			} 
			else {
	%>
	<bbUI:receipt type="FAIL"
		title="Error retrieving course group from Perception">
		<%=qe.getMessage()%>
	</bbUI:receipt>
	<%
				return;
				
			}   //end of if(qe.getQMErrorCode...
		} //end of large catch

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
			
			<%
					ScheduleV42[] schedulesarray;
					try {
						schedulesarray = qmwise.getStub().getScheduleListByParticipantV42(new Integer(UserSynchronizer.getPhantomUserId()).intValue());
					} catch(Exception e) {
						QMWiseException qe = new QMWiseException(e);
			%>
			<bbUI:receipt type="FAIL" title="Error getting group schedule list">
				<%=qe.getMessage()%>
			</bbUI:receipt>
			<%
						return;
					}

					Vector<ScheduleV42> schedules = new Vector<ScheduleV42>();

					for(int i = 0; i < schedulesarray.length; i++) {
						if(schedulesarray[i].getGroup_ID() == perceptiongroupid)
							schedules.add(schedulesarray[i]);
					}

					ScheduleV42[] zeroschedulesarray;
					try {
						zeroschedulesarray = qmwise.getStub().getScheduleListByParticipantV42(0);
					} catch(Exception e) {
						QMWiseException qe = new QMWiseException(e);
			%>
			<bbUI:receipt type="FAIL" title="Error getting zero user schedule list">
				<%=qe.getMessage()%>
			</bbUI:receipt>
			<%
						return;
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
			%>
			<bbUI:receipt type="FAIL" title="Error getting assessment URL">
				<%=qe.getMessage()%>
			</bbUI:receipt>
			<%
							return;
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
				<script type="text/javascript">
						function showhideScheduleURL(box,rowID) {
							var row = document.getElementById(rowID) 
							row.style.display = box.checked? "table-row":"none"
							}
				</script>
				<tr>
					<!--<th>Assessment ID</th>-->
					<th>Schedule name</th>
					<th>Maximum attempts</th>
					<th>Start datetime</th>
					<th>End datetime</th>
					<th>Active?</th>
					<th>Try Out</th>
					<th>Show URL</th>
					<!--<th>Group</th>-->
				</tr>
			<%
						for(int i = 0; i < schedules.size(); i++) {
							String idStr="scheduleURL_"+Integer.toString(i);
							if(schedules.get(i) == null) continue;
							if(schedule_name!=null && schedule_name.length()>0 && 
									!schedule_name.equals(schedules.get(i).getSchedule_Name())) continue;						
									//this is what is different in the content item view. Want to see 
									//just the schedule created through the content item creation form.	
									
									
			%>
				<tr>
					<!--<td><%=schedules.get(i).getAssessment_ID()%></td>-->
					<td><%=schedules.get(i).getSchedule_Name()%></td>
					<td><%=schedules.get(i).isRestrict_Attempts() ? schedules.get(i).getMax_Attempts() : "no limit"%></td>
					<td><%=!schedules.get(i).isRestrict_Times() ? "None" : schedules.get(i).readSchedule_Starts_asCalendar().getTime().toString()%></td>
					<td><%=!schedules.get(i).isRestrict_Times() ? "None" : schedules.get(i).readSchedule_Stops_asCalendar().getTime().toString()%></td>
					<td><%=schedulesactive[i] ? "active" : "inactive"%></td>
					<td><a href="<%=scheduleurls[i]%>" target="_blank">Test
					assessment</a></td>
					<td><input type="checkbox" name="switchBox"
						onClick="showhideScheduleURL(this,'<%=idStr%>')" /></td>
					<!--<td><%=schedules.get(i).getGroup_ID()%></td>-->
				</tr>
				<tr id='<%=idStr%>' style="display: none;">
					<td><i>URL:</i></td>
					<td colspan="6"><code><%=basePath+"links/main.jsp?course_id="+courseId+"&amp;schedule_name="+URLEncoder.encode(schedules.get(i).getSchedule_Name())%></code></td>
				</tr>
			<% 			}
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
					int perceptionuserid;
					try {
						perceptionuserid = new Integer(qmwise.getStub().getParticipantByName(
							sessionUser.getUserName()).getParticipant_ID()).intValue();
					} catch(Exception e) {
						QMWiseException qe = new QMWiseException(e);
			%>
			
					<bbUI:receipt type="FAIL"
						title="Error retrieving participant from Perception">
						<%=qe.getMessage()%>
					</bbUI:receipt>
			<%
			
						return;
					}

					ScheduleV42[] schedulesarray;
					try {
						schedulesarray = qmwise.getStub().getScheduleListByParticipantV42(perceptionuserid);
					} catch(Exception e) {
						QMWiseException qe = new QMWiseException(e);
			%>
					<bbUI:receipt type="FAIL"
						title="Error getting participant schedule list">
						<%=qe.getMessage()%>
					</bbUI:receipt>
			<%
						return;
					}

					Vector<ScheduleV42> schedules = new Vector<ScheduleV42>();

					for(int i = 0; i < schedulesarray.length; i++) {
						if(schedulesarray[i].getGroup_ID() == perceptiongroupid)
							schedules.add(schedulesarray[i]);
					}

					ScheduleV42[] zeroschedulesarray;
					try {
						zeroschedulesarray = qmwise.getStub().getScheduleListByParticipantV42(0);
					} catch(Exception e) {
						QMWiseException qe = new QMWiseException(e);
			
			%>
					<bbUI:receipt type="FAIL" title="Error getting zero user schedule list">
						<%=qe.getMessage()%>
					</bbUI:receipt>
			<%
						return;
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
									//exception is saying "no attempts left"
									scheduleurls[i] = null;
								}
							} catch(Exception e) {
								QMWiseException qe = new QMWiseException(e);
			%>
			<bbUI:receipt type="FAIL" title="Error getting assessment URL">
				<%=qe.getMessage()%>
			</bbUI:receipt>
			<%
								return;
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
						//if(showOnlySchedule!=null && showOnlySchedule.length()>0 && 
						//		!showOnlySchedule.equals(schedules.get(i).getSchedule_Name())) continue;
						
						if(schedule_name!=null && schedule_name.length()>0 && 
								!schedule_name.equals(schedules.get(i).getSchedule_Name())) continue;						
								//this is what is different in the content item view. Want to see 
								//just the schedule created through the content item creation form.						
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
			
			<% 		if(schedulesactive[i]) { %> <% if(scheduleurls[i] == null) { 
			%> 			No attempts remaining 
			<% 		} else 
					{ 
			%>
						<form><input type="button" value="Take assessment"
							onclick="window.open('<%=scheduleurls[i]%>');">
						</form>
			<% 		}
			%> 
			<% 		} else { 
			%>			 inactive 
			<% 		} 
			%>
						</td>
						<!--<td><%=schedules.get(i).getGroup_ID()%></td>-->
					</tr>
			<% 		}
			
			%>
				</table>
			<%
				} //end of Student view if-else statement				

	%>			
			

	
</bbNG:learningSystemPage>
	
