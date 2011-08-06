<%@ page
	language="java"
	import="
		java.util.*,
		blackboard.platform.*,
		blackboard.platform.session.*,
		blackboard.data.user.*,
		blackboard.persist.* ,
		blackboard.persist.user.*,
		blackboard.data.course.*,
		blackboard.persist.course.*,
		com.questionmark.*
	"
	errorPage="/error.jsp"
	pageEncoding="UTF-8"
%>

<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ taglib uri="/bbData" prefix="bbData"%>
<%@ taglib uri="/bbNG" prefix="bbNG"%>

<bbNG:learningSystemPage ctxId="ctx"
	title="Questionmark Perception Connector Settings">
	<%
	QMPSysAdminContext qbc=new QMPSysAdminContext(request,ctx);
	qbc.UpdateProperties();
	%>
	<bbNG:pageHeader>
		<bbNG:pageTitleBar iconUrl='<%=qbc.path+"/images/qm.gif"%>'
			title="Questionmark Perception Connector Settings" />
	</bbNG:pageHeader>	

	<%
		if (qbc.failTitle == null) {
	%>

	<bbNG:receipt type="SUCCESS" title="Settings Saved" recallUrl="test_perception.jsp">
		Your settings have been saved. Click OK to test the connection.
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
