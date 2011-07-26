<%

/*

	Package:	Perception
	Version: 	1.0.1
	Blackboard:	6.1
	Author:		Matt Elton
	Copyright 2005 VLE Genius. All Rights Reserved
	
*/

%>

<%@ page import="java.io.UnsupportedEncodingException,
				 java.util.*,
				 com.qm.bb6.perception.util.*" %>
<%@ taglib uri="/bbData" prefix="bbData"%>
<bbData:context id="bbContext">
<%

	// bug fix for Bb remove script
  try
  {
  	StringBuffer removePath = new StringBuffer();
//  	removePath.append( BbUtils.getFullPath("RemoveAssessment") );
  	removePath.append( "/RemoveAssessment" );
  	removePath.append( "?course_id=" + bbContext.getCourseId().toExternalString() );
  	removePath.append( "&content_id=" + bbContext.getContentId().toExternalString() );
   	RequestDispatcher rd  = pageContext.getServletContext().getRequestDispatcher( removePath.toString() );
  	rd.include(request, response);
  }
  catch (Exception e)
  {
	// ignore
  }
%>
</bbData:context>