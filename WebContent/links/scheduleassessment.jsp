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
		blackboard.data.gradebook.*,
		blackboard.persist.course.*,
		blackboard.persist.gradebook.*,
		org.apache.axis.*,
		java.rmi.RemoteException,
		javax.xml.namespace.QName,
		com.questionmark.*,
		com.questionmark.QMWISe.*"
%>

<%@ taglib uri="/bbUI" prefix="bbUI" %>
<%@ taglib uri="/bbData" prefix="bbData" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<bbData:context id="ctx">

		<bbUI:docTemplate>
		
			<bbUI:titleBar iconUrl='<%=path+"/images/qm.gif"%>' >
				Questionmark Perception connector
			</bbUI:titleBar>
	
			<bbUI:breadcrumbBar environment="COURSE" isContent="false">
				<bbUI:breadcrumb>SCHEDULE CREATED</bbUI:breadcrumb>
			</bbUI:breadcrumbBar>			
		
		
			<%
			QMWise qmwise;
			int groupId;
			Boolean perParticipant, limitAttempts, setAccessPeriod, useGradebook;

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

			// get the group ID, using the supplied group name
			try {
				groupId = new Integer(qmwise.getStub().getGroupByName(request.getParameter("group")).getGroup_ID()).intValue();
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
				<bbUI:receipt type="FAIL" title="Error getting group ID">
					<%=qe.getMessage()%>
				</bbUI:receipt>
				<%
				return;
			}

			// check whether the "separate schedule for each user" box has been ticked
			perParticipant = request.getParameter("per_participant") != null || request.getParameter("per_participant_hidden").equals("1");

			// check whether the "separate schedule for each user" box has been ticked
			limitAttempts = request.getParameter("limit_attempts") != null;

			// check whether the "separate schedule for each user" box has been ticked
			setAccessPeriod = request.getParameter("set_access_period") != null;

			// check whether the "store results in gradebook" box has been ticked
			useGradebook = request.getParameter("use_gradebook") != null;

			// create a "Schedule" object for the current user, from the data provided
			ScheduleV42 schedule = new ScheduleV42();
			try {
				schedule.setSchedule_Name(request.getParameter("schedule"));
				schedule.setAssessment_ID(request.getParameter("assessment"));

				schedule.setRestrict_Attempts(limitAttempts);
				if(limitAttempts) {
					if(!request.getParameter("limit").matches("[1-9][0-9]*"))
						throw new Exception("Attempt limit must be a positive integer");
					schedule.setMax_Attempts(new Integer(request.getParameter("limit")).intValue());
				}

				schedule.setRestrict_Times(setAccessPeriod);

				Calendar startCal = Calendar.getInstance();
				Calendar endCal = Calendar.getInstance();
				if(setAccessPeriod) {
					String regexhour = "[0-1][0-9]|2[0-3]";
					String regexmin = "[0-5][0-9]";
					//check times make sense
					if(!(request.getParameter("start_hour").matches(regexhour) && request.getParameter("start_minute").matches(regexmin) && request.getParameter("end_hour").matches(regexhour) && request.getParameter("end_minute").matches(regexmin)))
						throw new Exception("Times must be in 24-hour HH:MM format");

					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

					startCal.setTime(df.parse(request.getParameter("start_0").substring(0, 10)));
					startCal.set(Calendar.HOUR_OF_DAY, new Integer(request.getParameter("start_hour")).intValue());
					startCal.set(Calendar.MINUTE, new Integer(request.getParameter("start_minute")).intValue());

					endCal.setTime(df.parse(request.getParameter("end_1").substring(0, 10)));
					endCal.set(Calendar.HOUR_OF_DAY, new Integer(request.getParameter("end_hour")).intValue());
					endCal.set(Calendar.MINUTE, new Integer(request.getParameter("end_minute")).intValue());

					if(endCal.before(startCal) || endCal.equals(startCal))
						throw new Exception("The end date must be after the start date");
				}
				schedule.setSchedule_Starts(startCal);
				schedule.setSchedule_Stops(endCal);

				schedule.setGroup_ID(groupId);
				schedule.setGroup_Tree_ID(groupId); //required. 0 is not accepted so needs to be the same as group id
				schedule.setWeb_Delivery(true); //required, otherwise the test is not takable via the web
			
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
				<bbUI:receipt type="FAIL" title="Error setting schedule parameters">
					<%="Message: " + qe.getMessage() + "Fault Reason: " + qe.getFaultReason()%>
				</bbUI:receipt>
				<%
				return;
			}

			// createScheduleParticipantV42() this schedule
			try {
				String[] scheduleids = qmwise.getStub().createScheduleGroupV42(schedule, perParticipant);
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
				<bbUI:receipt type="FAIL" title="Error creating group schedule">
					<%=qe.getMessage()%>
				</bbUI:receipt>
				<%
				return;
			}

			// if required, add a gradebook listitem
			if(useGradebook) try {
				//Retrieve the Db persistence manager from the persistence service
				BbPersistenceManager bbPm = BbServiceManager.getPersistenceService().getDbPersistenceManager();

				//load course by short course name to get its Blackboard ID
				CourseDbLoader courseLoader = (CourseDbLoader) bbPm.getLoader(CourseDbLoader.TYPE);
				Course course;
				try {
					course = courseLoader.loadByBatchUid(((String) request.getParameter("group")));
				} catch(KeyNotFoundException e) {
					%>
					<bbUI:receipt type="FAIL" title="Error getting Blackboard course details">
						<%=e.getMessage()%>
					</bbUI:receipt>
					<%
					return;
				}

				LineitemDbPersister lineitemdbpersister = (LineitemDbPersister) bbPm.getPersister(LineitemDbPersister.TYPE);
				Lineitem lineitem = new Lineitem();
				lineitem.setName(request.getParameter("schedule"));
				lineitem.setCourseId(course.getId());
				lineitem.setIsAvailable(true);
				lineitem.setType("Questionmark Perception assessment");
				lineitem.validate();

				lineitemdbpersister.persist(lineitem);
			} catch(Exception e) {
				QMWiseException qe = new QMWiseException(e);
				%>
				<bbUI:receipt type="FAIL" title="Error creating gradebook Lineitem">
					<%=qe.getMessage()%>
				</bbUI:receipt>
				<%
				return;
			}

			String recallurl = "main.jsp?course_id=" + request.getParameter("course_id") + "#Schedules";
			%>

			<bbUI:receipt type="SUCCESS" title="Success" recallUrl="<%=recallurl%>">
				The schedule was successfully created
			</bbUI:receipt>

		</bbUI:docTemplate>

</bbData:context>

