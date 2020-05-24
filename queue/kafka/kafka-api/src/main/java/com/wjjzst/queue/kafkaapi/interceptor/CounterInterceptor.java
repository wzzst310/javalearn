package com.wjjzst.queue.kafkaapi.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @Author: Wjj
 * @Date: 2020/5/25 1:48 上午
 * @desc:
 */
public class CounterInterceptor implements ProducerInterceptor<String, String> {

    private long successNum = 0L;
    private long errorNum = 0L;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception == null) {
            successNum++;
        } else {
            errorNum++;
        }
    }

    @Override
    public void close() {
        System.out.println("successNum=" + successNum);
        System.out.println("errorNum=" + errorNum);

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
