package com.wang.springbootrmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author dwang
 * @create 2020-03-05 9:41
 */
public class QuickProvider {

    public static void main(String[] args) throws IOException, TimeoutException {

        //连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("wangdi");
        connectionFactory.setPassword("wangdi");
        connectionFactory.setConnectionTimeout(100000);

        //创建连接
        Connection connection = connectionFactory.newConnection();
        //创建通道channel
        Channel channel = connection.createChannel();
        for (int i = 0; i < 5; i++) {
            String msg = "hello rabbitmq"+i;
            channel.basicPublish("", "test-01", null, msg.getBytes());
        }
        channel.close();
        connection.close();
    }

}
