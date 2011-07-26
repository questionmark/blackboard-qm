/*
 * @(#)PerceptionGroupPersisterImpl.java 1.0.1 Sep 1 2005
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
public class PerceptionGroupPersisterImpl extends PerceptionSOAPRequestor implements PerceptionGroupPersister {
	
	public static final String CREATE_GROUP = "CreateGroup";
	public static final String UPDATE_GROUP = "SetGroup";
	public static final String DELETE_GROUP_BY_ID = "DeleteGroup";

	public void persist( PerceptionGroup group ) throws PerceptionDataException{
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		PerceptionObjectSerializer serializer = new PerceptionObjectSerializer( GroupDeserializer.getGroupParameterMap());
		addRequestMapping( smr, GroupDeserializer.TAG_NAME, serializer, PerceptionGroup.class );
		if( group.getGroupId() == null ){
			String id = insert( CREATE_GROUP, GroupDeserializer.TAG_NAME, GroupDeserializer.GROUP_ID_PARAM, group, smr);
			group.setGroupId( id );
		}else{
			update( UPDATE_GROUP, GroupDeserializer.TAG_NAME, group, smr);
		}
	}
	
	public void delete( PerceptionGroup group ) throws PerceptionDataException{
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		deleteById( DELETE_GROUP_BY_ID, GroupDeserializer.GROUP_ID_PARAM, group.getGroupId(), smr );
		
	}
	

}
