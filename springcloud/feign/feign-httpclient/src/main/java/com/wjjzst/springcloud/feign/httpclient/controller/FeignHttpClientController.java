package com.wjjzst.springcloud.feign.httpclient.controller;

import com.wjjzst.springcloud.feign.httpclient.service.FeignHttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Wjj
 * @Date: 2020/4/29 1:08 上午
 * @desc:
 */
@RestController
public class FeignHttpClientController {
    @Autowired
    private FeignHttpClientService feignHttpClientService;

    // 服务消费者对位提供服务
    @GetMapping(value = "/search/github")
    public String searchGithubRepoByStr(@RequestParam("str") String queryStr) {
        return feignHttpClientService.searchRepo(queryStr);
    }
}
