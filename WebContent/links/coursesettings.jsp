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


<bbData:context id="ctx">

		<bbUI:docTemplate>
		
			<bbUI:titleBar iconUrl='<%=path+"/images/qm.gif"%>' >
				Questionmark Perception connector
			</bbUI:titleBar>

			<bbUI:breadcrumbBar environment="COURSE" isContent="false">
				<bbUI:breadcrumb>COURSE SETTINGS</bbUI:breadcrumb>
			</bbUI:breadcrumbBar>		
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

			String recallurl = path+"/links/main.jsp?course_id=" + request.getParameter("course_id");
			%>

			<bbUI:receipt type="SUCCESS" title="Success" recallUrl="<%=recallurl%>">
				Course settings were successfully saved
			</bbUI:receipt>

		</bbUI:docTemplate>

</bbData:context>

