package com.marshal.halcyon.quartz.mapper;

import com.marshal.halcyon.quartz.entity.JobDetails;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface JobDetailsMapper extends Mapper<JobDetails> {

    List<JobDetails> queryJobDetailsInfo(JobDetails condition);
}