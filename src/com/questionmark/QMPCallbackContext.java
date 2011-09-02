package com.questionmark;

import javax.servlet.http.HttpServletRequest;

import blackboard.data.ValidationException;
import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.data.gradebook.Lineitem;
import blackboard.persist.BbPersistenceManager;
import blackboard.persist.Id;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.persist.gradebook.LineitemDbLoader;
import blackboard.persist.gradebook.LineitemDbPersister;
import blackboard.persist.user.UserDbLoader;
import blackboard.platform.context.Context;

public class QMPCallbackContext extends QMPCourseContext {

	public String scheduleName=null;
	public float scoreMax=0;
	
	public QMPCallbackContext(HttpServletRequest request, Context ctx) {
		super(request, ctx, "Participant_Name");
		if (failTitle!=null)
			return;
		if (userRole.equals(CourseMembership.Role.GUEST) || isAdministrator) {
			Fail("Perception callback","ignored call back for non-student role");
			return;
		}
		scheduleName=request.getParameter("bb_schedulename");
		scoreMax=new Float(request.getParameter("Score_Max")).floatValue();
		String content_id=QMPContentItem.ExtractContentId(scheduleName);
		try {
			if (Synchronize()) {
				if (content_id.isEmpty()) {
					// old style logic for a quick schedule or unconverted 8.0/9.0 item
					LineitemDbLoader lineitemLoader = (LineitemDbLoader) bbPm.getLoader(LineitemDbLoader.TYPE);
					Lineitem lineitem = (Lineitem) lineitemLoader.loadByCourseIdAndLineitemName(courseIdObject,scheduleName).get(0);
					LineitemDbPersister lineitemdbpersister = (LineitemDbPersister) bbPm.getPersister(LineitemDbPersister.TYPE);
					// check if points possible is zero -- if so we need to update it
					if(lineitem.getPointsPossible() == 0f) {
						lineitem.setPointsPossible(scoreMax);
						lineitem.validate();
						lineitemdbpersister.persist(lineitem);
					}
					QMPContentItem.UpdateGradebook(this,lineitem,null,null,scoreMax,request.getParameter("Score_Percentage"),
							request.getParameter("Score_Attained"));
				} else {
					// new logic for a known content item
					QMPContentItem contentItem=new QMPContentItem(this,content_id,null);
					contentItem.UpdateGradebook(scoreMax,request.getParameter("Score_Percentage"),
							request.getParameter("Score_Attained"));
				}		
			}
		} catch (ValidationException e) {
			Fail("Unexpected ValidationException",e.getMessage());
		} catch (QMWiseException e) {
			FailQMWISe(e);
		} catch (PersistenceException e) {
			Fail("Unexpected PersistenceException",e.getMessage());
		}
	}

}
