package com.wjjzst.springcloud.provider.hystrix.dashboard.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: Wjj
 * @Date: 2020/5/18 1:30 上午
 * @desc:
 */
@FeignClient("consumer-hystrix-dashboard")
public interface ConsumerService {
    @RequestMapping(value = "/helloService", method = RequestMethod.GET)
    String getHelloServiceData();
}
