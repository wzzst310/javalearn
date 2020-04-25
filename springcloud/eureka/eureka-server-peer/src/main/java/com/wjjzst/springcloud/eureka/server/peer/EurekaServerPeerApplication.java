package com.wjjzst.springcloud.eureka.server.peer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: Wjj
 * @Date: 2020/4/26 12:07 上午
 * @desc:
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerPeerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerPeerApplication.class, args);
    }
}
