package com.marshal.halcyon.message.rabbitmq.test.routing;

import com.marshal.halcyon.message.rabbitmq.test.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


/**
 * @auth: Marshal
 * @date: 2019/7/20
 * @desc: 发布订阅模式
 */
public class Producer {

    public static final String EXCHANGE_NAME = "halcyon_exchange_direct";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //创建队列声明
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        //发送消息
        String routingKey = "info";
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, "hello_halcyon".getBytes());

        System.out.println("send  \"hello_halcyon\"");

        channel.close();

        connection.close();
    }
}
