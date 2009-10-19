/**
 * QuestionResultInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class QuestionResultInfo  implements java.io.Serializable {
    private java.lang.String question_ID;

    private int revision;

    private java.lang.String question_Description;

    private java.lang.String question_Type;

    private java.lang.String topic_Name;

    private short number_Outcomes;

    private java.lang.String question_Wording;

    private com.questionmark.QMWISe.Outcome[] outcomeList;

    private java.lang.String last_Updated;

    public QuestionResultInfo() {
    }

    public QuestionResultInfo(
           java.lang.String question_ID,
           int revision,
           java.lang.String question_Description,
           java.lang.String question_Type,
           java.lang.String topic_Name,
           short number_Outcomes,
           java.lang.String question_Wording,
           com.questionmark.QMWISe.Outcome[] outcomeList,
           java.lang.String last_Updated) {
           this.question_ID = question_ID;
           this.revision = revision;
           this.question_Description = question_Description;
           this.question_Type = question_Type;
           this.topic_Name = topic_Name;
           this.number_Outcomes = number_Outcomes;
           this.question_Wording = question_Wording;
           this.outcomeList = outcomeList;
           this.last_Updated = last_Updated;
    }


    /**
     * Gets the question_ID value for this QuestionResultInfo.
     * 
     * @return question_ID
     */
    public java.lang.String getQuestion_ID() {
        return question_ID;
    }


    /**
     * Sets the question_ID value for this QuestionResultInfo.
     * 
     * @param question_ID
     */
    public void setQuestion_ID(java.lang.String question_ID) {
        this.question_ID = question_ID;
    }


    /**
     * Gets the revision value for this QuestionResultInfo.
     * 
     * @return revision
     */
    public int getRevision() {
        return revision;
    }


    /**
     * Sets the revision value for this QuestionResultInfo.
     * 
     * @param revision
     */
    public void setRevision(int revision) {
        this.revision = revision;
    }


    /**
     * Gets the question_Description value for this QuestionResultInfo.
     * 
     * @return question_Description
     */
    public java.lang.String getQuestion_Description() {
        return question_Description;
    }


    /**
     * Sets the question_Description value for this QuestionResultInfo.
     * 
     * @param question_Description
     */
    public void setQuestion_Description(java.lang.String question_Description) {
        this.question_Description = question_Description;
    }


    /**
     * Gets the question_Type value for this QuestionResultInfo.
     * 
     * @return question_Type
     */
    public java.lang.String getQuestion_Type() {
        return question_Type;
    }


    /**
     * Sets the question_Type value for this QuestionResultInfo.
     * 
     * @param question_Type
     */
    public void setQuestion_Type(java.lang.String question_Type) {
        this.question_Type = question_Type;
    }


    /**
     * Gets the topic_Name value for this QuestionResultInfo.
     * 
     * @return topic_Name
     */
    public java.lang.String getTopic_Name() {
        return topic_Name;
    }


    /**
     * Sets the topic_Name value for this QuestionResultInfo.
     * 
     * @param topic_Name
     */
    public void setTopic_Name(java.lang.String topic_Name) {
        this.topic_Name = topic_Name;
    }


    /**
     * Gets the number_Outcomes value for this QuestionResultInfo.
     * 
     * @return number_Outcomes
     */
    public short getNumber_Outcomes() {
        return number_Outcomes;
    }


    /**
     * Sets the number_Outcomes value for this QuestionResultInfo.
     * 
     * @param number_Outcomes
     */
    public void setNumber_Outcomes(short number_Outcomes) {
        this.number_Outcomes = number_Outcomes;
    }


    /**
     * Gets the question_Wording value for this QuestionResultInfo.
     * 
     * @return question_Wording
     */
    public java.lang.String getQuestion_Wording() {
        return question_Wording;
    }


    /**
     * Sets the question_Wording value for this QuestionResultInfo.
     * 
     * @param question_Wording
     */
    public void setQuestion_Wording(java.lang.String question_Wording) {
        this.question_Wording = question_Wording;
    }


    /**
     * Gets the outcomeList value for this QuestionResultInfo.
     * 
     * @return outcomeList
     */
    public com.questionmark.QMWISe.Outcome[] getOutcomeList() {
        return outcomeList;
    }


    /**
     * Sets the outcomeList value for this QuestionResultInfo.
     * 
     * @param outcomeList
     */
    public void setOutcomeList(com.questionmark.QMWISe.Outcome[] outcomeList) {
        this.outcomeList = outcomeList;
    }


    /**
     * Gets the last_Updated value for this QuestionResultInfo.
     * 
     * @return last_Updated
     */
    public java.lang.String getLast_Updated() {
        return last_Updated;
    }


    /**
     * Sets the last_Updated value for this QuestionResultInfo.
     * 
     * @param last_Updated
     */
    public void setLast_Updated(java.lang.String last_Updated) {
        this.last_Updated = last_Updated;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QuestionResultInfo)) return false;
        QuestionResultInfo other = (QuestionResultInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.question_ID==null && other.getQuestion_ID()==null) || 
             (this.question_ID!=null &&
              this.question_ID.equals(other.getQuestion_ID()))) &&
            this.revision == other.getRevision() &&
            ((this.question_Description==null && other.getQuestion_Description()==null) || 
             (this.question_Description!=null &&
              this.question_Description.equals(other.getQuestion_Description()))) &&
            ((this.question_Type==null && other.getQuestion_Type()==null) || 
             (this.question_Type!=null &&
              this.question_Type.equals(other.getQuestion_Type()))) &&
            ((this.topic_Name==null && other.getTopic_Name()==null) || 
             (this.topic_Name!=null &&
              this.topic_Name.equals(other.getTopic_Name()))) &&
            this.number_Outcomes == other.getNumber_Outcomes() &&
            ((this.question_Wording==null && other.getQuestion_Wording()==null) || 
             (this.question_Wording!=null &&
              this.question_Wording.equals(other.getQuestion_Wording()))) &&
            ((this.outcomeList==null && other.getOutcomeList()==null) || 
             (this.outcomeList!=null &&
              java.util.Arrays.equals(this.outcomeList, other.getOutcomeList()))) &&
            ((this.last_Updated==null && other.getLast_Updated()==null) || 
             (this.last_Updated!=null &&
              this.last_Updated.equals(other.getLast_Updated())));
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
        if (getQuestion_ID() != null) {
            _hashCode += getQuestion_ID().hashCode();
        }
        _hashCode += getRevision();
        if (getQuestion_Description() != null) {
            _hashCode += getQuestion_Description().hashCode();
        }
        if (getQuestion_Type() != null) {
            _hashCode += getQuestion_Type().hashCode();
        }
        if (getTopic_Name() != null) {
            _hashCode += getTopic_Name().hashCode();
        }
        _hashCode += getNumber_Outcomes();
        if (getQuestion_Wording() != null) {
            _hashCode += getQuestion_Wording().hashCode();
        }
        if (getOutcomeList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOutcomeList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOutcomeList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLast_Updated() != null) {
            _hashCode += getLast_Updated().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QuestionResultInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "QuestionResultInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("question_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Question_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("revision");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Revision"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("question_Description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Question_Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("question_Type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Question_Type"));
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
        elemField.setFieldName("number_Outcomes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Number_Outcomes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("question_Wording");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Question_Wording"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outcomeList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "OutcomeList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Outcome"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Outcome"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("last_Updated");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Last_Updated"));
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
