package com.wjjzst.springcloud.provider.ribbon.loadbalance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Wjj
 * @Date: 2020/5/8 12:24 上午
 * @desc:
 */
@RestController
public class TestController {
    @GetMapping("/add")
    public String add(Integer a, Integer b, HttpServletRequest request) {
        return " From Port: " + request.getServerPort() + ", Result: " + (a + b);
    }
}
