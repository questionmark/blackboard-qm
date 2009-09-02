<%@ page
	language="java"
	import="
		java.util.*,
		blackboard.platform.*,
		blackboard.platform.session.*,
		blackboard.data.user.*,
		blackboard.persist.* ,
		blackboard.persist.user.*,
		blackboard.data.course.*,
		blackboard.persist.course.*,
		com.questionmark.*
	"
	errorPage="/error.jsp"
	pageEncoding="UTF-8"
%>

<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ taglib uri="/bbData" prefix="bbData"%>

<bbData:context>
	<bbUI:docTemplate title ="Custom My Courses Module">
		<bbUI:breadcrumbBar></bbUI:breadcrumbBar>
		<bbUI:titleBar iconUrl="/images/ci/icons/tools_u.gif">
			Configure Blackboard Admin Tools Properties
		</bbUI:titleBar>
		<jsp:useBean id="PropertiesManager" scope="session" class="com.questionmark.PropertiesBean"/>
		<%
			/*
			//Retrieve the Db persistence manager from the persistence service
			BbPersistenceManager bbPm = BbServiceManager.getPersistenceService().getDbPersistenceManager();
			// Retrieve the course identifier from the URL
			String courseId = request.getParameter("course_id");
			// Generate a persistence framework course Id to be used for loading the course
			Id sessionCourseId = bbPm.generateId(Course.COURSE_DATA_TYPE, courseId);
			// Load the course and get the ID
			CourseDbLoader courseLoader = (CourseDbLoader)bbPm.getLoader(CourseDbLoader.TYPE);
			Course sessionCourse = courseLoader.loadById(sessionCourseId);
			String courseTitle = sessionCourse.getTitle();

			out.println("Course ID: "+courseId+".<br>Course title: "+courseTitle+".<br>");
			*/

			try {
				PropertiesManager.setProperties(request);
			} catch(Exception e) {
				%>
				<bbUI:receipt type="FAIL" title="Error saving settings" recallUrl="admin/config.jsp">
					<%=e.getMessage()%>
				</bbUI:receipt>
				<%
				return;
			}
			QMWise.reset();
		%>
		<bbUI:receipt type="SUCCESS" recallUrl="test_perception.jsp">
			Your settings have been saved. Click OK to test the connection.
		</bbUI:receipt>
	</bbUI:docTemplate>
</bbData:context>
