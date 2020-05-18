package com.wjjzst.springcloud.consumer.feign.hystrix.service.impl;

import com.wjjzst.springcloud.consumer.feign.hystrix.service.IUserService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: Wjj
 * @Date: 2020/5/17 9:49 下午
 * @desc:
 */
@Component
public class UserServiceFallbackFactory implements FallbackFactory<IUserService> {

    @Override
    public IUserService create(Throwable throwable) {
        return new IUserService() {
            @Override
            public String getUser(String username) {
                return "The user does not exist in this system, " +
                        "please confirm username";
            }
        };
    }
}
