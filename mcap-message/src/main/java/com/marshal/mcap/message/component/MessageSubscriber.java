package com.marshal.mcap.message.component;

import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2018/12/6
 * @desc: redis消息监听器
 */
@Component
public class MessageSubscriber {

    public void onMessage(Object message){
        System.out.println(message);
    }
}
