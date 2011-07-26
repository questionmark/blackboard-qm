/*
 * @(#)SOAPUtils.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.util;

/**
 * SOAP related utility functions
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class SOAPUtils {
	
	public static String parseSOAPValue(String str){
		if(str == null)
			return "";
		String newStr = str.replaceAll("<", "&lt;");
		newStr = newStr.replaceAll("&", "&amp;");
		return newStr.replaceAll(">", "&gt;");
	}

}
