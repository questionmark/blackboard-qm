
<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%><%@ page
	language="java"
	pageEncoding="UTF-8"
	contentType="text/plain"
	import="
		java.util.*,
		blackboard.platform.*,
		blackboard.base.*,
		blackboard.platform.session.*,
		blackboard.data.user.*,
		blackboard.persist.* ,
		blackboard.persist.user.*,
		blackboard.data.course.*,
		blackboard.data.gradebook.*,
		blackboard.persist.course.*,
		blackboard.persist.gradebook.*
	"
%>

<%
//Retrieve the Db persistence manager from the persistence service
BbPersistenceManager bbPm = BbServiceManager.getPersistenceService().getDbPersistenceManager();

// Generate a persistence framework course Id to be used for 
// loading the course
//Id courseIdObject = bbPm.generateId(Course.COURSE_DATA_TYPE, courseId);

CourseDbLoader courseLoader = (CourseDbLoader) bbPm.getLoader(CourseDbLoader.TYPE);
Course course;

//get LineitemDbLoader
LineitemDbLoader lineitemLoader = (LineitemDbLoader) bbPm.getLoader(LineitemDbLoader.TYPE);
Lineitem lineitem;

try {
	@SuppressWarnings("unchecked")
	Iterator it = request.getParameterMap().entrySet().iterator();
	while(it.hasNext()){
		@SuppressWarnings("unchecked")
		Map.Entry pairs = (Map.Entry) it.next();
		//out.println(pairs.getKey() + " = " + pairs.getValue());
		
		//for Iterator values =  (Iterator) pairs.getValue();
		
	}
	@SuppressWarnings("unchecked")
	Enumeration ParameterNames = request.getParameterNames();
	while(ParameterNames.hasMoreElements()){
		String name = (String) ParameterNames.nextElement();
		
		String[] values = request.getParameterValues(name);
		
		for(int i=0; i < values.length ; i++){
			out.println(name + " = " + values[i]);
		}
	}
	
	
	
	course = courseLoader.loadByBatchUid(((String) request.getParameter("bb_courseid")));
	
} catch(KeyNotFoundException e) {
	out.println("Perception: Callback: couldn't load course with batchuid " + ((String) request.getParameter("bb_courseid")));
	
	return;
	
	
}

try {
	lineitem = (Lineitem) lineitemLoader.loadByCourseIdAndLineitemName(course.getId(), request.getParameter("bb_schedulename")).get(0);
} catch(java.lang.IndexOutOfBoundsException e) {
	//lineitem doesn't exist yet -- "use gradebook" box was not checked 
	//otherwise it would exist already. so we ignore this callback.
	out.println("Perception: Callback: Ignoring score since there is no corresponding gradebook column");
	return;
} catch(Exception e) {
	out.println("Perception: Callback: got an exception: " + e);
	return;
}

//get LineitemDbPersister
LineitemDbPersister lineitemdbpersister = (LineitemDbPersister) bbPm.getPersister(LineitemDbPersister.TYPE);

//check if points possible is zero -- if so we need to update it
if(lineitem.getPointsPossible() == 0f) {
	lineitem.setPointsPossible(new Float(request.getParameter("Score_Max")).floatValue());
	try {
		lineitem.validate();
	} catch(Exception e) {
		out.println("Perception: Callback: Lineitem validation failed: " + e);
		return;
	}
	try {
		lineitemdbpersister.persist(lineitem);
	} catch(Exception e) {
		out.println("Perception: Callback: Lineitem persisting failed: " + e);
		return;
	}
}

// get the membership loader
CourseMembershipDbLoader coursemembershipdbloader = (CourseMembershipDbLoader) bbPm.getLoader(CourseMembershipDbLoader.TYPE);
CourseMembership coursemembership;

//get a user loader
UserDbLoader userdbloader = (UserDbLoader) bbPm.getLoader(UserDbLoader.TYPE);
User user;

//get user
try {
	user = userdbloader.loadByUserName(request.getParameter("Participant_Name"));
} catch(Exception e) {
	out.println("Perception: Callback: Couldn't find user '" + request.getParameter("Participant_Name)" + "': " + e));
	return;
}

try {
	coursemembership = coursemembershipdbloader.loadByCourseAndUserId(course.getId(), user.getId());
} catch (KeyNotFoundException e) {
	// There is no membership record.
	out.println("Perception: Callback: User " + request.getParameter("Participant_Name") + " doesn't have a role on this course");
	return;
} catch (PersistenceException pe) {
	out.println("Perception: Callback: Error loading user " + request.getParameter("Participant_Name") + "'s membership");
	return;
}

//get a score loader 
ScoreDbLoader scoredbloader = (ScoreDbLoader) bbPm.getLoader(ScoreDbLoader.TYPE);
Score score;

try {
	score = scoredbloader.loadByCourseMembershipIdAndLineitemId(coursemembership.getId(), lineitem.getId());
	if(new Float(score.getGrade()).floatValue() >= new Float(request.getParameter("Score_Attained")).floatValue()) {
		//new score is less than old score -- ignore
		out.println("Perception: Callback: ignored a score since it was less than or equal to old score");
		return;
	}
	score.setGrade(request.getParameter("Score_Attained"));
	score.setDateChanged();
} catch(KeyNotFoundException e) {
	//doesn't exist -- make a new one
	score = new Score();
	score.setCourseMembershipId(coursemembership.getId());
	score.setDateAdded();
	score.setLineitemId(lineitem.getId());
	score.setGrade(request.getParameter("Score_Attained"));
} catch(Exception e) {
	out.println("Perception: Callback: Error getting old score: " + e);
	return;
}

//validate score
try {
	score.validate();
} catch(Exception e) {
	out.println("Perception: Callback: score did not validate: " + e);
	return;
}

//get score persister
ScoreDbPersister scoredbpersister = (ScoreDbPersister) bbPm.getPersister(ScoreDbPersister.TYPE);

//persist it (write it to the database)
scoredbpersister.persist(score);
out.println("Perception: Callback: added a score to gradebook");

%>