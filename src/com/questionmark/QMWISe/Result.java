/**
 * Result.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public class Result  implements java.io.Serializable {
    private java.lang.String result_ID;

    private java.lang.String assessment_ID;

    private boolean write_Answer_Data;

    private java.lang.String participant;

    private java.lang.String member_Group;

    private java.lang.String participant_Details;

    private java.lang.String hostname;

    private java.lang.String IP_Address;

    private boolean still_Going;

    private short status;

    private short feedback;

    private short number_Sections;

    private int max_Score;

    private int total_Score;

    private java.lang.String special_1;

    private java.lang.String special_2;

    private java.lang.String special_3;

    private java.lang.String special_4;

    private java.lang.String special_5;

    private java.lang.String special_6;

    private java.lang.String special_7;

    private java.lang.String special_8;

    private java.lang.String special_9;

    private java.lang.String special_10;

    private int time_Taken;

    private java.lang.String score_Band_Title;

    private int score_Band_Number;

    private short percentage_Score;

    private java.lang.String schedule_Name;

    private boolean monitored;

    private java.lang.String monitor_Name;

    private com.questionmark.QMWISe.TopicScoring[] topicScoringList;

    private java.lang.String when_Started;

    private java.lang.String session_Last_Modified;

    private java.lang.String when_Finished;

    public Result() {
    }

    public Result(
           java.lang.String result_ID,
           java.lang.String assessment_ID,
           boolean write_Answer_Data,
           java.lang.String participant,
           java.lang.String member_Group,
           java.lang.String participant_Details,
           java.lang.String hostname,
           java.lang.String IP_Address,
           boolean still_Going,
           short status,
           short feedback,
           short number_Sections,
           int max_Score,
           int total_Score,
           java.lang.String special_1,
           java.lang.String special_2,
           java.lang.String special_3,
           java.lang.String special_4,
           java.lang.String special_5,
           java.lang.String special_6,
           java.lang.String special_7,
           java.lang.String special_8,
           java.lang.String special_9,
           java.lang.String special_10,
           int time_Taken,
           java.lang.String score_Band_Title,
           int score_Band_Number,
           short percentage_Score,
           java.lang.String schedule_Name,
           boolean monitored,
           java.lang.String monitor_Name,
           com.questionmark.QMWISe.TopicScoring[] topicScoringList,
           java.lang.String when_Started,
           java.lang.String session_Last_Modified,
           java.lang.String when_Finished) {
           this.result_ID = result_ID;
           this.assessment_ID = assessment_ID;
           this.write_Answer_Data = write_Answer_Data;
           this.participant = participant;
           this.member_Group = member_Group;
           this.participant_Details = participant_Details;
           this.hostname = hostname;
           this.IP_Address = IP_Address;
           this.still_Going = still_Going;
           this.status = status;
           this.feedback = feedback;
           this.number_Sections = number_Sections;
           this.max_Score = max_Score;
           this.total_Score = total_Score;
           this.special_1 = special_1;
           this.special_2 = special_2;
           this.special_3 = special_3;
           this.special_4 = special_4;
           this.special_5 = special_5;
           this.special_6 = special_6;
           this.special_7 = special_7;
           this.special_8 = special_8;
           this.special_9 = special_9;
           this.special_10 = special_10;
           this.time_Taken = time_Taken;
           this.score_Band_Title = score_Band_Title;
           this.score_Band_Number = score_Band_Number;
           this.percentage_Score = percentage_Score;
           this.schedule_Name = schedule_Name;
           this.monitored = monitored;
           this.monitor_Name = monitor_Name;
           this.topicScoringList = topicScoringList;
           this.when_Started = when_Started;
           this.session_Last_Modified = session_Last_Modified;
           this.when_Finished = when_Finished;
    }


    /**
     * Gets the result_ID value for this Result.
     * 
     * @return result_ID
     */
    public java.lang.String getResult_ID() {
        return result_ID;
    }


    /**
     * Sets the result_ID value for this Result.
     * 
     * @param result_ID
     */
    public void setResult_ID(java.lang.String result_ID) {
        this.result_ID = result_ID;
    }


    /**
     * Gets the assessment_ID value for this Result.
     * 
     * @return assessment_ID
     */
    public java.lang.String getAssessment_ID() {
        return assessment_ID;
    }


    /**
     * Sets the assessment_ID value for this Result.
     * 
     * @param assessment_ID
     */
    public void setAssessment_ID(java.lang.String assessment_ID) {
        this.assessment_ID = assessment_ID;
    }


    /**
     * Gets the write_Answer_Data value for this Result.
     * 
     * @return write_Answer_Data
     */
    public boolean isWrite_Answer_Data() {
        return write_Answer_Data;
    }


    /**
     * Sets the write_Answer_Data value for this Result.
     * 
     * @param write_Answer_Data
     */
    public void setWrite_Answer_Data(boolean write_Answer_Data) {
        this.write_Answer_Data = write_Answer_Data;
    }


    /**
     * Gets the participant value for this Result.
     * 
     * @return participant
     */
    public java.lang.String getParticipant() {
        return participant;
    }


    /**
     * Sets the participant value for this Result.
     * 
     * @param participant
     */
    public void setParticipant(java.lang.String participant) {
        this.participant = participant;
    }


    /**
     * Gets the member_Group value for this Result.
     * 
     * @return member_Group
     */
    public java.lang.String getMember_Group() {
        return member_Group;
    }


    /**
     * Sets the member_Group value for this Result.
     * 
     * @param member_Group
     */
    public void setMember_Group(java.lang.String member_Group) {
        this.member_Group = member_Group;
    }


    /**
     * Gets the participant_Details value for this Result.
     * 
     * @return participant_Details
     */
    public java.lang.String getParticipant_Details() {
        return participant_Details;
    }


    /**
     * Sets the participant_Details value for this Result.
     * 
     * @param participant_Details
     */
    public void setParticipant_Details(java.lang.String participant_Details) {
        this.participant_Details = participant_Details;
    }


    /**
     * Gets the hostname value for this Result.
     * 
     * @return hostname
     */
    public java.lang.String getHostname() {
        return hostname;
    }


    /**
     * Sets the hostname value for this Result.
     * 
     * @param hostname
     */
    public void setHostname(java.lang.String hostname) {
        this.hostname = hostname;
    }


    /**
     * Gets the IP_Address value for this Result.
     * 
     * @return IP_Address
     */
    public java.lang.String getIP_Address() {
        return IP_Address;
    }


    /**
     * Sets the IP_Address value for this Result.
     * 
     * @param IP_Address
     */
    public void setIP_Address(java.lang.String IP_Address) {
        this.IP_Address = IP_Address;
    }


    /**
     * Gets the still_Going value for this Result.
     * 
     * @return still_Going
     */
    public boolean isStill_Going() {
        return still_Going;
    }


    /**
     * Sets the still_Going value for this Result.
     * 
     * @param still_Going
     */
    public void setStill_Going(boolean still_Going) {
        this.still_Going = still_Going;
    }


    /**
     * Gets the status value for this Result.
     * 
     * @return status
     */
    public short getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Result.
     * 
     * @param status
     */
    public void setStatus(short status) {
        this.status = status;
    }


    /**
     * Gets the feedback value for this Result.
     * 
     * @return feedback
     */
    public short getFeedback() {
        return feedback;
    }


    /**
     * Sets the feedback value for this Result.
     * 
     * @param feedback
     */
    public void setFeedback(short feedback) {
        this.feedback = feedback;
    }


    /**
     * Gets the number_Sections value for this Result.
     * 
     * @return number_Sections
     */
    public short getNumber_Sections() {
        return number_Sections;
    }


    /**
     * Sets the number_Sections value for this Result.
     * 
     * @param number_Sections
     */
    public void setNumber_Sections(short number_Sections) {
        this.number_Sections = number_Sections;
    }


    /**
     * Gets the max_Score value for this Result.
     * 
     * @return max_Score
     */
    public int getMax_Score() {
        return max_Score;
    }


    /**
     * Sets the max_Score value for this Result.
     * 
     * @param max_Score
     */
    public void setMax_Score(int max_Score) {
        this.max_Score = max_Score;
    }


    /**
     * Gets the total_Score value for this Result.
     * 
     * @return total_Score
     */
    public int getTotal_Score() {
        return total_Score;
    }


    /**
     * Sets the total_Score value for this Result.
     * 
     * @param total_Score
     */
    public void setTotal_Score(int total_Score) {
        this.total_Score = total_Score;
    }


    /**
     * Gets the special_1 value for this Result.
     * 
     * @return special_1
     */
    public java.lang.String getSpecial_1() {
        return special_1;
    }


    /**
     * Sets the special_1 value for this Result.
     * 
     * @param special_1
     */
    public void setSpecial_1(java.lang.String special_1) {
        this.special_1 = special_1;
    }


    /**
     * Gets the special_2 value for this Result.
     * 
     * @return special_2
     */
    public java.lang.String getSpecial_2() {
        return special_2;
    }


    /**
     * Sets the special_2 value for this Result.
     * 
     * @param special_2
     */
    public void setSpecial_2(java.lang.String special_2) {
        this.special_2 = special_2;
    }


    /**
     * Gets the special_3 value for this Result.
     * 
     * @return special_3
     */
    public java.lang.String getSpecial_3() {
        return special_3;
    }


    /**
     * Sets the special_3 value for this Result.
     * 
     * @param special_3
     */
    public void setSpecial_3(java.lang.String special_3) {
        this.special_3 = special_3;
    }


    /**
     * Gets the special_4 value for this Result.
     * 
     * @return special_4
     */
    public java.lang.String getSpecial_4() {
        return special_4;
    }


    /**
     * Sets the special_4 value for this Result.
     * 
     * @param special_4
     */
    public void setSpecial_4(java.lang.String special_4) {
        this.special_4 = special_4;
    }


    /**
     * Gets the special_5 value for this Result.
     * 
     * @return special_5
     */
    public java.lang.String getSpecial_5() {
        return special_5;
    }


    /**
     * Sets the special_5 value for this Result.
     * 
     * @param special_5
     */
    public void setSpecial_5(java.lang.String special_5) {
        this.special_5 = special_5;
    }


    /**
     * Gets the special_6 value for this Result.
     * 
     * @return special_6
     */
    public java.lang.String getSpecial_6() {
        return special_6;
    }


    /**
     * Sets the special_6 value for this Result.
     * 
     * @param special_6
     */
    public void setSpecial_6(java.lang.String special_6) {
        this.special_6 = special_6;
    }


    /**
     * Gets the special_7 value for this Result.
     * 
     * @return special_7
     */
    public java.lang.String getSpecial_7() {
        return special_7;
    }


    /**
     * Sets the special_7 value for this Result.
     * 
     * @param special_7
     */
    public void setSpecial_7(java.lang.String special_7) {
        this.special_7 = special_7;
    }


    /**
     * Gets the special_8 value for this Result.
     * 
     * @return special_8
     */
    public java.lang.String getSpecial_8() {
        return special_8;
    }


    /**
     * Sets the special_8 value for this Result.
     * 
     * @param special_8
     */
    public void setSpecial_8(java.lang.String special_8) {
        this.special_8 = special_8;
    }


    /**
     * Gets the special_9 value for this Result.
     * 
     * @return special_9
     */
    public java.lang.String getSpecial_9() {
        return special_9;
    }


    /**
     * Sets the special_9 value for this Result.
     * 
     * @param special_9
     */
    public void setSpecial_9(java.lang.String special_9) {
        this.special_9 = special_9;
    }


    /**
     * Gets the special_10 value for this Result.
     * 
     * @return special_10
     */
    public java.lang.String getSpecial_10() {
        return special_10;
    }


    /**
     * Sets the special_10 value for this Result.
     * 
     * @param special_10
     */
    public void setSpecial_10(java.lang.String special_10) {
        this.special_10 = special_10;
    }


    /**
     * Gets the time_Taken value for this Result.
     * 
     * @return time_Taken
     */
    public int getTime_Taken() {
        return time_Taken;
    }


    /**
     * Sets the time_Taken value for this Result.
     * 
     * @param time_Taken
     */
    public void setTime_Taken(int time_Taken) {
        this.time_Taken = time_Taken;
    }


    /**
     * Gets the score_Band_Title value for this Result.
     * 
     * @return score_Band_Title
     */
    public java.lang.String getScore_Band_Title() {
        return score_Band_Title;
    }


    /**
     * Sets the score_Band_Title value for this Result.
     * 
     * @param score_Band_Title
     */
    public void setScore_Band_Title(java.lang.String score_Band_Title) {
        this.score_Band_Title = score_Band_Title;
    }


    /**
     * Gets the score_Band_Number value for this Result.
     * 
     * @return score_Band_Number
     */
    public int getScore_Band_Number() {
        return score_Band_Number;
    }


    /**
     * Sets the score_Band_Number value for this Result.
     * 
     * @param score_Band_Number
     */
    public void setScore_Band_Number(int score_Band_Number) {
        this.score_Band_Number = score_Band_Number;
    }


    /**
     * Gets the percentage_Score value for this Result.
     * 
     * @return percentage_Score
     */
    public short getPercentage_Score() {
        return percentage_Score;
    }


    /**
     * Sets the percentage_Score value for this Result.
     * 
     * @param percentage_Score
     */
    public void setPercentage_Score(short percentage_Score) {
        this.percentage_Score = percentage_Score;
    }


    /**
     * Gets the schedule_Name value for this Result.
     * 
     * @return schedule_Name
     */
    public java.lang.String getSchedule_Name() {
        return schedule_Name;
    }


    /**
     * Sets the schedule_Name value for this Result.
     * 
     * @param schedule_Name
     */
    public void setSchedule_Name(java.lang.String schedule_Name) {
        this.schedule_Name = schedule_Name;
    }


    /**
     * Gets the monitored value for this Result.
     * 
     * @return monitored
     */
    public boolean isMonitored() {
        return monitored;
    }


    /**
     * Sets the monitored value for this Result.
     * 
     * @param monitored
     */
    public void setMonitored(boolean monitored) {
        this.monitored = monitored;
    }


    /**
     * Gets the monitor_Name value for this Result.
     * 
     * @return monitor_Name
     */
    public java.lang.String getMonitor_Name() {
        return monitor_Name;
    }


    /**
     * Sets the monitor_Name value for this Result.
     * 
     * @param monitor_Name
     */
    public void setMonitor_Name(java.lang.String monitor_Name) {
        this.monitor_Name = monitor_Name;
    }


    /**
     * Gets the topicScoringList value for this Result.
     * 
     * @return topicScoringList
     */
    public com.questionmark.QMWISe.TopicScoring[] getTopicScoringList() {
        return topicScoringList;
    }


    /**
     * Sets the topicScoringList value for this Result.
     * 
     * @param topicScoringList
     */
    public void setTopicScoringList(com.questionmark.QMWISe.TopicScoring[] topicScoringList) {
        this.topicScoringList = topicScoringList;
    }


    /**
     * Gets the when_Started value for this Result.
     * 
     * @return when_Started
     */
    public java.lang.String getWhen_Started() {
        return when_Started;
    }


    /**
     * Sets the when_Started value for this Result.
     * 
     * @param when_Started
     */
    public void setWhen_Started(java.lang.String when_Started) {
        this.when_Started = when_Started;
    }


    /**
     * Gets the session_Last_Modified value for this Result.
     * 
     * @return session_Last_Modified
     */
    public java.lang.String getSession_Last_Modified() {
        return session_Last_Modified;
    }


    /**
     * Sets the session_Last_Modified value for this Result.
     * 
     * @param session_Last_Modified
     */
    public void setSession_Last_Modified(java.lang.String session_Last_Modified) {
        this.session_Last_Modified = session_Last_Modified;
    }


    /**
     * Gets the when_Finished value for this Result.
     * 
     * @return when_Finished
     */
    public java.lang.String getWhen_Finished() {
        return when_Finished;
    }


    /**
     * Sets the when_Finished value for this Result.
     * 
     * @param when_Finished
     */
    public void setWhen_Finished(java.lang.String when_Finished) {
        this.when_Finished = when_Finished;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Result)) return false;
        Result other = (Result) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.result_ID==null && other.getResult_ID()==null) || 
             (this.result_ID!=null &&
              this.result_ID.equals(other.getResult_ID()))) &&
            ((this.assessment_ID==null && other.getAssessment_ID()==null) || 
             (this.assessment_ID!=null &&
              this.assessment_ID.equals(other.getAssessment_ID()))) &&
            this.write_Answer_Data == other.isWrite_Answer_Data() &&
            ((this.participant==null && other.getParticipant()==null) || 
             (this.participant!=null &&
              this.participant.equals(other.getParticipant()))) &&
            ((this.member_Group==null && other.getMember_Group()==null) || 
             (this.member_Group!=null &&
              this.member_Group.equals(other.getMember_Group()))) &&
            ((this.participant_Details==null && other.getParticipant_Details()==null) || 
             (this.participant_Details!=null &&
              this.participant_Details.equals(other.getParticipant_Details()))) &&
            ((this.hostname==null && other.getHostname()==null) || 
             (this.hostname!=null &&
              this.hostname.equals(other.getHostname()))) &&
            ((this.IP_Address==null && other.getIP_Address()==null) || 
             (this.IP_Address!=null &&
              this.IP_Address.equals(other.getIP_Address()))) &&
            this.still_Going == other.isStill_Going() &&
            this.status == other.getStatus() &&
            this.feedback == other.getFeedback() &&
            this.number_Sections == other.getNumber_Sections() &&
            this.max_Score == other.getMax_Score() &&
            this.total_Score == other.getTotal_Score() &&
            ((this.special_1==null && other.getSpecial_1()==null) || 
             (this.special_1!=null &&
              this.special_1.equals(other.getSpecial_1()))) &&
            ((this.special_2==null && other.getSpecial_2()==null) || 
             (this.special_2!=null &&
              this.special_2.equals(other.getSpecial_2()))) &&
            ((this.special_3==null && other.getSpecial_3()==null) || 
             (this.special_3!=null &&
              this.special_3.equals(other.getSpecial_3()))) &&
            ((this.special_4==null && other.getSpecial_4()==null) || 
             (this.special_4!=null &&
              this.special_4.equals(other.getSpecial_4()))) &&
            ((this.special_5==null && other.getSpecial_5()==null) || 
             (this.special_5!=null &&
              this.special_5.equals(other.getSpecial_5()))) &&
            ((this.special_6==null && other.getSpecial_6()==null) || 
             (this.special_6!=null &&
              this.special_6.equals(other.getSpecial_6()))) &&
            ((this.special_7==null && other.getSpecial_7()==null) || 
             (this.special_7!=null &&
              this.special_7.equals(other.getSpecial_7()))) &&
            ((this.special_8==null && other.getSpecial_8()==null) || 
             (this.special_8!=null &&
              this.special_8.equals(other.getSpecial_8()))) &&
            ((this.special_9==null && other.getSpecial_9()==null) || 
             (this.special_9!=null &&
              this.special_9.equals(other.getSpecial_9()))) &&
            ((this.special_10==null && other.getSpecial_10()==null) || 
             (this.special_10!=null &&
              this.special_10.equals(other.getSpecial_10()))) &&
            this.time_Taken == other.getTime_Taken() &&
            ((this.score_Band_Title==null && other.getScore_Band_Title()==null) || 
             (this.score_Band_Title!=null &&
              this.score_Band_Title.equals(other.getScore_Band_Title()))) &&
            this.score_Band_Number == other.getScore_Band_Number() &&
            this.percentage_Score == other.getPercentage_Score() &&
            ((this.schedule_Name==null && other.getSchedule_Name()==null) || 
             (this.schedule_Name!=null &&
              this.schedule_Name.equals(other.getSchedule_Name()))) &&
            this.monitored == other.isMonitored() &&
            ((this.monitor_Name==null && other.getMonitor_Name()==null) || 
             (this.monitor_Name!=null &&
              this.monitor_Name.equals(other.getMonitor_Name()))) &&
            ((this.topicScoringList==null && other.getTopicScoringList()==null) || 
             (this.topicScoringList!=null &&
              java.util.Arrays.equals(this.topicScoringList, other.getTopicScoringList()))) &&
            ((this.when_Started==null && other.getWhen_Started()==null) || 
             (this.when_Started!=null &&
              this.when_Started.equals(other.getWhen_Started()))) &&
            ((this.session_Last_Modified==null && other.getSession_Last_Modified()==null) || 
             (this.session_Last_Modified!=null &&
              this.session_Last_Modified.equals(other.getSession_Last_Modified()))) &&
            ((this.when_Finished==null && other.getWhen_Finished()==null) || 
             (this.when_Finished!=null &&
              this.when_Finished.equals(other.getWhen_Finished())));
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
        if (getResult_ID() != null) {
            _hashCode += getResult_ID().hashCode();
        }
        if (getAssessment_ID() != null) {
            _hashCode += getAssessment_ID().hashCode();
        }
        _hashCode += (isWrite_Answer_Data() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getParticipant() != null) {
            _hashCode += getParticipant().hashCode();
        }
        if (getMember_Group() != null) {
            _hashCode += getMember_Group().hashCode();
        }
        if (getParticipant_Details() != null) {
            _hashCode += getParticipant_Details().hashCode();
        }
        if (getHostname() != null) {
            _hashCode += getHostname().hashCode();
        }
        if (getIP_Address() != null) {
            _hashCode += getIP_Address().hashCode();
        }
        _hashCode += (isStill_Going() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getStatus();
        _hashCode += getFeedback();
        _hashCode += getNumber_Sections();
        _hashCode += getMax_Score();
        _hashCode += getTotal_Score();
        if (getSpecial_1() != null) {
            _hashCode += getSpecial_1().hashCode();
        }
        if (getSpecial_2() != null) {
            _hashCode += getSpecial_2().hashCode();
        }
        if (getSpecial_3() != null) {
            _hashCode += getSpecial_3().hashCode();
        }
        if (getSpecial_4() != null) {
            _hashCode += getSpecial_4().hashCode();
        }
        if (getSpecial_5() != null) {
            _hashCode += getSpecial_5().hashCode();
        }
        if (getSpecial_6() != null) {
            _hashCode += getSpecial_6().hashCode();
        }
        if (getSpecial_7() != null) {
            _hashCode += getSpecial_7().hashCode();
        }
        if (getSpecial_8() != null) {
            _hashCode += getSpecial_8().hashCode();
        }
        if (getSpecial_9() != null) {
            _hashCode += getSpecial_9().hashCode();
        }
        if (getSpecial_10() != null) {
            _hashCode += getSpecial_10().hashCode();
        }
        _hashCode += getTime_Taken();
        if (getScore_Band_Title() != null) {
            _hashCode += getScore_Band_Title().hashCode();
        }
        _hashCode += getScore_Band_Number();
        _hashCode += getPercentage_Score();
        if (getSchedule_Name() != null) {
            _hashCode += getSchedule_Name().hashCode();
        }
        _hashCode += (isMonitored() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMonitor_Name() != null) {
            _hashCode += getMonitor_Name().hashCode();
        }
        if (getTopicScoringList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTopicScoringList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTopicScoringList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getWhen_Started() != null) {
            _hashCode += getWhen_Started().hashCode();
        }
        if (getSession_Last_Modified() != null) {
            _hashCode += getSession_Last_Modified().hashCode();
        }
        if (getWhen_Finished() != null) {
            _hashCode += getWhen_Finished().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Result.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("result_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Result_ID"));
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
        elemField.setFieldName("write_Answer_Data");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Write_Answer_Data"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("participant");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("member_Group");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Member_Group"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("participant_Details");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Participant_Details"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hostname");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Hostname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IP_Address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "IP_Address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("still_Going");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Still_Going"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("feedback");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Feedback"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number_Sections");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Number_Sections"));
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
        elemField.setFieldName("total_Score");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Total_Score"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_6");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_7");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_7"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_8");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_8"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_9");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("special_10");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Special_10"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("time_Taken");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Time_Taken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("score_Band_Title");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Score_Band_Title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("score_Band_Number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Score_Band_Number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percentage_Score");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Percentage_Score"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
        elemField.setFieldName("monitored");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Monitored"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monitor_Name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Monitor_Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("topicScoringList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "TopicScoringList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "TopicScoring"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "TopicScoring"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("when_Started");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "When_Started"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("session_Last_Modified");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "Session_Last_Modified"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("when_Finished");
        elemField.setXmlName(new javax.xml.namespace.QName("http://questionmark.com/QMWISe/", "When_Finished"));
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
