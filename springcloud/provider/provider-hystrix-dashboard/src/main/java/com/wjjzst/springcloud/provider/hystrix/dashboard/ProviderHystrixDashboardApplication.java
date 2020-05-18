package com.wjjzst.springcloud.provider.hystrix.dashboard;

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
@EnableFeignClients
@EnableHystrix
public class ProviderHystrixDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderHystrixDashboardApplication.class, args);
    }
}
