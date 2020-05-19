package com.wjjzst.springcloud.consumer.hystrix.badrequest.service.dataservice;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: Wjj
 * @Date: 2020/5/20 12:32 上午
 * @desc:
 */
public class PSFallbackBadRequestExpcetion extends HystrixCommand<String> {
    private static Logger log = LoggerFactory.getLogger(PSFallbackBadRequestExpcetion.class);

    public PSFallbackBadRequestExpcetion() {
        super(HystrixCommandGroupKey.Factory.asKey("GroupBRE"));
    }

    @Override
    protected String run() throws Exception {
        throw new HystrixBadRequestException("HystrixBadRequestException error");
    }

    @Override
    protected String getFallback() {
        System.out.println(getFailedExecutionException().getMessage());
        return "invoke HystrixBadRequestException fallback method:  ";
    }


}
