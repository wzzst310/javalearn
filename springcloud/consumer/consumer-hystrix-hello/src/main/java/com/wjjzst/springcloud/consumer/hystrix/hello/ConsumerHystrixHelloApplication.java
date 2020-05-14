package com.wjjzst.springcloud.consumer.hystrix.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @Author: Wjj
 * @Date: 2020/5/15 12:08 上午
 * @desc:
 */
@SpringBootApplication
@EnableHystrix
@EnableEurekaClient
public class ConsumerHystrixHelloApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerHystrixHelloApplication.class, args);
    }
}
