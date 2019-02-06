package com.marshal.halcyon.workflow.manager;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2019/2/5
 * @desc:
 */
@Component
public class CustomGroupEntityManagerFactory implements SessionFactory {

    @Autowired
    CustomGroupEntityManager customGroupEntityManager;

    @Override
    public Class<?> getSessionType() {
        return GroupEntityManager.class;
    }

    @Override
    public Session openSession(CommandContext commandContext) {
        return customGroupEntityManager;
    }
}
