package com.marshal.halcyon.message.activemq.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2019/2/23
 * @desc: 标准activeMQ订阅者
 */
@Component
public class StdMessageSubscriber extends ACMQMessageSubscriber {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    @JmsListener(destination = "topic:std", containerFactory = "topicListenerFactory")
    public void onTextMessage(String text) {
        System.out.println("订阅者拿到消息" + text);
    }

}
