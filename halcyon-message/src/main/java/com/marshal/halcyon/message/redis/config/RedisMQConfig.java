package com.marshal.halcyon.message.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marshal.halcyon.core.util.ApplicationContextHolder;
import com.marshal.halcyon.message.IMessageSubscriber;
import com.marshal.halcyon.message.redis.component.RedisMessageSubscriber;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;
import java.util.Map;

/**
 * @auth: Marshal
 * @date: 2018/12/5
 * @desc: Redis配置
 */
@Configuration
public class RedisMQConfig {
    /**
     * redis消息监听器container
     * 可以添加多个监听不同话题的redis监听器，
     * 只需要把消息监听器和相应的消息订阅处理器绑定
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        Map<String, RedisMessageSubscriber> messageSubscribers = ApplicationContextHolder.getApplicationContext().getBeansOfType(RedisMessageSubscriber.class);
        //添加所有消息监听适配器
        messageSubscribers.forEach((k, v) -> {
            MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(v, RedisMessageSubscriber.onMessageMethodName);
            //手动执行afterPropertiesSet方法，否则无法创建invoker,导致空指针
            messageListenerAdapter.afterPropertiesSet();
            container.addMessageListener(messageListenerAdapter, new PatternTopic(v.getTopicName()));
        });
        return container;
    }

}
