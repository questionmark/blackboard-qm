/**
 * AssessmentResultInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class AssessmentResultInfo  implements java.io.Serializable {
    private java.lang.String assessment_ID;

    private int revision;

    private java.lang.String session_Name;

    private java.lang.String session_Author;

    private boolean whether_Time_Limit;

    private int time_Limit;

    private short number_Sections;

    private java.lang.String description;

    private java.lang.String last_Updated;

    private java.lang.String when_Modified;

    public AssessmentResultInfo() {
    }

    public AssessmentResultInfo(
           java.lang.String assessment_ID,
           int revision,
           java.lang.String session_Name,
           java.lang.String session_Author,
           boolean whether_Time_Limit,
           int time_Limit,
           short number_Sections,
           java.lang.String description,
           java.lang.String last_Updated,
           java.lang.String when_Modified) {
           this.assessment_ID = assessment_ID;
           this.revision = revision;
           this.session_Name = session_Name;
           this.session_Author = session_Author;
           this.whether_Time_Limit = whether_Time_Limit;
           this.time_Limit = time_Limit;
           this.number_Sections = number_Sections;
           this.description = description;
           this.last_Updated = last_Updated;
           this.when_Modified = when_Modified;
    }


    /**
     * Gets the assessment_ID value for this AssessmentResultInfo.
     * 
     * @return assessment_ID
     */
    public java.lang.String getAssessment_ID() {
        return assessment_ID;
    }


    /**
     * Sets the assessment_ID value for this AssessmentResultInfo.
     * 
     * @param assessment_ID
     */
    public void setAssessment_ID(java.lang.String assessment_ID) {
        this.assessment_ID = assessment_ID;
    }


    /**
     * Gets the revision value for this AssessmentResultInfo.
     * 
     * @return revision
     */
    public int getRevision() {
        return revision;
    }


    /**
     * Sets the revision value for this AssessmentResultInfo.
     * 
     * @param revision
     */
    public void setRevision(int revision) {
        this.revision = revision;
    }


    /**
     * Gets the session_Name value for this AssessmentResultInfo.
     * 
     * @return session_Name
     */
    public java.lang.String getSession_Name() {
        return session_Name;
    }


    /**
     * Sets the session_Name value for this AssessmentResultInfo.
     * 
     * @param session_Name
     */
    public void setSession_Name(java.lang.String session_Name) {
        this.session_Name = session_Name;
    }


    /**
     * Gets the session_Author value for this AssessmentResultInfo.
     * 
     * @return session_Author
     */
    public java.lang.String getSession_Author() {
        return session_Author;
    }


    /**
     * Sets the session_Author value for this AssessmentResultInfo.
     * 
     * @param session_Author
     */
    public void setSession_Author(java.lang.String session_Author) {
        this.session_Author = session_Author;
    }


    /**
     * Gets the whether_Time_Limit value for this AssessmentResultInfo.
     * 
     * @return whether_Time_Limit
     */
    public boolean isWhether_Time_Limit() {
        return whether_Time_Limit;
    }


    /**
     * Sets the whether_Time_Limit value for this AssessmentResultInfo.
     * 
     * @param whether_Time_Limit
     */
    public void setWhether_Time_Limit(boolean whether_Time_Limit) {
        this.whether_Time_Limit = whether_Time_Limit;
    }


    /**
     * Gets the time_Limit value for this AssessmentResultInfo.
     * 
     * @return time_Limit
     */
    public int getTime_Limit() {
        return time_Limit;
    }


    /**
     * Sets the time_Limit value for this AssessmentResultInfo.
     * 
     * @param time_Limit
     */
    public void setTime_Limit(int time_Limit) {
        this.time_Limit = time_Limit;
    }


    /**
     * Gets the number_Sections value for this AssessmentResultInfo.
     * 
     * @return number_Sections
     */
    public short getNumber_Sections() {
        return number_Sections;
    }


    /**
     * Sets the number_Sections value for this AssessmentResultInfo.
     * 
     * @param number_Sections
     */
    public void setNumber_Sections(short number_Sections) {
        this.number_Sections = number_Sections;
    }


    /**
     * Gets the description value for this AssessmentResultInfo.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this AssessmentResultInfo.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the last_Updated value for this AssessmentResultInfo.
     * 
     * @return last_Updated
     */
    public java.lang.String getLast_Updated() {
        return last_Updated;
    }


    /**
     * Sets the last_Updated value for this AssessmentResultInfo.
     * 
     * @param last_Updated
     */
    public void setLast_Updated(java.lang.String last_Updated) {
        this.last_Updated = last_Updated;
    }


    /**
     * Gets the when_Modified value for this AssessmentResultInfo.
     * 
     * @return when_Modified
     */
    public java.lang.String getWhen_Modified() {
        return when_Modified;
    }


    /**
     * Sets the when_Modified value for this AssessmentResultInfo.
     * 
     * @param when_Modified
     */
    public void setWhen_Modified(java.lang.String when_Modified) {
        this.when_Modified = when_Modified;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssessmentResultInfo)) return false;
        AssessmentResultInfo other = (AssessmentResultInfo) obj;
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
            this.revision == other.getRevision() &&
            ((this.session_Name==null && other.getSession_Name()==null) || 
             (this.session_Name!=null &&
              this.session_Name.equals(other.getSession_Name()))) &&
            ((this.session_Author==null && other.getSession_Author()==null) || 
             (this.session_Author!=null &&
              this.session_Author.equals(other.getSession_Author()))) &&
            this.whether_Time_Limit == other.isWhether_Time_Limit() &&
            this.time_Limit == other.getTime_Limit() &&
            this.number_Sections == other.getNumber_Sections() &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.last_Updated==null && other.getLast_Updated()==null) || 
             (this.last_Updated!=null &&
              this.last_Updated.equals(other.getLast_Updated()))) &&
            ((this.when_Modified==null && other.getWhen_Modified()==null) || 
             (this.when_Modified!=null &&
              this.when_Modified.equals(other.getWhen_Modified())));
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
        _hashCode += getRevision();
        if (getSession_Name() != null) {
            _hashCode += getSession_Name().hashCode();
        }
        if (getSession_Author() != null) {
            _hashCode += getSession_Author().hashCode();
        }
        _hashCode += (isWhether_Time_Limit() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getTime_Limit();
        _hashCode += getNumber_Sections();
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getLast_Updated() != null) {
            _hashCode += getLast_Updated().hashCode();
        }
        if (getWhen_Modified() != null) {
            _hashCode += getWhen_Modified().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssessmentResultInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assessment_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment_ID"));
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
        elemField.setFieldName("session_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Session_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("session_Author");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Session_Author"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("whether_Time_Limit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Whether_Time_Limit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("time_Limit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Time_Limit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number_Sections");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Number_Sections"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
        elemField.setFieldName("last_Updated");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Last_Updated"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("when_Modified");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "When_Modified"));
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
