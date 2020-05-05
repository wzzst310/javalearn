package com.wjjzst.springcloud.feign.httpclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Wjj
 * @Date: 2020/5/5 12:03 上午
 * @desc:
 */

@SpringBootApplication
@EnableFeignClients
public class FeignHttpClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignHttpClientApplication.class, args);
    }
}
