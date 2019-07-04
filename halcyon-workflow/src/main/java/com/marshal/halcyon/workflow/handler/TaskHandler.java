package com.marshal.halcyon.workflow.handler;

import com.marshal.halcyon.core.component.SessionContext;
import com.marshal.halcyon.workflow.entity.TaskActionRequestExt;
import com.marshal.halcyon.workflow.exception.TaskHandleException;
import org.activiti.engine.task.Task;

/**
 * @auth: Marshal
 * @date: 2019/7/4
 * @desc: 流程任务处理器
 */
public interface TaskHandler {

    /**
     * 任务处理器唯一标识，根据任务处理行为区分
     *
     * @return
     */
    String getAction();

    /**
     * 任务处理器前置处理
     *
     * @throws TaskHandleException
     */
    default void preHandler(SessionContext sessionContext, Task taskEntity, TaskActionRequestExt actionRequest) throws TaskHandleException {

    }

    /**
     * 任务处理器主要处理过程
     *
     * @throws TaskHandleException
     */
    void process(SessionContext sessionContext, Task taskEntity, TaskActionRequestExt actionRequest) throws TaskHandleException;

    /**
     * 任务处理器后置处理
     *
     * @throws TaskHandleException
     */
    default void postHandler(SessionContext sessionContext, Task taskEntity, TaskActionRequestExt actionRequest) throws TaskHandleException {

    }

    /**
     * 任务处理facade
     *
     * @throws TaskHandleException
     */
    default void handle(SessionContext sessionContext, Task taskEntity, TaskActionRequestExt actionRequest) throws TaskHandleException {

        preHandler(sessionContext, taskEntity, actionRequest);

        process(sessionContext, taskEntity, actionRequest);

        postHandler(sessionContext, taskEntity, actionRequest);

    }
}
