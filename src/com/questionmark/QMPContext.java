package com.questionmark;

import java.rmi.RemoteException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;

import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.data.user.User;
import blackboard.persist.BbPersistenceManager;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.platform.persistence.PersistenceServiceFactory;
import blackboard.platform.plugin.PlugInException;
import blackboard.persist.Id;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.platform.context.Context;

import com.questionmark.QMWISe.Participant;
import com.questionmark.QMWISe.QMWISeSoapStub;
import com.questionmark.QMWISe.Version2;

public class QMPContext {

	public enum PageType {
		tool,
		controlPanel,
		content
	}
	
	public HttpServletRequest request = null;
	public String path;
	public String basePath;
	public BbPersistenceManager bbPm = null;
	public User user = null;
	public Boolean sysAdmin = false;
	public PropertiesBean pb= null;
	public String failTitle = null;
	public String failMsg = null;
	public String phantomID = null;
	
	public QMPContext(HttpServletRequest request, Context ctx ) {
		this.request = request;
		path = request.getContextPath();
		basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//Retrieve the Db persistence manager from the persistence service
		bbPm = PersistenceServiceFactory.getInstance().getDbPersistenceManager();
		if (ctx.hasUserContext()) {
			user = ctx.getUser();
			sysAdmin = user.getSystemRole().equals(User.SystemRole.SYSTEM_ADMIN);
		} else {
			sysAdmin=false;
		}
		// Get our properties object
		pb = new PropertiesBean();
		phantomID = PropertiesBean.idCache.get("phantomid");
		//	return;
	}

	
	public void FindPhantomUserId() throws QMWiseException {
		//get Perception group id, make it if it doesn't exist yet
		if (phantomID == null) {
			QMWise q=null;
			try {
				q=QMWise.connect();
				phantomID=q.stub.getParticipantByName("bb-phantom").getParticipant_ID();
				PropertiesBean.idCache.put("phantomid",phantomID);
			} catch (RemoteException e) {
				QMWiseException qe = new QMWiseException(e);
				if(qe.getQMErrorCode() == 1101) {
					// user doesn't exist yet
					phantomID=null;
					CreatePhantomUser();
				} else {
					throw qe;
				}
			} finally {
				QMWise.close(q);
			}
		}
	}
	
	
	public void CreatePhantomUser() throws QMWiseException {
		QMWise q=null;
		try {
			q=QMWise.connect();
			Participant newuser = new Participant();							
			//Clean out special characters by replacing them with acceptable ones (By Perception)
			newuser.setFirst_Name("Phantom");
			newuser.setLast_Name("User"); 							
			newuser.setParticipant_Name("bb-phantom");
			newuser.setPassword(RandomPassword());
			phantomID = q.stub.createParticipant(newuser);
			PropertiesBean.idCache.put("phantomid",phantomID);
		} catch(RemoteException e) {
			throw new QMWiseException(e);
		} finally {
			QMWise.close(q);
		}
	}

	//Static routine to help handle invalid special characters in the Blackboard user details, which would break Perception
	public static String replaceSpecChars (String replaceString){
		/* Search replace routine..remove special characters
		 * In summary:
		 * 		' " \ / all become space
		 * 		, | : all become ;
		 * 		£ goes to # - Perception won't allow £.
		 *		<> go to [] - BB doesn't allow these in names
		 * 		& goes to -
		*/	
		StringBuilder sb = new StringBuilder(replaceString.length());
		
        for (int i = 0; i < replaceString.length(); i++) 
        {        	        	
        	switch(replaceString.charAt(i)){
        	
	        	case '\'':
	        	case '\"':
	        	case '\\':
	        	case '/':
	        		sb.append(' ');
	        		break;
	        	
	        	case ',':
	        	case '|':
	        	case ':':
	        		sb.append(';');
	        		break;
	        	
	        	case '£':
	        		sb.append('#');
	        		break;
	        		
	        	case '<':
	        		sb.append('[');
	        		break;
	        	
	        	case '>':
	        		sb.append(']');
	        		break;
	        	
	        	case '&':
	        		sb.append('-');
	        		break;
	        	
	        	default:
	        		sb.append(replaceString.charAt(i));
	        		break;
        	}  
        } 
        return sb.toString();	
	}
	
	
	public static String RandomPassword() {
		// Generates a random hex string suitable for stuffing in QMWISe create user calls.
		StringBuilder sb=new StringBuilder(20);
		for (;sb.length()<20;) {
			sb.append(Long.toHexString(Double.doubleToLongBits(Math.random())));
		}
		return sb.toString().substring(0,20);
	}

	
	public void Fail(String title, String text) {
		if (failTitle == null) {
			failTitle=StringEscapeUtils.escapeHtml(title);
			failMsg=StringEscapeUtils.escapeHtml(text);
		}
	}


	public void FailRaw(String title, String text) {
		if (failTitle == null) {
			failTitle=title;
			failMsg=text;
		}
	}
	
	
	public void Log(String msg) {
		System.out.println(msg);
	}
	
	
	public String GetRequestString() {
		StringBuilder sb = new StringBuilder(4096);
		Enumeration e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			String header = (String) e.nextElement();
			if (header != null) {
				sb.append(header);
				sb.append(": ");
				sb.append(request.getHeader(header));
				sb.append("\r\n");
			}
		}
		sb.append("\r\n");
		e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String paramName = (String) e.nextElement();
			sb.append(paramName);
			sb.append(" = ");
			sb.append(request.getParameter(paramName));
			sb.append("\r\n");
		}
		return sb.toString();
	}
}
