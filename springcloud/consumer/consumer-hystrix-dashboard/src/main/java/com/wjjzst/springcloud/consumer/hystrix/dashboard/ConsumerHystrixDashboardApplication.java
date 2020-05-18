package com.wjjzst.springcloud.consumer.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Wjj
 * @Date: 2020/5/18 12:29 上午
 * @desc:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients
public class ConsumerHystrixDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerHystrixDashboardApplication.class, args);
    }
}
