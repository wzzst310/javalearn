package com.wjjzst.springcloud.consumer.hystrix.dashboard.service.dataservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Author: Wjj
 * @Date: 2020/5/18 1:17 上午
 * @desc:
 */
@FeignClient("provider-hystrix-dashboard")
public interface IProviderService {
    @RequestMapping(value = "/getDashboard",method = RequestMethod.GET)
    List<String> getProviderData();

}
