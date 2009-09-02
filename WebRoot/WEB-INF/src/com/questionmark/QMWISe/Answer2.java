/**
 * Answer2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class Answer2  extends com.questionmark.QMWISe.Answer  implements java.io.Serializable {
    private int outcome_Exponential2Property;

    public Answer2() {
    }

    public Answer2(
           java.lang.String question_ID,
           int revision,
           short occurrence,
           java.lang.String topic_Name,
           short block_Number,
           short question_Number,
           short status,
           short times_Answered,
           int max_Score,
           int actual_Score,
           boolean know_Time_Taken,
           int time_Taken,
           short number_Outcomes,
           short outcome_Number,
           int outcome_Exponential,
           java.lang.String answer_Truncated,
           java.lang.String answer_Full,
           java.lang.String comment,
           int outcome_Exponential2Property) {
        super(
            question_ID,
            revision,
            occurrence,
            topic_Name,
            block_Number,
            question_Number,
            status,
            times_Answered,
            max_Score,
            actual_Score,
            know_Time_Taken,
            time_Taken,
            number_Outcomes,
            outcome_Number,
            outcome_Exponential,
            answer_Truncated,
            answer_Full,
            comment);
        this.outcome_Exponential2Property = outcome_Exponential2Property;
    }


    /**
     * Gets the outcome_Exponential2Property value for this Answer2.
     * 
     * @return outcome_Exponential2Property
     */
    public int getOutcome_Exponential2Property() {
        return outcome_Exponential2Property;
    }


    /**
     * Sets the outcome_Exponential2Property value for this Answer2.
     * 
     * @param outcome_Exponential2Property
     */
    public void setOutcome_Exponential2Property(int outcome_Exponential2Property) {
        this.outcome_Exponential2Property = outcome_Exponential2Property;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Answer2)) return false;
        Answer2 other = (Answer2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.outcome_Exponential2Property == other.getOutcome_Exponential2Property();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        _hashCode += getOutcome_Exponential2Property();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Answer2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Answer2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outcome_Exponential2Property");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Outcome_Exponential2Property"));
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
