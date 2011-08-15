package com.questionmark;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.questionmark.QMWISe.Assessment;
import com.questionmark.QMWISe.ScheduleV42;

import blackboard.data.ValidationException;
import blackboard.data.content.CourseDocument;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.platform.context.Context;
import blackboard.platform.plugin.PlugInUtil;
import blackboard.platform.plugin.PlugInException;

public class QMPContentCreator extends QMPCourseContext {

	public Assessment[] assessmentList=null;
	public SelectAssessmentItem[] selectAssessmentList=null;
	public QMPContentItem contentItem = null;
	public String title = "Questionmark Perception Schedule";
	
	public QMPContentCreator(HttpServletRequest request, Context ctx, HttpServletResponse response) {
		super(request, ctx);
		try {
			if (isAdministrator && PlugInUtil.authorizeForCourseControlPanelContent(request,response)) {
				if (Synchronize()) {				
					System.out.println("User Synchronized OK!  UserID="+userID);
				}
			} else {
				Fail("Access Denied", "This page is not available to your course role.");
			}
		} catch (QMWiseException e) {
			FailQMWISe(e);
		} catch (PlugInException e) {
			Fail("Unexpected PlugInException",e.getMessage());
		}
	}
	
	
	public void NewForm() {
		try {
			String parent_id = request.getParameter("content_id");
			contentItem=new QMPContentItem(this,null,parent_id);
			assessmentList=GetAssessments();		
			selectAssessmentList=GetAssessmentTree(null,null);
		} catch (PersistenceException e) {
			Fail("Unexpected PersistenceException",e.getMessage());
		} catch (ValidationException e) {
			Fail("Unexpected ValidationException",e.getMessage());
		} catch (QMWiseException e) {
			FailQMWISe(e);
		}
	}
	
	
	public void ProcessNewForm() {
		try {
			if (ValidateNewForm()) {
				contentItem=new QMPContentItem(this,this.request);
				contentItem.CreateNew();
			}
		} catch (PersistenceException e) {
			Fail("Unexpected PersistenceException",e.getMessage());
		}
	}
	
	
	public boolean ValidateNewForm() {
		String parent_id = request.getParameter("parent_id");
		if (parent_id == null) {
			Fail("Unexpected Error","parent_id required");
		}
		String schedule_description = request.getParameter("schedule_text_area");
		if (schedule_description.length() > 4000) {
			Fail("Form Validation Error","Description must not exceed more than 4000 characters");
			return false;
		}
		String limit=request.getParameter("limit");
		if (limit!=null && !limit.matches("[1-9][0-9]*")) {
			Fail("Form Validation Error","Limit for attempts must be an integer");
			return false;
		}
		if (!ValidateDates())
			return false;
		return true;
	}

	
	public boolean ValidateDates() {
		if (request.getParameter("set_access_period")!=null) {
			try {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Calendar startCal=Calendar.getInstance();
				Calendar endCal=Calendar.getInstance();
				String startString=request.getParameter("start_0");
				if (startString.length()>=10) {
					startCal.setTime(df.parse(startString.substring(0,10)));
				} else {
					// probably means "don't change this time"
					//	Log("Unexpected end string from datePicker: "+startString);
					startCal=contentItem.startdate;
				}
				startCal.set(Calendar.HOUR_OF_DAY, new Integer(request.getParameter("start_hour")).intValue());
				startCal.set(Calendar.MINUTE, new Integer(request.getParameter("start_minute")).intValue());
				String endString=request.getParameter("end_1");
				if (endString.length()>=10) {
					endCal.setTime(df.parse(endString.substring(0,10)));
				} else {
					//	Log("Unexpected end string from datePicker: "+endString);
					endCal=contentItem.enddate;
				}
				endCal.set(Calendar.HOUR_OF_DAY, new Integer(request.getParameter("end_hour")).intValue());
				endCal.set(Calendar.MINUTE, new Integer(request.getParameter("end_minute")).intValue());
				if (endCal.before(startCal) || endCal.equals(startCal)) {
					Fail("Form Validation Error","The end date must be after the start date");
					return false;
				}
			} catch (ParseException e) {
				Fail("Unexpected ParseException","While parsing start or end date");
				return false;
			}
		}
		return true;
	}
	
	public void EditForm() {
		try {
			String content_id = request.getParameter("content_id");
			contentItem=new QMPContentItem(this,content_id,null);
			title = contentItem.name;
			// we don't need the assessmentList
			// assessmentList=GetAssessments();
			// selectAssessmentList=GetAssessmentTree(null,null);
			if (contentItem.schedules.size()<1)
				Fail("Missing Schedule","This schedule cannot be edited as it is missing in Perception");
		} catch (PersistenceException e) {
			Fail("Unexpected PersistenceException",e.getMessage());
		} catch (ValidationException e) {
			Fail("Unexpected ValidationException",e.getMessage());
		} catch (QMWiseException e) {
			FailQMWISe(e);
		}
	}
	

	public void ProcessEditForm() {
		try {
			String content_id = request.getParameter("content_id");
			contentItem=new QMPContentItem(this,content_id,null);
			if (ValidateEditForm()) {
				title = contentItem.name;
				contentItem.Update(request);
			}
		} catch (PersistenceException e) {
			Fail("Unexpected PersistenceException",e.getMessage());
		} catch (ValidationException e) {
			Fail("Unexpected ValidationException",e.getMessage());
		} catch (QMWiseException e) {
			FailQMWISe(e);
		}
	}
	
	
	public boolean ValidateEditForm() {
		if (!ValidateNewForm())
			return false;
		String new_schedule_name = request.getParameter("new_schedule_name");
		if (new_schedule_name.length() == 0 || new_schedule_name.length() > 50) {
			Fail("Form Validation Error","Schedule name must not be empty or longer than 50 characters");
			return false;
		}
		return true;
	}

	
	public String ProcessDelete() {
		String result=null;
		try {
			String content_id = request.getParameter("content_id");
			contentItem=new QMPContentItem(this,content_id,null);
			title = contentItem.name;
			result=contentItem.Delete();
		} catch (PersistenceException e) {
			Fail("Unexpected PersistenceException",e.getMessage());
		} catch (ValidationException e) {
			Fail("Unexpected ValidationException",e.getMessage());
		} catch (QMWiseException e) {
			FailQMWISe(e);
		}
		return result;
	}


	public void ProcessQuickForm() {
		try {
			if (ValidateQuickForm()) {
				contentItem=new QMPContentItem(this,request);
				title = contentItem.name;
				contentItem.QuickCreate();
			}
		} catch (PersistenceException e) {
			Fail("Unexpected PersistenceException",e.getMessage());
		}
	}
	
	
	public boolean ValidateQuickForm() {
		String limit=request.getParameter("limit");
		if (limit!=null && !limit.matches("[1-9][0-9]*")) {
			Fail("Form Validation Error","Limit for attempts must be an integer");
			return false;
		}
		if (!ValidateDates())
			return false;
		return true;
	}
}


