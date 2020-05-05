package com.wjjzst.springcloud.consumer.feign.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Wjj
 * @Date: 2020/5/5 2:13 下午
 * @desc:
 */
@SpringBootApplication
@EnableFeignClients
public class ConsumerFeignFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignFileApplication.class, args);
    }
}
