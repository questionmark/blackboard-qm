/*
 * @(#)GroupMembershipSerializer.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.data.impl;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import org.apache.soap.*;
import org.apache.soap.encoding.soapenc.*;
import org.apache.soap.rpc.*;
import org.apache.soap.util.*;
import org.apache.soap.util.xml.*;

import com.qm.bb6.perception.data.*;
import com.qm.bb6.perception.service.*;
import com.qm.bb6.perception.util.SOAPUtils;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class GroupMembershipSerializer implements Serializer {
		
	public static final String GROUP_ID_PARAMETER = GroupDeserializer.GROUP_ID_PARAM;
	public static final String ADMINISTRATOR_ID_PARAMETER = AdministratorDeserializer.ADMINISTRATOR_ID_PARAM;
	public static final String PARTICIPANT_ID_PARAMETER = ParticipantDeserializer.PARTICIPANT_ID_PARAM;

	public static final String PARTICIPANT_LIST_PARAMETER = "ParticipantIDList";
	public static final String ADMINISTRATOR_LIST_PARAMETER = "AdministratorIDList";

	private boolean do_admin_roles = false;
	
	public GroupMembershipSerializer( boolean do_admin_roles ){
		this.do_admin_roles = do_admin_roles;
	}
	
	public void marshall(	String inScopeEncStyle, Class javaType, Object src,
	               			Object context, Writer sink, NSStack nsStack,
	               			XMLJavaMappingRegistry xjmr, SOAPContext ctx) throws IllegalArgumentException, IOException {
	
		nsStack.pushScope();
		
		if( src != null ){
		
			List enrolments = (List) src;
			String group_id;
			
			Iterator iterator = (Iterator) enrolments.iterator();
			
			if( iterator.hasNext() ){
				PerceptionGroupMembership enrolment = (PerceptionGroupMembership) iterator.next();
				sink.write("<" + GROUP_ID_PARAMETER);
				sink.write(" xmlns=\"" + PerceptionSOAPRequestor.QMWISe_NS + "\"");
				sink.write(">");
				sink.write( SOAPUtils.parseSOAPValue(enrolment.getGroupId()) );
				sink.write("</" + GROUP_ID_PARAMETER + '>');
				if( do_admin_roles ){
					sink.write("<" + ADMINISTRATOR_LIST_PARAMETER);
				}else{
					sink.write("<" + PARTICIPANT_LIST_PARAMETER);
				}
				sink.write(" xmlns=\"" + PerceptionSOAPRequestor.QMWISe_NS + "\"");
				sink.write(">");
				addEnrolment( enrolment, sink );
				
				group_id = enrolment.getGroupId();
				
				while( iterator.hasNext() ){
					
					enrolment = (PerceptionGroupMembership) iterator.next();
					if( group_id.equalsIgnoreCase( enrolment.getGroupId() )){
						if( enrolment.getIsAdmin() == do_admin_roles ){
							addEnrolment( enrolment, sink );
						}else{
							throw new IllegalArgumentException("Enrolment list contains invalid roles");
						}
					}else{
						throw new IllegalArgumentException("Enrolment list contains mulitple groups");
					}
				}
				
				if( do_admin_roles ){
					sink.write("</" + ADMINISTRATOR_LIST_PARAMETER + '>');
				}else{
					sink.write("</" + PARTICIPANT_LIST_PARAMETER + '>');
				}
				
			}

		
		} else {
			// do not add
			//SoapEncUtils.generateNullStructure(inScopeEncStyle, javaType, context, sink, nsStack, xjmr);
		}
		nsStack.popScope();
	
	}	

	private void addEnrolment( PerceptionGroupMembership enrolment, Writer sink ) throws IOException{
		if( do_admin_roles ){
			sink.write("<" + ADMINISTRATOR_ID_PARAMETER + ">");
			sink.write( SOAPUtils.parseSOAPValue(enrolment.getUserId()) );
			sink.write("</" + ADMINISTRATOR_ID_PARAMETER + '>');
		}else{
			sink.write("<" + PARTICIPANT_ID_PARAMETER + ">");
			sink.write( SOAPUtils.parseSOAPValue(enrolment.getUserId()) );
			sink.write("</" + PARTICIPANT_ID_PARAMETER + '>');	
		}
		
	}
}
