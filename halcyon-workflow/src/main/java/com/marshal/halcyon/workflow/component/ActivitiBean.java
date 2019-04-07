package com.marshal.halcyon.workflow.component;

/**
 * 实现该接口的类，将可以在 activiti 流程中调用
 *
 * @author Marshal
 */
public interface ActivitiBean {

    default String getBeanName() {
        return null;
    }

}
