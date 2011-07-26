/*
 * SelectAssessmentComparator.java
 *
 * Created on October 25, 2007, 10:16 AM
 *
 * Copyright 2006 VLE Genius Ltd. All rights reserved.
 */

package com.qm.bb6.perception.util;

import java.util.Comparator;

import com.qm.bb6.perception.data.AssessmentTreeItem;

/**
 *
 * @author Matt Elton
 */
public class SelectAssessmentComparator implements Comparator {
		
	public static final int ITEM_NAME = 1;
	public static final int ITEM_TYPE = 2;

	private int type = ITEM_TYPE;

	/**
	 * creates a new SelectAssessmentComparator of a specified type
	 * @param type
	 * @throws IllegalArgumentException   if type is invalid
	 */
	public SelectAssessmentComparator(int type) {
		this.type = type;
	}

	/**
	 * @return property key name
	 * @return label used for UI editing
	 */
	public int compare(Object cc1, Object cc2) throws ClassCastException{
		if(cc1 == null || cc2 == null)
			throw new ClassCastException("null value detected");
		if(cc1 instanceof AssessmentTreeItem && cc2 instanceof AssessmentTreeItem){
			AssessmentTreeItem treeItem1 = (AssessmentTreeItem) cc1;
			AssessmentTreeItem treeItem2 = (AssessmentTreeItem) cc2;
			int comparison;
			if( type == ITEM_NAME){
				comparison = compareStrings( treeItem1.getName(), treeItem2.getName() );
			}else{ //SELECTED
				comparison = compareType( treeItem1.getType(), treeItem2.getType() );
			}

			if( comparison == 0 ){
				if( type  != ITEM_NAME ){
					comparison = compareType( treeItem1.getType(), treeItem2.getType() );
				}else{
					comparison = compareStrings( treeItem1.getName(), treeItem2.getName() );
				}
			}


			return comparison;
		}else{
			throw new ClassCastException("Both values must be of the same type");
		}
	}


	private int compareStrings( String str1, String str2 ){
		if( str1 == null )
			return 1;
		else if( str2 == null)
			return -1;
		return str1.compareTo(str2);
	}

	private int compareType( int type1, int type2 ){ // true first
		if( type1 == type2 )
			return 0;
		else if( type1 == AssessmentTreeItem.ASSESSMENT_FOLDER )
			return -1;
		else
			return 1;
	}


	public boolean equals(Object comparator){
		return false;
	}

}
