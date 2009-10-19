/**
 * TopicScoring.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class TopicScoring  implements java.io.Serializable {
    private java.lang.String topic_ID;

    private java.lang.String topic_Name;

    private java.lang.String description;

    private double percentage_Score;

    private int actual_Score;

    private int maximum_Score;

    private int num_Questions;

    public TopicScoring() {
    }

    public TopicScoring(
           java.lang.String topic_ID,
           java.lang.String topic_Name,
           java.lang.String description,
           double percentage_Score,
           int actual_Score,
           int maximum_Score,
           int num_Questions) {
           this.topic_ID = topic_ID;
           this.topic_Name = topic_Name;
           this.description = description;
           this.percentage_Score = percentage_Score;
           this.actual_Score = actual_Score;
           this.maximum_Score = maximum_Score;
           this.num_Questions = num_Questions;
    }


    /**
     * Gets the topic_ID value for this TopicScoring.
     * 
     * @return topic_ID
     */
    public java.lang.String getTopic_ID() {
        return topic_ID;
    }


    /**
     * Sets the topic_ID value for this TopicScoring.
     * 
     * @param topic_ID
     */
    public void setTopic_ID(java.lang.String topic_ID) {
        this.topic_ID = topic_ID;
    }


    /**
     * Gets the topic_Name value for this TopicScoring.
     * 
     * @return topic_Name
     */
    public java.lang.String getTopic_Name() {
        return topic_Name;
    }


    /**
     * Sets the topic_Name value for this TopicScoring.
     * 
     * @param topic_Name
     */
    public void setTopic_Name(java.lang.String topic_Name) {
        this.topic_Name = topic_Name;
    }


    /**
     * Gets the description value for this TopicScoring.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this TopicScoring.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the percentage_Score value for this TopicScoring.
     * 
     * @return percentage_Score
     */
    public double getPercentage_Score() {
        return percentage_Score;
    }


    /**
     * Sets the percentage_Score value for this TopicScoring.
     * 
     * @param percentage_Score
     */
    public void setPercentage_Score(double percentage_Score) {
        this.percentage_Score = percentage_Score;
    }


    /**
     * Gets the actual_Score value for this TopicScoring.
     * 
     * @return actual_Score
     */
    public int getActual_Score() {
        return actual_Score;
    }


    /**
     * Sets the actual_Score value for this TopicScoring.
     * 
     * @param actual_Score
     */
    public void setActual_Score(int actual_Score) {
        this.actual_Score = actual_Score;
    }


    /**
     * Gets the maximum_Score value for this TopicScoring.
     * 
     * @return maximum_Score
     */
    public int getMaximum_Score() {
        return maximum_Score;
    }


    /**
     * Sets the maximum_Score value for this TopicScoring.
     * 
     * @param maximum_Score
     */
    public void setMaximum_Score(int maximum_Score) {
        this.maximum_Score = maximum_Score;
    }


    /**
     * Gets the num_Questions value for this TopicScoring.
     * 
     * @return num_Questions
     */
    public int getNum_Questions() {
        return num_Questions;
    }


    /**
     * Sets the num_Questions value for this TopicScoring.
     * 
     * @param num_Questions
     */
    public void setNum_Questions(int num_Questions) {
        this.num_Questions = num_Questions;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TopicScoring)) return false;
        TopicScoring other = (TopicScoring) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.topic_ID==null && other.getTopic_ID()==null) || 
             (this.topic_ID!=null &&
              this.topic_ID.equals(other.getTopic_ID()))) &&
            ((this.topic_Name==null && other.getTopic_Name()==null) || 
             (this.topic_Name!=null &&
              this.topic_Name.equals(other.getTopic_Name()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            this.percentage_Score == other.getPercentage_Score() &&
            this.actual_Score == other.getActual_Score() &&
            this.maximum_Score == other.getMaximum_Score() &&
            this.num_Questions == other.getNum_Questions();
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
        if (getTopic_ID() != null) {
            _hashCode += getTopic_ID().hashCode();
        }
        if (getTopic_Name() != null) {
            _hashCode += getTopic_Name().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        _hashCode += new Double(getPercentage_Score()).hashCode();
        _hashCode += getActual_Score();
        _hashCode += getMaximum_Score();
        _hashCode += getNum_Questions();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TopicScoring.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "TopicScoring"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("topic_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Topic_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("topic_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Topic_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percentage_Score");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Percentage_Score"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actual_Score");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Actual_Score"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maximum_Score");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Maximum_Score"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("num_Questions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Num_Questions"));
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
