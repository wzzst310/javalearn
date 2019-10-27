package com.wjjzst.queue.rabbitmq.rabbitmqapi.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: Wjj
 * @Date: 2019/8/22 1:03
 * @desc:
 */
public class Producer4TopicExchange {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1 创建连接工厂ConnectionFactory 并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("129.204.35.106");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("wjj");
        connectionFactory.setPassword("wzzst310");

        // 2 通过连接工厂创建一个连接
        Connection connection = connectionFactory.newConnection();
        // 3 通过connection创建一个channel
        Channel channel = connection.createChannel();
        // 4 声明
        String exchangeName = "test_topic_exchange";
        String routingKey1 = "user.save";
        String routingKey2 = "user.update";
        String routingKey3 = "user.delete.abc";
        // 5 通过channel发送数据
        String msg = "Hello RabbitMQ! 4 Topic Exchange Message";
        // basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
        // 不传exchange: AMQP default(第一个)
        channel.basicPublish(exchangeName, routingKey1, null, (msg + " ----> 1").getBytes());
        channel.basicPublish(exchangeName, routingKey2, null, (msg + " ----> 2").getBytes());
        channel.basicPublish(exchangeName, routingKey3, null, (msg + " ----> 3").getBytes());
        // 6 记得关闭相关的连接
        channel.close();
        connection.close();
    }
}
