package com.wjjzst.springcloud.feign.hello.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Wjj
 * @Date: 2020/4/29 1:12 上午
 * @desc:
 */
@Configuration
public class HelloFeignServiceConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
