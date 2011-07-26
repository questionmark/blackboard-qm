/*
 * @(#)SecurityUtils.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.util;

import com.vle.util.MD5;

/**
 * Security related utility functions
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public class SecurityUtils {
	
	private static MD5 md5 = new MD5();
	
	public static int getASCIIChecksum( String text ) throws NonASCIIStringException {
		try{
			char[] chars = text.toCharArray();
			int checksum = 0;
			for( int i=0 ; i < chars.length; i++ ){
				checksum += (int) chars[ i ];
			}
			return checksum;
			
		}catch(Throwable t){
			throw new NonASCIIStringException();
		}
	}

	public static String getMD5Checksum( String text ) throws java.io.UnsupportedEncodingException{
		return md5.encode( text, "UTF-8" );
	}
	
	public static boolean isChecksumMd5( String str ){
		if( str != null && str.length() == 32 ){
			
			if( str.matches("^[A-Fa-f0-9]$") )
				return true;
		}
		return false;
	}
	
	public static boolean isChecksumASCII( String str ){
		if( str != null && str.length() > 0 ){
			try{
				if( Integer.parseInt( str ) >= 0 )
					return true;
			}catch(Exception e){
				//ignore
			}
		}
		return false;
	}

}
