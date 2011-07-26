/*
 * @(#)PerceptionIDDeserializer.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.service;

import java.io.*;
import java.util.*;
import org.w3c.dom.*;

import org.apache.soap.*;
import org.apache.soap.encoding.soapenc.*;
import org.apache.soap.rpc.*;
import org.apache.soap.util.*;
import org.apache.soap.util.xml.*;

/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class PerceptionIDDeserializer implements Deserializer {
	
	public Bean unmarshall(String inScopeEncStyle, QName elementType, Node src, XMLJavaMappingRegistry xjmr, SOAPContext ctx) 
								
																									throws IllegalArgumentException {
		// bug fix for QMWISe
		//  - returns embedded Schedule_ID on CreateSchedule
		if( src.getFirstChild() instanceof Element ){
			return new Bean( String.class, src.getFirstChild().getFirstChild().getNodeValue() );
		}																	
		return new Bean( String.class, src.getFirstChild().getNodeValue() );

	}
	
}
