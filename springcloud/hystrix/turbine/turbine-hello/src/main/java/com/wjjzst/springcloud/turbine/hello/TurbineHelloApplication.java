package com.wjjzst.springcloud.turbine.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @Author: Wjj
 * @Date: 2020/5/19 12:49 上午
 * @desc:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrixDashboard
@EnableTurbine
public class TurbineHelloApplication {
    public static void main(String[] args) {
        SpringApplication.run(TurbineHelloApplication.class, args);
    }
}
