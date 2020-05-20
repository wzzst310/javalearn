package com.wjjzst.springcloud.consumer.hystrix.badrequest.service.dataservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: Wjj
 * @Date: 2020/5/20 11:15 下午
 * @desc:
 */
@FeignClient(value = "provider-hystrix-dashboard",fallback = PSFallbackBackRequestExceptionFeignImpl.class)
public interface PSFallbackBackRequestExceptionFeign {
    @GetMapping("/getBadRequest")
    String getBadRequestService();
}
