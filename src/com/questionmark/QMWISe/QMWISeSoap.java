/**
 * QMWISeSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.questionmark.QMWISe;

public interface QMWISeSoap extends java.rmi.Remote {

    /**
     * This method provides information about the QMWISe software
     * that is installed, including the version of the software that is installed,
     * which build of the software is installed and a statement of Question
     * Mark's rights under the license agreement.
     */
    public com.questionmark.QMWISe.Version getAbout() throws java.rmi.RemoteException;

    /**
     * This method provides information about the QMWISe software
     * that is installed, including the version of the software that is installed,
     * which build of the software is installed, licensing information and
     * a statement of Question Mark's rights under the license agreement.
     */
    public com.questionmark.QMWISe.Version2 getAbout2() throws java.rmi.RemoteException;

    /**
     * This method returns all current configuration settings contained
     * in the file Web.config.
     */
    public com.questionmark.QMWISe.Configuration[] getConfiguration() throws java.rmi.RemoteException;

    /**
     * This method returns all current configuration settings contained
     * in the file Web.config after reading them from the PerceptionV4.ini
     * file.
     */
    public com.questionmark.QMWISe.Configuration[] setConfiguration() throws java.rmi.RemoteException;

    /**
     * This method processes a Participant ID and returns a list giving
     * the the full details of the participant and their group memberships
     * (specified by Group ID) if the Participant ID exists in the database.
     */
    public com.questionmark.QMWISe.Participant getParticipant(java.lang.String participant_ID) throws java.rmi.RemoteException;

    /**
     * This method processes a Participant Name and returns a list
     * giving the the full details of the participant and their group memberships
     * (specified by Group ID) if the Participant ID exists in the database.
     */
    public com.questionmark.QMWISe.Participant getParticipantByName(java.lang.String participant_Name) throws java.rmi.RemoteException;

    /**
     * This method returns a list giving the full details of all the
     * participants in the database. Each set of participant details that
     * is returned will include a list of the groups (specified by Group
     * ID) of which the participant is a member.
     */
    public com.questionmark.QMWISe.Participant[] getParticipantList() throws java.rmi.RemoteException;

    /**
     * This method processes a Group ID and returns a list giving
     * the the full details of all the participants that are members of that
     * group if the Group ID exists and has any participants belonging to
     * it. If a participant details list is returned, it will include a list
     * of all the groups (specified by Group ID) of which the participant
     * is a member.
     */
    public com.questionmark.QMWISe.Participant[] getParticipantListByGroup(java.lang.String group_ID) throws java.rmi.RemoteException;

    /**
     * This method processes a Participant ID and returns a list all
     * groups in the database who are owned by the participant if the Participant
     * ID exists in the database.
     */
    public com.questionmark.QMWISe.Group[] getParticipantGroupList(java.lang.String participant_ID) throws java.rmi.RemoteException;

    /**
     * This method processes a Participant Name and a Password and
     * checks whether there is a participant with the Participant Name, and
     * if so whether the Password is theirs.
     */
    public void checkParticipant(java.lang.String participant_Name, java.lang.String password, javax.xml.rpc.holders.IntHolder status, javax.xml.rpc.holders.StringHolder participant_ID) throws java.rmi.RemoteException;

    /**
     * This method creates a participant from the participant information
     * supplied and returns the Participant ID of the new participant. If
     * the Participant Name does not already exist in the database, the new
     * participant is created. Any group specified will have the new participant
     * as a member if the group exists in the database. Otherwise the group
     * will be ignored.
     */
    public java.lang.String createParticipant(com.questionmark.QMWISe.Participant participant) throws java.rmi.RemoteException;

    /**
     * This method processes a Participant ID and updates the details
     * of an existing participant from the participant information supplied.
     * Any group specified will have the new participant as a member if the
     * group exists in the database. Otherwise the group will be ignored.
     */
    public void setParticipant(com.questionmark.QMWISe.Participant participant) throws java.rmi.RemoteException;

    /**
     * This method processes a Participant ID and deletes an existing
     * participant from the database. If the participant has any schedules
     * that are not linked to any other participants, then these schedules
     * are also deleted.
     */
    public void deleteParticipant(java.lang.String participant_ID) throws java.rmi.RemoteException;

    /**
     * This method processes a Group ID and returns a list giving
     * the the full details of the group if the Group ID exists in the database.
     */
    public com.questionmark.QMWISe.Group getGroup(java.lang.String group_ID) throws java.rmi.RemoteException;

    /**
     * This method processes a Group Name and returns a list giving
     * the the full details of the group if it exists in the database.
     */
    public com.questionmark.QMWISe.Group getGroupByName(java.lang.String group_Name) throws java.rmi.RemoteException;

    /**
     * This method returns a list giving the full details of all the
     * groups in the database.
     */
    public com.questionmark.QMWISe.Group[] getGroupList() throws java.rmi.RemoteException;

    /**
     * This method creates a group from the group information supplied
     * and returns the Group ID of the new group. If the Group Name does
     * not already exist in the database, the new group is created. If the
     * Perception Registration System is installed, this method can be used
     * to create a new account.
     */
    public java.lang.String createGroup(com.questionmark.QMWISe.Group group) throws java.rmi.RemoteException;

    /**
     * This method processes a Group ID and updates the details of
     * an existing group from the group information supplied. If the Perception
     * Registration System is installed, this method can be used to modify
     * an account.
     */
    public void setGroup(com.questionmark.QMWISe.Group group) throws java.rmi.RemoteException;

    /**
     * This method processes a Group ID and deletes an existing group
     * from the database. If the group has any schedules that are not linked
     * to any other groups, then these schedules are also deleted.
     */
    public void deleteGroup(java.lang.String group_ID) throws java.rmi.RemoteException;

    /**
     * This method makes one or more participants (specified by their
     * Participant ID) members of a group specified by its Group ID. Any
     * participant specified must exist in the database for it to belong
     * to the group. Otherwise the participant will be ignored.
     */
    public void addGroupParticipantList(java.lang.String group_ID, java.lang.String[] participantIDList) throws java.rmi.RemoteException;

    /**
     * This method processes a Group ID and removes one or more participants
     * (specified by Participant ID) from membership of that group. Any participant
     * specified must both exist in the database and be a member of the group
     * for it to be removed from that group. Otherwise the participant will
     * be ignored.
     */
    public void deleteGroupParticipantList(java.lang.String group_ID, java.lang.String[] participantIDList) throws java.rmi.RemoteException;

    /**
     * This method makes one or more administrators (specified by
     * their Administrator ID) owners of a group specified by its Group ID.
     * All the administrators specified must exist in the database, otherwise
     * none of them will be made owners of the group.
     */
    public void addGroupAdministratorList(java.lang.String group_ID, java.lang.String[] administratorIDList) throws java.rmi.RemoteException;

    /**
     * This method changes one or more administrators (specified by
     * their Administrator ID) from being owners of a group specified by
     * its Group ID to not being owners. All the administrators specified
     * must exist in the database and be owners of the group, otherwise none
     * of them will be removed from ownership of the group.
     */
    public void deleteGroupAdministratorList(java.lang.String group_ID, java.lang.String[] administratorIDList) throws java.rmi.RemoteException;

    /**
     * This method processes a Question ID and return the question
     * detail in QML lite format (eliminating question outcome).
     */
    public java.lang.String getQuestionPresentation(java.lang.String question_ID) throws java.rmi.RemoteException;

    /**
     * This method processes an Assessment ID and returns a list giving
     * the the full details of the assessment if the assessment exists in
     * the database.
     */
    public com.questionmark.QMWISe.Assessment getAssessment(java.lang.String assessment_ID) throws java.rmi.RemoteException;

    /**
     * This method returns a list giving the full details of all the
     * assessments in the database.
     */
    public com.questionmark.QMWISe.Assessment[] getAssessmentList() throws java.rmi.RemoteException;

    /**
     * This method processes a Group ID and returns a list giving
     * the the full details of all the assessments that have been assigned
     * to the group if that Group ID exists and has any assessments assigned
     * to it.
     */
    public com.questionmark.QMWISe.Assessment[] getAssessmentListByGroup(java.lang.String group_ID) throws java.rmi.RemoteException;

    /**
     * This method processes an Administrator ID and returns a list
     * giving the full details of all the assessments that the administrator
     * can schedule if the Administrator ID exists.
     */
    public com.questionmark.QMWISe.Assessment[] getAssessmentListByAdministrator(java.lang.String administrator_ID) throws java.rmi.RemoteException;

    /**
     * This method processes an Assessment ID and returns a list giving
     * the full details of all the topics that have been set as ‘reported
     * topics’ in the assessment.
     */
    public com.questionmark.QMWISe.Topic[] getAssessmentReportedTopics(java.lang.String assessment_ID) throws java.rmi.RemoteException;

    /**
     * This method processes an Assessment ID and returns a list giving
     * the the full details of the assessment definition if the Assessment
     * ID exists in the database.
     */
    public com.questionmark.QMWISe.AssessmentDefinition getAssessmentDefinition(java.lang.String assessment_ID) throws java.rmi.RemoteException;

    /**
     * This method processes a Session Name, a Template Name and other
     * topic, question and assessment outcome information and returns the
     * Assessment ID of the new assessment definition.
     */
    public java.lang.String createAssessmentDefinition(com.questionmark.QMWISe.AssessmentDefinition assessmentDefinition) throws java.rmi.RemoteException;

    /**
     * This method processes an Assessment ID and updates the details
     * of an existing assessment defintion from the information supplied.
     */
    public void setAssessmentDefinition(com.questionmark.QMWISe.AssessmentDefinition assessmentDefinition) throws java.rmi.RemoteException;

    /**
     * This method processes an Assessment ID and deletes an existing
     * assessment.
     */
    public void deleteAssessment(java.lang.String assessment_ID) throws java.rmi.RemoteException;

    /**
     * Get the children nodes of a specified node from the assessment
     * tree as available to the specified administrator.
     */
    public com.questionmark.QMWISe.AssessmentTreeItem[] getAssessmentTreeByAdministrator(java.lang.String administrator_ID, java.lang.String parent_ID, int onlyRunFromIntegration) throws java.rmi.RemoteException;

    /**
     * Creates a V4.2 schedule for the participant in Schedule.Participant_ID.
     * If the schedule includes offline delivery an APack4 file is created.
     */
    public int createScheduleParticipantV42(com.questionmark.QMWISe.ScheduleV42 schedule) throws java.rmi.RemoteException;

    /**
     * This method creates V4.2 schedule(s) for a group in the database.
     * If the schedule includes offline delivery an APack4 file is created.
     * 	If wanted, a schedule is created individually on each participant.
     * Returns the Schedule IDs of the new schedules.
     */
    public java.lang.String[] createScheduleGroupV42(com.questionmark.QMWISe.ScheduleV42 schedule, boolean scheduleEachMember) throws java.rmi.RemoteException;

    /**
     * This method processes a Schedule ID and returns a list giving
     * the the full details of the V4.2 schedule if the schedule exists in
     * the database.
     */
    public com.questionmark.QMWISe.ScheduleV42 getScheduleV42(int scheduleId) throws java.rmi.RemoteException;

    /**
     * This method returns a list giving the full details of all the
     * V4.2 schedules in the database.
     */
    public com.questionmark.QMWISe.ScheduleV42[] getScheduleListV42() throws java.rmi.RemoteException;

    /**
     * This method processes a Participant ID and returns a list giving
     * the the full details of all the V4.2 schedules that are assigned to
     * the participant if the Participant ID exists and has any schedules
     * assigned to it. The schedules in the list include those for all participants
     * assigned to the schedule, whether the participant is assigned directly
     * to the schedule, or whether the participant belongs to a group that
     * is assigned to the schedule.
     */
    public com.questionmark.QMWISe.ScheduleV42[] getScheduleListByParticipantV42(int participantId) throws java.rmi.RemoteException;

    /**
     * This method processes a Group ID and returns a list giving
     * the full details of all the V4.2 schedules that are assigned to the
     * group if the Group ID exists and has any schedules assigned to it.
     */
    public com.questionmark.QMWISe.ScheduleV42[] getScheduleListByGroupV42(int groupId) throws java.rmi.RemoteException;

    /**
     * This method processes an Assessment ID and returns a list giving
     * the the full details of all the V4.2 schedules that are assigned to
     * this assessment if the Assessment ID exists and has any schedules
     * assigned to it.
     */
    public com.questionmark.QMWISe.ScheduleV42[] getScheduleListByAssessmentV42(java.lang.String assessment_ID) throws java.rmi.RemoteException;

    /**
     * This method processes a Schedule ID and updates the details
     * of an existing V4.2 schedule from the schedule information supplied.
     */
    public void setScheduleV42(com.questionmark.QMWISe.ScheduleV42 schedule) throws java.rmi.RemoteException;

    /**
     * This method processes a Schedule ID and deletes an existing
     * V4.2 schedule from the database. If the schedule includes offline
     * delivery the APack4 file is deleted too.
     */
    public void deleteScheduleV42(int scheduleId) throws java.rmi.RemoteException;

    /**
     * This method processes a Schedule ID and returns a list giving
     * the the full details of the V4.1 schedule if the schedule exists in
     * the database.
     */
    public com.questionmark.QMWISe.Schedule getSchedule(java.lang.String schedule_ID) throws java.rmi.RemoteException;

    /**
     * This method returns a list giving the full details of all the
     * V4.1 schedules in the database.
     */
    public com.questionmark.QMWISe.Schedule[] getScheduleList() throws java.rmi.RemoteException;

    /**
     * This method processes a Participant ID and returns a list giving
     * the the full details of all the V4.1 schedules that are assigned to
     * the participant if the Participant ID exists and has any schedules
     * assigned to it. The schedules in the list include those for all participants
     * assigned to the schedule, whether the participant is assigned directly
     * to the schedule, or whether the participant belongs to a group that
     * is assigned to the schedule.
     */
    public com.questionmark.QMWISe.Schedule[] getScheduleListByParticipant(java.lang.String participant_ID) throws java.rmi.RemoteException;

    /**
     * This method processes a Group ID and returns a list giving
     * the full details of all the V4.1 schedules that are assigned to the
     * group if the Group ID exists and has any schedules assigned to it.
     */
    public com.questionmark.QMWISe.Schedule[] getScheduleListByGroup(java.lang.String group_ID) throws java.rmi.RemoteException;

    /**
     * This method processes an Assessment ID and returns a list giving
     * the the full details of all the V4.1 schedules that are assigned to
     * this assessment if the Assessment ID exists and has any schedules
     * assigned to it.
     */
    public com.questionmark.QMWISe.Schedule[] getScheduleListByAssessment(java.lang.String assessment_ID) throws java.rmi.RemoteException;

    /**
     * This method creates a V4.1 schedule for a group (specified
     * by their Group ID) from the schedule information supplied and returns
     * the Schedule ID of the new schedule.
     */
    public java.lang.String[] createScheduleGroup(boolean schedule_Participants, com.questionmark.QMWISe.Schedule schedule) throws java.rmi.RemoteException;

    /**
     * This method creates a V4.1 schedule for a participant (specified
     * by their Participant ID) from the schedule information supplied and
     * returns the Schedule ID of the new schedule.
     */
    public java.lang.String createScheduleParticipant(com.questionmark.QMWISe.Schedule schedule) throws java.rmi.RemoteException;

    /**
     * This method processes a Schedule ID and updates the details
     * of an existing V4.1 schedule from the schedule information supplied.
     */
    public void setSchedule(com.questionmark.QMWISe.Schedule schedule) throws java.rmi.RemoteException;

    /**
     * This method processes a Schedule ID and deletes an existing
     * V4.1 schedule from the database.
     */
    public void deleteSchedule(java.lang.String schedule_ID) throws java.rmi.RemoteException;

    /**
     * This method processes a Participant ID and deletes any schedules
     * that exist for that participant from the database.
     */
    public void deleteScheduleListByParticipant(java.lang.String participant_ID) throws java.rmi.RemoteException;

    /**
     * This method returns the number of attempts available on a schedule
     * for Questionmark to Go and decrements this number to zero.
     */
    public int confirmAssessmentDownload(java.lang.String schedule_ID) throws java.rmi.RemoteException;

    /**
     * This method send emails to participants for the given event,
     * and to proctors of the given test center
     */
    public com.questionmark.QMWISe.EmailReturn sendEmailsScheduledEvent(java.lang.String event, java.lang.String test_Center_Name, int proctor_Only, java.lang.String proctor_Email) throws java.rmi.RemoteException;

    /**
     * This method processes a Result ID and returns a list giving
     * the the full details of the result if the result exists in the database.
     */
    public com.questionmark.QMWISe.Result getResult(java.lang.String result_ID) throws java.rmi.RemoteException;

    /**
     * This method returns a list giving the full details of all the
     * results in the database.
     */
    public com.questionmark.QMWISe.Result[] getResultList() throws java.rmi.RemoteException;

    /**
     * This method processes a Group Name and returns a list giving
     * the the full details of all the results obtained by participants in
     * that group if the group exists and has any results associated with
     * it.
     */
    public com.questionmark.QMWISe.Result[] getResultListByGroup(java.lang.String group_Name) throws java.rmi.RemoteException;

    /**
     * This method processes a Participant Name and returns a list
     * giving the the full details of all the results obtained by that participant
     * if the participant exists and has any results associated with them.
     */
    public com.questionmark.QMWISe.Result[] getResultListByParticipant(java.lang.String participant_Name) throws java.rmi.RemoteException;

    /**
     * This method processes an Assessment ID and returns a list giving
     * the the full details of all the results obtained by participants who
     * have taken that assessment if the assessment exists and has any results
     * associated with it.
     */
    public com.questionmark.QMWISe.Result[] getResultListByAssessment(java.lang.String assessment_ID) throws java.rmi.RemoteException;

    /**
     * This method processes an Assessment ID and a Participant Name
     * and returns a URL that can be used by the participant to take the
     * assessment if the assessment exists.
     */
    public java.lang.String getAccessAssessment(java.lang.String assessment_ID, java.lang.String participant_Name, java.lang.String participant_Details, java.lang.String group_name) throws java.rmi.RemoteException;

    /**
     * This method processes a Participant Name and returns a URL
     * that can be used to display a list of assessments for that participant
     * if the participant assessment exists.
     */
    public java.lang.String getAccessAssessmentList(java.lang.String participant_Name) throws java.rmi.RemoteException;

    /**
     * This method processes an Assessment ID, a Participant Name
     * and a Notify URL and returns a URL that notifies the first URL when
     * the assessment finishes when be used by the participant takes the
     * assessment if the assessment exists.
     */
    public java.lang.String getAccessAssessmentNotify(java.lang.String assessment_ID, java.lang.String participant_Name, java.lang.String notify, java.lang.String PIP, com.questionmark.QMWISe.Parameter[] parameterList) throws java.rmi.RemoteException;

    /**
     * This method processes a Result ID and template name and returns
     * a URL that can be used to run a coaching report for the participant
     * who took the assessment that generated the result if the result exists.
     */
    public java.lang.String getAccessReportTemplate(java.lang.String result_ID, java.lang.String template_Name) throws java.rmi.RemoteException;

    /**
     * This method processes a Result ID and returns a URL that can
     * be used to run a coaching report for the participant who took the
     * assessment that generated the result if the result exists.
     */
    public java.lang.String getAccessReport(java.lang.String result_ID) throws java.rmi.RemoteException;

    /**
     * This method processes an Administrator Name and returns a URL
     * that enables the administrator to log in to Enterprise Manager (without
     * using a password) if the administrator exists.
     */
    public java.lang.String getAccessAdministrator(java.lang.String administrator_Name) throws java.rmi.RemoteException;

    /**
     * This method processes an Administrator ID and returns a list
     * giving the the full details of the administrator if the Administrator
     * ID exists in the database.
     */
    public com.questionmark.QMWISe.Administrator getAdministrator(java.lang.String administrator_ID) throws java.rmi.RemoteException;

    /**
     * This method processes an Administrator Name and a Password
     * and checks whether there is an administrator with the Administrator
     * Name, and if so whether the Password is theirs.
     */
    public void checkAdministrator(java.lang.String administrator_Name, java.lang.String password, javax.xml.rpc.holders.IntHolder status, javax.xml.rpc.holders.StringHolder administrator_ID) throws java.rmi.RemoteException;

    /**
     * This method processes an Administrator Name and returns a list
     * giving the the full details of the administrator if the Administrator
     * ID exists in the database.
     */
    public com.questionmark.QMWISe.Administrator getAdministratorByName(java.lang.String administrator_Name) throws java.rmi.RemoteException;

    /**
     * This method returns a list giving the full details of all the
     * administrators in the database.
     */
    public com.questionmark.QMWISe.Administrator[] getAdministratorList() throws java.rmi.RemoteException;

    /**
     * This method processes a Group ID and returns a list giving
     * the the full details of all the administrators who are owners of the
     * group if the Group ID exists and has any owners.
     */
    public com.questionmark.QMWISe.Administrator[] getAdministratorListByGroup(java.lang.String group_ID) throws java.rmi.RemoteException;

    /**
     * This method processes an Administrator ID and returns a list
     * all groups in the database who are owned by the administrator if the
     * Administrator ID exists in the database.
     */
    public com.questionmark.QMWISe.Group[] getAdministratorGroupList(java.lang.String administrator_ID) throws java.rmi.RemoteException;

    /**
     * This method returns a list giving the full details of all the
     * administrator profiles in the database.
     */
    public java.lang.String[] getProfileNameList() throws java.rmi.RemoteException;

    /**
     * This method creates an administrator from the administrator
     * information supplied and returns the Administrator ID of the new administrator.
     */
    public java.lang.String createAdministrator(com.questionmark.QMWISe.Administrator administrator) throws java.rmi.RemoteException;

    /**
     * This method is same as CreateAdministrator except that the
     * password is encrypted and stored in repository. Also the fields First
     * name,Last name,Department are added.
     */
    public java.lang.String createAdministratorWithPassword(com.questionmark.QMWISe.Administrator2 administrator) throws java.rmi.RemoteException;

    /**
     * This method creates an administrator from the administrator
     * information supplied and returns the Administrator ID of the new administrator
     * who inhibit change password at login(CPL) enabled.
     */
    public java.lang.String createAdministratorChangePasswordAtLogin(com.questionmark.QMWISe.Administrator administrator) throws java.rmi.RemoteException;

    /**
     * This method processes an Assessment ID and returns a list giving
     * the full details of the results information for the assessment with
     * that Assessment ID if it exists in the database.
     */
    public com.questionmark.QMWISe.AssessmentResultInfo getAssessmentResultInfo(java.lang.String assessment_ID) throws java.rmi.RemoteException;

    /**
     * This method processes an Assessment ID and returns a list giving
     * the full details of the results information for the assessment with
     * that Assessment ID if it exists in the database.
     */
    public com.questionmark.QMWISe.AssessmentResultInfo2 getAssessmentResultInfo2(java.lang.String assessment_ID) throws java.rmi.RemoteException;

    /**
     * This method returns a list giving the full details of the results
     * information for all the assessments in the database.
     */
    public com.questionmark.QMWISe.AssessmentResultInfo[] getAssessmentResultInfoList() throws java.rmi.RemoteException;

    /**
     * This method returns a list giving the full details of the results
     * information for all the assessments in the database.
     */
    public com.questionmark.QMWISe.AssessmentResultInfo2[] getAssessmentResultInfoList2() throws java.rmi.RemoteException;

    /**
     * This method process a list of assessment information (which
     * includes the Assessment ID) and creates a new assessment record in
     * the database for new each assessment in the list. It returns the number
     * of new records created.
     */
    public int createAssessmentResultInfoList(com.questionmark.QMWISe.AssessmentResultInfo[] assessmentResultInfoList) throws java.rmi.RemoteException;

    /**
     * This method process a list of assessment information (which
     * includes the Assessment ID) and creates a new assessment record in
     * the database for new each assessment in the list. It returns the number
     * of new records created.
     */
    public int createAssessmentResultInfoList2(com.questionmark.QMWISe.AssessmentResultInfo2[] assessmentResultInfoList2) throws java.rmi.RemoteException;

    /**
     * This method returns a list giving the the full details of each
     * question in the database, if any exist.
     */
    public com.questionmark.QMWISe.QuestionResultInfo[] getQuestionResultInfoList() throws java.rmi.RemoteException;

    /**
     * This method creates one or more questions (specified by their
     * Question ID) from a list of question information supplied and returns
     * the number of new questions created.
     */
    public int createQuestionResultInfoList(com.questionmark.QMWISe.QuestionResultInfo[] questionResultInfoList) throws java.rmi.RemoteException;

    /**
     * This method processes an Result ID and returns a list giving
     * the the full details of the result if the result exists in the database.
     */
    public com.questionmark.QMWISe.AssessmentResult getAssessmentResult(java.lang.String result_ID) throws java.rmi.RemoteException;

    /**
     * This method processes an Result ID and returns a list giving
     * the the full details of the result if the result exists in the database.
     */
    public com.questionmark.QMWISe.AssessmentResult2 getAssessmentResult2(java.lang.String result_ID) throws java.rmi.RemoteException;

    /**
     * This method processes a Group Name and returns a list giving
     * the the full details of all the results for all the participants who
     * were members of that group when the results were stored, if the Group
     * Name exists in the database.
     */
    public com.questionmark.QMWISe.AssessmentResult[] getAssessmentResultListByGroup(java.lang.String group_Name) throws java.rmi.RemoteException;

    /**
     * This method processes an Participant Name and returns a list
     * giving the the full details of all the results for that participant
     * if the Participant Name exists in the database.
     */
    public com.questionmark.QMWISe.AssessmentResult[] getAssessmentResultListByParticipant(java.lang.String participant_Name) throws java.rmi.RemoteException;

    /**
     * This method processes an Assessment ID and returns a list giving
     * the the full details of all the results for that assessment if the
     * Assessment ID exists in the database.
     */
    public com.questionmark.QMWISe.AssessmentResult[] getAssessmentResultListByAssessment(java.lang.String assessment_ID) throws java.rmi.RemoteException;

    /**
     * This method creates a new result from the information supplied
     * and returns the Result ID of the new result.
     */
    public java.lang.String createAssessmentResult(com.questionmark.QMWISe.AssessmentResult assessmentResult) throws java.rmi.RemoteException;

    /**
     * This method creates a new result from the information supplied
     * and returns the Result ID of the new result.
     */
    public java.lang.String createAssessmentResult2(com.questionmark.QMWISe.AssessmentResult2 assessmentResult2) throws java.rmi.RemoteException;

    /**
     * This method returns the Correct Answer for a question, where
     * available. If no revision is specified, it gives it for the latest
     * revision.
     */
    public com.questionmark.QMWISe.CorrectAnswer getQuestionCorrectAnswer(com.questionmark.QMWISe.Question question) throws java.rmi.RemoteException;

    /**
     * This method creates a topic from the information supplied and
     * returns the Topic ID of the new topic.
     */
    public java.lang.String createTopic(com.questionmark.QMWISe.Topic topic) throws java.rmi.RemoteException;

    /**
     * This method creates an assessment folder from the information
     * supplied and returns the ID of the new assessment folder.
     */
    public java.lang.String createAssessmentFolder(com.questionmark.QMWISe.AssessmentFolder assessmentFolder) throws java.rmi.RemoteException;

    /**
     * This method assigns an administrator to a specified topic and
     * sets their permissions to the value specified.
     */
    public void assignAdministratorToTopic(java.lang.String administrator_ID, java.lang.String topic_ID, javax.xml.rpc.holders.StringHolder permissions) throws java.rmi.RemoteException;

    /**
     * This method assigns an administrator to a specified assessment
     * folder and sets their permissions to the value specified.
     */
    public void assignAdministratorToAssessmentFolder(java.lang.String administrator_ID, java.lang.String folder_ID, javax.xml.rpc.holders.StringHolder permissions) throws java.rmi.RemoteException;

    /**
     * This method  processes an Assessment ID and returns an XML
     * representation of the assessment for external delivery. A snapshot
     * of the assessment may optionally be saved so that answers to the assessment
     * may be scored later using the ScoreAssessmentSnapshot method.
     */
    public com.questionmark.QMWISe.DeliveryAssessmentSnapshot createAssessmentSnapshot(com.questionmark.QMWISe.CreateAssessmentSnapshotParams assessmentSnapshotDefinition) throws java.rmi.RemoteException;

    /**
     * This method takes the responses from a printed Delivery Snapshot
     * and uses the saved Scoring Snapshot to score all the questions and
     * calculate assessment and topic scoring and records all the results
     * in the Perception Answer database.
     */
    public int scoreAssessmentSnapshot(com.questionmark.QMWISe.AssessmentSnapshotAnswers assessmentSnapshotAnswers) throws java.rmi.RemoteException;

    /**
     * This method returns a ResponseList with information regarding
     * the types & choices of each question, enabling the client to validate
     * the partipants' responses
     */
    public com.questionmark.QMWISe.SnapshotResponseConstraints getAssessmentSnapshotResponseConstraints(java.lang.String snapshotID) throws java.rmi.RemoteException;

    /**
     * This method processes an Snapshot ID and returns an XML representation
     * of the assessment for external delivery.
     */
    public com.questionmark.QMWISe.DeliveryAssessmentSnapshot getAssessmentSnapshot(java.lang.String snapshot_ID) throws java.rmi.RemoteException;

    /**
     * This method processes an Snapshot ID and returns an assessment
     * snapshot summary.
     */
    public com.questionmark.QMWISe.DeliveryAssessmentSnapshot getAssessmentSnapshotSummary(java.lang.String snapshot_ID) throws java.rmi.RemoteException;

    /**
     * This method processes an Snapshot ID and delete an assessment
     * snapshot if exists.
     */
    public void deleteAssessmentSnapshot(java.lang.String snapshot_ID) throws java.rmi.RemoteException;

    /**
     * The participant is added to the Questionmark database and schedules
     * to one or more assessments.
     */
    public com.questionmark.QMWISe.CreateAndScheduleParticipant createAndScheduleParticipant(com.questionmark.QMWISe.Participant participant, com.questionmark.QMWISe.Schedule[] scheduleList) throws java.rmi.RemoteException;

    /**
     * The participant is given a URL to take one or more scheduled
     * assessment
     */
    public java.lang.String getAccessScheduleNotify(java.lang.String schedule_ID, java.lang.String participant_Name, java.lang.String notify, java.lang.String PIP, com.questionmark.QMWISe.Parameter[] parameterList) throws java.rmi.RemoteException;

    /**
     * Any schedules assigned to the participant are removed. The
     * participant remains in the database (but may be removed by a call
     * to DeleteParticipant).
     */
    public com.questionmark.QMWISe.Schedule[] deleteScheduleList(java.lang.String[] scheduleIDList) throws java.rmi.RemoteException;

    /**
     * This method creates a schedule for the given participant, assessment
     * name, group, sub group and test center. New participant, test center,
     * group and subgroup will be created if they are not found in the system.
     */
    public int createScheduleWithParticipantGroupTestCenter(com.questionmark.QMWISe.Participant2 participant2, com.questionmark.QMWISe.Schedule2 schedule2, com.questionmark.QMWISe.Event event, com.questionmark.QMWISe.TestCenter testCenter) throws java.rmi.RemoteException;
}
