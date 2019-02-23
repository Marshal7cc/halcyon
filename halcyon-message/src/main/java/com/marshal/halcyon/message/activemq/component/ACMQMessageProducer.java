package com.marshal.halcyon.message.activemq.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marshal.halcyon.message.IMessageProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2019/2/23
 * @desc:
 */
@Component
public class ACMQMessageProducer implements IMessageProducer {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void produce(String key, Object message) {
        jmsMessagingTemplate.convertAndSend(new ActiveMQQueue(key), message);
    }
}
