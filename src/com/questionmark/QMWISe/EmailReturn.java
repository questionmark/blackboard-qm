/**
 * EmailReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class EmailReturn  implements java.io.Serializable {
    private int emailSentToParticipant;

    private int emailSentToProctor;

    public EmailReturn() {
    }

    public EmailReturn(
           int emailSentToParticipant,
           int emailSentToProctor) {
           this.emailSentToParticipant = emailSentToParticipant;
           this.emailSentToProctor = emailSentToProctor;
    }


    /**
     * Gets the emailSentToParticipant value for this EmailReturn.
     * 
     * @return emailSentToParticipant
     */
    public int getEmailSentToParticipant() {
        return emailSentToParticipant;
    }


    /**
     * Sets the emailSentToParticipant value for this EmailReturn.
     * 
     * @param emailSentToParticipant
     */
    public void setEmailSentToParticipant(int emailSentToParticipant) {
        this.emailSentToParticipant = emailSentToParticipant;
    }


    /**
     * Gets the emailSentToProctor value for this EmailReturn.
     * 
     * @return emailSentToProctor
     */
    public int getEmailSentToProctor() {
        return emailSentToProctor;
    }


    /**
     * Sets the emailSentToProctor value for this EmailReturn.
     * 
     * @param emailSentToProctor
     */
    public void setEmailSentToProctor(int emailSentToProctor) {
        this.emailSentToProctor = emailSentToProctor;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EmailReturn)) return false;
        EmailReturn other = (EmailReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.emailSentToParticipant == other.getEmailSentToParticipant() &&
            this.emailSentToProctor == other.getEmailSentToProctor();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getEmailSentToParticipant();
        _hashCode += getEmailSentToProctor();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EmailReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "EmailReturn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailSentToParticipant");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "EmailSentToParticipant"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailSentToProctor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "EmailSentToProctor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
