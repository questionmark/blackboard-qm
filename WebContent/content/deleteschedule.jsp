<!-- 
 Filename		: content/deleteschedule.jsp
 Description	: Runs when deleting a schedule.

-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	import="java.util.*,
	java.text.*,
	blackboard.platform.*,
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
	
    
<%@ taglib uri="/bbUI" prefix="bbUI" %>
<%@ taglib uri="/bbData" prefix="bbData" %>

<bbData:context id="ctx">
	<bbUI:docTemplate title="Questionmark Perception Schedule">
	<%
	QMPContentCreator cc=new QMPContentCreator(request,ctx,response);
	String result=cc.ProcessDelete();
	%>
	<%
		if (cc.failTitle == null) {
			String okUrl = "/webapps/blackboard/content/listContentEditable.jsp?content_id="
				+ cc.contentItem.parentId.getExternalString()
				+ "&course_id="
				+ cc.courseId
				+ "&mode=quick";	%>

	<bbUI:receipt type="SUCCESS" title="Schedule Deleted" recallUrl="<%= StringEscapeUtils.escapeHtml(okUrl) %>">
		<p>The schedule <i><%=cc.contentItem.name %></i> was successfully updated</p>
		<%	if (result.length()>0) {
			%>
			<h2>Warnings:</h2>
			<pre><%=StringEscapeUtils.escapeHtml(result) %></pre>
			<p>&nbsp;</p>
			<%
		}
		%>
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


