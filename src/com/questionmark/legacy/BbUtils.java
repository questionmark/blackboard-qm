/*
 * @(#)BbUtils.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.questionmark.legacy;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import blackboard.base.*;
import blackboard.data.course.*;
import blackboard.data.content.Content;
import blackboard.data.gradebook.*;
import blackboard.data.gradebook.impl.*;
import blackboard.data.user.User;
import blackboard.persist.*;
import blackboard.persist.announcement.*;
import blackboard.persist.content.*;
import blackboard.persist.course.*;
import blackboard.persist.gradebook.*;
import blackboard.persist.gradebook.ext.OutcomeDefinitionCategoryDbLoader;
import blackboard.persist.gradebook.ext.OutcomeDefinitionCategoryDbPersister;
import blackboard.persist.gradebook.ext.OutcomeDefinitionScaleDbLoader;
import blackboard.persist.gradebook.ext.OutcomeDefinitionScaleDbPersister;
import blackboard.persist.gradebook.impl.*;
import blackboard.persist.user.*;
import blackboard.platform.context.Context;
import blackboard.platform.plugin.*;
import blackboard.platform.*;
import blackboard.platform.security.authentication.HttpAuthManager;

//import com.qm.bb6.perception.config.*;
//import com.qm.bb6.perception.data.*;
//import com.qm.bb6.perception.service.*;


/**
 * Utility functions relating the Bb platform
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class BbUtils { 	

    public static BbPersistenceManager getBbPersistenceManager(){
    	return BbServiceManager.getPersistenceService().getDbPersistenceManager();
    }

    public static CourseDbLoader getCourseDbLoader() throws PersistenceException {
		return ((CourseDbLoader) getBbPersistenceManager().getLoader(CourseDbLoader.TYPE));
    }

    public static CourseMembershipDbLoader getCourseMembershipDbLoader() throws PersistenceException {
		return ((CourseMembershipDbLoader) getBbPersistenceManager().getLoader(CourseMembershipDbLoader.TYPE));
    }

    public static UserDbLoader getUserDbLoader() throws PersistenceException {
		return ((UserDbLoader) getBbPersistenceManager().getLoader(UserDbLoader.TYPE));
    }

	public static LineitemDbLoader getLineitemDbLoader() throws PersistenceException {
		return ((LineitemDbLoader) getBbPersistenceManager().getLoader(LineitemDbLoader.TYPE));
    }

    public static LineitemDbPersister getLineitemDbPersister() throws PersistenceException {
		return ((LineitemDbPersister) getBbPersistenceManager().getPersister(LineitemDbPersister.TYPE));
    }

    public static ScoreDbLoader getScoreDbLoader() throws PersistenceException {
		return ((ScoreDbLoader) getBbPersistenceManager().getLoader(ScoreDbLoader.TYPE));
    }

    public static ScoreDbPersister getScoreDbPersister() throws PersistenceException {
		return ((ScoreDbPersister) getBbPersistenceManager().getPersister(ScoreDbPersister.TYPE));
    }

    public static OutcomeDefinitionCategoryDbLoader getOutcomeDefinitionCategoryDbLoader() throws PersistenceException {
		return ((OutcomeDefinitionCategoryDbLoader) getBbPersistenceManager().getLoader(OutcomeDefinitionCategoryDbLoader.TYPE));
    }

    public static OutcomeDefinitionCategoryDbPersister getOutcomeDefinitionCategoryDbPersister() throws PersistenceException {
		return ((OutcomeDefinitionCategoryDbPersister) getBbPersistenceManager().getPersister(OutcomeDefinitionCategoryDbPersister.TYPE));
    }

    public static OutcomeDefinitionScaleDbLoader getOutcomeDefinitionScaleDbLoader() throws PersistenceException {
		return ((OutcomeDefinitionScaleDbLoader) getBbPersistenceManager().getLoader(OutcomeDefinitionScaleDbLoader.TYPE));
    }

    public static OutcomeDefinitionScaleDbPersister getOutcomeDefinitionScaleDbPersister() throws PersistenceException {
		return ((OutcomeDefinitionScaleDbPersister) getBbPersistenceManager().getPersister(OutcomeDefinitionScaleDbPersister.TYPE));
    }

    public static OutcomeDefinitionDbLoader getOutcomeDefinitionDbLoader() throws PersistenceException {
		return ((OutcomeDefinitionDbLoader) getBbPersistenceManager().getLoader(OutcomeDefinitionDbLoader.TYPE));
    }

    public static OutcomeDefinitionDbPersister getOutcomeDefinitionDbPersister() throws PersistenceException {
		return ((OutcomeDefinitionDbPersister) getBbPersistenceManager().getPersister(OutcomeDefinitionDbPersister.TYPE));
    }
	
	//added 4.4
	public static AnnouncementDbLoader getAnnouncementDbLoader() throws PersistenceException {
		return ((AnnouncementDbLoader) getBbPersistenceManager().getLoader(AnnouncementDbLoader.TYPE));
    }
	
	//added 4.4
	public static AnnouncementDbPersister getAnnouncementDbPersister() throws PersistenceException {
		return ((AnnouncementDbPersister) getBbPersistenceManager().getPersister(AnnouncementDbPersister.TYPE));
    }
	
    
	public static void sendLoginRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		response.sendRedirect("/webapps/login?new_loc=" + HttpAuthManager.getCurrentLoc(request));
	}

	
	public static boolean hasSystemAdminPrivileges( PerceptionSettings settings, Context ctx ){
		
		if( ctx != null ){
			User user = ctx.getUser();
			if( user != null && user.getIsAvailable() ){
				if( user.getSystemRole() == User.SystemRole.SYSTEM_ADMIN ){
					return true;
				}else{
			/*
			 		User.SystemRole[] roles = settings.getSystemRoles();
					if( roles != null ){
						for( int i=0; i < roles.length; i++ ){
							if( roles[i].equals( user.getSystemRole() ) )
								return true;
						}
					}
			*/
				}
			}
		}
		return false;
	}

	public static boolean hasControlPanelPrivileges( PerceptionSettings settings, Context ctx ){
		
		if( ctx != null ){
			CourseMembership enrolment = ctx.getCourseMembership();
			if( enrolment != null && enrolment.getIsAvailable() ){
		//		CourseMembership.Role[] roles = settings.getCourseRoles();
				CourseMembership.Role[] roles = new CourseMembership.Role[]{ 
																			CourseMembership.Role.INSTRUCTOR, 
																			CourseMembership.Role.TEACHING_ASSISTANT,
																			CourseMembership.Role.COURSE_BUILDER
																			};
				if( roles != null ){
					for( int i=0; i < roles.length; i++ ){
						if( roles[i].equals( enrolment.getRole() ) )
							return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean hasCoursePrivileges( PerceptionSettings settings, Context ctx ){
		
		if( ctx != null ){
			CourseMembership enrolment = ctx.getCourseMembership();
			if( enrolment != null && enrolment.getIsAvailable() ){
				if( ! enrolment.getRole().equals( CourseMembership.Role.GUEST )) // ban guest
					return true;
			}
		}
		return false;
	}

	/**
	 * Gets the current system locale or null if unknown
	 * @param request   servlet request
	public static Locale getLocale( HttpServletRequest request ) throws BundleKeyNotFoundException{
		return null;
	}
	 */
	
	/**
	 * Gets the current user locale or null if unknown
	 * @param request   servlet request
	 * @param ctx   the bb context
	public static Locale getLocale( HttpServletRequest request, Context ctx ) throws BundleKeyNotFoundException{
		return null;
	}
	 */

	/**
	 * Gets a system bundle string for the given context.
	 * Context is needed to fetch the current Locale, but
	 * this is not checked yet anyway
	 * @param key   string key name
	 * @param ctx   the bb context
	 * @throws com.qm.bb6.perception.service.BundleKeyNotFoundException     if the key isn't found
	public static String getSystemString( String key, Locale locale ) throws BundleKeyNotFoundException{
		try{
			ResourceBundle bundle = PerceptionServiceManager.getSystemResourceBundle( locale ); // use Locale later
			return bundle.getString( key );
		}catch( MissingResourceException mre ){
			throw new BundleKeyNotFoundException( mre );
		}
	}
	 */

	/**
	 * Gets a course bundle string for the given context.
	 * Context is needed to fetch the current Locale, but
	 * this is not checked yet anyway
	 * @param key   string key name
	 * @param ctx   the bb context
	 * @throws com.qm.bb6.perception.service.BundleKeyNotFoundException     if the key isn't found
	public static String getCourseString( String key, Locale locale ) throws BundleKeyNotFoundException{
		try{
			ResourceBundle bundle = PerceptionServiceManager.getCourseResourceBundle( locale ); // use Locale later
			return bundle.getString( key );
		}catch( MissingResourceException mre ){
			throw new BundleKeyNotFoundException( mre );
		}
	}
	 */

	public static String getFullPath(String path){
		return PlugInUtil.getUri("qm","perception", path);
	}

	/**
	 * @since Jun 16, 2004
	 */
	public static String addQueryParam(String url, String param, String value) throws MalformedURLException, UnsupportedEncodingException {
		String newUrl;
		if(url == null)
			newUrl = new String();
		else
			newUrl = new String(url);
		
		if( ! param.equals( WebUtils.parseQueryValue( param ) ) ){
			// invalid param name
			throw new MalformedURLException("Invalid URL parameter");
		}
		
		boolean addedWithRegex = false;
		// look for any existing params of the same name
		try{
			Pattern pattern = Pattern.compile("(.*)([\\?\\&])(" + param + "=[^\\&]*)(\\&.*)?");
			Matcher matcher = pattern.matcher( newUrl );
			if( matcher.matches() ){
				newUrl = newUrl.replaceFirst(matcher.group(3), param + "=" + value);
				addedWithRegex = true;
			}
		}catch(Exception e){
			// regexp error
		}

		if( !addedWithRegex )
			newUrl += ( newUrl.indexOf(	"?" ) >= 0 ? "&" : "?" ) + param + "=" + WebUtils.parseQueryValue(value);
		return newUrl;
	}

	public static String getCleverUrl( String path, Course course, Content content, String recallUrl ) throws MalformedURLException, UnsupportedEncodingException{
		StringBuffer sBuf = new StringBuffer( getFullPath( path ) );
		if( sBuf.indexOf( "?" ) >= 0 ){
			sBuf.append( "&" );
		}else{
			sBuf.append( "?" );
		}
		sBuf.append( "course_id=" + course.getId().toExternalString() );
		if( content != null )
			sBuf.append( "&content_id=" + content.getId().toExternalString() );	

		if( recallUrl != null )
			return addQueryParam( sBuf.toString(), "recallUrl", recallUrl );
		else
			return sBuf.toString();
	}

	public static String getCleverUrl( String path, Course course, String recallUrl ) throws MalformedURLException, UnsupportedEncodingException{
		return getCleverUrl( path, course, null, recallUrl );
	}

	public static String getSortUrl(HttpServletRequest request) throws java.io.UnsupportedEncodingException{
		StringBuffer sortUrlBuffer = new StringBuffer();
		if( request != null ){
			for( Enumeration e = request.getParameterNames(); e.hasMoreElements(); ){
				String paramName = (String) e.nextElement();
				String paramValue = request.getParameter( paramName );
				if( isValidSortUrlParam(paramName) && paramValue.indexOf("&") < 0 ){
					if( sortUrlBuffer.length() > 0 )
						sortUrlBuffer.append("&");
					else
						sortUrlBuffer.append("?");
						
					sortUrlBuffer.append( paramName + "=" + paramValue ); // no escaping, bizarrely Bb does this regardless!
				}
			}
			
		}
		return sortUrlBuffer.toString();
	}
	
	private static boolean isValidSortUrlParam( String paramName ) throws java.io.UnsupportedEncodingException{
		String[] bannedParams = new String[]{ "bsession", "bsession_str" };
		for( int i=0; i < bannedParams.length; i++ ){
			if( bannedParams[ i ].equals( paramName ) )
				return false;
			else if( !paramName.equals( WebUtils.parseQueryValue(paramName)))
				return false;
		}
		return true;
	}

	public static ContentDbLoader getContentDbLoader() throws PersistenceException{
		return (ContentDbLoader) BbServiceManager.getPersistenceService().getDbPersistenceManager().getLoader( ContentDbLoader.TYPE );
	}

    public static ContentDbPersister getContentDbPersister() throws PersistenceException {
		return ((ContentDbPersister) getBbPersistenceManager().getPersister(ContentDbPersister.TYPE));
    }
    
	public static boolean isMatchingId(Id id1, Id id2){
		try{
			return (id1.compareTo(id2) == 0);
		}catch(Exception e){
			return false;
		}
	}
	


	/**
	 * Gets a safe system bundle string for the given context.
	 * returns key if not found
	 * @param key   string key name
	 * @param ctx   the bb context
	public static String getSafeSystemString( String key, Locale locale ) {
		try{
			return BbUtils.getSystemString( key, locale );
		}catch( BundleKeyNotFoundException bknfe ){
			PerceptionLog log = PerceptionServiceManager.getPerceptionLog();
			log.logError( "Key not found: " + key, bknfe );
			return key;
		}
	}
	 */

	/**
	 * Gets a safe course bundle string for the given context.
	 * returns key if not found
	 * @param key   string key name
	 * @param ctx   the bb context
	public static String getSafeCourseString( String key, Locale locale ) {
		try{
			return BbUtils.getCourseString( key, locale );
		}catch( BundleKeyNotFoundException bknfe ){
			PerceptionLog log = PerceptionServiceManager.getPerceptionLog();
			log.logError( "Key not found: " + key, bknfe );
			return key;
		}
	}
	 */

    public static OutcomeDefinitionCategoryDbLoader getOutcomeDefinitionCategoryDbLoader(BbPersistenceManager bbPM) throws PersistenceException {
		return ((OutcomeDefinitionCategoryDbLoader) bbPM.getLoader(OutcomeDefinitionCategoryDbLoader.TYPE));
    }

    public static OutcomeDefinitionCategoryDbPersister getOutcomeDefinitionCategoryDbPersister(BbPersistenceManager bbPM) throws PersistenceException {
		return ((OutcomeDefinitionCategoryDbPersister) bbPM.getPersister(OutcomeDefinitionCategoryDbPersister.TYPE));
    }

    public static OutcomeDefinitionScaleDbLoader getOutcomeDefinitionScaleDbLoader(BbPersistenceManager bbPM) throws PersistenceException {
		return ((OutcomeDefinitionScaleDbLoader) bbPM.getLoader(OutcomeDefinitionScaleDbLoader.TYPE));
    }

    public static OutcomeDefinitionScaleDbPersister getOutcomeDefinitionScaleDbPersister(BbPersistenceManager bbPM) throws PersistenceException {
		return ((OutcomeDefinitionScaleDbPersister) bbPM.getPersister(OutcomeDefinitionScaleDbPersister.TYPE));
    }

    public static OutcomeDefinitionDbLoader getOutcomeDefinitionDbLoader(BbPersistenceManager bbPM) throws PersistenceException {
		return ((OutcomeDefinitionDbLoader) bbPM.getLoader(OutcomeDefinitionDbLoader.TYPE));
    }

    public static OutcomeDefinitionDbPersister getOutcomeDefinitionDbPersister(BbPersistenceManager bbPM) throws PersistenceException {
		return ((OutcomeDefinitionDbPersister) bbPM.getPersister(OutcomeDefinitionDbPersister.TYPE));
    }

	
    public static AttemptDbLoader getAttemptDbLoader(BbPersistenceManager bbPM) throws PersistenceException {
		return ((AttemptDbLoader) bbPM.getLoader(AttemptDbLoader.TYPE));
    }
	
    /*
    public static AttemptDbPersister getAttemptDbPersister(BbPersistenceManager bbPM) throws PersistenceException {
		return ((AttemptDbPersister) bbPM.getPersister(AttemptDbPersister.TYPE));
    }
	*/
    
    public static OutcomeDbLoader getOutcomeDbLoader(BbPersistenceManager bbPM) throws PersistenceException {
		return ((OutcomeDbLoader) bbPM.getLoader(OutcomeDbLoader.TYPE));
    }

	/**
	 * Orders a list of attempts to set the first, best or last as the selected attempt
	 * @see com.qm.bb6.perception.config.CourseSettings
	public static void sortAttempts( Outcome outcome, int selectionType ) throws BbServiceException, InitializationException, PersistenceException {
		
		AttemptsSorter sorter = getAttemptsSorter();
		sorter.sortAttempts( outcome, selectionType );
		
	}
	
	public static boolean isAverageDisplayed( OutcomeDefinition outcomeDef ) throws BbServiceException, InitializationException {
		AttemptsSorter sorter = getAttemptsSorter();
		return sorter.isAverageDisplayed(outcomeDef);
	}
	
	public static AttemptsSorter getAttemptsSorter() throws BbServiceException, InitializationException {
		AttemptsSorter sorter;
		if( isAtLeastVersion(7,1) ){
			try{
				Class sorterClass = Class.forName( "com.qm.bb6.perception.util.AttemptsSorter71Impl" );
				sorter = (AttemptsSorter) sorterClass.newInstance();
			}catch( Exception e ){
				e.printStackTrace();
				sorter = new AttemptsSorter6Impl();
			}
		}else{
			sorter = new AttemptsSorter6Impl();
		}
		return sorter;
	}
	 */
	
	public static Version getBbVersion() throws BbServiceException,InitializationException {
		PlugInManager pinManager = (PlugInManager) BbServiceManager.lookupService(PlugInManager.class);
		return pinManager.getPlatformVersion();
	}
	
	public static boolean isVersion( int major, int minor ) throws BbServiceException,InitializationException {
		Version version = getBbVersion();
		if( version.getMajor() == major && version.getMinor() == minor )
			return true;
		return false;
	}

	public static boolean isAtLeastVersion( int major, int minor ) throws BbServiceException,InitializationException {
		Version version = getBbVersion();
		Version minVersion = new Version( major, minor, 1 );
		if( version.compare( minVersion ) == Version.LESS_THAN )
			return false;
		return true;
	}
	
	public static boolean isNullOrEmpty( Object source ){
		
		if( source == null )
			return true;
		if( source instanceof String ){
			if( ((String) source).trim().length() == 0 )
				return true;
		}else if( source.getClass().isArray() ){
			if( ((Object[]) source).length == 0 )
				return true;
		}else if( source instanceof List ){
			if( ((List) source).isEmpty() )
				return true;
		}else if( source instanceof Id ){
			if( ((Id) source).isSet() && ((Id) source) != Id.UNSET_ID ){
				return false;
			}else{
				return true;
			}
		}
		return false;
	}

}
