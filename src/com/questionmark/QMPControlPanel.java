package com.questionmark;

import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.questionmark.QMWISe.ScheduleV42;

import blackboard.data.content.Content;
import blackboard.data.navigation.CourseToc;
import blackboard.persist.Id;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.persist.content.ContentDbLoader;
import blackboard.persist.navigation.CourseTocDbLoader;
import blackboard.platform.context.Context;

public class QMPControlPanel extends QMPCourseContext {

	public String panelTitle="Questionmark Perception Control Panel";
	public boolean linkView=false;
	public SelectAssessmentItem[] selectAssessmentList=null;
	public Vector<Content> contentItems=null;
	public Vector<Content> legacyItems=null;
	public Hashtable<String,Content> contentNameHash = null;
	
	public QMPControlPanel(HttpServletRequest request, Context ctx) {
		super(request, ctx);
		if (failTitle!=null)
			return;
		String schedule_name = request.getParameter("schedule_name");
		linkView=(schedule_name != null);
		try {
			if (Synchronize()) {
				Log("User Synchronized OK!  UserID="+userID+"("+courseUser.getUserName()+")");
				if (isAdministrator) {
					GetContentItems();
					Vector<ScheduleV42> schedules=GroupSchedules(schedule_name);
					FilterContentItemSchedules(schedules);
					GetScheduleInfo(schedules);
					if (pb.getProperty(PropertiesBean.oldassessmentlist_key)!=null) {
						selectAssessmentList=GetAssessmentList();
					} else {
						selectAssessmentList=GetAssessmentTree(null,null);
					}
				} else if (schedule_name != null) {
					// In future, we perhaps should filter content item schedules here
					panelTitle="Assessment Launch Page";
					Vector<ScheduleV42> schedules=GroupSchedules(schedule_name);
					if (schedules.size()==0)
						Fail("Assessment Not Found","This assessment is no longer available (no matching schedule)");
					else
						GetScheduleInfo(schedules);
				} else if (!courseSettings.getProperty("hide_schedules","0").equals("1")){
					panelTitle="Questionmark Perception";
					GetContentItems();
					Vector<ScheduleV42> schedules=GroupSchedules(schedule_name);
					FilterContentItemSchedules(schedules);
					GetScheduleInfo(schedules);
				} else {
					Fail("Questionmark Perception","This page has been hidden by the course instructor");
				}
			}
		} catch (PersistenceException e) {
			Fail("Unexpected PersistenceException",e.getMessage());
		} catch (QMWiseException e) {
			FailQMWISe(e);
		}
	}

	
	public void FilterContentItemSchedules(Vector<ScheduleV42> schedules) {
		Hashtable<String,ScheduleV42> contentIDHash = new Hashtable<String,ScheduleV42>();
		// two pass
		// (1) filter out anything with [BB_ID_X] format... remember which content items matched
		for(int i=0;i<schedules.size();) {
			ScheduleV42 s=schedules.get(i);
			String sName=s.getSchedule_Name();
			String sContentId=QMPContentItem.ExtractContentId(sName);
			if (sContentId.length()>0) {
				schedules.remove(i);
				contentIDHash.put(sContentId,s);
				continue;
			}
			i++;
		}
		// (2) filter out anything matching just name, provided we didn't already match it
		for (int i=0;i<schedules.size();) {
			ScheduleV42 s=schedules.get(i);
			String sName=s.getSchedule_Name();
			String sContentId=QMPContentItem.ExtractContentId(sName);
			if (sContentId.length()==0 && contentNameHash.containsKey(sName)) {
				Content c=contentNameHash.get(sName);
				if (!contentIDHash.containsKey(c.getId().toExternalString())) {
					// matching name; wasn't in the ID hash; filter it
					schedules.remove(i);
					// and pop it in the hash so we don't match it again
					contentIDHash.put(c.getId().toExternalString(), s);
					continue;
				}
			}
			i++;
		}
	}

	
	public void GetContentItems() throws KeyNotFoundException, PersistenceException {
		legacyItems=new Vector<Content>();
		contentItems=new Vector<Content>();
		contentNameHash=new Hashtable<String,Content>();
		// Thanks to http://forums.edugarage.com/forums/t/2058.aspx
		CourseTocDbLoader cTocDbLoader = (CourseTocDbLoader)bbPm.getLoader(CourseTocDbLoader.TYPE);
		ContentDbLoader contentLoader = (ContentDbLoader)bbPm.getLoader(ContentDbLoader.TYPE);
		List<CourseToc> listCourseToc = cTocDbLoader.loadByCourseId(courseIdObject);
		ListIterator<CourseToc> iteratorListCourseToc = listCourseToc.listIterator();
		while (iteratorListCourseToc.hasNext()) // iterate through the course TOC items
		{
			CourseToc cToc = (CourseToc) iteratorListCourseToc.next(); // retrieve each TOC item
			// determine if the TOC item is of type "CONTENT" rather than application, or something else
		    if ( (cToc.getTargetType() == CourseToc.Target.CONTENT) && (cToc.getContentId() != Id.UNSET_ID) ) 
			{
		    	// we have determined that the TOC item is content, next we need to load the content object and iterate through it     			
		    	// load the content tree into an object "content" and iterate through it       
		    	List<Content> listContent = contentLoader.loadListById(cToc.getContentId());
		    	ListIterator<Content> iteratorListContent = listContent.listIterator();        
		    	while (iteratorListContent.hasNext()) // iterate through the content items in this content object 
		    	{       
		    		Content content = (Content) iteratorListContent.next(); // reteive each content item
		    		if (content.getContentHandler().equalsIgnoreCase("qm/assessment-link"))
		    			legacyItems.add(content);
		    		else if (content.getContentHandler().equalsIgnoreCase("qm/schedule-link")) {
		    			contentItems.add(content);
		    			contentNameHash.put(content.getTitle(),content);
		    		}
		    	}
			}
		}
	}
		
}