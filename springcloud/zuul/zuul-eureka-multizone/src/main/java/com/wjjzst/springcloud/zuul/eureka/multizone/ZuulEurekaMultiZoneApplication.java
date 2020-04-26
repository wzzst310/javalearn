package com.wjjzst.springcloud.zuul.eureka.multizone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: Wjj
 * @Date: 2020/4/26 10:07 下午
 * @desc:
 */
@SpringBootApplication
@EnableEurekaClient
public class ZuulEurekaMultiZoneApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulEurekaMultiZoneApplication.class, args);
    }
}
