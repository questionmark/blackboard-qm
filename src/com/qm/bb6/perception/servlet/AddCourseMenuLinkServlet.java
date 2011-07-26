/*
 * @(#)AddCourseMenuLinkServlet.java 1.0.1 Sep 1 2005
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

import blackboard.base.BbList;
import blackboard.data.course.*;
import blackboard.data.navigation.CourseToc;
import blackboard.persist.navigation.*;
import blackboard.platform.context.Context;

import com.qm.bb6.perception.config.*;
import com.qm.bb6.perception.util.*;
import com.qm.bb6.perception.security.*;
import com.qm.bb6.perception.service.*;

/**
 * Creates the course menu link
 * Access available to instructor only
 *
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class AddCourseMenuLinkServlet extends CourseAdminServlet {
	

	public boolean doContent(HttpServletRequest request, HttpServletResponse response, Context ctx) throws ServletException, IOException{
		
		Locale locale = (Locale) request.getAttribute( LOCALE_PARAM );
		PerceptionSettings settings = PerceptionSettings.loadFromCache();

		if( settings.canAddCourseMenuLink( ctx ) ){
				
			try{
				
				int maxPosition = 0;
				CourseToc toc = null;
				CourseTocDbLoader loader = CourseTocDbLoader.Default.getInstance();
				BbList courseTocs = loader.loadByCourseId( ctx.getCourseId() );
				if( courseTocs != null ){
					Iterator iterator = courseTocs.iterator();
					while( iterator.hasNext() ){
						CourseToc _toc = (CourseToc) iterator.next();
						if( _toc.getInternalHandle().equals( TOC_HANDLE ) )
							toc = _toc;
						
						if( _toc.getPosition() > maxPosition )
							maxPosition = _toc.getPosition();
					}
				}else{
					courseTocs = new BbList(); // empty list
				}
										
				if( toc == null ){
					// create menu item
					toc = new CourseToc();
					
					toc.setAllowGuests( false );
					toc.setAllowObservers( false );
					toc.setCourseId( ctx.getCourseId() );
					toc.setIsEnabled( true );
					toc.setIsEntryPoint( false );
					toc.setLabel( TOC_LABEL );
					toc.setLaunchInNewWindow( false ); // can't, bug in 6.3
					toc.setPosition( maxPosition + 1 );
					toc.setTargetType( CourseToc.Target.APPLICATION );
					toc.setUrl( null );
					toc.setContentId( null );
					toc.setInternalHandle( TOC_HANDLE );
					CourseTocDbPersister.Default.getInstance().persist( toc );
				}else{
					// don't create
				}		
					
			}catch(Exception e){
				PerceptionLog log = PerceptionServiceManager.getPerceptionLog();
				log.logDebug( "Error create course menu item", e );
				throw new ServletException("Error creating course menu item", e);
			}
			return sendSuccess(request, response);
		}else{
			return this.sendError(request, response, getSafeSystemString("access-denied.title", locale), getSafeSystemString("access-denied.message", locale));
		}


	}
			

}
