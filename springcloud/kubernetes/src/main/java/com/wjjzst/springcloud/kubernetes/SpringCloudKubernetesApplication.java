package com.wjjzst.springcloud.kubernetes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: Wjj
 * @Date: 2020/12/26 3:52
 * @desc:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudKubernetesApplication {
    @Autowired
    private DiscoveryClient discoveryClient;
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudKubernetesApplication.class, args);
    }
}
