package com.marshal.halcyon.message;

/**
 * @auth: Marshal
 * @date: 2018/12/6
 * @desc: 消息监听器
 */
public interface IMessageSubscriber {

    void onMessage(Object message);

    void onTextMessage(String text);

    String getTopicName();

}
