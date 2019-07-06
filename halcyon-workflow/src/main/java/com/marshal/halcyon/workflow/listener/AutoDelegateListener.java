package com.marshal.halcyon.workflow.listener;

import com.marshal.halcyon.base.hr.service.HrEmployeeService;
import com.marshal.halcyon.core.listener.ContextRefreshedListener;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2019/7/6
 * @desc: 流程自动转交监听器
 */
@Component
public class AutoDelegateListener implements TaskListener, ContextRefreshedListener {

    private TaskService taskService;

    @Autowired
    private HrEmployeeService hrEmployeeService;

    @Override
    public void notify(DelegateTask delegateTask) {
        TaskEntity task = (TaskEntity) delegateTask;


    }

    @Override
    public void contextInitialized(ApplicationContext applicationContext) {
        taskService = applicationContext.getBean(TaskService.class);
    }
}
