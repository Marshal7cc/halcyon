package com.marshal.halcyon.workflow.service;

import com.marshal.halcyon.core.component.SessionContext;
import com.marshal.halcyon.workflow.entity.HistoricProcessInstanceResponseExt;
import com.marshal.halcyon.workflow.exception.TaskHandleException;
import com.marshal.halcyon.workflow.entity.TaskActionRequestExt;
import com.marshal.halcyon.workflow.entity.TaskResponseExt;
import org.activiti.engine.task.Task;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ActivitiService {

    /**
     * 工作流任务处理
     *
     * @param sessionContext    session信息
     * @param taskId            task id
     * @param taskActionRequest 任务处理请求信息
     * @param isAdmin           是否为管理员
     * @throws TaskHandleException
     */
    void handleTask(SessionContext sessionContext, String taskId, TaskActionRequestExt taskActionRequest, boolean isAdmin)
            throws TaskHandleException, IllegalArgumentException;

    /**
     * 代办列表
     *
     * @param taskList
     * @return
     */
    List<TaskResponseExt> getTaskList(List<TaskResponseExt> taskList);

    /**
     * 查看任务详情
     *
     * @param task
     * @return
     */
    TaskResponseExt getTaskDetail(TaskResponseExt task);

    /**
     * 流程监控/历史流程列表
     *
     * @param historicProcessInstanceList
     * @return
     */
    List<HistoricProcessInstanceResponseExt> getHistoricProcessInstanceList(List<HistoricProcessInstanceResponseExt> historicProcessInstanceList);

    /**
     * 流程监控/历史流程详情
     *
     * @param sessionContext
     * @param processInstanceId
     * @return
     */
    HistoricProcessInstanceResponseExt getProcessInstanceDetail(SessionContext sessionContext, String processInstanceId);

}
