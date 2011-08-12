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

<%@ taglib uri="/bbData" prefix="bbData"%>
<%@ taglib uri="/bbNG" prefix="bbNG"%>

<bbNG:learningSystemPage ctxId="ctx" title="Questionmark Perception connector">

	<%
	QMPCourseContext cc=new QMPCourseContext(request,ctx);
	String link=null;
	if (cc.failTitle==null)
		link=cc.EnterpriseManagerLink();
	%>
	<bbNG:pageHeader>
		<bbNG:pageTitleBar iconUrl='<%=cc.path+"/images/qm.gif"%>'
			title='Questionmark Perception Results' />
	</bbNG:pageHeader>	

	<%
	if (link != null) {
		response.sendRedirect(link);
	} else {	
	%>

	<bbNG:receipt type="FAIL" title="<%=cc.failTitle %>">
		<%=cc.failMsg %>
	</bbNG:receipt>

	<%
	} //End of other view
	%>
</bbNG:learningSystemPage>


