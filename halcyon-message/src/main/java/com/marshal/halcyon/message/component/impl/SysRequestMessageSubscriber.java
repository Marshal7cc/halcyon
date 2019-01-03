package com.marshal.halcyon.message.component.impl;

import com.marshal.halcyon.message.component.IMessageSubscriber;
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
public class SysRequestMessageSubscriber implements IMessageSubscriber {

    public static final String h = "halcyon:sysRequestInfo";

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public void onMessage(Object message) {
        String hk = UUID.randomUUID().toString();
        redisTemplate.opsForHash().put(h, hk, message);
    }

}
