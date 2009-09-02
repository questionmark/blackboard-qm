/**
 * Outcome.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class Outcome  implements java.io.Serializable {
    private short outcome_Number;

    private java.lang.String outcome_Name;

    private java.lang.String feedback;

    public Outcome() {
    }

    public Outcome(
           short outcome_Number,
           java.lang.String outcome_Name,
           java.lang.String feedback) {
           this.outcome_Number = outcome_Number;
           this.outcome_Name = outcome_Name;
           this.feedback = feedback;
    }


    /**
     * Gets the outcome_Number value for this Outcome.
     * 
     * @return outcome_Number
     */
    public short getOutcome_Number() {
        return outcome_Number;
    }


    /**
     * Sets the outcome_Number value for this Outcome.
     * 
     * @param outcome_Number
     */
    public void setOutcome_Number(short outcome_Number) {
        this.outcome_Number = outcome_Number;
    }


    /**
     * Gets the outcome_Name value for this Outcome.
     * 
     * @return outcome_Name
     */
    public java.lang.String getOutcome_Name() {
        return outcome_Name;
    }


    /**
     * Sets the outcome_Name value for this Outcome.
     * 
     * @param outcome_Name
     */
    public void setOutcome_Name(java.lang.String outcome_Name) {
        this.outcome_Name = outcome_Name;
    }


    /**
     * Gets the feedback value for this Outcome.
     * 
     * @return feedback
     */
    public java.lang.String getFeedback() {
        return feedback;
    }


    /**
     * Sets the feedback value for this Outcome.
     * 
     * @param feedback
     */
    public void setFeedback(java.lang.String feedback) {
        this.feedback = feedback;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Outcome)) return false;
        Outcome other = (Outcome) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.outcome_Number == other.getOutcome_Number() &&
            ((this.outcome_Name==null && other.getOutcome_Name()==null) || 
             (this.outcome_Name!=null &&
              this.outcome_Name.equals(other.getOutcome_Name()))) &&
            ((this.feedback==null && other.getFeedback()==null) || 
             (this.feedback!=null &&
              this.feedback.equals(other.getFeedback())));
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
        _hashCode += getOutcome_Number();
        if (getOutcome_Name() != null) {
            _hashCode += getOutcome_Name().hashCode();
        }
        if (getFeedback() != null) {
            _hashCode += getFeedback().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Outcome.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Outcome"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outcome_Number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Outcome_Number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outcome_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Outcome_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("feedback");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Feedback"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
