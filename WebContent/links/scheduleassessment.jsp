<%@ page
	language="java"
	pageEncoding="UTF-8"
	import="
		java.util.*,
		java.text.*,
		blackboard.platform.*,
		blackboard.base.*,
		blackboard.platform.persistence.PersistenceServiceFactory,
		blackboard.platform.session.*,
		blackboard.data.user.*,
		blackboard.persist.* ,
		blackboard.persist.user.*,
		blackboard.data.course.*,
		blackboard.data.gradebook.*,
		blackboard.persist.course.*,
		blackboard.persist.gradebook.*,
		blackboard.servlet.util.DatePickerUtil,		
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


<%@page import="org.apache.log4j.helpers.SyslogWriter"%><bbData:context id="ctx">
	<bbUI:docTemplateHead title="Questionmark Perception connector" />	
	<bbUI:docTemplate>
		
		<%
		QMWise qmwise;
		int groupId;
		Boolean perParticipant, limitAttempts, setAccessPeriod;
		String useGradebook, gradeResultType;
		
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

		// read the value of the "store results in gradebook" select menu
		useGradebook = request.getParameter("use_gradebook");
		
		// read in the value of the "Select result to display in Grade Center" select
		// menu		
		gradeResultType = request.getParameter("result_type");

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
				startCal = DatePickerUtil.pickerDatetimeStrToCal(request.getParameter("scheduleStart_datetime"));
				endCal = DatePickerUtil.pickerDatetimeStrToCal(request.getParameter("scheduleEnd_datetime"));
				if (endCal.before(startCal) || endCal.equals(startCal))
					throw new Exception("The end date must be after the start date");
			}
			schedule.updateSchedule_Starts_fromCalendar(startCal);
			schedule.updateSchedule_Stops_fromCalendar(endCal);

			schedule.setGroup_ID(groupId);
			schedule.setGroup_Tree_ID(groupId); //required. 0 is not accepted so needs to be the same as group id
			schedule.setWeb_Delivery(true); //required, otherwise the test is not takable via the web
		
		} catch(Exception e) {
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
		if(!useGradebook.equals("no")) try {
			//Retrieve the Db persistence manager from the persistence service
			BbPersistenceManager bbPm = PersistenceServiceFactory.getInstance().getDbPersistenceManager();

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

			LineitemDbPersister lineitemdbpersister = 
				(LineitemDbPersister) bbPm.getPersister(LineitemDbPersister.TYPE);
			
			Lineitem lineitem = new Lineitem();			
			lineitem.setName(request.getParameter("schedule"));
			lineitem.setCourseId(course.getId());
			lineitem.setIsAvailable(true);
			
			// Feed in the chosen grade type from the chosen option
			lineitem.setType("QM Assessment Grade: " + gradeResultType);
			lineitem.validate();			
			if (useGradebook.equals("percent")) {
				lineitem.setPointsPossible(100f);
			}			
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

		<bbUI:receipt  iconUrl='<%=path+"/images/qm.gif"%>' type="SUCCESS" title="Schedule Creation Successful" recallUrl="<%=recallurl%>">
			<p>The schedule was successfully created</p>
		</bbUI:receipt>

	</bbUI:docTemplate>
</bbData:context>

