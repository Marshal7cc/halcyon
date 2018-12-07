package com.marshal.mcap.message.component;

import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2018/12/6
 * @desc: redis消息监听器抽象接口
 */

public interface IMessageSubscriber {

    void onMessage(Object message);

}
