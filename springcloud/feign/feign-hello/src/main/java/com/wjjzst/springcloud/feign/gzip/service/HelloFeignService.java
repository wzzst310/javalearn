package com.wjjzst.springcloud.feign.gzip.service;

import com.wjjzst.springcloud.feign.gzip.config.HelloFeignServiceConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Wjj
 * @Date: 2020/4/29 1:04 上午
 * @desc:
 */
@FeignClient(name = "github-client", url = "https://api.github.com", configuration = HelloFeignServiceConfig.class)
public interface HelloFeignService {
    /**
     * content: {"message":"Validation Failed","errors":[{"resource":"Search","field":"q","code":"missing"}],
     * "documentation_url":"https://developer.github.com/v3/search"}
     * @param queryStr
     * @return
     */
    @RequestMapping(value = "/search/repositories", method = RequestMethod.GET)
    String searchRepo(@RequestParam("q") String queryStr);
}
