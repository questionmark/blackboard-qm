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

<%@ taglib uri="/bbData" prefix="bbData"%>
<%@ taglib uri="/bbUI" prefix="bbUI"%>

<bbData:context id="ctx">
	<bbUI:docTemplate title="Course Settings">
	<%
	QMPCourseContext qbc=new QMPCourseContext(request,ctx);
	if (qbc.failTitle == null)
		qbc.UpdateSettings(request);
	%>
	<%
		if (qbc.failTitle == null) {
			String recallurl = "main.jsp?course_id=" + qbc.courseId;
	%>

	<bbUI:receipt type="SUCCESS" title="Settings Saved" recallUrl="<%=recallurl%>">
		Your settings have been saved.
	</bbUI:receipt>

	<%	} else {
	%>

	<bbUI:receipt type="FAIL" title="<%=qbc.failTitle %>">
		<%=qbc.failMsg %>
	</bbUI:receipt>

	<%
		} //End of error view
	%>
	</bbUI:docTemplate>
</bbData:context>


