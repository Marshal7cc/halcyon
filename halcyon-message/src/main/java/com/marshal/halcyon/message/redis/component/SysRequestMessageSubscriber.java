package com.marshal.halcyon.message.redis.component;

import com.marshal.halcyon.message.IMessageSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @auth: Marshal
 * @date: 2018/12/7
 * @desc:
 */
@Component
public class SysRequestMessageSubscriber extends RedisMessageSubscriber {

    public static final String h = "halcyon:cache:sys_request";

    public static final String topic = "halcyon:topic:sys_request";

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public void onMessage(Object message) {
        String hk = UUID.randomUUID().toString();
        redisTemplate.opsForHash().put(h, hk, message);
    }

    @Override
    public String getTopicName() {
        return topic;
    }

}
