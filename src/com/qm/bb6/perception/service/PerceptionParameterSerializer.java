/*
 * @(#)PerceptionParameterSerializer.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.service;

import java.io.*;
import java.text.SimpleDateFormat;
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
public class PerceptionParameterSerializer implements Serializer {
		
	public void marshall(	String inScopeEncStyle, Class javaType, Object src,
	               			Object context, Writer sink, NSStack nsStack,
	               			XMLJavaMappingRegistry xjmr, SOAPContext ctx) throws IllegalArgumentException, IOException {
	
		nsStack.pushScope();
		
		if( src != null ){
		
			PerceptionLog log = PerceptionServiceManager.getPerceptionLog();

			sink.write("<" + context);
			sink.write(">");
			sink.write( SOAPUtils.parseSOAPValue(convertParameter(src)) );
			sink.write("</" + context + '>');
		
		} else {
			// add null structure
			sink.write("<" + context);
			sink.write(">");
			sink.write("</" + context + '>');
		}
		nsStack.popScope();
	
	}	

	protected String convertParameter( Object src ) throws IllegalArgumentException {
		
		if( src == null ){
			return "";
		}else if( src instanceof String ){
			return (String) src;
		}else if( src instanceof Boolean ){
			return ((Boolean) src).booleanValue() ? "true" : "false";
		}else if( src instanceof Integer ){
			return String.valueOf(((Integer) src).intValue());
		}else if( src instanceof Byte ){
			return String.valueOf(((Byte) src).byteValue());
		}else if( src instanceof Character ){
			return String.valueOf(((Character) src).charValue());
		}else if( src instanceof Double ){
			return String.valueOf(((Double) src).doubleValue());
		}else if( src instanceof Float ){
			return String.valueOf(((Float) src).floatValue());
		}else if( src instanceof Long ){
			return String.valueOf(((Long) src).longValue());
		}else if( src instanceof Short ){
			return String.valueOf(((Short) src).shortValue());
		}else if( src instanceof Calendar ){
			try{
				SimpleDateFormat basicFormat1 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat basicFormat2 = new SimpleDateFormat("HH:mm:ss");
				Calendar cal = (Calendar) src;
				Calendar cal2 = Calendar.getInstance();
				cal2.setTimeInMillis( cal.getTimeInMillis() - cal.getTimeZone().getOffset( cal.getTimeInMillis() ) );

				StringBuffer calBuf = new StringBuffer();
				calBuf.append( basicFormat1.format( cal2.getTime() ) );
				calBuf.append( "T" );
				calBuf.append( basicFormat2.format( cal2.getTime() ) );
				return calBuf.toString();
			}catch(Exception e){
				throw new IllegalArgumentException("Unable to format date into Perception format");
			}
		}else{
			throw new IllegalArgumentException("Unable to format parameter, unknown type");
		}
	}

}
