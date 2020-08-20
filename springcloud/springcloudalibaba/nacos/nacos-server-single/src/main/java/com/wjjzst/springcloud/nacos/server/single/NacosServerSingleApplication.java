package com.wjjzst.springcloud.nacos.server.single;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: Wjj
 * @create: 2020/8/20 2:46 下午
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosServerSingleApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosServerSingleApplication.class, args);
    }
}
