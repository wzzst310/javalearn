//package com.wjjzst.queue.rabbitmq.rabbitmqspringcloud.config;
//
///**
// * @author: wangzhe
// * @create: 2020/9/23 2:01 下午
// * @Description
// */
//public enum RabbitExchangeEnum {
//
//
//    /**
//     * rabbit交换机名称
//     * 默认一个应用设置一个交换机
//     * exchange.{0}.{1}
//     * 0: 交换机类型 direct、topic、fanout、headers
//     * 1: 应用名称
//     */
//    DIRECT_EXCHANGE("directExchange", "exchange.direct.gateway", RabbitExchangeTypeEnum.NORMAL_QUEUE),
//    FANOUT_EXCHANGE("fanoutExchange", "exchange.fanout.gateway", RabbitExchangeTypeEnum.FANOUT_QUEUE),
//    TOPIC_EXCHANGE("topicExchange", "exchange.topic.gateway", RabbitExchangeTypeEnum.TOPIC_QUEUE),
//    ;
//
//    /**
//     * 交换机beanName
//     */
//    private String beanName;
//    /**
//     * 交换机key
//     */
//    private String exchangeName;
//    /**
//     * 交换机类型
//     */
//    private RabbitExchangeTypeEnum rabbitExchangeTypeEnum;
//
//    RabbitExchangeEnum(String beanName, String exchangeName, RabbitExchangeTypeEnum rabbitExchangeTypeEnum) {
//        this.beanName = beanName;
//        this.exchangeName = exchangeName;
//        this.rabbitExchangeTypeEnum = rabbitExchangeTypeEnum;
//    }
//
//    public String getExchangeName() {
//        return exchangeName;
//    }
//
//    public String getBeanName() {
//        return beanName;
//    }
//
//    public RabbitExchangeTypeEnum getRabbitExchangeTypeEnum() {
//        return rabbitExchangeTypeEnum;
//    }
//}