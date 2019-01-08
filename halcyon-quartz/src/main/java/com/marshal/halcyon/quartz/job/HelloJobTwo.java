package com.marshal.halcyon.quartz.job;

import org.quartz.*;

public class HelloJobTwo implements Job {
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
        System.out.println("Hello JobTwo Execute");
        System.out.println("------------------------");
    }
}
