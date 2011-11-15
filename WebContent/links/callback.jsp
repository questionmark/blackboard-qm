
<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%><%@ page
	language="java"
	pageEncoding="UTF-8"
	contentType="text/plain"
	import="java.util.*,blackboard.platform.*,blackboard.base.*,blackboard.platform.context.*,blackboard.platform.session.*,blackboard.platform.persistence.*,blackboard.data.user.*,blackboard.persist.*,blackboard.persist.user.*,blackboard.data.course.*,blackboard.data.gradebook.*,blackboard.persist.course.*,blackboard.persist.gradebook.*,com.questionmark.*"
%>

<%
	ContextManager ctxMgr = (ContextManager) BbServiceManager
			.lookupService(ContextManager.class);
	Context ctx = ctxMgr.setContext(request);
	try {
		QMPCallbackContext cb = new QMPCallbackContext(request, ctx);
		if (cb.failTitle == null) {
			out.println("Perception: Callback: added a score to gradebook");
		} else {
			out.println(cb.failTitle + ": " + cb.failMsg);
			out.println("");
			out.print(cb.GetRequestString());
		}
	} finally {
		ctxMgr.releaseContext();
	}
%>