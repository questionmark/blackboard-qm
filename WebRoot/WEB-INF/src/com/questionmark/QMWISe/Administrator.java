/**
 * Administrator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class Administrator  implements java.io.Serializable {
    private java.lang.String administrator_ID;

    private java.lang.String administrator_Name;

    private java.lang.String password;

    private java.lang.String profile_Name;

    private int authenticateExt;

    private java.lang.String email;

    private java.lang.String URL;

    public Administrator() {
    }

    public Administrator(
           java.lang.String administrator_ID,
           java.lang.String administrator_Name,
           java.lang.String password,
           java.lang.String profile_Name,
           int authenticateExt,
           java.lang.String email,
           java.lang.String URL) {
           this.administrator_ID = administrator_ID;
           this.administrator_Name = administrator_Name;
           this.password = password;
           this.profile_Name = profile_Name;
           this.authenticateExt = authenticateExt;
           this.email = email;
           this.URL = URL;
    }


    /**
     * Gets the administrator_ID value for this Administrator.
     * 
     * @return administrator_ID
     */
    public java.lang.String getAdministrator_ID() {
        return administrator_ID;
    }


    /**
     * Sets the administrator_ID value for this Administrator.
     * 
     * @param administrator_ID
     */
    public void setAdministrator_ID(java.lang.String administrator_ID) {
        this.administrator_ID = administrator_ID;
    }


    /**
     * Gets the administrator_Name value for this Administrator.
     * 
     * @return administrator_Name
     */
    public java.lang.String getAdministrator_Name() {
        return administrator_Name;
    }


    /**
     * Sets the administrator_Name value for this Administrator.
     * 
     * @param administrator_Name
     */
    public void setAdministrator_Name(java.lang.String administrator_Name) {
        this.administrator_Name = administrator_Name;
    }


    /**
     * Gets the password value for this Administrator.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this Administrator.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the profile_Name value for this Administrator.
     * 
     * @return profile_Name
     */
    public java.lang.String getProfile_Name() {
        return profile_Name;
    }


    /**
     * Sets the profile_Name value for this Administrator.
     * 
     * @param profile_Name
     */
    public void setProfile_Name(java.lang.String profile_Name) {
        this.profile_Name = profile_Name;
    }


    /**
     * Gets the authenticateExt value for this Administrator.
     * 
     * @return authenticateExt
     */
    public int getAuthenticateExt() {
        return authenticateExt;
    }


    /**
     * Sets the authenticateExt value for this Administrator.
     * 
     * @param authenticateExt
     */
    public void setAuthenticateExt(int authenticateExt) {
        this.authenticateExt = authenticateExt;
    }


    /**
     * Gets the email value for this Administrator.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this Administrator.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the URL value for this Administrator.
     * 
     * @return URL
     */
    public java.lang.String getURL() {
        return URL;
    }


    /**
     * Sets the URL value for this Administrator.
     * 
     * @param URL
     */
    public void setURL(java.lang.String URL) {
        this.URL = URL;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Administrator)) return false;
        Administrator other = (Administrator) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.administrator_ID==null && other.getAdministrator_ID()==null) || 
             (this.administrator_ID!=null &&
              this.administrator_ID.equals(other.getAdministrator_ID()))) &&
            ((this.administrator_Name==null && other.getAdministrator_Name()==null) || 
             (this.administrator_Name!=null &&
              this.administrator_Name.equals(other.getAdministrator_Name()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.profile_Name==null && other.getProfile_Name()==null) || 
             (this.profile_Name!=null &&
              this.profile_Name.equals(other.getProfile_Name()))) &&
            this.authenticateExt == other.getAuthenticateExt() &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.URL==null && other.getURL()==null) || 
             (this.URL!=null &&
              this.URL.equals(other.getURL())));
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
        if (getAdministrator_ID() != null) {
            _hashCode += getAdministrator_ID().hashCode();
        }
        if (getAdministrator_Name() != null) {
            _hashCode += getAdministrator_Name().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getProfile_Name() != null) {
            _hashCode += getProfile_Name().hashCode();
        }
        _hashCode += getAuthenticateExt();
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getURL() != null) {
            _hashCode += getURL().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Administrator.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("administrator_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("administrator_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profile_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Profile_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authenticateExt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AuthenticateExt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("URL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "URL"));
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
