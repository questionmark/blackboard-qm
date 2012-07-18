<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<%@ taglib uri="/bbNG" prefix="bbNG"%>

<bbNG:learningSystemPage ctxId="ctx"
	title="Questionmark Perception Connector Settings">
	<%
	QMPCourseContext qbc=new QMPCourseContext(request,ctx);
	if (qbc.failTitle == null)
		qbc.UpdateSettings(request);
	%>
	<bbNG:pageHeader>
		<bbNG:pageTitleBar iconUrl='<%=qbc.path+"/images/qm.gif"%>'
			title="Course Settings" />
	</bbNG:pageHeader>

	<%
		if (qbc.failTitle == null) {
			String recallurl = "main.jsp?course_id=" + qbc.courseId;
	%>

	<bbNG:receipt type="SUCCESS" title="Settings Saved" recallUrl="<%=recallurl%>">
		Your settings have been saved.
	</bbNG:receipt>

	<%	} else {
	%>

	<bbNG:receipt type="FAIL" title="<%=qbc.failTitle %>">
		<%=qbc.failMsg %>
	</bbNG:receipt>

	<%
		} //End of error view
	%>
</bbNG:learningSystemPage>
