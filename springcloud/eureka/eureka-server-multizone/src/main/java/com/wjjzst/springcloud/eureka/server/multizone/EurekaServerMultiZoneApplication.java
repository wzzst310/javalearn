package com.wjjzst.springcloud.eureka.server.multizone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: Wjj
 * @Date: 2020/4/26 10:07 下午
 * @desc:
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerMultiZoneApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerMultiZoneApplication.class, args);
    }
}
