/*
 * @(#)PerceptionGroupLoader.java 1.0.1 Sep 1 2005
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
public interface PerceptionGroupLoader {
	
	PerceptionGroup getGroupById( String group_id ) throws PerceptionDataException;
	
	PerceptionGroup getGroupByName( String groupName ) throws PerceptionDataException;

	List getGroupsByParticipantId( String user_id ) throws PerceptionDataException;

	List getGroupsByAdministratorId( String user_id ) throws PerceptionDataException;

	List getAllGroups() throws PerceptionDataException;
	
}
