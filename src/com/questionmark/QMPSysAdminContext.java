package com.questionmark;

import javax.servlet.http.HttpServletRequest;

import com.questionmark.QMWISe.Version2;

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
			} catch(Exception e) {
				Fail("Unexpected Error","Failed to update settings: "+e.getMessage());
			}
		}
	}


	public Version2 Test() {
		QMWise q=null;
		try {
			q=QMWise.connect();
			Version2 version=q.getVersion();
			return version;
		} catch (QMWiseException e) {
			Fail("QMWISe Exception",e.getMessage());
		} finally {
			QMWise.close(q);
		}
		return null;
	}


}
