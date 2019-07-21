package com.marshal.halcyon.message.rabbitmq.test.simple;

import com.marshal.halcyon.message.rabbitmq.test.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


/**
 * @auth: Marshal
 * @date: 2019/7/20
 * @desc: 简单队列
 */
public class Producer {

    private static final String QUEUE_NAME = "halcyon_simple_queue";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //获取频道
        Channel channel = connection.createChannel();
        //创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.txSelect();
        try{
            channel.basicPublish("", QUEUE_NAME, null, "hello_halcyon".getBytes());
            channel.txCommit();
        }catch (Exception e){
            channel.txRollback();
        }

//        channel.confirmSelect();
//        channel.basicPublish("", QUEUE_NAME, null, "hello_halcyon".getBytes());
//        if(channel.waitForConfirms()){
//            //success
//        }else {
//            //error
//        }

        channel.close();

        connection.close();
    }
}
