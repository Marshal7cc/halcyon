package com.marshal.halcyon.workflow.leavebill.task;

import com.marshal.halcyon.workflow.component.ActivitiBean;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @auth: Marshal
 * @date: 2019/3/31
 * @desc:
 */
@Component
public class EndServiceTask implements JavaDelegate, ActivitiBean {

    public void execute(DelegateExecution delegateExecution) {
        System.out.println("hahah");
    }
}
