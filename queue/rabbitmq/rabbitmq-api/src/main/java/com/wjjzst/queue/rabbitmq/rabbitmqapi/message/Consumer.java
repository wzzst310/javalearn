package com.wjjzst.queue.rabbitmq.rabbitmqapi.message;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author: Wjj
 * @Date: 2019/8/22 1:03
 * @desc:
 */
public class Consumer {
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
        // 4声明(创建) 一个队列
        String queueName = "test001";
        channel.queueDeclare(queueName, true, false, false, null);
        // 5 创建一个消费者
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("Received message : " + msg);
                Map<String, Object> headers = properties.getHeaders();
                System.err.println("headers get my1 value:  " + headers.get("my1"));
                System.err.println("headers get my2 value:  " + headers.get("my2"));
                // 处理完业务之后，手动删除消息
                // this.getChannel().basicAck(envelope.getDeliveryTag(), false);
            }
        };
        // 6 设置Channel
        channel.basicConsume(queueName, true, defaultConsumer);
        try {
            TimeUnit.DAYS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 5 记得关闭相关的连接
        channel.close();
        connection.close();
    }
}
