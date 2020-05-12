package com.wjjzst.springcloud.consumer.ribbon.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Wjj
 * @Date: 2020/5/8 12:00 上午
 * @desc:
 */
@SpringBootApplication
@EnableEurekaClient
public class ConsumerRibbonHelloApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerRibbonHelloApplication.class, args);
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
