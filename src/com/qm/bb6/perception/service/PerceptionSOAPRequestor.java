/*
 * @(#)PerceptionSOAPRequestor.java 1.0.1 Sep 1 2005
 *
 * Copyright 2005 Questionmark Ltd. All Rights Reserved.
 * 
 * This software is the proprietary information of VLE Genius  
 * Use is subject to license terms.
 * 
 */
 
package com.qm.bb6.perception.service;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.xml.parsers.*;

import org.w3c.dom.*;

import org.apache.soap.*;
import org.apache.soap.encoding.*;
import org.apache.soap.encoding.soapenc.*;
import org.apache.soap.rpc.*;
import org.apache.soap.transport.http.*;
import org.apache.soap.util.*;
import org.apache.soap.util.xml.*;

import com.qm.bb6.perception.config.*;
import com.qm.bb6.perception.data.*;
import com.qm.bb6.perception.service.impl.*;
import com.qm.bb6.perception.security.AccessDeniedException;
import com.qm.bb6.perception.util.*;


/**
 * Service requestor for SOAP requests made from Perception
 * 
 * @author Matt Elton, VLE Genius
 * @version 1.0.1 Sep 1 2005
 * @since Perception Bridge 1.0
 */
public abstract class PerceptionSOAPRequestor {
	
	public static final String CLIENT_ID_PARAM = "ClientID";
	public static final String CHECKSUM_PARAM = "Checksum";
	public static final String TRUST_PARAM = "Trust";
	public static final String SECURITY_PARAM = "Security";
	public static final String DIGEST_PROPERTY = "Digest";
	public static final String ENCODING_PROPERTY = "Encoding";
	public static final String SIGNATURE_PROPERTY = "Signature";
	public static final String QMWISe_NS = "http://questionmark.com/QMWISe/";
	
	public static final int NO_TIMEOUT = -1;
	public static final int DEFAULT_TIMEOUT = 4000; //10000;
	
	private static final String PERCEPTION_KEY = "FFFFFFFFFFFFFFFF";
	
	public static final String ERROR_CODE_NODE_NAME = "error";
	public static final String ERROR_MESSAGE_NODE_NAME = "message";
	
	 
	private boolean useTrusted = true;
	private PerceptionSettings settings = null;

	public Object load( String methodName, SOAPMappingRegistry smr ) throws PerceptionDataException {
		return load( methodName, null, smr );
	}

	public Object load( String methodName, Vector params, SOAPMappingRegistry smr ) throws PerceptionDataException {
	
		
		PerceptionLog log = PerceptionServiceManager.getPerceptionLog();
		
		try	{
	
			settings = PerceptionSettings.loadFromCache();
			
			String endpointURL = settings.getFullQMWISeUrl(); //"http://perception.vlegenius.com/QMWISe4/QMWISe.asmx";
			
			Call call = new Call();
			
			int timeout = getTimeout( methodName );
			if( timeout > 0 ){
				SOAPHTTPConnection conn = new SOAPHTTPConnection();
				conn.setTimeout( timeout );
				call.setSOAPTransport( conn );
			}
			call.setTargetObjectURI( QMWISe_NS );
			call.setMethodName( methodName );
			call.setEncodingStyleURI( Constants.NS_URI_SOAP_ENC );
	
			if( smr != null )
				call.setSOAPMappingRegistry(smr);
			
			call.setParams( params );
			
			Header header = call.getHeader();
			if( header == null ){
				header = new Header();		
			}

			String bodyXML;
			try{
				bodyXML = getBodyXML( call );
			}catch(IOException e){
				// can't really happen
				log.logError("Error creating SOAP body", e);
				bodyXML = "";
			}
			
			if( settings.getUseChecksum() ){
				// security is on, add security headers
				header.setHeaderEntries( getHeaderEntries(bodyXML) );
				call.setHeader( header );
			}
	
			log.logDebug( "Calling Method: " + QMWISe_NS + methodName );
			
			URL QMWISEUrl = new URL(endpointURL);
			
			log.logDebug( "URL: " + QMWISEUrl.toExternalForm() );
			log.logDebug( "Body: " + bodyXML );
			
			Response resp = call.invoke(QMWISEUrl, QMWISe_NS + methodName);
	
			try {
	
	            String theResp = null;
	            Object content = resp.getBodyPart(0).getContent();
			
	            if (content instanceof InputStream) {
	                // FIXME: Not sure if this is right? Can there be binary data 
	                // in a SOAP response? We might need a way to specify the 
	                // character set to use in conversion.
	                Thread.yield(); // Give other Threads a chance to run
	
	                StringBuffer sbuf = new StringBuffer();
	                BufferedReader reader = new BufferedReader(new InputStreamReader((ByteArrayInputStream) content));
	
	                try {
	                    char[] b = new char[1024];
	                    while ((reader.read(b, 0, b.length) != -1)) {
	                        sbuf.append(b);
	                    }
	                } catch (IOException ioe) {
	                    
	                }
	                theResp = sbuf.toString();
	            } else {
	                theResp = (String) content;
	            }
	            log.logDebug( theResp );
	        } catch (Exception mex) {
	            log.logDebug( "Failed to log Perception response", mex );
	        }
	
			if ( resp.generatedFault() ) {
				Fault fault = resp.getFault();
				
				// attempt to read error code and message
				int errorCode = -1;
				String errorMessage = null;
				Vector entries = fault.getDetailEntries();
				try{
					for( Iterator i = entries.iterator(); i.hasNext(); ){
						Element entry = (Element) i.next();
						if( ERROR_CODE_NODE_NAME.equalsIgnoreCase(entry.getTagName()) ){
							errorCode = Integer.parseInt( (String) entry.getFirstChild().getNodeValue() );
						}else if( ERROR_MESSAGE_NODE_NAME.equalsIgnoreCase(entry.getTagName()) ){
							errorMessage = (String) entry.getFirstChild().getNodeValue();
						}
					}
					
				}catch(Exception e){
					log.logDebug("Unable to read Perception error code and message", e);
					StringBuffer errorBuf = new StringBuffer();
					errorBuf.append( "Perception SOAP call failed: " );
					errorBuf.append("  Code = " + fault.getFaultCode());  
					errorBuf.append("  String = " + fault.getFaultString());
					log.logError( errorBuf.toString() );
				}
				
				if( errorCode > 0 ){
					// use getInstance to instantiate sub classes automatically
					log.logDebug("Code=" + errorCode + ", Message=" + errorMessage);
					throw PerceptionDataException.getInstance( errorCode, errorMessage );
				}else{
					// error unknown
					throw new PerceptionDataException();
				}
			} else {
				Parameter result = resp.getReturnValue();
				if( result != null ){
					Object value = result.getValue();
					return value;
				}else{
					// nothing to return
					return null;
				}
			}
		}catch(MalformedURLException t){
			log.logDebug( "Path to QMWISe is invalid", t );
			throw new PerceptionDataException();
		}catch(UnsupportedEncodingException t){
			log.logDebug( "UTF-8 not supported on this platform", t );
			throw new PerceptionDataException();
		}catch(SOAPException t){
			log.logError( "SOAP Exception", t );
			if( t.getTargetException() != null )
				log.logError( "SOAP Exception", t.getTargetException() );
			if( t.getTargetException() instanceof IllegalArgumentException ){
				// Perception server is probably down
				throw new PerceptionNotFoundException();
			}else{
				throw new PerceptionDataException();
			}
		}
	}

	public static void addSimpleTextNode(Element e, String propertyName, String value){
		Element typeElement = e.getOwnerDocument().createElement(propertyName);
		typeElement.appendChild(e.getOwnerDocument().createTextNode(value));
		e.appendChild(typeElement);
	}
	
	private Vector getHeaderEntries( String bodyXML ) throws UnsupportedEncodingException{

		Vector vector = new Vector();
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder domBuilder = null;
		try {
			domBuilder = domFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document doc = domBuilder.newDocument();
		Element elem;
		
		if( useTrusted ){
			elem = doc.createElement( TRUST_PARAM );
			
			String digest = SecurityUtils.getMD5Checksum( bodyXML + PERCEPTION_KEY + settings.getTrustedSecretKey() );
			String encoding = "none";
			String signature = SecurityUtils.getMD5Checksum( digest + encoding + PERCEPTION_KEY + settings.getTrustedSecretKey() );
			
			addSimpleTextNode( elem, DIGEST_PROPERTY, digest );
			addSimpleTextNode( elem, ENCODING_PROPERTY, encoding );
			addSimpleTextNode( elem, SIGNATURE_PROPERTY, signature );
		}else{
			elem = doc.createElement( SECURITY_PARAM );
			if( settings.getClientID() != null ){
				addSimpleTextNode( elem, CLIENT_ID_PARAM, settings.getClientID() );
				String checksum = SecurityUtils.getMD5Checksum( settings.getClientID() + settings.getClientPassword() );
				addSimpleTextNode( elem, CHECKSUM_PARAM, checksum );
			}
		}
	
		elem.setAttribute("xmlns",QMWISe_NS);
		vector.add(elem);
		return vector;

	}
	
	private String getBodyXML( Call call ) throws IOException{
		// get details
		Vector params = call.getParams();
		SOAPMappingRegistry smr = call.getSOAPMappingRegistry();
		String inScopeEncStyle = call.getEncodingStyleURI();
		SOAPContext ctx = call.getSOAPContext();
		
		if( params == null || params.size() == 0 )
			return "";
		StringWriter bodyXML = new StringWriter ();
		NSStack nsStack = new NSStack();
		for( Enumeration elements = params.elements(); elements.hasMoreElements(); ){
			Object element = elements.nextElement();
			if( element instanceof Parameter ){
				Parameter param = (Parameter) element;
				Object value = param.getValue();
				if( value == null ){
					Serializer stringSerializer = smr.querySerializer(String.class, inScopeEncStyle);
					stringSerializer.marshall( inScopeEncStyle, String.class, "", param.getName(), bodyXML, nsStack, smr, ctx );
				}else{
					Serializer stringSerializer = smr.querySerializer(value.getClass(), inScopeEncStyle);
					stringSerializer.marshall( inScopeEncStyle, value.getClass(), value, param.getName(), bodyXML, nsStack, smr, ctx );
				}
			}
		}
		return bodyXML.toString();
	}

	/**
	 * Fetches a single record from Perception for a given ID<br>
	 * E.G.<br>
	 * 	&nbsp;&nbsp;&nbsp; 
	 *	insert( "GetParticipant", "PARTICIPANT_ID", "1234567", smr );
	 * @param methodName   the QMWISe service method
	 * @param parameterName   the name parameter used to send the Perception ID
	 * @param obj    the ID
	 * @param smr    the mapping registry
	 * @throws com.qm.bb6.perception.service.PerceptionNotFoundException  if Perception couldn't be contacted
	 * @throws com.qm.bb6.perception.service.ObjectNotFoundException     if the Perception object isn't found
	 * @throws com.qm.bb6.perception.service.AccessDeniedException      if the security parameters sent to Perception were rejected
	 */
	protected Object loadById( String methodName, String parameterName, String ID, SOAPMappingRegistry smr ) 
														throws PerceptionDataException {
		Vector bodyEntries = new Vector();
		Parameter param = new Parameter( parameterName, String.class, ID, null );
		bodyEntries.add( param );
		addIDMapping( smr, parameterName );
		Object object = load( methodName, bodyEntries, smr );
		if( object == null )
			throw new ObjectNotFoundException();
		return object;
	}

	/**
	 * Inserts a record into Perception via SOAP<br>
	 * E.G.<br>
	 * 	&nbsp;&nbsp;&nbsp; 
	 *	insert( "CreateParticipant", "Participant", "PARTICIPANT_ID", participant, smr );<br><br>
	 * The object's ID parameter is set if returned from Perception.
	 * @param methodName   the QMWISe service method
	 * @param parameterName   the name parameter used to send the Perception object
	 * @param return_ID   the name parameter used to return the new object's Perception ID
	 * @param obj    the object to persist
	 * @param smr    the mapping registry
	 * @return   the ID of the new object
	 * @throws com.qm.bb6.perception.service.PerceptionNotFoundException  if Perception couldn't be contacted
	 * @throws com.qm.bb6.perception.service.PerceptionDataException     if Perception isn't happy with the values being sent. Record may exist
	 * @throws com.qm.bb6.perception.service.AccessDeniedException      if the security parameters sent to Perception were rejected
	 */
	protected String insert( String methodName, String parameterName, String return_ID, Object obj, SOAPMappingRegistry smr ) 
															throws PerceptionDataException {
		Vector bodyEntries = new Vector();
		Parameter param = new Parameter( parameterName, obj.getClass(), obj, null );
		bodyEntries.add( param );
		addIDResponseMapping( smr, return_ID );
		addDefaultRequestMappings( smr );

		Object object = load( methodName, bodyEntries, smr );
		return (String) object;
	}

	/**
	 * Updates a record in Perception via SOAP<br>
	 * E.G.<br>
	 * 	&nbsp;&nbsp;&nbsp; 
	 *	update( "SetParticipant", "PARTICIPANT_ID", participant, smr );<br><br>
	 * The object's ID parameter is set if returned from Perception.
	 * @param methodName   the QMWISe service method
	 * @param parameterName   the name parameter used to send the Perception object
	 * @param obj    the object to persist
	 * @param smr    the mapping registry
	 * @throws com.qm.bb6.perception.service.PerceptionNotFoundException  if Perception couldn't be contacted
	 * @throws com.qm.bb6.perception.service.PerceptionDataException     if Perception isn't happy with the values being sent. Record may exist
	 * @throws com.qm.bb6.perception.service.AccessDeniedException      if the security parameters sent to Perception were rejected
	 */
	protected void update( String methodName, String parameterName, Object obj, SOAPMappingRegistry smr ) 
															throws PerceptionDataException {
		Vector bodyEntries = new Vector();
		Parameter param = new Parameter( parameterName, obj.getClass(), obj, null );
		bodyEntries.add( param );
		addDefaultRequestMappings( smr );
		load( methodName, bodyEntries, smr );
	}
	
	/**
	 * Updates a record in Perception via SOAP<br>
	 * E.G.<br>
	 * 	&nbsp;&nbsp;&nbsp; 
	 *	update( "DeleteParticipant", "PARTICIPANT_ID", Participant_ID, smr );<br><br>
	 * The object's ID parameter is set if returned from Perception.
	 * @param methodName   the QMWISe service method
	 * @param parameterName   the name parameter used to send the Perception object
	 * @param ID    the object's ID
	 * @param smr    the mapping registry
	 * @throws com.qm.bb6.perception.service.PerceptionNotFoundException  if Perception couldn't be contacted
	 * @throws com.qm.bb6.perception.service.PerceptionDataException     if Perception isn't happy with the values being sent. Record may exist
	 * @throws com.qm.bb6.perception.service.AccessDeniedException      if the security parameters sent to Perception were rejected
	 */
	protected void deleteById( String methodName, String parameterName, String ID, SOAPMappingRegistry smr ) 
															throws PerceptionDataException {
		Vector bodyEntries = new Vector();
		Parameter param = new Parameter( parameterName, String.class, ID, null );
		bodyEntries.add( param );
		addIDMapping( smr, parameterName );
		load( methodName, bodyEntries, smr );
	}
			
	protected static void addIDMapping( SOAPMappingRegistry smr, String name, Class type ){
		addRequestMapping( smr, name, new PerceptionIDSerializer(), type );
	}

	protected static void addIDMapping( SOAPMappingRegistry smr, String name ){
		addIDMapping( smr, name, String.class );
	}

	protected static void addIDResponseMapping( SOAPMappingRegistry smr, String name ){
		addResponseMapping( smr, name, new PerceptionIDDeserializer(), String.class );
	}

	protected static void addListMapping( SOAPMappingRegistry smr, String name, Deserializer listDeserializer ){
		addResponseMapping( smr, name, listDeserializer, List.class );
	}

	protected static void addRequestMapping( SOAPMappingRegistry smr, String name, Serializer serializer, Class type ){
		addMapping( smr, name, serializer, new BeanSerializer(), type );
	}

	protected static void addResponseMapping( SOAPMappingRegistry smr, String name, Deserializer deserializer, Class type ){
		addMapping( smr, name, new BeanSerializer(), deserializer, type );
	}

	protected static void addMapping( SOAPMappingRegistry smr, String name, Serializer serializer, Deserializer deserializer, Class type ){
		if( smr != null && name != null && serializer != null && deserializer != null && type != null ){
			QName qname = new QName( QMWISe_NS, name );
			smr.mapTypes(Constants.NS_URI_SOAP_ENC, qname, type, serializer, deserializer ); 	
		}
	}

	protected static void addDefaultRequestMappings( SOAPMappingRegistry smr ){
		PerceptionParameterSerializer serializer = new PerceptionParameterSerializer();
		addRequestMapping( smr, "String", serializer, String.class );
		addRequestMapping( smr, "Integer", serializer, Integer.class );
		addRequestMapping( smr, "Boolean", serializer, Boolean.class );
		addRequestMapping( smr, "Calendar", serializer, Calendar.class );
		addRequestMapping( smr, "Character", serializer, Character.class );
		addRequestMapping( smr, "Long", serializer, Long.class );
		addRequestMapping( smr, "Double", serializer, Double.class );
		addRequestMapping( smr, "Float", serializer, Float.class );		
	}
	
	protected int getTimeout( String methodName ){
		return NO_TIMEOUT;
	}
}
