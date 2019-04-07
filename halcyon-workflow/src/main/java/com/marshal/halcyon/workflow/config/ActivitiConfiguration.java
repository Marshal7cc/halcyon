package com.marshal.halcyon.workflow.config;

import com.marshal.halcyon.workflow.component.ActivitiBeanProvider;
import com.marshal.halcyon.workflow.manager.CustomGroupEntityManagerFactory;
import com.marshal.halcyon.workflow.manager.CustomUserEntityManager;
import com.marshal.halcyon.workflow.manager.CustomGroupEntityManager;
import com.marshal.halcyon.workflow.manager.CustomUserEntityManagerFactory;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.activiti.engine.impl.persistence.entity.data.GroupDataManager;
import org.activiti.engine.impl.persistence.entity.data.UserDataManager;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @auth: Marshal
 * @date: 2019/2/5
 * @desc: Activiti工作流配置文件
 */
@Configuration
public class ActivitiConfiguration extends AbstractProcessEngineAutoConfiguration {

    @Autowired
    DataSource dataSource;

    @Autowired
    ActivitiBeanProvider activitiBeanProvider;

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(PlatformTransactionManager transactionManager,
                                                                             SpringAsyncExecutor springAsyncExecutor) throws IOException {
        SpringProcessEngineConfiguration springProcessEngineConfiguration = baseSpringProcessEngineConfiguration(
                dataSource,
                transactionManager,
                springAsyncExecutor);

        //设置字体，防止流程图中文乱码
        springProcessEngineConfiguration.setActivityFontName("宋体");
        springProcessEngineConfiguration.setLabelFontName("宋体");
        springProcessEngineConfiguration.setAnnotationFontName("宋体");

        //配置beans
        springProcessEngineConfiguration.setBeans(activitiBeanProvider);

        // 配置自定义的用户和组管理
        springProcessEngineConfiguration.setUserEntityManager(customUserEntityManager());
        springProcessEngineConfiguration.setGroupEntityManager(customGroupEntityManager());

        List<SessionFactory> customSessionFactories = new ArrayList<>();
        customSessionFactories.add(customUserEntityManagerFactory());
        customSessionFactories.add(customGroupEntityManagerFactory());
        springProcessEngineConfiguration.setCustomSessionFactories(customSessionFactories);
        return springProcessEngineConfiguration;
    }


    @Bean
    UserEntityManager customUserEntityManager() {
        return new CustomUserEntityManager();
    }

    @Bean
    GroupEntityManager customGroupEntityManager() {
        return new CustomGroupEntityManager();
    }

    @Bean
    CustomGroupEntityManagerFactory customGroupEntityManagerFactory() {
        return new CustomGroupEntityManagerFactory();
    }

    @Bean
    CustomUserEntityManagerFactory customUserEntityManagerFactory() {
        return new CustomUserEntityManagerFactory();
    }
}
