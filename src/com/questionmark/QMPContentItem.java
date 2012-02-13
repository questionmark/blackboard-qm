package com.questionmark;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import blackboard.data.gradebook.Score;
import blackboard.persist.Id;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.persist.content.ContentDbLoader;
import blackboard.persist.content.ContentDbPersister;
import blackboard.persist.gradebook.LineitemDbLoader;
import blackboard.persist.gradebook.LineitemDbPersister;
import blackboard.persist.gradebook.ScoreDbLoader;
import blackboard.persist.gradebook.ScoreDbPersister;

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
	
	public QMPContentItem(QMPCourseContext ctx, String content_id, String parent_id) throws PersistenceException, ValidationException, QMWiseException {
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
		if (parent_id!=null)
			parentId=ctx.bbPm.generateId(CourseDocument.DATA_TYPE,parent_id);
		name=request.getParameter("schedule");
		description=request.getParameter("schedule_text_area");
		if (description==null)
			description="";
		assessmentID=request.getParameter("assessment");
		limitAttempts = request.getParameter("limit_attempts") != null;
		if (limitAttempts)
			maxAttempts=new Integer(request.getParameter("limit")).intValue();
		individualSchedules=request.getParameter("per_participant") != null
				|| request.getParameter("per_participant_hidden").equals("1");
		accessPeriod=request.getParameter("set_access_period") != null;
		if (accessPeriod) {
			try {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String startString=request.getParameter("start_0"); 				
				if (startString.length()>=10)
					// fix for broken form submit in some browsers
					startdate.setTime(df.parse(startString.substring(0,10)));
				startdate.set(Calendar.HOUR_OF_DAY, new Integer(request.getParameter("start_hour")).intValue());
				startdate.set(Calendar.MINUTE, new Integer(request.getParameter("start_minute")).intValue());
				String endString=request.getParameter("end_1");
				if (endString.length()>=10)
					// fix for broken form submit in some browsers
					enddate.setTime(df.parse(endString.substring(0,10)));
				enddate.set(Calendar.HOUR_OF_DAY, new Integer(request.getParameter("end_hour")).intValue());
				enddate.set(Calendar.MINUTE, new Integer(request.getParameter("end_minute")).intValue());
			} catch (ParseException e) {
				accessPeriod=false;
				SetDefaultAccessPeriod();
			}
		} else {
			SetDefaultAccessPeriod();
		}
		available=request.getParameter("available")==null ||
			!request.getParameter("available").equalsIgnoreCase("false");
		// read the value of the "store results in gradebook" select menu
		gradebookScore=request.getParameter("use_gradebook");
		gradebookScoreType=request.getParameter("result_type");
	}
	
	
	public QMPContentItem(QMPCourseContext ctx, String content_id, boolean quickly) throws PersistenceException, QMWiseException, ValidationException {
		this.ctx=ctx;
		LoadCourseDocument(content_id);
		if (!quickly)
			LoadSchedule();
		LoadLineitem();
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
		QMWise q=null;
		try {
			q=QMWise.connect();
			if (!CheckDuplicateLineItem(name)) {
				ctx.Fail("Duplicate Name","There is already a gradebook column with that name.");
				return;
			}
			schedule=NewSchedule();
			NewLineItem();
			courseDoc=NewCourseDocument();
			PersistLineitem();
			PersistCourseDocument();
			// must create schedule after persisting document to get Id
			CreateSchedule(q);
		} catch(RemoteException e) {
			QMWiseException qe=new QMWiseException(e);
			ctx.FailQMWISe(qe);
		} catch(PersistenceException e) {
			ctx.Fail("Unexpected PersistenceException","Error while saving content item: "+e.getMessage());
		} catch(ValidationException e) {
			ctx.Fail("Unexpected ValidationException","Error while saving content item: "+e.getMessage());
		} finally {
			QMWise.close(q);
		}
	}

	
	public void QuickCreate() {
		QMWise q=null;
		try {
			q=QMWise.connect();
			if (!CheckDuplicateLineItem(name)) {
				ctx.Fail("Duplicate Name","There is already a gradebook column with that name.");
				return;
			}
			schedule=NewSchedule();
			NewLineItem();
			// skip the course document
			PersistLineitem();
			CreateSchedule(q);
		} catch(ValidationException e) {
			ctx.Fail("Unexpected ValidationException","Error while saving content item: "+e.getMessage());
		} catch(PersistenceException e) {
			ctx.Fail("Unexpected PersistenceException","Error while saving content item: "+e.getMessage());
		} catch(RemoteException e) {
			QMWiseException qe=new QMWiseException(e);
			ctx.FailQMWISe(qe);
		} finally {
			QMWise.close(q);
		}
	}

	
	public void Update(HttpServletRequest request) {
		try {
			String newName = request.getParameter("new_schedule_name");
			boolean newLimit = false;
			if (newName != null) {
				if (!name.equals(newName) && !CheckDuplicateLineItem(newName)) {
					ctx.Fail("Duplicate Name","There is already a gradebook column with that name.");
					return;
				}
				name=newName;
			}
			String newDescription = request.getParameter("schedule_text_area");
			if (newDescription != null)
				description=newDescription;
			if (individualSchedules && request.getParameter("limit_attempts")!=null) {
				maxAttempts=new Integer(request.getParameter("limit")).intValue();
				newLimit=true;
			}
			if (request.getParameter("set_access_period") != null) {
				accessPeriod=true;
				try {
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String startString=request.getParameter("start_0"); 				
					if (startString.length()>=10)
						// fix for broken form submit in some browsers
						startdate.setTime(df.parse(startString.substring(0,10)));
					startdate.set(Calendar.HOUR_OF_DAY, new Integer(request.getParameter("start_hour")).intValue());
					startdate.set(Calendar.MINUTE, new Integer(request.getParameter("start_minute")).intValue());
					String endString=request.getParameter("end_1");
					if (endString.length()>=10)
						// fix for broken form submit in some browsers
						enddate.setTime(df.parse(endString.substring(0,10)));
					enddate.set(Calendar.HOUR_OF_DAY, new Integer(request.getParameter("end_hour")).intValue());
					enddate.set(Calendar.MINUTE, new Integer(request.getParameter("end_minute")).intValue());
				} catch (ParseException e) {
					accessPeriod=false;
					SetDefaultAccessPeriod();
				}
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
		// name will be updated later
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
	
	
	public void CreateSchedule(QMWise q) throws RemoteException {
		// just before creating the schedule we add the prefix for the content Id
		if (contentId!=null) {
			String prefix="BB"+contentId.toExternalString()+" ";
			schedule.setSchedule_Name(prefix+name);
		}
		@SuppressWarnings("unused")
		String[] scheduleids = q.stub.createScheduleGroupV42(schedule,individualSchedules);
	}

	
	public static String ExtractContentId(String scheduleName) {
		StringBuilder sb = new StringBuilder(10);
		char mode='[';
		for (int i=0;i<scheduleName.length();i++) {
			char c=scheduleName.charAt(i);
			switch (mode) {
			case '[':
				if (c==mode)
					mode='1';
				else if (c=='B')
					mode='2';
				else
					mode='X';
				continue;
			case '1':
				if (c=='B')
					mode='2';
				else
					mode='X';
				continue;
			case '2':
				if (c=='B')
					mode='I';
				else
					mode='X';
				continue;
			case 'I':
				if (c==']' || c==' ' || c=='.' || c=='-')
					mode='$';
				else
					sb.append(c);
				continue;
			}
			break;
		}
		if (mode=='$') {
			try {
				String id=sb.toString();
				Id contentId = Id.generateId(Content.DATA_TYPE, id);
				ContentDbLoader courseDocumentLoader = ContentDbLoader.Default.getInstance();
				@SuppressWarnings("unused")
				Content courseDoc = courseDocumentLoader.loadById( contentId );
				return id;
			} catch (PersistenceException e) {
				return "";
			}
		}
		return "";
	}

	
	public static String FixScheduleName(String nameFix) {
		StringBuilder sb = new StringBuilder(10);
		for (int i=0;i<nameFix.length();i++) {
			char c=nameFix.charAt(i);
			if (c=='[' || c==']')
				continue;
			sb.append(c);
		}
		return sb.toString();
	}

	
	public void LoadSchedule() throws QMWiseException {
		ctx.Log("Loading schedule for BB"+contentId.toExternalString()+" "+name);
		schedules=ctx.GroupSchedules(name,contentId);
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
		QMWise q=null;
		try {
			q=QMWise.connect();
			if (individualSchedules) {
				// This may not work well for large courses or courses with many assessments...
				ScheduleV42[] allSchedules = q.stub.getScheduleListByGroupV42(new Integer(ctx.groupID).intValue());
				String matchName = schedule.getSchedule_Name();
				for(ScheduleV42 iSchedule: allSchedules){
					if (!matchName.equals(iSchedule.getSchedule_Name()))
						continue;
					UpdateSchedule(q,iSchedule,newLimit);
				}

			} else {
				UpdateSchedule(q,schedule,newLimit);
			}
		} finally {
			QMWise.close(q);
		}
	}

	
	public void UpdateSchedule(QMWise q, ScheduleV42 s, boolean newLimit) throws RemoteException {
		boolean update=false;
		String prefixedName="BB"+contentId.toExternalString()+" "+name;
		if (!s.getSchedule_Name().equals(prefixedName)) {
			s.setSchedule_Name(prefixedName);
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
			q.stub.setScheduleV42(s);
	}

	
	public void DeleteSchedule(StringBuilder sb) throws RemoteException {
		QMWise q=null;
		try {
			q=QMWise.connect();
			if (individualSchedules) {
				// We delete the phantom user's schedule first, once this has gone we can delete
				// the rest knowing that a parallel synchronization won't be recreating them!
				String matchName = schedule.getSchedule_Name();
				int matchID = schedule.getParticipant_ID();
				q.stub.deleteScheduleV42(schedule.getSchedule_ID());
				// This may not work well for large courses or courses with many assessments...
				ScheduleV42[] allSchedules = q.stub.getScheduleListByGroupV42(new Integer(ctx.groupID).intValue());
				for(ScheduleV42 iSchedule: allSchedules){
					if (matchID == iSchedule.getParticipant_ID() || !matchName.equals(iSchedule.getSchedule_Name()))
						continue;
					try {
						q.stub.deleteScheduleV42(iSchedule.getSchedule_ID());
					} catch (RemoteException e) {
						QMWiseException qe=new QMWiseException(e);
						sb.append("Failed to delete Perception Schedule for participant "+iSchedule.getParticipant_Name()+"; "+qe.getMessage()+"\n");
					}
				}
			} else {
				q.stub.deleteScheduleV42(schedule.getSchedule_ID());
			}
		} finally {
			QMWise.close(q);
		}
	}
	
	
	public void NewLineItem() {
		if (!gradebookScore.equals("no")) {
			lineitem = new Lineitem();
			lineitem.setName(name);
			lineitem.setCourseId(ctx.course.getId());
			lineitem.setIsAvailable(true);						
			lineitem.setType("QM Assessment Grade: " + gradebookScoreType);
			if (gradebookScore.equals("percent")) {
				lineitem.setPointsPossible(100f);
			}
		}
	}
	
	
	public void LoadLineitem() throws KeyNotFoundException, PersistenceException {
		// gradebook information is also set later, but differently
		LineitemDbLoader lineitemdbloader = (LineitemDbLoader)ctx.bbPm.getLoader(LineitemDbLoader.TYPE);
		List<Lineitem> lineitems = lineitemdbloader.loadByCourseIdAndLineitemName(ctx.courseIdObject,name);
		if (lineitems.size()>=1) {
			lineitem=lineitems.get(0);
			gradebookScore=(lineitem.getPointsPossible()==100f)?"percent":"point";
			String lineitemType=lineitem.getType();
			if (lineitemType==null)
				lineitemType="BEST";
			if (lineitemType.endsWith("FIRST"))
				gradebookScoreType="FIRST";
			else if (lineitemType.endsWith("WORST"))
				gradebookScoreType="WORST";
			else if (lineitemType.endsWith("LAST"))
				gradebookScoreType="LAST";
			else // if (lineitemType.endsWith("BEST")) - default behaviour before lineitemType
				gradebookScoreType="BEST";
		} else {
			lineitem=null;
			gradebookScore="no";
			gradebookScoreType="BEST";
		}
	}
	
	
	public boolean UpdateLineitem() {
		boolean update=false;
		if (lineitem!=null && !lineitem.getName().equals(name)) {
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
	
	
	public boolean CheckDuplicateLineItem(String checkName) throws PersistenceException {
		LineitemDbLoader lineitemdbloader = (LineitemDbLoader)ctx.bbPm.getLoader(LineitemDbLoader.TYPE);
		List<Lineitem> lineitems = lineitemdbloader.loadByCourseIdAndLineitemName(ctx.courseIdObject,checkName);
		return (lineitems.size()==0);
	}

	
	public void UpdateGradebook(float scoreMax, String percentage, String points) throws PersistenceException, ValidationException {
		if (lineitem!=null) {
			UpdateGradebook(ctx,lineitem,gradebookScore,gradebookScoreType,scoreMax,percentage,points);
		}
	}

	public static void UpdateGradebook(QMPCourseContext ctx, Lineitem lineitem, String score,
			String scoreType, float scoreMax, String percentage, String points) throws PersistenceException, ValidationException {
		ScoreDbLoader scoredbloader = (ScoreDbLoader) ctx.bbPm.getLoader(ScoreDbLoader.TYPE);
		Score gbScore=null;
		float value=0;
		if (score==null) {
			if(lineitem.getPointsPossible() == 100f) {
				// read the percentage score from the request
				score="percent";
			} else {
				// read the point score from the request
				score="point";
			}
		} else if (score.equals("percet")) // fix spelling mistake in previous builds
			score="percent";
		if (score.equals("percent")) {
			if (percentage==null) {
				ctx.Fail("Perception callback","Expected percentage score but found null");
				return;
			}
			value=new Float(percentage).floatValue();
		} else if (score.equals("point")) {
			if (points==null) {
				ctx.Fail("Perception callback","Expected points score but found null");
				return;
			}
			value=new Float(points).floatValue();
		} else if (score.equals("no")) {
			return;
		}
		if (scoreType==null) {
			String lineitemType=lineitem.getType();
			if (lineitemType==null)
				lineitemType="BEST";
			if (lineitemType.endsWith("FIRST"))
				scoreType="FIRST";
			else if (lineitemType.endsWith("WORST"))
				scoreType="WORST";
			else if (lineitemType.endsWith("LAST"))
				scoreType="LAST";
			else // if (lineitemType.endsWith("BEST")) - default behaviour before lineitemType
				scoreType="BEST";
			
		}
		try {
			gbScore=scoredbloader.loadByCourseMembershipIdAndLineitemId(ctx.crsMembership.getId(), lineitem.getId());
			if (scoreType.equals("FIRST")) {
				if (!(gbScore.getGrade().equalsIgnoreCase("-"))) {
					//This is not the first score coming in so therefore is ignored.
					ctx.Fail("Perception Callback","ignored a score since it was not the first attempt: FIRST Score option selected");
				}
			} else if (scoreType.equals("BEST")) {
				if(new Float(gbScore.getGrade()).floatValue() >= value) {
					// new score is less than old score -- ignore
					ctx.Fail("Perception Callback","ignored a score since it was less than or equal to old score : BEST Score option selected");
					return;
				}
			} else if (scoreType.equals("WORST")) {
				if(new Float(gbScore.getGrade()).floatValue() <= value) {
					//new score is more than old score -- ignore
					ctx.Fail("Perception Callback","ignored a score since it was more than or equal to old score: WORST score option selected");
					return;
				}				
			} else { // scoreType.equals("LAST")
				// nothing to do
				;
			}
			gbScore.setGrade(new Float(value).toString());
			gbScore.setDateChanged();
		} catch (KeyNotFoundException e) {
			gbScore=new Score();
			gbScore.setCourseMembershipId(ctx.crsMembership.getId());
			gbScore.setDateAdded();
			gbScore.setLineitemId(lineitem.getId());
			gbScore.setGrade(new Float(value).toString());
		}
		gbScore.validate();
		//get score persister
		ScoreDbPersister scoredbpersister = (ScoreDbPersister)ctx.bbPm.getPersister(ScoreDbPersister.TYPE);
		//persist it (write it to the database)
		scoredbpersister.persist(gbScore);
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
		// need to setUrl once we know the contentId
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
		if (contentId==null || courseDoc.getRenderType()!=Content.RenderType.URL) {
			contentId=courseDoc.getId();
			courseDoc.setUrl(ctx.path+"/content/viewschedule.jsp?course_id="+ctx.courseId+"&content_id="+contentId.toExternalString());
			courseDoc.setRenderType(Content.RenderType.URL);
			persister.persist(courseDoc);
		}
	}
}
