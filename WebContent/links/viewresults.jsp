<%@ page language="java" pageEncoding="UTF-8"
	import="
		java.util.*,
		java.text.*,
		java.net.*,
		blackboard.platform.*,
		blackboard.base.*,
		blackboard.platform.session.*,
		blackboard.data.user.*,
		blackboard.platform.persistence.PersistenceServiceFactory,
		blackboard.persist.* ,
		blackboard.persist.user.*,
		blackboard.data.course.*,
		blackboard.persist.course.*,
		org.apache.axis.*,
		org.apache.commons.lang.StringEscapeUtils,
		java.rmi.RemoteException,
		javax.xml.namespace.QName,
		com.questionmark.*,
		com.questionmark.QMWISe.*"
%>

<%@ taglib uri="/bbData" prefix="bbData"%>
<%@ taglib uri="/bbNG" prefix="bbNG"%>

<bbNG:learningSystemPage ctxId="ctx" title="Questionmark Perception connector">

	<% QMPResultsContext rc=new QMPResultsContext(request,ctx); %>
	<bbNG:pageHeader>
		<bbNG:pageTitleBar iconUrl='<%=rc.path+"/images/qm.gif"%>'
			title='Questionmark Perception Results' />
	</bbNG:pageHeader>	

	<%
		if (rc.failTitle == null) {
			DateFormat pdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			if (rc.reportLink!=null) {
				response.sendRedirect(rc.reportLink);
			} else {				
	%>

	<bbNG:actionControlBar showWhenEmpty="true">	
		<bbNG:actionButton url='<%=rc.path+"/links/main.jsp?course_id="+rc.courseId %>' title="Control Panel"/>		
		<%
				if(rc.pb.getProperty("perception.singlesignon") != null) {
		%>
		<bbNG:actionButton url='<%=rc.path+"/links/enterprisemanager.jsp?course_id="+rc.courseId %>' 
			title="Log in to Enterprise Manager" target="_blank"/>
		<%
				}
		%>	
	</bbNG:actionControlBar>

	<h1 id="Results">Assessment Results</h1>
			
	<bbNG:inventoryList collection="<%=rc.resultList %>" objectVar="r"
		className="com.questionmark.QMWISe.Result" description="Assessment Results"
		emptyMsg="No results available" showAll="false">
		<!--<td><%=r.getAssessment_ID()%></td>-->
		<!--<td><%=r.getSchedule_Name()%></td> Requires QMWISe fix-->
		<bbNG:listElement name="participant" label="Participant" isRowHeader="true">
			<%=r.getParticipant()+" ("+r.getSpecial_1()+" "+r.getSpecial_2()+")" %>
		</bbNG:listElement>
		<bbNG:listElement name="score" label="Score">
			<%=!r.isStill_Going() ? r.getTotal_Score() + "/" + r.getMax_Score() + " (" + r.getPercentage_Score() + "%)" : "Incomplete" %>
		</bbNG:listElement>
		<bbNG:listElement name="time_taken" label="Time taken">
			<%=!r.isStill_Going() ? r.getTime_Taken() + "s" : "" %>
		</bbNG:listElement>
		<bbNG:listElement name="end_time" label="When Finished">
			<%=!r.isStill_Going() ? pdf.parse(r.getWhen_Finished()).toString() : "Unfinished" %>
		</bbNG:listElement>
		<bbNG:listElement name="link" label="Coaching Report">
			<a href='<%="viewresults.jsp?course_id="+rc.courseId+"&result_id="+r.getResult_ID() %>' target="_blank">View Now</a>
		</bbNG:listElement>
	</bbNG:inventoryList>
	
	<%			
			}
		} else {
	%>

	<bbNG:receipt type="FAIL" title="<%=rc.failTitle %>">
		<%=rc.failMsg %>
	</bbNG:receipt>

	<%
		} //End of other view
	%>
</bbNG:learningSystemPage>


