/**
 * Schedule2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class Schedule2  implements java.io.Serializable {
    private java.lang.String session_Language;

    private boolean participant_Can_Choose;

    private int select_Form;

    private java.lang.String schedule_ID;

    private java.lang.String assessment_ID;

    private java.lang.String participant_ID;

    private java.lang.String group_ID;

    private java.lang.String schedule_Name;

    private boolean restrict_Times;

    private boolean restrict_Attempts;

    private int max_Attempts;

    private int monitored;

    private java.lang.String schedule_Starts;

    private java.lang.String schedule_Stops;

    public Schedule2() {
    }

    public Schedule2(
           java.lang.String session_Language,
           boolean participant_Can_Choose,
           int select_Form,
           java.lang.String schedule_ID,
           java.lang.String assessment_ID,
           java.lang.String participant_ID,
           java.lang.String group_ID,
           java.lang.String schedule_Name,
           boolean restrict_Times,
           boolean restrict_Attempts,
           int max_Attempts,
           int monitored,
           java.lang.String schedule_Starts,
           java.lang.String schedule_Stops) {
           this.session_Language = session_Language;
           this.participant_Can_Choose = participant_Can_Choose;
           this.select_Form = select_Form;
           this.schedule_ID = schedule_ID;
           this.assessment_ID = assessment_ID;
           this.participant_ID = participant_ID;
           this.group_ID = group_ID;
           this.schedule_Name = schedule_Name;
           this.restrict_Times = restrict_Times;
           this.restrict_Attempts = restrict_Attempts;
           this.max_Attempts = max_Attempts;
           this.monitored = monitored;
           this.schedule_Starts = schedule_Starts;
           this.schedule_Stops = schedule_Stops;
    }


    /**
     * Gets the session_Language value for this Schedule2.
     * 
     * @return session_Language
     */
    public java.lang.String getSession_Language() {
        return session_Language;
    }


    /**
     * Sets the session_Language value for this Schedule2.
     * 
     * @param session_Language
     */
    public void setSession_Language(java.lang.String session_Language) {
        this.session_Language = session_Language;
    }


    /**
     * Gets the participant_Can_Choose value for this Schedule2.
     * 
     * @return participant_Can_Choose
     */
    public boolean isParticipant_Can_Choose() {
        return participant_Can_Choose;
    }


    /**
     * Sets the participant_Can_Choose value for this Schedule2.
     * 
     * @param participant_Can_Choose
     */
    public void setParticipant_Can_Choose(boolean participant_Can_Choose) {
        this.participant_Can_Choose = participant_Can_Choose;
    }


    /**
     * Gets the select_Form value for this Schedule2.
     * 
     * @return select_Form
     */
    public int getSelect_Form() {
        return select_Form;
    }


    /**
     * Sets the select_Form value for this Schedule2.
     * 
     * @param select_Form
     */
    public void setSelect_Form(int select_Form) {
        this.select_Form = select_Form;
    }


    /**
     * Gets the schedule_ID value for this Schedule2.
     * 
     * @return schedule_ID
     */
    public java.lang.String getSchedule_ID() {
        return schedule_ID;
    }


    /**
     * Sets the schedule_ID value for this Schedule2.
     * 
     * @param schedule_ID
     */
    public void setSchedule_ID(java.lang.String schedule_ID) {
        this.schedule_ID = schedule_ID;
    }


    /**
     * Gets the assessment_ID value for this Schedule2.
     * 
     * @return assessment_ID
     */
    public java.lang.String getAssessment_ID() {
        return assessment_ID;
    }


    /**
     * Sets the assessment_ID value for this Schedule2.
     * 
     * @param assessment_ID
     */
    public void setAssessment_ID(java.lang.String assessment_ID) {
        this.assessment_ID = assessment_ID;
    }


    /**
     * Gets the participant_ID value for this Schedule2.
     * 
     * @return participant_ID
     */
    public java.lang.String getParticipant_ID() {
        return participant_ID;
    }


    /**
     * Sets the participant_ID value for this Schedule2.
     * 
     * @param participant_ID
     */
    public void setParticipant_ID(java.lang.String participant_ID) {
        this.participant_ID = participant_ID;
    }


    /**
     * Gets the group_ID value for this Schedule2.
     * 
     * @return group_ID
     */
    public java.lang.String getGroup_ID() {
        return group_ID;
    }


    /**
     * Sets the group_ID value for this Schedule2.
     * 
     * @param group_ID
     */
    public void setGroup_ID(java.lang.String group_ID) {
        this.group_ID = group_ID;
    }


    /**
     * Gets the schedule_Name value for this Schedule2.
     * 
     * @return schedule_Name
     */
    public java.lang.String getSchedule_Name() {
        return schedule_Name;
    }


    /**
     * Sets the schedule_Name value for this Schedule2.
     * 
     * @param schedule_Name
     */
    public void setSchedule_Name(java.lang.String schedule_Name) {
        this.schedule_Name = schedule_Name;
    }


    /**
     * Gets the restrict_Times value for this Schedule2.
     * 
     * @return restrict_Times
     */
    public boolean isRestrict_Times() {
        return restrict_Times;
    }


    /**
     * Sets the restrict_Times value for this Schedule2.
     * 
     * @param restrict_Times
     */
    public void setRestrict_Times(boolean restrict_Times) {
        this.restrict_Times = restrict_Times;
    }


    /**
     * Gets the restrict_Attempts value for this Schedule2.
     * 
     * @return restrict_Attempts
     */
    public boolean isRestrict_Attempts() {
        return restrict_Attempts;
    }


    /**
     * Sets the restrict_Attempts value for this Schedule2.
     * 
     * @param restrict_Attempts
     */
    public void setRestrict_Attempts(boolean restrict_Attempts) {
        this.restrict_Attempts = restrict_Attempts;
    }


    /**
     * Gets the max_Attempts value for this Schedule2.
     * 
     * @return max_Attempts
     */
    public int getMax_Attempts() {
        return max_Attempts;
    }


    /**
     * Sets the max_Attempts value for this Schedule2.
     * 
     * @param max_Attempts
     */
    public void setMax_Attempts(int max_Attempts) {
        this.max_Attempts = max_Attempts;
    }


    /**
     * Gets the monitored value for this Schedule2.
     * 
     * @return monitored
     */
    public int getMonitored() {
        return monitored;
    }


    /**
     * Sets the monitored value for this Schedule2.
     * 
     * @param monitored
     */
    public void setMonitored(int monitored) {
        this.monitored = monitored;
    }


    /**
     * Gets the schedule_Starts value for this Schedule2.
     * 
     * @return schedule_Starts
     */
    public java.lang.String getSchedule_Starts() {
        return schedule_Starts;
    }


    /**
     * Sets the schedule_Starts value for this Schedule2.
     * 
     * @param schedule_Starts
     */
    public void setSchedule_Starts(java.lang.String schedule_Starts) {
        this.schedule_Starts = schedule_Starts;
    }


    /**
     * Gets the schedule_Stops value for this Schedule2.
     * 
     * @return schedule_Stops
     */
    public java.lang.String getSchedule_Stops() {
        return schedule_Stops;
    }


    /**
     * Sets the schedule_Stops value for this Schedule2.
     * 
     * @param schedule_Stops
     */
    public void setSchedule_Stops(java.lang.String schedule_Stops) {
        this.schedule_Stops = schedule_Stops;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Schedule2)) return false;
        Schedule2 other = (Schedule2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.session_Language==null && other.getSession_Language()==null) || 
             (this.session_Language!=null &&
              this.session_Language.equals(other.getSession_Language()))) &&
            this.participant_Can_Choose == other.isParticipant_Can_Choose() &&
            this.select_Form == other.getSelect_Form() &&
            ((this.schedule_ID==null && other.getSchedule_ID()==null) || 
             (this.schedule_ID!=null &&
              this.schedule_ID.equals(other.getSchedule_ID()))) &&
            ((this.assessment_ID==null && other.getAssessment_ID()==null) || 
             (this.assessment_ID!=null &&
              this.assessment_ID.equals(other.getAssessment_ID()))) &&
            ((this.participant_ID==null && other.getParticipant_ID()==null) || 
             (this.participant_ID!=null &&
              this.participant_ID.equals(other.getParticipant_ID()))) &&
            ((this.group_ID==null && other.getGroup_ID()==null) || 
             (this.group_ID!=null &&
              this.group_ID.equals(other.getGroup_ID()))) &&
            ((this.schedule_Name==null && other.getSchedule_Name()==null) || 
             (this.schedule_Name!=null &&
              this.schedule_Name.equals(other.getSchedule_Name()))) &&
            this.restrict_Times == other.isRestrict_Times() &&
            this.restrict_Attempts == other.isRestrict_Attempts() &&
            this.max_Attempts == other.getMax_Attempts() &&
            this.monitored == other.getMonitored() &&
            ((this.schedule_Starts==null && other.getSchedule_Starts()==null) || 
             (this.schedule_Starts!=null &&
              this.schedule_Starts.equals(other.getSchedule_Starts()))) &&
            ((this.schedule_Stops==null && other.getSchedule_Stops()==null) || 
             (this.schedule_Stops!=null &&
              this.schedule_Stops.equals(other.getSchedule_Stops())));
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
        if (getSession_Language() != null) {
            _hashCode += getSession_Language().hashCode();
        }
        _hashCode += (isParticipant_Can_Choose() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getSelect_Form();
        if (getSchedule_ID() != null) {
            _hashCode += getSchedule_ID().hashCode();
        }
        if (getAssessment_ID() != null) {
            _hashCode += getAssessment_ID().hashCode();
        }
        if (getParticipant_ID() != null) {
            _hashCode += getParticipant_ID().hashCode();
        }
        if (getGroup_ID() != null) {
            _hashCode += getGroup_ID().hashCode();
        }
        if (getSchedule_Name() != null) {
            _hashCode += getSchedule_Name().hashCode();
        }
        _hashCode += (isRestrict_Times() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isRestrict_Attempts() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getMax_Attempts();
        _hashCode += getMonitored();
        if (getSchedule_Starts() != null) {
            _hashCode += getSchedule_Starts().hashCode();
        }
        if (getSchedule_Stops() != null) {
            _hashCode += getSchedule_Stops().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Schedule2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("session_Language");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Session_Language"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("participant_Can_Choose");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_Can_Choose"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("select_Form");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Select_Form"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schedule_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assessment_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("participant_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("group_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schedule_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("restrict_Times");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Restrict_Times"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("restrict_Attempts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Restrict_Attempts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("max_Attempts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Max_Attempts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monitored");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Monitored"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schedule_Starts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_Starts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schedule_Stops");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_Stops"));
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
