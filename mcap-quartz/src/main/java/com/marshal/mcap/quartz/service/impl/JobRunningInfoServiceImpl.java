package com.marshal.mcap.quartz.service.impl;

import com.github.pagehelper.PageHelper;
import com.marshal.mcap.quartz.entity.JobRunningInfo;
import com.marshal.mcap.quartz.mapper.JobRunningInfoMapper;
import com.marshal.mcap.quartz.service.JobRunningInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auth: Marshal
 * @date: 2018/11/1
 * @desc:
 */
@Service
public class JobRunningInfoServiceImpl implements JobRunningInfoService {

    @Autowired
    JobRunningInfoMapper jobRunningInfoMapper;

    /**
     * for 注入到Listener
     */
    public JobRunningInfoServiceImpl() {
    }

    @Override
    public List<JobRunningInfo> queryJobRunningInfo(JobRunningInfo condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return jobRunningInfoMapper.select(condition);
    }

    @Override
    public void saveJobRunningInfo(JobRunningInfo jobRunningInfo) {
        jobRunningInfoMapper.insertSelective(jobRunningInfo);
    }
}
