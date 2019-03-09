package com.marshal.halcyon.quartz.service.impl;

import com.github.pagehelper.PageHelper;
import com.marshal.halcyon.core.service.impl.BaseServiceImpl;
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
public class JobLogsServiceImpl extends BaseServiceImpl<JobLogs> implements JobLogsService {

    @Autowired
    JobLogsMapper jobLogsMapper;

    /**
     * for 注入到Listener
     */
    public JobLogsServiceImpl() {
    }

}
