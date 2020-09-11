package com.wjjzst.springcloud.provider.feign.multiparam.controller;

import com.alibaba.fastjson.JSON;
import com.wjjzst.common.dto.TestDTO;
import com.wjjzst.common.enums.TestEnum;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wjjzst.springcloud.provider.feign.multiparam.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Wjj
 * @Date: 2020/5/5 8:58 上午
 * @desc:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value = "/add",method= RequestMethod.GET)
    public String addUser(User user, HttpServletRequest request) {
        String token = request.getHeader("oauthToken");
        return "hello," + user.getName();
    }
    @RequestMapping(value = "/update",method= RequestMethod.POST)
    public String upadateUser(@RequestBody User user) {
        return "hello," + user.getName();
    }

    @RequestMapping(value = "/testEnum",method= RequestMethod.POST)
    public String testEnum(@RequestBody TestDTO dto) {
        TestEnum testEnum = dto.getTestEnum();
        System.out.println(JSON.toJSONString(dto));
        return "hello," + testEnum;
    }

}
