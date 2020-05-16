package com.wjjzst.springcloud.consumer.feign.hystrix.service.impl;

import com.wjjzst.springcloud.consumer.feign.hystrix.service.IUserService;
import org.springframework.stereotype.Component;

/**
 * @Author: Wjj
 * @Date: 2020/5/16 11:15 下午
 * @desc:
 */
@Component
public class UserServiceFallback implements IUserService {

    @Override
    public String getUser(String username) {
        return "The user does not exist in this system, please confirm username";
    }
}
