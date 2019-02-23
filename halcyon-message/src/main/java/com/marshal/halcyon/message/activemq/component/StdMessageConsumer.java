package com.marshal.halcyon.message.activemq.component;

import com.marshal.halcyon.message.IMessageConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @auth: Marshal
 * @date: 2019/2/23
 * @desc: 标准activeMQ消费者
 */
@Service
public class StdMessageConsumer implements IMessageConsumer {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    @JmsListener(destination = "queue:std", containerFactory = "queueListenerFactory")
    public void consume(String key) {
        System.out.println("消费者1拿到消息" + key);
    }
}
