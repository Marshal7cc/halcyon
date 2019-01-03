package com.marshal.halcyon.message.component;

/**
 * @auth: Marshal
 * @date: 2018/12/8
 * @desc: redis消息发布器
 */
public interface IMessagePublisher {

    void publish(String channel, Object message);

}
