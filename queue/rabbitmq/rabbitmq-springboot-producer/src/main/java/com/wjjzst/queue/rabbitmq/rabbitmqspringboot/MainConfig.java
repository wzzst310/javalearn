package com.wjjzst.queue.rabbitmq.rabbitmqspringboot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.wjjzst.queue.rabbitmq.rabbitmqspringboot.producer"})
public class MainConfig {
}
