package com.questionmark;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.questionmark.QMWISe.Assessment;
import com.questionmark.QMWISe.ScheduleV42;

import blackboard.platform.context.Context;

public class QMPControlPanel extends QMPCourseContext {

	public String panelTitle="Questionmark Perception Control Panel";
	public String syncResult="";
	public boolean linkView=false;
	public Assessment[] assessmentList=null;
	
	public QMPControlPanel(HttpServletRequest request, Context ctx) {
		super(request, ctx);
		String schedule_name = request.getParameter("schedule_name");
		linkView=(schedule_name != null);
		try {
			if (Synchronize()) {
				System.out.println("User Synchronized OK!  UserID="+userID);
				if (isAdministrator) {
					String syncperiod = pb.getProperty("perception.syncperiod");
					String syncusers = pb.getProperty("perception.syncusers");
					if(syncperiod == null)
						syncperiod = "60";
					Long syncperiodms = new Long(syncperiod) * 60 * 1000;
					if (syncusers != null) {
						if(new Date().getTime() > configReader.getCourseSyncDate() + syncperiodms) {
							//synchronize course users
							System.out.println("Perception: course " + courseId + ": sync period expired...");
							syncResult=ForceSynchronization();
						}
					}
					System.out.println("Forced synchronized OK! "+syncResult.toString());
					Vector<ScheduleV42> schedules=GroupSchedules(schedule_name);
					GetScheduleInfo(schedules);
					assessmentList=GetAssessments();
				} else if (schedule_name != null) {
					panelTitle="Assessment Launch Page";
					Vector<ScheduleV42> schedules=GroupSchedules(schedule_name);
					if (schedules.size()==0)
						Fail("Assessment Not Found","This assessment is no longer available (no matching schedule)");
					else
						GetScheduleInfo(schedules);
				} else if (!courseSettings.getProperty("hide_schedules","0").equals("1")){
					panelTitle="Questionmark Perception";
					Vector<ScheduleV42> schedules=GroupSchedules(null);
					GetScheduleInfo(schedules);
				} else {
					Fail("Questionmark Perception","This page has been hidden by the course instructor");
				}
			}
		} catch (QMWiseException e) {
			FailQMWISe(e);
		}
	}

		
}