package com.wjjzst.springcloud.consumer.ribbon.loadbalance.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.ribbon.Ribbon;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Wjj
 * @Date: 2020/5/8 1:22 上午
 * @desc:
 */

@Configuration
public class TestConfiguration1 {
    @Bean
    public IRule ribbonRule(IClientConfig config){
        return new RoundRobinRule();
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
