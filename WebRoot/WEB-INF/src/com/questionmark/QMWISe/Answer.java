/**
 * Answer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class Answer  implements java.io.Serializable {
    private java.lang.String question_ID;

    private int revision;

    private short occurrence;

    private java.lang.String topic_Name;

    private short block_Number;

    private short question_Number;

    private short status;

    private short times_Answered;

    private int max_Score;

    private int actual_Score;

    private boolean know_Time_Taken;

    private int time_Taken;

    private short number_Outcomes;

    private short outcome_Number;

    private int outcome_Exponential;

    private java.lang.String answer_Truncated;

    private java.lang.String answer_Full;

    private java.lang.String comment;

    public Answer() {
    }

    public Answer(
           java.lang.String question_ID,
           int revision,
           short occurrence,
           java.lang.String topic_Name,
           short block_Number,
           short question_Number,
           short status,
           short times_Answered,
           int max_Score,
           int actual_Score,
           boolean know_Time_Taken,
           int time_Taken,
           short number_Outcomes,
           short outcome_Number,
           int outcome_Exponential,
           java.lang.String answer_Truncated,
           java.lang.String answer_Full,
           java.lang.String comment) {
           this.question_ID = question_ID;
           this.revision = revision;
           this.occurrence = occurrence;
           this.topic_Name = topic_Name;
           this.block_Number = block_Number;
           this.question_Number = question_Number;
           this.status = status;
           this.times_Answered = times_Answered;
           this.max_Score = max_Score;
           this.actual_Score = actual_Score;
           this.know_Time_Taken = know_Time_Taken;
           this.time_Taken = time_Taken;
           this.number_Outcomes = number_Outcomes;
           this.outcome_Number = outcome_Number;
           this.outcome_Exponential = outcome_Exponential;
           this.answer_Truncated = answer_Truncated;
           this.answer_Full = answer_Full;
           this.comment = comment;
    }


    /**
     * Gets the question_ID value for this Answer.
     * 
     * @return question_ID
     */
    public java.lang.String getQuestion_ID() {
        return question_ID;
    }


    /**
     * Sets the question_ID value for this Answer.
     * 
     * @param question_ID
     */
    public void setQuestion_ID(java.lang.String question_ID) {
        this.question_ID = question_ID;
    }


    /**
     * Gets the revision value for this Answer.
     * 
     * @return revision
     */
    public int getRevision() {
        return revision;
    }


    /**
     * Sets the revision value for this Answer.
     * 
     * @param revision
     */
    public void setRevision(int revision) {
        this.revision = revision;
    }


    /**
     * Gets the occurrence value for this Answer.
     * 
     * @return occurrence
     */
    public short getOccurrence() {
        return occurrence;
    }


    /**
     * Sets the occurrence value for this Answer.
     * 
     * @param occurrence
     */
    public void setOccurrence(short occurrence) {
        this.occurrence = occurrence;
    }


    /**
     * Gets the topic_Name value for this Answer.
     * 
     * @return topic_Name
     */
    public java.lang.String getTopic_Name() {
        return topic_Name;
    }


    /**
     * Sets the topic_Name value for this Answer.
     * 
     * @param topic_Name
     */
    public void setTopic_Name(java.lang.String topic_Name) {
        this.topic_Name = topic_Name;
    }


    /**
     * Gets the block_Number value for this Answer.
     * 
     * @return block_Number
     */
    public short getBlock_Number() {
        return block_Number;
    }


    /**
     * Sets the block_Number value for this Answer.
     * 
     * @param block_Number
     */
    public void setBlock_Number(short block_Number) {
        this.block_Number = block_Number;
    }


    /**
     * Gets the question_Number value for this Answer.
     * 
     * @return question_Number
     */
    public short getQuestion_Number() {
        return question_Number;
    }


    /**
     * Sets the question_Number value for this Answer.
     * 
     * @param question_Number
     */
    public void setQuestion_Number(short question_Number) {
        this.question_Number = question_Number;
    }


    /**
     * Gets the status value for this Answer.
     * 
     * @return status
     */
    public short getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Answer.
     * 
     * @param status
     */
    public void setStatus(short status) {
        this.status = status;
    }


    /**
     * Gets the times_Answered value for this Answer.
     * 
     * @return times_Answered
     */
    public short getTimes_Answered() {
        return times_Answered;
    }


    /**
     * Sets the times_Answered value for this Answer.
     * 
     * @param times_Answered
     */
    public void setTimes_Answered(short times_Answered) {
        this.times_Answered = times_Answered;
    }


    /**
     * Gets the max_Score value for this Answer.
     * 
     * @return max_Score
     */
    public int getMax_Score() {
        return max_Score;
    }


    /**
     * Sets the max_Score value for this Answer.
     * 
     * @param max_Score
     */
    public void setMax_Score(int max_Score) {
        this.max_Score = max_Score;
    }


    /**
     * Gets the actual_Score value for this Answer.
     * 
     * @return actual_Score
     */
    public int getActual_Score() {
        return actual_Score;
    }


    /**
     * Sets the actual_Score value for this Answer.
     * 
     * @param actual_Score
     */
    public void setActual_Score(int actual_Score) {
        this.actual_Score = actual_Score;
    }


    /**
     * Gets the know_Time_Taken value for this Answer.
     * 
     * @return know_Time_Taken
     */
    public boolean isKnow_Time_Taken() {
        return know_Time_Taken;
    }


    /**
     * Sets the know_Time_Taken value for this Answer.
     * 
     * @param know_Time_Taken
     */
    public void setKnow_Time_Taken(boolean know_Time_Taken) {
        this.know_Time_Taken = know_Time_Taken;
    }


    /**
     * Gets the time_Taken value for this Answer.
     * 
     * @return time_Taken
     */
    public int getTime_Taken() {
        return time_Taken;
    }


    /**
     * Sets the time_Taken value for this Answer.
     * 
     * @param time_Taken
     */
    public void setTime_Taken(int time_Taken) {
        this.time_Taken = time_Taken;
    }


    /**
     * Gets the number_Outcomes value for this Answer.
     * 
     * @return number_Outcomes
     */
    public short getNumber_Outcomes() {
        return number_Outcomes;
    }


    /**
     * Sets the number_Outcomes value for this Answer.
     * 
     * @param number_Outcomes
     */
    public void setNumber_Outcomes(short number_Outcomes) {
        this.number_Outcomes = number_Outcomes;
    }


    /**
     * Gets the outcome_Number value for this Answer.
     * 
     * @return outcome_Number
     */
    public short getOutcome_Number() {
        return outcome_Number;
    }


    /**
     * Sets the outcome_Number value for this Answer.
     * 
     * @param outcome_Number
     */
    public void setOutcome_Number(short outcome_Number) {
        this.outcome_Number = outcome_Number;
    }


    /**
     * Gets the outcome_Exponential value for this Answer.
     * 
     * @return outcome_Exponential
     */
    public int getOutcome_Exponential() {
        return outcome_Exponential;
    }


    /**
     * Sets the outcome_Exponential value for this Answer.
     * 
     * @param outcome_Exponential
     */
    public void setOutcome_Exponential(int outcome_Exponential) {
        this.outcome_Exponential = outcome_Exponential;
    }


    /**
     * Gets the answer_Truncated value for this Answer.
     * 
     * @return answer_Truncated
     */
    public java.lang.String getAnswer_Truncated() {
        return answer_Truncated;
    }


    /**
     * Sets the answer_Truncated value for this Answer.
     * 
     * @param answer_Truncated
     */
    public void setAnswer_Truncated(java.lang.String answer_Truncated) {
        this.answer_Truncated = answer_Truncated;
    }


    /**
     * Gets the answer_Full value for this Answer.
     * 
     * @return answer_Full
     */
    public java.lang.String getAnswer_Full() {
        return answer_Full;
    }


    /**
     * Sets the answer_Full value for this Answer.
     * 
     * @param answer_Full
     */
    public void setAnswer_Full(java.lang.String answer_Full) {
        this.answer_Full = answer_Full;
    }


    /**
     * Gets the comment value for this Answer.
     * 
     * @return comment
     */
    public java.lang.String getComment() {
        return comment;
    }


    /**
     * Sets the comment value for this Answer.
     * 
     * @param comment
     */
    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Answer)) return false;
        Answer other = (Answer) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.question_ID==null && other.getQuestion_ID()==null) || 
             (this.question_ID!=null &&
              this.question_ID.equals(other.getQuestion_ID()))) &&
            this.revision == other.getRevision() &&
            this.occurrence == other.getOccurrence() &&
            ((this.topic_Name==null && other.getTopic_Name()==null) || 
             (this.topic_Name!=null &&
              this.topic_Name.equals(other.getTopic_Name()))) &&
            this.block_Number == other.getBlock_Number() &&
            this.question_Number == other.getQuestion_Number() &&
            this.status == other.getStatus() &&
            this.times_Answered == other.getTimes_Answered() &&
            this.max_Score == other.getMax_Score() &&
            this.actual_Score == other.getActual_Score() &&
            this.know_Time_Taken == other.isKnow_Time_Taken() &&
            this.time_Taken == other.getTime_Taken() &&
            this.number_Outcomes == other.getNumber_Outcomes() &&
            this.outcome_Number == other.getOutcome_Number() &&
            this.outcome_Exponential == other.getOutcome_Exponential() &&
            ((this.answer_Truncated==null && other.getAnswer_Truncated()==null) || 
             (this.answer_Truncated!=null &&
              this.answer_Truncated.equals(other.getAnswer_Truncated()))) &&
            ((this.answer_Full==null && other.getAnswer_Full()==null) || 
             (this.answer_Full!=null &&
              this.answer_Full.equals(other.getAnswer_Full()))) &&
            ((this.comment==null && other.getComment()==null) || 
             (this.comment!=null &&
              this.comment.equals(other.getComment())));
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
        if (getQuestion_ID() != null) {
            _hashCode += getQuestion_ID().hashCode();
        }
        _hashCode += getRevision();
        _hashCode += getOccurrence();
        if (getTopic_Name() != null) {
            _hashCode += getTopic_Name().hashCode();
        }
        _hashCode += getBlock_Number();
        _hashCode += getQuestion_Number();
        _hashCode += getStatus();
        _hashCode += getTimes_Answered();
        _hashCode += getMax_Score();
        _hashCode += getActual_Score();
        _hashCode += (isKnow_Time_Taken() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getTime_Taken();
        _hashCode += getNumber_Outcomes();
        _hashCode += getOutcome_Number();
        _hashCode += getOutcome_Exponential();
        if (getAnswer_Truncated() != null) {
            _hashCode += getAnswer_Truncated().hashCode();
        }
        if (getAnswer_Full() != null) {
            _hashCode += getAnswer_Full().hashCode();
        }
        if (getComment() != null) {
            _hashCode += getComment().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Answer.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Answer"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("question_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Question_ID"));
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
        elemField.setFieldName("occurrence");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Occurrence"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("topic_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Topic_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("block_Number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Block_Number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("question_Number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Question_Number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("times_Answered");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Times_Answered"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("max_Score");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Max_Score"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actual_Score");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Actual_Score"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("know_Time_Taken");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Know_Time_Taken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("time_Taken");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Time_Taken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number_Outcomes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Number_Outcomes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outcome_Number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Outcome_Number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outcome_Exponential");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Outcome_Exponential"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("answer_Truncated");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Answer_Truncated"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("answer_Full");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Answer_Full"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Comment"));
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
