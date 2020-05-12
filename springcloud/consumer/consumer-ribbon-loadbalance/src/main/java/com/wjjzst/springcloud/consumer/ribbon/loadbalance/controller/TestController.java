package com.wjjzst.springcloud.consumer.ribbon.loadbalance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Wjj
 * @Date: 2020/5/8 12:10 上午
 * @desc:
 */
@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private LoadBalancerClient lbClient;

    @GetMapping("/add")
    public String add(Integer a, Integer b) {
        String result = restTemplate.getForObject("http://provider-ribbon-loadbalance/add?a=" + a + "&b=" + b, String.class);
        System.out.println(result);
        return result;
    }

    @GetMapping("/add1")
    public void add1(Integer a, Integer b) {
        ServiceInstance instance = this.lbClient.choose("provider-ribbon-loadbalance");
        System.out.println(instance.getHost()+":"+instance.getPort());
    }

    @GetMapping("/add2")
    public void add2(Integer a, Integer b) {
        ServiceInstance instance = this.lbClient.choose("provider-ribbon-loadbalance-b");
        System.out.println(instance.getHost()+":"+instance.getPort());
    }
}
