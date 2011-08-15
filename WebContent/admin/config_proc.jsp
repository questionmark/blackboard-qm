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

<bbData:context id="ctx">
	<bbUI:docTemplate title ="Questionmark Perception Connector Settings">
	<%
	QMPSysAdminContext qbc=new QMPSysAdminContext(request,ctx);
	qbc.UpdateProperties();
	%>
	<%
		if (qbc.failTitle == null) {
	%>

	<bbUI:receipt type="SUCCESS" title="Settings Saved" recallUrl="test_perception.jsp">
		Your settings have been saved. Click OK to test the connection.
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

