package com.marshal.halcyon.message.activemq.component;

import com.marshal.halcyon.message.IMessageSubscriber;

/**
 * @auth: Marshal
 * @date: 2019/2/23
 * @desc:
 */
public abstract class ACMQMessageSubscriber implements IMessageSubscriber {

    @Override
    public void onMessage(Object message) {

    }

    @Override
    public String getTopicName() {
        return null;
    }
}
