/*
 * @(#)AssessmentTreeItem.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.data;


/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class AssessmentTreeItem {

	public static final int ASSESSMENT_FOLDER = 0;
	public static final int ASSESSMENT = 1;
	
	private String Item_Id = null;
	private int Type = 0;
	private String Name = null;
	private String Parent_Id = null;

	public AssessmentTreeItem() {
		//TODO
	}

	public String getItemId(){
		return Item_Id;
	}

	public int getType(){
		return Type;
	}

	public String getName(){
		return Name;
	}

	public String getParentId(){
		return Parent_Id;
	}

	public void setItemId( String value ){
		Item_Id = value;
	}

	public void setType( int value ){
		if( value != ASSESSMENT_FOLDER && value != ASSESSMENT )
			throw new IllegalArgumentException("Value must be " + ASSESSMENT_FOLDER + " or " + ASSESSMENT);
		Type = value;
	}

	public void setName( String value ){
		Name = value;
	}

	public void setParentId( String value ){
		Parent_Id = value;
	}



}