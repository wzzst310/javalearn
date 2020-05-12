package com.wjjzst.springcloud.provider.ribbon.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: Wjj
 * @Date: 2020/5/8 12:00 上午
 * @desc:
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderRibbonHelloApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderRibbonHelloApplication.class, args);
    }
}
