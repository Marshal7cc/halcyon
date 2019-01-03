package com.marshal.halcyon.quartz.service;

import com.marshal.halcyon.quartz.entity.JobRunningInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * author: Marshal
 * Date: 2018/11/1
 * Time: 21:57
 * Description:
 */
public interface JobRunningInfoService {

    List<JobRunningInfo> queryJobRunningInfo(JobRunningInfo condition, int pageNum, int pageSize);

    void saveJobRunningInfo(JobRunningInfo jobRunningInfo);
}
