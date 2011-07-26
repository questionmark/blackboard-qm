/*
 * @(#)SynchronisationServlet.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.servlet;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import blackboard.base.ListFilter;
import blackboard.data.course.*;
import blackboard.data.user.User;

import com.qm.bb6.perception.data.*;
import com.qm.bb6.perception.util.*;

/**
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public abstract class SynchronisationServlet extends AuthenticatedServlet {
			
	protected static class ParticipantsNotEnrolledFilter extends ListFilter {
		
		private List groupParticipants;
		private boolean useExternal = false;
		
		protected ParticipantsNotEnrolledFilter( List groupParticipants, boolean useExternal ){
			this.groupParticipants = groupParticipants;
			this.useExternal = useExternal;
		}
				
		protected boolean passesFilter(Object object){
			if(object == null || groupParticipants == null || !(object instanceof CourseMembership))
				return true;
			else {
				User bbUser = ((CourseMembership) object).getUser();
				for( Iterator i=groupParticipants.iterator(); i.hasNext(); ){
					PerceptionUser participant = (PerceptionUser) i.next();
					if( useExternal && participant.getUserName().equalsIgnoreCase( bbUser.getBatchUid() ) ){
						return false;
					}else if( (!useExternal) && participant.getUserName().equalsIgnoreCase( bbUser.getUserName() ) ){
						return false;
					}
				}
			}
			return true;
		}
		
	}
	

	public static final String TOTAL_STEPS_ATTR = "totalSteps";
	public static final String PROCESS_TITLE_ATTR = "PROCESS_TITLE_ATTR";
	public static final String PROCESS_MESSAGE_ATTR = "PROCESS_MESSAGE_ATTR";
	public static final String PROCESS_START_ATTR = "PROCESS_START_ATTR";
	public static final String PROCESS_MIDDLE_ATTR = "PROCESS_MIDDLE_ATTR";
	public static final String PROCESS_END_ATTR = "PROCESS_END_ATTR";
	public static final String PROCESSING_MESSAGE_ATTR = "PROCESSING_MESSAGE_ATTR";
			
	
	public boolean sendSynchronisationDisabled(HttpServletRequest request, HttpServletResponse response, Locale locale) throws ServletException, IOException{
		return this.sendError(request, response, 
											getSafeCourseString("synchronization-disabled.title", locale), 
											getSafeCourseString("synchronization-disabled.message", locale));
	}
	
	public void startLoadingPage( HttpServletRequest request, HttpServletResponse response, int totalSteps ) throws ServletException, IOException{
		request.setAttribute( TOTAL_STEPS_ATTR, new Integer(totalSteps) );
		getServletConfig().getServletContext().getRequestDispatcher("/jsp/loading_start.jsp").include(request, response);
	}


	public void endLoadingPage( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		getServletConfig().getServletContext().getRequestDispatcher("/jsp/loading_end.jsp").include(request, response);
	}
	
	
	protected static void writeScriptUpdate( PrintWriter clientWriter, int stepCount, String message ) throws IOException {
		clientWriter.println("<script language=\"JavaScript1.2\" type=\"text/javascript\">");
		clientWriter.println("<!--");
		clientWriter.println( "changeStepCount(" + stepCount + ");" );
		clientWriter.println( "changeMessage(\"" + WebUtils.escapeDoubleQuotes(message) + "\");" );		
		clientWriter.println("//-->");
		clientWriter.println("</script>");
		clientWriter.flush();
	}

	protected static void writeErrorUpdate( PrintWriter clientWriter, String title, String message ) throws IOException {
		clientWriter.println("<script language=\"JavaScript1.2\" type=\"text/javascript\">");
		clientWriter.println("<!--");
		clientWriter.println( "changeError(\"" + WebUtils.escapeDoubleQuotes(title) + "\");" );
		clientWriter.println( "changeMessage(\"" + WebUtils.escapeDoubleQuotes(message) + "\");" );		
		clientWriter.println("//-->");
		clientWriter.println("</script>");
		clientWriter.flush();
	}

	protected static void writeComplete( PrintWriter clientWriter, String title, String message ) throws IOException {
		clientWriter.println("<script language=\"JavaScript1.2\" type=\"text/javascript\">");
		clientWriter.println("<!--");
		clientWriter.println( "finishProcess(\"" + WebUtils.escapeDoubleQuotes(title) + "\", \"" + WebUtils.escapeDoubleQuotes(message) + "\");" );
		clientWriter.println("//-->");
		clientWriter.println("</script>");
		clientWriter.flush();
	}

	protected static void writeBackErrorUpdate( PrintWriter clientWriter, String title, String message ) throws IOException {
		clientWriter.println("<script language=\"JavaScript1.2\" type=\"text/javascript\">");
		clientWriter.println("<!--");
		clientWriter.println( "changeError(\"" + WebUtils.escapeDoubleQuotes(title) + "\");" );
		clientWriter.println( "changeMessage(\"" + WebUtils.escapeDoubleQuotes(message) + "\");" );	
		clientWriter.println( "goBack = true;" );	
		clientWriter.println("//-->");
		clientWriter.println("</script>");
		clientWriter.flush();
	}

}
