/*
 * @(#)PerceptionGroupMembershipPersister.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.data;

import java.util.List;

import com.qm.bb6.perception.service.*;
import com.qm.bb6.perception.security.AccessDeniedException;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public interface PerceptionGroupMembershipPersister {
	
	void persist( PerceptionGroupMembership enrolment ) throws PerceptionDataException;
	
	void delete( PerceptionGroupMembership enrolment ) throws PerceptionDataException;

	void addAdministrators( List enrolments ) throws PerceptionDataException;

	void addParticipants( List enrolments ) throws PerceptionDataException;
	
	void deleteAdministrators( List enrolments ) throws PerceptionDataException;

	void deleteParticipants( List enrolments ) throws PerceptionDataException;

}
