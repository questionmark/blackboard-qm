/*
 * @(#)PerceptionGroupLoaderImpl.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.data.impl;

import java.util.*;

import org.apache.soap.Constants;
import org.apache.soap.encoding.SOAPMappingRegistry;
import org.apache.soap.encoding.soapenc.BeanSerializer;
import org.apache.soap.util.xml.QName;

import com.qm.bb6.perception.data.*;
import com.qm.bb6.perception.service.*;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class PerceptionGroupLoaderImpl extends PerceptionSOAPRequestor implements PerceptionGroupLoader {
	
	public static final String GET_GROUP_BY_ID = "GetGroup";
	public static final String GET_GROUP_BY_NAME = "GetGroupByName";
	public static final String GET_GROUPS_BY_PARTICIPANT_ID = "GetParticipantGroupList";
	public static final String GET_GROUPS_BY_ADMINISTRATOR_ID = "GetAdministratorGroupList";
	public static final String GET_ALL_GROUPS = "GetGroupList";


	public PerceptionGroup getGroupById( String group_id ) throws PerceptionDataException{
		return (PerceptionGroup) loadById( GET_GROUP_BY_ID, GroupDeserializer.GROUP_ID_PARAM, group_id, getMappingRegistry()  );
	}
	
	public PerceptionGroup getGroupByName( String groupName ) throws PerceptionDataException{
		return (PerceptionGroup) loadById( GET_GROUP_BY_NAME, GroupDeserializer.GET_GROUP_BY_NAME, groupName, getMappingRegistry()  );
	}

	public List getAllGroups() throws PerceptionDataException{
		return (List) load( GET_ALL_GROUPS, getListMappingRegistry() );
	}

	public List getGroupsByParticipantId( String group_id ) throws PerceptionDataException{
		return (List) loadById( GET_GROUPS_BY_PARTICIPANT_ID, ParticipantDeserializer.PARTICIPANT_ID_PARAM, group_id, getListMappingRegistry()  );
	}

	public List getGroupsByAdministratorId( String administrator_id ) throws PerceptionDataException{
		return (List) loadById( GET_GROUPS_BY_ADMINISTRATOR_ID, AdministratorDeserializer.ADMINISTRATOR_ID_PARAM, administrator_id, getListMappingRegistry());
	}

	private SOAPMappingRegistry getMappingRegistry(){
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		addResponseMapping( smr, GroupDeserializer.TAG_NAME, new GroupDeserializer(), PerceptionGroup.class );
		return smr;
	}

	private SOAPMappingRegistry getListMappingRegistry(){
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		this.addListMapping( smr, GroupListDeserializer.LIST_TAG_NAME, new GroupListDeserializer() );
		return smr;
	}
	
}
