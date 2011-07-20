package com.questionmark;

import javax.servlet.http.HttpServletRequest;

import blackboard.platform.context.Context;

public class QMPSysAdminContext extends QMPContext {

	public QMPSysAdminContext(HttpServletRequest request, Context ctx) {
		super(request, ctx);
		if (! sysAdmin) {
			Fail("System Administration","Your role is not authorized to view this page");
		}
	}

	public void UpdateProperties() {
		if (sysAdmin) {
			try {
				pb.setProperties(request);
				QMWise.reset();
			} catch(Exception e) {
				Fail("Unexpected Error","Failed to updated settings: "+e.getMessage());
			}
		}
	}
}
