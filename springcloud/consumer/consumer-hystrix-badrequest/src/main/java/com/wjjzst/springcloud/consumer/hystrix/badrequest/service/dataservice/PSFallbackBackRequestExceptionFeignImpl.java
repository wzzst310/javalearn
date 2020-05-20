package com.wjjzst.springcloud.consumer.hystrix.badrequest.service.dataservice;

import org.springframework.stereotype.Component;

/**
 * @Author: Wjj
 * @Date: 2020/5/20 11:24 下午
 * @desc:
 */
@Component
public class PSFallbackBackRequestExceptionFeignImpl implements  PSFallbackBackRequestExceptionFeign{
    @Override
    public String getBadRequestService() {
        return "invoke HystrixBadRequestException fallback method:  ";
    }
}
