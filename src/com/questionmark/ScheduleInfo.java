package com.questionmark;

import java.rmi.RemoteException;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;

import com.questionmark.QMWISe.Parameter;
import com.questionmark.QMWISe.ScheduleV42;

public class ScheduleInfo {

	public ScheduleV42 schedule=null;
	public boolean active=false;
	public String errorMsg=null;
	public String launchURL=null;
	public String contentName=null;
	
	public ScheduleInfo (QMPCourseContext ctx, ScheduleV42 schedule, String contentName, boolean tryOut) {
		this.schedule=schedule;
		this.contentName=contentName;
		if (tryOut)
			TryOutLink(ctx);
		else
			ScheduleLink(ctx);			
	}
	
	
	public String DisplayName() {
		if (errorMsg != null)
			return errorMsg;
		else if (contentName!=null)
			return contentName;
		else
			return schedule.getSchedule_Name();
	}

	
	public static class SortComparator implements Comparator<ScheduleInfo> {
		public int compare(ScheduleInfo a, ScheduleInfo b) {
			return a.DisplayName().toLowerCase().compareTo(b.DisplayName().toLowerCase());
		}

		public boolean equals(ScheduleInfo a, ScheduleInfo b) {
			return a.DisplayName().toLowerCase().equals(b.DisplayName().toLowerCase());
		}
	}

	
	public void TryOutLink (QMPCourseContext ctx) {
		Long now = new Date().getTime();
		QMWise q=null;
		try {
			q=QMWise.connect();
			Long schedule_start = schedule.readSchedule_Starts_asCalendar().getTime().getTime();
			Long schedule_stop = schedule.readSchedule_Stops_asCalendar().getTime().getTime();
			if (schedule.isRestrict_Times() && (
				schedule_start > now 
				//not started yet
				|| (schedule_stop >= 0 && schedule_stop < now))) {
				// stop time is set (not 0001 AD) && already finished
				active=false;
			} else {
				active=true;
			}
			launchURL = q.stub.getAccessAssessment(schedule.getAssessment_ID(),
					ctx.user.getUserName(),"", //participant details
					ctx.course.getCourseId()); //group name
		} catch (RemoteException e) {
			launchURL=null;
			QMWiseException qe = new QMWiseException(e);
			if(qe.getQMErrorCode() == 1301) {
				// Assessment not found
				errorMsg="ERROR: Assessment no longer available";
			} else {
				errorMsg="ERROR: Communication error, QMWISE "+qe.getQMErrorCode().toString();
			}
		} finally {
			QMWise.close(q);
		}
	}

	
	public void ScheduleLink (QMPCourseContext ctx) {
		Long now = new Date().getTime();
		QMWise q=null;
		try {
			q=QMWise.connect();
			active=true;
			launchURL = null;
//			if (schedule.isRestrict_Attempts() && schedule.getMax_Attempts()<1) {
//				active=false;
//			}
			Long schedule_start = schedule.readSchedule_Starts_asCalendar().getTime().getTime();
			Long schedule_stop = schedule.readSchedule_Stops_asCalendar().getTime().getTime();
			if (schedule.isRestrict_Times() && (
				schedule_start > now 
				//not started yet
				|| (schedule_stop >= 0 && schedule_stop < now))) {
				// stop time is set (not 0001 AD) && already finished
				active=false;
			}
			if (active) {
				String nameFix=schedule.getSchedule_Name();
				nameFix=QMPContentItem.FixScheduleName(nameFix);
				Parameter[] parameters = {
						new Parameter("bb_schedulename", nameFix),
						new Parameter("bb_scheduleid", new Integer(
							schedule.getSchedule_ID()).toString()),
						new Parameter("bb_courseid", ctx.course.getCourseId())
					};
				// First step, get the try out link to force a QMWISe error for a missing assessment
				String dummy = q.stub.getAccessAssessment(schedule.getAssessment_ID(),
						ctx.user.getUserName(),"", //participant details
						ctx.course.getCourseId()); //group name
				// No exception so the launch URL should work...
				launchURL = q.stub.getAccessScheduleNotify(
							new Integer(schedule.getSchedule_ID()).toString(),
							ctx.user.getUserName(),
							ctx.request.getScheme() + "://" + ctx.request.getServerName() 
							+ ctx.request.getContextPath() + "/links/callback.jsp",	
							"blackboard.pip", parameters);
			}
		} catch (RemoteException e) {
			launchURL = null;
			QMWiseException qe = new QMWiseException(e);
			if(qe.getQMErrorCode() == 1301) {
				// Assessment not found
				errorMsg="ERROR: Assessment no longer available";
			} else if (qe.getQMErrorCode() == 2040) {
				errorMsg="No more attempts remaining";
			} else {
				errorMsg="ERROR: Communication error, QMWISE "+qe.getQMErrorCode().toString();
			}
		} finally {
			QMWise.close(q);
		}
	}
	
}
