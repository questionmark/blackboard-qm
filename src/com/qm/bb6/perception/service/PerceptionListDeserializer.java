/*
 * @(#)PerceptionListDeserializer.java 1.0.1 Sep 1 2005
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
import org.apache.soap.rpc.*;
import org.apache.soap.util.*;
import org.apache.soap.util.xml.*;

import blackboard.base.BbList;
/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public abstract class PerceptionListDeserializer extends PerceptionDeserializer {
		
	public abstract String getElementTagName();
	
	public Bean unmarshall(String inScopeEncStyle, QName elementType, Node src, XMLJavaMappingRegistry xjmr, SOAPContext ctx) 
																										throws IllegalArgumentException {
	
		Element root = (Element) src;
		ArrayList objectList = new BbList(); // altered for 4.4
			
		NodeList elementList = root.getElementsByTagName( getElementTagName() );
		if( elementList.getLength() > 0){ // muliple
			for (int i = 0; i < elementList.getLength(); i++) {
				Node node = elementList.item(i);
				if(node instanceof Element){
					Bean elementBean = super.unmarshall( inScopeEncStyle, elementType, node, xjmr, ctx );
					objectList.add( elementBean.value );
				}
			}
		}
		return new Bean( List.class, objectList );
		
	}
	
	
}
