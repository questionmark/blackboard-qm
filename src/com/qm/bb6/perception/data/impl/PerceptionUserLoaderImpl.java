/*
 * @(#)PerceptionUserLoaderImpl.java 1.0.1 Sep 1 2005
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
import com.qm.bb6.perception.security.AccessDeniedException;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class PerceptionUserLoaderImpl extends PerceptionSOAPRequestor implements PerceptionUserLoader {

	public static final String GET_ADMINISTRATOR_BY_ID = "GetAdministrator";
	public static final String GET_ADMINISTRATOR_BY_NAME = "GetAdministratorByName";
	public static final String GET_ADMINISTRATORS_BY_GROUP_ID = "GetAdministratorListByGroup";
	public static final String GET_ALL_ADMINISTRATORS = "GetAdministratorList";

	public static final String GET_PARTICIPANT_BY_ID = "GetParticipant";
	public static final String GET_PARTICIPANT_BY_NAME = "GetParticipantByName";
	public static final String GET_PARTICIPANTS_BY_GROUP_ID = "GetParticipantListByGroup";
	public static final String GET_ALL_PARTICIPANTS = "GetParticipantList";


	public PerceptionUser getAdministratorById( String user_id ) throws PerceptionDataException{
		return (PerceptionUser) loadById( GET_ADMINISTRATOR_BY_ID, AdministratorDeserializer.ADMINISTRATOR_ID_PARAM, user_id, getAdministratorMappingRegistry() );
	}
	
	public PerceptionUser getAdministratorByName( String userName ) throws PerceptionDataException{
		return (PerceptionUser) loadById( GET_ADMINISTRATOR_BY_NAME, AdministratorDeserializer.ADMINISTRATOR_NAME_PARAM, userName, getAdministratorMappingRegistry() );
	}

	public List getAdministratorsByGroupId( String group_id ) throws PerceptionDataException{
		return (List) loadById( GET_ADMINISTRATORS_BY_GROUP_ID, GroupDeserializer.GROUP_ID_PARAM, group_id, getAdministratorListMappingRegistry() );
	}
	
	public List getAllAdministrators() throws PerceptionDataException{
		return (List) load( GET_ALL_ADMINISTRATORS, getAdministratorListMappingRegistry() );
	}
	
	public PerceptionUser getParticipantById( String user_id ) throws PerceptionDataException{
		return (PerceptionUser) loadById( GET_PARTICIPANT_BY_ID, ParticipantDeserializer.PARTICIPANT_ID_PARAM, user_id, getParticipantMappingRegistry() );
	}
	
	public PerceptionUser getParticipantByName( String userName ) throws PerceptionDataException{
		return (PerceptionUser) loadById( GET_PARTICIPANT_BY_NAME, ParticipantDeserializer.PARTICIPANT_NAME_PARAM, userName, getParticipantMappingRegistry() );
	}

	public List getParticipantsByGroupId( String group_id ) throws PerceptionDataException{
		return (List) loadById( GET_PARTICIPANTS_BY_GROUP_ID, GroupDeserializer.GROUP_ID_PARAM, group_id, getParticipantListMappingRegistry() );
	}

	public List getAllParticipants() throws PerceptionDataException{
		return (List) load( GET_ALL_PARTICIPANTS, getParticipantListMappingRegistry() );
	}
	
	private SOAPMappingRegistry getAdministratorMappingRegistry(){
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		addResponseMapping( smr, AdministratorDeserializer.TAG_NAME, new AdministratorDeserializer(), PerceptionAdministrator.class );
		return smr;
	}

	private SOAPMappingRegistry getAdministratorListMappingRegistry(){
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		this.addListMapping( smr, AdministratorListDeserializer.LIST_TAG_NAME, new AdministratorListDeserializer() );
		return smr;
	}	

	private SOAPMappingRegistry getParticipantMappingRegistry(){
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		addResponseMapping( smr, ParticipantDeserializer.TAG_NAME, new ParticipantDeserializer(), PerceptionParticipant.class );
		return smr;
	}

	private SOAPMappingRegistry getParticipantListMappingRegistry(){
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		this.addListMapping( smr, ParticipantListDeserializer.LIST_TAG_NAME, new ParticipantListDeserializer() );
		return smr;
	}	
	


}
