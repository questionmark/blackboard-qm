/*
 * PerceptionTopicMembershipPersisterImpl.java
 *
 * Created on February 16, 2007, 10:56 AM
 *
 * Copyright 2006 VLE Genius Ltd. All rights reserved.
 */

package com.qm.bb6.perception.data.impl;

import java.util.*;

import org.apache.soap.encoding.SOAPMappingRegistry;
import org.apache.soap.rpc.Parameter;

import com.qm.bb6.perception.data.*;
import com.qm.bb6.perception.service.*;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class PerceptionTopicMembershipPersisterImpl extends PerceptionSOAPRequestor implements PerceptionTopicMembershipPersister {
	
	public static final String UPDATE_MEMBERSHIP = "AssignAdministratorToTopic";

	public void persist( PerceptionTopicMembership membership ) throws PerceptionDataException{

		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		Vector bodyEntries = new Vector();
		bodyEntries.add( new Parameter( TopicMembershipDeserializer.ADMINISTRATOR_ID_PARAM, String.class, membership.getAdministratorId(), null ) );
		bodyEntries.add( new Parameter( TopicMembershipDeserializer.TOPIC_ID_PARAM, String.class, membership.getTopicId(), null ) );
		bodyEntries.add( new Parameter( TopicMembershipDeserializer.PERMISSIONS_PARAM, String.class, String.valueOf(membership.getPermissions()), null ) );
		addIDMapping( smr, TopicMembershipDeserializer.ADMINISTRATOR_ID_PARAM );
		addIDMapping( smr, TopicMembershipDeserializer.TOPIC_ID_PARAM );
		addIDMapping( smr, TopicMembershipDeserializer.PERMISSIONS_PARAM );
		
		super.load( UPDATE_MEMBERSHIP, bodyEntries, smr );

	}
	
	public void delete( PerceptionTopicMembership membership ) throws PerceptionDataException{
		membership.setPermissions( PerceptionPermission.NO_ACCESS );
		persist( membership );
	}
	
}
