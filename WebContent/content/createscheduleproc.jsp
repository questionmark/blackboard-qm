<!-- 
////////////////////////////

// Filename		: content/createscheduleproc.jsp
// Description	: 'createproc' for createscheduleform.jsp, does the content 
//				  item object initialisation and creation of schedule
				  attached to the content item for use by students and staff.
////////////////////////////
-->

<%@ page
	language="java"
	pageEncoding="UTF-8"
	import="java.util.*,
	java.text.*,
	blackboard.platform.*,
	blackboard.platform.session.*,
	blackboard.platform.persistence.*,
	blackboard.platform.plugin.*,
	blackboard.platform.security.authentication.*,
	blackboard.persist.*,blackboard.persist.user.*,
	blackboard.persist.course.*,blackboard.persist.content.*,
	blackboard.persist.content.ContentDbLoader,
	blackboard.persist.Id,blackboard.data.course.*,
	blackboard.data.user.*,
	blackboard.data.content.*,
	blackboard.base.*,
	blackboard.data.gradebook.*,
	blackboard.persist.gradebook.*,
	org.apache.axis.*,
	java.rmi.RemoteException,
	javax.xml.namespace.QName,com.questionmark.*,
	com.questionmark.QMWISe.*,
	blackboard.base.FormattedText.Type,
	org.apache.commons.lang.StringEscapeUtils"
%>

<%@ taglib uri="/bbUI" prefix="bbUI" %>
<%@ taglib uri="/bbData" prefix="bbData" %>
<%@ taglib uri="/bbNG" prefix="bbNG" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//Authenticate for use.	
	if (!PlugInUtil.authorizeForCourseControlPanel(request, response)) {
		//If user is not authorised to see this page then return blank page
		%>	
			<h1>You are not authorised to view this page</h1>			
		<%
		//Stop the script
		return;
	}

	String course_id = request.getParameter("course_id");
	String parent_id = request.getParameter("parent_id"); //  id of the parent folder
	String schedule_name = request.getParameter("schedule");
	//FormattedText ftext = request.getParameter("
	String schedule_description = request.getParameter("schedule_text_area");
	
	if (schedule_description.length() > 4000){
	%>
		<bbUI:receipt type="FAIL" title="Schedule description is too long!" buttonAlt="Ok" >
			 Cannot exceed more than 4000 characters. Please click ok to try again
		</bbUI:receipt>
	<%
	
	}
		
	//out.println("description string is: " + schedule_description);

	//Retrieve the Db persistence manager from the persistence service
	BbPersistenceManager bbPm = PersistenceServiceFactory.getInstance()
			.getDbPersistenceManager();

	//load course by short course name to get its Blackboard ID
	CourseDbLoader courseLoader = (CourseDbLoader) bbPm
			.getLoader(CourseDbLoader.TYPE);
	Course course = null;
	User user = null;
	
%>


<bbData:context id="ctx">

	
	<bbUI:breadcrumbBar environment="COURSE" handle="control_panel" isContent="true">
	
		<bbUI:breadcrumb>QUESTIONMARK PERCEPTION SCHEDULE CREATED</bbUI:breadcrumb>
	
	</bbUI:breadcrumbBar>	


		<bbUI:docTemplate>
			<%
				QMWise qmwise;
						int groupId;
						Boolean perParticipant, limitAttempts, setAccessPeriod;
						
						String useGradebook, gradeResultType;

						try {
							qmwise = new QMWise();
						} catch (Exception e) {
							QMWiseException qe = new QMWiseException(e);
			%>
				<bbUI:receipt type="FAIL" title="Error connecting to Perception server">
					<%=qe.getMessage()%>
				</bbUI:receipt>
				<%
					return;
							}

							// get the group ID, using the supplied group name
							try {
								groupId = new Integer(qmwise.getStub().getGroupByName(
										request.getParameter("group")).getGroup_ID())
										.intValue();
							} catch (Exception e) {
								QMWiseException qe = new QMWiseException(e);
				%>
				<bbUI:receipt type="FAIL" title="Error getting group ID">
					<%=qe.getMessage()%>
				</bbUI:receipt>
				<%
					return;
							}

							// check whether the "separate schedule for each user" box has been ticked
							perParticipant = request.getParameter("per_participant") != null
									|| request.getParameter("per_participant_hidden")
											.equals("1");

							// check whether the "separate schedule for each user" box has been ticked
							limitAttempts = request.getParameter("limit_attempts") != null;

							// check whether the "separate schedule for each user" box has been ticked
							setAccessPeriod = request.getParameter("set_access_period") != null;

							// read the value of the "store results in gradebook" select menu
							useGradebook = request.getParameter("use_gradebook");
							
							// read in the value of the "Select result to display in Grade Center" select
							// menu		
							gradeResultType = request.getParameter("result_type");
							
							// create a "Schedule" object for the current user, from the data provided
							ScheduleV42 schedule = new ScheduleV42();
							try {
								schedule.setSchedule_Name(schedule_name);
								
								schedule.setAssessment_ID(request
										.getParameter("assessment"));

								schedule.setRestrict_Attempts(limitAttempts);
								if (limitAttempts) {
									if (!request.getParameter("limit").matches(
											"[1-9][0-9]*"))
										throw new Exception(
												"Attempt limit must be a positive integer");
									schedule.setMax_Attempts(new Integer(request
											.getParameter("limit")).intValue());
								}

								schedule.setRestrict_Times(setAccessPeriod);

								Calendar startCal = Calendar.getInstance();
								Calendar endCal = Calendar.getInstance();
								if (setAccessPeriod) {
									String regexhour = "[0-1][0-9]|2[0-3]";
									String regexmin = "[0-5][0-9]";
									//check times make sense
									if (!(request.getParameter("start_hour").matches(
											regexhour)
											&& request.getParameter("start_minute")
													.matches(regexmin)
											&& request.getParameter("end_hour")
													.matches(regexhour) && request
											.getParameter("end_minute").matches(
													regexmin)))
										throw new Exception(
												"Times must be in 24-hour HH:MM format");

									DateFormat df = new SimpleDateFormat(
											"yyyy-MM-dd' 0:0:00'");

									startCal.setTime(df.parse(request
											.getParameter("start_0")));
									startCal.set(Calendar.HOUR_OF_DAY, new Integer(
											request.getParameter("start_hour"))
											.intValue());
									startCal.set(Calendar.MINUTE, new Integer(request
											.getParameter("start_minute")).intValue());

									endCal.setTime(df.parse(request
											.getParameter("end_1")));
									endCal.set(Calendar.HOUR_OF_DAY, new Integer(
											request.getParameter("end_hour"))
											.intValue());
									endCal.set(Calendar.MINUTE, new Integer(request
											.getParameter("end_minute")).intValue());

									if (endCal.before(startCal)
											|| endCal.equals(startCal))
										throw new Exception(
												"The end date must be after the start date");
								}
								schedule.updateSchedule_Starts_fromCalendar(startCal);
								schedule.updateSchedule_Stops_fromCalendar(endCal);

								schedule.setGroup_ID(groupId);
								schedule.setGroup_Tree_ID(groupId); //required. 0 is not accepted so needs to be the same as group id
								schedule.setWeb_Delivery(true); //required, otherwise the test is not takable via the web

							} catch (Exception e) {
								QMWiseException qe = new QMWiseException(e);
				%>
				<bbUI:receipt type="FAIL" title="Error setting schedule parameters">
					<%=qe.getMessage()%>
				</bbUI:receipt>
				<%
					return;
							}

							// createScheduleParticipantV42() this schedule
							try {
								String[] scheduleids = qmwise.getStub()
										.createScheduleGroupV42(schedule,
												perParticipant);
							} catch (Exception e) {
								QMWiseException qe = new QMWiseException(e);
				%>
				<bbUI:receipt type="FAIL" title="Error creating group schedule">
					<em>QMWISe Error:</em><br />
					<%=qe.getMessage()%>
				</bbUI:receipt>
		<%
			return;
					}

					// if required, add a gradebook listitem
					if (!useGradebook.equals("no"))
						try {

							try {
								course = courseLoader.loadByBatchUid(((String) request.getParameter("group")));
							} catch (KeyNotFoundException e) {
								%>
									<bbNG:receipt type="FAIL"
										title="Error getting Blackboard course details">
										<%=e.getMessage()%>
									</bbNG:receipt>
								<%									
							}

							LineitemDbPersister lineitemdbpersister = (LineitemDbPersister) bbPm
									.getPersister(LineitemDbPersister.TYPE);
							Lineitem lineitem = new Lineitem();
							lineitem.setName(schedule_name);
							lineitem.setCourseId(course.getId());
							lineitem.setIsAvailable(true);							
							lineitem.setType("QM Assessment Grade: " + gradeResultType);
							lineitem.validate();
							if (useGradebook.equals("percent")) {
								lineitem.setPointsPossible(100f);
							}
							lineitemdbpersister.persist(lineitem);
						} catch (PersistenceException pe) {

							System.out
									.println("Persistence Exception Message: "
											+ pe.getStackTrace());

						} catch (Exception e) {
							System.out.println(e.getStackTrace());
		%>
		<bbUI:receipt type="FAIL" title="Error creating gradebook Lineitem">
			<%=e.getMessage()%>
		</bbUI:receipt>
		<%
			return;
						}

					//save created schedule as a content item!!

					try {

						//get the parent and course objects...
						Id parentId = bbPm.generateId(CourseDocument.DATA_TYPE,parent_id);
						if (parentId == null){
							out.println("Stop here parent id is null, parent_id is"	+ parent_id);
							return;
						}
							

						Id courseId = bbPm.generateId(CourseDocument.DATA_TYPE,
								course_id);
						//prepare a ContentDbLoader
						ContentDbLoader contentLoader = ContentDbLoader.Default
								.getInstance();

						//may need to work out child items on page and set the child order for the coursedoc item.

						//Initialise content item as CourseDocument
						CourseDocument courseDoc = new CourseDocument();

						//title of the content item tied in with the Questionmark Created Scheduled name...
						courseDoc.setTitle(schedule_name);

						//set description of content item, in this case, perception schedule..
						//Escape any html that the user types. Only allowing plaintext.
						FormattedText text = new FormattedText(StringEscapeUtils.escapeHtml(schedule_description), FormattedText.Type.DEFAULT);						
												
						courseDoc.setBody(text);

						//made it unconditionally available to all, can change depending on user story.
						courseDoc.setIsAvailable(true);

						//set content resource type(Set content handler)

						courseDoc.setContentHandler("qm/schedule-link"); //NB Must match the entry in bb-manifest!!

						//set parent id
						courseDoc.setParentId(parentId);

						//set course id
						courseDoc.setCourseId(courseId); //get the course id from the context of create page.

						//here we have the option to set the order in which the child appears
						//courseDoc.setPosition(numberOfChildren);

						//Now to try and save our content item using the ContentDbPersister
						//first get the persister object instance
						ContentDbPersister persister = ContentDbPersister.Default
								.getInstance();

						//now to persist!
						//save details of this item.
						persister.persist(courseDoc);

						//out.println("<p>" + user.getGivenName() + "," + "\n"); 
						//out.println("content creation was a success </p>");

						user = ctx.getUser();

						//deal with OK click action	
						String okUrl = "/webapps/blackboard/content/listContentEditable.jsp?content_id="
								+ parent_id
								+ "&amp;course_id="
								+ course_id
								+ "&amp;mode=quick";
		%>

		<bbUI:receipt  iconUrl='<%=path+"/images/qm.gif"%>' type="SUCCESS" title="Schedule Creation Successful" recallUrl="<%=okUrl%>">
			<p>The schedule was successfully created</p>
		</bbUI:receipt>
		<%
					}

							catch (PersistenceException pE) {
				%>
				<bbUI:receipt type="FAIL" title="Content item creation unsuccessful" recallUrl="" buttonName="failOk" buttonAlt="OK">
					Sorry but there was a problem creating this content item, see below:<br>
					<%
						out.println("Persistence Exception caught, Message: "
														+ pE.getMessage());
					%>
				</bbUI:receipt>
				<%
					}
				%>						
		
		</bbUI:docTemplate>

</bbData:context>

