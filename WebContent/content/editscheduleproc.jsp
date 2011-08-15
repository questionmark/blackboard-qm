<!-- 
 Filename		: content/editscheduleproc.jsp
 Description	: Runs when the user submits changes in editschedule form.

-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	import="java.util.*,
	java.text.*,
	blackboard.platform.*,
	blackboard.platform.context.Context,
	blackboard.platform.session.*,
	blackboard.platform.persistence.*,
	blackboard.platform.plugin.*,
	blackboard.platform.security.authentication.*,
	blackboard.persist.*,
	blackboard.persist.user.*,
	blackboard.persist.course.*,
	blackboard.persist.content.*,
	blackboard.persist.content.ContentDbLoader,
	blackboard.persist.Id,blackboard.data.course.*,
	blackboard.data.user.*,
	blackboard.data.content.*,
	blackboard.data.ValidationException,
	blackboard.base.*,
	blackboard.data.gradebook.*,
	blackboard.persist.gradebook.*,
	org.apache.axis.*,
	org.apache.commons.lang.StringEscapeUtils,
	java.rmi.RemoteException,
	javax.xml.namespace.QName,
	com.questionmark.*,
	com.questionmark.QMWISe.*"
	pageEncoding="ISO-8859-1"
%>

<%@ taglib uri="/bbData" prefix="bbData" %>
<%@ taglib uri="/bbUI" prefix="bbUI" %>

<bbData:context id="ctx">
	<bbUI:docTemplate title="Questionmark Perception Schedule Updated">
	<%
	QMPContentCreator cc=new QMPContentCreator(request,ctx,response);
	if (cc.failTitle==null)
		cc.ProcessEditForm();
	%>
	<%
		if (cc.failTitle == null) {
			String okUrl = "/webapps/blackboard/content/listContent.jsp?content_id="
				+ cc.contentItem.parentId.getExternalString()
				+ "&course_id="
				+ cc.courseId
				+ "&mode=reset";	%>

	<bbUI:receipt type="SUCCESS" title="Schedule Updated" recallUrl="<%= StringEscapeUtils.escapeHtml(okUrl) %>">
		<p>The schedule <i><%=cc.contentItem.name %></i> was successfully updated</p>
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



