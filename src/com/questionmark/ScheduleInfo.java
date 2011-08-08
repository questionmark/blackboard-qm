package com.questionmark;

import java.rmi.RemoteException;
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

	
	public void TryOutLink (QMPCourseContext ctx) {
		Long now = new Date().getTime();
		try {
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
			launchURL = ctx.stub.getAccessAssessment(schedule.getAssessment_ID(),
					ctx.user.getUserName(),"", //participant details
					ctx.course.getBatchUid()); //group name
		} catch (RemoteException e) {
			launchURL=null;
			QMWiseException qe = new QMWiseException(e);
			if(qe.getQMErrorCode() == 1301) {
				// Assessment not found
				errorMsg="ERROR: Assessment no longer available";
			} else {
				errorMsg="ERROR: Communication error, QMWISE "+qe.getQMErrorCode().toString();
			}
		}
	}

	
	public void ScheduleLink (QMPCourseContext ctx) {
		Long now = new Date().getTime();
		try {
			active=true;
			launchURL = null;
			if (schedule.isRestrict_Attempts() && schedule.getMax_Attempts()<1) {
				active=false;
			}
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
				Parameter[] parameters = {
						new Parameter("bb_schedulename", contentName==null?schedule.getSchedule_Name():contentName),
						new Parameter("bb_scheduleid", new Integer(
							schedule.getSchedule_ID()).toString()),
						new Parameter("bb_courseid", ctx.course.getBatchUid())
					};
				// First step, get the try out link to force a QMWISe error for a missing assessment
				String dummy = ctx.stub.getAccessAssessment(schedule.getAssessment_ID(),
						ctx.user.getUserName(),"", //participant details
						ctx.course.getBatchUid()); //group name
				// No exception so the launch URL should work...
				launchURL = ctx.stub.getAccessScheduleNotify(
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
			} else {
				errorMsg="ERROR: Communication error, QMWISE "+qe.getQMErrorCode().toString();
			}
		}
	}
	
}
