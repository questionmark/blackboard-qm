<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	import="
	com.sun.mail.handlers.text_html,
	org.apache.commons.lang.StringEscapeUtils,
	org.apache.velocity.app.event.implement.EscapeHtmlReference,
	com.questionmark.*"
%>

<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ taglib uri="/bbData" prefix="bbData"%>

<%
String protocol = "";
String host = "";
String port = "";
String directory = "";

boolean perception_security = false;
String username = "";
String checksum = "";

boolean sync_users = false;
boolean sync_groups = false;
boolean sync_members = false;
String syncperiod = "";
boolean single_signon = false;
%>

<bbData:context id="ctx">
	<bbUI:docTemplate title="Questionmark Perception Connector Settings">
<%
	QMPSysAdminContext qbc=new QMPSysAdminContext(request,ctx);
	if (qbc.failTitle == null) {
%>
		<bbUI:titleBar iconUrl="/images/ci/icons/tools_u.gif">
			Questionmark Perception Connector Settings
		</bbUI:titleBar>
		<form action="config_proc.jsp" method="POST">
			<bbUI:step number="1" title="QMWISe Connection">
				<bbUI:instructions>
					Please enter the hostname and port number of the perception webservice below
				</bbUI:instructions>
				<bbUI:dataElement label="Perception server details" required="true">
					<%
						protocol = qbc.pb.getProperty("perception.protocol");
						if (protocol == null)
							protocol = "https://";
						host = qbc.pb.getProperty("perception.host");
						port = qbc.pb.getProperty("perception.port");
						directory = qbc.pb.getProperty("perception.directory");
					%>
					<select name="perception.protocol">
						<option value="https://" <%=protocol.equals("https://")?"selected=\"selected\"":""%>>https://</option>
						<option value="http://" <%=protocol.equals("http://")?"selected=\"selected\"":""%> >http://</option>
					</select>
					<input maxlength="100" size="40" type="text" name="perception.host" value="<%=( host == null )?"localhost":host%>">
					:
					<input maxlength="10" size="6" type="text" name="perception.port" value="<%=( port == null )?"80":port%>">
					/
					<input maxlength="100" size="12" type="text" name="perception.directory" value="<%=( directory == null )?"QMWISe4":directory%>">
				</bbUI:dataElement>
	
				<bbUI:dataElement label="Use Perception security (recommended)" required="false">
					<% perception_security = (qbc.pb.getProperty("perception.security") == null)? false : true; %>
					<input name="perception.security" id="perception.security" type="checkbox" value="Yes" <%=perception_security?"checked":"" %>>
				</bbUI:dataElement>
	
				<bbUI:dataElement label="Perception username" required="false">
					<% username = qbc.pb.getProperty("perception.username"); %>
					<input maxlength="100" size="30" type="text" name="perception.username" value="<%=( username == null )?"":username%>">
				</bbUI:dataElement>
	
				<bbUI:dataElement label="Perception checksum" required="false">
					<% checksum = qbc.pb.getProperty("perception.checksum"); %>
					<input maxlength="100" size="30" type="text" name="perception.checksum" value="<%=( checksum == null )?"":checksum%>">
				</bbUI:dataElement>
			</bbUI:step>
	
			<bbUI:step number="2" title="Controlling Synchronization">
				<bbUI:instructions>
					Please enter the options for synchronizing group and user data with Perception
				</bbUI:instructions>
							
				<bbUI:dataElement label="Synchronize Perception groups automatically" required="false">
					<%
					if (qbc.pb.getProperty("perception.version") == null)
						sync_groups = (qbc.pb.getProperty("perception.syncusers") == null)? false : true; 
					else
						sync_groups = (qbc.pb.getProperty("perception.syncgroups") == null)? false : true;
					%>
					<input name="perception.syncgroups" id="perception.syncgroups" type="checkbox" value="Yes" <%=sync_groups?"checked":"" %>>
					<p><i>Select this option to automatically create groups in Perception</i></p>
					<blockquote>
					<p>If <em>unchecked</em>, the connector will only be available to courses that already have
					a corresponding group in Perception.  This enables you to control use of the connector
					on a course-by-course basis <em>in Perception</em>.  To enable the connector for a course
					use Perception Enterprise Manager to create a group with a name that matches the <em>course
					ID</em> for each course you want to use the connector with.</p>
					</blockquote>
				</bbUI:dataElement>	
							
				<bbUI:dataElement label="Synchronize Perception users automatically?" required="false">
					<% sync_users = (qbc.pb.getProperty("perception.syncusers") == null)? false : true; %>
					<input name="perception.syncusers" id="perception.syncusers" type="checkbox" value="Yes" <%=sync_users?"checked":"" %>>
					<p><i>Select this option to automatically create administrators and participants in Perception</i></p>
					<blockquote>
					<p>If <em>unchecked</em>, the connector will only be available to administrators and
					participants that already exist in Perception.</p>
					</blockquote>
				</bbUI:dataElement>
	
				<bbUI:dataElement label="Synchronize group membership automatically?" required="false">
					<%
					if (qbc.pb.getProperty("perception.version") == null)
						sync_members = (qbc.pb.getProperty("perception.syncusers") == null)? false : true; 
					else
						sync_members = (qbc.pb.getProperty("perception.syncmembers") == null)? false : true;
					%>
					<input name="perception.syncmembers" id="perception.syncmembers" type="checkbox" value="Yes" <%=sync_members?"checked":"" %>>
					<p><i>Select this option to automatically assign administrators and participants to groups in Perception</i></p>
					<blockquote>
					<p>If <em>unchecked</em>, the connector will only be available to administrators and
					participants that are already members of the corresponding group in Perception.</p>
					</blockquote>
				</bbUI:dataElement>
	
				<bbUI:dataElement label="Experimental: Synchronize assessment folders automatically?" required="false">
					<%
					boolean sync_folders=(qbc.pb.getProperty("perception.syncfolders") == null)? false : true;
					%>
					<input name="perception.syncfolders" id="perception.syncfolders" type="checkbox" value="Yes" <%=sync_folders?"checked":"" %>>
					<p><i>Select this option to automatically create assessment folders and assign administrator permissions in Perception</i></p>
					<blockquote>
					<p><em>Warning:</em> assessment folder permissions may not be granted until the course is manually
					synchronized from the course control panel.  To revoke permissions, you must use Perception
					Enterprise Manager.</p>
					</blockquote>
				</bbUI:dataElement>
	
				<bbUI:dataElement label="Access Failure Link" required="false">
					<%
					String link=qbc.pb.getProperty("perception.accesslink");
					if (link == null)
						link="";
					%>
					<input maxlength="1024" size="100" type="text" name="perception.accesslink" value="<%=link%>">
					<p><i>An optional link to a web page with information helpful to people denied access to Perception</i></p>
				</bbUI:dataElement>
	
				<%
					syncperiod = qbc.pb.getProperty("perception.syncperiod");
					if (syncperiod != null) {
				%>
				<bbUI:dataElement label="User synchronization frequency (minutes)" required="false">
					<input name="perception.syncperiod" id="perception.syncperiod" type="text" value="<%=( syncperiod == null )?"60":syncperiod%>" disabled="disabled">
					<p><i>The synchronization frequency is no longer used.</i></p>
					<blockquote>
						<p><em>Warning:</em> this version of the connector contains a new "just-in-time" synchronization
						algorithm.  Users are synchronized individually when they access the connector so
						that they can take Perception assessments immediately after enrollment.  (Course instructors
						and teaching assistants can still synchronize all course members
						at once using the "Synchronize Now" option in the course control panel.)</p>
					</blockquote>
				</bbUI:dataElement>
				<%	} %>
	
				<input type="hidden" name="perception.version" value="1" />
			</bbUI:step>
	
			<bbUI:step number="3" title="Connector Options">
					
				<bbUI:dataElement label="Enable Single Sign-On to Perception Enterprise Manager for course instructors" required="false">
					<% single_signon = (qbc.pb.getProperty("perception.singlesignon") == null)? false : true; %>
					<input name="perception.singlesignon" id="perception.singlesignon" type="checkbox" value="Yes" <%=single_signon?"checked":"" %>>
				</bbUI:dataElement>
				
			</bbUI:step>
	
			<bbUI:stepSubmit title="Submit" number="4" cancelUrl="javaScript:history.go(-1);" />
		</form>
	<%	} else {
	%>

	<bbUI:receipt type="FAIL" title="<%=qbc.failTitle %>">
		<%=qbc.failMsg %>
	</bbUI:receipt>

	<%
		} //End of error view
	%>
	</bbUI:docTemplate>
</bbData:context>


