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
		blackboard.platform.plugin.*,
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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<bbData:context id="ctx">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>Questionmark Perception connector</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="questionmark perception,questionmark,perception,assessment,connector">
		<meta http-equiv="description" content="Questionmark Perception connector for Blackboard">
		<!--
			<link rel="stylesheet" type="text/css" href="styles.css">
		-->
	</head>

	<body>
		<bbUI:docTemplate>
			<%

			// Retrieve the course identifier from the form
			String courseId = request.getParameter("course_id");

			try {
				CourseSettings courseSettings = new CourseSettings(courseId);
	
				Boolean hideSchedules = request.getParameter("hide_schedules") != null;
				
				courseSettings.setProperty("hide_schedules",hideSchedules?"1":"0");
				
				courseSettings.saveSettingsFile();
			
			} catch(PlugInException e) {
				%>
				<bbUI:receipt type="FAIL" title="Error saving course settings">
					<%=e.getMessage()%>
				</bbUI:receipt>
				<%
				return;
			}

			String recallurl = "links/main.jsp?course_id=" + request.getParameter("course_id");
			%>

			<bbUI:receipt type="SUCCESS" title="Success" recallUrl="<%=recallurl%>">
				Course settings were successfully saved
			</bbUI:receipt>

		</bbUI:docTemplate>
	</body>
</html>
</bbData:context>

