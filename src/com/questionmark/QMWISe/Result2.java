/**
 * Result2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class Result2  extends com.questionmark.QMWISe.Result  implements java.io.Serializable {
    private java.lang.String firstName;

    private java.lang.String lastName;

    private java.lang.String primaryEmailAddress;

    private java.lang.String subgroupPath;

    private java.lang.String courseProperty;

    private int scoreBandIDProperty;

    public Result2() {
    }

    public Result2(
           java.lang.String result_ID,
           java.lang.String assessment_ID,
           boolean write_Answer_Data,
           java.lang.String participant,
           java.lang.String member_Group,
           java.lang.String participant_Details,
           java.lang.String hostname,
           java.lang.String IP_Address,
           boolean still_Going,
           short status,
           short feedback,
           short number_Sections,
           int max_Score,
           int total_Score,
           java.lang.String special_1,
           java.lang.String special_2,
           java.lang.String special_3,
           java.lang.String special_4,
           java.lang.String special_5,
           java.lang.String special_6,
           java.lang.String special_7,
           java.lang.String special_8,
           java.lang.String special_9,
           java.lang.String special_10,
           int time_Taken,
           java.lang.String score_Band_Title,
           int score_Band_Number,
           short percentage_Score,
           java.lang.String schedule_Name,
           boolean monitored,
           java.lang.String monitor_Name,
           com.questionmark.QMWISe.TopicScoring[] topicScoringList,
           java.lang.String when_Started,
           java.lang.String session_Last_Modified,
           java.lang.String when_Finished,
           java.lang.String firstName,
           java.lang.String lastName,
           java.lang.String primaryEmailAddress,
           java.lang.String subgroupPath,
           java.lang.String courseProperty,
           int scoreBandIDProperty) {
        super(
            result_ID,
            assessment_ID,
            write_Answer_Data,
            participant,
            member_Group,
            participant_Details,
            hostname,
            IP_Address,
            still_Going,
            status,
            feedback,
            number_Sections,
            max_Score,
            total_Score,
            special_1,
            special_2,
            special_3,
            special_4,
            special_5,
            special_6,
            special_7,
            special_8,
            special_9,
            special_10,
            time_Taken,
            score_Band_Title,
            score_Band_Number,
            percentage_Score,
            schedule_Name,
            monitored,
            monitor_Name,
            topicScoringList,
            when_Started,
            session_Last_Modified,
            when_Finished);
        this.firstName = firstName;
        this.lastName = lastName;
        this.primaryEmailAddress = primaryEmailAddress;
        this.subgroupPath = subgroupPath;
        this.courseProperty = courseProperty;
        this.scoreBandIDProperty = scoreBandIDProperty;
    }


    /**
     * Gets the firstName value for this Result2.
     * 
     * @return firstName
     */
    public java.lang.String getFirstName() {
        return firstName;
    }


    /**
     * Sets the firstName value for this Result2.
     * 
     * @param firstName
     */
    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }


    /**
     * Gets the lastName value for this Result2.
     * 
     * @return lastName
     */
    public java.lang.String getLastName() {
        return lastName;
    }


    /**
     * Sets the lastName value for this Result2.
     * 
     * @param lastName
     */
    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }


    /**
     * Gets the primaryEmailAddress value for this Result2.
     * 
     * @return primaryEmailAddress
     */
    public java.lang.String getPrimaryEmailAddress() {
        return primaryEmailAddress;
    }


    /**
     * Sets the primaryEmailAddress value for this Result2.
     * 
     * @param primaryEmailAddress
     */
    public void setPrimaryEmailAddress(java.lang.String primaryEmailAddress) {
        this.primaryEmailAddress = primaryEmailAddress;
    }


    /**
     * Gets the subgroupPath value for this Result2.
     * 
     * @return subgroupPath
     */
    public java.lang.String getSubgroupPath() {
        return subgroupPath;
    }


    /**
     * Sets the subgroupPath value for this Result2.
     * 
     * @param subgroupPath
     */
    public void setSubgroupPath(java.lang.String subgroupPath) {
        this.subgroupPath = subgroupPath;
    }


    /**
     * Gets the courseProperty value for this Result2.
     * 
     * @return courseProperty
     */
    public java.lang.String getCourseProperty() {
        return courseProperty;
    }


    /**
     * Sets the courseProperty value for this Result2.
     * 
     * @param courseProperty
     */
    public void setCourseProperty(java.lang.String courseProperty) {
        this.courseProperty = courseProperty;
    }


    /**
     * Gets the scoreBandIDProperty value for this Result2.
     * 
     * @return scoreBandIDProperty
     */
    public int getScoreBandIDProperty() {
        return scoreBandIDProperty;
    }


    /**
     * Sets the scoreBandIDProperty value for this Result2.
     * 
     * @param scoreBandIDProperty
     */
    public void setScoreBandIDProperty(int scoreBandIDProperty) {
        this.scoreBandIDProperty = scoreBandIDProperty;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Result2)) return false;
        Result2 other = (Result2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.firstName==null && other.getFirstName()==null) || 
             (this.firstName!=null &&
              this.firstName.equals(other.getFirstName()))) &&
            ((this.lastName==null && other.getLastName()==null) || 
             (this.lastName!=null &&
              this.lastName.equals(other.getLastName()))) &&
            ((this.primaryEmailAddress==null && other.getPrimaryEmailAddress()==null) || 
             (this.primaryEmailAddress!=null &&
              this.primaryEmailAddress.equals(other.getPrimaryEmailAddress()))) &&
            ((this.subgroupPath==null && other.getSubgroupPath()==null) || 
             (this.subgroupPath!=null &&
              this.subgroupPath.equals(other.getSubgroupPath()))) &&
            ((this.courseProperty==null && other.getCourseProperty()==null) || 
             (this.courseProperty!=null &&
              this.courseProperty.equals(other.getCourseProperty()))) &&
            this.scoreBandIDProperty == other.getScoreBandIDProperty();
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
        if (getFirstName() != null) {
            _hashCode += getFirstName().hashCode();
        }
        if (getLastName() != null) {
            _hashCode += getLastName().hashCode();
        }
        if (getPrimaryEmailAddress() != null) {
            _hashCode += getPrimaryEmailAddress().hashCode();
        }
        if (getSubgroupPath() != null) {
            _hashCode += getSubgroupPath().hashCode();
        }
        if (getCourseProperty() != null) {
            _hashCode += getCourseProperty().hashCode();
        }
        _hashCode += getScoreBandIDProperty();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Result2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "FirstName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "LastName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primaryEmailAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "PrimaryEmailAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subgroupPath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "SubgroupPath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("courseProperty");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CourseProperty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scoreBandIDProperty");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScoreBandIDProperty"));
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
