package com.wjjzst.springcloud.consumer.hystrix.badrequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Wjj
 * @Date: 2020/5/19 10:36 下午
 * @desc:
 */
@EnableEurekaClient
@SpringBootApplication
@EnableHystrix
@EnableFeignClients
public class ConsumerHystrixBadRequsetApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerHystrixBadRequsetApplication.class, args);
    }
}
