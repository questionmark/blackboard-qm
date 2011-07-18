package com.questionmark;

import javax.servlet.http.HttpServletRequest;

import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.persist.Id;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.platform.context.Context;
import blackboard.platform.persistence.PersistenceServiceFactory;
import blackboard.platform.plugin.PlugInException;

public class QMPCourseContext extends QMPContext {

	public String courseId = null;
	public ConfigFileReader configReader = null;
	public CourseSettings courseSettings = null;
	public CourseDbLoader courseLoader = null;
	public CourseMembershipDbLoader crsMembershipLoader = null;
	public Course course = null;
	public CourseMembership crsMembership = null;
	public CourseMembership.Role userRole=CourseMembership.Role.GUEST;
	
	public QMPCourseContext(HttpServletRequest request, Context ctx) {
		super(request, ctx);
		courseId = request.getParameter("course_id");
		try {
			if(courseId != null) {
				configReader = new ConfigFileReader(courseId);
				// load the courseSettings file too...
				courseSettings = new CourseSettings(courseId);
				// Generate a persistence framework course Id to be used for loading the course
				Id courseIdObject = bbPm.generateId(Course.DATA_TYPE, courseId);
				courseLoader = (CourseDbLoader) bbPm.getLoader(CourseDbLoader.TYPE);
				course = courseLoader.loadById(courseIdObject);
				if (course != null) {
					// get the membership data to determine the User's Role
					crsMembershipLoader = (CourseMembershipDbLoader) bbPm.getLoader(CourseMembershipDbLoader.TYPE);
					try {
						Id sessionUserId = user.getId();				
						crsMembership = crsMembershipLoader.loadByCourseAndUserId(courseIdObject, sessionUserId);
						userRole=crsMembership.getRole();
						if ( ! userRole.equals(CourseMembership.Role.INSTRUCTOR)
								&& ! userRole.equals(CourseMembership.Role.TEACHING_ASSISTANT)
								&& !userRole.equals(CourseMembership.Role.STUDENT)) {
							Fail("Course Role","You do not have permission to see this page.");
							userRole=CourseMembership.Role.GUEST;
						}
					} catch (KeyNotFoundException e) {
						// There is no membership record.
						Fail("Course Role","No role found for the current user in this course "+e.getMessage());
					}
				} else {
					FailCourse();
				}
			} else {
				FailCourse();
			}
		} catch (PlugInException e) {
			Fail("Unexpected PlugInException",e.getMessage());
		} catch (PersistenceException e) {
			Fail("Unexpected PersistenceException",e.getMessage());
		}
	}

	public void FailCourse() {
		Fail("Course Not Found","There was no course associated with this request");
	}
	

}
