package com.wjjzst.queue.rabbitmq.rabbitmqapi.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: Wjj
 * @Date: 2019/8/22 1:03
 * @desc:
 */
public class Producer {
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
        // 4 指定我们的消息投递模式: 消息的确认模式
        channel.confirmSelect();
        // 5 声明
        String exchangeName = "test_consumer_exchange";
        String routingKey = "consumer.save";

        // 5 通过channel发送数据
        for (int i = 0; i < 100; i++) {
            String msg = "Hello RabbitMQ! 4 Consumer Message!";
            msg += " ----> " + i;
            // basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
            // 不传exchange: AMQP default(第一个)
            channel.basicPublish(exchangeName, routingKey, true, null, msg.getBytes());
        }
        // 6 记得关闭相关的连接
        // channel.close();
        // connection.close();
    }
}
