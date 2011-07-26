<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page isErrorPage = "true" %>
<%
	String message = (String) request.getAttribute("message");
	if( exception == null )
		exception = (Exception) request.getAttribute("exception");
	String strException = exception.getMessage();
%>	
<bbUI:docTemplate>
	<bbUI:receipt type="FAIL" title="Error">
	<%=strException%>
	<p>
	<pre>
	<%
		// now display a stack trace of the exception
	  PrintWriter pw = new PrintWriter( out );
	  exception.printStackTrace( pw );
	%>
	</pre>
	</bbUI:receipt><br>
	<%= message %>
</bbUI:docTemplate>

