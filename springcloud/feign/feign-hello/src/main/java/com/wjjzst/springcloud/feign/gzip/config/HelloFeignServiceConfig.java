package com.wjjzst.springcloud.feign.gzip.config;

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
    /**
     * Logger.Level的具体级别如下：
     * NONE: 不记录任何日志
     * BASIC: 仅记录请求方法，URL及响应状态码和执行时间
     * HEADERS: 除了记录BASIC级别的信息外，还会记录请求地址和响应的头信息
     * FULL: 记录所有请求与响应的明细，包括头信息、请求体、元数据
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
