/*
 * @(#)TestConnectionServlet.java 1.0.1 Sep 1 2005
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

import blackboard.platform.context.Context;

import com.qm.bb6.perception.config.*;
import com.qm.bb6.perception.context.*;
import com.qm.bb6.perception.util.*;
import com.qm.bb6.perception.security.*;
import com.qm.bb6.perception.service.*;

import org.apache.soap.encoding.SOAPMappingRegistry;

/**
 * Tests the Perception settings by calling GetAbout
 *
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class TestConnectionServlet extends AuthenticatedServlet {
	
	public static class About {
	
		private int Major_Version = 0;
		private int Minor_Version = 0;
		private int Build_Version = 0;
		private String Build_String = null;
		private String Build_Date = null;
		private String License_Text = null;
		private String QMDbolib = null;
	
		public About() {
			//TODO
		}
	
		public int getMajorVersion(){
			return Major_Version;
		}
	
		public int getMinorVersion(){
			return Minor_Version;
		}
	
		public int getBuildVersion(){
			return Build_Version;
		}
	
		public String getBuildString(){
			return Build_String;
		}
	
		public String getBuildDate(){
			return Build_Date;
		}
	
		public String getLicenseText(){
			return License_Text;
		}
	
		public String getQMDbolib(){
			return QMDbolib;
		}
	
		public void setMajorVersion( int value ){
			Major_Version = value;
		}
	
		public void setMinorVersion( int value ){
			Minor_Version = value;
		}
	
		public void setBuildVersion( int value ){
			Build_Version = value;
		}
	
		public void setBuildString( String value ){
			Build_String = value;
		}
	
		public void setBuildDate( String value ){
			Build_Date = value;
		}
	
		public void setLicenseText( String value ){
			License_Text = value;
		}
	
		public void setQMDbolib( String value ){
			QMDbolib = value;
		}
	
	
	
	}

	public static class AboutDeserializer extends PerceptionDeserializer {
			
		public static final String TAG_NAME = "GetAboutResult";
		
		private static PerceptionSerializerMap parameterMap = new PerceptionSerializerMap();
		
		static {
				
			parameterMap.put( "MajorVersion", "MajorVersion", Integer.class );
			parameterMap.put( "MinorVersion", "MinorVersion", Integer.class );
	        parameterMap.put( "BuildVersion", "BuildVersion" , Integer.class );
	        parameterMap.put( "BuildString", "BuildString", String.class, false, false );
	        parameterMap.put( "BuildDate", "BuildDate" , String.class );
	       	parameterMap.put( "LicenseText", "LicenseText" , String.class );
	      	parameterMap.put( "QMDbolib", "QMDbolib" , String.class );
		}
		
		
		public static PerceptionSerializerMap getAdministratorParameterMap(){
			return parameterMap;
		}
	
		public PerceptionSerializerMap getParameterMap(){
			return parameterMap;
		}
		
		public Class getSerializedClass(){
			return About.class;
		}
			
	}
	
	private static class AboutLoader extends PerceptionSOAPRequestor {
		
		public static final String GET_ABOUT = "GetAbout";
		public static final String GET_ACCESS_URL = "URL";
		
		public About getAbout() throws PerceptionDataException{
			SOAPMappingRegistry smr = new SOAPMappingRegistry();	
			addResponseMapping( smr, AboutDeserializer.TAG_NAME, new AboutDeserializer(), About.class );
			return (About) load( GET_ABOUT, null, smr );
	
		}
		
		protected int getTimeout( String methodName ){
			return 10000; // 10 seconds
		}
	}
	
	
	public boolean doContent(HttpServletRequest request, HttpServletResponse response, Context ctx) throws ServletException, IOException{
		
		Locale locale = (Locale) request.getAttribute( LOCALE_PARAM );
		PerceptionLog log = PerceptionServiceManager.getPerceptionLog();

		try{

			AboutLoader aboutLoader = new AboutLoader();
			About about = aboutLoader.getAbout();
			
			StringBuffer sBuf = new StringBuffer();
			sBuf.append( getSafeSystemString("perception-version.label", locale) + " " + about.getBuildString() + "<br>" );
			sBuf.append( getSafeSystemString("perception-build-date.label", locale) + " " + about.getBuildDate() + "<br>" );
			sBuf.append( getSafeSystemString("perception-license-text.label", locale) + " " + about.getLicenseText() + "<br>" );
			
			return sendSuccess( request, response, getSafeSystemString("perception-about.title", locale), sBuf.toString() );

		}catch( PerceptionNotFoundException e ){
			log.logDebug( "Unable to launch EM", e );
			return this.sendError(request, response, getSafeCourseString("perception-not-found.title", locale), getSafeCourseString("perception-not-found.message", locale));
		}catch( PerceptionSecurityException e ){
			log.logDebug( "Unable to launch EM", e );
			return this.sendError(request, response, getSafeSystemString("access-denied.title", locale), getSafeSystemString("access-denied.message", locale));
		}catch( PerceptionDataException e ){ // TODO
			log.logDebug( "Unable to launch EM", e );
			return sendPerceptionDataException(request, response, e, null);
		}
	}
			

	protected boolean requiresAdminPrivileges(){
		return true;
	}

	protected boolean requiresControlPanelPrivileges(){
		return false;
	}

	protected boolean requiresCoursePrivileges(){
		return false;
	}
	
}
