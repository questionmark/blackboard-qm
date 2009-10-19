/**
 * AssessmentBlock.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class AssessmentBlock  implements java.io.Serializable {
    private java.lang.String block_Name;

    private boolean feedback;

    private boolean shuffle_Questions;

    private boolean suspend_Time_Limit;

    private boolean use_Template_File;

    private java.lang.String template_Name;

    private java.lang.String introduction_Text;

    private com.questionmark.QMWISe.Item[] itemList;

    public AssessmentBlock() {
    }

    public AssessmentBlock(
           java.lang.String block_Name,
           boolean feedback,
           boolean shuffle_Questions,
           boolean suspend_Time_Limit,
           boolean use_Template_File,
           java.lang.String template_Name,
           java.lang.String introduction_Text,
           com.questionmark.QMWISe.Item[] itemList) {
           this.block_Name = block_Name;
           this.feedback = feedback;
           this.shuffle_Questions = shuffle_Questions;
           this.suspend_Time_Limit = suspend_Time_Limit;
           this.use_Template_File = use_Template_File;
           this.template_Name = template_Name;
           this.introduction_Text = introduction_Text;
           this.itemList = itemList;
    }


    /**
     * Gets the block_Name value for this AssessmentBlock.
     * 
     * @return block_Name
     */
    public java.lang.String getBlock_Name() {
        return block_Name;
    }


    /**
     * Sets the block_Name value for this AssessmentBlock.
     * 
     * @param block_Name
     */
    public void setBlock_Name(java.lang.String block_Name) {
        this.block_Name = block_Name;
    }


    /**
     * Gets the feedback value for this AssessmentBlock.
     * 
     * @return feedback
     */
    public boolean isFeedback() {
        return feedback;
    }


    /**
     * Sets the feedback value for this AssessmentBlock.
     * 
     * @param feedback
     */
    public void setFeedback(boolean feedback) {
        this.feedback = feedback;
    }


    /**
     * Gets the shuffle_Questions value for this AssessmentBlock.
     * 
     * @return shuffle_Questions
     */
    public boolean isShuffle_Questions() {
        return shuffle_Questions;
    }


    /**
     * Sets the shuffle_Questions value for this AssessmentBlock.
     * 
     * @param shuffle_Questions
     */
    public void setShuffle_Questions(boolean shuffle_Questions) {
        this.shuffle_Questions = shuffle_Questions;
    }


    /**
     * Gets the suspend_Time_Limit value for this AssessmentBlock.
     * 
     * @return suspend_Time_Limit
     */
    public boolean isSuspend_Time_Limit() {
        return suspend_Time_Limit;
    }


    /**
     * Sets the suspend_Time_Limit value for this AssessmentBlock.
     * 
     * @param suspend_Time_Limit
     */
    public void setSuspend_Time_Limit(boolean suspend_Time_Limit) {
        this.suspend_Time_Limit = suspend_Time_Limit;
    }


    /**
     * Gets the use_Template_File value for this AssessmentBlock.
     * 
     * @return use_Template_File
     */
    public boolean isUse_Template_File() {
        return use_Template_File;
    }


    /**
     * Sets the use_Template_File value for this AssessmentBlock.
     * 
     * @param use_Template_File
     */
    public void setUse_Template_File(boolean use_Template_File) {
        this.use_Template_File = use_Template_File;
    }


    /**
     * Gets the template_Name value for this AssessmentBlock.
     * 
     * @return template_Name
     */
    public java.lang.String getTemplate_Name() {
        return template_Name;
    }


    /**
     * Sets the template_Name value for this AssessmentBlock.
     * 
     * @param template_Name
     */
    public void setTemplate_Name(java.lang.String template_Name) {
        this.template_Name = template_Name;
    }


    /**
     * Gets the introduction_Text value for this AssessmentBlock.
     * 
     * @return introduction_Text
     */
    public java.lang.String getIntroduction_Text() {
        return introduction_Text;
    }


    /**
     * Sets the introduction_Text value for this AssessmentBlock.
     * 
     * @param introduction_Text
     */
    public void setIntroduction_Text(java.lang.String introduction_Text) {
        this.introduction_Text = introduction_Text;
    }


    /**
     * Gets the itemList value for this AssessmentBlock.
     * 
     * @return itemList
     */
    public com.questionmark.QMWISe.Item[] getItemList() {
        return itemList;
    }


    /**
     * Sets the itemList value for this AssessmentBlock.
     * 
     * @param itemList
     */
    public void setItemList(com.questionmark.QMWISe.Item[] itemList) {
        this.itemList = itemList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssessmentBlock)) return false;
        AssessmentBlock other = (AssessmentBlock) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.block_Name==null && other.getBlock_Name()==null) || 
             (this.block_Name!=null &&
              this.block_Name.equals(other.getBlock_Name()))) &&
            this.feedback == other.isFeedback() &&
            this.shuffle_Questions == other.isShuffle_Questions() &&
            this.suspend_Time_Limit == other.isSuspend_Time_Limit() &&
            this.use_Template_File == other.isUse_Template_File() &&
            ((this.template_Name==null && other.getTemplate_Name()==null) || 
             (this.template_Name!=null &&
              this.template_Name.equals(other.getTemplate_Name()))) &&
            ((this.introduction_Text==null && other.getIntroduction_Text()==null) || 
             (this.introduction_Text!=null &&
              this.introduction_Text.equals(other.getIntroduction_Text()))) &&
            ((this.itemList==null && other.getItemList()==null) || 
             (this.itemList!=null &&
              java.util.Arrays.equals(this.itemList, other.getItemList())));
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
        if (getBlock_Name() != null) {
            _hashCode += getBlock_Name().hashCode();
        }
        _hashCode += (isFeedback() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isShuffle_Questions() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isSuspend_Time_Limit() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isUse_Template_File() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTemplate_Name() != null) {
            _hashCode += getTemplate_Name().hashCode();
        }
        if (getIntroduction_Text() != null) {
            _hashCode += getIntroduction_Text().hashCode();
        }
        if (getItemList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getItemList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getItemList(), i);
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
        new org.apache.axis.description.TypeDesc(AssessmentBlock.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentBlock"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("block_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Block_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("feedback");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Feedback"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shuffle_Questions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Shuffle_Questions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("suspend_Time_Limit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Suspend_Time_Limit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("use_Template_File");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Use_Template_File"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("introduction_Text");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Introduction_Text"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("itemList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ItemList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Item"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Item"));
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
