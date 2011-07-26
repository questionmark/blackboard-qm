<%@ taglib uri="/bbData" prefix="bbData"%>
<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ page import="java.util.*,
				 com.qm.bb6.perception.util.BbUtils" %>
					

<bbData:context id="bbContext">
	<%
	Locale locale = BbUtils.getLocale( request, bbContext );
	%>
	<bbUI:docTemplate title="Questionmark">

		<form name="openWinForm" action="<%= BbUtils.getFullPath( "PerceptionLogin" ) %>" method="GET" TARGET="_new">
			<%
			if( bbContext.getCourse() != null ){
				%><input type="hidden" name="course_id" value="<%= bbContext.getCourseId().toExternalString() %>"><%
			}
			%>
		</form>
		<script language="javascript">
		<!--
		
		document.openWinForm.submit();
		document.go(-1);
		
		// -->
		</script>

		<bbUI:receipt 	type="FAIL" 
					title="Questionmark" >
			
			<%= BbUtils.getSafeCourseString( "click-here-to-submit.message", locale ) %><br><br>
			<a href="javascript:document.openWinForm.submit()"><%= BbUtils.getSafeCourseString( "click-here-to-submit.link", locale ) %></a>
			
		</bbUI:receipt>
				
	</bbUI:docTemplate>
</bbData:context>