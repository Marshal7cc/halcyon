package com.marshal.mcap.message.controller;

import com.marshal.mcap.core.beans.ResponseData;
import com.marshal.mcap.message.component.MessagePublisher;
import com.marshal.mcap.message.component.RequestInfoSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

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
        redisTemplate.delete(RequestInfoSubscriber.h);
//        redisTemplate.opsForHash().put("mcap:requestInfo","key2","value1");
//        redisTemplate.opsForHash().put("requestInfo","key2","value1");
//        redisTemplate.opsForHash().put("mcap:LEAF:requestInfo1","key2","value1");
//        redisTemplate.opsForHash().put("mcap:requestInfo2","key2","value1");
//        Map<Object,Object> map = redisTemplate.opsForHash().entries("mcap:requestInfo");

    }
}
