package com.wjjzst.springcloud.consumer.ribbon.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/add")
    public String add(Integer a, Integer b) {
        String result = restTemplate.getForObject("http://provider-ribbon-hello/add?a=" + a + "&b=" + b, String.class);
        System.out.println(result);
        return result;
    }
}
