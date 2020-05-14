package com.wjjzst.springcloud.consumer.hystrix.hello.service;

/**
 * @Author: Wjj
 * @Date: 2020/5/15 12:18 上午
 * @desc:
 */
public interface IUserService {
    String getUser(String username) throws Exception;
}
