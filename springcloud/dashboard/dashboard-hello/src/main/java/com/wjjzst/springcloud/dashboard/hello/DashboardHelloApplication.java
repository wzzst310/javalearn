package com.wjjzst.springcloud.dashboard.hello;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

/**
 * @Author: Wjj
 * @Date: 2020/5/18 9:58 下午
 * @desc:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrixDashboard
public class DashboardHelloApplication {
    public static void main(String[] args) {
        SpringApplication.run(DashboardHelloApplication.class, args);
    }
}
