/*
 * @(#)BootstrapServlet.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.servlet;

import java.util.List;
import java.util.Vector;
import java.util.regex.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import blackboard.data.navigation.*;
import blackboard.platform.BbServiceManager;
import blackboard.persist.*;
import blackboard.persist.navigation.*;
import blackboard.platform.context.ContextManager;
import blackboard.platform.vxi.data.VirtualInstallation;
import blackboard.platform.vxi.service.VirtualInstallationManager;

import com.qm.bb6.perception.config.*;
import com.qm.bb6.perception.service.*;

/**
 * Bootstrap functions
 * - moves the Questionmark course tool to the assessments group
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class BootstrapServlet extends HttpServlet {

	public void init() throws ServletException{
		
	 	String _vi;
	
		String path = getServletConfig().getServletContext().getRealPath("");
	    path = path.replace('\\', '/');

		try{
			Pattern pattern = Pattern.compile(".*/vi/([^/]*)/.*");
			Matcher matcher = pattern.matcher( path );
			if( matcher.matches() ){
				_vi = matcher.group(1);
			}else{
				throw new Exception();
			}
		}catch(Exception e){
			// regexp error
			System.out.println("Perception Bootstrap: Unable to determine VI, using bb_bb60");
			_vi = "bb_bb60";
			e.printStackTrace();
		}
		
		try{
		    VirtualInstallationManager viMgr = (VirtualInstallationManager) BbServiceManager.lookupService(VirtualInstallationManager.class);
		    VirtualInstallation vi = viMgr.getVirtualInstallationByBbuid( _vi );
		    ContextManager ctxMgr = (ContextManager) BbServiceManager.lookupService(ContextManager.class);
		    ctxMgr.setContext(vi);

			// more course tool to assessments group
			
			NavigationItem navItem = NavigationItemDbLoader.Default.getInstance().loadByInternalHandle( "qm-perceptionBridgeCourse-nav-1" );
		
			if( ! "assessment".equals( navItem.getSubGroup() )  ){
				navItem.setSubGroup( "assessment" );
				NavigationItemDbPersister.Default.getInstance().persist( navItem );
			}
			
			// attempt to set logging mode
			PerceptionLog log = PerceptionServiceManager.getPerceptionLog();
			PerceptionSettings settings = PerceptionSettings.load();
			log.setLogMode( settings.getLoggingLevel() );

		}catch(Exception e){
			System.out.println("VLE Bootstrap: " + e.getLocalizedMessage());
			e.printStackTrace();			
		}finally{
			try {
			    ContextManager ctxMgr = (ContextManager) BbServiceManager.lookupService(ContextManager.class);
			    ctxMgr.releaseContext();
			}catch(Exception e){
				// unable to release context
			}
		}
					
	}

	


}
