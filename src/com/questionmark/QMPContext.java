package com.questionmark;

import javax.servlet.http.HttpServletRequest;

import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.data.user.User;
import blackboard.persist.BbPersistenceManager;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.platform.persistence.PersistenceServiceFactory;
import blackboard.platform.plugin.PlugInException;
import blackboard.persist.Id;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.platform.context.Context;

public class QMPContext {

	public enum PageType {
		tool,
		controlPanel,
		content
	}
	
	public HttpServletRequest request = null;
	public String path;
	public String basePath;
	public QMWise qmwise = null;
	public BbPersistenceManager bbPm = null;
	public User user = null;
	public Boolean sysAdmin = false;
	public PropertiesBean pb= null;
	public String failTitle = null;
	public String failText = null;
	
	public QMPContext(HttpServletRequest request, Context ctx ) {
		this.request = request;
		path = request.getContextPath();
		basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//Retrieve the Db persistence manager from the persistence service
		bbPm = PersistenceServiceFactory.getInstance().getDbPersistenceManager();
		user = ctx.getUser();
		sysAdmin = user.getSystemRole().equals(User.SystemRole.SYSTEM_ADMIN);
		try {
			// connect to QMWise
			qmwise = new QMWise();				
		} catch(Exception e) {
			QMWiseException qe = new QMWiseException(e);
			Fail("Error connecting to Perception server",qe.getMessage());
		}
		// Get our properties object
		pb = new PropertiesBean();
		//	return;
	}

	public void Fail(String title, String text) {
		if (failTitle == null) {
			failTitle=title;
			failText=text;
		}
	}
}
