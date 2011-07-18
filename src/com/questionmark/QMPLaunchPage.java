package com.questionmark;

import javax.servlet.http.HttpServletRequest;
import java.rmi.RemoteException;

import blackboard.data.course.CourseMembership;
import blackboard.platform.context.Context;

public class QMPLaunchPage extends QMPCourseContext {

	public String assessment_id = null;
	public String scheduleURL = null;
	
	public QMPLaunchPage(HttpServletRequest request, Context ctx) {
		super(request, ctx);
		assessment_id=request.getParameter("AID");
		if(assessment_id == null) {
			Fail("No Content","This page is no longer supported by the Questionmark Perception Connector.");
/*		} else if (userRole == CourseMembership.Role.STUDENT) {
			System.out.println("STUDENT found: userRole.equals(GUEST) ....");			
			if (userRole.equals(CourseMembership.Role.NONE)) {
				System.out.println("...true");
			} else {
				System.out.println("...false");				
			}
*/		} else if (! userRole.equals(CourseMembership.Role.GUEST)) {
			try {
				scheduleURL = qmwise.getStub().getAccessAssessment(assessment_id,user.getUserName(),
					"", //participant details
					course.getBatchUid() //group name
					);
				if (scheduleURL == null) {
					Fail("No Access to Assessment","Unexpected error: GetAccessAssessment returned null");
				}
			} catch(RemoteException e) {
				QMWiseException qe = new QMWiseException(e);
				if(qe.getQMErrorCode() == 1301){
					Fail("Assessment Not Found",
							"This assessment can no longer be accessed, it may have been removed from Perception. "+e.getMessage());
				} else {
					Fail("No Access to Assessment",e.getMessage());
				}
				System.out.println("Assessment Not Found: "+e.getMessage());
			}
		} else {
			System.out.println("User found with course role: "+userRole.toString());
			Fail("Course Role","You do not have permission to see the launch page.");
		}
	}

}
