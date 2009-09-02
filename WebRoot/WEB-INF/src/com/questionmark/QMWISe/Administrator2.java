/**
 * Administrator2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class Administrator2  implements java.io.Serializable {
    private java.lang.String administrator_ID;

    private java.lang.String administrator_Name;

    private java.lang.String password;

    private java.lang.String profile_Name;

    private int authenticateExt;

    private java.lang.String email;

    private java.lang.String URL;

    private java.lang.String first_Name;

    private java.lang.String last_Name;

    private java.lang.String department;

    public Administrator2() {
    }

    public Administrator2(
           java.lang.String administrator_ID,
           java.lang.String administrator_Name,
           java.lang.String password,
           java.lang.String profile_Name,
           int authenticateExt,
           java.lang.String email,
           java.lang.String URL,
           java.lang.String first_Name,
           java.lang.String last_Name,
           java.lang.String department) {
           this.administrator_ID = administrator_ID;
           this.administrator_Name = administrator_Name;
           this.password = password;
           this.profile_Name = profile_Name;
           this.authenticateExt = authenticateExt;
           this.email = email;
           this.URL = URL;
           this.first_Name = first_Name;
           this.last_Name = last_Name;
           this.department = department;
    }


    /**
     * Gets the administrator_ID value for this Administrator2.
     * 
     * @return administrator_ID
     */
    public java.lang.String getAdministrator_ID() {
        return administrator_ID;
    }


    /**
     * Sets the administrator_ID value for this Administrator2.
     * 
     * @param administrator_ID
     */
    public void setAdministrator_ID(java.lang.String administrator_ID) {
        this.administrator_ID = administrator_ID;
    }


    /**
     * Gets the administrator_Name value for this Administrator2.
     * 
     * @return administrator_Name
     */
    public java.lang.String getAdministrator_Name() {
        return administrator_Name;
    }


    /**
     * Sets the administrator_Name value for this Administrator2.
     * 
     * @param administrator_Name
     */
    public void setAdministrator_Name(java.lang.String administrator_Name) {
        this.administrator_Name = administrator_Name;
    }


    /**
     * Gets the password value for this Administrator2.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this Administrator2.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the profile_Name value for this Administrator2.
     * 
     * @return profile_Name
     */
    public java.lang.String getProfile_Name() {
        return profile_Name;
    }


    /**
     * Sets the profile_Name value for this Administrator2.
     * 
     * @param profile_Name
     */
    public void setProfile_Name(java.lang.String profile_Name) {
        this.profile_Name = profile_Name;
    }


    /**
     * Gets the authenticateExt value for this Administrator2.
     * 
     * @return authenticateExt
     */
    public int getAuthenticateExt() {
        return authenticateExt;
    }


    /**
     * Sets the authenticateExt value for this Administrator2.
     * 
     * @param authenticateExt
     */
    public void setAuthenticateExt(int authenticateExt) {
        this.authenticateExt = authenticateExt;
    }


    /**
     * Gets the email value for this Administrator2.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this Administrator2.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the URL value for this Administrator2.
     * 
     * @return URL
     */
    public java.lang.String getURL() {
        return URL;
    }


    /**
     * Sets the URL value for this Administrator2.
     * 
     * @param URL
     */
    public void setURL(java.lang.String URL) {
        this.URL = URL;
    }


    /**
     * Gets the first_Name value for this Administrator2.
     * 
     * @return first_Name
     */
    public java.lang.String getFirst_Name() {
        return first_Name;
    }


    /**
     * Sets the first_Name value for this Administrator2.
     * 
     * @param first_Name
     */
    public void setFirst_Name(java.lang.String first_Name) {
        this.first_Name = first_Name;
    }


    /**
     * Gets the last_Name value for this Administrator2.
     * 
     * @return last_Name
     */
    public java.lang.String getLast_Name() {
        return last_Name;
    }


    /**
     * Sets the last_Name value for this Administrator2.
     * 
     * @param last_Name
     */
    public void setLast_Name(java.lang.String last_Name) {
        this.last_Name = last_Name;
    }


    /**
     * Gets the department value for this Administrator2.
     * 
     * @return department
     */
    public java.lang.String getDepartment() {
        return department;
    }


    /**
     * Sets the department value for this Administrator2.
     * 
     * @param department
     */
    public void setDepartment(java.lang.String department) {
        this.department = department;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Administrator2)) return false;
        Administrator2 other = (Administrator2) obj;
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
              this.URL.equals(other.getURL()))) &&
            ((this.first_Name==null && other.getFirst_Name()==null) || 
             (this.first_Name!=null &&
              this.first_Name.equals(other.getFirst_Name()))) &&
            ((this.last_Name==null && other.getLast_Name()==null) || 
             (this.last_Name!=null &&
              this.last_Name.equals(other.getLast_Name()))) &&
            ((this.department==null && other.getDepartment()==null) || 
             (this.department!=null &&
              this.department.equals(other.getDepartment())));
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
        if (getFirst_Name() != null) {
            _hashCode += getFirst_Name().hashCode();
        }
        if (getLast_Name() != null) {
            _hashCode += getLast_Name().hashCode();
        }
        if (getDepartment() != null) {
            _hashCode += getDepartment().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Administrator2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator2"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("first_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "First_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("last_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Last_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("department");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Department"));
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
