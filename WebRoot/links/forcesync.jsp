<%@ page
	language="java"
	pageEncoding="UTF-8"
	import="
		java.util.*,
		blackboard.platform.*,
		blackboard.base.*,
		blackboard.platform.session.*,
		blackboard.data.user.*,
		blackboard.persist.* ,
		blackboard.persist.user.*,
		blackboard.data.course.*,
		blackboard.persist.course.*,
		org.apache.axis.*,
		java.rmi.RemoteException,
		javax.xml.namespace.QName,
		com.questionmark.*,
		com.questionmark.QMWISe.*
	"
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
				// Retrieve the course identifier from the URL
				String courseId = request.getParameter("course_id");

				//create a ConfigFileReader to save synchronization date
				ConfigFileReader configReader = new ConfigFileReader(courseId);

				//synchronize course users
				System.out.println("Perception: course " + courseId + ": user synchronization forced");
				UserSynchronizer us = new UserSynchronizer();
				String result;
				try {
					result = us.synchronizeCourse(courseId);
					configReader.setCourseSyncDate();
				} catch (Exception e) {
					System.out.println("Perception: course " + courseId + ": synchronization failed: " + e.getMessage());
					%>
					<bbUI:receipt type="FAIL" title="Error synchronizing course users with Perception">
						<%=e.getMessage()%>
					</bbUI:receipt>
					<%
					return;
				}
			%>
			<bbUI:receipt type="SUCCESS" title="Users synchronized with Perception">
				<%=result%>
			</bbUI:receipt>
		</bbUI:docTemplate>
	</body>
</html>
</bbData:context>
