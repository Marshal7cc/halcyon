package com.marshal.mcap.quartz.plugin;

import com.marshal.mcap.quartz.listener.JobRunningListener;
import org.quartz.ListenerManager;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.spi.ClassLoadHelper;
import org.quartz.spi.SchedulerPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/11/1
 * Time: 22:45
 * Description:quartz.properties===>指定Quartz插件===>注册listener
 */
public class RunningListenerPlugin implements SchedulerPlugin {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationContext applicationContext;

    private Scheduler scheduler;

    /**
     * 自动加载配置文件的配置,加上get/set后自动以配置文件的属性注入
     */
    private boolean logRunningInfo;

    @Override
    public void initialize(String s, Scheduler scheduler, ClassLoadHelper classLoadHelper) throws SchedulerException {
        this.scheduler = scheduler;
    }

    /**
     * 调度器启动后注册listener
     */
    @Override
    public void start() {
        try {
            applicationContext = (ApplicationContext) scheduler.getContext().get("applicationContext");
            ListenerManager listenerManager = scheduler.getListenerManager();
            if (isLogRunningInfo()) {
                listenerManager.addJobListener(new JobRunningListener(applicationContext), EverythingMatcher.allJobs());
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() {

    }

    public boolean isLogRunningInfo() {
        return logRunningInfo;
    }

    public void setLogRunningInfo(boolean logRunningInfo) {
        this.logRunningInfo = logRunningInfo;
    }
}
