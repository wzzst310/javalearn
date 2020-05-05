package com.wjjzst.springcloud.provider.feign.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: Wjj
 * @Date: 2020/5/5 2:13 下午
 * @desc:
 */
@SpringBootApplication
// @EnableEurekaClient  只要有加了eureka的依赖 不贴注解也能自动注册到eureka
public class ProviderFeignFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderFeignFileApplication.class, args);
    }
}
