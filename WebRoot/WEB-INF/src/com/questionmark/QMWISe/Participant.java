/**
 * Participant.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class Participant  implements java.io.Serializable {
    private java.lang.String participant_ID;

    private java.lang.String participant_Name;

    private java.lang.String password;

    private java.lang.String first_Name;

    private java.lang.String last_Name;

    private java.lang.String middle_Name;

    private int use_Correspondence;

    private java.lang.String primary_Address_1;

    private java.lang.String primary_Address_2;

    private java.lang.String primary_City;

    private java.lang.String primary_State;

    private java.lang.String primary_ZIP_Code;

    private java.lang.String primary_Country;

    private java.lang.String primary_Phone;

    private java.lang.String primary_Fax;

    private java.lang.String primary_Email;

    private java.lang.String secondary_Address_1;

    private java.lang.String secondary_Address_2;

    private java.lang.String secondary_City;

    private java.lang.String secondary_State;

    private java.lang.String secondary_ZIP_Code;

    private java.lang.String secondary_Country;

    private java.lang.String secondary_Phone;

    private java.lang.String secondary_Fax;

    private java.lang.String secondary_Email;

    private java.lang.String salutation;

    private java.lang.String organization_Name;

    private java.lang.String department;

    private java.lang.String title;

    private java.lang.String assistant_Name;

    private java.lang.String manager_Name;

    private java.lang.String gender;

    private java.lang.String URL;

    private java.lang.String details;

    private java.lang.String details_1;

    private java.lang.String details_2;

    private java.lang.String details_3;

    private java.lang.String details_4;

    private java.lang.String details_5;

    private java.lang.String details_6;

    private java.lang.String details_7;

    private java.lang.String details_8;

    private java.lang.String details_9;

    private java.lang.String details_10;

    private java.lang.String details_11;

    private java.lang.String details_12;

    private java.lang.String details_13;

    private java.lang.String details_14;

    private java.lang.String details_15;

    private java.lang.String details_16;

    private java.lang.String details_17;

    private java.lang.String details_18;

    private java.lang.String details_19;

    private java.lang.String details_20;

    private int authenticate_Ext;

    private java.lang.String[] groupIDList;

    private java.lang.String date_Registration;

    public Participant() {
    }

    public Participant(
           java.lang.String participant_ID,
           java.lang.String participant_Name,
           java.lang.String password,
           java.lang.String first_Name,
           java.lang.String last_Name,
           java.lang.String middle_Name,
           int use_Correspondence,
           java.lang.String primary_Address_1,
           java.lang.String primary_Address_2,
           java.lang.String primary_City,
           java.lang.String primary_State,
           java.lang.String primary_ZIP_Code,
           java.lang.String primary_Country,
           java.lang.String primary_Phone,
           java.lang.String primary_Fax,
           java.lang.String primary_Email,
           java.lang.String secondary_Address_1,
           java.lang.String secondary_Address_2,
           java.lang.String secondary_City,
           java.lang.String secondary_State,
           java.lang.String secondary_ZIP_Code,
           java.lang.String secondary_Country,
           java.lang.String secondary_Phone,
           java.lang.String secondary_Fax,
           java.lang.String secondary_Email,
           java.lang.String salutation,
           java.lang.String organization_Name,
           java.lang.String department,
           java.lang.String title,
           java.lang.String assistant_Name,
           java.lang.String manager_Name,
           java.lang.String gender,
           java.lang.String URL,
           java.lang.String details,
           java.lang.String details_1,
           java.lang.String details_2,
           java.lang.String details_3,
           java.lang.String details_4,
           java.lang.String details_5,
           java.lang.String details_6,
           java.lang.String details_7,
           java.lang.String details_8,
           java.lang.String details_9,
           java.lang.String details_10,
           java.lang.String details_11,
           java.lang.String details_12,
           java.lang.String details_13,
           java.lang.String details_14,
           java.lang.String details_15,
           java.lang.String details_16,
           java.lang.String details_17,
           java.lang.String details_18,
           java.lang.String details_19,
           java.lang.String details_20,
           int authenticate_Ext,
           java.lang.String[] groupIDList,
           java.lang.String date_Registration) {
           this.participant_ID = participant_ID;
           this.participant_Name = participant_Name;
           this.password = password;
           this.first_Name = first_Name;
           this.last_Name = last_Name;
           this.middle_Name = middle_Name;
           this.use_Correspondence = use_Correspondence;
           this.primary_Address_1 = primary_Address_1;
           this.primary_Address_2 = primary_Address_2;
           this.primary_City = primary_City;
           this.primary_State = primary_State;
           this.primary_ZIP_Code = primary_ZIP_Code;
           this.primary_Country = primary_Country;
           this.primary_Phone = primary_Phone;
           this.primary_Fax = primary_Fax;
           this.primary_Email = primary_Email;
           this.secondary_Address_1 = secondary_Address_1;
           this.secondary_Address_2 = secondary_Address_2;
           this.secondary_City = secondary_City;
           this.secondary_State = secondary_State;
           this.secondary_ZIP_Code = secondary_ZIP_Code;
           this.secondary_Country = secondary_Country;
           this.secondary_Phone = secondary_Phone;
           this.secondary_Fax = secondary_Fax;
           this.secondary_Email = secondary_Email;
           this.salutation = salutation;
           this.organization_Name = organization_Name;
           this.department = department;
           this.title = title;
           this.assistant_Name = assistant_Name;
           this.manager_Name = manager_Name;
           this.gender = gender;
           this.URL = URL;
           this.details = details;
           this.details_1 = details_1;
           this.details_2 = details_2;
           this.details_3 = details_3;
           this.details_4 = details_4;
           this.details_5 = details_5;
           this.details_6 = details_6;
           this.details_7 = details_7;
           this.details_8 = details_8;
           this.details_9 = details_9;
           this.details_10 = details_10;
           this.details_11 = details_11;
           this.details_12 = details_12;
           this.details_13 = details_13;
           this.details_14 = details_14;
           this.details_15 = details_15;
           this.details_16 = details_16;
           this.details_17 = details_17;
           this.details_18 = details_18;
           this.details_19 = details_19;
           this.details_20 = details_20;
           this.authenticate_Ext = authenticate_Ext;
           this.groupIDList = groupIDList;
           this.date_Registration = date_Registration;
    }


    /**
     * Gets the participant_ID value for this Participant.
     * 
     * @return participant_ID
     */
    public java.lang.String getParticipant_ID() {
        return participant_ID;
    }


    /**
     * Sets the participant_ID value for this Participant.
     * 
     * @param participant_ID
     */
    public void setParticipant_ID(java.lang.String participant_ID) {
        this.participant_ID = participant_ID;
    }


    /**
     * Gets the participant_Name value for this Participant.
     * 
     * @return participant_Name
     */
    public java.lang.String getParticipant_Name() {
        return participant_Name;
    }


    /**
     * Sets the participant_Name value for this Participant.
     * 
     * @param participant_Name
     */
    public void setParticipant_Name(java.lang.String participant_Name) {
        this.participant_Name = participant_Name;
    }


    /**
     * Gets the password value for this Participant.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this Participant.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the first_Name value for this Participant.
     * 
     * @return first_Name
     */
    public java.lang.String getFirst_Name() {
        return first_Name;
    }


    /**
     * Sets the first_Name value for this Participant.
     * 
     * @param first_Name
     */
    public void setFirst_Name(java.lang.String first_Name) {
        this.first_Name = first_Name;
    }


    /**
     * Gets the last_Name value for this Participant.
     * 
     * @return last_Name
     */
    public java.lang.String getLast_Name() {
        return last_Name;
    }


    /**
     * Sets the last_Name value for this Participant.
     * 
     * @param last_Name
     */
    public void setLast_Name(java.lang.String last_Name) {
        this.last_Name = last_Name;
    }


    /**
     * Gets the middle_Name value for this Participant.
     * 
     * @return middle_Name
     */
    public java.lang.String getMiddle_Name() {
        return middle_Name;
    }


    /**
     * Sets the middle_Name value for this Participant.
     * 
     * @param middle_Name
     */
    public void setMiddle_Name(java.lang.String middle_Name) {
        this.middle_Name = middle_Name;
    }


    /**
     * Gets the use_Correspondence value for this Participant.
     * 
     * @return use_Correspondence
     */
    public int getUse_Correspondence() {
        return use_Correspondence;
    }


    /**
     * Sets the use_Correspondence value for this Participant.
     * 
     * @param use_Correspondence
     */
    public void setUse_Correspondence(int use_Correspondence) {
        this.use_Correspondence = use_Correspondence;
    }


    /**
     * Gets the primary_Address_1 value for this Participant.
     * 
     * @return primary_Address_1
     */
    public java.lang.String getPrimary_Address_1() {
        return primary_Address_1;
    }


    /**
     * Sets the primary_Address_1 value for this Participant.
     * 
     * @param primary_Address_1
     */
    public void setPrimary_Address_1(java.lang.String primary_Address_1) {
        this.primary_Address_1 = primary_Address_1;
    }


    /**
     * Gets the primary_Address_2 value for this Participant.
     * 
     * @return primary_Address_2
     */
    public java.lang.String getPrimary_Address_2() {
        return primary_Address_2;
    }


    /**
     * Sets the primary_Address_2 value for this Participant.
     * 
     * @param primary_Address_2
     */
    public void setPrimary_Address_2(java.lang.String primary_Address_2) {
        this.primary_Address_2 = primary_Address_2;
    }


    /**
     * Gets the primary_City value for this Participant.
     * 
     * @return primary_City
     */
    public java.lang.String getPrimary_City() {
        return primary_City;
    }


    /**
     * Sets the primary_City value for this Participant.
     * 
     * @param primary_City
     */
    public void setPrimary_City(java.lang.String primary_City) {
        this.primary_City = primary_City;
    }


    /**
     * Gets the primary_State value for this Participant.
     * 
     * @return primary_State
     */
    public java.lang.String getPrimary_State() {
        return primary_State;
    }


    /**
     * Sets the primary_State value for this Participant.
     * 
     * @param primary_State
     */
    public void setPrimary_State(java.lang.String primary_State) {
        this.primary_State = primary_State;
    }


    /**
     * Gets the primary_ZIP_Code value for this Participant.
     * 
     * @return primary_ZIP_Code
     */
    public java.lang.String getPrimary_ZIP_Code() {
        return primary_ZIP_Code;
    }


    /**
     * Sets the primary_ZIP_Code value for this Participant.
     * 
     * @param primary_ZIP_Code
     */
    public void setPrimary_ZIP_Code(java.lang.String primary_ZIP_Code) {
        this.primary_ZIP_Code = primary_ZIP_Code;
    }


    /**
     * Gets the primary_Country value for this Participant.
     * 
     * @return primary_Country
     */
    public java.lang.String getPrimary_Country() {
        return primary_Country;
    }


    /**
     * Sets the primary_Country value for this Participant.
     * 
     * @param primary_Country
     */
    public void setPrimary_Country(java.lang.String primary_Country) {
        this.primary_Country = primary_Country;
    }


    /**
     * Gets the primary_Phone value for this Participant.
     * 
     * @return primary_Phone
     */
    public java.lang.String getPrimary_Phone() {
        return primary_Phone;
    }


    /**
     * Sets the primary_Phone value for this Participant.
     * 
     * @param primary_Phone
     */
    public void setPrimary_Phone(java.lang.String primary_Phone) {
        this.primary_Phone = primary_Phone;
    }


    /**
     * Gets the primary_Fax value for this Participant.
     * 
     * @return primary_Fax
     */
    public java.lang.String getPrimary_Fax() {
        return primary_Fax;
    }


    /**
     * Sets the primary_Fax value for this Participant.
     * 
     * @param primary_Fax
     */
    public void setPrimary_Fax(java.lang.String primary_Fax) {
        this.primary_Fax = primary_Fax;
    }


    /**
     * Gets the primary_Email value for this Participant.
     * 
     * @return primary_Email
     */
    public java.lang.String getPrimary_Email() {
        return primary_Email;
    }


    /**
     * Sets the primary_Email value for this Participant.
     * 
     * @param primary_Email
     */
    public void setPrimary_Email(java.lang.String primary_Email) {
        this.primary_Email = primary_Email;
    }


    /**
     * Gets the secondary_Address_1 value for this Participant.
     * 
     * @return secondary_Address_1
     */
    public java.lang.String getSecondary_Address_1() {
        return secondary_Address_1;
    }


    /**
     * Sets the secondary_Address_1 value for this Participant.
     * 
     * @param secondary_Address_1
     */
    public void setSecondary_Address_1(java.lang.String secondary_Address_1) {
        this.secondary_Address_1 = secondary_Address_1;
    }


    /**
     * Gets the secondary_Address_2 value for this Participant.
     * 
     * @return secondary_Address_2
     */
    public java.lang.String getSecondary_Address_2() {
        return secondary_Address_2;
    }


    /**
     * Sets the secondary_Address_2 value for this Participant.
     * 
     * @param secondary_Address_2
     */
    public void setSecondary_Address_2(java.lang.String secondary_Address_2) {
        this.secondary_Address_2 = secondary_Address_2;
    }


    /**
     * Gets the secondary_City value for this Participant.
     * 
     * @return secondary_City
     */
    public java.lang.String getSecondary_City() {
        return secondary_City;
    }


    /**
     * Sets the secondary_City value for this Participant.
     * 
     * @param secondary_City
     */
    public void setSecondary_City(java.lang.String secondary_City) {
        this.secondary_City = secondary_City;
    }


    /**
     * Gets the secondary_State value for this Participant.
     * 
     * @return secondary_State
     */
    public java.lang.String getSecondary_State() {
        return secondary_State;
    }


    /**
     * Sets the secondary_State value for this Participant.
     * 
     * @param secondary_State
     */
    public void setSecondary_State(java.lang.String secondary_State) {
        this.secondary_State = secondary_State;
    }


    /**
     * Gets the secondary_ZIP_Code value for this Participant.
     * 
     * @return secondary_ZIP_Code
     */
    public java.lang.String getSecondary_ZIP_Code() {
        return secondary_ZIP_Code;
    }


    /**
     * Sets the secondary_ZIP_Code value for this Participant.
     * 
     * @param secondary_ZIP_Code
     */
    public void setSecondary_ZIP_Code(java.lang.String secondary_ZIP_Code) {
        this.secondary_ZIP_Code = secondary_ZIP_Code;
    }


    /**
     * Gets the secondary_Country value for this Participant.
     * 
     * @return secondary_Country
     */
    public java.lang.String getSecondary_Country() {
        return secondary_Country;
    }


    /**
     * Sets the secondary_Country value for this Participant.
     * 
     * @param secondary_Country
     */
    public void setSecondary_Country(java.lang.String secondary_Country) {
        this.secondary_Country = secondary_Country;
    }


    /**
     * Gets the secondary_Phone value for this Participant.
     * 
     * @return secondary_Phone
     */
    public java.lang.String getSecondary_Phone() {
        return secondary_Phone;
    }


    /**
     * Sets the secondary_Phone value for this Participant.
     * 
     * @param secondary_Phone
     */
    public void setSecondary_Phone(java.lang.String secondary_Phone) {
        this.secondary_Phone = secondary_Phone;
    }


    /**
     * Gets the secondary_Fax value for this Participant.
     * 
     * @return secondary_Fax
     */
    public java.lang.String getSecondary_Fax() {
        return secondary_Fax;
    }


    /**
     * Sets the secondary_Fax value for this Participant.
     * 
     * @param secondary_Fax
     */
    public void setSecondary_Fax(java.lang.String secondary_Fax) {
        this.secondary_Fax = secondary_Fax;
    }


    /**
     * Gets the secondary_Email value for this Participant.
     * 
     * @return secondary_Email
     */
    public java.lang.String getSecondary_Email() {
        return secondary_Email;
    }


    /**
     * Sets the secondary_Email value for this Participant.
     * 
     * @param secondary_Email
     */
    public void setSecondary_Email(java.lang.String secondary_Email) {
        this.secondary_Email = secondary_Email;
    }


    /**
     * Gets the salutation value for this Participant.
     * 
     * @return salutation
     */
    public java.lang.String getSalutation() {
        return salutation;
    }


    /**
     * Sets the salutation value for this Participant.
     * 
     * @param salutation
     */
    public void setSalutation(java.lang.String salutation) {
        this.salutation = salutation;
    }


    /**
     * Gets the organization_Name value for this Participant.
     * 
     * @return organization_Name
     */
    public java.lang.String getOrganization_Name() {
        return organization_Name;
    }


    /**
     * Sets the organization_Name value for this Participant.
     * 
     * @param organization_Name
     */
    public void setOrganization_Name(java.lang.String organization_Name) {
        this.organization_Name = organization_Name;
    }


    /**
     * Gets the department value for this Participant.
     * 
     * @return department
     */
    public java.lang.String getDepartment() {
        return department;
    }


    /**
     * Sets the department value for this Participant.
     * 
     * @param department
     */
    public void setDepartment(java.lang.String department) {
        this.department = department;
    }


    /**
     * Gets the title value for this Participant.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this Participant.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the assistant_Name value for this Participant.
     * 
     * @return assistant_Name
     */
    public java.lang.String getAssistant_Name() {
        return assistant_Name;
    }


    /**
     * Sets the assistant_Name value for this Participant.
     * 
     * @param assistant_Name
     */
    public void setAssistant_Name(java.lang.String assistant_Name) {
        this.assistant_Name = assistant_Name;
    }


    /**
     * Gets the manager_Name value for this Participant.
     * 
     * @return manager_Name
     */
    public java.lang.String getManager_Name() {
        return manager_Name;
    }


    /**
     * Sets the manager_Name value for this Participant.
     * 
     * @param manager_Name
     */
    public void setManager_Name(java.lang.String manager_Name) {
        this.manager_Name = manager_Name;
    }


    /**
     * Gets the gender value for this Participant.
     * 
     * @return gender
     */
    public java.lang.String getGender() {
        return gender;
    }


    /**
     * Sets the gender value for this Participant.
     * 
     * @param gender
     */
    public void setGender(java.lang.String gender) {
        this.gender = gender;
    }


    /**
     * Gets the URL value for this Participant.
     * 
     * @return URL
     */
    public java.lang.String getURL() {
        return URL;
    }


    /**
     * Sets the URL value for this Participant.
     * 
     * @param URL
     */
    public void setURL(java.lang.String URL) {
        this.URL = URL;
    }


    /**
     * Gets the details value for this Participant.
     * 
     * @return details
     */
    public java.lang.String getDetails() {
        return details;
    }


    /**
     * Sets the details value for this Participant.
     * 
     * @param details
     */
    public void setDetails(java.lang.String details) {
        this.details = details;
    }


    /**
     * Gets the details_1 value for this Participant.
     * 
     * @return details_1
     */
    public java.lang.String getDetails_1() {
        return details_1;
    }


    /**
     * Sets the details_1 value for this Participant.
     * 
     * @param details_1
     */
    public void setDetails_1(java.lang.String details_1) {
        this.details_1 = details_1;
    }


    /**
     * Gets the details_2 value for this Participant.
     * 
     * @return details_2
     */
    public java.lang.String getDetails_2() {
        return details_2;
    }


    /**
     * Sets the details_2 value for this Participant.
     * 
     * @param details_2
     */
    public void setDetails_2(java.lang.String details_2) {
        this.details_2 = details_2;
    }


    /**
     * Gets the details_3 value for this Participant.
     * 
     * @return details_3
     */
    public java.lang.String getDetails_3() {
        return details_3;
    }


    /**
     * Sets the details_3 value for this Participant.
     * 
     * @param details_3
     */
    public void setDetails_3(java.lang.String details_3) {
        this.details_3 = details_3;
    }


    /**
     * Gets the details_4 value for this Participant.
     * 
     * @return details_4
     */
    public java.lang.String getDetails_4() {
        return details_4;
    }


    /**
     * Sets the details_4 value for this Participant.
     * 
     * @param details_4
     */
    public void setDetails_4(java.lang.String details_4) {
        this.details_4 = details_4;
    }


    /**
     * Gets the details_5 value for this Participant.
     * 
     * @return details_5
     */
    public java.lang.String getDetails_5() {
        return details_5;
    }


    /**
     * Sets the details_5 value for this Participant.
     * 
     * @param details_5
     */
    public void setDetails_5(java.lang.String details_5) {
        this.details_5 = details_5;
    }


    /**
     * Gets the details_6 value for this Participant.
     * 
     * @return details_6
     */
    public java.lang.String getDetails_6() {
        return details_6;
    }


    /**
     * Sets the details_6 value for this Participant.
     * 
     * @param details_6
     */
    public void setDetails_6(java.lang.String details_6) {
        this.details_6 = details_6;
    }


    /**
     * Gets the details_7 value for this Participant.
     * 
     * @return details_7
     */
    public java.lang.String getDetails_7() {
        return details_7;
    }


    /**
     * Sets the details_7 value for this Participant.
     * 
     * @param details_7
     */
    public void setDetails_7(java.lang.String details_7) {
        this.details_7 = details_7;
    }


    /**
     * Gets the details_8 value for this Participant.
     * 
     * @return details_8
     */
    public java.lang.String getDetails_8() {
        return details_8;
    }


    /**
     * Sets the details_8 value for this Participant.
     * 
     * @param details_8
     */
    public void setDetails_8(java.lang.String details_8) {
        this.details_8 = details_8;
    }


    /**
     * Gets the details_9 value for this Participant.
     * 
     * @return details_9
     */
    public java.lang.String getDetails_9() {
        return details_9;
    }


    /**
     * Sets the details_9 value for this Participant.
     * 
     * @param details_9
     */
    public void setDetails_9(java.lang.String details_9) {
        this.details_9 = details_9;
    }


    /**
     * Gets the details_10 value for this Participant.
     * 
     * @return details_10
     */
    public java.lang.String getDetails_10() {
        return details_10;
    }


    /**
     * Sets the details_10 value for this Participant.
     * 
     * @param details_10
     */
    public void setDetails_10(java.lang.String details_10) {
        this.details_10 = details_10;
    }


    /**
     * Gets the details_11 value for this Participant.
     * 
     * @return details_11
     */
    public java.lang.String getDetails_11() {
        return details_11;
    }


    /**
     * Sets the details_11 value for this Participant.
     * 
     * @param details_11
     */
    public void setDetails_11(java.lang.String details_11) {
        this.details_11 = details_11;
    }


    /**
     * Gets the details_12 value for this Participant.
     * 
     * @return details_12
     */
    public java.lang.String getDetails_12() {
        return details_12;
    }


    /**
     * Sets the details_12 value for this Participant.
     * 
     * @param details_12
     */
    public void setDetails_12(java.lang.String details_12) {
        this.details_12 = details_12;
    }


    /**
     * Gets the details_13 value for this Participant.
     * 
     * @return details_13
     */
    public java.lang.String getDetails_13() {
        return details_13;
    }


    /**
     * Sets the details_13 value for this Participant.
     * 
     * @param details_13
     */
    public void setDetails_13(java.lang.String details_13) {
        this.details_13 = details_13;
    }


    /**
     * Gets the details_14 value for this Participant.
     * 
     * @return details_14
     */
    public java.lang.String getDetails_14() {
        return details_14;
    }


    /**
     * Sets the details_14 value for this Participant.
     * 
     * @param details_14
     */
    public void setDetails_14(java.lang.String details_14) {
        this.details_14 = details_14;
    }


    /**
     * Gets the details_15 value for this Participant.
     * 
     * @return details_15
     */
    public java.lang.String getDetails_15() {
        return details_15;
    }


    /**
     * Sets the details_15 value for this Participant.
     * 
     * @param details_15
     */
    public void setDetails_15(java.lang.String details_15) {
        this.details_15 = details_15;
    }


    /**
     * Gets the details_16 value for this Participant.
     * 
     * @return details_16
     */
    public java.lang.String getDetails_16() {
        return details_16;
    }


    /**
     * Sets the details_16 value for this Participant.
     * 
     * @param details_16
     */
    public void setDetails_16(java.lang.String details_16) {
        this.details_16 = details_16;
    }


    /**
     * Gets the details_17 value for this Participant.
     * 
     * @return details_17
     */
    public java.lang.String getDetails_17() {
        return details_17;
    }


    /**
     * Sets the details_17 value for this Participant.
     * 
     * @param details_17
     */
    public void setDetails_17(java.lang.String details_17) {
        this.details_17 = details_17;
    }


    /**
     * Gets the details_18 value for this Participant.
     * 
     * @return details_18
     */
    public java.lang.String getDetails_18() {
        return details_18;
    }


    /**
     * Sets the details_18 value for this Participant.
     * 
     * @param details_18
     */
    public void setDetails_18(java.lang.String details_18) {
        this.details_18 = details_18;
    }


    /**
     * Gets the details_19 value for this Participant.
     * 
     * @return details_19
     */
    public java.lang.String getDetails_19() {
        return details_19;
    }


    /**
     * Sets the details_19 value for this Participant.
     * 
     * @param details_19
     */
    public void setDetails_19(java.lang.String details_19) {
        this.details_19 = details_19;
    }


    /**
     * Gets the details_20 value for this Participant.
     * 
     * @return details_20
     */
    public java.lang.String getDetails_20() {
        return details_20;
    }


    /**
     * Sets the details_20 value for this Participant.
     * 
     * @param details_20
     */
    public void setDetails_20(java.lang.String details_20) {
        this.details_20 = details_20;
    }


    /**
     * Gets the authenticate_Ext value for this Participant.
     * 
     * @return authenticate_Ext
     */
    public int getAuthenticate_Ext() {
        return authenticate_Ext;
    }


    /**
     * Sets the authenticate_Ext value for this Participant.
     * 
     * @param authenticate_Ext
     */
    public void setAuthenticate_Ext(int authenticate_Ext) {
        this.authenticate_Ext = authenticate_Ext;
    }


    /**
     * Gets the groupIDList value for this Participant.
     * 
     * @return groupIDList
     */
    public java.lang.String[] getGroupIDList() {
        return groupIDList;
    }


    /**
     * Sets the groupIDList value for this Participant.
     * 
     * @param groupIDList
     */
    public void setGroupIDList(java.lang.String[] groupIDList) {
        this.groupIDList = groupIDList;
    }


    /**
     * Gets the date_Registration value for this Participant.
     * 
     * @return date_Registration
     */
    public java.lang.String getDate_Registration() {
        return date_Registration;
    }


    /**
     * Sets the date_Registration value for this Participant.
     * 
     * @param date_Registration
     */
    public void setDate_Registration(java.lang.String date_Registration) {
        this.date_Registration = date_Registration;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Participant)) return false;
        Participant other = (Participant) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.participant_ID==null && other.getParticipant_ID()==null) || 
             (this.participant_ID!=null &&
              this.participant_ID.equals(other.getParticipant_ID()))) &&
            ((this.participant_Name==null && other.getParticipant_Name()==null) || 
             (this.participant_Name!=null &&
              this.participant_Name.equals(other.getParticipant_Name()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.first_Name==null && other.getFirst_Name()==null) || 
             (this.first_Name!=null &&
              this.first_Name.equals(other.getFirst_Name()))) &&
            ((this.last_Name==null && other.getLast_Name()==null) || 
             (this.last_Name!=null &&
              this.last_Name.equals(other.getLast_Name()))) &&
            ((this.middle_Name==null && other.getMiddle_Name()==null) || 
             (this.middle_Name!=null &&
              this.middle_Name.equals(other.getMiddle_Name()))) &&
            this.use_Correspondence == other.getUse_Correspondence() &&
            ((this.primary_Address_1==null && other.getPrimary_Address_1()==null) || 
             (this.primary_Address_1!=null &&
              this.primary_Address_1.equals(other.getPrimary_Address_1()))) &&
            ((this.primary_Address_2==null && other.getPrimary_Address_2()==null) || 
             (this.primary_Address_2!=null &&
              this.primary_Address_2.equals(other.getPrimary_Address_2()))) &&
            ((this.primary_City==null && other.getPrimary_City()==null) || 
             (this.primary_City!=null &&
              this.primary_City.equals(other.getPrimary_City()))) &&
            ((this.primary_State==null && other.getPrimary_State()==null) || 
             (this.primary_State!=null &&
              this.primary_State.equals(other.getPrimary_State()))) &&
            ((this.primary_ZIP_Code==null && other.getPrimary_ZIP_Code()==null) || 
             (this.primary_ZIP_Code!=null &&
              this.primary_ZIP_Code.equals(other.getPrimary_ZIP_Code()))) &&
            ((this.primary_Country==null && other.getPrimary_Country()==null) || 
             (this.primary_Country!=null &&
              this.primary_Country.equals(other.getPrimary_Country()))) &&
            ((this.primary_Phone==null && other.getPrimary_Phone()==null) || 
             (this.primary_Phone!=null &&
              this.primary_Phone.equals(other.getPrimary_Phone()))) &&
            ((this.primary_Fax==null && other.getPrimary_Fax()==null) || 
             (this.primary_Fax!=null &&
              this.primary_Fax.equals(other.getPrimary_Fax()))) &&
            ((this.primary_Email==null && other.getPrimary_Email()==null) || 
             (this.primary_Email!=null &&
              this.primary_Email.equals(other.getPrimary_Email()))) &&
            ((this.secondary_Address_1==null && other.getSecondary_Address_1()==null) || 
             (this.secondary_Address_1!=null &&
              this.secondary_Address_1.equals(other.getSecondary_Address_1()))) &&
            ((this.secondary_Address_2==null && other.getSecondary_Address_2()==null) || 
             (this.secondary_Address_2!=null &&
              this.secondary_Address_2.equals(other.getSecondary_Address_2()))) &&
            ((this.secondary_City==null && other.getSecondary_City()==null) || 
             (this.secondary_City!=null &&
              this.secondary_City.equals(other.getSecondary_City()))) &&
            ((this.secondary_State==null && other.getSecondary_State()==null) || 
             (this.secondary_State!=null &&
              this.secondary_State.equals(other.getSecondary_State()))) &&
            ((this.secondary_ZIP_Code==null && other.getSecondary_ZIP_Code()==null) || 
             (this.secondary_ZIP_Code!=null &&
              this.secondary_ZIP_Code.equals(other.getSecondary_ZIP_Code()))) &&
            ((this.secondary_Country==null && other.getSecondary_Country()==null) || 
             (this.secondary_Country!=null &&
              this.secondary_Country.equals(other.getSecondary_Country()))) &&
            ((this.secondary_Phone==null && other.getSecondary_Phone()==null) || 
             (this.secondary_Phone!=null &&
              this.secondary_Phone.equals(other.getSecondary_Phone()))) &&
            ((this.secondary_Fax==null && other.getSecondary_Fax()==null) || 
             (this.secondary_Fax!=null &&
              this.secondary_Fax.equals(other.getSecondary_Fax()))) &&
            ((this.secondary_Email==null && other.getSecondary_Email()==null) || 
             (this.secondary_Email!=null &&
              this.secondary_Email.equals(other.getSecondary_Email()))) &&
            ((this.salutation==null && other.getSalutation()==null) || 
             (this.salutation!=null &&
              this.salutation.equals(other.getSalutation()))) &&
            ((this.organization_Name==null && other.getOrganization_Name()==null) || 
             (this.organization_Name!=null &&
              this.organization_Name.equals(other.getOrganization_Name()))) &&
            ((this.department==null && other.getDepartment()==null) || 
             (this.department!=null &&
              this.department.equals(other.getDepartment()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.assistant_Name==null && other.getAssistant_Name()==null) || 
             (this.assistant_Name!=null &&
              this.assistant_Name.equals(other.getAssistant_Name()))) &&
            ((this.manager_Name==null && other.getManager_Name()==null) || 
             (this.manager_Name!=null &&
              this.manager_Name.equals(other.getManager_Name()))) &&
            ((this.gender==null && other.getGender()==null) || 
             (this.gender!=null &&
              this.gender.equals(other.getGender()))) &&
            ((this.URL==null && other.getURL()==null) || 
             (this.URL!=null &&
              this.URL.equals(other.getURL()))) &&
            ((this.details==null && other.getDetails()==null) || 
             (this.details!=null &&
              this.details.equals(other.getDetails()))) &&
            ((this.details_1==null && other.getDetails_1()==null) || 
             (this.details_1!=null &&
              this.details_1.equals(other.getDetails_1()))) &&
            ((this.details_2==null && other.getDetails_2()==null) || 
             (this.details_2!=null &&
              this.details_2.equals(other.getDetails_2()))) &&
            ((this.details_3==null && other.getDetails_3()==null) || 
             (this.details_3!=null &&
              this.details_3.equals(other.getDetails_3()))) &&
            ((this.details_4==null && other.getDetails_4()==null) || 
             (this.details_4!=null &&
              this.details_4.equals(other.getDetails_4()))) &&
            ((this.details_5==null && other.getDetails_5()==null) || 
             (this.details_5!=null &&
              this.details_5.equals(other.getDetails_5()))) &&
            ((this.details_6==null && other.getDetails_6()==null) || 
             (this.details_6!=null &&
              this.details_6.equals(other.getDetails_6()))) &&
            ((this.details_7==null && other.getDetails_7()==null) || 
             (this.details_7!=null &&
              this.details_7.equals(other.getDetails_7()))) &&
            ((this.details_8==null && other.getDetails_8()==null) || 
             (this.details_8!=null &&
              this.details_8.equals(other.getDetails_8()))) &&
            ((this.details_9==null && other.getDetails_9()==null) || 
             (this.details_9!=null &&
              this.details_9.equals(other.getDetails_9()))) &&
            ((this.details_10==null && other.getDetails_10()==null) || 
             (this.details_10!=null &&
              this.details_10.equals(other.getDetails_10()))) &&
            ((this.details_11==null && other.getDetails_11()==null) || 
             (this.details_11!=null &&
              this.details_11.equals(other.getDetails_11()))) &&
            ((this.details_12==null && other.getDetails_12()==null) || 
             (this.details_12!=null &&
              this.details_12.equals(other.getDetails_12()))) &&
            ((this.details_13==null && other.getDetails_13()==null) || 
             (this.details_13!=null &&
              this.details_13.equals(other.getDetails_13()))) &&
            ((this.details_14==null && other.getDetails_14()==null) || 
             (this.details_14!=null &&
              this.details_14.equals(other.getDetails_14()))) &&
            ((this.details_15==null && other.getDetails_15()==null) || 
             (this.details_15!=null &&
              this.details_15.equals(other.getDetails_15()))) &&
            ((this.details_16==null && other.getDetails_16()==null) || 
             (this.details_16!=null &&
              this.details_16.equals(other.getDetails_16()))) &&
            ((this.details_17==null && other.getDetails_17()==null) || 
             (this.details_17!=null &&
              this.details_17.equals(other.getDetails_17()))) &&
            ((this.details_18==null && other.getDetails_18()==null) || 
             (this.details_18!=null &&
              this.details_18.equals(other.getDetails_18()))) &&
            ((this.details_19==null && other.getDetails_19()==null) || 
             (this.details_19!=null &&
              this.details_19.equals(other.getDetails_19()))) &&
            ((this.details_20==null && other.getDetails_20()==null) || 
             (this.details_20!=null &&
              this.details_20.equals(other.getDetails_20()))) &&
            this.authenticate_Ext == other.getAuthenticate_Ext() &&
            ((this.groupIDList==null && other.getGroupIDList()==null) || 
             (this.groupIDList!=null &&
              java.util.Arrays.equals(this.groupIDList, other.getGroupIDList()))) &&
            ((this.date_Registration==null && other.getDate_Registration()==null) || 
             (this.date_Registration!=null &&
              this.date_Registration.equals(other.getDate_Registration())));
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
        if (getParticipant_ID() != null) {
            _hashCode += getParticipant_ID().hashCode();
        }
        if (getParticipant_Name() != null) {
            _hashCode += getParticipant_Name().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getFirst_Name() != null) {
            _hashCode += getFirst_Name().hashCode();
        }
        if (getLast_Name() != null) {
            _hashCode += getLast_Name().hashCode();
        }
        if (getMiddle_Name() != null) {
            _hashCode += getMiddle_Name().hashCode();
        }
        _hashCode += getUse_Correspondence();
        if (getPrimary_Address_1() != null) {
            _hashCode += getPrimary_Address_1().hashCode();
        }
        if (getPrimary_Address_2() != null) {
            _hashCode += getPrimary_Address_2().hashCode();
        }
        if (getPrimary_City() != null) {
            _hashCode += getPrimary_City().hashCode();
        }
        if (getPrimary_State() != null) {
            _hashCode += getPrimary_State().hashCode();
        }
        if (getPrimary_ZIP_Code() != null) {
            _hashCode += getPrimary_ZIP_Code().hashCode();
        }
        if (getPrimary_Country() != null) {
            _hashCode += getPrimary_Country().hashCode();
        }
        if (getPrimary_Phone() != null) {
            _hashCode += getPrimary_Phone().hashCode();
        }
        if (getPrimary_Fax() != null) {
            _hashCode += getPrimary_Fax().hashCode();
        }
        if (getPrimary_Email() != null) {
            _hashCode += getPrimary_Email().hashCode();
        }
        if (getSecondary_Address_1() != null) {
            _hashCode += getSecondary_Address_1().hashCode();
        }
        if (getSecondary_Address_2() != null) {
            _hashCode += getSecondary_Address_2().hashCode();
        }
        if (getSecondary_City() != null) {
            _hashCode += getSecondary_City().hashCode();
        }
        if (getSecondary_State() != null) {
            _hashCode += getSecondary_State().hashCode();
        }
        if (getSecondary_ZIP_Code() != null) {
            _hashCode += getSecondary_ZIP_Code().hashCode();
        }
        if (getSecondary_Country() != null) {
            _hashCode += getSecondary_Country().hashCode();
        }
        if (getSecondary_Phone() != null) {
            _hashCode += getSecondary_Phone().hashCode();
        }
        if (getSecondary_Fax() != null) {
            _hashCode += getSecondary_Fax().hashCode();
        }
        if (getSecondary_Email() != null) {
            _hashCode += getSecondary_Email().hashCode();
        }
        if (getSalutation() != null) {
            _hashCode += getSalutation().hashCode();
        }
        if (getOrganization_Name() != null) {
            _hashCode += getOrganization_Name().hashCode();
        }
        if (getDepartment() != null) {
            _hashCode += getDepartment().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getAssistant_Name() != null) {
            _hashCode += getAssistant_Name().hashCode();
        }
        if (getManager_Name() != null) {
            _hashCode += getManager_Name().hashCode();
        }
        if (getGender() != null) {
            _hashCode += getGender().hashCode();
        }
        if (getURL() != null) {
            _hashCode += getURL().hashCode();
        }
        if (getDetails() != null) {
            _hashCode += getDetails().hashCode();
        }
        if (getDetails_1() != null) {
            _hashCode += getDetails_1().hashCode();
        }
        if (getDetails_2() != null) {
            _hashCode += getDetails_2().hashCode();
        }
        if (getDetails_3() != null) {
            _hashCode += getDetails_3().hashCode();
        }
        if (getDetails_4() != null) {
            _hashCode += getDetails_4().hashCode();
        }
        if (getDetails_5() != null) {
            _hashCode += getDetails_5().hashCode();
        }
        if (getDetails_6() != null) {
            _hashCode += getDetails_6().hashCode();
        }
        if (getDetails_7() != null) {
            _hashCode += getDetails_7().hashCode();
        }
        if (getDetails_8() != null) {
            _hashCode += getDetails_8().hashCode();
        }
        if (getDetails_9() != null) {
            _hashCode += getDetails_9().hashCode();
        }
        if (getDetails_10() != null) {
            _hashCode += getDetails_10().hashCode();
        }
        if (getDetails_11() != null) {
            _hashCode += getDetails_11().hashCode();
        }
        if (getDetails_12() != null) {
            _hashCode += getDetails_12().hashCode();
        }
        if (getDetails_13() != null) {
            _hashCode += getDetails_13().hashCode();
        }
        if (getDetails_14() != null) {
            _hashCode += getDetails_14().hashCode();
        }
        if (getDetails_15() != null) {
            _hashCode += getDetails_15().hashCode();
        }
        if (getDetails_16() != null) {
            _hashCode += getDetails_16().hashCode();
        }
        if (getDetails_17() != null) {
            _hashCode += getDetails_17().hashCode();
        }
        if (getDetails_18() != null) {
            _hashCode += getDetails_18().hashCode();
        }
        if (getDetails_19() != null) {
            _hashCode += getDetails_19().hashCode();
        }
        if (getDetails_20() != null) {
            _hashCode += getDetails_20().hashCode();
        }
        _hashCode += getAuthenticate_Ext();
        if (getGroupIDList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGroupIDList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGroupIDList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDate_Registration() != null) {
            _hashCode += getDate_Registration().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Participant.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("participant_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("first_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "First_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("last_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Last_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("middle_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Middle_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("use_Correspondence");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Use_Correspondence"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primary_Address_1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Primary_Address_1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primary_Address_2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Primary_Address_2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primary_City");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Primary_City"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primary_State");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Primary_State"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primary_ZIP_Code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Primary_ZIP_Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primary_Country");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Primary_Country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primary_Phone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Primary_Phone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primary_Fax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Primary_Fax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primary_Email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Primary_Email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondary_Address_1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Secondary_Address_1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondary_Address_2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Secondary_Address_2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondary_City");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Secondary_City"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondary_State");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Secondary_State"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondary_ZIP_Code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Secondary_ZIP_Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondary_Country");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Secondary_Country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondary_Phone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Secondary_Phone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondary_Fax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Secondary_Fax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondary_Email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Secondary_Email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("salutation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Salutation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organization_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Organization_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("department");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Department"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assistant_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Assistant_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manager_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Manager_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gender");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Gender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("URL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "URL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_6");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_7");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_7"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_8");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_8"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_9");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_10");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_10"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_11");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_11"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_12");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_12"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_13");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_13"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_14");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_14"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_15");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_15"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_16");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_16"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_17");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_17"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_18");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_19");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_19"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details_20");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Details_20"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authenticate_Ext");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Authenticate_Ext"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupIDList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "GroupIDList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Group_ID"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date_Registration");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Date_Registration"));
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
