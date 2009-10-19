<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" %>

<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ taglib uri="/bbData" prefix="bbData"%>

<%
	String username = "";
	String checksum = "";
	String host = "";
	String port = "";
	String directory = "";
	String mailrecipients = "";
	String mailfrom = "";
	String usefulldsklist = "";
	String dsksusers = "";
	String dsksgroups = "";
	String dsksmemberships = "";
	String syncperiod = "";
	boolean sync_users = false;
	boolean perception_security = false;
	boolean single_signon = false;
	boolean use_dsk_list = false;
%>

<bbData:context>
	<bbUI:docTemplate title="Blackboard Administrator Tools">
		<bbUI:titleBar iconUrl="/images/ci/icons/tools_u.gif">
			Configure Blackboard Administrator Tools
		</bbUI:titleBar>
		<jsp:useBean id="PropertiesManager" scope="session" class="com.questionmark.PropertiesBean" />
		<form action="config_proc.jsp" method="POST">
			<bbUI:step number="1" title="Perception server settings">

				<bbUI:instructions>
					Please enter the hostname and port number of the perception webservice below
				</bbUI:instructions>

				<bbUI:dataElement label="Perception server details" required="true">
					<%
						host = PropertiesManager.getProperty("perception.host");
						port = PropertiesManager.getProperty("perception.port");
						directory = PropertiesManager.getProperty("perception.directory");
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
				</bbUI:dataElement>

				<bbUI:dataElement label="Use Perception security (recommended)" required="false">
					<% perception_security = (PropertiesManager.getProperty("perception.security") == null)? false : true; %>
					<input name="perception.security" id="perception.security" type="checkbox" value="Yes" <%=perception_security?"checked":"" %>>
				</bbUI:dataElement>

				<bbUI:dataElement label="Perception username" required="false">
					<% username = PropertiesManager.getProperty("perception.username"); %>
					<input maxlength="100" enabled="1" size="30" type="text" name="perception.username" value="<%=( username == null )?"":username%>">
				</bbUI:dataElement>

				<bbUI:dataElement label="Perception checksum" required="false">
					<% checksum = PropertiesManager.getProperty("perception.checksum"); %>
					<input maxlength="100" enabled="1" size="30" type="text" name="perception.checksum" value="<%=( checksum == null )?"":checksum%>">
				</bbUI:dataElement>

			</bbUI:step>
			
			<bbUI:step number="2" title="Building Block settings">
			
				<bbUI:dataElement label="Synchronize Perception users automatically?" required="false">
					<% sync_users = (PropertiesManager.getProperty("perception.syncusers") == null)? false : true; %>
					<input name="perception.syncusers" id="perception.syncusers" type="checkbox" value="Yes" <%=sync_users?"checked":"" %>>
				</bbUI:dataElement>
				
				<bbUI:instructions>
					Enter the number of minutes between user synchronizations - whenever a user clicks on the "Questionmark Perception Connector" 
					course tool, if the last synchronization was not performed within the specified period, a user synchronization will take place.
				</bbUI:instructions>
				
				<bbUI:dataElement label="User synchronization frequency (minutes)" required="false">
					<% syncperiod = PropertiesManager.getProperty("perception.syncperiod");%>
					<input name="perception.syncperiod" id="perception.syncperiod" type="text" value="<%=( syncperiod == null )?"60":syncperiod%>">
				</bbUI:dataElement>
				
				<bbUI:dataElement label="Enable Single Sign-On to Perception Enterprise Manager for course instructors" required="false">
					<% single_signon = (PropertiesManager.getProperty("perception.singlesignon") == null)? false : true; %>
					<input name="perception.singlesignon" id="perception.singlesignon" type="checkbox" value="Yes" <%=single_signon?"checked":"" %>>
				</bbUI:dataElement>
				
			</bbUI:step>
			
			<bbUI:stepSubmit number="3" cancelUrl="javaScript:history.go(-1);" />
		</form>
	</bbUI:docTemplate>
</bbData:context>
