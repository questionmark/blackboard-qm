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



<bbData:context id="ctx">

	<%	


	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	// Retrieve the course identifier from the URL
	String courseId = request.getParameter("course_id");	
	//null checks..
	if(courseId == null) {
	%>
	
		<bbUI:receipt type="FAIL" title="No course ID was given">
			No course ID was given with the request
		</bbUI:receipt>
		
	<%
		return;
	}

	//Retreive the content identifier from the url
	
	String content_id = request.getParameter("content_id");
	//null check..
	if(content_id == null) {
	%>
	
		<bbUI:receipt type="FAIL" title="No content ID was given">
			No content ID was given with the request
		</bbUI:receipt>
		
	<%
		return;
	}

	
	//Get a User , course membership details, and Course instance via context
	User sessionUser = ctx.getUser();
	Id sessionUserId = sessionUser.getId();
	Course courseCtx = ctx.getCourse();	
	CourseMembership cMembership = ctx.getCourseMembership(); // may be null if a SysAdmin!	
	
	//Retrieve the Db persistence manager from the persistence service
	BbPersistenceManager bbPm = PersistenceServiceFactory.getInstance().getDbPersistenceManager();

	// Generate a persistence framework course Id to be used for 
	// loading the course
	
	Id courseIdObject = bbPm.generateId(Course.DATA_TYPE, courseId);
	CourseDbLoader courseLoader = (CourseDbLoader) bbPm.getLoader(CourseDbLoader.TYPE);
	Course course = courseLoader.loadById(courseIdObject);

	PropertiesBean pb = new PropertiesBean();	//probably not needed
		
	//Now get the content id informataion
	
	Id contentId = Id.generateId(Content.DATA_TYPE, content_id);
	ContentDbLoader courseDocumentLoader = ContentDbLoader.Default.getInstance();
	Content courseDoc = courseDocumentLoader.loadById( contentId ); 

	// can now query this from the content item object reference...
	
	String handler = courseDoc.getContentHandler();
	
	String persistantParentId = courseDoc.getParentId().toExternalString();
	Id parentId = bbPm.generateId(Content.DATA_TYPE, persistantParentId);
	
	
	
	String persistentScheduleName = courseDoc.getTitle();	
	
	%>

	<bbUI:docTemplateHead title="Questionmark Perception connector" />

	<bbUI:docTemplateBody onLoad="disable_set_access()">
		<bbUI:titleBar iconUrl='<%=path+"/images/qm.gif"%>' >
			Questionmark Scheduled Assessment
		</bbUI:titleBar>
		
		<bbUI:breadcrumbBar environment="COURSE" isContent="true">
			<bbUI:breadcrumb><%=persistentScheduleName%></bbUI:breadcrumb>
		</bbUI:breadcrumbBar>
	
	
	<!--put in variables to display right here, DEBUGGING CODE
	
	<p>
			
		<%	
			out.println("course id: from context:-  " + courseId + "\n");
			out.println("content id: from context:-  " + content_id + "\n");

			out.println("Parent id from persisted object: " + persistantParentId+ "\n");
			
			out.println("Schedule name from persisted object: " + persistentScheduleName + "\n");
			
			out.println("group name from context: " + request.getParameter("group") + "\n");

			out.println("Content handler string from persisted object: "
					+ handler + "\n");
			
			out.println("This is your system role user: " + sessionUser.getSystemRole().toExternalString());
		%>
	</p>
	-->
	
	<%

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

		//-----------------------------------------------------------------------
		//synchronization: No Automatic or manual option for synching code needed 
		//for Content item view. Only code for 'forced' sync if group not found:
		//-----------------------------------------------------------------------
%>
		<%@ include file="../common/gsynchronization.jspf" %>
<%			
		//-----------------------------------------------------------------------
		//view (still) specific to current user, i.e. Student can only Take assessments
		// and Staff can "Test Assessments"
		//-----------------------------------------------------------------------

		
		// get the membership data to determine the User's Role
		CourseMembershipDbLoader crsMembershipLoader = (CourseMembershipDbLoader) 
			bbPm.getLoader(CourseMembershipDbLoader.TYPE);
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
					|| crsMembership.getRole() == CourseMembership.Role.TEACHING_ASSISTANT
						|| sessionUser.getSystemRole() == sessionUser.getSystemRole().SYSTEM_ADMIN)
					
				{
					//-----------------------------------------------------------------------
					//System Administrator or Instructor or Teaching Assistant 
					// Can be altered upon code request, or through system admin control panel
					//-----------------------------------------------------------------------
				if(!PlugInUtil.authorizeForCourseControlPanelContent(request, response))
				return;						

	%>
			
			
			<div id="actionbar" class="actionBar clearfix editmode" align="right">
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
							if(persistentScheduleName!=null && persistentScheduleName.length()>0 && 
									!persistentScheduleName.equals(schedules.get(i).getSchedule_Name())) continue;						
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
					<td><a href="<%=scheduleurls[i]%>" target="_blank">
							Test assessment
						</a>
					</td>
					<td><input type="checkbox" name="switchBox"
						onClick="showhideScheduleURL(this,'<%=idStr%>')" /></td>
					<!--<td><%=schedules.get(i).getGroup_ID()%></td>-->
				</tr>
				<tr id='<%=idStr%>' style="display: none;">
					<td><i>URL:</i></td>
					<td colspan="6"><code><%=basePath+"links/main.jsp?course_id="+courseId+"&amp;schedule_name="+URLEncoder.encode(schedules.get(i).getSchedule_Name())%></code></td>
				</tr>
	<%		 			}
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
					
					if ((persistentScheduleName == null || persistentScheduleName.length()==0) && 
						courseSettings.getProperty("hide_schedules","0").equals("1")) {
						persistentScheduleName="HIDE_ALL_SCHEDULES";
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
						
						if(persistentScheduleName!=null && persistentScheduleName.length()>0 && 
								!persistentScheduleName.equals(schedules.get(i).getSchedule_Name())) continue;						
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
			
	<%		 		if(schedulesactive[i]) {  if(scheduleurls[i] == null) { %>
			 			No attempts remaining 
	<%		 		} else 
					{ 
	%>
						<form><input type="button" value="Take assessment"
							onclick="window.open('<%=scheduleurls[i]%>');">
						</form>
	<%		 		}
	%> 
	<%		 		} else { 
	%>					 inactive 
	<%		 		} 
	%>
						</td>
						<!--<td><%=schedules.get(i).getGroup_ID()%></td>-->
					</tr>
	<%		 		}
			
	%>
				</table>
	<%
				} //end of Student view if-else statement				

	%>			
			
	</bbUI:docTemplateBody>
</bbData:context>	
