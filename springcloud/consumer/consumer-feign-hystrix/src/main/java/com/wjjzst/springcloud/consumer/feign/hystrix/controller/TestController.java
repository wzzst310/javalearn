package com.wjjzst.springcloud.consumer.feign.hystrix.controller;

import com.wjjzst.springcloud.consumer.feign.hystrix.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Wjj
 * @Date: 2020/5/16 11:14 下午
 * @desc:
 */
@RestController
public class TestController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public String getUser(@RequestParam("username") String username) {
        return userService.getUser(username);
    }
}
