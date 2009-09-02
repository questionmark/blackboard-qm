/**
 * AssessmentResultInfo2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class AssessmentResultInfo2  extends com.questionmark.QMWISe.AssessmentResultInfo  implements java.io.Serializable {
    private int assessment_TypeProperty;

    private java.lang.String courseProperty;

    public AssessmentResultInfo2() {
    }

    public AssessmentResultInfo2(
           java.lang.String assessment_ID,
           int revision,
           java.lang.String session_Name,
           java.lang.String session_Author,
           boolean whether_Time_Limit,
           int time_Limit,
           short number_Sections,
           java.lang.String description,
           java.lang.String last_Updated,
           java.lang.String when_Modified,
           int assessment_TypeProperty,
           java.lang.String courseProperty) {
        super(
            assessment_ID,
            revision,
            session_Name,
            session_Author,
            whether_Time_Limit,
            time_Limit,
            number_Sections,
            description,
            last_Updated,
            when_Modified);
        this.assessment_TypeProperty = assessment_TypeProperty;
        this.courseProperty = courseProperty;
    }


    /**
     * Gets the assessment_TypeProperty value for this AssessmentResultInfo2.
     * 
     * @return assessment_TypeProperty
     */
    public int getAssessment_TypeProperty() {
        return assessment_TypeProperty;
    }


    /**
     * Sets the assessment_TypeProperty value for this AssessmentResultInfo2.
     * 
     * @param assessment_TypeProperty
     */
    public void setAssessment_TypeProperty(int assessment_TypeProperty) {
        this.assessment_TypeProperty = assessment_TypeProperty;
    }


    /**
     * Gets the courseProperty value for this AssessmentResultInfo2.
     * 
     * @return courseProperty
     */
    public java.lang.String getCourseProperty() {
        return courseProperty;
    }


    /**
     * Sets the courseProperty value for this AssessmentResultInfo2.
     * 
     * @param courseProperty
     */
    public void setCourseProperty(java.lang.String courseProperty) {
        this.courseProperty = courseProperty;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssessmentResultInfo2)) return false;
        AssessmentResultInfo2 other = (AssessmentResultInfo2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.assessment_TypeProperty == other.getAssessment_TypeProperty() &&
            ((this.courseProperty==null && other.getCourseProperty()==null) || 
             (this.courseProperty!=null &&
              this.courseProperty.equals(other.getCourseProperty())));
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
        _hashCode += getAssessment_TypeProperty();
        if (getCourseProperty() != null) {
            _hashCode += getCourseProperty().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssessmentResultInfo2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfo2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assessment_TypeProperty");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment_TypeProperty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("courseProperty");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CourseProperty"));
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
