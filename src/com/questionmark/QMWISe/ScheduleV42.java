/**
 * ScheduleV42.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScheduleV42  extends com.questionmark.QMWISe.DataEntity  implements java.io.Serializable {
    private int schedule_ID;

    private java.lang.String assessment_ID;

    private java.lang.String session_Name;

    private int participant_ID;

    private int group_ID;

    private int group_Tree_ID;

    private java.lang.String schedule_Name;

    private boolean restrict_Times;

    private java.lang.String schedule_Starts;

    private java.lang.String schedule_Stops;

    private boolean restrict_Attempts;

    private int max_Attempts;

    private int monitored;

    private int test_Center_ID;

    private int min_Days_Between_Attempts;

    private boolean time_Limit_Override;

    private int time_Limit;

    private java.lang.String participant_Name;

    private java.lang.String group_Name;

    private java.lang.String test_Center_Name;

    private boolean web_Delivery;

    private boolean offline_Delivery;

    private java.lang.String APack4Url;

    public ScheduleV42() {
    }

    public ScheduleV42(
           int schedule_ID,
           java.lang.String assessment_ID,
           java.lang.String session_Name,
           int participant_ID,
           int group_ID,
           int group_Tree_ID,
           java.lang.String schedule_Name,
           boolean restrict_Times,
           java.lang.String schedule_Starts,
           java.lang.String schedule_Stops,
           boolean restrict_Attempts,
           int max_Attempts,
           int monitored,
           int test_Center_ID,
           int min_Days_Between_Attempts,
           boolean time_Limit_Override,
           int time_Limit,
           java.lang.String participant_Name,
           java.lang.String group_Name,
           java.lang.String test_Center_Name,
           boolean web_Delivery,
           boolean offline_Delivery,
           java.lang.String APack4Url) {
        this.schedule_ID = schedule_ID;
        this.assessment_ID = assessment_ID;
        this.session_Name = session_Name;
        this.participant_ID = participant_ID;
        this.group_ID = group_ID;
        this.group_Tree_ID = group_Tree_ID;
        this.schedule_Name = schedule_Name;
        this.restrict_Times = restrict_Times;
        this.schedule_Starts = schedule_Starts;
        this.schedule_Stops = schedule_Stops;
        this.restrict_Attempts = restrict_Attempts;
        this.max_Attempts = max_Attempts;
        this.monitored = monitored;
        this.test_Center_ID = test_Center_ID;
        this.min_Days_Between_Attempts = min_Days_Between_Attempts;
        this.time_Limit_Override = time_Limit_Override;
        this.time_Limit = time_Limit;
        this.participant_Name = participant_Name;
        this.group_Name = group_Name;
        this.test_Center_Name = test_Center_Name;
        this.web_Delivery = web_Delivery;
        this.offline_Delivery = offline_Delivery;
        this.APack4Url = APack4Url;
    }


    /**
     * Gets the schedule_ID value for this ScheduleV42.
     * 
     * @return schedule_ID
     */
    public int getSchedule_ID() {
        return schedule_ID;
    }


    /**
     * Sets the schedule_ID value for this ScheduleV42.
     * 
     * @param schedule_ID
     */
    public void setSchedule_ID(int schedule_ID) {
        this.schedule_ID = schedule_ID;
    }


    /**
     * Gets the assessment_ID value for this ScheduleV42.
     * 
     * @return assessment_ID
     */
    public java.lang.String getAssessment_ID() {
        return assessment_ID;
    }


    /**
     * Sets the assessment_ID value for this ScheduleV42.
     * 
     * @param assessment_ID
     */
    public void setAssessment_ID(java.lang.String assessment_ID) {
        this.assessment_ID = assessment_ID;
    }


    /**
     * Gets the session_Name value for this ScheduleV42.
     * 
     * @return session_Name
     */
    public java.lang.String getSession_Name() {
        return session_Name;
    }


    /**
     * Sets the session_Name value for this ScheduleV42.
     * 
     * @param session_Name
     */
    public void setSession_Name(java.lang.String session_Name) {
        this.session_Name = session_Name;
    }


    /**
     * Gets the participant_ID value for this ScheduleV42.
     * 
     * @return participant_ID
     */
    public int getParticipant_ID() {
        return participant_ID;
    }


    /**
     * Sets the participant_ID value for this ScheduleV42.
     * 
     * @param participant_ID
     */
    public void setParticipant_ID(int participant_ID) {
        this.participant_ID = participant_ID;
    }


    /**
     * Gets the group_ID value for this ScheduleV42.
     * 
     * @return group_ID
     */
    public int getGroup_ID() {
        return group_ID;
    }


    /**
     * Sets the group_ID value for this ScheduleV42.
     * 
     * @param group_ID
     */
    public void setGroup_ID(int group_ID) {
        this.group_ID = group_ID;
    }


    /**
     * Gets the group_Tree_ID value for this ScheduleV42.
     * 
     * @return group_Tree_ID
     */
    public int getGroup_Tree_ID() {
        return group_Tree_ID;
    }


    /**
     * Sets the group_Tree_ID value for this ScheduleV42.
     * 
     * @param group_Tree_ID
     */
    public void setGroup_Tree_ID(int group_Tree_ID) {
        this.group_Tree_ID = group_Tree_ID;
    }


    /**
     * Gets the schedule_Name value for this ScheduleV42.
     * 
     * @return schedule_Name
     */
    public java.lang.String getSchedule_Name() {
        return schedule_Name;
    }


    /**
     * Sets the schedule_Name value for this ScheduleV42.
     * 
     * @param schedule_Name
     */
    public void setSchedule_Name(java.lang.String schedule_Name) {
        this.schedule_Name = schedule_Name;
    }


    /**
     * Gets the restrict_Times value for this ScheduleV42.
     * 
     * @return restrict_Times
     */
    public boolean isRestrict_Times() {
        return restrict_Times;
    }


    /**
     * Sets the restrict_Times value for this ScheduleV42.
     * 
     * @param restrict_Times
     */
    public void setRestrict_Times(boolean restrict_Times) {
        this.restrict_Times = restrict_Times;
    }


    /**
     * Gets the schedule_Starts value for this ScheduleV42.
     * 
     * @return schedule_Starts
     */
    public java.lang.String getSchedule_Starts() {
        return schedule_Starts;
    }

    public java.util.Calendar getSchedule_Starts_Calendar() {
    	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    	Calendar start=Calendar.getInstance();
    	try {
    		start.setTime(df.parse(schedule_Starts));
    	}
    	catch(ParseException e) {
    		start.setTime(new Date(0));
    	}    	
        return start;
    }


    /**
     * Sets the schedule_Starts value for this ScheduleV42.
     * 
     * @param schedule_Starts
     */
    public void setSchedule_Starts(java.lang.String schedule_Starts) {
        this.schedule_Starts = schedule_Starts;
    }

    public void setSchedule_Starts(java.util.Calendar schedule_Starts) {
    	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.schedule_Starts = df.format(schedule_Starts.getTime());
    }
    

    /**
     * Gets the schedule_Stops value for this ScheduleV42.
     * 
     * @return schedule_Stops
     */
    public java.lang.String getSchedule_Stops() {
        return schedule_Stops;
    }

    public java.util.Calendar getSchedule_Stops_Calendar() {
    	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    	Calendar stop=Calendar.getInstance();
    	try {
    		stop.setTime(df.parse(schedule_Stops));
    	}
    	catch(ParseException e) {
    		stop.setTime(new Date(0));
    	}
        return stop;
    }


    /**
     * Sets the schedule_Stops value for this ScheduleV42.
     * 
     * @param schedule_Stops
     */
    public void setSchedule_Stops(java.lang.String schedule_Stops) {
        this.schedule_Stops = schedule_Stops;
    }

    public void setSchedule_Stops(java.util.Calendar schedule_Stops) {
    	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.schedule_Stops = df.format(schedule_Stops.getTime());
    }
    

   /**
     * Gets the restrict_Attempts value for this ScheduleV42.
     * 
     * @return restrict_Attempts
     */
    public boolean isRestrict_Attempts() {
        return restrict_Attempts;
    }


    /**
     * Sets the restrict_Attempts value for this ScheduleV42.
     * 
     * @param restrict_Attempts
     */
    public void setRestrict_Attempts(boolean restrict_Attempts) {
        this.restrict_Attempts = restrict_Attempts;
    }


    /**
     * Gets the max_Attempts value for this ScheduleV42.
     * 
     * @return max_Attempts
     */
    public int getMax_Attempts() {
        return max_Attempts;
    }


    /**
     * Sets the max_Attempts value for this ScheduleV42.
     * 
     * @param max_Attempts
     */
    public void setMax_Attempts(int max_Attempts) {
        this.max_Attempts = max_Attempts;
    }


    /**
     * Gets the monitored value for this ScheduleV42.
     * 
     * @return monitored
     */
    public int getMonitored() {
        return monitored;
    }


    /**
     * Sets the monitored value for this ScheduleV42.
     * 
     * @param monitored
     */
    public void setMonitored(int monitored) {
        this.monitored = monitored;
    }


    /**
     * Gets the test_Center_ID value for this ScheduleV42.
     * 
     * @return test_Center_ID
     */
    public int getTest_Center_ID() {
        return test_Center_ID;
    }


    /**
     * Sets the test_Center_ID value for this ScheduleV42.
     * 
     * @param test_Center_ID
     */
    public void setTest_Center_ID(int test_Center_ID) {
        this.test_Center_ID = test_Center_ID;
    }


    /**
     * Gets the min_Days_Between_Attempts value for this ScheduleV42.
     * 
     * @return min_Days_Between_Attempts
     */
    public int getMin_Days_Between_Attempts() {
        return min_Days_Between_Attempts;
    }


    /**
     * Sets the min_Days_Between_Attempts value for this ScheduleV42.
     * 
     * @param min_Days_Between_Attempts
     */
    public void setMin_Days_Between_Attempts(int min_Days_Between_Attempts) {
        this.min_Days_Between_Attempts = min_Days_Between_Attempts;
    }


    /**
     * Gets the time_Limit_Override value for this ScheduleV42.
     * 
     * @return time_Limit_Override
     */
    public boolean isTime_Limit_Override() {
        return time_Limit_Override;
    }


    /**
     * Sets the time_Limit_Override value for this ScheduleV42.
     * 
     * @param time_Limit_Override
     */
    public void setTime_Limit_Override(boolean time_Limit_Override) {
        this.time_Limit_Override = time_Limit_Override;
    }


    /**
     * Gets the time_Limit value for this ScheduleV42.
     * 
     * @return time_Limit
     */
    public int getTime_Limit() {
        return time_Limit;
    }


    /**
     * Sets the time_Limit value for this ScheduleV42.
     * 
     * @param time_Limit
     */
    public void setTime_Limit(int time_Limit) {
        this.time_Limit = time_Limit;
    }


    /**
     * Gets the participant_Name value for this ScheduleV42.
     * 
     * @return participant_Name
     */
    public java.lang.String getParticipant_Name() {
        return participant_Name;
    }


    /**
     * Sets the participant_Name value for this ScheduleV42.
     * 
     * @param participant_Name
     */
    public void setParticipant_Name(java.lang.String participant_Name) {
        this.participant_Name = participant_Name;
    }


    /**
     * Gets the group_Name value for this ScheduleV42.
     * 
     * @return group_Name
     */
    public java.lang.String getGroup_Name() {
        return group_Name;
    }


    /**
     * Sets the group_Name value for this ScheduleV42.
     * 
     * @param group_Name
     */
    public void setGroup_Name(java.lang.String group_Name) {
        this.group_Name = group_Name;
    }


    /**
     * Gets the test_Center_Name value for this ScheduleV42.
     * 
     * @return test_Center_Name
     */
    public java.lang.String getTest_Center_Name() {
        return test_Center_Name;
    }


    /**
     * Sets the test_Center_Name value for this ScheduleV42.
     * 
     * @param test_Center_Name
     */
    public void setTest_Center_Name(java.lang.String test_Center_Name) {
        this.test_Center_Name = test_Center_Name;
    }


    /**
     * Gets the web_Delivery value for this ScheduleV42.
     * 
     * @return web_Delivery
     */
    public boolean isWeb_Delivery() {
        return web_Delivery;
    }


    /**
     * Sets the web_Delivery value for this ScheduleV42.
     * 
     * @param web_Delivery
     */
    public void setWeb_Delivery(boolean web_Delivery) {
        this.web_Delivery = web_Delivery;
    }


    /**
     * Gets the offline_Delivery value for this ScheduleV42.
     * 
     * @return offline_Delivery
     */
    public boolean isOffline_Delivery() {
        return offline_Delivery;
    }


    /**
     * Sets the offline_Delivery value for this ScheduleV42.
     * 
     * @param offline_Delivery
     */
    public void setOffline_Delivery(boolean offline_Delivery) {
        this.offline_Delivery = offline_Delivery;
    }


    /**
     * Gets the APack4Url value for this ScheduleV42.
     * 
     * @return APack4Url
     */
    public java.lang.String getAPack4Url() {
        return APack4Url;
    }


    /**
     * Sets the APack4Url value for this ScheduleV42.
     * 
     * @param APack4Url
     */
    public void setAPack4Url(java.lang.String APack4Url) {
        this.APack4Url = APack4Url;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ScheduleV42)) return false;
        ScheduleV42 other = (ScheduleV42) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.schedule_ID == other.getSchedule_ID() &&
            ((this.assessment_ID==null && other.getAssessment_ID()==null) || 
             (this.assessment_ID!=null &&
              this.assessment_ID.equals(other.getAssessment_ID()))) &&
            ((this.session_Name==null && other.getSession_Name()==null) || 
             (this.session_Name!=null &&
              this.session_Name.equals(other.getSession_Name()))) &&
            this.participant_ID == other.getParticipant_ID() &&
            this.group_ID == other.getGroup_ID() &&
            this.group_Tree_ID == other.getGroup_Tree_ID() &&
            ((this.schedule_Name==null && other.getSchedule_Name()==null) || 
             (this.schedule_Name!=null &&
              this.schedule_Name.equals(other.getSchedule_Name()))) &&
            this.restrict_Times == other.isRestrict_Times() &&
            ((this.schedule_Starts==null && other.getSchedule_Starts()==null) || 
             (this.schedule_Starts!=null &&
              this.schedule_Starts.equals(other.getSchedule_Starts()))) &&
            ((this.schedule_Stops==null && other.getSchedule_Stops()==null) || 
             (this.schedule_Stops!=null &&
              this.schedule_Stops.equals(other.getSchedule_Stops()))) &&
            this.restrict_Attempts == other.isRestrict_Attempts() &&
            this.max_Attempts == other.getMax_Attempts() &&
            this.monitored == other.getMonitored() &&
            this.test_Center_ID == other.getTest_Center_ID() &&
            this.min_Days_Between_Attempts == other.getMin_Days_Between_Attempts() &&
            this.time_Limit_Override == other.isTime_Limit_Override() &&
            this.time_Limit == other.getTime_Limit() &&
            ((this.participant_Name==null && other.getParticipant_Name()==null) || 
             (this.participant_Name!=null &&
              this.participant_Name.equals(other.getParticipant_Name()))) &&
            ((this.group_Name==null && other.getGroup_Name()==null) || 
             (this.group_Name!=null &&
              this.group_Name.equals(other.getGroup_Name()))) &&
            ((this.test_Center_Name==null && other.getTest_Center_Name()==null) || 
             (this.test_Center_Name!=null &&
              this.test_Center_Name.equals(other.getTest_Center_Name()))) &&
            this.web_Delivery == other.isWeb_Delivery() &&
            this.offline_Delivery == other.isOffline_Delivery() &&
            ((this.APack4Url==null && other.getAPack4Url()==null) || 
             (this.APack4Url!=null &&
              this.APack4Url.equals(other.getAPack4Url())));
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
        _hashCode += getSchedule_ID();
        if (getAssessment_ID() != null) {
            _hashCode += getAssessment_ID().hashCode();
        }
        if (getSession_Name() != null) {
            _hashCode += getSession_Name().hashCode();
        }
        _hashCode += getParticipant_ID();
        _hashCode += getGroup_ID();
        _hashCode += getGroup_Tree_ID();
        if (getSchedule_Name() != null) {
            _hashCode += getSchedule_Name().hashCode();
        }
        _hashCode += (isRestrict_Times() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getSchedule_Starts() != null) {
            _hashCode += getSchedule_Starts().hashCode();
        }
        if (getSchedule_Stops() != null) {
            _hashCode += getSchedule_Stops().hashCode();
        }
        _hashCode += (isRestrict_Attempts() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getMax_Attempts();
        _hashCode += getMonitored();
        _hashCode += getTest_Center_ID();
        _hashCode += getMin_Days_Between_Attempts();
        _hashCode += (isTime_Limit_Override() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getTime_Limit();
        if (getParticipant_Name() != null) {
            _hashCode += getParticipant_Name().hashCode();
        }
        if (getGroup_Name() != null) {
            _hashCode += getGroup_Name().hashCode();
        }
        if (getTest_Center_Name() != null) {
            _hashCode += getTest_Center_Name().hashCode();
        }
        _hashCode += (isWeb_Delivery() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isOffline_Delivery() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getAPack4Url() != null) {
            _hashCode += getAPack4Url().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ScheduleV42.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleV42"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schedule_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("session_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Session_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("participant_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("group_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("group_Tree_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_Tree_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("schedule_Starts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_Starts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schedule_Stops");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_Stops"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("test_Center_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Test_Center_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("min_Days_Between_Attempts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Min_Days_Between_Attempts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("time_Limit_Override");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Time_Limit_Override"));
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
        elemField.setFieldName("participant_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_Name"));
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
        elemField.setFieldName("test_Center_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Test_Center_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("web_Delivery");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Web_Delivery"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("offline_Delivery");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Offline_Delivery"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("APack4Url");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "APack4Url"));
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
