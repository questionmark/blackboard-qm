<%

/*

	Version: 	1.0
	Blackboard:	6.1
	Author:		Matt Elton
	Copyright 2005 VLE Genius.
	
*/
%>
<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%
	String message = (String) request.getAttribute("message");
	String type = (String) request.getAttribute("type");
	String title = (String) request.getAttribute("title");
	String recallUrl = (String) request.getAttribute("recallUrl");
%>		

<bbUI:docTemplate>
	<bbUI:receipt type="<%= type %>" title="<%= title %>" recallUrl="<%= recallUrl %>">
		<%

		if(message != null){
			out.println(message);
		}
			
		%>
	</bbUI:receipt><br>
	
</bbUI:docTemplate>

