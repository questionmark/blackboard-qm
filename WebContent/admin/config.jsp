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
						protocol = qbc.pb.getProperty("perception.protocol");
						host = qbc.pb.getProperty("perception.host");
						port = qbc.pb.getProperty("perception.port");
						directory = qbc.pb.getProperty("perception.directory");
					%>
					<select name="perception.protocol">
						<option value="https://" <%=protocol.equals("https://")?"selected=\"selected\"":""%>>https://</option>
						<option value="http://" <%=protocol.equals("http://")?"selected=\"selected\"":""%> >http://</option>
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

			<bbNG:step title="Controlling Synchronization">
							
				<bbNG:stepInstructions text="Please enter the options for synchronizing group and user data with Perception"/>

				<bbNG:dataElement label="Synchronize Perception groups automatically" isRequired="false">
					<%
					if (qbc.pb.getProperty("perception.version") == null)
						sync_groups = (qbc.pb.getProperty("perception.syncusers") == null)? false : true; 
					else
						sync_groups = (qbc.pb.getProperty("perception.syncgroups") == null)? false : true;
					%>
					<input name="perception.syncgroups" id="perception.syncgroups" type="checkbox" value="Yes" <%=sync_groups?"checked":"" %>>
					<bbNG:elementInstructions text="Select this option to automatically create groups in Perception"/>
					<blockquote>
					<p>If <em>unchecked</em>, the connector will only be available to courses that already have
					a corresponding group in Perception.  This enables you to control use of the connector
					on a course-by-course basis <em>in Perception</em>.  To enable the connector for a course
					use Perception Enterprise Manager to create a group with a name that matches the <em>course
					ID</em> for each course you want to use the connector with.</p>
					</blockquote>
				</bbNG:dataElement>	
							
				<bbNG:dataElement label="Synchronize Perception users automatically?" isRequired="false">
					<% sync_users = (qbc.pb.getProperty("perception.syncusers") == null)? false : true; %>
					<input name="perception.syncusers" id="perception.syncusers" type="checkbox" value="Yes" <%=sync_users?"checked":"" %>>
					<bbNG:elementInstructions text="Select this option to automatically create administrators and participants in Perception"/>
					<blockquote>
					<p>If <em>unchecked</em>, the connector will only be available to administrators and
					participants that already exist in Perception.</p>
					</blockquote>
				</bbNG:dataElement>


				<bbNG:dataElement label="Synchronize group membership automatically?" isRequired="false">
					<%
					if (qbc.pb.getProperty("perception.version") == null)
						sync_members = (qbc.pb.getProperty("perception.syncusers") == null)? false : true; 
					else
						sync_members = (qbc.pb.getProperty("perception.syncmembers") == null)? false : true;
					%>
					<input name="perception.syncmembers" id="perception.syncmembers" type="checkbox" value="Yes" <%=sync_members?"checked":"" %>>
					<bbNG:elementInstructions text="Select this option to automatically assign administrators and participants to groups in Perception"/>
					<blockquote>
					<p>If <em>unchecked</em>, the connector will only be available to administrators and
					participants that are already members of the corresponding group in Perception.</p>
					</blockquote>
				</bbNG:dataElement>

				<bbNG:dataElement label="Experimental: Synchronize assessment folders automatically?" isRequired="false">
					<%
					boolean sync_folders=(qbc.pb.getProperty("perception.syncfolders") == null)? false : true;
					%>
					<input name="perception.syncfolders" id="perception.syncfolders" type="checkbox" value="Yes" <%=sync_folders?"checked":"" %>>
					<bbNG:elementInstructions text="Select this option to automatically create assessment folders and assign administrator permissions in Perception"/>
					<blockquote>
					<p><em>Warning:</em> assessment folder permissions may not be granted until the course is manually
					synchronized from the course control panel.  To revoke permissions, you must use Perception
					Enterprise Manager.</p>
					</blockquote>
				</bbNG:dataElement>

				<bbNG:dataElement label="Access Failure Link" isRequired="false">
					<%
					String link=qbc.pb.getProperty("perception.accesslink");
					if (link == null)
						link="";
					%>
					<input maxlength="1024" enabled="1" size="100" type="text" name="perception.accesslink" value="<%=link%>">
					<bbNG:elementInstructions text="An optional link to a web page with information helpful to people denied access to Perception"/>
				</bbNG:dataElement>

				<%
					syncperiod = qbc.pb.getProperty("perception.syncperiod");
					if (syncperiod != null) {
				%>
				<bbNG:dataElement label="User synchronization frequency (minutes)" isRequired="false">
					<input name="perception.syncperiod" id="perception.syncperiod" type="text" value="<%=( syncperiod == null )?"60":syncperiod%>" disabled="disabled">
					<bbNG:elementInstructions text="The synchronization frequency is no longer used."/>
					<blockquote>
						<p><em>Warning:</em> this version of the connector contains a new "just-in-time" synchronization
						algorithm.  Users are synchronized individually when they access the connector so
						that they can take Perception assessments immediately after enrollment.  (Course instructors
						and teaching assistants can still synchronize all course members
						at once using the "Synchronize Now" option in the course control panel.)</p>
					</blockquote>
				</bbNG:dataElement>
				<%	} %>

				<input type="hidden" name="perception.version" value="1" />
			</bbNG:step>
			
			<bbNG:step title="Connector Options">
					
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

	<bbNG:receipt type="FAIL" title="<%=qbc.failTitle %>">
		<%=qbc.failMsg %>
	</bbNG:receipt>

	<%
		} //End of error view
	%>
	
</bbNG:learningSystemPage>

