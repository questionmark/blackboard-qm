/**
 * DeliveryAssessmentSnapshotChoice.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class DeliveryAssessmentSnapshotChoice  implements java.io.Serializable {
    private com.questionmark.QMWISe.DeliveryAssessmentSnapshotContent CONTENT;

    private java.lang.String ID;  // attribute

    public DeliveryAssessmentSnapshotChoice() {
    }

    public DeliveryAssessmentSnapshotChoice(
           com.questionmark.QMWISe.DeliveryAssessmentSnapshotContent CONTENT,
           java.lang.String ID) {
           this.CONTENT = CONTENT;
           this.ID = ID;
    }


    /**
     * Gets the CONTENT value for this DeliveryAssessmentSnapshotChoice.
     * 
     * @return CONTENT
     */
    public com.questionmark.QMWISe.DeliveryAssessmentSnapshotContent getCONTENT() {
        return CONTENT;
    }


    /**
     * Sets the CONTENT value for this DeliveryAssessmentSnapshotChoice.
     * 
     * @param CONTENT
     */
    public void setCONTENT(com.questionmark.QMWISe.DeliveryAssessmentSnapshotContent CONTENT) {
        this.CONTENT = CONTENT;
    }


    /**
     * Gets the ID value for this DeliveryAssessmentSnapshotChoice.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this DeliveryAssessmentSnapshotChoice.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeliveryAssessmentSnapshotChoice)) return false;
        DeliveryAssessmentSnapshotChoice other = (DeliveryAssessmentSnapshotChoice) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CONTENT==null && other.getCONTENT()==null) || 
             (this.CONTENT!=null &&
              this.CONTENT.equals(other.getCONTENT()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID())));
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
        if (getCONTENT() != null) {
            _hashCode += getCONTENT().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeliveryAssessmentSnapshotChoice.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotChoice"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("ID");
        attrField.setXmlName(new javax.xml.namespace.QName("", "ID"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTENT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CONTENT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotContent"));
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
