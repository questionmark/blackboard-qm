package com.questionmark;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.questionmark.QMWISe.Assessment;
import com.questionmark.QMWISe.ScheduleV42;

import blackboard.data.content.Content;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.content.ContentDbLoader;
import blackboard.platform.context.Context;
import blackboard.platform.plugin.PlugInException;
import blackboard.platform.plugin.PlugInUtil;

public class QMPContentEdit extends QMPCourseContext {

	public String content_id = null;
	public String parent_id = null;
	public ContentDbLoader courseDocumentLoader = null;
	public Content courseDoc=null;
	public Assessment[] assessmentList=null;
	public String schedule_name = null;
	public String schedule_description = null;
	public ScheduleV42 schedule=null;
	public int nSchedules=0;
	
	public QMPContentEdit(HttpServletRequest request, Context ctx, HttpServletResponse response) {
		super(request, ctx);
		content_id=request.getParameter("content_id");
		try {
			Id contentId = Id.generateId(Content.DATA_TYPE, content_id);
			courseDocumentLoader = ContentDbLoader.Default.getInstance();
			courseDoc = courseDocumentLoader.loadById( contentId ); 
			// can now query this...
			parent_id = courseDoc.getParentId().toExternalString();				
			schedule_name = courseDoc.getTitle();		
			schedule_description = courseDoc.getBody().getText();
			if (isAdministrator && PlugInUtil.authorizeForCourseControlPanelContent(request,response)) {
				if (Synchronize()) {
					System.out.println("User Synchronized OK!  UserID="+userID);
					Vector<ScheduleV42> schedules=GroupSchedules(schedule_name);
					assessmentList=GetAssessments();
					nSchedules=schedules.size();
					if (nSchedules<1)
						Fail("Missing Schedule","This schedule cannot be edited as it is missing in Perception");
					schedule=schedules.get(0);
				}
			} else {
				Fail("Access Denied", "This page is not available to your course role.");
			}
		} catch (QMWiseException e) {
			FailQMWISe(e);
		} catch (PersistenceException e) {
			Fail("Unexpected PersistenceException",e.getMessage());
		} catch (PlugInException e) {
			Fail("Unexpected PlugInException",e.getMessage());
		}
	}

}
