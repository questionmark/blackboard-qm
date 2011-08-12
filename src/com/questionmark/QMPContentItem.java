package com.questionmark;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;

import blackboard.base.BbList;
import blackboard.base.FormattedText;
import blackboard.data.ExtendedData;
import blackboard.data.ValidationException;
import blackboard.data.content.Content;
import blackboard.data.content.CourseDocument;
import blackboard.data.course.CourseMembership;
import blackboard.data.course.CourseMembership.Role;
import blackboard.data.gradebook.Lineitem;
import blackboard.data.gradebook.Score;
import blackboard.data.user.User;
import blackboard.persist.Id;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.persist.content.ContentDbLoader;
import blackboard.persist.content.ContentDbPersister;
import blackboard.persist.gradebook.LineitemDbLoader;
import blackboard.persist.gradebook.LineitemDbPersister;
import blackboard.persist.gradebook.ScoreDbLoader;
import blackboard.persist.gradebook.ScoreDbPersister;
import blackboard.servlet.util.DatePickerUtil;

import com.questionmark.QMWISe.Assessment;
import com.questionmark.QMWISe.Participant;
import com.questionmark.QMWISe.ScheduleV42;
import com.questionmark.legacy.ContentSettings;
import com.questionmark.legacy.PerceptionSettings;

/*
 * Useful references:
 * http://forums.edugarage.com/forums/p/2167/7161.aspx
 * http://forums.edugarage.com/forums/t/2058.aspx
 * */

public class QMPContentItem {

	private QMPCourseContext ctx=null;
	public Id parentId=null;
	public Id contentId=null;
	private boolean copyFlag=false; // set to true if we detect that we've been copied
	public static int LEGACY_VERSION=-1; // for future support of 4.3/4.4 created content
	public static int BASIC_SCHEDULE_VERSION=0; // schedule names match in Perception
	public static int MAGIC_SCHEDULE_VERSION=1; // schedule names are keyed with content id in Perception
	public static int XDATA_VERSION=2; // content item uses xData
	public static int CURRENT_VERSION=2;
	public int version=BASIC_SCHEDULE_VERSION;
	public Content courseDoc=null;
	public Vector<ScheduleV42> schedules = new Vector<ScheduleV42>();
	private ScheduleV42 schedule = null;
	private Id lineitemId=null;
	public Lineitem lineitem = null;
	public String name="Perception Assessment";
	public String description="Questionmark Perception Schedule";
	public String assessmentID=null;
	boolean assessmentMissing=false;
	public boolean limitAttempts=false;
	public int maxAttempts=0;
	public boolean individualSchedules=false;
	public boolean accessPeriod=false;
	public Calendar startdate = Calendar.getInstance();
	public Calendar enddate = Calendar.getInstance();
	public boolean available = true;
	public String gradebookScore="no";
	public String gradebookScoreType="BEST";
	private ContentSettings legacyInfo=null;
	
	public QMPContentItem(QMPCourseContext ctx, String content_id, String parent_id) throws PersistenceException, QMWiseException, ValidationException {
		this.ctx=ctx;
		if (content_id != null) {
			// ignore parent_id
			LoadCourseDocument(content_id);
			LoadSchedule();
			LoadLineitem();
			if (copyFlag || version==LEGACY_VERSION)
				PersistCourseDocument();
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
			if (!CheckDuplicateLineItem(name)) {
				ctx.Fail("Duplicate Name","There is already a gradebook column with that name.");
				return;
			}
			version=CURRENT_VERSION;
			schedule=NewSchedule();
			NewLineItem();
			courseDoc=NewCourseDocument();
			PersistLineitem();
			// must persist the document after persisting the line item to record the Id
			PersistCourseDocument();
			// must create schedule after persisting document to get Id
			CreateSchedule();
		} catch(RemoteException e) {
			QMWiseException qe=new QMWiseException(e);
			ctx.FailQMWISe(qe);
		} catch(PersistenceException e) {
			ctx.Fail("Unexpected PersistenceException","Error while saving content item: "+e.getMessage());
		} catch(ValidationException e) {
			ctx.Fail("Unexpected ValidationException","Error while saving content item: "+e.getMessage());
		}
	}

	
	public void QuickCreate() {
		try {
			if (!CheckDuplicateLineItem(name)) {
				ctx.Fail("Duplicate Name","There is already a gradebook column with that name.");
				return;
			}
			schedule=NewSchedule();
			NewLineItem();
			// skip the course document
			PersistLineitem();
			CreateSchedule();
		} catch(ValidationException e) {
			ctx.Fail("Unexpected ValidationException","Error while saving content item: "+e.getMessage());
		} catch(PersistenceException e) {
			ctx.Fail("Unexpected PersistenceException","Error while saving content item: "+e.getMessage());
		} catch(RemoteException e) {
			QMWiseException qe=new QMWiseException(e);
			ctx.FailQMWISe(qe);
		}	
	}

	
	public void Update(HttpServletRequest request) {
		try {
			Calendar newStartdate=null;
			Calendar newEnddate=null;
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
	
	
	public void CreateSchedule() throws RemoteException {
		// just before creating the schedule we add the prefix for the content Id
		if (contentId!=null) {
			String prefix="BB"+contentId.toExternalString()+" ";
			schedule.setSchedule_Name(prefix+name);
		}
		@SuppressWarnings("unused")
		String[] scheduleids = ctx.stub.createScheduleGroupV42(schedule,individualSchedules);
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

	
	public void LoadSchedule() throws QMWiseException, KeyNotFoundException, PersistenceException {
		String prefix="BB"+contentId.toExternalString()+" ";
		ctx.Log("Loading schedule for BB"+contentId.toExternalString()+" "+name);
		if (version>=XDATA_VERSION || version==LEGACY_VERSION) {
			if (copyFlag) {
				// there are no schedules, create them
				NewSchedule();
				try {
					CreateSchedule();
				} catch (RemoteException e) {
					QMWiseException qe=new QMWiseException(e);
					if(qe.getQMErrorCode() == 1301) {
						// the assessment was missing
						assessmentMissing=true;
						ctx.Fail("Assessment Not Found","This assessment is no longer available.");
					} else
						throw qe;
				}
			} else if (version==LEGACY_VERSION) {
				if (!individualSchedules) {
					String scheduleID=legacyInfo.getScheduleId();
					try {
						schedule=ctx.stub.getScheduleV42(new Integer(scheduleID).intValue());
						// Will be updated when/if we persist this.
						schedule.setSchedule_Name(name);
					} catch (RemoteException e) {
						// schedule has gone missing; treat as for copy
						NewSchedule();
						try {
							CreateSchedule();
						} catch (RemoteException re) {
							QMWiseException qe=new QMWiseException(re);
							if(qe.getQMErrorCode() == 1301) {
								// the assessment was missing
								assessmentMissing=true;
								ctx.Fail("Assessment Not Found","This assessment is no longer available.");
							} else
								throw qe;
						}
					}
				} else {
					// We have some participant schedules; approximately right at best no doubt.
					ArrayList<CourseMembership> allMembershipsList = ctx.crsMembershipLoader.loadByCourseId(ctx.courseIdObject, null, true);
					ListIterator<CourseMembership> iterator = allMembershipsList.listIterator();
					Hashtable<String,Integer> participantHash= new Hashtable<String,Integer>();
					while(iterator.hasNext()) {
						CourseMembership membership = (CourseMembership) iterator.next();
						Role userRole=membership.getRole();
						if (!(userRole.equals(CourseMembership.Role.INSTRUCTOR) || 
								userRole.equals(CourseMembership.Role.TEACHING_ASSISTANT)))
							participantHash.put(membership.getUser().getUserName(), 0);
					}
					String[] scheduleIDs=legacyInfo.getParticipantScheduleIds();
					for (String scheduleID: scheduleIDs) {
						try {
							schedule=ctx.stub.getScheduleV42(new Integer(scheduleID).intValue());
							if (participantHash.containsKey(schedule.getParticipant_Name()))
								UpdateSchedule(schedule,false);
							else
								ctx.stub.deleteScheduleV42(new Integer(scheduleID).intValue());
							participantHash.put(schedule.getParticipant_Name(),new Integer(schedule.getParticipant_ID()));
						} catch (RemoteException e) {
							// ignore participant schedules that have gone missing
							;
						}
					}
					// Now add a schedule for bb-phantom
					NewSchedule();
					schedule.setSchedule_Name(prefix+name);
					schedule.setParticipant_ID(new Integer(ctx.phantomID).intValue());
					try {
						ctx.stub.createScheduleParticipantV42(schedule);
					} catch (RemoteException re) {
						QMWiseException qe=new QMWiseException(re);
						if(qe.getQMErrorCode() == 1301) {
							// the assessment was missing
							assessmentMissing=true;
							ctx.Fail("Assessment Not Found","This assessment is no longer available.");
						} else
							throw qe;
					}
					// Important, we might well leave some course students without a schedule, catch below...
				}
			}
			// we need only search for schedules which match the contentId in future
			schedules=ctx.GroupSchedules(name,contentId);
			if (schedules.size()>0)
				schedule=schedules.get(0);
			else if (individualSchedules && schedules.size()==0 && !ctx.isAdministrator && !assessmentMissing) {
				// catch from above...
				// this student has missed out on a synchronization so we correct the problem now
				NewSchedule();
				schedule.setSchedule_Name(prefix+name);
				schedule.setParticipant_ID(new Integer(ctx.userID).intValue());
				schedule.setParticipant_Name(ctx.user.getUserName());
				try {
					ctx.stub.createScheduleParticipantV42(schedule);
				} catch (RemoteException re) {
					throw new QMWiseException(re);
				}
			} else {
				// assessment, group or phantom schedule went missing in Perception; presumably by design?
				// Workaround for accidental deletion: make a copy of the content item!
				// It is possible that a missing assessment may be restored in future of course
				ctx.Fail("Assessment Not Found","This assessment is no longer available (no matching schedule?)");
			}
		} else {
			schedules=ctx.GroupSchedules(name,contentId);
			if (schedules.size()>0) {
				schedule=schedules.get(0);
				if (!schedule.getSchedule_Name().equals(name) && version==BASIC_SCHEDULE_VERSION)
					version=MAGIC_SCHEDULE_VERSION;
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
			} else {
				// schedules have gone missing in Perception; most likely copied content item
				// the bad news is that we don't know anything about the item any more
				// we have to FAIL
				ctx.Fail("Assessment Not Found","This assessment is no longer available (no matching schedule)");
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
		String prefixedName="BB"+contentId.toExternalString()+" "+name;
		if (!s.getSchedule_Name().equals(prefixedName)) {
			s.setSchedule_Name(prefixedName);
			update=true;
		}
		if (individualSchedules && newLimit && s.getMax_Attempts()!=maxAttempts) {
			s.setMax_Attempts(maxAttempts);
			update=true;
		}
		// else: ignore new limit because we can't convert a group schedule to an individual one
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
	
	
	public void LoadLineitem() throws PersistenceException, ValidationException, QMWiseException {
		// gradebook information is also set later, but differently
		LineitemDbLoader lineitemdbloader = (LineitemDbLoader)ctx.bbPm.getLoader(LineitemDbLoader.TYPE);
		if (version>=XDATA_VERSION || version==LEGACY_VERSION) {
			String lineitemName=name;
			if (version==LEGACY_VERSION) {
				try {
					Assessment assessment=ctx.stub.getAssessment(assessmentID);
					lineitemName=assessment.getSession_Name();
				} catch (RemoteException e) {
					QMWiseException qe=new QMWiseException(e);
					if(qe.getQMErrorCode() == 1301) {
						// the assessment was missing
						assessmentMissing=true;
						gradebookScore="no";
						ctx.Fail("Assessment Not Found","This assessment is no longer available.");
					} else
						throw qe;
				}
				if (!CheckDuplicateLineItem(name)) {
					// We are now in a context where the column names clash, forget the gradebook
					gradebookScore="no";
				}
			}
			if (gradebookScore.equals("no")) {
				lineitemId=null;
				lineitem=null;
			} else if (copyFlag || lineitemId==null) {
				// Deal with a copied content items and legacy items here
				// ...line items are copied independently so will have new ids
				List<Lineitem> lineitems = lineitemdbloader.loadByCourseIdAndLineitemName(ctx.courseIdObject,lineitemName);
				if (lineitems.size()==1) {
					lineitem=lineitems.get(0);
					lineitemId=lineitem.getId();
					if (!lineitem.getName().equals(name)) {
						lineitem.setName(name);
						PersistLineitem();
					}
					ExtendedData xData=courseDoc.getExtendedData();
					xData.setValue("lineitemID", lineitemId.toExternalString());
					courseDoc.setExtendedData(xData);
				} else if (lineitems.size()==0) {
					// copied to another course, no lineitem?  Probably individually moved, create new one
					NewLineItem();
					PersistLineitem();
				} else {
					// more than 1 matching lineitem; we decouple.
					lineitem=null;
					lineitemId=null;
					gradebookScore="no";
					gradebookScoreType="BEST";
				}
			} else {
				try {
					lineitem=lineitemdbloader.loadById(lineitemId);
				} catch (KeyNotFoundException e) {
					// the lineitem has been deleted; turn off gradebook support
					gradebookScore="no";
					lineitemId=null;
					lineitem=null;
				}
			// all the information we need was in the content item;
			}
		} else {
			// we have no information about the lineItem; search by name
			List<Lineitem> lineitems = lineitemdbloader.loadByCourseIdAndLineitemName(ctx.courseIdObject,name);
			if (lineitems.size()>=1) {
				lineitem=lineitems.get(0);
				lineitemId=lineitem.getId();
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
				lineitemId=null;
				gradebookScore="no";
				gradebookScoreType="BEST";
			}
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
			if (lineitemId == null) {
				lineitemId=lineitem.getId();
				if (courseDoc!=null) {
					ExtendedData xData=courseDoc.getExtendedData();
					xData.setValue("lineitemID", lineitemId.toExternalString());
					courseDoc.setExtendedData(xData);
				}
			}
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
		} else
			SetDefaultAccessPeriod();			
		//set content resource type(Set content handler)
		courseDoc.setContentHandler("qm/schedule-link"); //NB Must match the entry in bb-manifest!!
		//set parent id
		courseDoc.setParentId(parentId);
		//set course id
		courseDoc.setCourseId(ctx.courseIdObject); //get the course id from the context of create page.
		//here we have the option to set the order in which the child appears
		//courseDoc.setPosition(numberOfChildren);
		//
		// Now we add our data to the content item; see http://forums.edugarage.com/forums/p/2167/7161.aspx
		ExtendedData xData=courseDoc.getExtendedData();
		xData.setValue("version",new Integer(version).toString());
		xData.setValue("courseID",ctx.courseIdObject.toExternalString());
		xData.setValue("assessmentID", assessmentID);
		xData.setValue("limitAttempts", limitAttempts?"true":"false");
		xData.setValue("maxAttempts", new Integer(maxAttempts).toString());
		xData.setValue("individualSchedules", individualSchedules?"true":"false");
		xData.setValue("accessPeriod", accessPeriod?"true":"false");
		// the startdate and enddate are stored directly in the content item (see above)
		xData.setValue("gradebookScore", gradebookScore);
		xData.setValue("gradebookScoreType", gradebookScoreType);
		courseDoc.setExtendedData(xData);
		return courseDoc;
	}

	
	public void LoadCourseDocument(String content_id) throws PersistenceException {
		ExtendedData xData=null;
		contentId = Id.generateId(Content.DATA_TYPE, content_id);
		ContentDbLoader courseDocumentLoader = ContentDbLoader.Default.getInstance();
		courseDoc = courseDocumentLoader.loadById( contentId ); 
		// can now query this...
		parentId = courseDoc.getParentId();	
		available = courseDoc.getIsAvailable();
		if (courseDoc.getContentHandler().equalsIgnoreCase("qm/assessment-link")) {
			version=LEGACY_VERSION;
			legacyInfo=ContentSettings.load(courseDoc);
			name=courseDoc.getTitle();
			courseDoc.setContentHandler("qm/schedule-link");
			courseDoc.setUrl(null);
			courseDoc.setRenderType(Content.RenderType.DEFAULT);
			// note that the schedule name may not match and will be legacyInfo.getBbStoredScheduleName();
			description="Questionmark Perception Schedule Migrated from Blackboard 7";
			assessmentID=legacyInfo.getAssessmentId();
			maxAttempts=legacyInfo.getMaxAttempts();
			limitAttempts=(maxAttempts>0);
			if (legacyInfo.getScheduleId()==null) {
				if (legacyInfo.getParticipantScheduleIds() == null) {
					// both null;
					copyFlag=true;
					individualSchedules=limitAttempts;
				} else {
					individualSchedules=true;
				}
			} else {
				individualSchedules=false;
			}
			accessPeriod=(courseDoc.getEndDate() != null && courseDoc.getStartDate() != null);
			if (accessPeriod) {
				startdate=courseDoc.getStartDate();
				enddate=courseDoc.getEndDate();
			}
			// legacy connector used assessment name, not schedule name for the gradebook entry
			switch (legacyInfo.getResultType()) {
			case PerceptionSettings.SCORE:
				gradebookScore="point";
				break;
			case PerceptionSettings.PERCENTAGE:
				gradebookScore="percent";
				break;
			case PerceptionSettings.NO_RESULT:
			default:
				gradebookScore="no";
				break;
			}
			if (!gradebookScore.equals("no")) {
				// This was the default behaviour in the legacy connector; the setting was not
				// controlled in the content item but in the course, so we've lost it if the
				// content item was copied anyway (we can't read the course settings anymore).
				gradebookScoreType="LAST";
			}
			UpdateCourseDocument();
		} else {
			name = courseDoc.getTitle();		
			description = courseDoc.getBody().getText();
			xData=courseDoc.getExtendedData();
			String versionString=xData.getValue("version");
			if (versionString==null)
				// may revise this to MAGIC_SCHEDULE_VERSION later
				version=BASIC_SCHEDULE_VERSION;
			else
				version=new Integer(versionString).intValue();
		}
		if (version>=XDATA_VERSION) {
			assessmentID=xData.getValue("assessmentID");
			limitAttempts=xData.getValue("limitAttempts").equals("true");
			maxAttempts=new Integer(xData.getValue("maxAttempts")).intValue();
			individualSchedules=xData.getValue("individualSchedules").equals("true");
			accessPeriod=xData.getValue("accessPeriod").equals("true");
			if (accessPeriod) {
				startdate=courseDoc.getStartDate();
				enddate=courseDoc.getEndDate();
			}
			gradebookScore=xData.getValue("gradebookScore");
			gradebookScoreType=xData.getValue("gradebookScoreType");
			if (xData.getValue("courseID")==null) {
				// Catch for buggy condition, use previous logic
				version=BASIC_SCHEDULE_VERSION;
				return;
			}
			if (!xData.getValue("courseID").equals(ctx.courseId) ||
					!xData.getValue("contentID").equals(content_id)) {
				// We've been copied to a different course or content item
				copyFlag=true;
				if (xData.getValue("courseID").equals(ctx.courseId)) {
					// copied within a course; we can't point to the same gradebook lineitem
					gradebookScore="no";
					gradebookScoreType="BEST";
				} else {
					xData.setValue("courseID",ctx.courseIdObject.toExternalString());					
				}
				xData.setValue("contentID", contentId.toExternalString());
				courseDoc.setExtendedData(xData);
				lineitemId=null;
			} else {
				// We're good to go
				copyFlag=false;
				if (!gradebookScore.equals("no") && xData.getValue("lineitemID")!=null)
					lineitemId=Id.generateId(Lineitem.LINEITEM_DATA_TYPE, xData.getValue("lineitemID"));
			}
		}			
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
		ExtendedData xData=courseDoc.getExtendedData();
		if (version<XDATA_VERSION) {
			update=true;
			xData.setValue("version",new Integer(CURRENT_VERSION).toString());
			xData.setValue("courseID",ctx.courseIdObject.toExternalString());
			xData.setValue("contentID", contentId.toExternalString());
			xData.setValue("assessmentID", assessmentID);
			xData.setValue("limitAttempts", limitAttempts?"true":"false");
			xData.setValue("maxAttempts", new Integer(maxAttempts).toString());
			xData.setValue("individualSchedules", individualSchedules?"true":"false");
			xData.setValue("accessPeriod", accessPeriod?"true":"false");
			// the startdate and enddate are stored directly in the content item (see above)
			xData.setValue("gradebookScore", gradebookScore);
			xData.setValue("gradebookScoreType", gradebookScoreType);
			if (lineitem!=null) {
				xData.setValue("lineitemID", lineitemId.toExternalString());
			}
		} else {
			if (courseDoc.getRenderType()!=Content.RenderType.DEFAULT) {
				courseDoc.setRenderType(Content.RenderType.DEFAULT);
				courseDoc.setUrl(null);
				update=true;
			}
			if (CURRENT_VERSION!=version) {
				xData.setValue("version",new Integer(CURRENT_VERSION).toString());
				update=true;
			}
			// we can't change assessmentID
			// we can't change limitAttempts and individualSchedules, but can change maxAttempts
			if (maxAttempts!=new Integer(xData.getValue("maxAttempts")).intValue()) {
				xData.setValue("maxAttempts", new Integer(maxAttempts).toString());
				update=true;
			}
			if (accessPeriod!=xData.getValue("accessPeriod").equals("true")) {
				xData.setValue("accessPeriod", accessPeriod?"true":"false");
				update=true;
			}
			// we can't change the gradebook behaviour but catch this for future
			if (!gradebookScore.equals(xData.getValue("gradebookScore"))) {
				xData.setValue("gradebookScore", gradebookScore);
				update=true;
			}
			if (gradebookScore.equals("percet")) { // fix stupid typo in previous builds
				xData.setValue("gradebookScore", "percent");
				update=true;
			}
			if (!gradebookScoreType.equals(xData.getValue("gradebookScoreType"))) {
				xData.setValue("gradebookScoreType", gradebookScoreType);
				update=true;
			}			
		}
		courseDoc.setExtendedData(xData);		
		return update;
	}
	
	
	public void PersistCourseDocument() throws PersistenceException, ValidationException {
		//Now to try and save our content item using the ContentDbPersister
		//first get the persister object instance
		ContentDbPersister persister = (ContentDbPersister)ctx.bbPm.getPersister( ContentDbPersister.TYPE );
		//now to persist!
		persister.persist(courseDoc);
		if (contentId==null) {
			// This is the first time we've persisted this content item, record the Id
			contentId=courseDoc.getId();
			ExtendedData xData=courseDoc.getExtendedData();
			xData.setValue("contentID", contentId.toExternalString());
			courseDoc.setExtendedData(xData);
			persister.persist(courseDoc);
		}
	}


}
