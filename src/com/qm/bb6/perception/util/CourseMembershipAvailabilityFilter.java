/*
 * CourseMembershipAvailabilityFilter.java
 *
 * Created on January 16, 2008, 2:54 PM
 *
 * Copyright 2006 VLE Genius Ltd. All rights reserved.
 */

package com.qm.bb6.perception.util;


import blackboard.base.*;
import blackboard.data.course.CourseMembership;

/**
 * @author Matt Elton
 */
public class CourseMembershipAvailabilityFilter extends ListFilter {

	protected boolean passesFilter(Object object){
		if(object == null || !(object instanceof CourseMembership))
			return false;
		else{
			CourseMembership cm = (CourseMembership) object;
			return cm.getIsAvailable();
		}
	}
}