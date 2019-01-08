package com.marshal.halcyon.quartz.service;

import com.marshal.halcyon.quartz.entity.JobLogs;

import java.util.List;

public interface JobLogsService {

    List<JobLogs> queryJobLogs(JobLogs condition, int pageNum, int pageSize);

    void saveJobLog(JobLogs jobLogs);
}
