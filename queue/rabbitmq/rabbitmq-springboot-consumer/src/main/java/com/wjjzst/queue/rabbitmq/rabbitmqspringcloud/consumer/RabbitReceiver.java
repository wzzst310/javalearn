package com.wjjzst.queue.rabbitmq.rabbitmqspringcloud.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-1",
                    durable = "true"),
            exchange = @Exchange(value = "exchange-1",
                    durable = "true",
                    type = "topic",
                    ignoreDeclarationExceptions = "true"),
            key = "springboot.*")
    )
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        System.err.println("--------------------------------------------------");
        System.err.println("消费端: " + message.getPayload());
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        // 手工ack
        channel.basicAck(deliveryTag, false); //第二个参数表示不批量的ack 而是一个一个ack
    }
}
