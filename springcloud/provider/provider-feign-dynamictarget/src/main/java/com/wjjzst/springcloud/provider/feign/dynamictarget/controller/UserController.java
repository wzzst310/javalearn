package com.wjjzst.springcloud.provider.feign.dynamictarget.controller;

import com.wjjzst.springcloud.provider.feign.dynamictarget.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: Wjj
 * @Date: 2020/5/5 8:58 上午
 * @desc:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value = "/update",method= RequestMethod.POST)
    public String upadateUser(@RequestBody User user) {
        return "hello," + user.getName();
    }

}
