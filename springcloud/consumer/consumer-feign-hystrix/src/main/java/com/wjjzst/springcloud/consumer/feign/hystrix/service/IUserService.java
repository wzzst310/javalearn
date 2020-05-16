package com.wjjzst.springcloud.consumer.feign.hystrix.service;

import com.wjjzst.springcloud.consumer.feign.hystrix.service.impl.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Wjj
 * @Date: 2020/5/16 11:14 下午
 * @desc:
 */
@FeignClient(value = "provider-feign-hystrix",fallback = UserServiceFallback.class)
public interface IUserService {
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    String getUser(@RequestParam("username") String username);
}
