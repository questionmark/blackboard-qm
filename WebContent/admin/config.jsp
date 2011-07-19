<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" 
	import="
	com.sun.mail.handlers.text_html,
	org.apache.commons.lang.StringEscapeUtils,
	org.apache.velocity.app.event.implement.EscapeHtmlReference,
	com.questionmark.*"
%>

<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ taglib uri="/bbData" prefix="bbData"%>
<%@ taglib uri="/bbNG" prefix="bbNG"%>

<%
String host = "";
String port = "";
String directory = "";

boolean perception_security = false;
String username = "";
String checksum = "";

boolean sync_users = false;
String syncperiod = "";
boolean single_signon = false;
boolean limit_availability = false;
%>


<bbNG:learningSystemPage ctxId="ctx"
	title="Questionmark Perception Connector Settings">
	<% QMPSysAdminContext qbc=new QMPSysAdminContext(request,ctx); %>
	<bbNG:pageHeader>
		<bbNG:pageTitleBar iconUrl='<%=qbc.path+"/images/qm.gif"%>'
			title="Questionmark Perception Connector Settings" />
	</bbNG:pageHeader>	

	<%
		if (qbc.failTitle == null) {
	%>

	<form action="config_proc.jsp" method="POST">
		<bbNG:dataCollection>			
			<bbNG:step title="QMWISe Connection">
				<bbNG:stepInstructions text="Please enter the hostname and port number of the perception webservice below"/>
				
				<bbNG:dataElement label="Perception server details" isRequired="true">
					<%
						host = qbc.pb.getProperty("perception.host");
						port = qbc.pb.getProperty("perception.port");
						directory = qbc.pb.getProperty("perception.directory");
					%>
					<select name="perception.protocol">
						<option value="http://">http://</option>
						<option value="https://">https://</option>
					</select>
					<input maxlength="100" enabled="1" size="40" type="text" name="perception.host" value="<%=( host == null )?"localhost":host%>">
					:
					<input maxlength="10" enabled="1" size="6" type="text" name="perception.port" value="<%=( port == null )?"80":port%>">
					/
					<input maxlength="100" enabled="1" size="12" type="text" name="perception.directory" value="<%=( directory == null )?"QMWISe4":directory%>">
				</bbNG:dataElement>

				<bbNG:dataElement label="Use Perception security (recommended)" isRequired="false">
					<% perception_security = (qbc.pb.getProperty("perception.security") == null)? false : true; %>
					<input name="perception.security" id="perception.security" type="checkbox" value="Yes" <%=perception_security?"checked":"" %>>
				</bbNG:dataElement>

				<bbNG:dataElement label="Perception username" isRequired="false">
					<% username = qbc.pb.getProperty("perception.username"); %>
					<input maxlength="100" enabled="1" size="30" type="text" name="perception.username" value="<%=( username == null )?"":username%>">
				</bbNG:dataElement>

				<bbNG:dataElement label="Perception checksum" isRequired="false">
					<% checksum = qbc.pb.getProperty("perception.checksum"); %>
					<input maxlength="100" enabled="1" size="30" type="text" name="perception.checksum" value="<%=( checksum == null )?"":checksum%>">
				</bbNG:dataElement>

			</bbNG:step>

			<bbNG:step title="Controlling Availability">
			
				<bbNG:dataElement label="Control Availability using Perception Groups" isRequired="false">
					<% limit_availability = (qbc.pb.getProperty("perception.limitavailability") == null)? false : true; %>
					<input name="perception.limitavailability" id="perception.limitavailability" type="checkbox" value="Yes" <%=limit_availability?"checked":"" %>>
				</bbNG:dataElement>

				<p>If checked, the connector will only be available to courses with a corresponding group
				in Perception: to make the connector available you must create a group with a name that
				matches the course <em>ID</em>.
				</p>
			
			</bbNG:step>
			
			<bbNG:step title="Connector Options">
					
				<bbNG:dataElement label="Synchronize Perception users automatically?" isRequired="false">
					<% sync_users = (qbc.pb.getProperty("perception.syncusers") == null)? false : true; %>
					<input name="perception.syncusers" id="perception.syncusers" type="checkbox" value="Yes" <%=sync_users?"checked":"" %>>
				</bbNG:dataElement>
				
				<bbNG:stepInstructions text="Enter the number of minutes between user synchronizations - whenever a user clicks on the 'Questionmark Perception Connector' 
					course tool, if the last synchronization was not performed within the specified period, a user synchronization will take place."/>
				
				<bbNG:dataElement label="User synchronization frequency (minutes)" isRequired="false">
					<% syncperiod = qbc.pb.getProperty("perception.syncperiod");%>
					<input name="perception.syncperiod" id="perception.syncperiod" type="text" value="<%=( syncperiod == null )?"60":syncperiod%>">
				</bbNG:dataElement>
				
				<bbNG:dataElement label="Enable Single Sign-On to Perception Enterprise Manager for course instructors" isRequired="false">
					<% single_signon = (qbc.pb.getProperty("perception.singlesignon") == null)? false : true; %>
					<input name="perception.singlesignon" id="perception.singlesignon" type="checkbox" value="Yes" <%=single_signon?"checked":"" %>>
				</bbNG:dataElement>
				
			</bbNG:step>
									
			<bbNG:stepSubmit title="Submit"/>

		</bbNG:dataCollection>
	</form>

	<%	} else {
	%>

	<bbNG:receipt type="FAIL" title="<%=StringEscapeUtils.escapeHtml(qbc.failTitle) %>">
		<%=StringEscapeUtils.escapeHtml(qbc.failText) %>
	</bbNG:receipt>

	<%
		} //End of error view
	%>
	
</bbNG:learningSystemPage>

