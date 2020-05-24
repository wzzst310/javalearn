package com.wjjzst.queue.kafkaapi.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author: Wjj
 * @Date: 2020/5/25 1:48 上午
 * @desc:
 */
public class CustomProducer {

    public static void main(String[] args) throws InterruptedException {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop1:9092,hadoop2:9092,hadoop3:9092,hadoop4:9092,hadoop5:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        ArrayList<String> interceptors = new ArrayList<>();
        interceptors.add("com.wjjzst.queue.kafkaapi.interceptor.CounterInterceptor");
        interceptors.add("com.wjjzst.queue.kafkaapi.interceptor.TimeInterceptor");
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);


        //1.创建1个生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        //2.调用send方法
        for (int i = 0; i < 1000000; i++) {
            Thread.sleep(10);
            producer.send(new ProducerRecord<String, String>("number", i + "", "message-" + i));
        }

        //3.关闭生产者
        producer.close();
    }
}
