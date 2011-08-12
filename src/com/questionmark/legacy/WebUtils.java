/*
 * @(#)WebUtils.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.questionmark.legacy;

import java.io.*;
/**
 * Web related utility functions
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class WebUtils {
	
	public static boolean readBoolean(String str, boolean defaultBool){
		try{
			if( str != null )
				return Boolean.valueOf(str).booleanValue();
			
		}catch(Exception e){
		}
		return defaultBool;
	}

	public static boolean convertBoolean(Object obj, boolean defaultBool){
		try{
			((Boolean) obj).booleanValue();
			
		}catch(Exception e){
		}
		return defaultBool;
	}

	
	public static int readInt(String str, int defaultInt){
		try{
			return Integer.valueOf(str).intValue();
		}catch(Exception e){
			return defaultInt;
		}
	}

	public static String parseQueryValue(String str) throws UnsupportedEncodingException{
		if(str == null)
			return "";
		return java.net.URLEncoder.encode(str, "UTF-8");
	}
	
	public static String parseFormValue(String str){
		if(str == null)
			return "";
		String tmp = str.replaceAll("\"", "&quot;"); 
		return tmp.replaceAll("&", "&amp;");
	}

	public static String parseHtmlValue(String str){
		if(str == null)
			return "";
		String newStr = str.replaceAll("&", "&amp;");
		newStr = newStr.replaceAll("<", "&lt;");
		return newStr.replaceAll(">", "&gt;");
	}

	public static String deparseHtmlValue(String str){
		if(str == null)
			return "";
		String newStr = str.replaceAll("&lt;", "<");
		newStr = newStr.replaceAll("&gt;", ">");
		return newStr.replaceAll("&amp;", "&");
	}

	public static String getChecked(boolean bool){
		if( bool )
			return "CHECKED";
		return "";
	}
	
	public static String getSelected(boolean bool){
		if( bool )
			return "SELECTED";
		return "";
	}

	public static String escapeDoubleQuotes(String str){
		if(str == null)
			return "";
		String tmp = str.replaceAll("\"", "\\\""); 
		return tmp;
	}

	public static String unencode(String str) throws UnsupportedEncodingException{
		if(str == null)
			return "";
		return java.net.URLDecoder.decode(str, "UTF-8");
	}

}
