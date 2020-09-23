package com.wjjzst.queue.rabbitmq.rabbitmqapi.quickstart;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
        // 4 通过channel发送数据
        for (int i = 0; i < 5; i++) {
            String msg = "Hello RabbitMQ!";
            msg += " ----> " + i;
            // basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
            // 不传exchange: AMQP default(第一个)
            channel.basicPublish("", "test001", null, msg.getBytes());
        }
        // 5 记得关闭相关的连接
        channel.close();
        connection.close();
    }
    public static void connectionFactory() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(AMQP.PROTOCOL.PORT);
        factory.setUsername("wjj");
        factory.setPassword("wzzst310");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // 声明一个接收被删除的消息的交换机和队列
        String EXCHANGE_DEAD_NAME = "exchange.dead";
        String QUEUE_DEAD_NAME = "queue_dead";
        channel.exchangeDeclare(EXCHANGE_DEAD_NAME, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(QUEUE_DEAD_NAME, false, false, false, null);
        channel.queueBind(QUEUE_DEAD_NAME, EXCHANGE_DEAD_NAME, "routingkey.dead");
        String EXCHANGE_NAME = "exchange.fanout";
        String QUEUE_NAME = "queue_name";
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        Map<String, Object> arguments = new HashMap<String, Object>();
        // 统一设置队列中的所有消息的过期时间
        arguments.put("x-message-ttl", 30000);
        // 设置超过多少毫秒没有消费者来访问队列，就删除队列的时间
        arguments.put("x-expires", 20000);
        // 设置队列的最新的N条消息，如果超过N条，前面的消息将从队列中移除掉
        arguments.put("x-max-length", 4);
        // 设置队列的内容的最大空间，超过该阈值就删除之前的消息
        //
        arguments.put("x-max-length-bytes", 1024);
        // 将删除的消息推送到指定的交换机，一般x-dead-letter-exchange和x-dead-letter-routing-key需要同时设置
        arguments.put("x-dead-letter-exchange", "exchange.dead");
        // 将删除的消息推送到指定的交换机对应的路由键
        arguments.put("x-dead-letter-routing-key", "routingkey.dead");
        // 设置消息的优先级，优先级大的优先被消费 arguments.put("x-max-priority", 10);
        channel.queueDeclare(QUEUE_NAME, false, false, false, arguments);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
        String message = "Hello RabbitMQ: ";
        for (int i = 1; i <= 5; i++) {
            // expiration: 设置单条消息的过期时间
            AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties().builder().priority(i).expiration(i * 1000 + "");
            channel.basicPublish(EXCHANGE_NAME, "", properties.build(), (message + i).getBytes("UTF-8"));
        }
        channel.close();
        connection.close();
    }
}
