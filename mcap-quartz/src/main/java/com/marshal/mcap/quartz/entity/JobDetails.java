package com.marshal.mcap.quartz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marshal.mcap.core.constants.BaseConstants;

import javax.persistence.*;
import java.util.Date;

@Table(name = "qrtz_job_details")
public class JobDetails {
    @Id
    @Column(name = "SCHED_NAME")
    @GeneratedValue(generator = "JDBC")
    private String schedName;

    @Id
    @Column(name = "JOB_NAME")
    private String jobName;

    @Id
    @Column(name = "JOB_GROUP")
    private String jobGroup;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "JOB_CLASS_NAME")
    private String jobClassName;

    @Column(name = "IS_DURABLE")
    private String isDurable;

    @Column(name = "IS_NONCONCURRENT")
    private String isNonconcurrent;

    @Column(name = "IS_UPDATE_DATA")
    private String isUpdateData;

    @Column(name = "REQUESTS_RECOVERY")
    private String requestsRecovery;

    @Column(name = "JOB_DATA")
    private byte[] jobData;

    //其他属性
    @JsonFormat(pattern = BaseConstants.DATE_TIME_FORMAT)
    @Transient
    private Date fireTime;

    @JsonFormat(pattern = BaseConstants.DATE_TIME_FORMAT)
    @Transient
    private Date nextFireTime;

    @Transient
    private String runningState;

    @Transient
    private String triggerType;

    /**
     * @return SCHED_NAME
     */
    public String getSchedName() {
        return schedName;
    }

    /**
     * @param schedName
     */
    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    /**
     * @return JOB_NAME
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * @param jobName
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * @return JOB_GROUP
     */
    public String getJobGroup() {
        return jobGroup;
    }

    /**
     * @param jobGroup
     */
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    /**
     * @return DESCRIPTION
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return JOB_CLASS_NAME
     */
    public String getJobClassName() {
        return jobClassName;
    }

    /**
     * @param jobClassName
     */
    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    /**
     * @return IS_DURABLE
     */
    public String getIsDurable() {
        return isDurable;
    }

    /**
     * @param isDurable
     */
    public void setIsDurable(String isDurable) {
        this.isDurable = isDurable;
    }

    /**
     * @return IS_NONCONCURRENT
     */
    public String getIsNonconcurrent() {
        return isNonconcurrent;
    }

    /**
     * @param isNonconcurrent
     */
    public void setIsNonconcurrent(String isNonconcurrent) {
        this.isNonconcurrent = isNonconcurrent;
    }

    /**
     * @return IS_UPDATE_DATA
     */
    public String getIsUpdateData() {
        return isUpdateData;
    }

    /**
     * @param isUpdateData
     */
    public void setIsUpdateData(String isUpdateData) {
        this.isUpdateData = isUpdateData;
    }

    /**
     * @return REQUESTS_RECOVERY
     */
    public String getRequestsRecovery() {
        return requestsRecovery;
    }

    /**
     * @param requestsRecovery
     */
    public void setRequestsRecovery(String requestsRecovery) {
        this.requestsRecovery = requestsRecovery;
    }

    /**
     * @return JOB_DATA
     */
    public byte[] getJobData() {
        return jobData;
    }

    /**
     * @param jobData
     */
    public void setJobData(byte[] jobData) {
        this.jobData = jobData;
    }

    public Date getFireTime() {
        return fireTime;
    }

    public void setFireTime(Date fireTime) {
        this.fireTime = fireTime;
    }

    public Date getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public String getRunningState() {
        return runningState;
    }

    public void setRunningState(String runningState) {
        this.runningState = runningState;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }
}