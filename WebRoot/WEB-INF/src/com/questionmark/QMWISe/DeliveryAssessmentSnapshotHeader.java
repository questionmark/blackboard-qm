/**
 * DeliveryAssessmentSnapshotHeader.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class DeliveryAssessmentSnapshotHeader  implements java.io.Serializable {
    private int assessment_Type;

    private java.lang.String name;

    private java.lang.String description;

    private java.lang.String author;

    private boolean save_Answers;

    private boolean save_Answer_Data;

    private int number_Of_Blocks;

    private int number_Of_Questions;

    private java.lang.String course;

    private int time_Limit;

    public DeliveryAssessmentSnapshotHeader() {
    }

    public DeliveryAssessmentSnapshotHeader(
           int assessment_Type,
           java.lang.String name,
           java.lang.String description,
           java.lang.String author,
           boolean save_Answers,
           boolean save_Answer_Data,
           int number_Of_Blocks,
           int number_Of_Questions,
           java.lang.String course,
           int time_Limit) {
           this.assessment_Type = assessment_Type;
           this.name = name;
           this.description = description;
           this.author = author;
           this.save_Answers = save_Answers;
           this.save_Answer_Data = save_Answer_Data;
           this.number_Of_Blocks = number_Of_Blocks;
           this.number_Of_Questions = number_Of_Questions;
           this.course = course;
           this.time_Limit = time_Limit;
    }


    /**
     * Gets the assessment_Type value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @return assessment_Type
     */
    public int getAssessment_Type() {
        return assessment_Type;
    }


    /**
     * Sets the assessment_Type value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @param assessment_Type
     */
    public void setAssessment_Type(int assessment_Type) {
        this.assessment_Type = assessment_Type;
    }


    /**
     * Gets the name value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the description value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the author value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @return author
     */
    public java.lang.String getAuthor() {
        return author;
    }


    /**
     * Sets the author value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @param author
     */
    public void setAuthor(java.lang.String author) {
        this.author = author;
    }


    /**
     * Gets the save_Answers value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @return save_Answers
     */
    public boolean isSave_Answers() {
        return save_Answers;
    }


    /**
     * Sets the save_Answers value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @param save_Answers
     */
    public void setSave_Answers(boolean save_Answers) {
        this.save_Answers = save_Answers;
    }


    /**
     * Gets the save_Answer_Data value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @return save_Answer_Data
     */
    public boolean isSave_Answer_Data() {
        return save_Answer_Data;
    }


    /**
     * Sets the save_Answer_Data value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @param save_Answer_Data
     */
    public void setSave_Answer_Data(boolean save_Answer_Data) {
        this.save_Answer_Data = save_Answer_Data;
    }


    /**
     * Gets the number_Of_Blocks value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @return number_Of_Blocks
     */
    public int getNumber_Of_Blocks() {
        return number_Of_Blocks;
    }


    /**
     * Sets the number_Of_Blocks value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @param number_Of_Blocks
     */
    public void setNumber_Of_Blocks(int number_Of_Blocks) {
        this.number_Of_Blocks = number_Of_Blocks;
    }


    /**
     * Gets the number_Of_Questions value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @return number_Of_Questions
     */
    public int getNumber_Of_Questions() {
        return number_Of_Questions;
    }


    /**
     * Sets the number_Of_Questions value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @param number_Of_Questions
     */
    public void setNumber_Of_Questions(int number_Of_Questions) {
        this.number_Of_Questions = number_Of_Questions;
    }


    /**
     * Gets the course value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @return course
     */
    public java.lang.String getCourse() {
        return course;
    }


    /**
     * Sets the course value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @param course
     */
    public void setCourse(java.lang.String course) {
        this.course = course;
    }


    /**
     * Gets the time_Limit value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @return time_Limit
     */
    public int getTime_Limit() {
        return time_Limit;
    }


    /**
     * Sets the time_Limit value for this DeliveryAssessmentSnapshotHeader.
     * 
     * @param time_Limit
     */
    public void setTime_Limit(int time_Limit) {
        this.time_Limit = time_Limit;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeliveryAssessmentSnapshotHeader)) return false;
        DeliveryAssessmentSnapshotHeader other = (DeliveryAssessmentSnapshotHeader) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.assessment_Type == other.getAssessment_Type() &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.author==null && other.getAuthor()==null) || 
             (this.author!=null &&
              this.author.equals(other.getAuthor()))) &&
            this.save_Answers == other.isSave_Answers() &&
            this.save_Answer_Data == other.isSave_Answer_Data() &&
            this.number_Of_Blocks == other.getNumber_Of_Blocks() &&
            this.number_Of_Questions == other.getNumber_Of_Questions() &&
            ((this.course==null && other.getCourse()==null) || 
             (this.course!=null &&
              this.course.equals(other.getCourse()))) &&
            this.time_Limit == other.getTime_Limit();
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
        _hashCode += getAssessment_Type();
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getAuthor() != null) {
            _hashCode += getAuthor().hashCode();
        }
        _hashCode += (isSave_Answers() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isSave_Answer_Data() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getNumber_Of_Blocks();
        _hashCode += getNumber_Of_Questions();
        if (getCourse() != null) {
            _hashCode += getCourse().hashCode();
        }
        _hashCode += getTime_Limit();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeliveryAssessmentSnapshotHeader.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotHeader"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assessment_Type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment_Type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Name"));
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
        elemField.setFieldName("author");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Author"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("save_Answers");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Save_Answers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("save_Answer_Data");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Save_Answer_Data"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number_Of_Blocks");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Number_Of_Blocks"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number_Of_Questions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Number_Of_Questions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("course");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Course"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("time_Limit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Time_Limit"));
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
