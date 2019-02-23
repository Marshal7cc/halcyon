package com.marshal.halcyon.message.activemq.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import java.util.concurrent.Executors;

/**
 * @auth: Marshal
 * @date: 2019/2/23
 * @desc: ActiveMQ配置文件
 */
@Configuration
public class ActiveMQConfig {

    /**
     * 用于发布订阅
     * @param connectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(true);
        factory.setConnectionFactory(connectionFactory);
        factory.setTaskExecutor(Executors.newFixedThreadPool(6));
        factory.setConcurrency("6");
        return factory;
    }

    /**
     * 用于生产消费
     * @param connectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(false);
        factory.setConnectionFactory(connectionFactory);
        factory.setTaskExecutor(Executors.newFixedThreadPool(6));
        factory.setConcurrency("6");
        return factory;
    }


}
