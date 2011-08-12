/*
 * ConfigSettings.java
 *
 * Created on October 25, 2007, 4:32 PM
 *
 * Copyright 2006 VLE Genius Ltd. All rights reserved.
 */

package com.questionmark.legacy;

import blackboard.portal.data.ExtraInfo;

import java.util.Iterator;

/**
 *
 * @author Matt Elton
 */
public abstract class ConfigSettings {
	
	protected static boolean oldStyle = false;
	private static boolean checkedStyle = false;
	
	protected static boolean readBoolean( ExtraInfo ei, String name, boolean defaultValue){
		try{
			Object obj = readObject( ei, name );
			if( BbUtils.isNullOrEmpty(obj) )
				return defaultValue;
			else if( obj instanceof Boolean )
				return ((Boolean) obj).booleanValue();
			else
				return Boolean.parseBoolean( (String) obj );
		}catch(Exception e){
			return defaultValue;
		}
	}

	protected static int readInt( ExtraInfo ei, String name, int defaultValue){
		try{
			Object obj = readObject( ei, name );
			if( BbUtils.isNullOrEmpty(obj) )
				return defaultValue;
			else if( obj instanceof Integer )
				return ((Integer) obj).intValue();
			else
				return Integer.parseInt((String) obj);
		}catch(Exception e){
			return defaultValue;
		}
	}

	protected static String readString( ExtraInfo ei, String name, String defaultValue){
		try{
			String value = ei.getValue( name );
			if( BbUtils.isNullOrEmpty(value) )
				return null;
			return value;
		}catch(Exception e){
			return defaultValue;
		}
	}
	
	protected static Object readObject( ExtraInfo ei, String name ){
		if( !checkedStyle ){
			for( Iterator i=ei.getKeys().iterator(); i.hasNext(); ){
				String key = (String) i.next();
				Object obj = ei.getObjectValue(key);
				if( ! (obj instanceof String) ){
					oldStyle = true;
					break;
				}
			}
			checkedStyle = true;
		}
		
		if( oldStyle ){
			return ei.getObjectValue( name );
		}else{
			return ei.getValue( name );
		}
	}
	
	protected static void setString( ExtraInfo ei, String param, String value ){
		String value2write;
		if( value == null ){
			value2write = "";
		}else{
			value2write = new String(value);
		}
		ei.setValue(param, value2write);
	}
	
	protected static void setBoolean( ExtraInfo ei, String param, boolean value ){
		ei.setValue(param, String.valueOf(value));
	}
	
	protected static void setInt( ExtraInfo ei, String param, int value ){
		ei.setValue(param, String.valueOf(value));
	}
	
}
