/**
 * Version.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class Version  implements java.io.Serializable {
    private int majorVersion;

    private int minorVersion;

    private int buildVersion;

    private java.lang.String buildString;

    private java.lang.String buildDate;

    private java.lang.String licenseText;

    private java.lang.String QMDboLib;

    public Version() {
    }

    public Version(
           int majorVersion,
           int minorVersion,
           int buildVersion,
           java.lang.String buildString,
           java.lang.String buildDate,
           java.lang.String licenseText,
           java.lang.String QMDboLib) {
           this.majorVersion = majorVersion;
           this.minorVersion = minorVersion;
           this.buildVersion = buildVersion;
           this.buildString = buildString;
           this.buildDate = buildDate;
           this.licenseText = licenseText;
           this.QMDboLib = QMDboLib;
    }


    /**
     * Gets the majorVersion value for this Version.
     * 
     * @return majorVersion
     */
    public int getMajorVersion() {
        return majorVersion;
    }


    /**
     * Sets the majorVersion value for this Version.
     * 
     * @param majorVersion
     */
    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }


    /**
     * Gets the minorVersion value for this Version.
     * 
     * @return minorVersion
     */
    public int getMinorVersion() {
        return minorVersion;
    }


    /**
     * Sets the minorVersion value for this Version.
     * 
     * @param minorVersion
     */
    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }


    /**
     * Gets the buildVersion value for this Version.
     * 
     * @return buildVersion
     */
    public int getBuildVersion() {
        return buildVersion;
    }


    /**
     * Sets the buildVersion value for this Version.
     * 
     * @param buildVersion
     */
    public void setBuildVersion(int buildVersion) {
        this.buildVersion = buildVersion;
    }


    /**
     * Gets the buildString value for this Version.
     * 
     * @return buildString
     */
    public java.lang.String getBuildString() {
        return buildString;
    }


    /**
     * Sets the buildString value for this Version.
     * 
     * @param buildString
     */
    public void setBuildString(java.lang.String buildString) {
        this.buildString = buildString;
    }


    /**
     * Gets the buildDate value for this Version.
     * 
     * @return buildDate
     */
    public java.lang.String getBuildDate() {
        return buildDate;
    }


    /**
     * Sets the buildDate value for this Version.
     * 
     * @param buildDate
     */
    public void setBuildDate(java.lang.String buildDate) {
        this.buildDate = buildDate;
    }


    /**
     * Gets the licenseText value for this Version.
     * 
     * @return licenseText
     */
    public java.lang.String getLicenseText() {
        return licenseText;
    }


    /**
     * Sets the licenseText value for this Version.
     * 
     * @param licenseText
     */
    public void setLicenseText(java.lang.String licenseText) {
        this.licenseText = licenseText;
    }


    /**
     * Gets the QMDboLib value for this Version.
     * 
     * @return QMDboLib
     */
    public java.lang.String getQMDboLib() {
        return QMDboLib;
    }


    /**
     * Sets the QMDboLib value for this Version.
     * 
     * @param QMDboLib
     */
    public void setQMDboLib(java.lang.String QMDboLib) {
        this.QMDboLib = QMDboLib;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Version)) return false;
        Version other = (Version) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.majorVersion == other.getMajorVersion() &&
            this.minorVersion == other.getMinorVersion() &&
            this.buildVersion == other.getBuildVersion() &&
            ((this.buildString==null && other.getBuildString()==null) || 
             (this.buildString!=null &&
              this.buildString.equals(other.getBuildString()))) &&
            ((this.buildDate==null && other.getBuildDate()==null) || 
             (this.buildDate!=null &&
              this.buildDate.equals(other.getBuildDate()))) &&
            ((this.licenseText==null && other.getLicenseText()==null) || 
             (this.licenseText!=null &&
              this.licenseText.equals(other.getLicenseText()))) &&
            ((this.QMDboLib==null && other.getQMDboLib()==null) || 
             (this.QMDboLib!=null &&
              this.QMDboLib.equals(other.getQMDboLib())));
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
        _hashCode += getMajorVersion();
        _hashCode += getMinorVersion();
        _hashCode += getBuildVersion();
        if (getBuildString() != null) {
            _hashCode += getBuildString().hashCode();
        }
        if (getBuildDate() != null) {
            _hashCode += getBuildDate().hashCode();
        }
        if (getLicenseText() != null) {
            _hashCode += getLicenseText().hashCode();
        }
        if (getQMDboLib() != null) {
            _hashCode += getQMDboLib().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Version.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Version"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("majorVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "MajorVersion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minorVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "MinorVersion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buildVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "BuildVersion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buildString");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "BuildString"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buildDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "BuildDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("licenseText");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "LicenseText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("QMDboLib");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "QMDboLib"));
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
