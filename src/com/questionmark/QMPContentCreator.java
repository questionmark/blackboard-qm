package com.questionmark;

import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.questionmark.QMWISe.Assessment;
import com.questionmark.QMWISe.ScheduleV42;

import blackboard.platform.context.Context;
import blackboard.platform.plugin.PlugInUtil;
import blackboard.platform.plugin.PlugInException;

public class QMPContentCreator extends QMPCourseContext {

	public String parent_id=null;
	public Assessment[] assessmentList=null;
	
	public QMPContentCreator(HttpServletRequest request, Context ctx, HttpServletResponse response) {
		super(request, ctx);
		parent_id = request.getParameter("content_id");	
		try {
			if (isAdministrator && PlugInUtil.authorizeForCourseControlPanelContent(request,response)) {
				if (Synchronize()) {				
					System.out.println("User Synchronized OK!  UserID="+userID);
					assessmentList=GetAssessments();
				}
			} else {
				Fail("Access Denied", "This page is not available to your course role.");
			}
		} catch (QMWiseException e) {
			FailQMWISe(e);
		} catch (PlugInException e) {
			Fail("Unexpected PlugInException",e.getMessage());
		}
	}
}


