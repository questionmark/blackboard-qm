/**
 * Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class Response  implements java.io.Serializable {
    private com.questionmark.QMWISe.Choice[] choice;

    private java.lang.String comment;

    private java.lang.String id;  // attribute

    private java.lang.String choice_type;  // attribute

    private java.lang.String choice_max;  // attribute

    private java.lang.String choice_num;  // attribute

    public Response() {
    }

    public Response(
           com.questionmark.QMWISe.Choice[] choice,
           java.lang.String comment,
           java.lang.String id,
           java.lang.String choice_type,
           java.lang.String choice_max,
           java.lang.String choice_num) {
           this.choice = choice;
           this.comment = comment;
           this.id = id;
           this.choice_type = choice_type;
           this.choice_max = choice_max;
           this.choice_num = choice_num;
    }


    /**
     * Gets the choice value for this Response.
     * 
     * @return choice
     */
    public com.questionmark.QMWISe.Choice[] getChoice() {
        return choice;
    }


    /**
     * Sets the choice value for this Response.
     * 
     * @param choice
     */
    public void setChoice(com.questionmark.QMWISe.Choice[] choice) {
        this.choice = choice;
    }

    public com.questionmark.QMWISe.Choice getChoice(int i) {
        return this.choice[i];
    }

    public void setChoice(int i, com.questionmark.QMWISe.Choice _value) {
        this.choice[i] = _value;
    }


    /**
     * Gets the comment value for this Response.
     * 
     * @return comment
     */
    public java.lang.String getComment() {
        return comment;
    }


    /**
     * Sets the comment value for this Response.
     * 
     * @param comment
     */
    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }


    /**
     * Gets the id value for this Response.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this Response.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the choice_type value for this Response.
     * 
     * @return choice_type
     */
    public java.lang.String getChoice_type() {
        return choice_type;
    }


    /**
     * Sets the choice_type value for this Response.
     * 
     * @param choice_type
     */
    public void setChoice_type(java.lang.String choice_type) {
        this.choice_type = choice_type;
    }


    /**
     * Gets the choice_max value for this Response.
     * 
     * @return choice_max
     */
    public java.lang.String getChoice_max() {
        return choice_max;
    }


    /**
     * Sets the choice_max value for this Response.
     * 
     * @param choice_max
     */
    public void setChoice_max(java.lang.String choice_max) {
        this.choice_max = choice_max;
    }


    /**
     * Gets the choice_num value for this Response.
     * 
     * @return choice_num
     */
    public java.lang.String getChoice_num() {
        return choice_num;
    }


    /**
     * Sets the choice_num value for this Response.
     * 
     * @param choice_num
     */
    public void setChoice_num(java.lang.String choice_num) {
        this.choice_num = choice_num;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Response)) return false;
        Response other = (Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.choice==null && other.getChoice()==null) || 
             (this.choice!=null &&
              java.util.Arrays.equals(this.choice, other.getChoice()))) &&
            ((this.comment==null && other.getComment()==null) || 
             (this.comment!=null &&
              this.comment.equals(other.getComment()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.choice_type==null && other.getChoice_type()==null) || 
             (this.choice_type!=null &&
              this.choice_type.equals(other.getChoice_type()))) &&
            ((this.choice_max==null && other.getChoice_max()==null) || 
             (this.choice_max!=null &&
              this.choice_max.equals(other.getChoice_max()))) &&
            ((this.choice_num==null && other.getChoice_num()==null) || 
             (this.choice_num!=null &&
              this.choice_num.equals(other.getChoice_num())));
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
        if (getChoice() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getChoice());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getChoice(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComment() != null) {
            _hashCode += getComment().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getChoice_type() != null) {
            _hashCode += getChoice_type().hashCode();
        }
        if (getChoice_max() != null) {
            _hashCode += getChoice_max().hashCode();
        }
        if (getChoice_num() != null) {
            _hashCode += getChoice_num().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Response"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("id");
        attrField.setXmlName(new javax.xml.namespace.QName("", "id"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("choice_type");
        attrField.setXmlName(new javax.xml.namespace.QName("", "choice_type"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("choice_max");
        attrField.setXmlName(new javax.xml.namespace.QName("", "choice_max"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("choice_num");
        attrField.setXmlName(new javax.xml.namespace.QName("", "choice_num"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("choice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Choice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Choice"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
