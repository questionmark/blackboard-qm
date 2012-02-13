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
		com.questionmark.QMWISe.*,
		org.apache.commons.lang.StringEscapeUtils"
%>

<%@ taglib uri="/bbUI" prefix="bbUI" %>
<%@ taglib uri="/bbData" prefix="bbData" %>

<bbData:context id="ctx">
	<bbUI:docTemplate title="Questionmark Perception Schedule Created">
	<%
	QMPContentCreator cc=new QMPContentCreator(request,ctx,response);
	if (cc.failTitle==null)
		cc.ProcessQuickForm();
	%>
	<%
		if (cc.failTitle == null) {
			String okUrl = "main.jsp?course_id="+cc.courseId; %>

	<bbUI:receipt type="SUCCESS" title="Schedule Created" recallUrl="<%= StringEscapeUtils.escapeHtml(okUrl) %>">
		<p>The schedule <i><%=cc.contentItem.name %></i> was successfully created</p>
	</bbUI:receipt>

	<%	} else {
	%>

	<bbUI:receipt type="FAIL" title="<%=cc.failTitle %>">
		<%=cc.failMsg %>
	</bbUI:receipt>

	<%
		} //End of error view
	%>
	</bbUI:docTemplate>
</bbData:context>

