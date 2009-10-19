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

<%@ taglib uri="/bbUI" prefix="bbUI" %> 
<%@ taglib uri="/bbData" prefix="bbData" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<bbData:context id="ctx">
<%
String errortitle = null;
String errormessage = null;
QMWise qmwise;
String url;
User sessionUser = ctx.getUser();

PropertiesBean pb = new PropertiesBean();
if(pb.getProperty("perception.singlesignon") == null) {
	errortitle = "Single sign-on is not allowed";
	errormessage = "The administrator has disabled single sign-on to Perception Enterprise Manager for instructors. Contact the administrator if this is a problem.";
} else {
	try {
		qmwise = new QMWise();
		try {
			url = qmwise.getStub().getAccessAdministrator(sessionUser.getUserName());
			response.sendRedirect(url);
			return;
		} catch(Exception ne) {
			QMWiseException nqe = new QMWiseException(ne);
			errortitle = "Error getting Enterprise Manager URL";
			errormessage = nqe.getMessage();
		}
	} catch(Exception e) {
		QMWiseException qe = new QMWiseException(e);
		errortitle = "Error connecting to Perception server";
		errormessage = qe.getMessage();
	}
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>Questionmark Perception connector</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="questionmark perception,questionmark,perception,assessment,connector">
		<meta http-equiv="description" content="Questionmark Perception connector for Blackboard">
	</head>

	<body>
		<bbUI:docTemplate>
			<bbUI:receipt type="FAIL" title="<%=errortitle%>">
				<%=errormessage%>
			</bbUI:receipt>
		</bbUI:docTemplate>
	</body>
</html>
</bbData:context>
