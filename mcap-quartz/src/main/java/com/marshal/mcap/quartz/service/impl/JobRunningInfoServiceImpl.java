package com.marshal.mcap.quartz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.marshal.mcap.quartz.entity.JobRunningInfo;
import com.marshal.mcap.quartz.mapper.JobRunningInfoMapper;
import com.marshal.mcap.quartz.service.JobRunningInfoService;
import com.marshal.mcap.core.beans.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/11/1
 * Time: 22:03
 * Description:
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
