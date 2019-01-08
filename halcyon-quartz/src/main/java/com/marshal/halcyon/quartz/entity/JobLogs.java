package com.marshal.halcyon.quartz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marshal.halcyon.core.constants.BaseConstants;

import java.util.Date;
import javax.persistence.*;

/**
 * 记录任务执行日志
 */
@Table(name = "qrtz_job_logs")
public class JobLogs {
    @Id
    @Column(name = "JOB_LOGS_ID")
    @GeneratedValue(generator = "JDBC")
    private Long jobLogsId;

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


    public Long getJobLogsId() {
        return jobLogsId;
    }

    public void setJobLogsId(Long jobLogsId) {
        this.jobLogsId = jobLogsId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobResult() {
        return jobResult;
    }

    public void setJobResult(String jobResult) {
        this.jobResult = jobResult;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobStatusMessage() {
        return jobStatusMessage;
    }

    public void setJobStatusMessage(String jobStatusMessage) {
        this.jobStatusMessage = jobStatusMessage;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public Date getPreviousFireTime() {
        return previousFireTime;
    }

    public void setPreviousFireTime(Date previousFireTime) {
        this.previousFireTime = previousFireTime;
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

    public Long getRefireCount() {
        return refireCount;
    }

    public void setRefireCount(Long refireCount) {
        this.refireCount = refireCount;
    }

    public String getFireInstanceId() {
        return fireInstanceId;
    }

    public void setFireInstanceId(String fireInstanceId) {
        this.fireInstanceId = fireInstanceId;
    }

    public String getSchedulerInstanceId() {
        return schedulerInstanceId;
    }

    public void setSchedulerInstanceId(String schedulerInstanceId) {
        this.schedulerInstanceId = schedulerInstanceId;
    }

    public Date getScheduledFireTime() {
        return scheduledFireTime;
    }

    public void setScheduledFireTime(Date scheduledFireTime) {
        this.scheduledFireTime = scheduledFireTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}