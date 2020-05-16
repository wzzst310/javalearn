package com.wjjzst.springcloud.provider.feign.hystrix.controller;

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
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public String getUser(@RequestParam("username") String username) throws Exception {
        if ("spring".equals(username)) {
            return "This is real user";
        } else {
            throw new Exception();
        }
    }
}
