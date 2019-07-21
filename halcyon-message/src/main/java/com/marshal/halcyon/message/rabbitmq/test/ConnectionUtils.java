package com.marshal.halcyon.message.rabbitmq.test;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


/**
 * @auth: Marshal
 * @date: 2019/7/20
 * @desc:
 */
public class ConnectionUtils {

    public static Connection getConnection() {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("127.0.0.1");

        connectionFactory.setVirtualHost("/halcyon");

        connectionFactory.setUsername("halcyon_dev");

        connectionFactory.setPassword("halcyon_dev");

        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            return null;
        }
    }


}
