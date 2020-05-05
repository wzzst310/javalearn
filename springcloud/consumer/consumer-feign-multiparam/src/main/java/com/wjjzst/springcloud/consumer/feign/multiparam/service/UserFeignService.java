package com.wjjzst.springcloud.consumer.feign.multiparam.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.wjjzst.springcloud.consumer.feign.multiparam.model.User;

/**
 * @Author: Wjj
 * @Date: 2020/5/5 8:55 上午
 * @desc:
 */
@FeignClient("provider-feign-multiparam")
public interface UserFeignService {
    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    String addUser(User user);

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    String upadateUser(@RequestBody User user);
}
