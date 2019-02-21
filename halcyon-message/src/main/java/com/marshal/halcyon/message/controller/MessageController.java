package com.marshal.halcyon.message.controller;

import com.marshal.halcyon.message.component.impl.MessagePublisher;
import com.marshal.halcyon.message.component.impl.SysRequestMessageSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    MessagePublisher messagePublisher;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @RequestMapping("/publish")
    public void publish(){
//        redisTemplate.delete(SysRequestMessageSubscriber.h);
    }
}
