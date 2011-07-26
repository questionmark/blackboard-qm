<%

/*

	Package:	Perception
	Version: 	4.4
	Blackboard:	7.1
	Author:		Matt Elton
	Copyright 2007 VLE Genius. All Rights Reserved
	
*/

%>
<%@ page import="java.util.Locale,
				 blackboard.platform.context.Context,
				 com.qm.bb6.perception.context.PerceptionContext,
				 com.qm.bb6.perception.servlet.PerceptionLoginServlet,
				 com.qm.bb6.perception.util.*" %>

<%@ taglib uri="/bbUI" prefix="bbUI"%>

<bbUI:docTemplate title="Questionmark">
<%

	
	Context bbContext = (Context) request.getAttribute( PerceptionLoginServlet.CONTEXT_PARAM );
	Locale locale = (Locale) request.getAttribute( PerceptionLoginServlet.LOCALE_PARAM );

	String url = (String) request.getAttribute( PerceptionLoginServlet.TARGET_URL_PARAM );
	boolean windowless = ((Boolean) request.getAttribute( PerceptionLoginServlet.SHOW_TOOLBARS_PARAM )).booleanValue();
	
	%>
	<script language="Javascript">
	<!--
		
		function openPerception(){
			<%
			if( windowless ){
				%>window.open("<%= url %>","Perception", "menubar=no,toolbar=no,location=no");<%
			}else{
				%>window.open("<%= url %>");<%
			}
			
			%>
			return;
		}
		
		openPerception();
	// -->
	</script>

	<bbUI:receipt type="SUCCESS" title="<%= BbUtils.getCourseString( "launch-perception-in-new-window-receipt.title", locale ) %>">
		<%= BbUtils.getCourseString( "launch-perception-in-new-window-receipt.message", locale ) %><br><br>
		<a href="javascript:openPerception()"><%= BbUtils.getCourseString( "launch-perception-in-new-window-receipt.link", locale ) %></a>
	</bbUI:receipt><br>
</bbUI:docTemplate>