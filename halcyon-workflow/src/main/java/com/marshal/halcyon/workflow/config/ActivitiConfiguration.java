package com.marshal.halcyon.workflow.config;

import com.marshal.halcyon.workflow.component.ActivitiBeanProvider;

import com.marshal.halcyon.workflow.constant.ActivitiConstant;
import com.marshal.halcyon.workflow.interceptor.HalcyonCmdInterceptor;
import org.activiti.engine.impl.interceptor.CommandInterceptor;
import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

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
    private DataSource dataSource;

    @Autowired
    private ActivitiBeanProvider activitiBeanProvider;

    @Autowired
    private HalcyonCmdInterceptor halcyonCmdInterceptor;

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(PlatformTransactionManager transactionManager,
                                                                             SpringAsyncExecutor springAsyncExecutor) throws IOException {
        SpringProcessEngineConfiguration springProcessEngineConfiguration = baseSpringProcessEngineConfiguration(
                dataSource,
                transactionManager,
                springAsyncExecutor);

        //设置字体，防止流程图中文乱码
        springProcessEngineConfiguration.setActivityFontName(ActivitiConstant.FONT_NAME);
        springProcessEngineConfiguration.setLabelFontName(ActivitiConstant.FONT_NAME);
        springProcessEngineConfiguration.setAnnotationFontName(ActivitiConstant.FONT_NAME);

        //配置beans,若不指定则spring容器中所有bean都用作代理表达式
        springProcessEngineConfiguration.setBeans(activitiBeanProvider);

        //配置cmd 拦截器
        List<CommandInterceptor> commandInterceptors = new ArrayList<>();
        commandInterceptors.add(halcyonCmdInterceptor);
        springProcessEngineConfiguration.setCustomPostCommandInterceptors(commandInterceptors);

        return springProcessEngineConfiguration;
    }

}
