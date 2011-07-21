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

<%@ taglib uri="/bbUI" prefix="bbUI" %> 
<%@ taglib uri="/bbData" prefix="bbData" %>
<%@ taglib uri="/bbNG" prefix="bbNG" %>

<bbNG:learningSystemPage ctxId="ctx"
	title="Questionmark Perception Synchronization">
	<%
	QMPCourseContext qbc=new QMPCourseContext(request,ctx);
	String result = qbc.ForceSynchronization();
	%>
	<bbNG:pageHeader>
		<bbNG:pageTitleBar iconUrl='<%=qbc.path+"/images/qm.gif"%>'
			title="Forced Synchronization" />
	</bbNG:pageHeader>	

	<%
		if (qbc.failTitle == null) {
	%>
	<bbNG:receipt type="SUCCESS" title="Users synchronized with Perception">
		<h2>Synchronization Details</h2>
		<pre><%=StringEscapeUtils.escapeHtml(result) %></pre>
		<p>&nbsp;</p>
	</bbNG:receipt>

	<%	} else {
	%>

	<bbNG:receipt type="FAIL" title="<%=StringEscapeUtils.escapeHtml(qbc.failTitle) %>">
		<%=StringEscapeUtils.escapeHtml(qbc.failText) %>
	</bbNG:receipt>

	<%
		} //End of other view
	%>
	
</bbNG:learningSystemPage>


