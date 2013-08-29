package com.questionmark;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.questionmark.QMWISe.Result;
import com.questionmark.QMWISe.ScheduleV42;

import blackboard.persist.PersistenceException;
import blackboard.platform.context.Context;

public class QMPResultsContext extends QMPCourseContext {

	public List<Result> resultList=null;
	public Result[] results=null;
	public String resultID=null;
	public String reportLink=null;
	
	public QMPResultsContext(HttpServletRequest request, Context ctx) {
		super(request, ctx);
		if (failTitle!=null)
			return;
		resultID=request.getParameter("result_id");
		QMWise q=null;
		try {
			q=QMWise.connect();
			if (Synchronize()) {
				Log("User Synchronized OK!  UserID="+userID+"("+courseUser.getUserName()+")");
				if (isAdministrator) {
					if (resultID==null) {
						results = q.stub.getResultListByGroup(course.getCourseId());
						Arrays.sort(results, new ResultComparator());
						resultList=Arrays.asList(results);
					} else {
						reportLink=q.stub.getAccessReport(resultID);
					}
				} else {
					Fail("Questionmark Perception","This page has been hidden by the course instructor");
				}
			}
		} catch (RemoteException e) {
			QMWiseException qe=new QMWiseException(e);
			FailQMWISe(qe);
		} finally {
			QMWise.close(q);
		}
	}

}
