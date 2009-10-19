/**
 * Assessment.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class Assessment  implements java.io.Serializable {
    private java.lang.String assessment_ID;

    private int revision;

    private java.lang.String session_Name;

    private java.lang.String author;

    private boolean save_Answers;

    private boolean save_Answer_Data;

    private boolean open_Session;

    private java.lang.String session_Password;

    private boolean session_Timed;

    private int time_Limit;

    private java.lang.String template_Name;

    private int when_Feedback;

    private int end_Feedback;

    private boolean exclude_Unscored;

    private java.lang.Integer folder_ID;

    private java.lang.String description;

    private int monitored;

    private java.lang.String editor;

    private java.lang.String version;

    private boolean permit_External_Call;

    private java.lang.String created_Date;

    private java.lang.String modified_Date;

    public Assessment() {
    }

    public Assessment(
           java.lang.String assessment_ID,
           int revision,
           java.lang.String session_Name,
           java.lang.String author,
           boolean save_Answers,
           boolean save_Answer_Data,
           boolean open_Session,
           java.lang.String session_Password,
           boolean session_Timed,
           int time_Limit,
           java.lang.String template_Name,
           int when_Feedback,
           int end_Feedback,
           boolean exclude_Unscored,
           java.lang.Integer folder_ID,
           java.lang.String description,
           int monitored,
           java.lang.String editor,
           java.lang.String version,
           boolean permit_External_Call,
           java.lang.String created_Date,
           java.lang.String modified_Date) {
           this.assessment_ID = assessment_ID;
           this.revision = revision;
           this.session_Name = session_Name;
           this.author = author;
           this.save_Answers = save_Answers;
           this.save_Answer_Data = save_Answer_Data;
           this.open_Session = open_Session;
           this.session_Password = session_Password;
           this.session_Timed = session_Timed;
           this.time_Limit = time_Limit;
           this.template_Name = template_Name;
           this.when_Feedback = when_Feedback;
           this.end_Feedback = end_Feedback;
           this.exclude_Unscored = exclude_Unscored;
           this.folder_ID = folder_ID;
           this.description = description;
           this.monitored = monitored;
           this.editor = editor;
           this.version = version;
           this.permit_External_Call = permit_External_Call;
           this.created_Date = created_Date;
           this.modified_Date = modified_Date;
    }


    /**
     * Gets the assessment_ID value for this Assessment.
     * 
     * @return assessment_ID
     */
    public java.lang.String getAssessment_ID() {
        return assessment_ID;
    }


    /**
     * Sets the assessment_ID value for this Assessment.
     * 
     * @param assessment_ID
     */
    public void setAssessment_ID(java.lang.String assessment_ID) {
        this.assessment_ID = assessment_ID;
    }


    /**
     * Gets the revision value for this Assessment.
     * 
     * @return revision
     */
    public int getRevision() {
        return revision;
    }


    /**
     * Sets the revision value for this Assessment.
     * 
     * @param revision
     */
    public void setRevision(int revision) {
        this.revision = revision;
    }


    /**
     * Gets the session_Name value for this Assessment.
     * 
     * @return session_Name
     */
    public java.lang.String getSession_Name() {
        return session_Name;
    }


    /**
     * Sets the session_Name value for this Assessment.
     * 
     * @param session_Name
     */
    public void setSession_Name(java.lang.String session_Name) {
        this.session_Name = session_Name;
    }


    /**
     * Gets the author value for this Assessment.
     * 
     * @return author
     */
    public java.lang.String getAuthor() {
        return author;
    }


    /**
     * Sets the author value for this Assessment.
     * 
     * @param author
     */
    public void setAuthor(java.lang.String author) {
        this.author = author;
    }


    /**
     * Gets the save_Answers value for this Assessment.
     * 
     * @return save_Answers
     */
    public boolean isSave_Answers() {
        return save_Answers;
    }


    /**
     * Sets the save_Answers value for this Assessment.
     * 
     * @param save_Answers
     */
    public void setSave_Answers(boolean save_Answers) {
        this.save_Answers = save_Answers;
    }


    /**
     * Gets the save_Answer_Data value for this Assessment.
     * 
     * @return save_Answer_Data
     */
    public boolean isSave_Answer_Data() {
        return save_Answer_Data;
    }


    /**
     * Sets the save_Answer_Data value for this Assessment.
     * 
     * @param save_Answer_Data
     */
    public void setSave_Answer_Data(boolean save_Answer_Data) {
        this.save_Answer_Data = save_Answer_Data;
    }


    /**
     * Gets the open_Session value for this Assessment.
     * 
     * @return open_Session
     */
    public boolean isOpen_Session() {
        return open_Session;
    }


    /**
     * Sets the open_Session value for this Assessment.
     * 
     * @param open_Session
     */
    public void setOpen_Session(boolean open_Session) {
        this.open_Session = open_Session;
    }


    /**
     * Gets the session_Password value for this Assessment.
     * 
     * @return session_Password
     */
    public java.lang.String getSession_Password() {
        return session_Password;
    }


    /**
     * Sets the session_Password value for this Assessment.
     * 
     * @param session_Password
     */
    public void setSession_Password(java.lang.String session_Password) {
        this.session_Password = session_Password;
    }


    /**
     * Gets the session_Timed value for this Assessment.
     * 
     * @return session_Timed
     */
    public boolean isSession_Timed() {
        return session_Timed;
    }


    /**
     * Sets the session_Timed value for this Assessment.
     * 
     * @param session_Timed
     */
    public void setSession_Timed(boolean session_Timed) {
        this.session_Timed = session_Timed;
    }


    /**
     * Gets the time_Limit value for this Assessment.
     * 
     * @return time_Limit
     */
    public int getTime_Limit() {
        return time_Limit;
    }


    /**
     * Sets the time_Limit value for this Assessment.
     * 
     * @param time_Limit
     */
    public void setTime_Limit(int time_Limit) {
        this.time_Limit = time_Limit;
    }


    /**
     * Gets the template_Name value for this Assessment.
     * 
     * @return template_Name
     */
    public java.lang.String getTemplate_Name() {
        return template_Name;
    }


    /**
     * Sets the template_Name value for this Assessment.
     * 
     * @param template_Name
     */
    public void setTemplate_Name(java.lang.String template_Name) {
        this.template_Name = template_Name;
    }


    /**
     * Gets the when_Feedback value for this Assessment.
     * 
     * @return when_Feedback
     */
    public int getWhen_Feedback() {
        return when_Feedback;
    }


    /**
     * Sets the when_Feedback value for this Assessment.
     * 
     * @param when_Feedback
     */
    public void setWhen_Feedback(int when_Feedback) {
        this.when_Feedback = when_Feedback;
    }


    /**
     * Gets the end_Feedback value for this Assessment.
     * 
     * @return end_Feedback
     */
    public int getEnd_Feedback() {
        return end_Feedback;
    }


    /**
     * Sets the end_Feedback value for this Assessment.
     * 
     * @param end_Feedback
     */
    public void setEnd_Feedback(int end_Feedback) {
        this.end_Feedback = end_Feedback;
    }


    /**
     * Gets the exclude_Unscored value for this Assessment.
     * 
     * @return exclude_Unscored
     */
    public boolean isExclude_Unscored() {
        return exclude_Unscored;
    }


    /**
     * Sets the exclude_Unscored value for this Assessment.
     * 
     * @param exclude_Unscored
     */
    public void setExclude_Unscored(boolean exclude_Unscored) {
        this.exclude_Unscored = exclude_Unscored;
    }


    /**
     * Gets the folder_ID value for this Assessment.
     * 
     * @return folder_ID
     */
    public java.lang.Integer getFolder_ID() {
        return folder_ID;
    }


    /**
     * Sets the folder_ID value for this Assessment.
     * 
     * @param folder_ID
     */
    public void setFolder_ID(java.lang.Integer folder_ID) {
        this.folder_ID = folder_ID;
    }


    /**
     * Gets the description value for this Assessment.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this Assessment.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the monitored value for this Assessment.
     * 
     * @return monitored
     */
    public int getMonitored() {
        return monitored;
    }


    /**
     * Sets the monitored value for this Assessment.
     * 
     * @param monitored
     */
    public void setMonitored(int monitored) {
        this.monitored = monitored;
    }


    /**
     * Gets the editor value for this Assessment.
     * 
     * @return editor
     */
    public java.lang.String getEditor() {
        return editor;
    }


    /**
     * Sets the editor value for this Assessment.
     * 
     * @param editor
     */
    public void setEditor(java.lang.String editor) {
        this.editor = editor;
    }


    /**
     * Gets the version value for this Assessment.
     * 
     * @return version
     */
    public java.lang.String getVersion() {
        return version;
    }


    /**
     * Sets the version value for this Assessment.
     * 
     * @param version
     */
    public void setVersion(java.lang.String version) {
        this.version = version;
    }


    /**
     * Gets the permit_External_Call value for this Assessment.
     * 
     * @return permit_External_Call
     */
    public boolean isPermit_External_Call() {
        return permit_External_Call;
    }


    /**
     * Sets the permit_External_Call value for this Assessment.
     * 
     * @param permit_External_Call
     */
    public void setPermit_External_Call(boolean permit_External_Call) {
        this.permit_External_Call = permit_External_Call;
    }


    /**
     * Gets the created_Date value for this Assessment.
     * 
     * @return created_Date
     */
    public java.lang.String getCreated_Date() {
        return created_Date;
    }


    /**
     * Sets the created_Date value for this Assessment.
     * 
     * @param created_Date
     */
    public void setCreated_Date(java.lang.String created_Date) {
        this.created_Date = created_Date;
    }


    /**
     * Gets the modified_Date value for this Assessment.
     * 
     * @return modified_Date
     */
    public java.lang.String getModified_Date() {
        return modified_Date;
    }


    /**
     * Sets the modified_Date value for this Assessment.
     * 
     * @param modified_Date
     */
    public void setModified_Date(java.lang.String modified_Date) {
        this.modified_Date = modified_Date;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Assessment)) return false;
        Assessment other = (Assessment) obj;
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
            ((this.author==null && other.getAuthor()==null) || 
             (this.author!=null &&
              this.author.equals(other.getAuthor()))) &&
            this.save_Answers == other.isSave_Answers() &&
            this.save_Answer_Data == other.isSave_Answer_Data() &&
            this.open_Session == other.isOpen_Session() &&
            ((this.session_Password==null && other.getSession_Password()==null) || 
             (this.session_Password!=null &&
              this.session_Password.equals(other.getSession_Password()))) &&
            this.session_Timed == other.isSession_Timed() &&
            this.time_Limit == other.getTime_Limit() &&
            ((this.template_Name==null && other.getTemplate_Name()==null) || 
             (this.template_Name!=null &&
              this.template_Name.equals(other.getTemplate_Name()))) &&
            this.when_Feedback == other.getWhen_Feedback() &&
            this.end_Feedback == other.getEnd_Feedback() &&
            this.exclude_Unscored == other.isExclude_Unscored() &&
            ((this.folder_ID==null && other.getFolder_ID()==null) || 
             (this.folder_ID!=null &&
              this.folder_ID.equals(other.getFolder_ID()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            this.monitored == other.getMonitored() &&
            ((this.editor==null && other.getEditor()==null) || 
             (this.editor!=null &&
              this.editor.equals(other.getEditor()))) &&
            ((this.version==null && other.getVersion()==null) || 
             (this.version!=null &&
              this.version.equals(other.getVersion()))) &&
            this.permit_External_Call == other.isPermit_External_Call() &&
            ((this.created_Date==null && other.getCreated_Date()==null) || 
             (this.created_Date!=null &&
              this.created_Date.equals(other.getCreated_Date()))) &&
            ((this.modified_Date==null && other.getModified_Date()==null) || 
             (this.modified_Date!=null &&
              this.modified_Date.equals(other.getModified_Date())));
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
        if (getAuthor() != null) {
            _hashCode += getAuthor().hashCode();
        }
        _hashCode += (isSave_Answers() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isSave_Answer_Data() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isOpen_Session() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getSession_Password() != null) {
            _hashCode += getSession_Password().hashCode();
        }
        _hashCode += (isSession_Timed() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getTime_Limit();
        if (getTemplate_Name() != null) {
            _hashCode += getTemplate_Name().hashCode();
        }
        _hashCode += getWhen_Feedback();
        _hashCode += getEnd_Feedback();
        _hashCode += (isExclude_Unscored() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getFolder_ID() != null) {
            _hashCode += getFolder_ID().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        _hashCode += getMonitored();
        if (getEditor() != null) {
            _hashCode += getEditor().hashCode();
        }
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        _hashCode += (isPermit_External_Call() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getCreated_Date() != null) {
            _hashCode += getCreated_Date().hashCode();
        }
        if (getModified_Date() != null) {
            _hashCode += getModified_Date().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Assessment.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment"));
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
        elemField.setFieldName("open_Session");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Open_Session"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("session_Password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Session_Password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("session_Timed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Session_Timed"));
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
        elemField.setFieldName("template_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Template_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("when_Feedback");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "When_Feedback"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("end_Feedback");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "End_Feedback"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exclude_Unscored");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Exclude_Unscored"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folder_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Folder_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("monitored");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Monitored"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("editor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Editor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("permit_External_Call");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Permit_External_Call"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("created_Date");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Created_Date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modified_Date");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Modified_Date"));
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
