package com.marshal.halcyon.quartz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marshal.halcyon.core.constants.BaseConstants;

import java.util.Date;
import javax.persistence.*;

@Table(name = "qrtz_job_running_info")
public class JobRunningInfo {
    @Id
    @Column(name = "JOB_RUNNING_INFO_ID")
    @GeneratedValue(generator = "JDBC")
    private Long jobRunningInfoId;

    @Column(name = "JOB_NAME")
    private String jobName;

    @Column(name = "JOB_GROUP")
    private String jobGroup;

    @Column(name = "JOB_RESULT")
    private String jobResult;

    @Column(name = "JOB_STATUS")
    private String jobStatus;

    @Column(name = "JOB_STATUS_MESSAGE")
    private String jobStatusMessage;

    @Column(name = "TRIGGER_NAME")
    private String triggerName;

    @Column(name = "TRIGGER_GROUP")
    private String triggerGroup;

    @JsonFormat(pattern = BaseConstants.DATE_TIME_FORMAT)
    @Column(name = "PREVIOUS_FIRE_TIME")
    private Date previousFireTime;

    @JsonFormat(pattern = BaseConstants.DATE_TIME_FORMAT)
    @Column(name = "FIRE_TIME")
    @OrderBy("desc")
    private Date fireTime;

    @JsonFormat(pattern = BaseConstants.DATE_TIME_FORMAT)
    @Column(name = "NEXT_FIRE_TIME")
    private Date nextFireTime;

    @Column(name = "REFIRE_COUNT")
    private Long refireCount;

    @Column(name = "FIRE_INSTANCE_ID")
    private String fireInstanceId;

    @Column(name = "SCHEDULER_INSTANCE_ID")
    private String schedulerInstanceId;

    @JsonFormat(pattern = BaseConstants.DATE_TIME_FORMAT)
    @Column(name = "SCHEDULED_FIRE_TIME")
    private Date scheduledFireTime;

    /**
     * IP地址
     */
    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    /**
     * @return JOB_RUNNING_INFO_ID
     */
    public Long getJobRunningInfoId() {
        return jobRunningInfoId;
    }

    /**
     * @param jobRunningInfoId
     */
    public void setJobRunningInfoId(Long jobRunningInfoId) {
        this.jobRunningInfoId = jobRunningInfoId;
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
     * @return JOB_RESULT
     */
    public String getJobResult() {
        return jobResult;
    }

    /**
     * @param jobResult
     */
    public void setJobResult(String jobResult) {
        this.jobResult = jobResult;
    }

    /**
     * @return JOB_STATUS
     */
    public String getJobStatus() {
        return jobStatus;
    }

    /**
     * @param jobStatus
     */
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    /**
     * @return JOB_STATUS_MESSAGE
     */
    public String getJobStatusMessage() {
        return jobStatusMessage;
    }

    /**
     * @param jobStatusMessage
     */
    public void setJobStatusMessage(String jobStatusMessage) {
        this.jobStatusMessage = jobStatusMessage;
    }

    /**
     * @return TRIGGER_NAME
     */
    public String getTriggerName() {
        return triggerName;
    }

    /**
     * @param triggerName
     */
    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    /**
     * @return TRIGGER_GROUP
     */
    public String getTriggerGroup() {
        return triggerGroup;
    }

    /**
     * @param triggerGroup
     */
    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    /**
     * @return PREVIOUS_FIRE_TIME
     */
    public Date getPreviousFireTime() {
        return previousFireTime;
    }

    /**
     * @param previousFireTime
     */
    public void setPreviousFireTime(Date previousFireTime) {
        this.previousFireTime = previousFireTime;
    }

    /**
     * @return FIRE_TIME
     */
    public Date getFireTime() {
        return fireTime;
    }

    /**
     * @param fireTime
     */
    public void setFireTime(Date fireTime) {
        this.fireTime = fireTime;
    }

    /**
     * @return NEXT_FIRE_TIME
     */
    public Date getNextFireTime() {
        return nextFireTime;
    }

    /**
     * @param nextFireTime
     */
    public void setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    /**
     * @return REFIRE_COUNT
     */
    public Long getRefireCount() {
        return refireCount;
    }

    /**
     * @param refireCount
     */
    public void setRefireCount(Long refireCount) {
        this.refireCount = refireCount;
    }

    /**
     * @return FIRE_INSTANCE_ID
     */
    public String getFireInstanceId() {
        return fireInstanceId;
    }

    /**
     * @param fireInstanceId
     */
    public void setFireInstanceId(String fireInstanceId) {
        this.fireInstanceId = fireInstanceId;
    }

    /**
     * @return SCHEDULER_INSTANCE_ID
     */
    public String getSchedulerInstanceId() {
        return schedulerInstanceId;
    }

    /**
     * @param schedulerInstanceId
     */
    public void setSchedulerInstanceId(String schedulerInstanceId) {
        this.schedulerInstanceId = schedulerInstanceId;
    }

    /**
     * @return SCHEDULED_FIRE_TIME
     */
    public Date getScheduledFireTime() {
        return scheduledFireTime;
    }

    /**
     * @param scheduledFireTime
     */
    public void setScheduledFireTime(Date scheduledFireTime) {
        this.scheduledFireTime = scheduledFireTime;
    }

    /**
     * 获取IP地址
     *
     * @return IP_ADDRESS - IP地址
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * 设置IP地址
     *
     * @param ipAddress IP地址
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}