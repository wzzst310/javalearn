package com.wjjzst.springcloud.consumer.hystrix.dashboard.controller;

import com.wjjzst.springcloud.consumer.hystrix.dashboard.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Wjj
 * @Date: 2020/5/18 1:16 上午
 * @desc:
 */
@RestController
public class HelloController {
    @Autowired
    private IHelloService helloService;

    @GetMapping("/getProviderData")
    public List<String> getProviderData() {
        return helloService.getProviderData();
    }

    @RequestMapping(value = "/helloService", method = RequestMethod.GET)
    public String getHelloServiceData() {
        return "hello Service";
    }
}
