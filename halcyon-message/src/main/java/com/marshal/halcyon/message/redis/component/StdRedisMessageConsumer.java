package com.marshal.halcyon.message.redis.component;

import com.marshal.halcyon.message.IMessageConsumer;
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
public class StdRedisMessageConsumer implements IMessageConsumer {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public void consume(String queue) {
        String value = redisTemplate.opsForList().rightPop(queue);
        log.debug("consume success , the value is " + value);
    }
}
