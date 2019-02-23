package com.marshal.halcyon.message.controller;

import com.marshal.halcyon.message.activemq.component.ACMQMessageProducer;
import com.marshal.halcyon.message.activemq.component.ACMQMessagePublisher;
import com.marshal.halcyon.message.redis.component.RedisMessageProducer;
import com.marshal.halcyon.message.redis.component.RedisMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auth: Marshal
 * @date: 2018/12/6
 * @desc:
 */
@RestController
@RequestMapping("/sys/message/")
public class MessageController {

    @Autowired
    ACMQMessagePublisher ACMQMessagePublisher;

    @Autowired
    ACMQMessageProducer ACMQMessageProducer;

    @Autowired
    RedisMessagePublisher redisMessagePublisher;

    @Autowired
    RedisMessageProducer redisMessageProducer;

    @RequestMapping("/acmq/topic/publish")
    public void publish() {
        ACMQMessagePublisher.publish("topic:std", "哈哈哈评到");
    }

    @RequestMapping("/acmq/queue/publish")
    public void queue() {
        ACMQMessageProducer.produce("queue:std", "哈哈哈队列");
    }

    @RequestMapping("/redis/topic/publish")
    public void publishRedis() {
        redisMessagePublisher.publish("topic:std", "哈哈哈评到");
    }

    @RequestMapping("/redis/queue/publish")
    public void queueRedis() {
        redisMessageProducer.produce("queue:std", "哈哈哈队列");
    }
}
