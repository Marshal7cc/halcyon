package com.marshal.halcyon.workflow.service;

import com.marshal.halcyon.core.component.SessionContext;
import com.marshal.halcyon.workflow.entity.HistoricProcessInstanceResponseExt;
import com.marshal.halcyon.workflow.exception.TaskActionException;
import com.marshal.halcyon.workflow.entity.TaskActionRequestExt;
import com.marshal.halcyon.workflow.entity.TaskResponseExt;
import org.activiti.engine.task.Task;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ActivitiService {

    /**
     * 工作流任务处理
     *
     * @param sessionContext
     * @param taskId
     * @param taskActionRequest
     * @param isAdmin
     * @throws TaskActionException
     */
    @Transactional
    void executeTaskAction(SessionContext sessionContext, String taskId, TaskActionRequestExt taskActionRequest, boolean isAdmin)
            throws TaskActionException, IllegalArgumentException;

    void executeTaskByAdmin(SessionContext sessionContext, String procId, TaskActionRequestExt taskActionRequest)
            throws TaskActionException;

    void completeTask(SessionContext sessionContext, Task taskEntity, TaskActionRequestExt actionRequest) throws TaskActionException;

    void rejectTask(SessionContext sessionContext, Task taskEntity, TaskActionRequestExt actionRequest) throws TaskActionException;

    void delegateTask(SessionContext sessionContext, Task taskEntity, TaskActionRequestExt actionRequest) throws TaskActionException;

    void carbonCopy(SessionContext sessionContext, Task taskEntity, TaskActionRequestExt actionRequest) throws TaskActionException;

    void resolveTask(SessionContext sessionContext, Task taskEntity, TaskActionRequestExt actionRequest) throws TaskActionException;

    void jumpTo(SessionContext sessionContext, Task taskEntity, TaskActionRequestExt actionRequest);


    List<TaskResponseExt> getTaskList(List<TaskResponseExt> taskList);

    TaskResponseExt getTaskDetail(TaskResponseExt task);

    List<HistoricProcessInstanceResponseExt> getHistoricProcessInstanceList(List<HistoricProcessInstanceResponseExt> historicProcessInstanceList);

    HistoricProcessInstanceResponseExt getProcessInstanceDetail(SessionContext sessionContext, String processInstanceId);

}
