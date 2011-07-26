/*
 * PerceptionAssessmentFolderMembershipMembershipPersisterImpl.java
 *
 * Created on February 15, 2007, 8:51 PM
 *
 * Copyright 2006 VLE Genius Ltd. All rights reserved.
 */

package com.qm.bb6.perception.data.impl;

import java.util.*;

import org.apache.soap.rpc.Parameter;
import org.apache.soap.encoding.SOAPMappingRegistry;

import com.qm.bb6.perception.data.*;
import com.qm.bb6.perception.service.*;
import com.qm.bb6.perception.security.AccessDeniedException;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class PerceptionAssessmentFolderMembershipPersisterImpl extends PerceptionSOAPRequestor implements PerceptionAssessmentFolderMembershipPersister {
	
	public static final String UPDATE_MEMBERSHIP = "AssignAdministratorToAssessmentFolder";

	public void persist( PerceptionAssessmentFolderMembership membership ) throws PerceptionDataException{
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		Vector bodyEntries = new Vector();
		bodyEntries.add( new Parameter( AssessmentFolderMembershipDeserializer.ADMINISTRATOR_ID_PARAM, String.class, membership.getAdministratorId(), null ) );
		bodyEntries.add( new Parameter( AssessmentFolderMembershipDeserializer.FOLDER_ID_PARAM, String.class, membership.getFolderId(), null ) );
		bodyEntries.add( new Parameter( AssessmentFolderMembershipDeserializer.PERMISSIONS_PARAM, String.class, String.valueOf(membership.getPermissions()), null ) );
		addIDMapping( smr, AssessmentFolderMembershipDeserializer.ADMINISTRATOR_ID_PARAM );
		addIDMapping( smr, AssessmentFolderMembershipDeserializer.FOLDER_ID_PARAM );
		addIDMapping( smr, AssessmentFolderMembershipDeserializer.PERMISSIONS_PARAM );
		
		super.load( UPDATE_MEMBERSHIP, bodyEntries, smr );
	}
	
	public void delete( PerceptionAssessmentFolderMembership membership ) throws PerceptionDataException{
		membership.setPermissions( PerceptionPermission.NO_ACCESS );
		persist( membership );
	}
	

}
