/*
 * @(#)PerceptionObjectSerializer.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.service;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

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
public class PerceptionObjectSerializer implements Serializer {
		
	private PerceptionSerializerMap parameterMap = null;
	
	public PerceptionObjectSerializer(){
		// nothing to do
	}
	
	public PerceptionObjectSerializer(PerceptionSerializerMap parameterMap){
		this.parameterMap = parameterMap;
	}
	
	public PerceptionSerializerMap getParameterMap(){
		return this.parameterMap;
	}
	
	public void setParameterMap(PerceptionSerializerMap parameterMap){
		this.parameterMap = parameterMap;
	}
	
	public void marshall(	String inScopeEncStyle, Class javaType, Object src,
	               			Object context, Writer sink, NSStack nsStack,
	               			XMLJavaMappingRegistry xjmr, SOAPContext ctx) throws IllegalArgumentException, IOException {
	
		nsStack.pushScope();
		
		if( src != null ){
		
			PerceptionLog log = PerceptionServiceManager.getPerceptionLog();

			sink.write("<" + context);
			sink.write(" xmlns=\"" + PerceptionSOAPRequestor.QMWISe_NS + "\"");
			sink.write(">");
			
			try{
				PerceptionSerializerMap map = getParameterMap();
				Iterator keys = map.keySet().iterator();
				while( keys.hasNext() ){
					String fieldName = (String) keys.next();
					PerceptionSerializerMap.Property property = map.getProperty( fieldName );
					if( property != null && property.doPersist()){
						String methodName = property.getMethodName();
						Class valueClass = property.getType();
						Method method = src.getClass().getMethod( "get" + methodName, null ); // get getter method
						Object methodValue = method.invoke( src, null );
						if( methodValue != null || property.doPersistIfNull() ){
					//		log.logDebug( "fieldName=" + fieldName );
					//		log.logDebug( "methodName=" + methodName );
					//		log.logDebug( "valueClass=" + valueClass.getName() );
					//		log.logDebug( "methodValue=" + methodValue );
						//	if( methodValue != null ){ // don't send nulls to Perception
								// ok, we look for a serializer for the returned value
								Serializer objectSerializer = xjmr.querySerializer( valueClass, inScopeEncStyle );
								objectSerializer.marshall( inScopeEncStyle, valueClass, methodValue, fieldName, sink, nsStack, xjmr, ctx );
						//	}
						}
					}
				}
			}catch( InvocationTargetException ie ){
				log.logDebug( ie.getLocalizedMessage(), ie );
				throw new IllegalArgumentException( ie.getMessage() );
			}catch( NoSuchMethodException nsme ){
				log.logDebug( nsme.getLocalizedMessage(), nsme );
				throw new IllegalArgumentException( nsme.getMessage() );
			}catch( IllegalAccessException iae ){
				log.logDebug( iae.getLocalizedMessage(), iae );
				throw new IllegalArgumentException( iae.getMessage() );
			}
			sink.write("</" + context + '>');
		
		} else {
			// do not add
			//SoapEncUtils.generateNullStructure(inScopeEncStyle, javaType, context, sink, nsStack, xjmr);
		}
		nsStack.popScope();
	
	}	

}
