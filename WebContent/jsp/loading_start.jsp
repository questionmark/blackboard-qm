<%

/*

	Version: 	1.0
	Blackboard:	6.1
	Author:		Matt Elton
	Copyright 2005 VLE Genius.
	
*/
%>
<%@ page import="java.util.*,
				 blackboard.data.course.*,
				 blackboard.data.user.User,
				 blackboard.persist.Id,
				 blackboard.platform.context.Context,
				 com.qm.bb6.perception.servlet.*,
				 com.qm.bb6.perception.util.*" %>
<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ taglib uri="/perception" prefix="perception"%>
<%!

	boolean isNullOrEmpty( String str ){
		if( str == null || str.trim().length() == 0 )
			return true;
		return false;
	}
%>
<%
	Locale locale = (Locale) request.getAttribute( SynchronisationServlet.LOCALE_PARAM );
	String type = "SUCCESS";
	int totalSteps = ((Integer) request.getAttribute(SynchronisationServlet.TOTAL_STEPS_ATTR)).intValue();
	String recallUrl = (String) request.getAttribute("recallUrl");
	String processTitle = (String) request.getAttribute( SynchronisationServlet.PROCESS_TITLE_ATTR );
	String processMessage = (String) request.getAttribute( SynchronisationServlet.PROCESS_MESSAGE_ATTR );
	String processStart = (String) request.getAttribute( SynchronisationServlet.PROCESS_START_ATTR );
	String processMiddle = (String) request.getAttribute( SynchronisationServlet.PROCESS_MIDDLE_ATTR );
	String processEnd = (String) request.getAttribute( SynchronisationServlet.PROCESS_END_ATTR );
	String processingMessage = (String) request.getAttribute( SynchronisationServlet.PROCESSING_MESSAGE_ATTR );
	
	// set defaults
	if( isNullOrEmpty(processTitle) ){
		processTitle = BbUtils.getCourseString( "full-synchronization-process.title", locale );
	}
	if( isNullOrEmpty(processMessage) ){
		processMessage = BbUtils.getCourseString( "full-synchronization-process.message", locale );
	}
	if( isNullOrEmpty(processStart) ){
		processStart = BbUtils.getCourseString( "full-synchronization-process.processing.start", locale );
	}
	if( isNullOrEmpty(processMiddle) ){
		processMiddle = BbUtils.getCourseString( "full-synchronization-process.processing.middle", locale );
	}
	if( isNullOrEmpty(processEnd) ){
		processEnd = BbUtils.getCourseString( "full-synchronization-process.processing.end", locale );
	}
	if( isNullOrEmpty(processingMessage) ){
		processingMessage = BbUtils.getCourseString( "full-synchronization-process.processing.message", locale );
	}
	
	
%>		


<html>
<head>
	<title>Perception Syncronization</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="author" content="Matt Elton, VLE Genius">
	<meta name="copyright" content="Copyright 2005 Questionmark Computing Ltd.">
	<link type="text/css" rel="stylesheet" href="/ui/styles/blackboard.css" />
	<link type="text/css" rel="stylesheet" href="/ui/styles/palette.css" />
    <script language="JavaScript1.2" type="text/javascript">
    <!--
    
      var MESSAGE_LAYER_ID = "message";
      var STEP_COUNTER_ID = "stepCount";
      var ERROR_LAYER_ID = "error";
      var PROCESS_LAYER = "processingInfo";
      var recallUrl = "<%= recallUrl %>";
      var goBack = false;

      function changeElementText( element_id, message ){
          message = "" + message; // just in case it's null
          var messageLayer;
          if( document.getElementById ){ // for NN6+
              messageLayer = document.getElementById( element_id );
              messageLayer.innerHTML = message;
          }
          if( document.all ){ // IE
              messageLayer = document.all[ element_id ];
              messageLayer.innerHTML = message;
           }
       }
		
      function changeStepCount( stepNumber ){    	
      	changeElementText( STEP_COUNTER_ID, stepNumber );
      }

      function changeMessage( message ){
      	changeElementText( MESSAGE_LAYER_ID, message );
      }

      function changeError( message ){
      	changeElementText( PROCESS_LAYER, "" );
      	changeElementText( ERROR_LAYER_ID, message + "<br><br>" );
	  }       

      function finishProcess( title, message ){
      	changeStepCount(<%= totalSteps %>);
      	changeError( title );
      	changeMessage( message );
	  }       

	  function startDotting( ){
	  	// do nothing
	  }
	  
	  function nextPage(){
	  	if( !goBack ){
	  		location.href = recallUrl;
	  	}else{
	  		history.back();
	  	}
	  }
	  	
    // -->
    </script>
</head>


<body class="bbDefault" onLoad="startDotting();">



	<bbUI:receipt 	type="<%= type %>" 
					title="<%= processTitle %>" 
					recallUrl="javascript:nextPage();">
					
		<div name="Layer1" id="Layer1">
			<table cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td><bbUI:spacer width="5" height="5"/></td>
				</tr>
				<tr>
					<td><%= processMessage %><br/><br/></td>
				</tr>

				<tr>
					<td style="color:#990033;">
						<span id="processingInfo" name="processingInfo">
							<%= processStart %>&nbsp;
							<b><span id="stepCount" name="stepCount">0</span></b>&nbsp;
							<%= processMiddle %>&nbsp;
							<b><%= totalSteps %></b>
							<%= processEnd %>
							<br>
						</span>
					</td>
				</tr>
				<tr>
					<td style="color:#990033;">
						<b><span id="error" name="error"></span></b>
					</td>
				</tr>
				<tr>
					<td style="color:#990033;">
						<%= processingMessage %>&nbsp;
						<b><span id="message" name="message"></span></b>
						<br/><br/>
					</td>
				</tr>

			</table>
		</div>
		
	</bbUI:receipt><br>

	<script language="JavaScript1.2" type="text/javascript">
	<!--
	
	//-->
	</script>




