package com.wjjzst.springcloud.consumer.feign.file.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * @Author: Wjj
 * @Date: 2020/5/6 2:08 上午
 * @desc: Feign文件上传Configuration
 */
// @Configuration
public class FeignMultipartSupportConfig {
    @Bean
    @Primary
    @Scope("prototype")
    public Encoder multipartFormEncoder(){
        return new SpringFormEncoder();
    }
}
