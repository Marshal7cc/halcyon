package com.marshal.halcyon.message.component.impl;

import com.marshal.halcyon.message.component.IMessageConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2018/12/9
 * @desc:
 */
@Component
@Slf4j
public class SimpleMessageConsumer extends Thread implements IMessageConsumer {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public void run() {
        super.run();
    }

    @Override
    public void consumer(String key) {
        String value = redisTemplate.opsForList().rightPop(key);
        log.debug("consume success , the value is " + value);
    }
}
