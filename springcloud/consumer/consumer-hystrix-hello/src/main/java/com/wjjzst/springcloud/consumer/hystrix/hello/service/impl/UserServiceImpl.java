package com.wjjzst.springcloud.consumer.hystrix.hello.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wjjzst.springcloud.consumer.hystrix.hello.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @Author: Wjj
 * @Date: 2020/5/15 12:18 上午
 * @desc:
 */
@Service
public class UserServiceImpl implements IUserService {
    @Override
    @HystrixCommand(fallbackMethod = "defaultUser")
    public String getUser(String username) throws Exception {
        if ("spring".equals(username)) {
            return "This is real user";
        } else {
            throw new Exception();
        }
    }

    public String defaultUser(String username) {
        return "The user does not exist in this system";
    }

}
