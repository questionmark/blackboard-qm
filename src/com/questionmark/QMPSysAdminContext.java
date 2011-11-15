package com.questionmark;

import javax.servlet.http.HttpServletRequest;

import com.questionmark.QMWISe.Version2;

import blackboard.platform.context.Context;

public class QMPSysAdminContext extends QMPContext {

	public QMPSysAdminContext(HttpServletRequest request, Context ctx) {
		super(request, ctx);
		if (stub == null) {
			// ignore errors creating the QMWISe object because we may
			// not have configured it yet.
			failTitle=null;
		}
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
				Fail("Unexpected Error","Failed to update settings: "+e.getMessage());
			}
		}
	}


	public Version2 Test() {
		try {
			Version2 version=qmwise.getVersion();
			return version;
		} catch (QMWiseException e) {
			Fail("QMWISe Exception",e.getMessage());
		}
		return null;
	}


}
