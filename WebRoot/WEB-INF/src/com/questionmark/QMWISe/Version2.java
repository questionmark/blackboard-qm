/**
 * Version2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class Version2  extends com.questionmark.QMWISe.Version  implements java.io.Serializable {
    private java.lang.String licensingModel;

    private java.lang.String licenseExpires;

    private java.lang.String serverExpires;

    public Version2() {
    }

    public Version2(
           int majorVersion,
           int minorVersion,
           int buildVersion,
           java.lang.String buildString,
           java.lang.String buildDate,
           java.lang.String licenseText,
           java.lang.String QMDboLib,
           java.lang.String licensingModel,
           java.lang.String licenseExpires,
           java.lang.String serverExpires) {
        super(
            majorVersion,
            minorVersion,
            buildVersion,
            buildString,
            buildDate,
            licenseText,
            QMDboLib);
        this.licensingModel = licensingModel;
        this.licenseExpires = licenseExpires;
        this.serverExpires = serverExpires;
    }


    /**
     * Gets the licensingModel value for this Version2.
     * 
     * @return licensingModel
     */
    public java.lang.String getLicensingModel() {
        return licensingModel;
    }


    /**
     * Sets the licensingModel value for this Version2.
     * 
     * @param licensingModel
     */
    public void setLicensingModel(java.lang.String licensingModel) {
        this.licensingModel = licensingModel;
    }


    /**
     * Gets the licenseExpires value for this Version2.
     * 
     * @return licenseExpires
     */
    public java.lang.String getLicenseExpires() {
        return licenseExpires;
    }


    /**
     * Sets the licenseExpires value for this Version2.
     * 
     * @param licenseExpires
     */
    public void setLicenseExpires(java.lang.String licenseExpires) {
        this.licenseExpires = licenseExpires;
    }


    /**
     * Gets the serverExpires value for this Version2.
     * 
     * @return serverExpires
     */
    public java.lang.String getServerExpires() {
        return serverExpires;
    }


    /**
     * Sets the serverExpires value for this Version2.
     * 
     * @param serverExpires
     */
    public void setServerExpires(java.lang.String serverExpires) {
        this.serverExpires = serverExpires;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Version2)) return false;
        Version2 other = (Version2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.licensingModel==null && other.getLicensingModel()==null) || 
             (this.licensingModel!=null &&
              this.licensingModel.equals(other.getLicensingModel()))) &&
            ((this.licenseExpires==null && other.getLicenseExpires()==null) || 
             (this.licenseExpires!=null &&
              this.licenseExpires.equals(other.getLicenseExpires()))) &&
            ((this.serverExpires==null && other.getServerExpires()==null) || 
             (this.serverExpires!=null &&
              this.serverExpires.equals(other.getServerExpires())));
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
        if (getLicensingModel() != null) {
            _hashCode += getLicensingModel().hashCode();
        }
        if (getLicenseExpires() != null) {
            _hashCode += getLicenseExpires().hashCode();
        }
        if (getServerExpires() != null) {
            _hashCode += getServerExpires().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Version2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Version2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("licensingModel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "LicensingModel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("licenseExpires");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "LicenseExpires"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serverExpires");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ServerExpires"));
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
