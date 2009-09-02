/**
 * AssessmentResult2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class AssessmentResult2  implements java.io.Serializable {
    private com.questionmark.QMWISe.Result2 result;

    private java.lang.String feedback;

    private com.questionmark.QMWISe.Answer2[] answerList;

    private com.questionmark.QMWISe.UnencryptedLMSDetails LMSDetails;

    public AssessmentResult2() {
    }

    public AssessmentResult2(
           com.questionmark.QMWISe.Result2 result,
           java.lang.String feedback,
           com.questionmark.QMWISe.Answer2[] answerList,
           com.questionmark.QMWISe.UnencryptedLMSDetails LMSDetails) {
           this.result = result;
           this.feedback = feedback;
           this.answerList = answerList;
           this.LMSDetails = LMSDetails;
    }


    /**
     * Gets the result value for this AssessmentResult2.
     * 
     * @return result
     */
    public com.questionmark.QMWISe.Result2 getResult() {
        return result;
    }


    /**
     * Sets the result value for this AssessmentResult2.
     * 
     * @param result
     */
    public void setResult(com.questionmark.QMWISe.Result2 result) {
        this.result = result;
    }


    /**
     * Gets the feedback value for this AssessmentResult2.
     * 
     * @return feedback
     */
    public java.lang.String getFeedback() {
        return feedback;
    }


    /**
     * Sets the feedback value for this AssessmentResult2.
     * 
     * @param feedback
     */
    public void setFeedback(java.lang.String feedback) {
        this.feedback = feedback;
    }


    /**
     * Gets the answerList value for this AssessmentResult2.
     * 
     * @return answerList
     */
    public com.questionmark.QMWISe.Answer2[] getAnswerList() {
        return answerList;
    }


    /**
     * Sets the answerList value for this AssessmentResult2.
     * 
     * @param answerList
     */
    public void setAnswerList(com.questionmark.QMWISe.Answer2[] answerList) {
        this.answerList = answerList;
    }


    /**
     * Gets the LMSDetails value for this AssessmentResult2.
     * 
     * @return LMSDetails
     */
    public com.questionmark.QMWISe.UnencryptedLMSDetails getLMSDetails() {
        return LMSDetails;
    }


    /**
     * Sets the LMSDetails value for this AssessmentResult2.
     * 
     * @param LMSDetails
     */
    public void setLMSDetails(com.questionmark.QMWISe.UnencryptedLMSDetails LMSDetails) {
        this.LMSDetails = LMSDetails;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssessmentResult2)) return false;
        AssessmentResult2 other = (AssessmentResult2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.result==null && other.getResult()==null) || 
             (this.result!=null &&
              this.result.equals(other.getResult()))) &&
            ((this.feedback==null && other.getFeedback()==null) || 
             (this.feedback!=null &&
              this.feedback.equals(other.getFeedback()))) &&
            ((this.answerList==null && other.getAnswerList()==null) || 
             (this.answerList!=null &&
              java.util.Arrays.equals(this.answerList, other.getAnswerList()))) &&
            ((this.LMSDetails==null && other.getLMSDetails()==null) || 
             (this.LMSDetails!=null &&
              this.LMSDetails.equals(other.getLMSDetails())));
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
        if (getResult() != null) {
            _hashCode += getResult().hashCode();
        }
        if (getFeedback() != null) {
            _hashCode += getFeedback().hashCode();
        }
        if (getAnswerList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAnswerList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAnswerList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLMSDetails() != null) {
            _hashCode += getLMSDetails().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssessmentResult2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResult2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("result");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result2"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("answerList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AnswerList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Answer2"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Answer2"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LMSDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "LMSDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "UnencryptedLMSDetails"));
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
