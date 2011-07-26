/*
 * @(#)PerceptionUserLoader.java 1.0.1 Sep 1 2005
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


/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public interface PerceptionUserLoader {
	
	PerceptionUser getAdministratorById( String administrator_id ) throws PerceptionDataException;
	
	PerceptionUser getAdministratorByName( String userName ) throws PerceptionDataException;

	List getAdministratorsByGroupId( String group_id ) throws PerceptionDataException;

	List getAllAdministrators() throws PerceptionDataException;

	PerceptionUser getParticipantById( String participant_id ) throws PerceptionDataException;
	
	PerceptionUser getParticipantByName( String userName ) throws PerceptionDataException;

	List getParticipantsByGroupId( String group_id ) throws PerceptionDataException;

	List getAllParticipants() throws PerceptionDataException;
	
}
