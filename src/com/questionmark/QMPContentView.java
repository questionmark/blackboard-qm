package com.questionmark;

import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.questionmark.QMWISe.Assessment;
import com.questionmark.QMWISe.ScheduleV42;

import blackboard.data.content.Content;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.content.ContentDbLoader;
import blackboard.platform.context.Context;

public class QMPContentView extends QMPCourseContext {

	public String content_id = null;
	public String parent_id = null;
	public ContentDbLoader courseDocumentLoader = null;
	public Content courseDoc=null;
	public Assessment[] assessmentList=null;
	public String schedule_name = null;
	
	public QMPContentView(HttpServletRequest request, Context ctx) {
		super(request, ctx);
		content_id=request.getParameter("content_id");
		try {
			Id contentId = Id.generateId(Content.DATA_TYPE, content_id);
			courseDocumentLoader = ContentDbLoader.Default.getInstance();
			courseDoc = courseDocumentLoader.loadById( contentId ); 
			// can now query this...
			parent_id = courseDoc.getParentId().toExternalString();				
			schedule_name = courseDoc.getTitle();		
			String schedule_description = courseDoc.getBody().getText();
			if (Synchronize()) {
				System.out.println("User Synchronized OK!  UserID="+userID);
				Vector<ScheduleV42> schedules=GroupSchedules(schedule_name);
				if (isAdministrator) {
					GetScheduleInfo(schedules);
					assessmentList=GetAssessments();
				} else {
					GetScheduleInfo(schedules);
					if (schedules.size()==0)
						Fail("Assessment Not Found","This assessment is no longer available (no matching schedule)");
				}
			}
		} catch (QMWiseException e) {
			FailQMWISe(e);
		} catch (PersistenceException e) {
			Fail("Unexpected PersistenceException",e.getMessage());
		}
	}

}
