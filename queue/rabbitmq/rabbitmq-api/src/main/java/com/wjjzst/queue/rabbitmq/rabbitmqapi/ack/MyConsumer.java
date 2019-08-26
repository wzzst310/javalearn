package com.wjjzst.queue.rabbitmq.rabbitmqapi.ack;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import javax.swing.plaf.TreeUI;
import java.io.IOException;

public class MyConsumer extends DefaultConsumer {

    private Channel channel;

    public MyConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.err.println("-------- handle return --------");
        System.err.println("body: " + new String(body));
        if ((Integer) properties.getHeaders().get("num") == 0) {
            channel.basicNack(envelope.getDeliveryTag(), false, true);// 第三个参数重回队列
        } else {
            channel.basicAck(envelope.getDeliveryTag(), false);
        }

    }
}
