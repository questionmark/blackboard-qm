/*
 * AssessmentTreeItemFilter.java
 *
 * Created on October 25, 2007, 11:13 AM
 *
 * Copyright 2006 VLE Genius Ltd. All rights reserved.
 */

package com.qm.bb6.perception.util;


import java.lang.reflect.Method;
import java.util.*;

import blackboard.base.ListFilter;

import com.qm.bb6.perception.data.AssessmentTreeItem;

/**
 *
 * @author Matt Elton
 */
public class AssessmentTreeItemFilter extends ListFilter {
	
	private String search_string;
	
	public AssessmentTreeItemFilter(String text){
		search_string = text;
	}

	protected boolean passesFilter(Object object){
		if(object == null || !(object instanceof AssessmentTreeItem ) )
			return false;
		else if( BbUtils.isNullOrEmpty(search_string) )
			return true;
		
		else{
			AssessmentTreeItem item = (AssessmentTreeItem) object;
			if( item.getName().toLowerCase().startsWith( search_string.toLowerCase() ) )
				return true;
			else
				return false;
		}
	}
	
	
}