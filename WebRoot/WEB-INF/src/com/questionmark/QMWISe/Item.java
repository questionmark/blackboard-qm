/**
 * Item.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class Item  implements java.io.Serializable {
    private int method;

    private boolean include_Sub_Topics;

    private java.lang.String topic_ID;

    private int number_Of_Questions;

    private java.lang.String question_ID;

    public Item() {
    }

    public Item(
           int method,
           boolean include_Sub_Topics,
           java.lang.String topic_ID,
           int number_Of_Questions,
           java.lang.String question_ID) {
           this.method = method;
           this.include_Sub_Topics = include_Sub_Topics;
           this.topic_ID = topic_ID;
           this.number_Of_Questions = number_Of_Questions;
           this.question_ID = question_ID;
    }


    /**
     * Gets the method value for this Item.
     * 
     * @return method
     */
    public int getMethod() {
        return method;
    }


    /**
     * Sets the method value for this Item.
     * 
     * @param method
     */
    public void setMethod(int method) {
        this.method = method;
    }


    /**
     * Gets the include_Sub_Topics value for this Item.
     * 
     * @return include_Sub_Topics
     */
    public boolean isInclude_Sub_Topics() {
        return include_Sub_Topics;
    }


    /**
     * Sets the include_Sub_Topics value for this Item.
     * 
     * @param include_Sub_Topics
     */
    public void setInclude_Sub_Topics(boolean include_Sub_Topics) {
        this.include_Sub_Topics = include_Sub_Topics;
    }


    /**
     * Gets the topic_ID value for this Item.
     * 
     * @return topic_ID
     */
    public java.lang.String getTopic_ID() {
        return topic_ID;
    }


    /**
     * Sets the topic_ID value for this Item.
     * 
     * @param topic_ID
     */
    public void setTopic_ID(java.lang.String topic_ID) {
        this.topic_ID = topic_ID;
    }


    /**
     * Gets the number_Of_Questions value for this Item.
     * 
     * @return number_Of_Questions
     */
    public int getNumber_Of_Questions() {
        return number_Of_Questions;
    }


    /**
     * Sets the number_Of_Questions value for this Item.
     * 
     * @param number_Of_Questions
     */
    public void setNumber_Of_Questions(int number_Of_Questions) {
        this.number_Of_Questions = number_Of_Questions;
    }


    /**
     * Gets the question_ID value for this Item.
     * 
     * @return question_ID
     */
    public java.lang.String getQuestion_ID() {
        return question_ID;
    }


    /**
     * Sets the question_ID value for this Item.
     * 
     * @param question_ID
     */
    public void setQuestion_ID(java.lang.String question_ID) {
        this.question_ID = question_ID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Item)) return false;
        Item other = (Item) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.method == other.getMethod() &&
            this.include_Sub_Topics == other.isInclude_Sub_Topics() &&
            ((this.topic_ID==null && other.getTopic_ID()==null) || 
             (this.topic_ID!=null &&
              this.topic_ID.equals(other.getTopic_ID()))) &&
            this.number_Of_Questions == other.getNumber_Of_Questions() &&
            ((this.question_ID==null && other.getQuestion_ID()==null) || 
             (this.question_ID!=null &&
              this.question_ID.equals(other.getQuestion_ID())));
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
        _hashCode += getMethod();
        _hashCode += (isInclude_Sub_Topics() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTopic_ID() != null) {
            _hashCode += getTopic_ID().hashCode();
        }
        _hashCode += getNumber_Of_Questions();
        if (getQuestion_ID() != null) {
            _hashCode += getQuestion_ID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Item.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Item"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("method");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Method"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("include_Sub_Topics");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Include_Sub_Topics"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("topic_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Topic_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number_Of_Questions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Number_Of_Questions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("question_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Question_ID"));
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
