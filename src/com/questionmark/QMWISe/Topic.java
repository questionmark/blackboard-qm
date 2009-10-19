/**
 * Topic.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class Topic  implements java.io.Serializable {
    private java.lang.String topic_ID;

    private java.lang.String parent_ID;

    private java.lang.String topic_Name;

    private java.lang.String topic_Description;

    private com.questionmark.QMWISe.ScoreBand[] scoreBandList;

    public Topic() {
    }

    public Topic(
           java.lang.String topic_ID,
           java.lang.String parent_ID,
           java.lang.String topic_Name,
           java.lang.String topic_Description,
           com.questionmark.QMWISe.ScoreBand[] scoreBandList) {
           this.topic_ID = topic_ID;
           this.parent_ID = parent_ID;
           this.topic_Name = topic_Name;
           this.topic_Description = topic_Description;
           this.scoreBandList = scoreBandList;
    }


    /**
     * Gets the topic_ID value for this Topic.
     * 
     * @return topic_ID
     */
    public java.lang.String getTopic_ID() {
        return topic_ID;
    }


    /**
     * Sets the topic_ID value for this Topic.
     * 
     * @param topic_ID
     */
    public void setTopic_ID(java.lang.String topic_ID) {
        this.topic_ID = topic_ID;
    }


    /**
     * Gets the parent_ID value for this Topic.
     * 
     * @return parent_ID
     */
    public java.lang.String getParent_ID() {
        return parent_ID;
    }


    /**
     * Sets the parent_ID value for this Topic.
     * 
     * @param parent_ID
     */
    public void setParent_ID(java.lang.String parent_ID) {
        this.parent_ID = parent_ID;
    }


    /**
     * Gets the topic_Name value for this Topic.
     * 
     * @return topic_Name
     */
    public java.lang.String getTopic_Name() {
        return topic_Name;
    }


    /**
     * Sets the topic_Name value for this Topic.
     * 
     * @param topic_Name
     */
    public void setTopic_Name(java.lang.String topic_Name) {
        this.topic_Name = topic_Name;
    }


    /**
     * Gets the topic_Description value for this Topic.
     * 
     * @return topic_Description
     */
    public java.lang.String getTopic_Description() {
        return topic_Description;
    }


    /**
     * Sets the topic_Description value for this Topic.
     * 
     * @param topic_Description
     */
    public void setTopic_Description(java.lang.String topic_Description) {
        this.topic_Description = topic_Description;
    }


    /**
     * Gets the scoreBandList value for this Topic.
     * 
     * @return scoreBandList
     */
    public com.questionmark.QMWISe.ScoreBand[] getScoreBandList() {
        return scoreBandList;
    }


    /**
     * Sets the scoreBandList value for this Topic.
     * 
     * @param scoreBandList
     */
    public void setScoreBandList(com.questionmark.QMWISe.ScoreBand[] scoreBandList) {
        this.scoreBandList = scoreBandList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Topic)) return false;
        Topic other = (Topic) obj;
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
            ((this.parent_ID==null && other.getParent_ID()==null) || 
             (this.parent_ID!=null &&
              this.parent_ID.equals(other.getParent_ID()))) &&
            ((this.topic_Name==null && other.getTopic_Name()==null) || 
             (this.topic_Name!=null &&
              this.topic_Name.equals(other.getTopic_Name()))) &&
            ((this.topic_Description==null && other.getTopic_Description()==null) || 
             (this.topic_Description!=null &&
              this.topic_Description.equals(other.getTopic_Description()))) &&
            ((this.scoreBandList==null && other.getScoreBandList()==null) || 
             (this.scoreBandList!=null &&
              java.util.Arrays.equals(this.scoreBandList, other.getScoreBandList())));
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
        if (getParent_ID() != null) {
            _hashCode += getParent_ID().hashCode();
        }
        if (getTopic_Name() != null) {
            _hashCode += getTopic_Name().hashCode();
        }
        if (getTopic_Description() != null) {
            _hashCode += getTopic_Description().hashCode();
        }
        if (getScoreBandList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getScoreBandList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getScoreBandList(), i);
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
        new org.apache.axis.description.TypeDesc(Topic.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Topic"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("topic_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Topic_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parent_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Parent_ID"));
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
        elemField.setFieldName("topic_Description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Topic_Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scoreBandList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScoreBandList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScoreBand"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScoreBand"));
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
