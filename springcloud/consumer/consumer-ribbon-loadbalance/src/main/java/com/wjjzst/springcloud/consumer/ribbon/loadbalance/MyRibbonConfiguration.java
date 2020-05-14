package com.wjjzst.springcloud.consumer.ribbon.loadbalance;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Author: Wjj
 * @Date: 2020/5/14 2:04 上午
 * @desc:
 */
@Configuration
// @Profile("dev")
public class MyRibbonConfiguration {
    @Bean
    public IRule ribbonRule() {
        return new MyRule();
    }
}

