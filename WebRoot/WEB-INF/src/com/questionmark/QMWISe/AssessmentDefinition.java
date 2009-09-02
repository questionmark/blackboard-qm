/**
 * AssessmentDefinition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class AssessmentDefinition  implements java.io.Serializable {
    private com.questionmark.QMWISe.Assessment assessment;

    private com.questionmark.QMWISe.AssessmentBlock[] assessmentBlockList;

    private com.questionmark.QMWISe.AssessmentOutcome[] assessmentOutcomeList;

    public AssessmentDefinition() {
    }

    public AssessmentDefinition(
           com.questionmark.QMWISe.Assessment assessment,
           com.questionmark.QMWISe.AssessmentBlock[] assessmentBlockList,
           com.questionmark.QMWISe.AssessmentOutcome[] assessmentOutcomeList) {
           this.assessment = assessment;
           this.assessmentBlockList = assessmentBlockList;
           this.assessmentOutcomeList = assessmentOutcomeList;
    }


    /**
     * Gets the assessment value for this AssessmentDefinition.
     * 
     * @return assessment
     */
    public com.questionmark.QMWISe.Assessment getAssessment() {
        return assessment;
    }


    /**
     * Sets the assessment value for this AssessmentDefinition.
     * 
     * @param assessment
     */
    public void setAssessment(com.questionmark.QMWISe.Assessment assessment) {
        this.assessment = assessment;
    }


    /**
     * Gets the assessmentBlockList value for this AssessmentDefinition.
     * 
     * @return assessmentBlockList
     */
    public com.questionmark.QMWISe.AssessmentBlock[] getAssessmentBlockList() {
        return assessmentBlockList;
    }


    /**
     * Sets the assessmentBlockList value for this AssessmentDefinition.
     * 
     * @param assessmentBlockList
     */
    public void setAssessmentBlockList(com.questionmark.QMWISe.AssessmentBlock[] assessmentBlockList) {
        this.assessmentBlockList = assessmentBlockList;
    }


    /**
     * Gets the assessmentOutcomeList value for this AssessmentDefinition.
     * 
     * @return assessmentOutcomeList
     */
    public com.questionmark.QMWISe.AssessmentOutcome[] getAssessmentOutcomeList() {
        return assessmentOutcomeList;
    }


    /**
     * Sets the assessmentOutcomeList value for this AssessmentDefinition.
     * 
     * @param assessmentOutcomeList
     */
    public void setAssessmentOutcomeList(com.questionmark.QMWISe.AssessmentOutcome[] assessmentOutcomeList) {
        this.assessmentOutcomeList = assessmentOutcomeList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssessmentDefinition)) return false;
        AssessmentDefinition other = (AssessmentDefinition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.assessment==null && other.getAssessment()==null) || 
             (this.assessment!=null &&
              this.assessment.equals(other.getAssessment()))) &&
            ((this.assessmentBlockList==null && other.getAssessmentBlockList()==null) || 
             (this.assessmentBlockList!=null &&
              java.util.Arrays.equals(this.assessmentBlockList, other.getAssessmentBlockList()))) &&
            ((this.assessmentOutcomeList==null && other.getAssessmentOutcomeList()==null) || 
             (this.assessmentOutcomeList!=null &&
              java.util.Arrays.equals(this.assessmentOutcomeList, other.getAssessmentOutcomeList())));
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
        if (getAssessment() != null) {
            _hashCode += getAssessment().hashCode();
        }
        if (getAssessmentBlockList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAssessmentBlockList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAssessmentBlockList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAssessmentOutcomeList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAssessmentOutcomeList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAssessmentOutcomeList(), i);
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
        new org.apache.axis.description.TypeDesc(AssessmentDefinition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentDefinition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assessment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assessmentBlockList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentBlockList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentBlock"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentBlock"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assessmentOutcomeList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentOutcomeList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentOutcome"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentOutcome"));
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
