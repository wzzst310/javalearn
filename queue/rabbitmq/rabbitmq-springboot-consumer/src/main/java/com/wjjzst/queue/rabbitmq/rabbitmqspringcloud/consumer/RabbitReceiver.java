package com.wjjzst.queue.rabbitmq.rabbitmqspringcloud.consumer;

import com.rabbitmq.client.Channel;
import com.wjjzst.queue.rabbitmq.rabbitmqspringcloud.config.MyConditonAnnotaion;
import com.wjjzst.queue.rabbitmq.rabbitmqspringcloud.config.RabbitMqQueueCondition;
import com.wjjzst.queue.rabbitmq.rabbitmqspringcloud.entity.Order;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

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
