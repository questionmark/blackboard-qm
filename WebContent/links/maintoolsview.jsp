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

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String schedule_name = request.getParameter("schedule_name");	
%>

<bbData:context id="ctx">
	<bbUI:docTemplateHead title="Questionmark Perception connector" />

	<bbUI:docTemplateBody onLoad="disable_set_access()">
		<bbUI:titleBar iconUrl='<%=path+"/images/qm.gif"%>' >
			Questionmark Perception connector
		</bbUI:titleBar>
		
		<bbUI:breadcrumbBar environment="COURSE" isContent="false">
			<bbUI:breadcrumb>QUESTIONMARK PERCEPTION TOOLS VIEW</bbUI:breadcrumb>
		</bbUI:breadcrumbBar>
			



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

%>
		<%@ include file="../common/gsynchronization.jspf" %>
<%			
		
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
			
		<div id="actionbar" class="actionBar clearfix editmode" align="right">
												
	<%
			if(pb.getProperty("perception.singlesignon") != null) {
	%>
				
			<a href='<%=path+"/links/enterprisemanager.jsp"%>' target="_blank">
				Log in to Enterprise Manager</a>
			
	<%
			}
	%>
		</div>		

			<h1 id="Schedules">Schedules</h1>
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
							if(schedulesactive[i] == false) continue;
								
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
			<% 				if(scheduleurls[i] == null) { 
			%> 					No attempts remaining 
			<% 				}
							else 
							{ 
			%>		
								<form><input type="button" value="Take assessment"
									onclick="window.open('<%=scheduleurls[i]%>');">
								</form>
			<%	 			}
			%> 
			<% 			} 
						else
						{ ////technically shouldn't arrive at this line but hey.
			%>				 inactive 
			<% 			} 
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
			

	</bbUI:docTemplateBody>
</bbData:context>
