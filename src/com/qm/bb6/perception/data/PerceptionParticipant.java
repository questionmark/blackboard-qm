/*
 * @(#)PerceptionParticipant.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.data;

import java.util.*;

import org.w3c.dom.*;


/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class PerceptionParticipant extends PerceptionUser {

	private List groupMemberships = null;
	
	public int getRole(){
		return PERCEPTION_PARTICIPANT;
	}
	
	public List getGroupMemberships(){
		return groupMemberships;
	}

	public void setGroupMemberships( List groupMemberships ){
		this.groupMemberships = groupMemberships;
	}

	public void setGroupMemberships( Element root ){
		
		if( root != null ){
			ArrayList objectList = new ArrayList();
				
			NodeList elementList = root.getElementsByTagName( "Group_ID" );
			if( elementList.getLength() > 0){ // muliple
				for (int i = 0; i < elementList.getLength(); i++) {
					Node node = elementList.item(i);
					if(node instanceof Element){
						PerceptionGroupMembership groupMembership = new PerceptionGroupMembership();
						groupMembership.setGroupId( node.getFirstChild().getNodeValue() );
						groupMembership.setUserId( getUserId() );
						groupMembership.setIsAdmin( false );
						objectList.add( groupMembership );
					}
				}
			}
			setGroupMemberships(objectList);
		}
	}
}