/*
 * @(#)PerceptionServiceManager.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.service;

import java.util.*;

import com.qm.bb6.perception.data.*;
import com.qm.bb6.perception.data.impl.*;
import com.qm.bb6.perception.service.impl.*;

/**
 * Service holder for all bridge services
 * such as logging
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class PerceptionServiceManager {

	private static final String SYSTEM_BUNDLE = "com.qm.bb6.perception.service.bundles.system";
	private static final String COURSE_BUNDLE = "com.qm.bb6.perception.service.bundles.course";
	private static final String ERROR_CODE_BUNDLE = "com.qm.bb6.perception.service.bundles.error_codes";
	
	private static PerceptionLog _log;
	private static Locale _defaultLocale;
	private static Map _systemBundleCache;
	private static Map _courseBundleCache;
	private static Map _errorCodeBundleCache;
	
	static {
		
		_log = new PerceptionLogImpl( PerceptionLog.NORMAL_LOGGING ); // TODO: add debug etc
		_defaultLocale = Locale.US;
		_systemBundleCache = new HashMap();
		_courseBundleCache = new HashMap();
		_errorCodeBundleCache = new HashMap();
	}
	
	public static PerceptionLog getPerceptionLog(){
		return _log;
	}

	public static AssessmentTreeItemLoader getAssessmentTreeItemLoader(){
		return new AssessmentTreeItemLoaderImpl();
	}	

	public static PerceptionAssessmentLoader getPerceptionAssessmentLoader(){
		return new PerceptionAssessmentLoaderImpl();
	}	

	public static PerceptionGroupLoader getPerceptionGroupLoader(){
		return new PerceptionGroupLoaderImpl();
	}	

	public static PerceptionGroupPersister getPerceptionGroupPersister(){
		return new PerceptionGroupPersisterImpl();
	}	

	public static PerceptionGroupMembershipPersister getPerceptionGroupMembershipPersister(){
		return new PerceptionGroupMembershipPersisterImpl();
	}	

	public static PerceptionUserLoader getPerceptionUserLoader(){
		return new PerceptionUserLoaderImpl();
	}	

	public static PerceptionUserPersister getPerceptionUserPersister(){
		return new PerceptionUserPersisterImpl();
	}	

	public static PerceptionScheduleLoader getPerceptionScheduleLoader(){
		return new PerceptionScheduleLoaderImpl();
	}	

	public static PerceptionSchedulePersister getPerceptionSchedulePersister(){
		return new PerceptionSchedulePersisterImpl();
	}	
	
	public static PerceptionAssessmentFolderLoader getPerceptionAssessmentFolderLoader(){
		return new PerceptionAssessmentFolderLoaderImpl();
	}
	
	public static PerceptionAssessmentFolderPersister getPerceptionAssessmentFolderPersister(){
		return new PerceptionAssessmentFolderPersisterImpl();
	}
	
	public static PerceptionAssessmentFolderMembershipPersister getPerceptionAssessmentFolderMembershipPersister(){
		return new PerceptionAssessmentFolderMembershipPersisterImpl();
	}
	
	public static PerceptionTopicLoader getPerceptionTopicLoader(){
		return new PerceptionTopicLoaderImpl();
	}
	
	public static PerceptionTopicPersister getPerceptionTopicPersister(){
		return new PerceptionTopicPersisterImpl();
	}
	
	public static PerceptionTopicMembershipPersister getPerceptionTopicMembershipPersister(){
		return new PerceptionTopicMembershipPersisterImpl();
	}
	
	
	/**
	 * Gets the system specific bundle
	 * @param locale   the locale to fetch
	 * @throws java.util.MissingResourceException    - if not found
	 */
	public static ResourceBundle getSystemResourceBundle( Locale locale ) {
		if( locale == null )
			locale = _defaultLocale;
			
		if( _systemBundleCache.containsKey( locale ) ){
			return (ResourceBundle) _systemBundleCache.get( locale );
		}else{
			ResourceBundle bundle = ResourceBundle.getBundle( SYSTEM_BUNDLE, locale );
			_systemBundleCache.put( locale, bundle );
			return bundle;
		}
	}

	/**
	 * Gets the system specific bundle using the default Locale
	 * @throws java.util.MissingResourceException    - if not found
	 */
	public static ResourceBundle getSystemResourceBundle() {
		return getSystemResourceBundle( _defaultLocale );
	}

	/**
	 * Gets the course specific bundle
	 * @param locale   the locale to fetch
	 * @throws java.util.MissingResourceException    - if not found
	 */
	public static ResourceBundle getCourseResourceBundle( Locale locale ) {
		if( locale == null )
			locale = _defaultLocale;
			
		if( _courseBundleCache.containsKey( locale ) ){
			return (ResourceBundle) _courseBundleCache.get( locale );
		}else{
			ResourceBundle bundle = ResourceBundle.getBundle( COURSE_BUNDLE, locale );
			_courseBundleCache.put( locale, bundle );
			return bundle;
		}
	}

	/**
	 * Gets the course specific bundle using the default Locale
	 * @throws java.util.MissingResourceException    - if not found
	 */
	public static ResourceBundle getCourseResourceBundle() {
		return getCourseResourceBundle( _defaultLocale );
	}

	/**
	 * Gets the error codes specific bundle
	 * @param locale   the locale to fetch
	 * @throws java.util.MissingResourceException    - if not found
	 */
	public static ResourceBundle getErrorCodeResourceBundle( Locale locale ) {
		if( locale == null )
			locale = _defaultLocale;
			
		if( _errorCodeBundleCache.containsKey( locale ) ){
			return (ResourceBundle) _errorCodeBundleCache.get( locale );
		}else{
			ResourceBundle bundle = ResourceBundle.getBundle( ERROR_CODE_BUNDLE, locale );
			_errorCodeBundleCache.put( locale, bundle );
			return bundle;
		}
	}

	/**
	 * Gets the error codes  bundle using the default Locale
	 * @throws java.util.MissingResourceException    - if not found
	 */
	public static ResourceBundle getErrorCodeResourceBundle() {
		return getErrorCodeResourceBundle( _defaultLocale );
	}


}
