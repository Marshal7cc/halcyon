package com.marshal.mcap.quartz.service;

import com.marshal.mcap.quartz.entity.JobCreateInfo;
import com.marshal.mcap.quartz.entity.JobDetails;
import org.quartz.SchedulerException;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/11/1
 * Time: 20:34
 * Description:
 */
public interface QuartzService {

    /**
     *scheduler方法
     */
    boolean startup() throws SchedulerException;

    boolean standby() throws SchedulerException;

    Map<String, Object> schedulerInformation() throws SchedulerException;

    /**
     *job方法
     */
    List<JobDetails> queryJobDetail(JobDetails condition, int pageNum, int pageSize);

    void pauseJob(String jobName, String jobGroup) throws SchedulerException;

    void resumeJob(String jobName, String jobGroup) throws SchedulerException;

    void createJob(JobCreateInfo jobCreateInfo)throws Exception;

    void deleteJob(String jobName, String jobGroup) throws SchedulerException;
 }
