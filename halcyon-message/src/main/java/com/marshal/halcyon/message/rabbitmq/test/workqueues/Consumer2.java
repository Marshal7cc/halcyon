package com.marshal.halcyon.message.rabbitmq.test.workqueues;

import com.marshal.halcyon.message.rabbitmq.test.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @auth: Marshal
 * @date: 2019/7/20
 * @desc:
 */
public class Consumer2 {

    private static final String QUEUE_NAME = "halcyon_work_queue";

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

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, StandardCharsets.UTF_8);
                System.out.println("consumer2 rev msg:" + msg);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //公平分发的设置
                    if ("fair".equals(Producer.MODE)) {
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }
                }
            }
        };

        //监听队列
        boolean autoAck = true;

        //公平分发的设置
        if ("fair".equals(Producer.MODE)) {
            autoAck = false;
        }

        channel.basicConsume(QUEUE_NAME, autoAck, consumer);


    }
}
