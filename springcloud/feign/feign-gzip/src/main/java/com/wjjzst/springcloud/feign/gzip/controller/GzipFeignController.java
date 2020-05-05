package com.wjjzst.springcloud.feign.gzip.controller;

import com.wjjzst.springcloud.feign.gzip.service.GzipFeignService;
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
public class GzipFeignController {
    @Autowired
    private GzipFeignService gzipFeignService;

    // 服务消费者对位提供服务
    @GetMapping(value = "/search/github")
    public ResponseEntity<byte[]> searchGithubRepoByStr(@RequestParam("str") String queryStr) {
        return gzipFeignService.searchRepo(queryStr);
    }
}
