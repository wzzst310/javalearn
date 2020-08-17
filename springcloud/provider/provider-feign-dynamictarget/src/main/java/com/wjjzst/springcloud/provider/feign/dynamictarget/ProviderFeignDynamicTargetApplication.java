package com.wjjzst.springcloud.provider.feign.dynamictarget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: Wjj
 * @create: 2020/8/17 10:22 上午
 * @Description
 */
@SpringBootApplication
public class ProviderFeignDynamicTargetApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderFeignDynamicTargetApplication.class, args);
    }
}
