package com.wjjzst.springcloud.consumer.hystrix.badrequest.service.dataservice;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author: Wjj
 * @Date: 2020/5/19 10:42 下午
 * @desc: 本类是基于FeignBuilder指定才会用到的 直接hystrix command是不能切到熔断方法里面的
 */
@Component
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            if (response.status() >= 400 && response.status() <= 499) {
                String error = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
                return new HystrixBadRequestException(error);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return feign.FeignException.errorStatus(methodKey, response);
    }
}

