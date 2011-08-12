<%@ page
	language="java"
	pageEncoding="UTF-8"
	import="
		com.questionmark.*,
		com.questionmark.QMWISe.*
	"
%>

<%@ taglib uri="/bbData" prefix="bbData"%>
<%@ taglib uri="/bbNG" prefix="bbNG"%>

<bbNG:learningSystemPage ctxId="ctx"
	title="Testing Connection to Questionmark Perception">
	<%
	QMPSysAdminContext qbc=new QMPSysAdminContext(request,ctx);
	Version2 version = qbc.Test();
	%>
	<bbNG:pageHeader>
		<bbNG:pageTitleBar iconUrl='<%=qbc.path+"/images/qm.gif"%>'
			title="Testing Connection to Questionmark Perception" />
	</bbNG:pageHeader>	

	<%
		if (qbc.failTitle == null) {
	%>

	<bbNG:receipt type="SUCCESS" title="Connection successful!" recallUrl="/webapps/blackboard/admin/manage_plugins.jsp">
		Perception version <%=version.getBuildString()%><br>
			<%=version.getLicenseText()%><br>
			Licence expires <%=version.getLicenseExpires()%>
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
