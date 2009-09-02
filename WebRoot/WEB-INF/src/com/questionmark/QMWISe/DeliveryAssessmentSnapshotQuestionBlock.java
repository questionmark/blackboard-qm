/**
 * DeliveryAssessmentSnapshotQuestionBlock.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class DeliveryAssessmentSnapshotQuestionBlock  implements java.io.Serializable {
    private java.lang.String block_Name;

    private java.lang.String introduction_Text;

    private com.questionmark.QMWISe.DeliveryAssessmentSnapshotQuestion[] questionList;

    public DeliveryAssessmentSnapshotQuestionBlock() {
    }

    public DeliveryAssessmentSnapshotQuestionBlock(
           java.lang.String block_Name,
           java.lang.String introduction_Text,
           com.questionmark.QMWISe.DeliveryAssessmentSnapshotQuestion[] questionList) {
           this.block_Name = block_Name;
           this.introduction_Text = introduction_Text;
           this.questionList = questionList;
    }


    /**
     * Gets the block_Name value for this DeliveryAssessmentSnapshotQuestionBlock.
     * 
     * @return block_Name
     */
    public java.lang.String getBlock_Name() {
        return block_Name;
    }


    /**
     * Sets the block_Name value for this DeliveryAssessmentSnapshotQuestionBlock.
     * 
     * @param block_Name
     */
    public void setBlock_Name(java.lang.String block_Name) {
        this.block_Name = block_Name;
    }


    /**
     * Gets the introduction_Text value for this DeliveryAssessmentSnapshotQuestionBlock.
     * 
     * @return introduction_Text
     */
    public java.lang.String getIntroduction_Text() {
        return introduction_Text;
    }


    /**
     * Sets the introduction_Text value for this DeliveryAssessmentSnapshotQuestionBlock.
     * 
     * @param introduction_Text
     */
    public void setIntroduction_Text(java.lang.String introduction_Text) {
        this.introduction_Text = introduction_Text;
    }


    /**
     * Gets the questionList value for this DeliveryAssessmentSnapshotQuestionBlock.
     * 
     * @return questionList
     */
    public com.questionmark.QMWISe.DeliveryAssessmentSnapshotQuestion[] getQuestionList() {
        return questionList;
    }


    /**
     * Sets the questionList value for this DeliveryAssessmentSnapshotQuestionBlock.
     * 
     * @param questionList
     */
    public void setQuestionList(com.questionmark.QMWISe.DeliveryAssessmentSnapshotQuestion[] questionList) {
        this.questionList = questionList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeliveryAssessmentSnapshotQuestionBlock)) return false;
        DeliveryAssessmentSnapshotQuestionBlock other = (DeliveryAssessmentSnapshotQuestionBlock) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.block_Name==null && other.getBlock_Name()==null) || 
             (this.block_Name!=null &&
              this.block_Name.equals(other.getBlock_Name()))) &&
            ((this.introduction_Text==null && other.getIntroduction_Text()==null) || 
             (this.introduction_Text!=null &&
              this.introduction_Text.equals(other.getIntroduction_Text()))) &&
            ((this.questionList==null && other.getQuestionList()==null) || 
             (this.questionList!=null &&
              java.util.Arrays.equals(this.questionList, other.getQuestionList())));
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
        if (getBlock_Name() != null) {
            _hashCode += getBlock_Name().hashCode();
        }
        if (getIntroduction_Text() != null) {
            _hashCode += getIntroduction_Text().hashCode();
        }
        if (getQuestionList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getQuestionList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getQuestionList(), i);
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
        new org.apache.axis.description.TypeDesc(DeliveryAssessmentSnapshotQuestionBlock.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotQuestionBlock"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("block_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Block_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("introduction_Text");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Introduction_Text"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("questionList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "QuestionList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotQuestion"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "QUESTION"));
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
