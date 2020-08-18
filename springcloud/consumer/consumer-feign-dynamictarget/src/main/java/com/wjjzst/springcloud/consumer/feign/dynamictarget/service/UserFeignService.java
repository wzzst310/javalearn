package com.wjjzst.springcloud.consumer.feign.dynamictarget.service;

import com.wjjzst.springcloud.consumer.feign.dynamictarget.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: Wjj
 * @create: 2020/8/17 10:23 上午
 * @Description
 */
@FeignClient(value = "CLUSTER-ID-provider-feign-dynamictarget", fallbackFactory = UserFeignServiceFallback.class)
public interface UserFeignService {
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    String upadateUser(@RequestBody User user);
}
