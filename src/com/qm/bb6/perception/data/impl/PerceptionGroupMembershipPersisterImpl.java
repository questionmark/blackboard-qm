/*
 * @(#)PerceptionGroupMembershipPersisterImpl.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.data.impl;

import java.util.*;

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
public class PerceptionGroupMembershipPersisterImpl extends PerceptionSOAPRequestor implements PerceptionGroupMembershipPersister {
	
	public static final String CREATE_GROUP_PARTICPIANTS = "AddGroupParticipantList";
	public static final String DELETE_GROUP_PARTICPIANTS = "DeleteGroupParticipantList";

	public static final String CREATE_GROUP_ADMINISTRATORS = "AddGroupAdministratorList";
	public static final String DELETE_GROUP_ADMINISTRATORS = "DeleteGroupAdministratorList";

	public void persist( PerceptionGroupMembership enrolment ) throws PerceptionDataException{
		List list = new ArrayList();
		list.add( enrolment );
		if( enrolment.getIsAdmin() )
			addAdministrators( list );
		else
			addParticipants( list );
	}	

	public void addAdministrators( List enrolments ) throws PerceptionDataException{
		persist( enrolments, true, CREATE_GROUP_ADMINISTRATORS );
	}

	public void addParticipants( List enrolments ) throws PerceptionDataException{
		persist( enrolments, false, CREATE_GROUP_PARTICPIANTS );
	}
	
	private void persist( List enrolments, boolean doAdmin, String methodName ) throws PerceptionDataException{
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		addRequestMapping( smr, "", new GroupMembershipSerializer(doAdmin), enrolments.getClass() );
		update( methodName, "", enrolments, smr);
	}
	
	public void delete( PerceptionGroupMembership enrolment ) throws PerceptionDataException{
		List list = new ArrayList();
		list.add( enrolment );
		if( enrolment.getIsAdmin() )
			deleteAdministrators( list );
		else
			deleteParticipants( list );
	}	

	public void deleteAdministrators( List enrolments ) throws PerceptionDataException{
		delete( enrolments, true, DELETE_GROUP_ADMINISTRATORS );
	}

	public void deleteParticipants( List enrolments ) throws PerceptionDataException{
		delete( enrolments, false, DELETE_GROUP_PARTICPIANTS );
	}
	
	private void delete( List enrolments, boolean doAdmin, String methodName ) throws PerceptionDataException{
		try{
			SOAPMappingRegistry smr = new SOAPMappingRegistry();
			addRequestMapping( smr, "", new GroupMembershipSerializer(doAdmin), enrolments.getClass() );
			update( methodName, "", enrolments, smr);
		}catch(ObjectNotFoundException onfe){
			throw onfe;
		}catch(PerceptionDataException pde){
			throw new ObjectNotFoundException();
		}
	}

	

}
