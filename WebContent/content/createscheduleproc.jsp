<!-- 
////////////////////////////

// Filename		: content/createscheduleproc.jsp
// Description	: 'createproc' for createscheduleform.jsp, does the content 
//				  item object initialisation and creation of schedule
				  attached to the content item for use by students and staff.
////////////////////////////
-->

<%@ page
	language="java"
	pageEncoding="UTF-8"
	import="java.util.*,
	java.text.*,
	blackboard.platform.*,
	blackboard.platform.session.*,
	blackboard.platform.persistence.*,
	blackboard.platform.plugin.*,
	blackboard.platform.security.authentication.*,
	blackboard.persist.*,blackboard.persist.user.*,
	blackboard.persist.course.*,blackboard.persist.content.*,
	blackboard.persist.content.ContentDbLoader,
	blackboard.persist.Id,blackboard.data.course.*,
	blackboard.data.user.*,
	blackboard.data.content.*,
	blackboard.base.*,
	blackboard.data.gradebook.*,
	blackboard.persist.gradebook.*,
	blackboard.servlet.util.DatePickerUtil,
	org.apache.axis.*,
	java.rmi.RemoteException,
	javax.xml.namespace.QName,com.questionmark.*,
	com.questionmark.QMWISe.*,
	blackboard.base.FormattedText.Type,
	org.apache.commons.lang.StringEscapeUtils"
%>

<%@ taglib uri="/bbData" prefix="bbData" %>
<%@ taglib uri="/bbNG" prefix="bbNG" %>

<bbNG:learningSystemPage ctxId="ctx"
	title="Questionmark Perception Schedule">
	<%
	QMPContentCreator cc=new QMPContentCreator(request,ctx,response);
	cc.ProcessNewForm();
	%>
	<bbNG:pageHeader>
		<bbNG:pageTitleBar iconUrl='<%=cc.path+"/images/qm.gif"%>'
			title="Questionmark Perception Schedule Created" />
	</bbNG:pageHeader>	

	<%
		if (cc.failTitle == null) {
			String okUrl = "/webapps/blackboard/content/listContentEditable.jsp?content_id="
				+ cc.contentItem.parentId.getExternalString()
				+ "&course_id="
				+ cc.courseId
				+ "&mode=quick";	%>

	<bbNG:receipt type="SUCCESS" title="Schedule Created" recallUrl="<%= StringEscapeUtils.escapeHtml(okUrl) %>">
		<p>The schedule <i><%=cc.contentItem.name %></i> was successfully created</p>
	</bbNG:receipt>

	<%	} else {
	%>

	<bbNG:receipt type="FAIL" title="<%=cc.failTitle %>">
		<%=cc.failMsg %>
	</bbNG:receipt>

	<%
		} //End of error view
	%>
	
</bbNG:learningSystemPage>

