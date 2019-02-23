package com.marshal.halcyon.message.redis.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marshal.halcyon.message.IMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2018/12/9
 * @desc:
 */
@Slf4j
@Component
public class RedisMessageProducer implements IMessageProducer {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void produce(String queue, Object message) {
        if (message instanceof String || message instanceof Number) {
            redisTemplate.opsForList().leftPush(queue, message.toString());
        } else {
            try {
                redisTemplate.opsForList().leftPush(queue, objectMapper.writeValueAsString(message));
            } catch (JsonProcessingException e) {
                if (log.isErrorEnabled()) {
                    log.error("publish message failed.", e);
                }
            }
        }
    }
}
