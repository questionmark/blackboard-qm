/**
 * QMWISeLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class QMWISeLocator extends org.apache.axis.client.Service implements com.questionmark.QMWISe.QMWISe {

/**
 * Questionmark Web Integration Services environment copyright © Questionmark
 * Computing Limited 2002-2007.
 */

    public QMWISeLocator() {
    }


    public QMWISeLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public QMWISeLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for QMWISeSoap
    private java.lang.String QMWISeSoap_address = "http://assess/qmwise4/qmwise.asmx";

    public java.lang.String getQMWISeSoapAddress() {
        return QMWISeSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String QMWISeSoapWSDDServiceName = "QMWISeSoap";

    public java.lang.String getQMWISeSoapWSDDServiceName() {
        return QMWISeSoapWSDDServiceName;
    }

    public void setQMWISeSoapWSDDServiceName(java.lang.String name) {
        QMWISeSoapWSDDServiceName = name;
    }

    public com.questionmark.QMWISe.QMWISeSoap getQMWISeSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(QMWISeSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getQMWISeSoap(endpoint);
    }

    public com.questionmark.QMWISe.QMWISeSoap getQMWISeSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.questionmark.QMWISe.QMWISeSoapStub _stub = new com.questionmark.QMWISe.QMWISeSoapStub(portAddress, this);
            _stub.setPortName(getQMWISeSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setQMWISeSoapEndpointAddress(java.lang.String address) {
        QMWISeSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.questionmark.QMWISe.QMWISeSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.questionmark.QMWISe.QMWISeSoapStub _stub = new com.questionmark.QMWISe.QMWISeSoapStub(new java.net.URL(QMWISeSoap_address), this);
                _stub.setPortName(getQMWISeSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("QMWISeSoap".equals(inputPortName)) {
            return getQMWISeSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "QMWISe");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "QMWISeSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("QMWISeSoap".equals(portName)) {
            setQMWISeSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
