package com.wjjzst.springcloud.consumer.feign.multiparam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Wjj
 * @Date: 2020/5/5 2:40 上午
 * @desc:
 */
@SpringBootApplication
@EnableFeignClients
public class ConsumerFeignMultiParamApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignMultiParamApplication.class, args);
    }
}
