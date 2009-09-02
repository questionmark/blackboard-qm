/**
 * QMWISe.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public interface QMWISe extends javax.xml.rpc.Service {

/**
 * Questionmark Web Integration Services environment copyright © Questionmark
 * Computing Limited 2002-2007.
 */
    public java.lang.String getQMWISeSoapAddress();

    public com.questionmark.QMWISe.QMWISeSoap getQMWISeSoap() throws javax.xml.rpc.ServiceException;

    public com.questionmark.QMWISe.QMWISeSoap getQMWISeSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
