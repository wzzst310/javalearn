package com.wjjzst.springcloud.feign.gzip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Wjj
 * @Date: 2020/4/29 1:00 上午
 * @desc:
 */
@SpringBootApplication
@EnableFeignClients // 表示当程序启动时，会进行包扫描，扫描到所有带@FeignClient的注解类进行处理
public class FeignGzipApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignGzipApplication.class, args);
    }
}
