package com.wjjzst.springcloud.consumer.hystrix.dashboard.service.impl;

import com.wjjzst.springcloud.consumer.hystrix.dashboard.service.IHelloService;
import com.wjjzst.springcloud.consumer.hystrix.dashboard.service.dataservice.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Wjj
 * @Date: 2020/5/18 1:16 上午
 * @desc:
 */
@Service
public class HelloServiceImpl implements IHelloService {
    @Autowired
    private IProviderService providerService;
    @Override
    public List<String> getProviderData() {
        return providerService.getProviderData();
    }
}
