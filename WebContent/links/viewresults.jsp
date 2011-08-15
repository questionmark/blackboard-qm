<%@ page
	language="java"
	pageEncoding="UTF-8"
	import="
		java.util.*,
		java.text.*,
		blackboard.platform.*,
		blackboard.base.*,
		blackboard.platform.session.*,
		blackboard.platform.persistence.*,
		blackboard.data.user.*,
		blackboard.persist.* ,
		blackboard.persist.user.*,
		blackboard.data.course.*,
		blackboard.persist.course.*,
		org.apache.axis.*,
		java.rmi.RemoteException,
		javax.xml.namespace.QName,
		org.apache.commons.lang.StringEscapeUtils,
		com.questionmark.*,
		com.questionmark.QMWISe.*
	"
%>

<%@ taglib uri="/bbUI" prefix="bbUI" %> 
<%@ taglib uri="/bbData" prefix="bbData" %>

<bbData:context id="ctx">
	<%
	QMPResultsContext rc=new QMPResultsContext(request,ctx);
	if (rc.failTitle==null && rc.reportLink!=null) {
		response.sendRedirect(rc.reportLink);
	} else {
	%>
	<bbUI:docTemplate title="Questionmark Perception Results">
		<bbUI:coursePage>
		<%
		int resultsPerPage = 25;
		int resultPage= 0;
		int numPages=0;
		int listStart = 0;
		int listEnd = 0;
		if(request.getParameter("resultsPerPage") != null) {
			resultsPerPage = new Integer(request.getParameter("resultsPerPage")).intValue();
			if(resultsPerPage < 5) resultsPerPage = 5;
		}	
		if(request.getParameter("resultPage") != null) {
			resultPage=new Integer(request.getParameter("resultPage")).intValue();
			if (resultPage<0) resultPage=0;
		}
		listStart=resultPage*resultsPerPage;
		if (listStart<0) listStart=0;
		if (listStart>=rc.resultList.size())
			// something went wrong
			listStart=0;
		listEnd=listStart+resultsPerPage;
		if (listEnd>=rc.resultList.size()) listEnd=rc.resultList.size();
		resultPage=listStart/resultsPerPage;
		if (rc.resultList.size()>0)
			numPages=(rc.resultList.size()-1)/resultsPerPage+1;
		else
			numPages=0;	
		%>
			<bbUI:breadcrumbBar environment="COURSE" isContent="true">
				<bbUI:breadcrumb>View Results</bbUI:breadcrumb>
			</bbUI:breadcrumbBar>
		<%
			if (rc.failTitle == null) {
				DateFormat pdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		%>
			<bbUI:actionBar>
				<bbUI:actionItem title="Control Panel" href='<%=rc.path+"/links/main.jsp?course_id="+rc.courseId %>'
					imgUrl='<%=rc.path+"/images/qmsmall.gif" %>'/>
				<%
					if(rc.pb.getProperty("perception.singlesignon") != null) {
				%>
				<bbUI:actionItem title="Log in to Enterprise Manager" href='<%=rc.path+"/links/enterprisemanager.jsp?course_id="+rc.courseId %>'
					imgUrl='<%=rc.path+"/images/link.gif" %>' target="_blank"/>
				<%
					}
				%>
			</bbUI:actionBar>
	
		<h1 id="Results">Assessment Results</h1>
				
		<bbUI:list collection="<%=rc.resultList.subList(listStart,listEnd) %>" objectId="r"
			className="com.questionmark.QMWISe.Result" collectionLabel="Assessment Results">
			<!-- emptyMsg="No results available" showAll="false"  -->
			<!--<td><%=r.getAssessment_ID()%></td>-->
			<!--<td><%=r.getSchedule_Name()%></td> Requires QMWISe fix-->
			<bbUI:listElement name="participant" label="Participant">
				<%=r.getParticipant()+" ("+r.getSpecial_1()+" "+r.getSpecial_2()+")" %>
			</bbUI:listElement>
			<bbUI:listElement name="score" label="Score">
				<%=!r.isStill_Going() ? r.getTotal_Score() + "/" + r.getMax_Score() + " (" + r.getPercentage_Score() + "%)" : "Incomplete" %>
			</bbUI:listElement>
			<bbUI:listElement name="time_taken" label="Time taken">
				<%=!r.isStill_Going() ? r.getTime_Taken() + "s" : "" %>
			</bbUI:listElement>
			<bbUI:listElement name="end_time" label="When Finished">
				<%=!r.isStill_Going() ? pdf.parse(r.getWhen_Finished()).toString() : "Unfinished" %>
			</bbUI:listElement>
			<bbUI:listElement name="link" label="Coaching Report">
				<a href='<%="viewresults.jsp?course_id="+rc.courseId+"&result_id="+r.getResult_ID() %>' target="_blank">View Now</a>
			</bbUI:listElement>
		</bbUI:list>
	
		<p>	
			<%
			for(int i = 0; i<numPages; i++) {
				if(i*resultsPerPage==listStart) {
					%>
					<strong><%=i+1 %></strong>
					<%
				} else {
					%>
					<a href='<%=rc.path+"/links/viewresults.jsp?course_id="+rc.courseId+"&amp;resultPage="+i+
						"&amp;resultsPerPage="+resultsPerPage %>'><%=i+1 %></a>
					<%
				}
			}
			%>
		</p>
			
		<%			
			} else {
		%>
	
		<bbUI:receipt type="FAIL" title="<%=rc.failTitle %>">
			<%=rc.failMsg %>
		</bbUI:receipt>
	
		<%
			} //End of other view
		%>
		</bbUI:coursePage>
	</bbUI:docTemplate>
<%
	} //End of main page (not redirect)
%>
</bbData:context>

