
<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%><%@ page
	language="java"
	pageEncoding="UTF-8"
	contentType="text/plain"
	import="
		java.util.*,
		blackboard.platform.*,
		blackboard.base.*,
		blackboard.platform.session.*,
		blackboard.platform.persistence.*,
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
BbPersistenceManager bbPm = PersistenceServiceFactory.getInstance().getDbPersistenceManager();

// Generate a persistence framework course Id to be used for 
// loading the course
//Id courseIdObject = bbPm.generateId(Course.COURSE_DATA_TYPE, courseId);

CourseDbLoader courseLoader = (CourseDbLoader) bbPm.getLoader(CourseDbLoader.TYPE);
Course course;

//get LineitemDbLoader
LineitemDbLoader lineitemLoader = (LineitemDbLoader) bbPm.getLoader(LineitemDbLoader.TYPE);
Lineitem lineitem;
String scoreType; 

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
	lineitem = lineitemLoader.loadByCourseIdAndLineitemName(course.getId(), request.getParameter("bb_schedulename")).get(0);
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

if(lineitem.getPointsPossible() == 100f) {
	// read the percentage score from the request
	scoreType="Score_Percentage";
} else {
	// read the point score from the request
	scoreType="Score_Attained";
}

//Get the Grade result type selected by the instructor for this schedule - 'Best' score, 'Worst', 'First' or 'Last' scores.
String lineItemType = lineitem.getType();

      


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
	
	int gradeType = 0; // start with none.
	
	if(lineItemType.contains("FIRST")) gradeType = 1;			
	if(lineItemType.contains("BEST")) gradeType = 2;
	if(lineItemType.contains("WORST")) gradeType = 3;
	if(lineItemType.contains("LAST")) gradeType = 4;
	
	
	switch(gradeType){
	
		//First result score
		case 1:
						
			if (!(score.getGrade().equalsIgnoreCase("-"))) {
				//This is not the first score coming in so therefore is ignored.
				out.println("Perception: Callback: ignored a score since it was not the first attempt.");
				return;
			}
			//else this is the first score coming in for this schedule hence allowed to go through.
			break;
			
		//Best result score
		case 2:
			
			if(new Float(score.getGrade()).floatValue() >= new Float(request.getParameter(scoreType)).floatValue()) {
				//new score is less than old score -- ignore
				out.println("Perception: Callback: ignored a score since it was less than or equal to old score : BEST Score option selected");
				//Important to end this script in order to ignore this score value, if the best score value is selected, see above.
				return;
			}
			//else: The current score is LESS than the incoming score and hence this value cannot be ignored, break to allow for this new
			// score to replace the current grade. Very important.

			break;
			
		//Worst result score
		case 3:
			if(new Float(score.getGrade()).floatValue() <= new Float(request.getParameter(scoreType)).floatValue()) {
				//new score is more than old score -- ignore
				out.println("Perception: Callback: ignored a score since it was more than or equal to old score: WORST score option selected");
				//Important to end this script in order to ignore this score value, if the best score value is selected, see above.
				return;
			}
			//else: The current score is MORE than the incoming score and hence this value represents a worse score than the current one
			// - break to allow for this new score to replace the current grade. Very important.

			break;
			
		//Last result score - Score from the latest attempt.
		case 4:
			//Just let it through.
			//Test
			out.println("LAST score option selected.");
			break;
		        	
        default:
        	out.println("Perception Error: Callback: ignored a score since no result type option was detected..callback.jsp now terminating..");
		     return;
		     //no break because we want to terminate the script if the options are not clear.
	}  
			
	
	score.setGrade(request.getParameter(scoreType));
	score.setDateChanged();
	
} catch(KeyNotFoundException e) {
	//doesn't exist -- make a new one
	score = new Score();
	score.setCourseMembershipId(coursemembership.getId());
	score.setDateAdded();
	score.setLineitemId(lineitem.getId());
	score.setGrade(request.getParameter(scoreType));
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