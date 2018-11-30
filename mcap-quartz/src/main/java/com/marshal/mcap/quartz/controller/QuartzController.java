package com.marshal.mcap.quartz.controller;

import com.marshal.mcap.common.controller.BaseController;
import com.marshal.mcap.core.beans.ResponseData;
import com.marshal.mcap.quartz.entity.JobCreateInfo;
import com.marshal.mcap.quartz.entity.JobDetails;
import com.marshal.mcap.quartz.entity.JobRunningInfo;
import com.marshal.mcap.quartz.service.JobRunningInfoService;
import com.marshal.mcap.quartz.service.QuartzService;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/11/1
 * Time: 20:05
 * Description:定时任务控制器
 */
@RestController
@RequestMapping("/quartz")
public class QuartzController extends BaseController {

    @Autowired
    QuartzService quartzService;

    @Autowired
    JobRunningInfoService jobRunningInfoService;

    /**
     * 启动调度器
     *
     * @return
     * @throws SchedulerException
     */
    @RequestMapping("/scheduler/startup")
    public ResponseData startup() throws SchedulerException {
        if (quartzService.startup()) {
            return new ResponseData(true, "启动成功！");
        }
        return new ResponseData(false, "已经启动！请勿重复操作！");
    }

    @RequestMapping("/scheduler/standby")
    public ResponseData standby() throws SchedulerException {
        if (quartzService.standby()) {
            return new ResponseData(true, "关闭成功！");
        }
        return new ResponseData(false, "已经关闭，请勿重复操作！");
    }



    /**
     * 获取jobDetail信息
     *
     * @param condition
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/jobDetail/query")
    public ResponseData queryJobDetail(@RequestBody JobDetails condition,
                                       @RequestParam("pageNum") int pageNum,
                                       @RequestParam("pageSize") int pageSize) {
        return new ResponseData(quartzService.queryJobDetail(condition, pageNum, pageSize));
    }

    @RequestMapping("/job/pauseJob")
    public ResponseData pauseJob(@RequestParam String jobName, @RequestParam String jobGroup) throws SchedulerException {
        quartzService.pauseJob(jobName, jobGroup);
        return new ResponseData(true, "暂停成功");

    }

    @RequestMapping("/job/resumeJob")
    public ResponseData resumeJob(@RequestParam String jobName, @RequestParam String jobGroup) throws SchedulerException {
        quartzService.resumeJob(jobName, jobGroup);
        return new ResponseData(true, "恢复成功");
    }

    @RequestMapping("/job/deleteJob")
    public ResponseData deleteJob(@RequestParam String jobName, @RequestParam String jobGroup) throws SchedulerException {
        quartzService.deleteJob(jobName, jobGroup);
        return new ResponseData(true, "删除成功");
    }

    @RequestMapping("/job/createJob")
    public ResponseData resumeJob(@RequestBody JobCreateInfo jobCreateInfo) throws Exception {
        if (!getValidator().isValid(jobCreateInfo)) {
            return new ResponseData(false, getValidator().getErrors(jobCreateInfo));
        }
        jobCreateInfo.setTriggerGroup(jobCreateInfo.getJobGroup());
        jobCreateInfo.setTriggerName(jobCreateInfo.getJobName()+"_trigger");
        quartzService.createJob(jobCreateInfo);
        return new ResponseData(true,"新增成功!");
    }

    /**
     * 获取任务执行记录
     *
     * @param condition
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/jobRunningInfo/query")
    public ResponseData queryJobRunningInfo(@RequestBody JobRunningInfo condition,
                                            @RequestParam("pageNum") int pageNum,
                                            @RequestParam("pageSize") int pageSize) {
        return new ResponseData(jobRunningInfoService.queryJobRunningInfo(condition, pageNum, pageSize));
    }

}
