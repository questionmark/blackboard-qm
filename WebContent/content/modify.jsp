<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% /* @ page language="java" contentType="text/html; charset=ISO-8859-1" 
	import="java.util.*,
	blackboard.platform.*,
	blackboard.platform.session.*,
	blackboard.platform.plugin.*,
	blackboard.platform.security.authentication.*,
	blackboard.data.course.*,
	blackboard.data.user.*,
	blackboard.data.content.*,
	blackboard.persist.*,
	blackboard.persist.user.*,	
	blackboard.persist.course.*,	
	blackboard.persist.content.*,
	blackboard.persist.content.ContentDbLoader,
	blackboard.persist.Id,
	blackboard.base.*"
	pageEncoding="ISO-8859-1"
	
	*/
%>
	
    
<%@ taglib uri="/bbNG" prefix="bbNG" %>

<bbNG:learningSystemPage ctxId="contentCtx">
<bbNG:receipt type="PASS" title="Questionmark Scheduled Assessment">
	<i>You can't edit schedules using the Perception Connector
		<!-- Add code to enable login with single sign on to enterprise manager, check with perception server to enable sign on 
			use same code as maintools to check if single sign on enabled, then show link to login to enterprise manager
		 -->
	</i>
</bbNG:receipt>
</bbNG:learningSystemPage>






