package com.wjjzst.springcloud.consumer.ribbon.loadbalance;


import com.wjjzst.springcloud.consumer.ribbon.loadbalance.config.AvoidScan;
import com.wjjzst.springcloud.consumer.ribbon.loadbalance.config.TestConfiguration;
import com.wjjzst.springcloud.consumer.ribbon.loadbalance.config.TestConfiguration1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Wjj
 * @Date: 2020/5/8 12:00 上午
 * @desc:
 */
@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "provider-ribbon-hello", configuration = TestConfiguration.class)  // 如果要调用provider-ribbon-hello的服务  就需要TestConfiguration动态代理规则来实现

// 此配置可以交由配置文件  且配置文件优先级顺序更
//@RibbonClients(value = {
//        @RibbonClient(name = "provider-ribbon-loadbalance", configuration = TestConfiguration.class),
//        @RibbonClient(name = "provider-ribbon-loadbalance-b", configuration = TestConfiguration1.class)
//})
// @ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,annotationType = {AvoidScan.class})})  // 设置扫描注解的类过滤 springboot版本高了不行
// @ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {TestConfiguration.class, TestConfiguration1.class})})
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.wjjzst.springcloud.consumer.ribbon.loadbalance.config.*")})
// 设置扫描包过滤
public class ConsumerRibbonLoadBalanceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerRibbonLoadBalanceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
