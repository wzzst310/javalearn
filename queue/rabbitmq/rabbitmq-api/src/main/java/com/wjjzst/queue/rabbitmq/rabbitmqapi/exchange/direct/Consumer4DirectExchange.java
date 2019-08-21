package com.wjjzst.queue.rabbitmq.rabbitmqapi.exchange.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author: Wjj
 * @Date: 2019/8/22 1:03
 * @desc:
 */
public class Consumer4DirectExchange {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1 创建连接工厂ConnectionFactory 并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("129.204.35.106");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("wjj");
        connectionFactory.setPassword("wzzst310");
        connectionFactory.setAutomaticRecoveryEnabled(true);  // 是否支持自动重连
        connectionFactory.setNetworkRecoveryInterval(3000);  // 网络发生闪断的设置 重连时间
        // 2 通过连接工厂创建一个连接
        Connection connection = connectionFactory.newConnection();
        // 3 通过connection创建一个channel
        Channel channel = connection.createChannel();
        // 4声明(创建) 一个队列
        String exchangeName = "test_direct_exchange";
        String exchangeType = "direct";
        String queueName = "test_direct_exchange";
        String routingKey = "test.direct";
        // 表示声明了一个交换机
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        // 表示声明了一个队列
        channel.queueDeclare(queueName, true, false, false, null);
        // 建立一个绑定关系
        channel.queueBind(queueName, exchangeName, routingKey);
        // 5 创建一个消费者
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("Received message : " + msg);
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
