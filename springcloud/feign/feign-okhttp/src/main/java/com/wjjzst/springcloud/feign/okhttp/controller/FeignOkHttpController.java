package com.wjjzst.springcloud.feign.okhttp.controller;

import com.wjjzst.springcloud.feign.okhttp.service.FeignOkHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Wjj
 * @Date: 2020/4/29 1:08 上午
 * @desc:
 */
@RestController
public class FeignOkHttpController {
    @Autowired
    private FeignOkHttpService okHttpService;

    // 服务消费者对位提供服务
    @GetMapping(value = "/search/github")
    public String searchGithubRepoByStr(@RequestParam("str") String queryStr) {
        return okHttpService.searchRepo(queryStr);
    }
}
