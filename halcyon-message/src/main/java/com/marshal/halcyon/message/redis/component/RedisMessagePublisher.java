package com.marshal.halcyon.message.redis.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marshal.halcyon.message.IMessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2018/12/6
 * @desc: 标准redis消息发布器
 */
@Component
public class RedisMessagePublisher implements IMessagePublisher {

    private Logger logger = LoggerFactory.getLogger(RedisMessagePublisher.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public void publish(String topic, Object message) {
        if (message instanceof String || message instanceof Number) {
            redisTemplate.convertAndSend(topic, message.toString());
        } else {
            try {
                redisTemplate.convertAndSend(topic, objectMapper.writeValueAsString(message));
            } catch (JsonProcessingException e) {
                if (logger.isErrorEnabled()) {
                    logger.error("publish message failed.", e);
                }
            }
        }
    }
}
