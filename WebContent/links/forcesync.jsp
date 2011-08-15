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
		org.apache.commons.lang.StringEscapeUtils,
		java.rmi.RemoteException,
		javax.xml.namespace.QName,
		com.questionmark.*,
		com.questionmark.QMWISe.*
	"
%>

<%@ taglib uri="/bbData" prefix="bbData" %>
<%@ taglib uri="/bbUI" prefix="bbUI" %>

<bbData:context id="ctx">
	<bbUI:docTemplate title="Forced Synchronization">
	<%
	QMPCourseContext qbc=new QMPCourseContext(request,ctx);
	String result = qbc.ForceSynchronization();
	%>
	<%
		if (qbc.failTitle == null) {
	%>
	<bbUI:receipt type="SUCCESS" title="Users synchronized with Perception">
		<h2>Synchronization Details</h2>
		<pre><%=StringEscapeUtils.escapeHtml(result) %></pre>
		<p>&nbsp;</p>
	</bbUI:receipt>

	<%	} else {
	%>

	<bbUI:receipt type="FAIL" title="<%=qbc.failTitle %>">
		<%=qbc.failMsg %>
	</bbUI:receipt>

	<%
		} //End of other view
	%>
	</bbUI:docTemplate>
</bbData:context>

