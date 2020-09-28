//package com.wjjzst.queue.rabbitmq.rabbitmqspringcloud.config;
//
///**
// * @author: wangzhe
// * @create: 2020/9/23 2:00 下午
// * @Description
// */
//public enum RabbitQueueEnum {
//
//    ;
//    /**
//     * 队列BeanName
//     */
//    private String beanName;
//    /**
//     * 队列routeKey
//     */
//    private String routeKey;
//    /**
//     * 交换机
//     */
//    private RabbitExchangeEnum exchangeEnum;
//
//    /**
//     * 死信转发到队列
//     */
//    private RabbitQueueEnum rabbitQueueEnum;
//
//
//    RabbitQueueEnum(String beanName, String routeKey, RabbitExchangeEnum exchangeEnum, RabbitQueueEnum rabbitQueueEnum) {
//        this.beanName = beanName;
//        this.routeKey = routeKey;
//        this.exchangeEnum = exchangeEnum;
//        this.rabbitQueueEnum = rabbitQueueEnum;
//    }
//
//    public String getRouteKey() {
//        return routeKey;
//    }
//
//    public RabbitExchangeEnum getExchangeEnum() {
//        return exchangeEnum;
//    }
//
//    public String getExchangeName() {
//        return exchangeEnum.getExchangeName();
//    }
//
//    public String getBeanName() {
//        return beanName;
//    }
//
//    public RabbitQueueEnum getRabbitQueueEnum() {
//        return rabbitQueueEnum;
//    }
//}