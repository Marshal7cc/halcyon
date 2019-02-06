package com.marshal.halcyon.workflow.manager;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2019/2/5
 * @desc:
 */
@Component
public class CustomUserEntityManagerFactory implements SessionFactory {

    @Autowired
    CustomUserEntityManager customUserEntityManager;

    @Override
    public Class<?> getSessionType() {
        return UserEntityManager.class;
    }

    @Override
    public Session openSession(CommandContext commandContext) {
        return customUserEntityManager;
    }
}
