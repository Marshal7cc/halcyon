package com.marshal.halcyon.quartz.service.impl;

import com.github.pagehelper.PageHelper;
import com.marshal.halcyon.quartz.entity.JobLogs;
import com.marshal.halcyon.quartz.mapper.JobLogsMapper;
import com.marshal.halcyon.quartz.service.JobLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auth: Marshal
 * @date: 2018/11/1
 * @desc:
 */
@Service
public class JobLogsServiceImpl implements JobLogsService {

    @Autowired
    JobLogsMapper jobLogsMapper;

    /**
     * for 注入到Listener
     */
    public JobLogsServiceImpl() {
    }

    @Override
    public List<JobLogs> queryJobLogs(JobLogs condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return jobLogsMapper.select(condition);
    }

    @Override
    public void saveJobLog(JobLogs jobLogs) {
        jobLogsMapper.insertSelective(jobLogs);
    }
}
