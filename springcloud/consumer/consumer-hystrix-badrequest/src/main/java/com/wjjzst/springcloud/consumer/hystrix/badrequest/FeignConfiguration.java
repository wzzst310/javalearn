package com.wjjzst.springcloud.consumer.hystrix.badrequest;

import com.wjjzst.springcloud.consumer.hystrix.badrequest.service.dataservice.FeignErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Wjj
 * @Date: 2020/5/20 2:04 上午
 * @desc:
 */
@Configuration
public class FeignConfiguration {
    @Bean
    public FeignErrorDecoder feignErrorDecoder(){
        return new FeignErrorDecoder();
    }
}
