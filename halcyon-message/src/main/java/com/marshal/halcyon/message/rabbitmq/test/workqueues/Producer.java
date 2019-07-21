package com.marshal.halcyon.message.rabbitmq.test.workqueues;

import com.marshal.halcyon.message.rabbitmq.test.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


/**
 * @auth: Marshal
 * @date: 2019/7/20
 * @desc: workQueue 轮询分发/公平分发
 * <p>
 * 公平分发的设置：  1 channel.basicQos(1);  2  autoAck = false;  channel.basicAck(envelope.getDeliveryTag(), false);
 */
public class Producer {

    private static final String QUEUE_NAME = "halcyon_work_queue";

    public static final String MODE = "fair";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //获取频道
        Channel channel = connection.createChannel();
        //创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);


        //公平分发的设置
        if ("fair".equals(Producer.MODE)) {
            //每个消费者发送确认消息之前，消息队列不发送下一个消息到消费者，消费者一次只处理一个消息
            //限制同时给一个消费者发的消息不超过1条
            channel.basicQos(1);
        }

        for (int i = 1; i <= 50; i++) {
            channel.basicPublish("", QUEUE_NAME, null, ("hello_halcyon" + i).getBytes());
            System.out.println("producer send message " + "hello_halcyon" + i);
            Thread.sleep(100);
        }

        channel.close();

        connection.close();
    }
}
