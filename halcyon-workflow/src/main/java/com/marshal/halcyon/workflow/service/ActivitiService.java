package com.marshal.halcyon.workflow.service;

import com.marshal.halcyon.workflow.entity.TaskResponseExt;
import org.activiti.engine.repository.Model;

import java.util.List;

public interface ActivitiService {

    Model deployModel(String modelId) throws Exception;

    List<TaskResponseExt> getTaskList(List<TaskResponseExt> taskList);

    TaskResponseExt getTaskDetail(TaskResponseExt task);

}
