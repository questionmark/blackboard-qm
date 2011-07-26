/*
 * @(#)PerceptionDeserializer.java 1.0.1 Sep 1 2005
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
import java.text.*;
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
public abstract class PerceptionDeserializer implements Deserializer {
		
	public abstract PerceptionSerializerMap getParameterMap();
	
	public abstract Class getSerializedClass();
	
	
	public Bean unmarshall(String inScopeEncStyle, QName elementType, Node src, XMLJavaMappingRegistry xjmr, SOAPContext ctx) 
																										throws IllegalArgumentException {
	
		Element root = (Element) src;
		
		PerceptionSerializerMap parameterMap = getParameterMap();
		
		PerceptionLog log = PerceptionServiceManager.getPerceptionLog();
		
		try{
			Object serializedObject = getSerializedClass().newInstance();
		
			NodeList propertyList = root.getChildNodes();
			for (int j = 0; j < propertyList.getLength(); j++) {
				Node propertyNode = propertyList.item(j);
				if(propertyNode instanceof Element){
					Element propertyElement = (Element) propertyNode;
					if( parameterMap.containsKey( propertyElement.getTagName() ) ){
						String methodName = parameterMap.getMethodName( propertyElement.getTagName() );
						Class parameterClass = parameterMap.getType( propertyElement.getTagName() );
						
						if( Deserializer.class.isAssignableFrom( parameterClass ) ){
							// object requires it's own Deserializer
							try{
								QName subElementType = xjmr.queryElementType( parameterClass, methodName );						
								Bean elementBean = xjmr.unmarshall( inScopeEncStyle, subElementType, propertyNode, ctx );
								serializedObject = elementBean.value;
							}catch(Throwable t){
								log.logError( "Failed to unmarshall type with specified seserializer", t);
								serializedObject = null;
							}
						}else{
							Method method;
							try{
								method = serializedObject.getClass().getMethod( "set" + methodName, new Class[]{ parameterClass } );
							}catch( NoSuchMethodException nsme ){
								// might be a primitive set
								Method getMethod = serializedObject.getClass().getMethod( "get" + methodName, null );
								method = serializedObject.getClass().getMethod( "set" + methodName, new Class[]{ getMethod.getReturnType() } );
								
							}
							
							if( Element.class.isAssignableFrom(parameterClass) ){
								// Method access Element itself, so no formatting req'd
								method.invoke( serializedObject, new Object[]{ propertyElement } );
							}else{
								Object parameterValue = convertParameter( DOMUtils.getChildCharacterData(propertyElement), parameterClass );
							//	try{
									method.invoke( serializedObject, new Object[]{ parameterValue } );
							//	}catch(Exception e){
							//		System.out.println( "parameterValue=" + parameterValue );
							//		System.out.println( "parameterValue class=" + parameterValue.getClass() );
							//		System.out.println( "parameterClass=" + parameterClass );
							//	}
							}
						}
					}
	
				}
			}
			return new Bean( serializedObject.getClass(), serializedObject );

		}catch( InvocationTargetException ie ){
			log.logDebug( ie.getLocalizedMessage(), ie );
			throw new IllegalArgumentException( ie.getMessage() );
		}catch( InstantiationException ie ){
			log.logDebug( ie.getLocalizedMessage(), ie );
			throw new IllegalArgumentException( ie.getMessage() );
		}catch( NoSuchMethodException nsme ){
			log.logDebug( nsme.getLocalizedMessage(), nsme );
			throw new IllegalArgumentException( nsme.getMessage() );
		}catch( IllegalAccessException iae ){
			log.logDebug( iae.getLocalizedMessage(), iae );
			throw new IllegalArgumentException( iae.getMessage() );
		}
		
	}
	
	protected Object convertParameter( String parameter, Class type ) throws IllegalArgumentException {
		
		if( Boolean.class.isAssignableFrom( type ) ){
			return Boolean.valueOf( parameter );
		}else if( Integer.class.isAssignableFrom( type ) ){
			return Integer.valueOf( parameter );
		}else if( Byte.class.isAssignableFrom( type ) ){
			return Byte.valueOf( parameter );
		}else if( Character.class.isAssignableFrom( type ) ){
			return new Character(parameter.charAt(0));
		}else if( Double.class.isAssignableFrom( type ) ){
			return Double.valueOf( parameter );
		}else if( Float.class.isAssignableFrom( type ) ){
			return Float.valueOf( parameter );
		}else if( Long.class.isAssignableFrom( type ) ){
			return Long.valueOf( parameter );
		}else if( Short.class.isAssignableFrom( type ) ){
			return Short.valueOf( parameter );
		}else if( Calendar.class.isAssignableFrom( type ) ){
			if( parameter == null || parameter.trim().length() == 0 ){
				return null; // acceptable
			}else if( parameter.indexOf("T") >= 0 ){
				try{
					parameter = parameter.replace('T',' ');
					SimpleDateFormat basicFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();
					cal.setTime( basicFormat.parse(parameter, new ParsePosition(0)) );
					cal.add( Calendar.MILLISECOND, cal.getTimeZone().getOffset( cal.getTimeInMillis() ) ); // correct to local time
					return cal;
				}catch(Exception e){
					throw new IllegalArgumentException("Unable to format date send from Perception");
				}
			}else{
				try{
					SimpleDateFormat basicFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					cal.setTime( basicFormat.parse(parameter, new ParsePosition(0)) );
					cal.add( Calendar.MILLISECOND, cal.getTimeZone().getOffset( cal.getTimeInMillis() ) ); // correct to local time
					return cal;
				}catch(Exception e){
					throw new IllegalArgumentException("Unable to format date send from Perception");
				}
			}
		}else{
			return parameter;
		}
	}
	
}
