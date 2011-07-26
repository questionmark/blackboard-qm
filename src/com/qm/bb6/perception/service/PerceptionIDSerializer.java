/*
 * @(#)PerceptionIDSerializer.java 1.0.1 Sep 1 2005
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

import org.apache.soap.*;
import org.apache.soap.encoding.soapenc.*;
import org.apache.soap.rpc.*;
import org.apache.soap.util.*;
import org.apache.soap.util.xml.*;

import com.qm.bb6.perception.util.SOAPUtils;
/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class PerceptionIDSerializer implements Serializer {
		
	public void marshall(	String inScopeEncStyle, Class javaType, Object src,
	               			Object context, Writer sink, NSStack nsStack,
	               			XMLJavaMappingRegistry xjmr, SOAPContext ctx) throws IllegalArgumentException, IOException {
	
		nsStack.pushScope();
		
		if( src != null ){
		
			sink.write("<" + context);
			sink.write(" xmlns=\"" + PerceptionSOAPRequestor.QMWISe_NS + "\"");
			sink.write(">");
			if( src instanceof String )
				sink.write( SOAPUtils.parseSOAPValue((String) src));
			else 
				sink.write( SOAPUtils.parseSOAPValue(String.valueOf(src)));
			sink.write("</" + context + '>');
		
		} else {
			// do not add
			//SoapEncUtils.generateNullStructure(inScopeEncStyle, javaType, context, sink, nsStack, xjmr);
		}
		nsStack.popScope();
	
	}	

}
