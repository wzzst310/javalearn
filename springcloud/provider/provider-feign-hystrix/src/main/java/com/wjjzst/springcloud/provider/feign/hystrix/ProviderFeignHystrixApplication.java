package com.wjjzst.springcloud.provider.feign.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: Wjj
 * @Date: 2020/5/16 11:47 下午
 * @desc:
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderFeignHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderFeignHystrixApplication.class, args);
    }
}
