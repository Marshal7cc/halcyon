package com.marshal.halcyon.quartz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marshal.halcyon.core.constants.BaseConstants;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @auth: Marshal
 * @date: 2018/11/4
 * @desc: 新建job实体类
 */
public class JobCreateInfo implements Serializable {

    /**
     * job info
     */
    @NotNull
    private String jobName;

    @NotNull
    private String jobGroup;

    private String description;

    @NotNull
    private String jobClassName;

    private List<JobData> jobData;

    /**
     * trigger info
     */
    private String triggerGroup;

    private String triggerName;

    @JsonFormat(pattern = BaseConstants.DATE_TIME_FORMAT)
    private Date startTime;

    @JsonFormat(pattern = BaseConstants.DATE_TIME_FORMAT)
    private Date endTime;

    private Integer priority;

    /**
     * simple trigger
     */
    private Integer repeatInterval;
    private Integer repeatCount;

    /**
     * cronTrigger
     */
    private String cronExpression;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public List<JobData> getJobData() {
        return jobData;
    }

    public void setJobData(List<JobData> jobData) {
        this.jobData = jobData;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(Integer repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public Integer getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(Integer repeatCount) {
        this.repeatCount = repeatCount;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }
}
