package com.marshal.halcyon.quartz.service.impl;

import com.github.pagehelper.PageHelper;
import com.marshal.halcyon.quartz.entity.JobCreateInfo;
import com.marshal.halcyon.quartz.entity.JobData;
import com.marshal.halcyon.quartz.entity.JobDetails;
import com.marshal.halcyon.quartz.mapper.JobDetailsMapper;
import com.marshal.halcyon.quartz.service.QuartzService;
//import org.activiti.engine.JobNotFoundException;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/11/3
 * Time: 15:53
 * Description:
 */
@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    JobDetailsMapper jobDetailsMapper;

    @Autowired
    Scheduler scheduler;

    @Override
    public boolean startup() throws SchedulerException {
        if (scheduler.isInStandbyMode()) {
            scheduler.start();
            return true;
        }
        return false;
    }

    @Override
    public boolean standby() throws SchedulerException {
        if (scheduler.isInStandbyMode()) {
            return false;
        }
        scheduler.standby();
        return true;
    }

    @Override
    public Map<String, Object> schedulerInformation() throws SchedulerException {
        Map<String, Object> infoMap = new HashMap<>();
        SchedulerMetaData metaData = scheduler.getMetaData();
        if (metaData.getRunningSince() != null) {
            infoMap.put("runningSince", metaData.getRunningSince().getTime());
        }
        infoMap.put("numberOfJobsExecuted", metaData.getNumberOfJobsExecuted());
        infoMap.put("schedulerName", metaData.getSchedulerName());
        infoMap.put("schedulerInstanceId", metaData.getSchedulerInstanceId());
        infoMap.put("threadPoolSize", metaData.getThreadPoolSize());
        infoMap.put("version", metaData.getVersion());
        infoMap.put("inStandbyMode", metaData.isInStandbyMode());
        infoMap.put("jobStoreClustered", metaData.isJobStoreClustered());
        infoMap.put("jobStoreClass", metaData.getJobStoreClass());
        infoMap.put("jobStoreSupportsPersistence", metaData.isJobStoreSupportsPersistence());
        infoMap.put("started", metaData.isStarted());
        infoMap.put("shutdown", metaData.isShutdown());
        infoMap.put("schedulerRemote", metaData.isSchedulerRemote());
        return infoMap;
    }

    @Override
    public List<JobDetails> queryJobDetail(JobDetails condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobDetails> list = jobDetailsMapper.queryJobDetailsInfo(condition);
        for (JobDetails item : list) {
            try {
                JobKey jobKey = new JobKey(item.getJobName(), item.getJobGroup());
                List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                Trigger trigger = triggers.get(0);

                if (trigger == null) {
                    return list;
                }
                //设置触发器类型
                if (trigger instanceof SimpleTrigger) {
                    item.setTriggerType("SIMPLE");
                } else if (trigger instanceof CronTrigger) {
                    item.setTriggerType("CRON");
                }
                //job Running state
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                item.setRunningState(triggerState.name());
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    @Override
    public void pauseJob(String jobName, String jobGroup) throws SchedulerException {
        scheduler.pauseJob(new JobKey(jobName, jobGroup));
    }

    @Override
    public void resumeJob(String jobName, String jobGroup) throws SchedulerException {
        scheduler.resumeJob(new JobKey(jobName, jobGroup));
    }

    @Override
    public void deleteJob(String jobName, String jobGroup) throws SchedulerException {
        scheduler.deleteJob(new JobKey(jobName, jobGroup));
    }

    @Override
    public void createJob(JobCreateInfo jobCreateInfo) throws Exception {
        String jobClassName = jobCreateInfo.getJobClassName();

        //判断类是否存在
        boolean assignableFrom = false;
        Class forName = null;
        try {
            forName = Class.forName(jobClassName);
            assignableFrom = Job.class.isAssignableFrom(forName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (!assignableFrom || forName == null) {
            //throw new JobNotFoundException(jobClassName);

        }

        JobBuilder jobBuilder = JobBuilder.newJob(forName)
                .withIdentity(jobCreateInfo.getJobName(), jobCreateInfo.getJobGroup())
                .withDescription(jobCreateInfo.getDescription());
        if (jobCreateInfo.getJobData() != null && !jobCreateInfo.getJobData().isEmpty()) {
            List<JobData> jobData = jobCreateInfo.getJobData();
            JobDataMap jobDataMap = new JobDataMap();
            for (JobData item : jobData) {
                jobDataMap.put(item.getName(), item.getValue());
            }
            jobBuilder.usingJobData(jobDataMap);
        }
        JobDetail jobDetail = jobBuilder.build();

        Integer triggerPriority = jobCreateInfo.getPriority() == null ? 5 : jobCreateInfo.getPriority();
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger()
                .withIdentity(jobCreateInfo.getTriggerName(), jobCreateInfo.getTriggerGroup())
                .withPriority(triggerPriority)
                .forJob(jobDetail);
        if (jobCreateInfo.getStartTime() != null) {
            triggerBuilder.startAt(jobCreateInfo.getStartTime());
        }
        if (jobCreateInfo.getEndTime() != null) {
            triggerBuilder.endAt(jobCreateInfo.getEndTime());
        }

        ScheduleBuilder schedule = null;
        if (jobCreateInfo.getCronExpression() != null && !"".equals(jobCreateInfo.getCronExpression())) {
            schedule = CronScheduleBuilder.cronSchedule(jobCreateInfo.getCronExpression());
        } else {
            if (jobCreateInfo.getRepeatCount() == null || jobCreateInfo.getRepeatCount() < 1) {
                schedule = SimpleScheduleBuilder.repeatSecondlyForever();
            } else {
                schedule = SimpleScheduleBuilder.repeatSecondlyForTotalCount(jobCreateInfo.getRepeatCount(), jobCreateInfo.getRepeatInterval());
            }
        }

        //schedule job
        Trigger trigger = triggerBuilder.withSchedule(schedule).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
