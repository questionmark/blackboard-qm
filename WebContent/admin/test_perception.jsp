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

<bbData:context>
	<bbUI:docTemplate title="Custom My Courses Module">
		<bbUI:breadcrumbBar></bbUI:breadcrumbBar>
		<bbUI:titleBar iconUrl="/images/ci/icons/tools_u.gif">
			Test connection to Questionmark Perception
		</bbUI:titleBar>

		<%
		QMWise qmwise = null;

		try {
			qmwise = new QMWise();
		} catch(Exception e) {
			%>
			<bbUI:receipt type="FAIL" title="Error connecting to Perception server" recallUrl="config.jsp">
				<%=e.getMessage()%>
			</bbUI:receipt>
			<%
			return;
		}

		//get version info
		Version2 version = qmwise.getStub().getAbout2();

		%>
		<bbUI:receipt type="SUCCESS" title="Connection successful!" recallUrl="/webapps/blackboard/admin/manage_plugins.jsp">
			Perception version <%=version.getBuildString()%><br>
			<%=version.getLicenseText()%><br>
			Licence expires <%=version.getLicenseExpires()%>
		</bbUI:receipt>

	</bbUI:docTemplate>
</bbData:context>
