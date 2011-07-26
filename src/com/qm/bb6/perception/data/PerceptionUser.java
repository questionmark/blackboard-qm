/*
 * @(#)PerceptionUser.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.data;

import java.util.Calendar;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public abstract class PerceptionUser {

	public static final int PERCEPTION_ADMINISTRATOR = 0;
	public static final int PERCEPTION_PARTICIPANT = 1;
	
	private String user_id = null;
	private String user_name = null;
	private String password = null;
	private int authenticate_ext = 0;
	private String first_name = null;
	private String last_name = null;
	private String middle_name = null;
	private int use_correspondence = 0;
	private String primary_address1 = null;
	private String primary_address2 = null;
	private String primary_city = null;
	private String primary_state = null;
	private String primary_ZIP = null;
	private String primary_country = null;
	private String primary_phone = null;
	private String primary_fax = null;
	private String primary_email = null;
	private String salutation = null;
	private String organization_name = null;
	private String department = null;
	private String title = null;
	private String gender = null;
	private Calendar date_registration = null;
	private String profile_name = null;
	private String URL = null;


	public PerceptionUser() {
		//TODO
	}

	public String getUserId(){
		return user_id;
	}

	public String getUserName(){
		return user_name;
	}

	public String getPassword(){
		return password;
	}

	public int getAuthenticateExt(){
		return authenticate_ext;
	}

	public String getFirstName(){
		return first_name;
	}

	public String getLastName(){
		return last_name;
	}

	public String getMiddleName(){
		return middle_name;
	}

	public int getUseCorrespondence(){
		return use_correspondence;
	}

	public String getPrimaryAddress1(){
		return primary_address1;
	}

	public String getPrimaryAddress2(){
		return primary_address2;
	}

	public String getPrimaryCity(){
		return primary_city;
	}

	public String getPrimaryState(){
		return primary_state;
	}

	public String getPrimaryZIP(){
		return primary_ZIP;
	}

	public String getPrimaryCountry(){
		return primary_country;
	}

	public String getPrimaryPhone(){
		return primary_phone;
	}

	public String getPrimaryFax(){
		return primary_fax;
	}

	public String getPrimaryEmail(){
		return primary_email;
	}

	public String getSalutation(){
		return salutation;
	}

	public String getOrganizationName(){
		return organization_name;
	}

	public String getDepartment(){
		return department;
	}

	public String getTitle(){
		return title;
	}

	public String getGender(){
		return gender;
	}

	public Calendar getDateRegistration(){
		return date_registration;
	}

	public String getProfileName(){
		return profile_name;
	}

	public String getURL(){
		return URL;
	}



	public void setUserId( String value ){
		user_id = value;
	}

	public void setUserName( String value ){
		user_name = value;
	}

	public void setPassword( String value ){
		password = value;
	}

	public void setAuthenticateExt( int value ){
		authenticate_ext = value;
	}

	public void setFirstName( String value ){
		first_name = value;
	}

	public void setLastName( String value ){
		last_name = value;
	}

	public void setMiddleName( String value ){
		middle_name = value;
	}

	public void setUseCorrespondence( int value ){
		use_correspondence = value;
	}

	public void setPrimaryAddress1( String value ){
		primary_address1 = value;
	}

	public void setPrimaryAddress2( String value ){
		primary_address2 = value;
	}

	public void setPrimaryCity( String value ){
		primary_city = value;
	}

	public void setPrimaryState( String value ){
		primary_state = value;
	}

	public void setPrimaryZIP( String value ){
		primary_ZIP = value;
	}

	public void setPrimaryCountry( String value ){
		primary_country = value;
	}

	public void setPrimaryPhone( String value ){
		primary_phone = value;
	}

	public void setPrimaryFax( String value ){
		primary_fax = value;
	}

	public void setPrimaryEmail( String value ){
		primary_email = value;
	}

	public void setSalutation( String value ){
		salutation = value;
	}

	public void setOrganizationName( String value ){
		organization_name = value;
	}

	public void setDepartment( String value ){
		department = value;
	}

	public void setTitle( String value ){
		title = value;
	}

	public void setGender( String value ){
		gender = value;
	}

	public void setDateRegistration( Calendar value ){
		date_registration = value;
	}

	public void setProfileName( String value ){
		profile_name = value;
	}

	public void setURL( String value ){
		URL = value;
	}

	// abstracts
	
	public abstract int getRole();
	
}