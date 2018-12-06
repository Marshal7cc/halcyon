package com.marshal.mcap.message.controller;

import com.marshal.mcap.core.beans.ResponseData;
import com.marshal.mcap.message.component.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

    @RequestMapping("/publish")
    public void publish(){
        messagePublisher.publish("mcap","pushLish a message");
        messagePublisher.publish("mcap",new Date());
    }
}
