package com.wjjzst.springcloud.consumer.hystrix.dashboard.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Author: Wjj
 * @Date: 2020/5/18 1:16 上午
 * @desc:
 */
public interface IHelloService {
    List<String> getProviderData();
}
