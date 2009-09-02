/**
 * Group.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class Group  implements java.io.Serializable {
    private java.lang.String group_ID;

    private java.lang.String parent_ID;

    private java.lang.String group_Name;

    private java.lang.String description;

    private java.lang.String account_Internal_Ref;

    private java.lang.String account_Admin_Email;

    private java.lang.String directory_Name;

    private int account_Status;

    private java.lang.String special_1;

    private java.lang.String special_2;

    private java.lang.String special_3;

    private java.lang.String special_4;

    private java.lang.String special_5;

    private java.lang.String special_6;

    private java.lang.String special_7;

    private java.lang.String special_8;

    private java.lang.String special_9;

    private java.lang.String special_10;

    private int max_Participants;

    private int max_Sessions_Attempt;

    private int session_Taken;

    private java.lang.String account_Password;

    private int account_Type;

    private int use_Emailing;

    private java.lang.String email_Domains;

    private java.lang.String account_Finish;

    public Group() {
    }

    public Group(
           java.lang.String group_ID,
           java.lang.String parent_ID,
           java.lang.String group_Name,
           java.lang.String description,
           java.lang.String account_Internal_Ref,
           java.lang.String account_Admin_Email,
           java.lang.String directory_Name,
           int account_Status,
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
           int max_Participants,
           int max_Sessions_Attempt,
           int session_Taken,
           java.lang.String account_Password,
           int account_Type,
           int use_Emailing,
           java.lang.String email_Domains,
           java.lang.String account_Finish) {
           this.group_ID = group_ID;
           this.parent_ID = parent_ID;
           this.group_Name = group_Name;
           this.description = description;
           this.account_Internal_Ref = account_Internal_Ref;
           this.account_Admin_Email = account_Admin_Email;
           this.directory_Name = directory_Name;
           this.account_Status = account_Status;
           this.special_1 = special_1;
           this.special_2 = special_2;
           this.special_3 = special_3;
           this.special_4 = special_4;
           this.special_5 = special_5;
           this.special_6 = special_6;
           this.special_7 = special_7;
           this.special_8 = special_8;
           this.special_9 = special_9;
           this.special_10 = special_10;
           this.max_Participants = max_Participants;
           this.max_Sessions_Attempt = max_Sessions_Attempt;
           this.session_Taken = session_Taken;
           this.account_Password = account_Password;
           this.account_Type = account_Type;
           this.use_Emailing = use_Emailing;
           this.email_Domains = email_Domains;
           this.account_Finish = account_Finish;
    }


    /**
     * Gets the group_ID value for this Group.
     * 
     * @return group_ID
     */
    public java.lang.String getGroup_ID() {
        return group_ID;
    }


    /**
     * Sets the group_ID value for this Group.
     * 
     * @param group_ID
     */
    public void setGroup_ID(java.lang.String group_ID) {
        this.group_ID = group_ID;
    }


    /**
     * Gets the parent_ID value for this Group.
     * 
     * @return parent_ID
     */
    public java.lang.String getParent_ID() {
        return parent_ID;
    }


    /**
     * Sets the parent_ID value for this Group.
     * 
     * @param parent_ID
     */
    public void setParent_ID(java.lang.String parent_ID) {
        this.parent_ID = parent_ID;
    }


    /**
     * Gets the group_Name value for this Group.
     * 
     * @return group_Name
     */
    public java.lang.String getGroup_Name() {
        return group_Name;
    }


    /**
     * Sets the group_Name value for this Group.
     * 
     * @param group_Name
     */
    public void setGroup_Name(java.lang.String group_Name) {
        this.group_Name = group_Name;
    }


    /**
     * Gets the description value for this Group.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this Group.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the account_Internal_Ref value for this Group.
     * 
     * @return account_Internal_Ref
     */
    public java.lang.String getAccount_Internal_Ref() {
        return account_Internal_Ref;
    }


    /**
     * Sets the account_Internal_Ref value for this Group.
     * 
     * @param account_Internal_Ref
     */
    public void setAccount_Internal_Ref(java.lang.String account_Internal_Ref) {
        this.account_Internal_Ref = account_Internal_Ref;
    }


    /**
     * Gets the account_Admin_Email value for this Group.
     * 
     * @return account_Admin_Email
     */
    public java.lang.String getAccount_Admin_Email() {
        return account_Admin_Email;
    }


    /**
     * Sets the account_Admin_Email value for this Group.
     * 
     * @param account_Admin_Email
     */
    public void setAccount_Admin_Email(java.lang.String account_Admin_Email) {
        this.account_Admin_Email = account_Admin_Email;
    }


    /**
     * Gets the directory_Name value for this Group.
     * 
     * @return directory_Name
     */
    public java.lang.String getDirectory_Name() {
        return directory_Name;
    }


    /**
     * Sets the directory_Name value for this Group.
     * 
     * @param directory_Name
     */
    public void setDirectory_Name(java.lang.String directory_Name) {
        this.directory_Name = directory_Name;
    }


    /**
     * Gets the account_Status value for this Group.
     * 
     * @return account_Status
     */
    public int getAccount_Status() {
        return account_Status;
    }


    /**
     * Sets the account_Status value for this Group.
     * 
     * @param account_Status
     */
    public void setAccount_Status(int account_Status) {
        this.account_Status = account_Status;
    }


    /**
     * Gets the special_1 value for this Group.
     * 
     * @return special_1
     */
    public java.lang.String getSpecial_1() {
        return special_1;
    }


    /**
     * Sets the special_1 value for this Group.
     * 
     * @param special_1
     */
    public void setSpecial_1(java.lang.String special_1) {
        this.special_1 = special_1;
    }


    /**
     * Gets the special_2 value for this Group.
     * 
     * @return special_2
     */
    public java.lang.String getSpecial_2() {
        return special_2;
    }


    /**
     * Sets the special_2 value for this Group.
     * 
     * @param special_2
     */
    public void setSpecial_2(java.lang.String special_2) {
        this.special_2 = special_2;
    }


    /**
     * Gets the special_3 value for this Group.
     * 
     * @return special_3
     */
    public java.lang.String getSpecial_3() {
        return special_3;
    }


    /**
     * Sets the special_3 value for this Group.
     * 
     * @param special_3
     */
    public void setSpecial_3(java.lang.String special_3) {
        this.special_3 = special_3;
    }


    /**
     * Gets the special_4 value for this Group.
     * 
     * @return special_4
     */
    public java.lang.String getSpecial_4() {
        return special_4;
    }


    /**
     * Sets the special_4 value for this Group.
     * 
     * @param special_4
     */
    public void setSpecial_4(java.lang.String special_4) {
        this.special_4 = special_4;
    }


    /**
     * Gets the special_5 value for this Group.
     * 
     * @return special_5
     */
    public java.lang.String getSpecial_5() {
        return special_5;
    }


    /**
     * Sets the special_5 value for this Group.
     * 
     * @param special_5
     */
    public void setSpecial_5(java.lang.String special_5) {
        this.special_5 = special_5;
    }


    /**
     * Gets the special_6 value for this Group.
     * 
     * @return special_6
     */
    public java.lang.String getSpecial_6() {
        return special_6;
    }


    /**
     * Sets the special_6 value for this Group.
     * 
     * @param special_6
     */
    public void setSpecial_6(java.lang.String special_6) {
        this.special_6 = special_6;
    }


    /**
     * Gets the special_7 value for this Group.
     * 
     * @return special_7
     */
    public java.lang.String getSpecial_7() {
        return special_7;
    }


    /**
     * Sets the special_7 value for this Group.
     * 
     * @param special_7
     */
    public void setSpecial_7(java.lang.String special_7) {
        this.special_7 = special_7;
    }


    /**
     * Gets the special_8 value for this Group.
     * 
     * @return special_8
     */
    public java.lang.String getSpecial_8() {
        return special_8;
    }


    /**
     * Sets the special_8 value for this Group.
     * 
     * @param special_8
     */
    public void setSpecial_8(java.lang.String special_8) {
        this.special_8 = special_8;
    }


    /**
     * Gets the special_9 value for this Group.
     * 
     * @return special_9
     */
    public java.lang.String getSpecial_9() {
        return special_9;
    }


    /**
     * Sets the special_9 value for this Group.
     * 
     * @param special_9
     */
    public void setSpecial_9(java.lang.String special_9) {
        this.special_9 = special_9;
    }


    /**
     * Gets the special_10 value for this Group.
     * 
     * @return special_10
     */
    public java.lang.String getSpecial_10() {
        return special_10;
    }


    /**
     * Sets the special_10 value for this Group.
     * 
     * @param special_10
     */
    public void setSpecial_10(java.lang.String special_10) {
        this.special_10 = special_10;
    }


    /**
     * Gets the max_Participants value for this Group.
     * 
     * @return max_Participants
     */
    public int getMax_Participants() {
        return max_Participants;
    }


    /**
     * Sets the max_Participants value for this Group.
     * 
     * @param max_Participants
     */
    public void setMax_Participants(int max_Participants) {
        this.max_Participants = max_Participants;
    }


    /**
     * Gets the max_Sessions_Attempt value for this Group.
     * 
     * @return max_Sessions_Attempt
     */
    public int getMax_Sessions_Attempt() {
        return max_Sessions_Attempt;
    }


    /**
     * Sets the max_Sessions_Attempt value for this Group.
     * 
     * @param max_Sessions_Attempt
     */
    public void setMax_Sessions_Attempt(int max_Sessions_Attempt) {
        this.max_Sessions_Attempt = max_Sessions_Attempt;
    }


    /**
     * Gets the session_Taken value for this Group.
     * 
     * @return session_Taken
     */
    public int getSession_Taken() {
        return session_Taken;
    }


    /**
     * Sets the session_Taken value for this Group.
     * 
     * @param session_Taken
     */
    public void setSession_Taken(int session_Taken) {
        this.session_Taken = session_Taken;
    }


    /**
     * Gets the account_Password value for this Group.
     * 
     * @return account_Password
     */
    public java.lang.String getAccount_Password() {
        return account_Password;
    }


    /**
     * Sets the account_Password value for this Group.
     * 
     * @param account_Password
     */
    public void setAccount_Password(java.lang.String account_Password) {
        this.account_Password = account_Password;
    }


    /**
     * Gets the account_Type value for this Group.
     * 
     * @return account_Type
     */
    public int getAccount_Type() {
        return account_Type;
    }


    /**
     * Sets the account_Type value for this Group.
     * 
     * @param account_Type
     */
    public void setAccount_Type(int account_Type) {
        this.account_Type = account_Type;
    }


    /**
     * Gets the use_Emailing value for this Group.
     * 
     * @return use_Emailing
     */
    public int getUse_Emailing() {
        return use_Emailing;
    }


    /**
     * Sets the use_Emailing value for this Group.
     * 
     * @param use_Emailing
     */
    public void setUse_Emailing(int use_Emailing) {
        this.use_Emailing = use_Emailing;
    }


    /**
     * Gets the email_Domains value for this Group.
     * 
     * @return email_Domains
     */
    public java.lang.String getEmail_Domains() {
        return email_Domains;
    }


    /**
     * Sets the email_Domains value for this Group.
     * 
     * @param email_Domains
     */
    public void setEmail_Domains(java.lang.String email_Domains) {
        this.email_Domains = email_Domains;
    }


    /**
     * Gets the account_Finish value for this Group.
     * 
     * @return account_Finish
     */
    public java.lang.String getAccount_Finish() {
        return account_Finish;
    }


    /**
     * Sets the account_Finish value for this Group.
     * 
     * @param account_Finish
     */
    public void setAccount_Finish(java.lang.String account_Finish) {
        this.account_Finish = account_Finish;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Group)) return false;
        Group other = (Group) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.group_ID==null && other.getGroup_ID()==null) || 
             (this.group_ID!=null &&
              this.group_ID.equals(other.getGroup_ID()))) &&
            ((this.parent_ID==null && other.getParent_ID()==null) || 
             (this.parent_ID!=null &&
              this.parent_ID.equals(other.getParent_ID()))) &&
            ((this.group_Name==null && other.getGroup_Name()==null) || 
             (this.group_Name!=null &&
              this.group_Name.equals(other.getGroup_Name()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.account_Internal_Ref==null && other.getAccount_Internal_Ref()==null) || 
             (this.account_Internal_Ref!=null &&
              this.account_Internal_Ref.equals(other.getAccount_Internal_Ref()))) &&
            ((this.account_Admin_Email==null && other.getAccount_Admin_Email()==null) || 
             (this.account_Admin_Email!=null &&
              this.account_Admin_Email.equals(other.getAccount_Admin_Email()))) &&
            ((this.directory_Name==null && other.getDirectory_Name()==null) || 
             (this.directory_Name!=null &&
              this.directory_Name.equals(other.getDirectory_Name()))) &&
            this.account_Status == other.getAccount_Status() &&
            ((this.special_1==null && other.getSpecial_1()==null) || 
             (this.special_1!=null &&
              this.special_1.equals(other.getSpecial_1()))) &&
            ((this.special_2==null && other.getSpecial_2()==null) || 
             (this.special_2!=null &&
              this.special_2.equals(other.getSpecial_2()))) &&
            ((this.special_3==null && other.getSpecial_3()==null) || 
             (this.special_3!=null &&
              this.special_3.equals(other.getSpecial_3()))) &&
            ((this.special_4==null && other.getSpecial_4()==null) || 
             (this.special_4!=null &&
              this.special_4.equals(other.getSpecial_4()))) &&
            ((this.special_5==null && other.getSpecial_5()==null) || 
             (this.special_5!=null &&
              this.special_5.equals(other.getSpecial_5()))) &&
            ((this.special_6==null && other.getSpecial_6()==null) || 
             (this.special_6!=null &&
              this.special_6.equals(other.getSpecial_6()))) &&
            ((this.special_7==null && other.getSpecial_7()==null) || 
             (this.special_7!=null &&
              this.special_7.equals(other.getSpecial_7()))) &&
            ((this.special_8==null && other.getSpecial_8()==null) || 
             (this.special_8!=null &&
              this.special_8.equals(other.getSpecial_8()))) &&
            ((this.special_9==null && other.getSpecial_9()==null) || 
             (this.special_9!=null &&
              this.special_9.equals(other.getSpecial_9()))) &&
            ((this.special_10==null && other.getSpecial_10()==null) || 
             (this.special_10!=null &&
              this.special_10.equals(other.getSpecial_10()))) &&
            this.max_Participants == other.getMax_Participants() &&
            this.max_Sessions_Attempt == other.getMax_Sessions_Attempt() &&
            this.session_Taken == other.getSession_Taken() &&
            ((this.account_Password==null && other.getAccount_Password()==null) || 
             (this.account_Password!=null &&
              this.account_Password.equals(other.getAccount_Password()))) &&
            this.account_Type == other.getAccount_Type() &&
            this.use_Emailing == other.getUse_Emailing() &&
            ((this.email_Domains==null && other.getEmail_Domains()==null) || 
             (this.email_Domains!=null &&
              this.email_Domains.equals(other.getEmail_Domains()))) &&
            ((this.account_Finish==null && other.getAccount_Finish()==null) || 
             (this.account_Finish!=null &&
              this.account_Finish.equals(other.getAccount_Finish())));
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
        if (getGroup_ID() != null) {
            _hashCode += getGroup_ID().hashCode();
        }
        if (getParent_ID() != null) {
            _hashCode += getParent_ID().hashCode();
        }
        if (getGroup_Name() != null) {
            _hashCode += getGroup_Name().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getAccount_Internal_Ref() != null) {
            _hashCode += getAccount_Internal_Ref().hashCode();
        }
        if (getAccount_Admin_Email() != null) {
            _hashCode += getAccount_Admin_Email().hashCode();
        }
        if (getDirectory_Name() != null) {
            _hashCode += getDirectory_Name().hashCode();
        }
        _hashCode += getAccount_Status();
        if (getSpecial_1() != null) {
            _hashCode += getSpecial_1().hashCode();
        }
        if (getSpecial_2() != null) {
            _hashCode += getSpecial_2().hashCode();
        }
        if (getSpecial_3() != null) {
            _hashCode += getSpecial_3().hashCode();
        }
        if (getSpecial_4() != null) {
            _hashCode += getSpecial_4().hashCode();
        }
        if (getSpecial_5() != null) {
            _hashCode += getSpecial_5().hashCode();
        }
        if (getSpecial_6() != null) {
            _hashCode += getSpecial_6().hashCode();
        }
        if (getSpecial_7() != null) {
            _hashCode += getSpecial_7().hashCode();
        }
        if (getSpecial_8() != null) {
            _hashCode += getSpecial_8().hashCode();
        }
        if (getSpecial_9() != null) {
            _hashCode += getSpecial_9().hashCode();
        }
        if (getSpecial_10() != null) {
            _hashCode += getSpecial_10().hashCode();
        }
        _hashCode += getMax_Participants();
        _hashCode += getMax_Sessions_Attempt();
        _hashCode += getSession_Taken();
        if (getAccount_Password() != null) {
            _hashCode += getAccount_Password().hashCode();
        }
        _hashCode += getAccount_Type();
        _hashCode += getUse_Emailing();
        if (getEmail_Domains() != null) {
            _hashCode += getEmail_Domains().hashCode();
        }
        if (getAccount_Finish() != null) {
            _hashCode += getAccount_Finish().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Group.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("group_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parent_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Parent_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("group_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_Name"));
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
        elemField.setFieldName("account_Internal_Ref");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Account_Internal_Ref"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("account_Admin_Email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Account_Admin_Email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("directory_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Directory_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("account_Status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Account_Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_6");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_7");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_7"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_8");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_8"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_9");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_10");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_10"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("max_Participants");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Max_Participants"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("max_Sessions_Attempt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Max_Sessions_Attempt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("session_Taken");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Session_Taken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("account_Password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Account_Password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("account_Type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Account_Type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("use_Emailing");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Use_Emailing"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email_Domains");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Email_Domains"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("account_Finish");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Account_Finish"));
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
