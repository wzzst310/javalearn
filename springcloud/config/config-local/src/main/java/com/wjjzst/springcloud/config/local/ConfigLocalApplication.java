package com.wjjzst.springcloud.config.local;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Author: Wjj
 * @Date: 2020/4/26 12:47 上午
 * @desc:
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigLocalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigLocalApplication.class, args);
    }
}
