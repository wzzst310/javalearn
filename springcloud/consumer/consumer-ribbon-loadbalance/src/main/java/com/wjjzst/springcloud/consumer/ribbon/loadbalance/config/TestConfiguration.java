package com.wjjzst.springcloud.consumer.ribbon.loadbalance.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Wjj
 * @Date: 2020/5/8 1:22 上午
 * @desc:
 */

@AvoidScan
@Configuration
public class TestConfiguration {
    @Autowired
    IClientConfig config;
    @Bean
    public IRule ribbonRule(IClientConfig config){
        return new RandomRule();
    }
}

 //全局配置ribbon
/*@Configuration
public class TestConfiguration {
    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }
}*/
