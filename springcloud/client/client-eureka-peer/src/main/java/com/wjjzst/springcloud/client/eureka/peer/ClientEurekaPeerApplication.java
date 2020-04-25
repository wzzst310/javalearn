package com.wjjzst.springcloud.client.eureka.peer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: Wjj
 * @Date: 2020/4/26 1:12 上午
 * @desc:
 */
@SpringBootApplication
@EnableEurekaClient   // EnableEurekaClient eureka专属注解
// @EnableDiscoveryClient  // 当使用其他注册中心 使用的注解
public class ClientEurekaPeerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientEurekaPeerApplication.class,args);
    }
}
