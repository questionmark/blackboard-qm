/*
 * @(#)PerceptionSerializerMap.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.service;

import java.util.*;


/**
 * 
 *  Matt Elton, VLE Genius
 *  1.0.1 Sep 1 2005
 *  Perception Bridge 1.0
 */
public class PerceptionSerializerMap extends HashMap {
		
	public static class Property {
		
		private String methodName;
		private Class type;
		private boolean persist = true;
		private boolean persistIfNull = false;
		
		private Property( String methodName, Class type){
			this( methodName, type, true, false );
		}

		private Property( String methodName, Class type, boolean persist, boolean persistIfNull){
			if( methodName == null || type == null )
				throw new NullPointerException();
				
			this.methodName = methodName;
			this.type = type;
		}
		
		public String getMethodName(){
			return this.methodName;
		}
		
		public Class getType(){
			return this.type;
		}

		public boolean doPersist(){
			return this.persist;
		}

		public boolean doPersistIfNull(){
			return this.persistIfNull;
		}
	}
	
	public PerceptionSerializerMap(){
		super();
	}
	
	
	public String getMethodName( Object keyName ){
		try{
			Property property = (Property) super.get( keyName );
			return property.getMethodName();
		}catch(Exception e){
			return null;
		}
	}
	
	public Class getType( Object keyName ){
		try{
			Property property = (Property) super.get( keyName );
			return property.getType();
		}catch(Exception e){
			return null;
		}
	}

	public Property getProperty( Object keyName ){
		try{
			Property property = (Property) super.get( keyName );
			return property;
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * @deprecated   
	 */
	public Object put( Object keyName, Object value ){
		return put( (String) keyName, (String) value, String.class ); 
	}

	public Object put( String keyName, String methodName, Class type ){
		Property property = new Property( methodName, type );
		return super.put( keyName, property );

	}

	public Object put( String keyName, String methodName, Class type, boolean persist, boolean persistIfNull ){
		Property property = new Property( methodName, type, persist, persistIfNull );
		return super.put( keyName, property );

	}

	
}
