/**
 * DeliveryAssessmentSnapshot.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class DeliveryAssessmentSnapshot  implements java.io.Serializable {
    private java.lang.String assessment_ID;

    private int snapshot_ID;

    private java.lang.String random_Key;

    private com.questionmark.QMWISe.DeliveryAssessmentSnapshotHeader header;

    private com.questionmark.QMWISe.DeliveryAssessmentSnapshotQuestionBlock[] questionBlock;

    public DeliveryAssessmentSnapshot() {
    }

    public DeliveryAssessmentSnapshot(
           java.lang.String assessment_ID,
           int snapshot_ID,
           java.lang.String random_Key,
           com.questionmark.QMWISe.DeliveryAssessmentSnapshotHeader header,
           com.questionmark.QMWISe.DeliveryAssessmentSnapshotQuestionBlock[] questionBlock) {
           this.assessment_ID = assessment_ID;
           this.snapshot_ID = snapshot_ID;
           this.random_Key = random_Key;
           this.header = header;
           this.questionBlock = questionBlock;
    }


    /**
     * Gets the assessment_ID value for this DeliveryAssessmentSnapshot.
     * 
     * @return assessment_ID
     */
    public java.lang.String getAssessment_ID() {
        return assessment_ID;
    }


    /**
     * Sets the assessment_ID value for this DeliveryAssessmentSnapshot.
     * 
     * @param assessment_ID
     */
    public void setAssessment_ID(java.lang.String assessment_ID) {
        this.assessment_ID = assessment_ID;
    }


    /**
     * Gets the snapshot_ID value for this DeliveryAssessmentSnapshot.
     * 
     * @return snapshot_ID
     */
    public int getSnapshot_ID() {
        return snapshot_ID;
    }


    /**
     * Sets the snapshot_ID value for this DeliveryAssessmentSnapshot.
     * 
     * @param snapshot_ID
     */
    public void setSnapshot_ID(int snapshot_ID) {
        this.snapshot_ID = snapshot_ID;
    }


    /**
     * Gets the random_Key value for this DeliveryAssessmentSnapshot.
     * 
     * @return random_Key
     */
    public java.lang.String getRandom_Key() {
        return random_Key;
    }


    /**
     * Sets the random_Key value for this DeliveryAssessmentSnapshot.
     * 
     * @param random_Key
     */
    public void setRandom_Key(java.lang.String random_Key) {
        this.random_Key = random_Key;
    }


    /**
     * Gets the header value for this DeliveryAssessmentSnapshot.
     * 
     * @return header
     */
    public com.questionmark.QMWISe.DeliveryAssessmentSnapshotHeader getHeader() {
        return header;
    }


    /**
     * Sets the header value for this DeliveryAssessmentSnapshot.
     * 
     * @param header
     */
    public void setHeader(com.questionmark.QMWISe.DeliveryAssessmentSnapshotHeader header) {
        this.header = header;
    }


    /**
     * Gets the questionBlock value for this DeliveryAssessmentSnapshot.
     * 
     * @return questionBlock
     */
    public com.questionmark.QMWISe.DeliveryAssessmentSnapshotQuestionBlock[] getQuestionBlock() {
        return questionBlock;
    }


    /**
     * Sets the questionBlock value for this DeliveryAssessmentSnapshot.
     * 
     * @param questionBlock
     */
    public void setQuestionBlock(com.questionmark.QMWISe.DeliveryAssessmentSnapshotQuestionBlock[] questionBlock) {
        this.questionBlock = questionBlock;
    }

    public com.questionmark.QMWISe.DeliveryAssessmentSnapshotQuestionBlock getQuestionBlock(int i) {
        return this.questionBlock[i];
    }

    public void setQuestionBlock(int i, com.questionmark.QMWISe.DeliveryAssessmentSnapshotQuestionBlock _value) {
        this.questionBlock[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeliveryAssessmentSnapshot)) return false;
        DeliveryAssessmentSnapshot other = (DeliveryAssessmentSnapshot) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.assessment_ID==null && other.getAssessment_ID()==null) || 
             (this.assessment_ID!=null &&
              this.assessment_ID.equals(other.getAssessment_ID()))) &&
            this.snapshot_ID == other.getSnapshot_ID() &&
            ((this.random_Key==null && other.getRandom_Key()==null) || 
             (this.random_Key!=null &&
              this.random_Key.equals(other.getRandom_Key()))) &&
            ((this.header==null && other.getHeader()==null) || 
             (this.header!=null &&
              this.header.equals(other.getHeader()))) &&
            ((this.questionBlock==null && other.getQuestionBlock()==null) || 
             (this.questionBlock!=null &&
              java.util.Arrays.equals(this.questionBlock, other.getQuestionBlock())));
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
        if (getAssessment_ID() != null) {
            _hashCode += getAssessment_ID().hashCode();
        }
        _hashCode += getSnapshot_ID();
        if (getRandom_Key() != null) {
            _hashCode += getRandom_Key().hashCode();
        }
        if (getHeader() != null) {
            _hashCode += getHeader().hashCode();
        }
        if (getQuestionBlock() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getQuestionBlock());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getQuestionBlock(), i);
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
        new org.apache.axis.description.TypeDesc(DeliveryAssessmentSnapshot.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshot"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assessment_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("snapshot_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Snapshot_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("random_Key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Random_Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("header");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Header"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotHeader"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("questionBlock");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "QuestionBlock"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotQuestionBlock"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
