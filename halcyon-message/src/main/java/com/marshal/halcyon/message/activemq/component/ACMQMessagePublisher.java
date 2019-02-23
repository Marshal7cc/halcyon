package com.marshal.halcyon.message.activemq.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marshal.halcyon.message.IMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2019/2/23
 * @desc:
 */
@Slf4j
@Component
public class ACMQMessagePublisher implements IMessagePublisher {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void publish(String channel, Object message) {
        if (message instanceof String || message instanceof Number) {
            jmsMessagingTemplate.convertAndSend(new ActiveMQTopic(channel), message.toString());
        } else {
            try {
                jmsMessagingTemplate.convertAndSend(new ActiveMQTopic(channel), objectMapper.writeValueAsString(message));
            } catch (JsonProcessingException e) {
                if (log.isErrorEnabled()) {
                    log.error("publish message failed.", e);
                }
            }
        }
    }
}
