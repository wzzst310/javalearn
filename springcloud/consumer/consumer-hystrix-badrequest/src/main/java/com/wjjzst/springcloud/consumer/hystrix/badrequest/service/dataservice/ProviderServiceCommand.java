package com.wjjzst.springcloud.consumer.hystrix.badrequest.service.dataservice;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @Author: Wjj
 * @Date: 2020/5/19 11:04 下午
 * @desc:
 */

public class ProviderServiceCommand extends HystrixCommand<String> {
    private final String name;

    public ProviderServiceCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("GroupSC"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "Spring Cloud";
    }

    @Override
    protected String getFallback() {
        return "Failure Spring Cloud";
    }
}
