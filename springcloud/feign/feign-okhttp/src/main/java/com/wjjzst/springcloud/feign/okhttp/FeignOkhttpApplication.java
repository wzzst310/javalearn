package com.wjjzst.springcloud.feign.okhttp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Wjj
 * @Date: 2020/5/5 2:02 上午
 * @desc:
 */

@SpringBootApplication
@EnableFeignClients
public class FeignOkhttpApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignOkhttpApplication.class, args);
    }
}
