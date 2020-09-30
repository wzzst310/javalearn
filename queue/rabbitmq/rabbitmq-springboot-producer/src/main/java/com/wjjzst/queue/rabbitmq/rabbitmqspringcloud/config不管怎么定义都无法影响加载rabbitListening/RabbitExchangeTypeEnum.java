//package com.wjjzst.queue.rabbitmq.rabbitmqspringcloud.config;
//
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.FanoutExchange;
//import org.springframework.amqp.core.TopicExchange;
//
///**
// * @author: wangzhe
// * @create: 2020/9/23 1:59 下午
// * @Description
// */
//public enum RabbitExchangeTypeEnum {
//
//    /**
//     * 死信转发方式延迟队列
//     */
//    TTL_QUEUE(1, DirectExchange.class),
//    /**
//     * 正常队列
//     */
//    NORMAL_QUEUE(2, DirectExchange.class),
//    /**
//     * 广播队列
//     */
//    FANOUT_QUEUE(3, FanoutExchange.class),
//    /**
//     * topic队列
//     */
//    TOPIC_QUEUE(4, TopicExchange.class);
//
//
//    /**
//     * 队列routeKey
//     */
//    private int index;
//
//    /**
//     * 交换机class
//     */
//    private Class exchangeClazz;
//
//
//    RabbitExchangeTypeEnum(int index, Class exchangeClazz) {
//        this.index = index;
//        this.exchangeClazz = exchangeClazz;
//    }
//
//    public int getIndex() {
//        return index;
//    }
//
//    public Class getExchangeClazz() {
//        return exchangeClazz;
//    }
//}