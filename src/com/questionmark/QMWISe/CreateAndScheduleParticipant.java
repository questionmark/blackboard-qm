/**
 * CreateAndScheduleParticipant.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class CreateAndScheduleParticipant  implements java.io.Serializable {
    private com.questionmark.QMWISe.Participant participant;

    private com.questionmark.QMWISe.Schedule[] scheduleList;

    public CreateAndScheduleParticipant() {
    }

    public CreateAndScheduleParticipant(
           com.questionmark.QMWISe.Participant participant,
           com.questionmark.QMWISe.Schedule[] scheduleList) {
           this.participant = participant;
           this.scheduleList = scheduleList;
    }


    /**
     * Gets the participant value for this CreateAndScheduleParticipant.
     * 
     * @return participant
     */
    public com.questionmark.QMWISe.Participant getParticipant() {
        return participant;
    }


    /**
     * Sets the participant value for this CreateAndScheduleParticipant.
     * 
     * @param participant
     */
    public void setParticipant(com.questionmark.QMWISe.Participant participant) {
        this.participant = participant;
    }


    /**
     * Gets the scheduleList value for this CreateAndScheduleParticipant.
     * 
     * @return scheduleList
     */
    public com.questionmark.QMWISe.Schedule[] getScheduleList() {
        return scheduleList;
    }


    /**
     * Sets the scheduleList value for this CreateAndScheduleParticipant.
     * 
     * @param scheduleList
     */
    public void setScheduleList(com.questionmark.QMWISe.Schedule[] scheduleList) {
        this.scheduleList = scheduleList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateAndScheduleParticipant)) return false;
        CreateAndScheduleParticipant other = (CreateAndScheduleParticipant) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.participant==null && other.getParticipant()==null) || 
             (this.participant!=null &&
              this.participant.equals(other.getParticipant()))) &&
            ((this.scheduleList==null && other.getScheduleList()==null) || 
             (this.scheduleList!=null &&
              java.util.Arrays.equals(this.scheduleList, other.getScheduleList())));
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
        if (getParticipant() != null) {
            _hashCode += getParticipant().hashCode();
        }
        if (getScheduleList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getScheduleList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getScheduleList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateAndScheduleParticipant.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAndScheduleParticipant"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("participant");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scheduleList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule"));
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
