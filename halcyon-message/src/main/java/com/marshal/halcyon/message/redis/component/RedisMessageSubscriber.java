package com.marshal.halcyon.message.redis.component;

import com.marshal.halcyon.message.IMessageSubscriber;

/**
 * @auth: Marshal
 * @date: 2019/2/23
 * @desc: redis消息订阅者
 */
public abstract class RedisMessageSubscriber implements IMessageSubscriber {

    public static String onMessageMethodName = "onMessage";

    @Override
    public void onTextMessage(String text) {

    }
}
