package com.marshal.halcyon.message;

/**
 * @auth: Marshal
 * @date: 2018/12/8
 * @desc: 消息发布器
 */
public interface IMessagePublisher {

    void publish(String channel, Object message);

}
