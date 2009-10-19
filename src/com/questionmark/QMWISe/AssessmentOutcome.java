/**
 * AssessmentOutcome.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class AssessmentOutcome  implements java.io.Serializable {
    private java.lang.String outcome_Name;

    private boolean session_Score;

    private boolean topic_Scores;

    private boolean topic_Feedback;

    private int branch;

    private java.lang.String assessment_ID;

    private java.lang.String destination;

    private java.lang.String message;

    private int min_Percent;

    private int max_Percent;

    public AssessmentOutcome() {
    }

    public AssessmentOutcome(
           java.lang.String outcome_Name,
           boolean session_Score,
           boolean topic_Scores,
           boolean topic_Feedback,
           int branch,
           java.lang.String assessment_ID,
           java.lang.String destination,
           java.lang.String message,
           int min_Percent,
           int max_Percent) {
           this.outcome_Name = outcome_Name;
           this.session_Score = session_Score;
           this.topic_Scores = topic_Scores;
           this.topic_Feedback = topic_Feedback;
           this.branch = branch;
           this.assessment_ID = assessment_ID;
           this.destination = destination;
           this.message = message;
           this.min_Percent = min_Percent;
           this.max_Percent = max_Percent;
    }


    /**
     * Gets the outcome_Name value for this AssessmentOutcome.
     * 
     * @return outcome_Name
     */
    public java.lang.String getOutcome_Name() {
        return outcome_Name;
    }


    /**
     * Sets the outcome_Name value for this AssessmentOutcome.
     * 
     * @param outcome_Name
     */
    public void setOutcome_Name(java.lang.String outcome_Name) {
        this.outcome_Name = outcome_Name;
    }


    /**
     * Gets the session_Score value for this AssessmentOutcome.
     * 
     * @return session_Score
     */
    public boolean isSession_Score() {
        return session_Score;
    }


    /**
     * Sets the session_Score value for this AssessmentOutcome.
     * 
     * @param session_Score
     */
    public void setSession_Score(boolean session_Score) {
        this.session_Score = session_Score;
    }


    /**
     * Gets the topic_Scores value for this AssessmentOutcome.
     * 
     * @return topic_Scores
     */
    public boolean isTopic_Scores() {
        return topic_Scores;
    }


    /**
     * Sets the topic_Scores value for this AssessmentOutcome.
     * 
     * @param topic_Scores
     */
    public void setTopic_Scores(boolean topic_Scores) {
        this.topic_Scores = topic_Scores;
    }


    /**
     * Gets the topic_Feedback value for this AssessmentOutcome.
     * 
     * @return topic_Feedback
     */
    public boolean isTopic_Feedback() {
        return topic_Feedback;
    }


    /**
     * Sets the topic_Feedback value for this AssessmentOutcome.
     * 
     * @param topic_Feedback
     */
    public void setTopic_Feedback(boolean topic_Feedback) {
        this.topic_Feedback = topic_Feedback;
    }


    /**
     * Gets the branch value for this AssessmentOutcome.
     * 
     * @return branch
     */
    public int getBranch() {
        return branch;
    }


    /**
     * Sets the branch value for this AssessmentOutcome.
     * 
     * @param branch
     */
    public void setBranch(int branch) {
        this.branch = branch;
    }


    /**
     * Gets the assessment_ID value for this AssessmentOutcome.
     * 
     * @return assessment_ID
     */
    public java.lang.String getAssessment_ID() {
        return assessment_ID;
    }


    /**
     * Sets the assessment_ID value for this AssessmentOutcome.
     * 
     * @param assessment_ID
     */
    public void setAssessment_ID(java.lang.String assessment_ID) {
        this.assessment_ID = assessment_ID;
    }


    /**
     * Gets the destination value for this AssessmentOutcome.
     * 
     * @return destination
     */
    public java.lang.String getDestination() {
        return destination;
    }


    /**
     * Sets the destination value for this AssessmentOutcome.
     * 
     * @param destination
     */
    public void setDestination(java.lang.String destination) {
        this.destination = destination;
    }


    /**
     * Gets the message value for this AssessmentOutcome.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this AssessmentOutcome.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the min_Percent value for this AssessmentOutcome.
     * 
     * @return min_Percent
     */
    public int getMin_Percent() {
        return min_Percent;
    }


    /**
     * Sets the min_Percent value for this AssessmentOutcome.
     * 
     * @param min_Percent
     */
    public void setMin_Percent(int min_Percent) {
        this.min_Percent = min_Percent;
    }


    /**
     * Gets the max_Percent value for this AssessmentOutcome.
     * 
     * @return max_Percent
     */
    public int getMax_Percent() {
        return max_Percent;
    }


    /**
     * Sets the max_Percent value for this AssessmentOutcome.
     * 
     * @param max_Percent
     */
    public void setMax_Percent(int max_Percent) {
        this.max_Percent = max_Percent;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssessmentOutcome)) return false;
        AssessmentOutcome other = (AssessmentOutcome) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.outcome_Name==null && other.getOutcome_Name()==null) || 
             (this.outcome_Name!=null &&
              this.outcome_Name.equals(other.getOutcome_Name()))) &&
            this.session_Score == other.isSession_Score() &&
            this.topic_Scores == other.isTopic_Scores() &&
            this.topic_Feedback == other.isTopic_Feedback() &&
            this.branch == other.getBranch() &&
            ((this.assessment_ID==null && other.getAssessment_ID()==null) || 
             (this.assessment_ID!=null &&
              this.assessment_ID.equals(other.getAssessment_ID()))) &&
            ((this.destination==null && other.getDestination()==null) || 
             (this.destination!=null &&
              this.destination.equals(other.getDestination()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            this.min_Percent == other.getMin_Percent() &&
            this.max_Percent == other.getMax_Percent();
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
        if (getOutcome_Name() != null) {
            _hashCode += getOutcome_Name().hashCode();
        }
        _hashCode += (isSession_Score() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isTopic_Scores() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isTopic_Feedback() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getBranch();
        if (getAssessment_ID() != null) {
            _hashCode += getAssessment_ID().hashCode();
        }
        if (getDestination() != null) {
            _hashCode += getDestination().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        _hashCode += getMin_Percent();
        _hashCode += getMax_Percent();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssessmentOutcome.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentOutcome"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outcome_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Outcome_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("session_Score");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Session_Score"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("topic_Scores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Topic_Scores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("topic_Feedback");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Topic_Feedback"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("branch");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Branch"));
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
        elemField.setFieldName("destination");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Destination"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("min_Percent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Min_Percent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("max_Percent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Max_Percent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
