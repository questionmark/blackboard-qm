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
		resultID=request.getParameter("result_id");
		try {
			if (Synchronize()) {
				System.out.println("User Synchronized OK!  UserID="+userID);
				if (isAdministrator) {
					if (resultID==null) {
						results = stub.getResultListByGroup(course.getBatchUid());
						Arrays.sort(results, new ResultComparator());
						resultList=Arrays.asList(results);
					} else {
						reportLink=stub.getAccessReport(resultID);
					}
				} else {
					Fail("Questionmark Perception","This page has been hidden by the course instructor");
				}
			}
		} catch (RemoteException e) {
			QMWiseException qe=new QMWiseException(e);
			FailQMWISe(qe);
		}
	}

}
