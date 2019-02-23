package com.marshal.halcyon.message.redis.component;

import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2019/2/23
 * @desc:
 */
@Component
public class StdRedisMessageSubscriber extends RedisMessageSubscriber {

    private static final String channelName = "topic:std";

    @Override
    public void onMessage(Object message) {

    }

    @Override
    public String getChannelName() {
        return channelName;
    }
}
