package com.wjjzst.queue.kafka.kafkaspringbootproducer;

import com.wjjzst.queue.kafka.kafkaspringbootproducer.KafkaSpringbootProducerApplication;
import com.wjjzst.queue.kafka.kafkaspringbootproducer.consumer.KafkaConsumer;
import com.wjjzst.queue.kafka.kafkaspringbootproducer.model.Order;
import com.wjjzst.queue.kafka.kafkaspringbootproducer.producer.KafkaProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Wjj
 * @create: 2020/8/21 4:31 下午
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {
    @Autowired
    private KafkaProducer producer;

    @Autowired
    private KafkaConsumer consumer;

    @Test
    public void produce() {
        while (true) {
            int num = 1000;
            for (int i = 0; i < num; i++) {
                Order order = new Order();
                producer.send(order);
            }
        }

    }
}
