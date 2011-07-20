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
	public Vector<ScheduleInfo> scheduleInfo=null;
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

	
	public Vector<ScheduleV42> GroupSchedules(String filter) throws QMWiseException {
		Vector<ScheduleV42> schedules = new Vector<ScheduleV42>();
		try {
			// Assumption:
			// number of courses x number of schedules is less than...
			// max(number of students in a course) x number of limited attempt schedules in that course
			// Either way we would prefer a method that also filtered by group
			ScheduleV42[] phantomSchedules = null;
			phantomSchedules = stub.getScheduleListByParticipantV42(new Integer(phantomID).intValue());
			for(int i = 0; i < phantomSchedules.length; i++) {
				if(phantomSchedules[i].getGroup_ID() == new Integer(groupID).intValue())
					if (filter == null || filter.equals(phantomSchedules[i].getSchedule_Name()))
						schedules.add(phantomSchedules[i]);
			}
			ScheduleV42[] groupSchedules = null;
			// Not sure how well documented using 0 is here
			// returns approx (number of courses x number of group schedules) records
			groupSchedules=stub.getScheduleListByParticipantV42(0);
			for(int i = 0; i < groupSchedules.length; i++)
				if(groupSchedules[i].getGroup_ID() == new Integer(groupID).intValue())
					if (filter == null || filter.equals(groupSchedules[i].getSchedule_Name()))
						schedules.add(groupSchedules[i]);
		} catch (RemoteException e) {
			throw new QMWiseException(e);
		}
		return schedules;
	}
	
	
	public void GetScheduleInfo(Vector<ScheduleV42> schedules) {
		scheduleInfo = new Vector<ScheduleInfo>();
		for(int i = 0; i < schedules.size(); i++) {
			scheduleInfo.add(new ScheduleInfo(this,schedules.get(i),isAdministrator));
		}
	}
	
}