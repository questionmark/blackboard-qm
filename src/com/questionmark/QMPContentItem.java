package com.questionmark;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;

import blackboard.base.BbList;
import blackboard.base.FormattedText;
import blackboard.data.ValidationException;
import blackboard.data.content.Content;
import blackboard.data.content.CourseDocument;
import blackboard.data.gradebook.Lineitem;
import blackboard.persist.Id;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.persist.content.ContentDbLoader;
import blackboard.persist.content.ContentDbPersister;
import blackboard.persist.gradebook.LineitemDbLoader;
import blackboard.persist.gradebook.LineitemDbPersister;
import blackboard.servlet.util.DatePickerUtil;

import com.questionmark.QMWISe.ScheduleV42;

/*
 * Useful references:
 * http://forums.edugarage.com/forums/p/2167/7161.aspx
 * http://forums.edugarage.com/forums/t/2058.aspx
 * */

public class QMPContentItem {

	private QMPCourseContext ctx=null;
	public Id parentId=null;
	public Id contentId=null;
	public Content courseDoc=null;
	public Vector<ScheduleV42> schedules = null;
	public ScheduleV42 schedule = null;
	public Lineitem lineitem = null;
	public String name="Perception Assessment";
	public String description="Questionmark Perception Schedule";
	public String assessmentID=null;
	public boolean limitAttempts=false;
	public int maxAttempts=0;
	public boolean individualSchedules=false;
	public boolean accessPeriod=false;
	public Calendar startdate = Calendar.getInstance();
	public Calendar enddate = Calendar.getInstance();
	public boolean available = true;
	public String gradebookScore="no";
	public String gradebookScoreType=null;
	
	public QMPContentItem(QMPCourseContext ctx, String content_id, String parent_id) throws PersistenceException, QMWiseException {
		this.ctx=ctx;
		if (content_id != null) {
			// ignore parent_id
			LoadCourseDocument(content_id);
			LoadLineitem();
			LoadSchedule();
		} else {
			// new content item in parent_id (perhaps)
			parentId=ctx.bbPm.generateId(CourseDocument.DATA_TYPE,parent_id);			
			SetDefaultAccessPeriod();
		}
	}
	
	
	public QMPContentItem(QMPContentCreator ctx, HttpServletRequest request) throws PersistenceException {
		this.ctx=ctx;
		String parent_id=request.getParameter("parent_id");
		parentId=ctx.bbPm.generateId(CourseDocument.DATA_TYPE,parent_id);
		name=request.getParameter("schedule");
		description=request.getParameter("schedule_text_area");
		assessmentID=request.getParameter("assessment");
		limitAttempts = request.getParameter("limit_attempts") != null;
		if (limitAttempts)
			maxAttempts=new Integer(request.getParameter("limit")).intValue();
		individualSchedules=request.getParameter("per_participant") != null
				|| request.getParameter("per_participant_hidden").equals("1");
		accessPeriod=request.getParameter("set_access_period") != null;
		if (accessPeriod) {
			startdate=DatePickerUtil.pickerDatetimeStrToCal(request.getParameter("scheduleStart_datetime"));
			enddate=DatePickerUtil.pickerDatetimeStrToCal(request.getParameter("scheduleEnd_datetime"));
		} else {
			SetDefaultAccessPeriod();
		}
		available=request.getParameter("available")==null ||
			!request.getParameter("available").equalsIgnoreCase("false");
		// read the value of the "store results in gradebook" select menu
		gradebookScore=request.getParameter("use_gradebook");
		gradebookScoreType=request.getParameter("result_type");
	}
	
	
	public void SetDefaultAccessPeriod() {
		startdate.set(Calendar.HOUR_OF_DAY,9);
		startdate.set(Calendar.MINUTE,0);
		startdate.set(Calendar.SECOND,0);
		enddate.set(Calendar.HOUR_OF_DAY,17);
		enddate.set(Calendar.MINUTE,0);
		enddate.set(Calendar.SECOND,0);
		enddate.add(Calendar.DAY_OF_MONTH, 7);		
	}
	
	
	public void CreateNew() {
		try {
			schedule=NewSchedule();
			lineitem=NewLineItem();
			courseDoc=NewCourseDocument();
			CreateSchedule();
			PersistLineitem();
			PersistCourseDocument();
		} catch(RemoteException e) {
			QMWiseException qe=new QMWiseException(e);
			ctx.FailQMWISe(qe);
		} catch(PersistenceException e) {
			ctx.Fail("Unexpected PersistenceException","Error while saving content item: "+e.getMessage());
		} catch(ValidationException e) {
			ctx.Fail("Unexpected ValidationException","Error while saving content item: "+e.getMessage());
		}
	}

	
	public void Update(HttpServletRequest request) {
		try {
			Calendar newStartdate=null;
			Calendar newEnddate=null;
			String newName = request.getParameter("new_schedule_name");
			boolean newLimit = false;
			if (newName != null)
				name=newName;
			String newDescription = request.getParameter("schedule_text_area");
			if (newDescription != null)
				description=newDescription;
			if (individualSchedules && request.getParameter("limit_attempts")!=null) {
				maxAttempts=new Integer(request.getParameter("limit")).intValue();
				newLimit=true;
			}
			if (request.getParameter("set_access_period") != null) {
				accessPeriod=true;
				startdate=DatePickerUtil.pickerDatetimeStrToCal(request.getParameter("scheduleStart_datetime"));
				enddate=DatePickerUtil.pickerDatetimeStrToCal(request.getParameter("scheduleEnd_datetime"));			
			}
			// slightly different, on update we keep the current available value unless we
			// have some input.
			if (request.getParameter("available")!=null)
				available=!request.getParameter("available").equalsIgnoreCase("false");
			UpdateSchedule(newLimit);
			if (UpdateLineitem())
				PersistLineitem();
			if (UpdateCourseDocument())
				PersistCourseDocument();
		} catch (RemoteException e) {
			QMWiseException qe=new QMWiseException(e);
			ctx.FailQMWISe(qe);
		} catch(PersistenceException e) {
			ctx.Fail("Unexpected PersistenceException","Error while saving content item: "+e.getMessage());
		} catch(ValidationException e) {
			ctx.Fail("Unexpected ValidationException","Error while saving content item: "+e.getMessage());
		}
	}

	
	public String Delete() {
		StringBuilder sb = new StringBuilder(4096); // arbitrary 4K chunk
		try {
			DeleteSchedule(sb);
			// We don't delete the line item (because it contain gradebook stuff)
			// We don't delete the course document (as this is done for us we think)
		} catch (RemoteException e) {
			QMWiseException qe=new QMWiseException(e);
			ctx.FailQMWISe(qe);
		}
		return sb.toString();
	}
	
	public ScheduleV42 NewSchedule() {
		schedule = new ScheduleV42();
		schedule.setSchedule_Name(name);
		schedule.setAssessment_ID(assessmentID);
		schedule.setRestrict_Attempts(limitAttempts);
		if (limitAttempts)
			schedule.setMax_Attempts(maxAttempts);
		schedule.setRestrict_Times(accessPeriod);
		if (accessPeriod) {
			schedule.updateSchedule_Starts_fromCalendar(startdate);
			schedule.updateSchedule_Stops_fromCalendar(enddate);
		} else {
			// Java will not allow the dates to be left blank
			Calendar nullDate=Calendar.getInstance();
			schedule.updateSchedule_Starts_fromCalendar(nullDate);
			schedule.updateSchedule_Stops_fromCalendar(nullDate);
		}
		int intGroupID=new Integer(ctx.groupID).intValue();
		schedule.setGroup_ID(intGroupID);
		// group_tree_id is required. 0 is not accepted so we set it to be the same as group id
		schedule.setGroup_Tree_ID(intGroupID);
		// we must indicate that this is for web delivery, otherwise the test is not take-able
		schedule.setWeb_Delivery(true);
		return schedule;
	}
	
	
	public void CreateSchedule() throws RemoteException {
		@SuppressWarnings("unused")
		String[] scheduleids = ctx.stub.createScheduleGroupV42(schedule,individualSchedules);
	}

	
	public void LoadSchedule() throws QMWiseException {
		schedules=ctx.GroupSchedules(name);
		if (schedules.size()>0) {
			schedule=schedules.get(0);
			assessmentID=schedule.getAssessment_ID();
			limitAttempts=schedule.isRestrict_Attempts();
			if (limitAttempts)
				maxAttempts=schedule.getMax_Attempts();
			individualSchedules=(schedule.getParticipant_ID()!=0);
			accessPeriod=schedule.isRestrict_Times();
			if (accessPeriod) {
				startdate=schedule.readSchedule_Starts_asCalendar();
				enddate=schedule.readSchedule_Stops_asCalendar();
			} else {
				SetDefaultAccessPeriod();
			}
		}
	}

	
	public void UpdateSchedule(boolean newLimit) throws RemoteException {
		if (individualSchedules) {
			// This may not work well for large courses or courses with many assessments...
			ScheduleV42[] allSchedules = ctx.stub.getScheduleListByGroupV42(new Integer(ctx.groupID).intValue());
			String matchName = schedule.getSchedule_Name();
			for(ScheduleV42 iSchedule: allSchedules){
				if (!matchName.equals(iSchedule.getSchedule_Name()))
					continue;
				UpdateSchedule(iSchedule,newLimit);
			}
			
		} else {
			UpdateSchedule(schedule,newLimit);
		}
	}

	
	public void UpdateSchedule(ScheduleV42 s, boolean newLimit) throws RemoteException {
		boolean update=false;
		if (!s.getSchedule_Name().equals(name)) {
			s.setSchedule_Name(name);
			update=true;
		}
		if (individualSchedules && newLimit && s.getMax_Attempts()!=maxAttempts) {
			s.setMax_Attempts(maxAttempts);
			update=true;
		}
		// ignore new limit because we can't convert a group schedule to an individual one
		if (accessPeriod) {
			if (!s.isRestrict_Times()) {
				s.setRestrict_Times(true);
				s.updateSchedule_Starts_fromCalendar(startdate);
				s.updateSchedule_Stops_fromCalendar(enddate);
				update=true;
			} else {
				if (!startdate.equals(s.readSchedule_Starts_asCalendar())) {
					s.updateSchedule_Starts_fromCalendar(startdate);
					update=true;
				}
				if (!enddate.equals(s.readSchedule_Stops_asCalendar())) {
					s.updateSchedule_Stops_fromCalendar(enddate);
					update=true;
				}
			}
		}
		if (update)
			ctx.stub.setScheduleV42(s);
	}

	
	public void DeleteSchedule(StringBuilder sb) throws RemoteException {
		if (individualSchedules) {
			// We delete the phantom user's schedule first, once this has gone we can delete
			// the rest knowing that a parallel synchronization won't be recreating them!
			String matchName = schedule.getSchedule_Name();
			int matchID = schedule.getParticipant_ID();
			ctx.stub.deleteScheduleV42(schedule.getSchedule_ID());
			// This may not work well for large courses or courses with many assessments...
			ScheduleV42[] allSchedules = ctx.stub.getScheduleListByGroupV42(new Integer(ctx.groupID).intValue());
			for(ScheduleV42 iSchedule: allSchedules){
				if (matchID == iSchedule.getParticipant_ID() || !matchName.equals(iSchedule.getSchedule_Name()))
					continue;
				try {
					ctx.stub.deleteScheduleV42(iSchedule.getSchedule_ID());
				} catch (RemoteException e) {
					QMWiseException qe=new QMWiseException(e);
					sb.append("Failed to delete Perception Schedule for participant "+iSchedule.getParticipant_Name()+"; "+qe.getMessage()+"\n");
				}
			}
		} else {
			ctx.stub.deleteScheduleV42(schedule.getSchedule_ID());
		}
	}
	
	
	public Lineitem NewLineItem() {
		if (!gradebookScore.equals("no")) {
			Lineitem lineitem = new Lineitem();
			lineitem.setName(name);
			lineitem.setCourseId(ctx.course.getId());
			lineitem.setIsAvailable(true);						
			lineitem.setType("QM Assessment Grade: " + gradebookScoreType);
			if (gradebookScore.equals("percent")) {
				lineitem.setPointsPossible(100f);
			}
			return lineitem;
		} else
			return null;
	}
	
	
	public void LoadLineitem() throws KeyNotFoundException, PersistenceException {
		// gradebook information is also set later, but differently
		LineitemDbLoader lineitemdbloader = (LineitemDbLoader)ctx.bbPm.getLoader(LineitemDbLoader.TYPE);
		List<Lineitem> lineitems = lineitemdbloader.loadByCourseIdAndLineitemName(ctx.courseIdObject,name);
		if (lineitems.size()>=1) {
			lineitem=lineitems.get(0);
			gradebookScore=(lineitem.getPointsPossible()==100f)?"percent":"point";
			// TODO need to figure out how to load the score type from the line item
			gradebookScoreType="";
		}
	}
	
	
	public boolean UpdateLineitem() {
		boolean update=false;
		if (!lineitem.getName().equals(name)) {
			lineitem.setName(name);
			update=true;
		}
		return update;
	}
	
	
	public void PersistLineitem() throws ValidationException, PersistenceException {
		if (lineitem != null) {
			LineitemDbPersister lineitemdbpersister = (LineitemDbPersister) ctx.bbPm.getPersister(LineitemDbPersister.TYPE);
			lineitem.validate();
			lineitemdbpersister.persist(lineitem);
		}
	}
	
	
	public Content NewCourseDocument() {
//		if (parentId == null){
//			out.println("Stop here parent id is null, parent_id is"	+ parent_id);
//			return;
//		}
		//prepare a ContentDbLoader
		// ContentDbLoader contentLoader = ContentDbLoader.Default.getInstance();
		courseDoc = new Content();
		//title of the content item tied in with the Questionmark Created Scheduled name...
		courseDoc.setTitle(name);

		//set description of content item, in this case, perception schedule..
		//Escape any html that the user types. Only allowing plaintext.
		FormattedText text = new FormattedText(StringEscapeUtils.escapeHtml(description), FormattedText.Type.DEFAULT);														
		courseDoc.setBody(text);
		courseDoc.setIsAvailable(available);
		if (accessPeriod) {
			courseDoc.setStartDate(startdate);
			courseDoc.setEndDate(enddate);
		}
		//set content resource type(Set content handler)
		courseDoc.setContentHandler("qm/schedule-link"); //NB Must match the entry in bb-manifest!!
		//set parent id
		courseDoc.setParentId(parentId);
		//set course id
		courseDoc.setCourseId(ctx.courseIdObject); //get the course id from the context of create page.
		//here we have the option to set the order in which the child appears
		//courseDoc.setPosition(numberOfChildren);
		return courseDoc;
	}

	
	public void LoadCourseDocument(String content_id) throws PersistenceException {
		contentId = Id.generateId(Content.DATA_TYPE, content_id);
		ContentDbLoader courseDocumentLoader = ContentDbLoader.Default.getInstance();
		courseDoc = courseDocumentLoader.loadById( contentId ); 
		// can now query this...
		parentId = courseDoc.getParentId();				
		name = courseDoc.getTitle();		
		description = courseDoc.getBody().getText();
		available = courseDoc.getIsAvailable();
	}

	
	public boolean UpdateCourseDocument() {
		boolean update=false;
		if (!courseDoc.getTitle().equals(name)) {
			courseDoc.setTitle(name);
			update=true;
		}
		if (!courseDoc.getBody().getText().equals(description)) {
			FormattedText text = new FormattedText(StringEscapeUtils.escapeHtml(description), FormattedText.Type.DEFAULT);
			courseDoc.setBody(text);
			update=true;
		}
		if (accessPeriod) {
			if (!startdate.equals(courseDoc.getStartDate())) {
				courseDoc.setStartDate(startdate);
				update=true;
			}
			if (!enddate.equals(courseDoc.getEndDate())) {
				courseDoc.setEndDate(enddate);
				update=true;
			}			
		}
		if (available != courseDoc.getIsAvailable()) {
			courseDoc.setIsAvailable(available);
			update=true;
		}
		return update;
	}
	
	
	public void PersistCourseDocument() throws PersistenceException, ValidationException {
		//Now to try and save our content item using the ContentDbPersister
		//first get the persister object instance
		ContentDbPersister persister = (ContentDbPersister)ctx.bbPm.getPersister( ContentDbPersister.TYPE );
		//now to persist!
		//save details of this item.
		persister.persist(courseDoc);	
	}
}
