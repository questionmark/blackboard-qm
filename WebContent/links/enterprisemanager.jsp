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
		com.questionmark.*,
		com.questionmark.QMWISe.*
	"
%>

<%@ taglib uri="/bbData" prefix="bbData"%>
<%@ taglib uri="/bbUI" prefix="bbUI"%>

<bbData:context id="ctx">
	<%
	QMPCourseContext cc=new QMPCourseContext(request,ctx);
	String link=null;
	if (cc.failTitle==null)
		link=cc.EnterpriseManagerLink();
	%>
	<%
	if (link != null) {
		response.sendRedirect(link);
	} else {	
	%>
	<bbUI:docTemplate title="Questionmark Perception Results">
		<bbUI:receipt type="FAIL" title="<%=cc.failTitle %>">
			<%=cc.failMsg %>
		</bbUI:receipt>
	</bbUI:docTemplate>
	<%
	} //End of other view
	%>
</bbData:context>

