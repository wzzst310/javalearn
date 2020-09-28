//package com.wjjzst.queue.rabbitmq.rabbitmqspringcloud.config;
//
//
//import com.google.common.collect.Maps;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionBuilder;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author: wangzhe
// * @create: 2020/9/23 1:58 下午
// * @Description
// */
//
//@Component
//public class RabbitQueueBeanRegister implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {
//
//    private ApplicationContext applicationContext;
//
//    private BeanDefinitionRegistry beanDefinitionRegistry;
//
//    private String adapterSuffix = "Adapter";
//
//    private Map<RabbitQueueEnum, Queue> topicQueues = new HashMap<>();
//
//    private List<TopicConsumer> topicConsumers;
//
//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
//        this.beanDefinitionRegistry = beanDefinitionRegistry;
//        //声明交换机
//        declareExchange();
//        //声明队列和绑定
//        declareQueueAndBinding();
//        //奇怪的执行顺序
//        if (haveTopicQueue()) {
//            declareTopicMessageListenerAdapter();
//            declareTopicMessageListenerContainer();
//        }
//    }
//
//    private boolean haveTopicQueue() {
//        try {
//            topicConsumers = new ArrayList<>(applicationContext.getBeansOfType(TopicConsumer.class).values());
//            return !topicConsumers.isEmpty();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }
//
//    /**
//     * 声明交换机
//     */
//    private void declareExchange() {
//        for (RabbitExchangeEnum rabbitExchangeEnum : RabbitExchangeEnum.values()) {
//            switch (rabbitExchangeEnum.getRabbitExchangeTypeEnum()) {
//                case FANOUT_QUEUE:
//                    beanDefinitionRegistry.registerBeanDefinition(rabbitExchangeEnum.getBeanName(), BeanDefinitionBuilder.genericBeanDefinition(FanoutExchange.class, () -> (FanoutExchange) ExchangeBuilder
//                            .fanoutExchange(rabbitExchangeEnum.getExchangeName())
//                            .durable(true)
//                            .build()).getBeanDefinition());
//                    break;
//                case TOPIC_QUEUE:
//                    beanDefinitionRegistry.registerBeanDefinition(rabbitExchangeEnum.getBeanName(), BeanDefinitionBuilder.genericBeanDefinition(TopicExchange.class, () -> (TopicExchange) ExchangeBuilder
//                            .topicExchange(rabbitExchangeEnum.getExchangeName())
//                            .durable(true)
//                            .build()).getBeanDefinition());
//                    break;
//                default:
//                    beanDefinitionRegistry.registerBeanDefinition(rabbitExchangeEnum.getBeanName(), BeanDefinitionBuilder.genericBeanDefinition(DirectExchange.class, () -> (DirectExchange) ExchangeBuilder
//                            .directExchange(rabbitExchangeEnum.getExchangeName())
//                            .durable(true)
//                            .build()).getBeanDefinition());
//                    break;
//            }
//        }
//    }
//
//    /**
//     * 声明队列和绑定
//     */
//    private void declareQueueAndBinding() {
//        String bindingSuffix = "Binding";
//        for (RabbitQueueEnum rabbitQueueEnum : RabbitQueueEnum.values()) {
//            //注册所有队列
//            beanDefinitionRegistry.registerBeanDefinition(rabbitQueueEnum.getBeanName(), BeanDefinitionBuilder.genericBeanDefinition(Queue.class, () -> {
//                Queue queue;
//                switch (rabbitQueueEnum.getExchangeEnum().getRabbitExchangeTypeEnum()) {
//                    case TTL_QUEUE:
//                        queue = QueueBuilder
//                                .durable(rabbitQueueEnum.getRouteKey())
//                                // 配置到期后转发的交换
//                                .withArgument("x-dead-letter-exchange", rabbitQueueEnum.getRabbitQueueEnum().getExchangeName())
//                                // 配置到期后转发的路由键
//                                .withArgument("x-dead-letter-routing-key", rabbitQueueEnum.getRabbitQueueEnum().getRouteKey())
//                                .build();
//                        break;
//                    case TOPIC_QUEUE:
//                        queue = new AnonymousQueue(new AnonymousQueue.Base64UrlNamingStrategy(StringUtils.getTopicQueueNamePrefix(rabbitQueueEnum.getRouteKey())));
//                        topicQueues.put(rabbitQueueEnum, queue);
//                        break;
//                    default:
//                        queue = new Queue(rabbitQueueEnum.getRouteKey());
//                        break;
//                }
//                return queue;
//            }).getBeanDefinition());
//            //注册队列与交换机的绑定
//            switch (rabbitQueueEnum.getExchangeEnum().getRabbitExchangeTypeEnum()) {
//                case FANOUT_QUEUE:
//                    beanDefinitionRegistry.registerBeanDefinition(rabbitQueueEnum.getBeanName() + bindingSuffix, BeanDefinitionBuilder.genericBeanDefinition(Binding.class, () -> BindingBuilder
//                            .bind(applicationContext.getBean(rabbitQueueEnum.getBeanName(), Queue.class))
//                            .to(applicationContext.getBean(rabbitQueueEnum.getExchangeEnum().getBeanName(), FanoutExchange.class))).getBeanDefinition());
//
//                    break;
//                case NORMAL_QUEUE:
//                case TTL_QUEUE:
//                    beanDefinitionRegistry.registerBeanDefinition(rabbitQueueEnum.getBeanName() + bindingSuffix, BeanDefinitionBuilder.genericBeanDefinition(Binding.class, () -> BindingBuilder
//                            .bind(applicationContext.getBean(rabbitQueueEnum.getBeanName(), Queue.class))
//                            .to(applicationContext.getBean(rabbitQueueEnum.getExchangeEnum().getBeanName(), DirectExchange.class))
//                            .with(rabbitQueueEnum.getRouteKey())).getBeanDefinition());
//                    break;
//                case TOPIC_QUEUE:
//                    beanDefinitionRegistry.registerBeanDefinition(rabbitQueueEnum.getBeanName() + bindingSuffix, BeanDefinitionBuilder.genericBeanDefinition(Binding.class, () -> BindingBuilder
//                            .bind(applicationContext.getBean(rabbitQueueEnum.getBeanName(), Queue.class))
//                            .to(applicationContext.getBean(rabbitQueueEnum.getExchangeEnum().getBeanName(), TopicExchange.class))
//                            .with(StringUtils.getTopicQueueRoute(rabbitQueueEnum.getRouteKey()))).getBeanDefinition());
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
//
//    /**
//     * 声明Topic消息监听适配器
//     */
//    private void declareTopicMessageListenerAdapter() {
//        topicConsumers.forEach(topicConsumer -> beanDefinitionRegistry.registerBeanDefinition(topicConsumer.getQueueEnum().getBeanName() + adapterSuffix,
//                BeanDefinitionBuilder.genericBeanDefinition(MessageListenerAdapter.class, () -> new MessageListenerAdapter(topicConsumer)).getBeanDefinition()));
//    }
//
//    /**
//     * 声明Topic消息监听容器
//     */
//    private void declareTopicMessageListenerContainer() {
//        String containerSuffix = "Container";
//        topicConsumers.forEach(topicConsumer -> beanDefinitionRegistry.registerBeanDefinition(topicConsumer.getQueueEnum().getBeanName() + containerSuffix,
//                BeanDefinitionBuilder.genericBeanDefinition(SimpleMessageListenerContainer.class, () -> {
//                    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//                    container.setQueues(topicQueues.get(topicConsumer.getQueueEnum()));
//                    container.setConnectionFactory(applicationContext.getBean("rabbitConnectionFactory", ConnectionFactory.class));
//                    container.setMessageListener(applicationContext.getBean(topicConsumer.getQueueEnum().getBeanName() + adapterSuffix));
//                    container.setRabbitAdmin(applicationContext.getBean(RabbitAdmin.class));
//                    return container;
//                }).getBeanDefinition()));
//    }
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//}
