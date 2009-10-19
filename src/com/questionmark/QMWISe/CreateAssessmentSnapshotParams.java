/**
 * CreateAssessmentSnapshotParams.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class CreateAssessmentSnapshotParams  implements java.io.Serializable {
    private org.apache.axis.types.UnsignedLong assessment_ID;

    private java.lang.String random_Key;

    private boolean save_Snapshot;

    private int expiry;

    private java.util.Calendar expiry_Date;

    public CreateAssessmentSnapshotParams() {
    }

    public CreateAssessmentSnapshotParams(
           org.apache.axis.types.UnsignedLong assessment_ID,
           java.lang.String random_Key,
           boolean save_Snapshot,
           int expiry,
           java.util.Calendar expiry_Date) {
           this.assessment_ID = assessment_ID;
           this.random_Key = random_Key;
           this.save_Snapshot = save_Snapshot;
           this.expiry = expiry;
           this.expiry_Date = expiry_Date;
    }


    /**
     * Gets the assessment_ID value for this CreateAssessmentSnapshotParams.
     * 
     * @return assessment_ID
     */
    public org.apache.axis.types.UnsignedLong getAssessment_ID() {
        return assessment_ID;
    }


    /**
     * Sets the assessment_ID value for this CreateAssessmentSnapshotParams.
     * 
     * @param assessment_ID
     */
    public void setAssessment_ID(org.apache.axis.types.UnsignedLong assessment_ID) {
        this.assessment_ID = assessment_ID;
    }


    /**
     * Gets the random_Key value for this CreateAssessmentSnapshotParams.
     * 
     * @return random_Key
     */
    public java.lang.String getRandom_Key() {
        return random_Key;
    }


    /**
     * Sets the random_Key value for this CreateAssessmentSnapshotParams.
     * 
     * @param random_Key
     */
    public void setRandom_Key(java.lang.String random_Key) {
        this.random_Key = random_Key;
    }


    /**
     * Gets the save_Snapshot value for this CreateAssessmentSnapshotParams.
     * 
     * @return save_Snapshot
     */
    public boolean isSave_Snapshot() {
        return save_Snapshot;
    }


    /**
     * Sets the save_Snapshot value for this CreateAssessmentSnapshotParams.
     * 
     * @param save_Snapshot
     */
    public void setSave_Snapshot(boolean save_Snapshot) {
        this.save_Snapshot = save_Snapshot;
    }


    /**
     * Gets the expiry value for this CreateAssessmentSnapshotParams.
     * 
     * @return expiry
     */
    public int getExpiry() {
        return expiry;
    }


    /**
     * Sets the expiry value for this CreateAssessmentSnapshotParams.
     * 
     * @param expiry
     */
    public void setExpiry(int expiry) {
        this.expiry = expiry;
    }


    /**
     * Gets the expiry_Date value for this CreateAssessmentSnapshotParams.
     * 
     * @return expiry_Date
     */
    public java.util.Calendar getExpiry_Date() {
        return expiry_Date;
    }


    /**
     * Sets the expiry_Date value for this CreateAssessmentSnapshotParams.
     * 
     * @param expiry_Date
     */
    public void setExpiry_Date(java.util.Calendar expiry_Date) {
        this.expiry_Date = expiry_Date;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateAssessmentSnapshotParams)) return false;
        CreateAssessmentSnapshotParams other = (CreateAssessmentSnapshotParams) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.assessment_ID==null && other.getAssessment_ID()==null) || 
             (this.assessment_ID!=null &&
              this.assessment_ID.equals(other.getAssessment_ID()))) &&
            ((this.random_Key==null && other.getRandom_Key()==null) || 
             (this.random_Key!=null &&
              this.random_Key.equals(other.getRandom_Key()))) &&
            this.save_Snapshot == other.isSave_Snapshot() &&
            this.expiry == other.getExpiry() &&
            ((this.expiry_Date==null && other.getExpiry_Date()==null) || 
             (this.expiry_Date!=null &&
              this.expiry_Date.equals(other.getExpiry_Date())));
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
        if (getAssessment_ID() != null) {
            _hashCode += getAssessment_ID().hashCode();
        }
        if (getRandom_Key() != null) {
            _hashCode += getRandom_Key().hashCode();
        }
        _hashCode += (isSave_Snapshot() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getExpiry();
        if (getExpiry_Date() != null) {
            _hashCode += getExpiry_Date().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateAssessmentSnapshotParams.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAssessmentSnapshotParams"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assessment_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedLong"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("random_Key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Random_Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("save_Snapshot");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Save_Snapshot"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expiry");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Expiry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expiry_Date");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Expiry_Date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
