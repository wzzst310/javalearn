package com.wjjzst.springcloud.consumer.hystrix.hello.controller;

import com.wjjzst.springcloud.consumer.hystrix.hello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Wjj
 * @Date: 2020/5/15 12:17 上午
 * @desc:
 */
@RestController
public class TestController {
    @Autowired
    private IUserService userService;
    @GetMapping("/getUser")
    public String getUser(@RequestParam String username) throws Exception {
        return userService.getUser(username);
    }

}
