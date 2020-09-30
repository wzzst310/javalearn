package com.wjjzst.queue.rabbitmq.rabbitmqspringcloud.config不管怎么定义都无法影响加载rabbitListening.myself;

import org.springframework.amqp.core.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: wangzhe
 * @create: 2020/9/23 11:45 上午
 * @Description
 */
@Component
public class NormalQueueRegistry implements BeanDefinitionRegistryPostProcessor , ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        try {
            for (NormalQueueEnum value : NormalQueueEnum.values()) {
                String queueName = value.getName();
                String queueExchange = value.getExchange();
                String queueKey = value.getRouteKey();
                System.out.println("=======>>>  正在加载队列 " + value.getName());
                /*
                BeanDefinitionBuilder exchangeBeanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DirectExchange.class);
                registry.registerBeanDefinition(queueExchange, exchangeBeanDefinitionBuilder.getBeanDefinition());
                DirectExchange exchange = SpringBeanUtil.getBean(queueExchange, DirectExchange.class);
                BeanDefinitionBuilder queueBeanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Queue.class);
                Queue queue = SpringBeanUtil.getBean(queueName, Queue.class);
                registry.registerBeanDefinition(queueName, queueBeanDefinitionBuilder.getBeanDefinition());
                BeanDefinitionBuilder.genericBeanDefinition(Binding.class,()->BindingBuilder.bind(queue).to(exchange).with(queueKey));
                */
                registry.registerBeanDefinition(queueExchange, BeanDefinitionBuilder.genericBeanDefinition(DirectExchange.class, () -> ExchangeBuilder
                        .directExchange("directExchange")
                        .durable(true)
                        .build()).getBeanDefinition());
                registry.registerBeanDefinition(queueName, BeanDefinitionBuilder.genericBeanDefinition(Queue.class, () -> new Queue(queueName)).getBeanDefinition());
                registry.registerBeanDefinition(queueKey, BeanDefinitionBuilder.genericBeanDefinition(Binding.class, () -> BindingBuilder
                        .bind(applicationContext.getBean(queueName, Queue.class))
                        .to(applicationContext.getBean(queueExchange, DirectExchange.class))
                        .with(queueKey)).getBeanDefinition());
            }
        } catch (Exception e) {
            System.out.println("初始化 Beans 异常");
            e.printStackTrace();
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
