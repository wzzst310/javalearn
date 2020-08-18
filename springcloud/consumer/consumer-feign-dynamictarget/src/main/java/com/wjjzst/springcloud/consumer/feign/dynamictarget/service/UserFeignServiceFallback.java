package com.wjjzst.springcloud.consumer.feign.dynamictarget.service;

import com.wjjzst.springcloud.consumer.feign.dynamictarget.model.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: Wjj
 * @create: 2020/8/17 6:05 下午
 * @Description
 */
@Component
public class UserFeignServiceFallback implements FallbackFactory<UserFeignService> {
    @Override
    public UserFeignService create(Throwable throwable) {
        return new UserFeignService() {
            @Override
            public String upadateUser(User user) {
                return null;
            }
        };
    }
}
