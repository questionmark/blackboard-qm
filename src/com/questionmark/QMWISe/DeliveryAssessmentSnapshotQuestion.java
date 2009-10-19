/**
 * DeliveryAssessmentSnapshotQuestion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class DeliveryAssessmentSnapshotQuestion  implements java.io.Serializable {
    private com.questionmark.QMWISe.DeliveryAssessmentSnapshotContent[] CONTENT;

    private com.questionmark.QMWISe.DeliveryAssessmentSnapshotChoice[] ANSWER;

    private java.lang.String ID;  // attribute

    private int MAX;  // attribute

    public DeliveryAssessmentSnapshotQuestion() {
    }

    public DeliveryAssessmentSnapshotQuestion(
           com.questionmark.QMWISe.DeliveryAssessmentSnapshotContent[] CONTENT,
           com.questionmark.QMWISe.DeliveryAssessmentSnapshotChoice[] ANSWER,
           java.lang.String ID,
           int MAX) {
           this.CONTENT = CONTENT;
           this.ANSWER = ANSWER;
           this.ID = ID;
           this.MAX = MAX;
    }


    /**
     * Gets the CONTENT value for this DeliveryAssessmentSnapshotQuestion.
     * 
     * @return CONTENT
     */
    public com.questionmark.QMWISe.DeliveryAssessmentSnapshotContent[] getCONTENT() {
        return CONTENT;
    }


    /**
     * Sets the CONTENT value for this DeliveryAssessmentSnapshotQuestion.
     * 
     * @param CONTENT
     */
    public void setCONTENT(com.questionmark.QMWISe.DeliveryAssessmentSnapshotContent[] CONTENT) {
        this.CONTENT = CONTENT;
    }

    public com.questionmark.QMWISe.DeliveryAssessmentSnapshotContent getCONTENT(int i) {
        return this.CONTENT[i];
    }

    public void setCONTENT(int i, com.questionmark.QMWISe.DeliveryAssessmentSnapshotContent _value) {
        this.CONTENT[i] = _value;
    }


    /**
     * Gets the ANSWER value for this DeliveryAssessmentSnapshotQuestion.
     * 
     * @return ANSWER
     */
    public com.questionmark.QMWISe.DeliveryAssessmentSnapshotChoice[] getANSWER() {
        return ANSWER;
    }


    /**
     * Sets the ANSWER value for this DeliveryAssessmentSnapshotQuestion.
     * 
     * @param ANSWER
     */
    public void setANSWER(com.questionmark.QMWISe.DeliveryAssessmentSnapshotChoice[] ANSWER) {
        this.ANSWER = ANSWER;
    }


    /**
     * Gets the ID value for this DeliveryAssessmentSnapshotQuestion.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this DeliveryAssessmentSnapshotQuestion.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the MAX value for this DeliveryAssessmentSnapshotQuestion.
     * 
     * @return MAX
     */
    public int getMAX() {
        return MAX;
    }


    /**
     * Sets the MAX value for this DeliveryAssessmentSnapshotQuestion.
     * 
     * @param MAX
     */
    public void setMAX(int MAX) {
        this.MAX = MAX;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeliveryAssessmentSnapshotQuestion)) return false;
        DeliveryAssessmentSnapshotQuestion other = (DeliveryAssessmentSnapshotQuestion) obj;
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
              java.util.Arrays.equals(this.CONTENT, other.getCONTENT()))) &&
            ((this.ANSWER==null && other.getANSWER()==null) || 
             (this.ANSWER!=null &&
              java.util.Arrays.equals(this.ANSWER, other.getANSWER()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            this.MAX == other.getMAX();
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
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCONTENT());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCONTENT(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getANSWER() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getANSWER());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getANSWER(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        _hashCode += getMAX();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeliveryAssessmentSnapshotQuestion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotQuestion"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("ID");
        attrField.setXmlName(new javax.xml.namespace.QName("", "ID"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("MAX");
        attrField.setXmlName(new javax.xml.namespace.QName("", "MAX"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTENT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CONTENT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotContent"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ANSWER");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ANSWER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotChoice"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CHOICE"));
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
