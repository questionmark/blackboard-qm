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
				 blackboard.base.BbList,
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
		queryString = queryString.replaceAll( "[\\&]?search=[^\\&]*", "&" ); 
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

	String getSelectAssessmentUrl( StringBuffer baseUrl, String itemId ){
		StringBuffer sBuf = new StringBuffer( baseUrl );
		sBuf.append( WebUtils.parseFormValue(itemId) );
		return sBuf.toString();
	}
	
	String getSearchUrl( String queryString ) throws UnsupportedEncodingException{
			StringBuffer urlBuffer = new StringBuffer();	
			urlBuffer.append( BbUtils.getFullPath("CreateAssessment") + "?" );
			queryString = queryString.replaceAll( "[\\&]?search=[^\\&]*", "&" ); 	
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
	String recallUrl = (String) request.getAttribute("recallUrl");
	
	PerceptionContext perceptionContext = (PerceptionContext) request.getAttribute( CreateAssessmentServlet.PERCEPTION_CONTENT_PARAM );
	PerceptionUser administrator = perceptionContext.getPerceptionUser();
	SelectAssessmentComparator nameComparator = new SelectAssessmentComparator(SelectAssessmentComparator.ITEM_NAME);
	SelectAssessmentComparator typeComparator = new SelectAssessmentComparator(SelectAssessmentComparator.ITEM_TYPE);

	Content parent = bbContext.getContent();
	
	// load course specific perception settings
	CourseSettings settings = CourseSettings.load( course );
	
	String lastUrl = (String) request.getAttribute( "recallUrl" );
	//String recallUrl = BbUtils.getCleverUrl("CourseAdmin", course, lastUrl);
	
	// get page title
	String title = BbUtils.getCourseString( "select-assessment.title", locale );
	
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
	
	StringBuffer baseSelectAssessmentUrl = new StringBuffer();
	baseSelectAssessmentUrl.append( BbUtils.getFullPath("CreateAssessment") );
	baseSelectAssessmentUrl.append( "?course_id=" + course.getId().toExternalString() );
	baseSelectAssessmentUrl.append( "&content_id=" + parent.getId().toExternalString() );
	baseSelectAssessmentUrl.append( "&" + CreateAssessmentServlet.PERCEPTION_ID_PARAM + "=" );

	
	%>		
	
	<bbUI:coursePage course="<%= course %>">
		<bbUI:breadcrumbBar environment="CTRL_PANEL" handle="control_panel">
			<bbUI:breadcrumb><%= title %></bbUI:breadcrumb>
		</bbUI:breadcrumbBar>
		<bbUI:titleBar iconUrl="<%= BbUtils.getFullPath("images/qmnew.gif") %>"> &nbsp; <%= title %></bbUI:titleBar>
		<br>
				<%
				// added toolbar for 4.4
				boolean openQuestionmarkInNewWindow = true;
				String targetWin = openQuestionmarkInNewWindow ? "PerceptionWin" : "_self";
				String lauchRecallUrl = openQuestionmarkInNewWindow ? "javascript:window.close();" : recallUrl;
				String qmLinkText = BbUtils.getCourseString( "qm-admin-link.title", locale );
				%>
				<bbUI:actionBar maxItems="3" >
					<bbUI:actionItem 	href="<%= BbUtils.getCleverUrl("LaunchEnterpriseManager", course, lauchRecallUrl) + "\\\" target=\\\"" + targetWin + "\\\"" %>" 
										imgUrl="<%= BbUtils.getFullPath("images/web_16.gif") %>" 
										title="<%= qmLinkText %>"/>
				
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
						<bbUI:actionItem 	href="<%= upUrl %>" 
											imgUrl="<%= BbUtils.getFullPath("images/remov_16.gif") %>" 
											title="<%= WebUtils.parseHtmlValue( previousFolder ) %>"/>
						<%
					}
					%>
				</bbUI:actionBar>
		<br>		
		<%

		// select assessement 

		try{
			AssessmentTreeItemLoader treeLoader = PerceptionServiceManager.getAssessmentTreeItemLoader();
			BbList branches;
			if( parent_id == null ){
				branches = (BbList) treeLoader.getAssessmentTreeByAdministratorId( administrator.getUserId(), false ); // should be true
			}else{
				branches = (BbList) treeLoader.getAssessmentTreeByAdministratorId( administrator.getUserId(), parent_id, false ); // should be true
			}

			String searchText = request.getParameter("search");
			if( !BbUtils.isNullOrEmpty(searchText) ){
				branches = branches.getFilteredSubList( new AssessmentTreeItemFilter(searchText) );
			}


			%>
			<table border="0" cellpadding="0" cellspacing="2">
			<%
			

			String searchUrl = getSearchUrl( request.getQueryString() );
			%>
			<form name="addForm" method="POST" action="<%= searchUrl %>" >
				<bbUI:search isShowAdvanced="false" >
					Search:  <input type="text" name="search" value="<%= WebUtils.parseFormValue(searchText) %>" >
					<input type="image" src="/images/ci/listbtns/search_off.gif" alt="Submit">
				</bbUI:search>
			</form>
			<%			

			if( branches == null || branches.size() == 0 ){
				String errorMessage = BbUtils.getCourseString( "no-assessments-found.message", locale );
				%>
				<tr>
					<td colspan="2"><B><font color="#FF0000"><%=errorMessage%></font></B></td>
				</tr>
				<%
			}else{


				%>		
				
				<bbUI:list 	collection="<%= branches %>"  
					objectId="treeItem" 
					className="com.qm.bb6.perception.data.AssessmentTreeItem" 
					sortUrl="<%= BbUtils.getFullPath( "CreateAssessment" ) + BbUtils.getSortUrl( request ) %>"
					description="<%= BbUtils.getSafeCourseString( "treeitem-collection.description", locale ) %>"
					>

					<%

					boolean isFolder = treeItem.getType() == AssessmentTreeItem.ASSESSMENT_FOLDER;

					%>
					<bbUI:listElement label="Name" name="name" comparator="<%= nameComparator %>" width="350">
						&nbsp;
						<%
						if( isFolder ){
							String folderUrl = getFolderUrl( request.getQueryString(), treeItem.getItemId(), parent_id, up_id );
							%><a href="<%=folderUrl%>"><%
						}
						%>
						<%= WebUtils.parseHtmlValue( treeItem.getName() ) %>
						<% 
						if( isFolder ){
							%></a><%
						}
						%>
					</bbUI:listElement>
					<bbUI:listElement label="Type" name="type" comparator="<%= typeComparator %>" width="150">
						&nbsp;
						<%
						if( isFolder ){
							%>Folder<%
						}else{
							%>Assessment<%
						}
						%>
					</bbUI:listElement>
					<bbUI:listElement label="" name="select">
						&nbsp;
						<%
						if( !isFolder ){
							%><bbUI:button type="INLINE" alt="select" 
								name="select" action="LINK" targetUrl="<%= getSelectAssessmentUrl(baseSelectAssessmentUrl,treeItem.getItemId()) %>"  /><%
						}
						%>
					</bbUI:listElement>

				</bbUI:list>
				<%
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
		
		
		<BR>
		<div align="right">
			<bbUI:button type="FORM_ACTION" alt="cancel" name="cancel" action="LINK" targetUrl="<%= recallUrl %>"/>
		</div>

		<perception:licenseInfo />

	</bbUI:coursePage>
</bbUI:docTemplate>
