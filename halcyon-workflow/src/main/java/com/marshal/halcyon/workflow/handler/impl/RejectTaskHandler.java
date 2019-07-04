package com.marshal.halcyon.workflow.handler.impl;

import com.marshal.halcyon.core.component.SessionContext;
import com.marshal.halcyon.workflow.constant.ActivitiConstant;
import com.marshal.halcyon.workflow.entity.TaskActionRequestExt;
import com.marshal.halcyon.workflow.exception.TaskHandleException;
import com.marshal.halcyon.workflow.handler.TaskHandler;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * @auth: Marshal
 * @date: 2019/7/4
 * @desc: 任务拒绝处理器
 */
public class RejectTaskHandler implements TaskHandler {

    @Autowired
    private TaskService taskService;

    @Override
    public String getAction() {
        return ActivitiConstant.ACTION_REJECT;
    }

    @Override
    public void process(SessionContext sessionContext, Task taskEntity, TaskActionRequestExt actionRequest) throws TaskHandleException {
        String taskId = taskEntity.getId();

        Authentication.setAuthenticatedUserId(sessionContext.getEmployeeCode());

        taskService.addComment(taskId, taskEntity.getProcessInstanceId(), "action", actionRequest.getAction());
        taskService.addComment(taskId, taskEntity.getProcessInstanceId(), "comment", actionRequest.getComment());

        HashMap<String, Object> variables = new HashMap<>();
        variables.put("approveResult", "reject");
        taskService.setVariables(taskId, variables);
        taskService.complete(taskId);
    }
}
