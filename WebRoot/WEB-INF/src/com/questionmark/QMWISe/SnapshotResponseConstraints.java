/**
 * SnapshotResponseConstraints.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class SnapshotResponseConstraints  implements java.io.Serializable {
    private java.lang.String snapshotID;

    private java.lang.String numQuestions;

    private com.questionmark.QMWISe.Response[] responseList;

    public SnapshotResponseConstraints() {
    }

    public SnapshotResponseConstraints(
           java.lang.String snapshotID,
           java.lang.String numQuestions,
           com.questionmark.QMWISe.Response[] responseList) {
           this.snapshotID = snapshotID;
           this.numQuestions = numQuestions;
           this.responseList = responseList;
    }


    /**
     * Gets the snapshotID value for this SnapshotResponseConstraints.
     * 
     * @return snapshotID
     */
    public java.lang.String getSnapshotID() {
        return snapshotID;
    }


    /**
     * Sets the snapshotID value for this SnapshotResponseConstraints.
     * 
     * @param snapshotID
     */
    public void setSnapshotID(java.lang.String snapshotID) {
        this.snapshotID = snapshotID;
    }


    /**
     * Gets the numQuestions value for this SnapshotResponseConstraints.
     * 
     * @return numQuestions
     */
    public java.lang.String getNumQuestions() {
        return numQuestions;
    }


    /**
     * Sets the numQuestions value for this SnapshotResponseConstraints.
     * 
     * @param numQuestions
     */
    public void setNumQuestions(java.lang.String numQuestions) {
        this.numQuestions = numQuestions;
    }


    /**
     * Gets the responseList value for this SnapshotResponseConstraints.
     * 
     * @return responseList
     */
    public com.questionmark.QMWISe.Response[] getResponseList() {
        return responseList;
    }


    /**
     * Sets the responseList value for this SnapshotResponseConstraints.
     * 
     * @param responseList
     */
    public void setResponseList(com.questionmark.QMWISe.Response[] responseList) {
        this.responseList = responseList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SnapshotResponseConstraints)) return false;
        SnapshotResponseConstraints other = (SnapshotResponseConstraints) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.snapshotID==null && other.getSnapshotID()==null) || 
             (this.snapshotID!=null &&
              this.snapshotID.equals(other.getSnapshotID()))) &&
            ((this.numQuestions==null && other.getNumQuestions()==null) || 
             (this.numQuestions!=null &&
              this.numQuestions.equals(other.getNumQuestions()))) &&
            ((this.responseList==null && other.getResponseList()==null) || 
             (this.responseList!=null &&
              java.util.Arrays.equals(this.responseList, other.getResponseList())));
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
        if (getSnapshotID() != null) {
            _hashCode += getSnapshotID().hashCode();
        }
        if (getNumQuestions() != null) {
            _hashCode += getNumQuestions().hashCode();
        }
        if (getResponseList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResponseList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResponseList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SnapshotResponseConstraints.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "SnapshotResponseConstraints"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("snapshotID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "SnapshotID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numQuestions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "NumQuestions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ResponseList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Response"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Response"));
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
