/*
 * @(#)PerceptionUserPersister.java 1.0.1 Sep 1 2005
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
public class PerceptionUserPersisterImpl extends PerceptionSOAPRequestor implements PerceptionUserPersister {
	
	public static final String CREATE_ADMINISTRATOR_4_2 = "CreateAdministrator";
	public static final String CREATE_ADMINISTRATOR_4_3 = "CreateAdministratorChangePasswordAtLogin";
	
//	public static final String UPDATE_ADMINISTRATOR = "SetAdministrator";
//	public static final String DELETE_ADMINISTRATOR_BY_ID = "DeleteAdministrator";

	public static final String CREATE_PARTICIPANT = "CreateParticipant";
	public static final String UPDATE_PARTICIPANT = "SetParticipant";
	public static final String DELETE_PARTICIPANT_BY_ID = "DeleteParticipant";


	public void persist( PerceptionUser user ) throws PerceptionDataException{
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		if( user.getRole() == PerceptionUser.PERCEPTION_PARTICIPANT ){
			PerceptionObjectSerializer serializer = new PerceptionObjectSerializer( ParticipantDeserializer.getParticipantParameterMap());
			addRequestMapping( smr, ParticipantDeserializer.TAG_NAME, serializer, PerceptionParticipant.class );
			if( user.getUserId() == null ){
				String id = insert( CREATE_PARTICIPANT, ParticipantDeserializer.TAG_NAME, ParticipantDeserializer.PARTICIPANT_ID_PARAM, user, smr);
				user.setUserId( id );
			}else{
				update( UPDATE_PARTICIPANT, ParticipantDeserializer.TAG_NAME, user, smr);
			}
		}else if( user.getRole() == PerceptionUser.PERCEPTION_ADMINISTRATOR ){
			PerceptionObjectSerializer serializer = new PerceptionObjectSerializer( AdministratorDeserializer.getAdministratorParameterMap());
			addRequestMapping( smr, AdministratorDeserializer.TAG_NAME, serializer, PerceptionAdministrator.class );
			if( user.getUserId() == null ){
				String id;
				try{
					id = insert( CREATE_ADMINISTRATOR_4_3, AdministratorDeserializer.TAG_NAME, AdministratorDeserializer.ADMINISTRATOR_ID_PARAM, user, smr);
				}catch(PerceptionDataException e){
					// maybe method wasn't found, try 4.2 old method
					id = insert( CREATE_ADMINISTRATOR_4_2, AdministratorDeserializer.TAG_NAME, AdministratorDeserializer.ADMINISTRATOR_ID_PARAM, user, smr);
				}
				user.setUserId( id );
			}else{
			//	update( UPDATE_ADMINISTRATOR, AdministratorDeserializer.TAG_NAME, user, smr);
			}
		}
	}
	
	public void delete( PerceptionUser user ) throws PerceptionDataException{
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		if( user.getRole() == PerceptionUser.PERCEPTION_PARTICIPANT ){
			deleteById( DELETE_PARTICIPANT_BY_ID, ParticipantDeserializer.PARTICIPANT_ID_PARAM, user.getUserId(), smr );
		}else if( user.getRole() == PerceptionUser.PERCEPTION_ADMINISTRATOR ){
//			deleteById( DELETE_ADMINISTRATOR_BY_ID, AdministratorDeserializer.ADMINISTRATOR_ID_PARAM, user.getUserId(), smr );
		}
		
	}
	

}
