package com.marshal.halcyon.quartz.job;

import org.quartz.*;

public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        try {
            /**
             * 打印instance_id,测试集群环境
             */
            System.out.println(jobExecutionContext.getScheduler().getSchedulerInstanceId());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        System.out.println("------------------------");
        System.out.println("Hello Job Execute");
        System.out.println("------------------------");
    }
}
