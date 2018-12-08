package com.marshal.mcap.message.component;

import com.marshal.mcap.message.component.IMessageSubscriber;
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
public class RequestInfoSubscriber implements IMessageSubscriber {

    public static final String h = "mcap:sysRequestInfo";

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public void onMessage(Object message) {
        String hk = UUID.randomUUID().toString();
        redisTemplate.opsForHash().put(h, hk, message);
    }

}
