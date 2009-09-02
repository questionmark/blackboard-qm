/**
 * AssessmentResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class AssessmentResult  implements java.io.Serializable {
    private com.questionmark.QMWISe.Result result;

    private java.lang.String feedback;

    private com.questionmark.QMWISe.Answer[] answerList;

    public AssessmentResult() {
    }

    public AssessmentResult(
           com.questionmark.QMWISe.Result result,
           java.lang.String feedback,
           com.questionmark.QMWISe.Answer[] answerList) {
           this.result = result;
           this.feedback = feedback;
           this.answerList = answerList;
    }


    /**
     * Gets the result value for this AssessmentResult.
     * 
     * @return result
     */
    public com.questionmark.QMWISe.Result getResult() {
        return result;
    }


    /**
     * Sets the result value for this AssessmentResult.
     * 
     * @param result
     */
    public void setResult(com.questionmark.QMWISe.Result result) {
        this.result = result;
    }


    /**
     * Gets the feedback value for this AssessmentResult.
     * 
     * @return feedback
     */
    public java.lang.String getFeedback() {
        return feedback;
    }


    /**
     * Sets the feedback value for this AssessmentResult.
     * 
     * @param feedback
     */
    public void setFeedback(java.lang.String feedback) {
        this.feedback = feedback;
    }


    /**
     * Gets the answerList value for this AssessmentResult.
     * 
     * @return answerList
     */
    public com.questionmark.QMWISe.Answer[] getAnswerList() {
        return answerList;
    }


    /**
     * Sets the answerList value for this AssessmentResult.
     * 
     * @param answerList
     */
    public void setAnswerList(com.questionmark.QMWISe.Answer[] answerList) {
        this.answerList = answerList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssessmentResult)) return false;
        AssessmentResult other = (AssessmentResult) obj;
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
              java.util.Arrays.equals(this.answerList, other.getAnswerList())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssessmentResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("result");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Answer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Answer"));
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
