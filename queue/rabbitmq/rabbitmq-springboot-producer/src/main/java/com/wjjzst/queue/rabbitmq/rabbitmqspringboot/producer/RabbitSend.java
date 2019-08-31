package com.wjjzst.queue.rabbitmq.rabbitmqspringboot.producer;


import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class RabbitSend {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("correlationData: " + correlationData);
            System.err.println("ack: " + ack);
            System.err.println("cause: " + cause);
            if (!ack) {  // 没有发送成功更新数据库
                System.err.println("异常处理....");
            }
        }
    };

    private final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText, String exchange, String routingKey) {
            System.err.println("return exchange : " + exchange + ", routingKey: " + routingKey
                    + ", replyCode: " + replyCode + ", replyText: " + replyText);
        }
    };

    public void send(Object message, Map<String, Object> properties) {
        MessageHeaders mhs = new MessageHeaders(properties);
        Message msg = MessageBuilder.createMessage(message, mhs);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString());   //保证 ID+ 时间戳 全局唯一   消息
        // rabbitTemplate.convertAndSend("exchange-1", "springboot.hello", msg, correlationData);  // confirmCallback
        rabbitTemplate.convertAndSend("exchange-1", "spring.hello", msg, correlationData);  // returnCallback
    }
}
