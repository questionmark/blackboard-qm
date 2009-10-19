/**
 * QMWISeSoapStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class QMWISeSoapStub extends org.apache.axis.client.Stub implements com.questionmark.QMWISe.QMWISeSoap {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[106];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
        _initOperationDesc5();
        _initOperationDesc6();
        _initOperationDesc7();
        _initOperationDesc8();
        _initOperationDesc9();
        _initOperationDesc10();
        _initOperationDesc11();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAbout");
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Version"));
        oper.setReturnClass(com.questionmark.QMWISe.Version.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAboutResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAbout2");
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Version2"));
        oper.setReturnClass(com.questionmark.QMWISe.Version2.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAbout2Result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetConfiguration");
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ConfigurationList"));
        oper.setReturnClass(com.questionmark.QMWISe.Configuration[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetConfigurationResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Configuration"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SetConfiguration");
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ConfigurationList"));
        oper.setReturnClass(com.questionmark.QMWISe.Configuration[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "SetConfigurationResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Configuration"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetParticipant");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant"));
        oper.setReturnClass(com.questionmark.QMWISe.Participant.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetParticipantByName");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_Name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant"));
        oper.setReturnClass(com.questionmark.QMWISe.Participant.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetParticipantList");
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ParticipantList"));
        oper.setReturnClass(com.questionmark.QMWISe.Participant[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ParticipantList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetParticipantListByGroup");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ParticipantList"));
        oper.setReturnClass(com.questionmark.QMWISe.Participant[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ParticipantList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetParticipantGroupList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GroupList"));
        oper.setReturnClass(com.questionmark.QMWISe.Group[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GroupList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CheckParticipant");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_Name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Status"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_ID"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateParticipant");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant"), com.questionmark.QMWISe.Participant.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_ID"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SetParticipant");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant"), com.questionmark.QMWISe.Participant.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("DeleteParticipant");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetGroup");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group"));
        oper.setReturnClass(com.questionmark.QMWISe.Group.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetGroupByName");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_Name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group"));
        oper.setReturnClass(com.questionmark.QMWISe.Group.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetGroupList");
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GroupList"));
        oper.setReturnClass(com.questionmark.QMWISe.Group[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GroupList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateGroup");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group"), com.questionmark.QMWISe.Group.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_ID"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SetGroup");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group"), com.questionmark.QMWISe.Group.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("DeleteGroup");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("AddGroupParticipantList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ParticipantIDList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ParticipantIDList"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_ID"));
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("DeleteGroupParticipantList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ParticipantIDList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ParticipantIDList"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_ID"));
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("AddGroupAdministratorList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AdministratorIDList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AdministratorIDList"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_ID"));
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("DeleteGroupAdministratorList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AdministratorIDList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AdministratorIDList"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_ID"));
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetQuestionPresentation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Question_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetQuestionPresentationResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment"));
        oper.setReturnClass(com.questionmark.QMWISe.Assessment.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessmentList");
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentList"));
        oper.setReturnClass(com.questionmark.QMWISe.Assessment[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessmentListByGroup");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentList"));
        oper.setReturnClass(com.questionmark.QMWISe.Assessment[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessmentListByAdministrator");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentList"));
        oper.setReturnClass(com.questionmark.QMWISe.Assessment[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessmentReportedTopics");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "TopicList"));
        oper.setReturnClass(com.questionmark.QMWISe.Topic[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "TopicList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Topic"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessmentDefinition");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentDefinition"));
        oper.setReturnClass(com.questionmark.QMWISe.AssessmentDefinition.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentDefinition"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateAssessmentDefinition");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentDefinition"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentDefinition"), com.questionmark.QMWISe.AssessmentDefinition.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAssessmentDefinitionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SetAssessmentDefinition");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentDefinition"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentDefinition"), com.questionmark.QMWISe.AssessmentDefinition.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[31] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("DeleteAssessment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[32] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessmentTreeByAdministrator");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Parent_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "OnlyRunFromIntegration"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentTreeItemList"));
        oper.setReturnClass(com.questionmark.QMWISe.AssessmentTreeItem[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentTreeItemList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentTreeItem"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[33] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateScheduleParticipantV42");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "schedule"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleV42"), com.questionmark.QMWISe.ScheduleV42.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        oper.setReturnClass(int.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_ID"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[34] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateScheduleGroupV42");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "schedule"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleV42"), com.questionmark.QMWISe.ScheduleV42.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "scheduleEachMember"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleIDList"));
        oper.setReturnClass(java.lang.String[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_ID"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_ID"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[35] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetScheduleV42");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "scheduleId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleV42"));
        oper.setReturnClass(com.questionmark.QMWISe.ScheduleV42.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetScheduleV42Result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[36] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetScheduleListV42");
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleListV42"));
        oper.setReturnClass(com.questionmark.QMWISe.ScheduleV42[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetScheduleListV42Result"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleV42"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[37] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetScheduleListByParticipantV42");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "participantId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleListV42"));
        oper.setReturnClass(com.questionmark.QMWISe.ScheduleV42[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetScheduleListByParticipantV42Result"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleV42"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[38] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetScheduleListByGroupV42");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "groupId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleListV42"));
        oper.setReturnClass(com.questionmark.QMWISe.ScheduleV42[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetScheduleListByGroupV42Result"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleV42"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[39] = oper;

    }

    private static void _initOperationDesc5(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetScheduleListByAssessmentV42");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleListV42"));
        oper.setReturnClass(com.questionmark.QMWISe.ScheduleV42[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetScheduleListByAssessmentV42Result"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleV42"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[40] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SetScheduleV42");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "schedule"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleV42"), com.questionmark.QMWISe.ScheduleV42.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[41] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("DeleteScheduleV42");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "scheduleId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[42] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetSchedule");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule"));
        oper.setReturnClass(com.questionmark.QMWISe.Schedule.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[43] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetScheduleList");
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleList"));
        oper.setReturnClass(com.questionmark.QMWISe.Schedule[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[44] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetScheduleListByParticipant");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleList"));
        oper.setReturnClass(com.questionmark.QMWISe.Schedule[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[45] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetScheduleListByGroup");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleList"));
        oper.setReturnClass(com.questionmark.QMWISe.Schedule[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[46] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetScheduleListByAssessment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleList"));
        oper.setReturnClass(com.questionmark.QMWISe.Schedule[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[47] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateScheduleGroup");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_Participants"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule"), com.questionmark.QMWISe.Schedule.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleIDList"));
        oper.setReturnClass(java.lang.String[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_ID"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_ID"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[48] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateScheduleParticipant");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule"), com.questionmark.QMWISe.Schedule.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_ID"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[49] = oper;

    }

    private static void _initOperationDesc6(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SetSchedule");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule"), com.questionmark.QMWISe.Schedule.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[50] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("DeleteSchedule");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[51] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("DeleteScheduleListByParticipant");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[52] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ConfirmAssessmentDownload");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        oper.setReturnClass(int.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ConfirmAssessmentDownloadResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[53] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SendEmailsScheduledEvent");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Event"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Test_Center_Name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Proctor_Only"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Proctor_Email"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "EmailReturn"));
        oper.setReturnClass(com.questionmark.QMWISe.EmailReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "SendEmailsScheduledEventResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[54] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetResult");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result"));
        oper.setReturnClass(com.questionmark.QMWISe.Result.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[55] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetResultList");
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ResultList"));
        oper.setReturnClass(com.questionmark.QMWISe.Result[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ResultList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[56] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetResultListByGroup");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_Name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ResultList"));
        oper.setReturnClass(com.questionmark.QMWISe.Result[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ResultList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[57] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetResultListByParticipant");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_Name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ResultList"));
        oper.setReturnClass(com.questionmark.QMWISe.Result[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ResultList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[58] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetResultListByAssessment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ResultList"));
        oper.setReturnClass(com.questionmark.QMWISe.Result[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ResultList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[59] = oper;

    }

    private static void _initOperationDesc7(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAccessAssessment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_Name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_Details"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "URL"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[60] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAccessAssessmentList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_Name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "URL"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[61] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAccessAssessmentNotify");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_Name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Notify"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "PIP"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ParameterList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ParameterList"), com.questionmark.QMWISe.Parameter[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Parameter"));
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "URL"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[62] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAccessReportTemplate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Template_Name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "URL"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[63] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAccessReport");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "URL"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[64] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAccessAdministrator");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_Name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "URL"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[65] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAdministrator");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator"));
        oper.setReturnClass(com.questionmark.QMWISe.Administrator.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[66] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CheckAdministrator");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_Name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Status"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_ID"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[67] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAdministratorByName");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_Name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator"));
        oper.setReturnClass(com.questionmark.QMWISe.Administrator.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[68] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAdministratorList");
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AdministratorList"));
        oper.setReturnClass(com.questionmark.QMWISe.Administrator[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AdministratorList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[69] = oper;

    }

    private static void _initOperationDesc8(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAdministratorListByGroup");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AdministratorList"));
        oper.setReturnClass(com.questionmark.QMWISe.Administrator[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AdministratorList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[70] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAdministratorGroupList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GroupList"));
        oper.setReturnClass(com.questionmark.QMWISe.Group[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GroupList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[71] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetProfileNameList");
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ProfileNameList"));
        oper.setReturnClass(java.lang.String[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ProfileNameList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ProfileName"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[72] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateAdministrator");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator"), com.questionmark.QMWISe.Administrator.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_ID"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[73] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateAdministratorWithPassword");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator2"), com.questionmark.QMWISe.Administrator2.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_ID"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[74] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateAdministratorChangePasswordAtLogin");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator"), com.questionmark.QMWISe.Administrator.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_ID"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[75] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessmentResultInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfo"));
        oper.setReturnClass(com.questionmark.QMWISe.AssessmentResultInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfo"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[76] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessmentResultInfo2");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfo2"));
        oper.setReturnClass(com.questionmark.QMWISe.AssessmentResultInfo2.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfo2"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[77] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessmentResultInfoList");
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfoList"));
        oper.setReturnClass(com.questionmark.QMWISe.AssessmentResultInfo[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfoList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfo"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[78] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessmentResultInfoList2");
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfoList2"));
        oper.setReturnClass(com.questionmark.QMWISe.AssessmentResultInfo2[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfoList2"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfo2"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[79] = oper;

    }

    private static void _initOperationDesc9(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateAssessmentResultInfoList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfoList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfoList"), com.questionmark.QMWISe.AssessmentResultInfo[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfo"));
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        oper.setReturnClass(int.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "NumberRecordsCreated"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[80] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateAssessmentResultInfoList2");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfoList2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfoList2"), com.questionmark.QMWISe.AssessmentResultInfo2[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfo2"));
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        oper.setReturnClass(int.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "NumberRecordsCreated"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[81] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetQuestionResultInfoList");
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "QuestionResultInfoList"));
        oper.setReturnClass(com.questionmark.QMWISe.QuestionResultInfo[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "QuestionResultInfoList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "QuestionResultInfo"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[82] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateQuestionResultInfoList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "QuestionResultInfoList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "QuestionResultInfoList"), com.questionmark.QMWISe.QuestionResultInfo[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "QuestionResultInfo"));
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        oper.setReturnClass(int.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "NumberRecordsCreated"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[83] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessmentResult");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResult"));
        oper.setReturnClass(com.questionmark.QMWISe.AssessmentResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[84] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessmentResult2");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResult2"));
        oper.setReturnClass(com.questionmark.QMWISe.AssessmentResult2.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResult2"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[85] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessmentResultListByGroup");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_Name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultList"));
        oper.setReturnClass(com.questionmark.QMWISe.AssessmentResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[86] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessmentResultListByParticipant");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_Name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultList"));
        oper.setReturnClass(com.questionmark.QMWISe.AssessmentResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[87] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessmentResultListByAssessment");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultList"));
        oper.setReturnClass(com.questionmark.QMWISe.AssessmentResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[88] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateAssessmentResult");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResult"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResult"), com.questionmark.QMWISe.AssessmentResult.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result_ID"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[89] = oper;

    }

    private static void _initOperationDesc10(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateAssessmentResult2");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResult2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResult2"), com.questionmark.QMWISe.AssessmentResult2.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result_ID"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[90] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetQuestionCorrectAnswer");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Question"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Question"), com.questionmark.QMWISe.Question.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CorrectAnswer"));
        oper.setReturnClass(com.questionmark.QMWISe.CorrectAnswer.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CorrectAnswer"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[91] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateTopic");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Topic"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Topic"), com.questionmark.QMWISe.Topic.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Topic_ID"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[92] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateAssessmentFolder");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentFolder"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentFolder"), com.questionmark.QMWISe.AssessmentFolder.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Folder_ID"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[93] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("AssignAdministratorToTopic");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Topic_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Permissions"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[94] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("AssignAdministratorToAssessmentFolder");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Folder_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Permissions"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[95] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateAssessmentSnapshot");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentSnapshotDefinition"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAssessmentSnapshotParams"), com.questionmark.QMWISe.CreateAssessmentSnapshotParams.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshot"));
        oper.setReturnClass(com.questionmark.QMWISe.DeliveryAssessmentSnapshot.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentSnapshotForDelivery"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[96] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ScoreAssessmentSnapshot");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentSnapshotAnswers"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentSnapshotAnswers"), com.questionmark.QMWISe.AssessmentSnapshotAnswers.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        oper.setReturnClass(int.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScoreAssessmentSnapshotResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[97] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessmentSnapshotResponseConstraints");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "snapshotID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "SnapshotResponseConstraints"));
        oper.setReturnClass(com.questionmark.QMWISe.SnapshotResponseConstraints.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentSnapshotResponseConstraints"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[98] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessmentSnapshot");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Snapshot_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshot"));
        oper.setReturnClass(com.questionmark.QMWISe.DeliveryAssessmentSnapshot.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentSnapshotForDelivery"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[99] = oper;

    }

    private static void _initOperationDesc11(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAssessmentSnapshotSummary");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Snapshot_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshot"));
        oper.setReturnClass(com.questionmark.QMWISe.DeliveryAssessmentSnapshot.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentSnapshotForDelivery"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[100] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("DeleteAssessmentSnapshot");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Snapshot_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[101] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateAndScheduleParticipant");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant"), com.questionmark.QMWISe.Participant.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleList"), com.questionmark.QMWISe.Schedule[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule"));
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAndScheduleParticipant"));
        oper.setReturnClass(com.questionmark.QMWISe.CreateAndScheduleParticipant.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAndScheduleParticipant"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[102] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAccessScheduleNotify");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_Name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Notify"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "PIP"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ParameterList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ParameterList"), com.questionmark.QMWISe.Parameter[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Parameter"));
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAccessScheduleNotifyResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[103] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("DeleteScheduleList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleIDList"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleIDList"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_ID"));
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleList"));
        oper.setReturnClass(com.questionmark.QMWISe.Schedule[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleList"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[104] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateScheduleWithParticipantGroupTestCenter");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant2"), com.questionmark.QMWISe.Participant2.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule2"), com.questionmark.QMWISe.Schedule2.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Event"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Event"), com.questionmark.QMWISe.Event.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "TestCenter"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "TestCenter"), com.questionmark.QMWISe.TestCenter.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        oper.setReturnClass(int.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_Id"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[105] = oper;

    }

    public QMWISeSoapStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public QMWISeSoapStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public QMWISeSoapStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Administrator.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator2");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Administrator2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AdministratorIDList");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_ID");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AdministratorList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Administrator[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Answer");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Answer.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Answer2");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Answer2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AnswerList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Answer[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Answer");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Answer");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AnswerList2");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Answer2[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Answer2");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Answer2");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Assessment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentBlock");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.AssessmentBlock.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentBlockList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.AssessmentBlock[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentBlock");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentBlock");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentDefinition");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.AssessmentDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentFolder");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.AssessmentFolder.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Assessment[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assessment");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentOutcome");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.AssessmentOutcome.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentOutcomeList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.AssessmentOutcome[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentOutcome");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentOutcome");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResult");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.AssessmentResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResult2");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.AssessmentResult2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfo");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.AssessmentResultInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfo2");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.AssessmentResultInfo2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfoList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.AssessmentResultInfo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfo");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfo");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfoList2");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.AssessmentResultInfo2[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfo2");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultInfo2");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResultList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.AssessmentResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResult");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentResult");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentSnapshotAnswers");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.AssessmentSnapshotAnswers.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentTreeItem");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.AssessmentTreeItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentTreeItemList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.AssessmentTreeItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentTreeItem");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssessmentTreeItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Choice");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Choice.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Configuration");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Configuration.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ConfigurationList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Configuration[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Configuration");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Configuration");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CorrectAnswer");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.CorrectAnswer.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAndScheduleParticipant");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.CreateAndScheduleParticipant.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAssessmentSnapshotParams");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.CreateAssessmentSnapshotParams.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DataEntity");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.DataEntity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshot");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.DeliveryAssessmentSnapshot.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotAnswer");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.DeliveryAssessmentSnapshotChoice[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotChoice");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CHOICE");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotChoice");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.DeliveryAssessmentSnapshotChoice.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotContent");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.DeliveryAssessmentSnapshotContent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotHeader");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.DeliveryAssessmentSnapshotHeader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotQuestion");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.DeliveryAssessmentSnapshotQuestion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotQuestionBlock");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.DeliveryAssessmentSnapshotQuestionBlock.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotQuestionList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.DeliveryAssessmentSnapshotQuestion[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeliveryAssessmentSnapshotQuestion");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "QUESTION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "EmailReturn");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.EmailReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Event");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Event.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Group.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GroupIDList");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_ID");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GroupList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Group[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Item");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Item.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ItemList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Item[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Item");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "LMSDetails");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.LMSDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Outcome");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Outcome.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "OutcomeList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Outcome[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Outcome");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Outcome");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Parameter");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Parameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ParameterList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Parameter[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Parameter");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Parameter");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Participant.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant2");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Participant2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ParticipantIDList");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_ID");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ParticipantList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Participant[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ProfileNameList");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ProfileName");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Question");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Question.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "QuestionResultInfo");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.QuestionResultInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "QuestionResultInfoList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.QuestionResultInfo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "QuestionResultInfo");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "QuestionResultInfo");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Response");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ResponseList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Response[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Response");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Response");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Result.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result2");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Result2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ResultList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Result[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Schedule.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule2");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Schedule2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleIDList");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule_ID");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Schedule[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Schedule");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleListV42");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.ScheduleV42[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleV42");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleV42");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScheduleV42");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.ScheduleV42.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScoreBand");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.ScoreBand.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScoreBandList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.ScoreBand[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScoreBand");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScoreBand");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "SnapshotResponseConstraints");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.SnapshotResponseConstraints.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "TestCenter");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.TestCenter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Topic");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Topic.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "TopicList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Topic[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Topic");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Topic");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "TopicScoring");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.TopicScoring.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "TopicScoringList");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.TopicScoring[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "TopicScoring");
            qName2 = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "TopicScoring");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "UnencryptedLMSDetails");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.UnencryptedLMSDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Version");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Version.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Version2");
            cachedSerQNames.add(qName);
            cls = com.questionmark.QMWISe.Version2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.questionmark.QMWISe.Version getAbout() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAbout");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAbout"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Version) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Version) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Version.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Version2 getAbout2() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAbout2");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAbout2"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Version2) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Version2) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Version2.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Configuration[] getConfiguration() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetConfiguration");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetConfiguration"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Configuration[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Configuration[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Configuration[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Configuration[] setConfiguration() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/SetConfiguration");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "SetConfiguration"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Configuration[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Configuration[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Configuration[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Participant getParticipant(java.lang.String participant_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetParticipant");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetParticipant"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {participant_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Participant) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Participant) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Participant.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Participant getParticipantByName(java.lang.String participant_Name) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetParticipantByName");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetParticipantByName"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {participant_Name});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Participant) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Participant) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Participant.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Participant[] getParticipantList() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetParticipantList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetParticipantList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Participant[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Participant[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Participant[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Participant[] getParticipantListByGroup(java.lang.String group_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetParticipantListByGroup");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetParticipantListByGroup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {group_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Participant[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Participant[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Participant[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Group[] getParticipantGroupList(java.lang.String participant_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetParticipantGroupList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetParticipantGroupList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {participant_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Group[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Group[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Group[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void checkParticipant(java.lang.String participant_Name, java.lang.String password, javax.xml.rpc.holders.IntHolder status, javax.xml.rpc.holders.StringHolder participant_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CheckParticipant");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CheckParticipant"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {participant_Name, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                status.value = ((java.lang.Integer) _output.get(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Status"))).intValue();
            } catch (java.lang.Exception _exception) {
                status.value = ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Status")), int.class)).intValue();
            }
            try {
                participant_ID.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_ID"));
            } catch (java.lang.Exception _exception) {
                participant_ID.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_ID")), java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String createParticipant(com.questionmark.QMWISe.Participant participant) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateParticipant");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateParticipant"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {participant});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void setParticipant(com.questionmark.QMWISe.Participant participant) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/SetParticipant");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "SetParticipant"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {participant});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void deleteParticipant(java.lang.String participant_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/DeleteParticipant");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeleteParticipant"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {participant_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Group getGroup(java.lang.String group_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetGroup");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetGroup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {group_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Group) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Group) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Group.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Group getGroupByName(java.lang.String group_Name) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetGroupByName");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetGroupByName"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {group_Name});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Group) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Group) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Group.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Group[] getGroupList() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetGroupList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetGroupList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Group[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Group[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Group[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String createGroup(com.questionmark.QMWISe.Group group) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateGroup");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateGroup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {group});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void setGroup(com.questionmark.QMWISe.Group group) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/SetGroup");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "SetGroup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {group});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void deleteGroup(java.lang.String group_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/DeleteGroup");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeleteGroup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {group_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void addGroupParticipantList(java.lang.String group_ID, java.lang.String[] participantIDList) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/AddGroupParticipantList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AddGroupParticipantList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {group_ID, participantIDList});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void deleteGroupParticipantList(java.lang.String group_ID, java.lang.String[] participantIDList) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/DeleteGroupParticipantList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeleteGroupParticipantList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {group_ID, participantIDList});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void addGroupAdministratorList(java.lang.String group_ID, java.lang.String[] administratorIDList) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/AddGroupAdministratorList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AddGroupAdministratorList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {group_ID, administratorIDList});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void deleteGroupAdministratorList(java.lang.String group_ID, java.lang.String[] administratorIDList) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/DeleteGroupAdministratorList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeleteGroupAdministratorList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {group_ID, administratorIDList});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String getQuestionPresentation(java.lang.String question_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetQuestionPresentation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetQuestionPresentation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {question_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Assessment getAssessment(java.lang.String assessment_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessment");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessment"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessment_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Assessment) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Assessment) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Assessment.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Assessment[] getAssessmentList() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessmentList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessmentList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Assessment[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Assessment[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Assessment[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Assessment[] getAssessmentListByGroup(java.lang.String group_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessmentListByGroup");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessmentListByGroup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {group_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Assessment[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Assessment[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Assessment[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Assessment[] getAssessmentListByAdministrator(java.lang.String administrator_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessmentListByAdministrator");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessmentListByAdministrator"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {administrator_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Assessment[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Assessment[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Assessment[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Topic[] getAssessmentReportedTopics(java.lang.String assessment_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessmentReportedTopics");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessmentReportedTopics"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessment_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Topic[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Topic[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Topic[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.AssessmentDefinition getAssessmentDefinition(java.lang.String assessment_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessmentDefinition");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessmentDefinition"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessment_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.AssessmentDefinition) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.AssessmentDefinition) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.AssessmentDefinition.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String createAssessmentDefinition(com.questionmark.QMWISe.AssessmentDefinition assessmentDefinition) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateAssessmentDefinition");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAssessmentDefinition"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessmentDefinition});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void setAssessmentDefinition(com.questionmark.QMWISe.AssessmentDefinition assessmentDefinition) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/SetAssessmentDefinition");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "SetAssessmentDefinition"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessmentDefinition});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void deleteAssessment(java.lang.String assessment_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[32]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/DeleteAssessment");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeleteAssessment"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessment_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.AssessmentTreeItem[] getAssessmentTreeByAdministrator(java.lang.String administrator_ID, java.lang.String parent_ID, int onlyRunFromIntegration) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[33]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessmentTreeByAdministrator");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessmentTreeByAdministrator"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {administrator_ID, parent_ID, new java.lang.Integer(onlyRunFromIntegration)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.AssessmentTreeItem[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.AssessmentTreeItem[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.AssessmentTreeItem[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public int createScheduleParticipantV42(com.questionmark.QMWISe.ScheduleV42 schedule) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[34]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateScheduleParticipantV42");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateScheduleParticipantV42"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {schedule});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Integer) _resp).intValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_resp, int.class)).intValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String[] createScheduleGroupV42(com.questionmark.QMWISe.ScheduleV42 schedule, boolean scheduleEachMember) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[35]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateScheduleGroupV42");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateScheduleGroupV42"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {schedule, new java.lang.Boolean(scheduleEachMember)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.ScheduleV42 getScheduleV42(int scheduleId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[36]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetScheduleV42");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetScheduleV42"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(scheduleId)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.ScheduleV42) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.ScheduleV42) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.ScheduleV42.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.ScheduleV42[] getScheduleListV42() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[37]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetScheduleListV42");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetScheduleListV42"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.ScheduleV42[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.ScheduleV42[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.ScheduleV42[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.ScheduleV42[] getScheduleListByParticipantV42(int participantId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[38]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetScheduleListByParticipantV42");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetScheduleListByParticipantV42"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(participantId)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.ScheduleV42[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.ScheduleV42[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.ScheduleV42[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.ScheduleV42[] getScheduleListByGroupV42(int groupId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[39]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetScheduleListByGroupV42");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetScheduleListByGroupV42"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(groupId)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.ScheduleV42[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.ScheduleV42[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.ScheduleV42[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.ScheduleV42[] getScheduleListByAssessmentV42(java.lang.String assessment_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[40]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetScheduleListByAssessmentV42");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetScheduleListByAssessmentV42"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessment_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.ScheduleV42[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.ScheduleV42[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.ScheduleV42[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void setScheduleV42(com.questionmark.QMWISe.ScheduleV42 schedule) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[41]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/SetScheduleV42");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "SetScheduleV42"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {schedule});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void deleteScheduleV42(int scheduleId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[42]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/DeleteScheduleV42");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeleteScheduleV42"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(scheduleId)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Schedule getSchedule(java.lang.String schedule_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[43]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetSchedule");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetSchedule"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {schedule_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Schedule) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Schedule) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Schedule.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Schedule[] getScheduleList() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[44]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetScheduleList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetScheduleList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Schedule[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Schedule[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Schedule[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Schedule[] getScheduleListByParticipant(java.lang.String participant_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[45]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetScheduleListByParticipant");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetScheduleListByParticipant"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {participant_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Schedule[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Schedule[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Schedule[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Schedule[] getScheduleListByGroup(java.lang.String group_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[46]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetScheduleListByGroup");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetScheduleListByGroup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {group_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Schedule[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Schedule[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Schedule[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Schedule[] getScheduleListByAssessment(java.lang.String assessment_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[47]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetScheduleListByAssessment");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetScheduleListByAssessment"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessment_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Schedule[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Schedule[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Schedule[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String[] createScheduleGroup(boolean schedule_Participants, com.questionmark.QMWISe.Schedule schedule) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[48]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateScheduleGroup");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateScheduleGroup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Boolean(schedule_Participants), schedule});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String createScheduleParticipant(com.questionmark.QMWISe.Schedule schedule) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[49]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateScheduleParticipant");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateScheduleParticipant"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {schedule});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void setSchedule(com.questionmark.QMWISe.Schedule schedule) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[50]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/SetSchedule");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "SetSchedule"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {schedule});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void deleteSchedule(java.lang.String schedule_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[51]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/DeleteSchedule");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeleteSchedule"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {schedule_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void deleteScheduleListByParticipant(java.lang.String participant_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[52]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/DeleteScheduleListByParticipant");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeleteScheduleListByParticipant"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {participant_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public int confirmAssessmentDownload(java.lang.String schedule_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[53]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/ConfirmAssessmentDownload");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ConfirmAssessmentDownload"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {schedule_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Integer) _resp).intValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_resp, int.class)).intValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.EmailReturn sendEmailsScheduledEvent(java.lang.String event, java.lang.String test_Center_Name, int proctor_Only, java.lang.String proctor_Email) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[54]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/SendEmailsScheduledEvent");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "SendEmailsScheduledEvent"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {event, test_Center_Name, new java.lang.Integer(proctor_Only), proctor_Email});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.EmailReturn) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.EmailReturn) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.EmailReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Result getResult(java.lang.String result_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[55]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetResult");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetResult"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {result_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Result) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Result) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Result.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Result[] getResultList() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[56]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetResultList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetResultList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Result[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Result[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Result[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Result[] getResultListByGroup(java.lang.String group_Name) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[57]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetResultListByGroup");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetResultListByGroup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {group_Name});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Result[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Result[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Result[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Result[] getResultListByParticipant(java.lang.String participant_Name) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[58]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetResultListByParticipant");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetResultListByParticipant"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {participant_Name});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Result[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Result[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Result[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Result[] getResultListByAssessment(java.lang.String assessment_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[59]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetResultListByAssessment");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetResultListByAssessment"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessment_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Result[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Result[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Result[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String getAccessAssessment(java.lang.String assessment_ID, java.lang.String participant_Name, java.lang.String participant_Details, java.lang.String group_name) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[60]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAccessAssessment");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAccessAssessment"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessment_ID, participant_Name, participant_Details, group_name});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String getAccessAssessmentList(java.lang.String participant_Name) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[61]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAccessAssessmentList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAccessAssessmentList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {participant_Name});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String getAccessAssessmentNotify(java.lang.String assessment_ID, java.lang.String participant_Name, java.lang.String notify, java.lang.String PIP, com.questionmark.QMWISe.Parameter[] parameterList) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[62]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAccessAssessmentNotify");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAccessAssessmentNotify"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessment_ID, participant_Name, notify, PIP, parameterList});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String getAccessReportTemplate(java.lang.String result_ID, java.lang.String template_Name) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[63]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAccessReportTemplate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAccessReportTemplate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {result_ID, template_Name});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String getAccessReport(java.lang.String result_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[64]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAccessReport");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAccessReport"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {result_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String getAccessAdministrator(java.lang.String administrator_Name) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[65]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAccessAdministrator");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAccessAdministrator"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {administrator_Name});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Administrator getAdministrator(java.lang.String administrator_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[66]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAdministrator");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAdministrator"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {administrator_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Administrator) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Administrator) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Administrator.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void checkAdministrator(java.lang.String administrator_Name, java.lang.String password, javax.xml.rpc.holders.IntHolder status, javax.xml.rpc.holders.StringHolder administrator_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[67]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CheckAdministrator");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CheckAdministrator"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {administrator_Name, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                status.value = ((java.lang.Integer) _output.get(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Status"))).intValue();
            } catch (java.lang.Exception _exception) {
                status.value = ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Status")), int.class)).intValue();
            }
            try {
                administrator_ID.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_ID"));
            } catch (java.lang.Exception _exception) {
                administrator_ID.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Administrator_ID")), java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Administrator getAdministratorByName(java.lang.String administrator_Name) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[68]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAdministratorByName");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAdministratorByName"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {administrator_Name});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Administrator) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Administrator) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Administrator.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Administrator[] getAdministratorList() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[69]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAdministratorList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAdministratorList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Administrator[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Administrator[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Administrator[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Administrator[] getAdministratorListByGroup(java.lang.String group_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[70]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAdministratorListByGroup");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAdministratorListByGroup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {group_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Administrator[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Administrator[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Administrator[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Group[] getAdministratorGroupList(java.lang.String administrator_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[71]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAdministratorGroupList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAdministratorGroupList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {administrator_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Group[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Group[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Group[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String[] getProfileNameList() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[72]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetProfileNameList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetProfileNameList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String createAdministrator(com.questionmark.QMWISe.Administrator administrator) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[73]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateAdministrator");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAdministrator"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {administrator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String createAdministratorWithPassword(com.questionmark.QMWISe.Administrator2 administrator) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[74]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateAdministratorWithPassword");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAdministratorWithPassword"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {administrator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String createAdministratorChangePasswordAtLogin(com.questionmark.QMWISe.Administrator administrator) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[75]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateAdministratorChangePasswordAtLogin");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAdministratorChangePasswordAtLogin"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {administrator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.AssessmentResultInfo getAssessmentResultInfo(java.lang.String assessment_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[76]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessmentResultInfo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessmentResultInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessment_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.AssessmentResultInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.AssessmentResultInfo) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.AssessmentResultInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.AssessmentResultInfo2 getAssessmentResultInfo2(java.lang.String assessment_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[77]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessmentResultInfo2");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessmentResultInfo2"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessment_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.AssessmentResultInfo2) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.AssessmentResultInfo2) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.AssessmentResultInfo2.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.AssessmentResultInfo[] getAssessmentResultInfoList() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[78]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessmentResultInfoList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessmentResultInfoList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.AssessmentResultInfo[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.AssessmentResultInfo[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.AssessmentResultInfo[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.AssessmentResultInfo2[] getAssessmentResultInfoList2() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[79]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessmentResultInfoList2");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessmentResultInfoList2"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.AssessmentResultInfo2[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.AssessmentResultInfo2[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.AssessmentResultInfo2[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public int createAssessmentResultInfoList(com.questionmark.QMWISe.AssessmentResultInfo[] assessmentResultInfoList) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[80]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateAssessmentResultInfoList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAssessmentResultInfoList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessmentResultInfoList});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Integer) _resp).intValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_resp, int.class)).intValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public int createAssessmentResultInfoList2(com.questionmark.QMWISe.AssessmentResultInfo2[] assessmentResultInfoList2) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[81]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateAssessmentResultInfoList2");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAssessmentResultInfoList2"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessmentResultInfoList2});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Integer) _resp).intValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_resp, int.class)).intValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.QuestionResultInfo[] getQuestionResultInfoList() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[82]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetQuestionResultInfoList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetQuestionResultInfoList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.QuestionResultInfo[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.QuestionResultInfo[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.QuestionResultInfo[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public int createQuestionResultInfoList(com.questionmark.QMWISe.QuestionResultInfo[] questionResultInfoList) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[83]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateQuestionResultInfoList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateQuestionResultInfoList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {questionResultInfoList});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Integer) _resp).intValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_resp, int.class)).intValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.AssessmentResult getAssessmentResult(java.lang.String result_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[84]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessmentResult");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessmentResult"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {result_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.AssessmentResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.AssessmentResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.AssessmentResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.AssessmentResult2 getAssessmentResult2(java.lang.String result_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[85]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessmentResult2");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessmentResult2"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {result_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.AssessmentResult2) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.AssessmentResult2) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.AssessmentResult2.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.AssessmentResult[] getAssessmentResultListByGroup(java.lang.String group_Name) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[86]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessmentResultListByGroup");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessmentResultListByGroup"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {group_Name});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.AssessmentResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.AssessmentResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.AssessmentResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.AssessmentResult[] getAssessmentResultListByParticipant(java.lang.String participant_Name) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[87]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessmentResultListByParticipant");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessmentResultListByParticipant"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {participant_Name});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.AssessmentResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.AssessmentResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.AssessmentResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.AssessmentResult[] getAssessmentResultListByAssessment(java.lang.String assessment_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[88]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessmentResultListByAssessment");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessmentResultListByAssessment"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessment_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.AssessmentResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.AssessmentResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.AssessmentResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String createAssessmentResult(com.questionmark.QMWISe.AssessmentResult assessmentResult) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[89]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateAssessmentResult");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAssessmentResult"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessmentResult});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String createAssessmentResult2(com.questionmark.QMWISe.AssessmentResult2 assessmentResult2) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[90]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateAssessmentResult2");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAssessmentResult2"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessmentResult2});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.CorrectAnswer getQuestionCorrectAnswer(com.questionmark.QMWISe.Question question) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[91]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetQuestionCorrectAnswer");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetQuestionCorrectAnswer"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {question});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.CorrectAnswer) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.CorrectAnswer) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.CorrectAnswer.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String createTopic(com.questionmark.QMWISe.Topic topic) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[92]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateTopic");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateTopic"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {topic});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String createAssessmentFolder(com.questionmark.QMWISe.AssessmentFolder assessmentFolder) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[93]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateAssessmentFolder");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAssessmentFolder"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessmentFolder});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void assignAdministratorToTopic(java.lang.String administrator_ID, java.lang.String topic_ID, javax.xml.rpc.holders.StringHolder permissions) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[94]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/AssignAdministratorToTopic");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssignAdministratorToTopic"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {administrator_ID, topic_ID, permissions.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                permissions.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Permissions"));
            } catch (java.lang.Exception _exception) {
                permissions.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Permissions")), java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void assignAdministratorToAssessmentFolder(java.lang.String administrator_ID, java.lang.String folder_ID, javax.xml.rpc.holders.StringHolder permissions) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[95]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/AssignAdministratorToAssessmentFolder");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "AssignAdministratorToAssessmentFolder"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {administrator_ID, folder_ID, permissions.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                permissions.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Permissions"));
            } catch (java.lang.Exception _exception) {
                permissions.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Permissions")), java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.DeliveryAssessmentSnapshot createAssessmentSnapshot(com.questionmark.QMWISe.CreateAssessmentSnapshotParams assessmentSnapshotDefinition) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[96]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateAssessmentSnapshot");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAssessmentSnapshot"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessmentSnapshotDefinition});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.DeliveryAssessmentSnapshot) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.DeliveryAssessmentSnapshot) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.DeliveryAssessmentSnapshot.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public int scoreAssessmentSnapshot(com.questionmark.QMWISe.AssessmentSnapshotAnswers assessmentSnapshotAnswers) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[97]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/ScoreAssessmentSnapshot");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "ScoreAssessmentSnapshot"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {assessmentSnapshotAnswers});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Integer) _resp).intValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_resp, int.class)).intValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.SnapshotResponseConstraints getAssessmentSnapshotResponseConstraints(java.lang.String snapshotID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[98]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessmentSnapshotResponseConstraints");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessmentSnapshotResponseConstraints"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {snapshotID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.SnapshotResponseConstraints) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.SnapshotResponseConstraints) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.SnapshotResponseConstraints.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.DeliveryAssessmentSnapshot getAssessmentSnapshot(java.lang.String snapshot_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[99]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessmentSnapshot");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessmentSnapshot"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {snapshot_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.DeliveryAssessmentSnapshot) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.DeliveryAssessmentSnapshot) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.DeliveryAssessmentSnapshot.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.DeliveryAssessmentSnapshot getAssessmentSnapshotSummary(java.lang.String snapshot_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[100]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAssessmentSnapshotSummary");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAssessmentSnapshotSummary"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {snapshot_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.DeliveryAssessmentSnapshot) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.DeliveryAssessmentSnapshot) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.DeliveryAssessmentSnapshot.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void deleteAssessmentSnapshot(java.lang.String snapshot_ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[101]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/DeleteAssessmentSnapshot");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeleteAssessmentSnapshot"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {snapshot_ID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.CreateAndScheduleParticipant createAndScheduleParticipant(com.questionmark.QMWISe.Participant participant, com.questionmark.QMWISe.Schedule[] scheduleList) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[102]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateAndScheduleParticipant");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateAndScheduleParticipant"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {participant, scheduleList});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.CreateAndScheduleParticipant) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.CreateAndScheduleParticipant) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.CreateAndScheduleParticipant.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String getAccessScheduleNotify(java.lang.String schedule_ID, java.lang.String participant_Name, java.lang.String notify, java.lang.String PIP, com.questionmark.QMWISe.Parameter[] parameterList) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[103]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/GetAccessScheduleNotify");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GetAccessScheduleNotify"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {schedule_ID, participant_Name, notify, PIP, parameterList});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.questionmark.QMWISe.Schedule[] deleteScheduleList(java.lang.String[] scheduleIDList) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[104]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/DeleteScheduleList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "DeleteScheduleList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduleIDList});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.questionmark.QMWISe.Schedule[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.questionmark.QMWISe.Schedule[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.questionmark.QMWISe.Schedule[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public int createScheduleWithParticipantGroupTestCenter(com.questionmark.QMWISe.Participant2 participant2, com.questionmark.QMWISe.Schedule2 schedule2, com.questionmark.QMWISe.Event event, com.questionmark.QMWISe.TestCenter testCenter) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[105]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://questionmark.com/QMWISe/CreateScheduleWithParticipantGroupTestCenter");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "CreateScheduleWithParticipantGroupTestCenter"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {participant2, schedule2, event, testCenter});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Integer) _resp).intValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_resp, int.class)).intValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
