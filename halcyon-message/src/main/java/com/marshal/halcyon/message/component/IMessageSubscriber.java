package com.marshal.halcyon.message.component;

/**
 * @auth: Marshal
 * @date: 2018/12/6
 * @desc: redis消息监听器
 */
public interface IMessageSubscriber {

    String onMessageMethodName = "onMessage";

    /**
     * 监听到消息时的方法
     *
     * @param message
     */
    void onMessage(Object message);

    String getChannelName();

}
