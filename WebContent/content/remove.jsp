
<% 
   /* Runs when deleting a content item.
   */  
   
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
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
	%>
	
    
<%@ taglib uri="/bbNG" prefix="bbNG" %>

<bbNG:learningSystemPage ctxId="contentCtx">
	<bbNG:receipt type="PASS" title="Content Item removed">Your Perception assessment schedule has been
		deleted successfully. Please note this schedule will also / should also be deleted
		from Enterprise Manager.
	</bbNG:receipt>
</bbNG:learningSystemPage>
