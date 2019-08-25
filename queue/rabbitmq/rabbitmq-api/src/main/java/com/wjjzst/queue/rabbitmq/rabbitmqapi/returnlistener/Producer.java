package com.wjjzst.queue.rabbitmq.rabbitmqapi.returnlistener;

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
        String exchangeName = "test_return_exchange";
        String routingKey = "return.save";
        String routingKeyError = "abc.save";

        // 5 通过channel发送数据
        for (int i = 0; i < 100; i++) {
            String msg = "Hello RabbitMQ! 4 Return Message!";
            msg += " ----> " + i;
            // basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
            // 不传exchange: AMQP default(第一个)
            // channel.basicPublish(exchangeName, routingKey, true, null, msg.getBytes());
            channel.basicPublish(exchangeName, routingKeyError, true, null, msg.getBytes());
        }
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("--------ack!--------");
                System.err.println(deliveryTag);

            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("--------no ack! --------");
                System.err.println(deliveryTag);
            }
        });
        channel.addReturnListener(new ReturnListener() {
            @Override
            // 响应码 文本 路由 配置 消息体内容
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.err.println("-------- handle return --------");
                System.err.println("replyCode: " + replyCode);
                System.err.println("replyText: " + replyText);
                System.err.println("exchange: " + exchange);
                System.err.println("routingKey: " + routingKey);
                System.err.println("properties: " + properties);
                System.err.println("body: " + new String(body));
            }
        });
        // 6 记得关闭相关的连接
        // channel.close();
        // connection.close();
    }
}
