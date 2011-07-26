<%@ page language="java" pageEncoding="UTF-8"
	import="
		java.util.*,
		java.text.*,
		java.net.*,
		blackboard.platform.*,
		blackboard.base.*,
		blackboard.platform.session.*,
		blackboard.data.user.*,
		blackboard.platform.persistence.PersistenceServiceFactory,
		blackboard.persist.* ,
		blackboard.persist.user.*,
		blackboard.data.course.*,
		blackboard.persist.course.*,
		org.apache.axis.*,
		org.apache.commons.lang.StringEscapeUtils,
		java.rmi.RemoteException,
		javax.xml.namespace.QName,
		com.questionmark.*,
		com.questionmark.QMWISe.*"
%>

<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ taglib uri="/bbData" prefix="bbData"%>
<%@ taglib uri="/bbNG" prefix="bbNG"%>

<%@page import="com.sun.mail.handlers.text_html"%>
<%@page import="org.apache.velocity.app.event.implement.EscapeHtmlReference"%>

<bbNG:learningSystemPage ctxId="ctx"
	title="Questionmark Perception connector">
	<% QMPLaunchPage qbc=new QMPLaunchPage(request,ctx); %>
	<bbNG:pageHeader>
		<bbNG:pageTitleBar iconUrl='<%=qbc.path+"/images/qm.gif"%>'
			title="Questionmark Perception connector" />
	</bbNG:pageHeader>	

	<%
		if (qbc.failTitle == null) {
	%>

	<h1 id="Schedules">Legacy Perception Assessment Link</h1>
	<p>Warning: <i>this item will not report grades to the Blackboard gradebook.</i></p>
	<p><a href="<%=StringEscapeUtils.escapeHtml(qbc.scheduleURL)%>">Launch the Test Now</a></p>

	<%	} else {
	%>

	<bbNG:receipt type="FAIL" title="<%=StringEscapeUtils.escapeHtml(qbc.failTitle) %>">
		<%=StringEscapeUtils.escapeHtml(qbc.failText) %>
	</bbNG:receipt>

	<%
		} //End of other view
	%>
	
</bbNG:learningSystemPage>
