package com.marshal.halcyon.message.component.impl;

import com.marshal.halcyon.message.component.IMessageProducer;
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
public class SimpleMessageProducer implements IMessageProducer {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public void produce(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
        log.debug("produce success , the value is " + value);
    }
}
