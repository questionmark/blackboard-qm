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
				 blackboard.data.content.Content,
				 blackboard.data.course.*,
				 blackboard.data.user.User,
				 blackboard.persist.Id,
				 blackboard.platform.context.Context,
				 com.qm.bb6.perception.context.PerceptionContext,
				 com.qm.bb6.perception.data.*,
				 com.qm.bb6.perception.servlet.*,
				 com.qm.bb6.perception.service.*,
				 com.qm.bb6.perception.config.*,
				 com.qm.bb6.perception.config.impl.SimpleContentSettings,
				 com.qm.bb6.perception.util.*" %>

<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ taglib uri="/bbData" prefix="bbData"%>
<%@ taglib uri="/perception" prefix="perception"%>
<%!

	public static final String PARENT_ID_PARAMETER = "parent_id";
	public static final String UP_ID_PARAMETER = "up_id";
	
	String getFolderUrl( String queryString, String folder_id, String parent_id, String[] up_ids ) throws UnsupportedEncodingException{
	
		StringBuffer urlBuffer = new StringBuffer();
		urlBuffer.append( BbUtils.getFullPath("CreateAssessment") + "?" );
		queryString = queryString.replaceAll( "[\\&]?" + PARENT_ID_PARAMETER + "=[^\\&]*", "&" ); 
		queryString = queryString.replaceAll( "[\\&]?" + UP_ID_PARAMETER + "=[^\\&]*", "&" ); 
		queryString += "&" + PARENT_ID_PARAMETER + "=" + WebUtils.parseQueryValue( folder_id );
		if( parent_id != null ){
			String up_ids_string = "";
			if( up_ids != null ){
				for( int i=0; i < up_ids.length; i++ ){
					up_ids_string += up_ids[i] + ",";
				}
			}
			up_ids_string += parent_id;
			queryString += "&" + UP_ID_PARAMETER + "=" + WebUtils.parseQueryValue( up_ids_string );
		}else{
			String up_ids_string = "";
			if( up_ids != null ){
				for( int i=0; i < up_ids.length; i++ ){
					if( folder_id.equals( up_ids[i] ) ){
						// stop here
						break;
					}else{
						up_ids_string += up_ids[i] + ",";
					}
				}
			}
			// chop
			if( up_ids_string.length() != 0 ){
				up_ids_string = up_ids_string.substring(0, up_ids_string.length() - 1);
				queryString += "&" + UP_ID_PARAMETER + "=" + WebUtils.parseQueryValue( up_ids_string );
			}
		}			
		queryString = queryString.replaceAll("[\\&]+", "&");
		if( queryString.startsWith("&") ){
			queryString = queryString.replaceFirst("&","?");
		}
		urlBuffer.append( queryString );
		return urlBuffer.toString();
		
	}


%>
<bbUI:docTemplate title="Questionmark">
<%

	
	Context bbContext = (Context) request.getAttribute( AuthenticatedServlet.CONTEXT_PARAM );
	Locale locale = (Locale) request.getAttribute( AuthenticatedServlet.LOCALE_PARAM );
	User user = bbContext.getUser();
	Course course = bbContext.getCourse();
	CourseMembership enrolment = bbContext.getCourseMembership();
	
	PerceptionContext perceptionContext = (PerceptionContext) request.getAttribute( CreateAssessmentServlet.PERCEPTION_CONTENT_PARAM );
	PerceptionUser administrator = perceptionContext.getPerceptionUser();

	Content parent = bbContext.getContent();
	
	// load course specific perception settings
	CourseSettings settings = CourseSettings.load( course );
	
	String lastUrl = (String) request.getAttribute( "recallUrl" );
	String recallUrl = BbUtils.getCleverUrl("CourseAdmin", course, lastUrl);
	
	// get page title
	String title = BbUtils.getCourseString( "add-assessment.title", locale );
	
	String parent_id = request.getParameter( PARENT_ID_PARAMETER );
	if( parent_id != null && parent_id.trim().length() == 0 )
		parent_id = null;

	String[] up_id;
	String up_id_string = request.getParameter( UP_ID_PARAMETER );
	if( up_id_string == null || up_id_string.trim().length() == 0 ){
	//	if( parent_id != null )
	//		up_id = new String[]{parent_id};
	//	else
			up_id = null;
	}else{
		up_id = up_id_string.split(",");
	}
		
	boolean hasAssessmentChecked = false;
	%>		
	
	<bbUI:coursePage course="<%= course %>">
		<bbUI:breadcrumbBar environment="CTRL_PANEL" handle="control_panel">
			<bbUI:breadcrumb><%= title %></bbUI:breadcrumb>
		</bbUI:breadcrumbBar>
		<bbUI:titleBar iconUrl="<%= BbUtils.getFullPath("images/qm.gif") %>"> &nbsp; <%= title %></bbUI:titleBar>
		<br>

		<form name="addForm" method="POST" action="<%= BbUtils.getFullPath("CreateAssessment") %>" >
			<input type="hidden" name="course_id" value="<%= course.getId().toExternalString() %>">
			<input type="hidden" name="content_id" value="<%= parent.getId().toExternalString() %>">
			
			<bbUI:step title="<%= BbUtils.getCourseString( "select-assessment.title", locale ) %>">
				<bbUI:dataElement label="<%= BbUtils.getCourseString( "select-assessment.label", locale ) %>">
					<%
					
					// select assessement 
					
						try{
							AssessmentTreeItemLoader treeLoader = PerceptionServiceManager.getAssessmentTreeItemLoader();
							List branches;
							if( parent_id == null ){
								branches = treeLoader.getAssessmentTreeByAdministratorId( administrator.getUserId(), false ); // should be true
							}else{
								branches = treeLoader.getAssessmentTreeByAdministratorId( administrator.getUserId(), parent_id, false ); // should be true
							}

							%>
							<table border="0" cellpadding="0" cellspacing="2">
							<%
							if( (up_id != null && up_id.length != 0) || (parent_id != null) ){
								// show up link
								String previousFolder = BbUtils.getCourseString( "previous-folder.label", locale );
								String upUrl;
								if( up_id != null && up_id.length != 0 ){
									upUrl = getFolderUrl( request.getQueryString(), up_id[up_id.length-1], null, up_id );
								}else{ // go to root
									upUrl = getFolderUrl( request.getQueryString(), null, null, null );
								}
								%>
								<tr>
									<td colspan="2">
										<a href="<%=upUrl%>"><img src="<%= BbUtils.getFullPath("images/remov_16.gif") %>" border="0"></a>
										<a href="<%=upUrl%>"><%= WebUtils.parseHtmlValue( previousFolder ) %></a>
									</td>
								</tr>
								<%
							}
							

							if( branches == null || branches.size() == 0 ){
								String errorMessage = BbUtils.getCourseString( "no-assessments-found.message", locale );
								%>
								<tr>
									<td colspan="2"><B><font color="#FF0000"><%=errorMessage%></font></B></td>
								</tr>
								<%
							}else{

								
								for( Iterator iterator = branches.iterator(); iterator.hasNext(); ){
									%><tr><%
									AssessmentTreeItem treeItem = (AssessmentTreeItem) iterator.next();
									if( treeItem.getType() == AssessmentTreeItem.ASSESSMENT_FOLDER ){
										String folderUrl = getFolderUrl( request.getQueryString(), treeItem.getItemId(), parent_id, up_id );
										%>
										<td colspan="2">
										<a href="<%=folderUrl%>"><img src="<%= BbUtils.getFullPath("images/foldr_16.gif") %>" border="0"></a>
										<a href="<%=folderUrl%>"><%= WebUtils.parseHtmlValue( treeItem.getName() ) %></a>
										</td>
										<%
									}else{
									
									
										%>
										<td><%= WebUtils.parseHtmlValue( treeItem.getName() ) %></td>
										<td><input 	type="radio" 
												name="<%= CreateAssessmentServlet.PERCEPTION_ID_PARAM %>"
												value="<%= WebUtils.parseFormValue(treeItem.getItemId()) %>"
												
												<%
												
												if( !hasAssessmentChecked ){
													%>CHECKED<%
													hasAssessmentChecked = true;
												}
												
												%>></td>
										<%
									}
									%></tr><%
								}
							}
							%>
							</table>
							<%
						}catch( PerceptionDataException pde ){
							ResourceBundle bundle = PerceptionServiceManager.getErrorCodeResourceBundle( locale );

							String errorMessage;
							try{
								errorMessage = bundle.getString( String.valueOf(pde.getCode()) );
							}catch( MissingResourceException bknfe ){
								errorMessage = pde.getMessage();
							}
							%><B><font color="#FF0000"><%=errorMessage%></font></B><%
						}
					
					%>
				</bbUI:dataElement>
			</bbUI:step>
			<%
				if( hasAssessmentChecked ){
					%><bbUI:stepSubmit/><%
				}else{
					%>
					<BR>
					<div align="right">
						<%
						if( lastUrl != null ){
							%><bbUI:button type="FORM_ACTION" alt="cancel" name="cancel" action="LINK" targetUrl="<%= lastUrl %>" /><%
						}else{
							%><bbUI:button type="FORM_ACTION" alt="cancel" name="cancel" action="LINK" targetUrl="javascript:history.go(-1);"/><%
						}					
						%>
					</div>
					<%
				}
				
			%>
		</form>

		<perception:licenseInfo />

	</bbUI:coursePage>
</bbUI:docTemplate>
