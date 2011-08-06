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
	public QMPContentItem contentItem = null;
	public String parent_id = null;
	public Assessment[] assessmentList=null;
	public String title = "Questionmark Perception Schedule";
	
	public QMPContentView(HttpServletRequest request, Context ctx) {
		super(request, ctx);
		content_id=request.getParameter("content_id");
		try {
			if (Synchronize()) {
				System.out.println("User Synchronized OK!  UserID="+userID);
				contentItem=new QMPContentItem(this,content_id,null);
				title=contentItem.name;
				// can now query this...
				parent_id = contentItem.courseDoc.getParentId().toExternalString();				
				Vector<ScheduleV42> schedules=GroupSchedules(contentItem.name);
				if (isAdministrator) {
					GetScheduleInfo(schedules);
					assessmentList=GetAssessments();
				} else {
					if (!contentItem.available)
						Fail("Assessment Not Available","This assessment is not currently available");
					else {
						GetScheduleInfo(schedules);
						if (schedules.size()==0)
							Fail("Assessment Not Found","This assessment is no longer available (no matching schedule)");
					}
				}
			}
		} catch (QMWiseException e) {
			FailQMWISe(e);
		} catch (PersistenceException e) {
			Fail("Unexpected PersistenceException",e.getMessage());
		}
	}

}
