<%@ page
	language="java"
	pageEncoding="UTF-8"
	import="
		com.questionmark.*,
		com.questionmark.QMWISe.*
	"
%>

<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ taglib uri="/bbData" prefix="bbData"%>

<bbData:context id="ctx">
	<bbUI:docTemplate title="Testing Connection to Questionmark Perception">
	<%
	QMPSysAdminContext qbc=new QMPSysAdminContext(request,ctx);
	Version2 version = qbc.Test();
	%>

	<%
		if (qbc.failTitle == null) {
	%>

	<bbUI:receipt type="SUCCESS" title="Connection successful!" recallUrl="/webapps/blackboard/admin/manage_plugins.jsp">
		Perception version <%=version.getBuildString()%><br>
			<%=version.getLicenseText()%><br>
			License expires <%=version.getLicenseExpires()%>
	</bbUI:receipt>

	<%	} else {
	%>

	<bbUI:receipt type="FAIL" title="<%=qbc.failTitle %>" recallUrl="/webapps/blackboard/admin/manage_plugins.jsp">
		<%=qbc.failMsg %>
	</bbUI:receipt>

	<%
		} //End of error view
	%>
	</bbUI:docTemplate>
</bbData:context>
