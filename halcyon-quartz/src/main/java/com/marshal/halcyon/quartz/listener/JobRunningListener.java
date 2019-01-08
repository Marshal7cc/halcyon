package com.marshal.halcyon.quartz.listener;
import com.marshal.halcyon.core.util.ApplicationContextHolder;
import com.marshal.halcyon.quartz.entity.JobLogs;
import com.marshal.halcyon.quartz.service.JobLogsService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.Date;

/**
 * @auth: Marshal
 * @date: 2018/11/1
 * @desc: 记录job运行记录监听器
 */
public class JobRunningListener implements JobListener {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final String VETOED = "Vetoed";

    private static final String FINISH = "Finish";

    private static final String FAILED = "Failed";

    private String name = "JobRunningListener";

    private String jobToBeFiredMessage = "Job {1}.{0} fired (by trigger {4}.{3}) at: {2, date, HH:mm:ss MM/dd/yyyy}";

    private String jobSuccessMessage = "Job {1}.{0} execution complete at {2, date, HH:mm:ss MM/dd/yyyy} "
            + "and reports: {8}";

    private String jobFailedMessage = "Job {1}.{0} execution failed at {2, date, HH:mm:ss MM/dd/yyyy} and reports: {8}";

    private String jobWasVetoedMessage = "Job {1}.{0} was vetoed.  It was to be fired (by trigger {4}.{3}) "
            + "at: {2, date, HH:mm:ss MM/dd/yyyy}";

    private JobLogsService jobLogsService;

    private ApplicationContext applicationContext;

    public JobRunningListener(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String getJobSuccessMessage() {
        return jobSuccessMessage;
    }

    public String getJobFailedMessage() {
        return jobFailedMessage;
    }

    public String getJobToBeFiredMessage() {
        return jobToBeFiredMessage;
    }

    public void setJobSuccessMessage(String jobSuccessMessage) {
        this.jobSuccessMessage = jobSuccessMessage;
    }

    public void setJobFailedMessage(String jobFailedMessage) {
        this.jobFailedMessage = jobFailedMessage;
    }

    public void setJobToBeFiredMessage(String jobToBeFiredMessage) {
        this.jobToBeFiredMessage = jobToBeFiredMessage;
    }

    public String getJobWasVetoedMessage() {
        return jobWasVetoedMessage;
    }

    public void setJobWasVetoedMessage(String jobWasVetoedMessage) {
        this.jobWasVetoedMessage = jobWasVetoedMessage;
    }

    public Logger getLog() {
        return this.log;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        Job job = jobExecutionContext.getJobInstance();
        if (job instanceof JobListener) {
            ((JobListener) job).jobToBeExecuted(jobExecutionContext);
        }
        if (!getLog().isInfoEnabled()) {
            return;
        }

        Trigger trigger = jobExecutionContext.getTrigger();

        Object[] args = {jobExecutionContext.getJobDetail().getKey().getName(), jobExecutionContext.getJobDetail().getKey().getGroup(),
                new Date(), trigger.getKey().getName(), trigger.getKey().getGroup(),
                trigger.getPreviousFireTime(), trigger.getNextFireTime(), jobExecutionContext.getRefireCount()};

        if (getLog().isInfoEnabled()) {
            getLog().info(MessageFormat.format(getJobToBeFiredMessage(), args));
        }
    }

    /**
     * job被自定义TriggerListener否决的时候执行
     * @param jobExecutionContext
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        Trigger trigger = jobExecutionContext.getTrigger();
        Object[] args = {jobExecutionContext.getJobDetail().getKey().getName(), jobExecutionContext.getJobDetail().getKey().getGroup(),
                new Date(), trigger.getKey().getName(), trigger.getKey().getGroup(),
                trigger.getPreviousFireTime(), trigger.getNextFireTime(), jobExecutionContext.getRefireCount()};

        String message = MessageFormat.format(getJobWasVetoedMessage(), args);
        if (getLog().isInfoEnabled()) {
            getLog().info(message);
        }
        JobLogs jobLogs = getCurrentRunningInfo(jobExecutionContext);
        if (message.length() > 2000) {
            message = message.substring(0, 2000);
        }
        jobLogs.setJobStatusMessage(message);
        jobLogs.setJobStatus(VETOED);
        saveJobLog(jobLogs);
    }

    /**
     * job执行后
     * @param jobExecutionContext
     * @param jobException
     */
    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException jobException) {
        Trigger trigger = jobExecutionContext.getTrigger();

        Object[] args = null;
        JobLogs jobLogs = getCurrentRunningInfo(jobExecutionContext);

        if (jobException != null) {
            String errMsg = jobException.getMessage();
            args = new Object[]{jobExecutionContext.getJobDetail().getKey().getName(), jobExecutionContext.getJobDetail().getKey().getGroup(),
                    new Date(), trigger.getKey().getName(), trigger.getKey().getGroup(),
                    trigger.getPreviousFireTime(), trigger.getNextFireTime(), Integer.valueOf(jobExecutionContext.getRefireCount()),
                    errMsg};
            String message = MessageFormat.format(getJobFailedMessage(), args);
            message = StringUtils.abbreviate(message, 225);
            if (getLog().isWarnEnabled()) {
                getLog().warn(message, jobException);
            }
            jobLogs.setJobStatusMessage(message);
            jobLogs.setJobStatus(FAILED);

        } else {
            String result = String.valueOf(jobExecutionContext.getResult());
            args = new Object[]{jobExecutionContext.getJobDetail().getKey().getName(), jobExecutionContext.getJobDetail().getKey().getGroup(),
                    new Date(), trigger.getKey().getName(), trigger.getKey().getGroup(),
                    trigger.getPreviousFireTime(), trigger.getNextFireTime(), Integer.valueOf(jobExecutionContext.getRefireCount()),
                    result};
            String message = MessageFormat.format(getJobSuccessMessage(), args);
            if (getLog().isInfoEnabled()) {
                getLog().info(message);
            }
            jobLogs.setJobStatusMessage(message);
            jobLogs.setJobStatus(FINISH);
        }
        saveJobLog(jobLogs);
        Job job = jobExecutionContext.getJobInstance();
        if (job instanceof JobListener) {
            jobExecutionContext.put("JOB_RUNNING_INFO_ID", jobLogs.getJobLogsId());
            ((JobListener) job).jobWasExecuted(jobExecutionContext, jobException);
        }


    }

    private JobLogs getCurrentRunningInfo(JobExecutionContext jobExecutionContext) {
        JobLogs record = new JobLogs();
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        String jobName = jobDetail.getKey().getName();
        String jobGroup = jobDetail.getKey().getGroup();
        Trigger trigger = jobExecutionContext.getTrigger();
        String triggerName = trigger.getKey().getName();
        String triggerGroup = trigger.getKey().getGroup();
        // Date preFireTime = trigger.getPreviousFireTime();
        Date nextFireTime = trigger.getNextFireTime();
        int refireCount = jobExecutionContext.getRefireCount();
        String fireInstanceId = jobExecutionContext.getFireInstanceId();
        Date fireTime = jobExecutionContext.getFireTime();
        // Job jobInstance = jobExecutionContext.getJobInstance();
        // long jobRunTime = jobExecutionContext.getJobRunTime();
        // Date nextFireTime2 = jobExecutionContext.getNextFireTime();
        Date previousFireTime = jobExecutionContext.getPreviousFireTime();
        Object result = jobExecutionContext.getResult();
        Date scheduledFireTime = jobExecutionContext.getScheduledFireTime();

        String schedulerInstanceId = "";
        try {
            schedulerInstanceId = jobExecutionContext.getScheduler().getSchedulerInstanceId();
        } catch (SchedulerException e) {
            if (getLog().isErrorEnabled()) {
                getLog().error(e.getMessage(), e);
            }
        }
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        record.setIpAddress(inetAddress.getHostAddress());
        record.setFireInstanceId(fireInstanceId);
        record.setFireTime(fireTime);
        record.setJobGroup(jobGroup);
        record.setJobName(jobName);
        record.setJobResult(result == null ? "" : String.valueOf(jobExecutionContext.getResult()));
        record.setNextFireTime(nextFireTime);
        record.setPreviousFireTime(previousFireTime);
        record.setRefireCount((long) refireCount);
        record.setScheduledFireTime(scheduledFireTime);
        record.setSchedulerInstanceId(schedulerInstanceId);
        record.setTriggerGroup(triggerGroup);
        record.setTriggerName(triggerName);

        return record;
    }

    private void saveJobLog(JobLogs jobLogs) {
        applicationContext = ApplicationContextHolder.getApplicationContext();
        jobLogsService = (JobLogsService) applicationContext.getBean(JobLogsService.class);
        jobLogsService.saveJobLog(jobLogs);
    }

}
