/*
 * @(#)ParticipantListDeserializer.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.data.impl;

import java.util.*;

import com.qm.bb6.perception.data.*;
import com.qm.bb6.perception.service.*;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class ParticipantListDeserializer extends PerceptionListDeserializer {
		
	public static final String LIST_TAG_NAME = "ParticipantList";

	public PerceptionSerializerMap getParameterMap(){
		return ParticipantDeserializer.getParticipantParameterMap();
	}
	
	public Class getSerializedClass(){
		return PerceptionParticipant.class;
	}
	
	public String getElementTagName(){
		return ParticipantDeserializer.TAG_NAME;
	}
		
}
