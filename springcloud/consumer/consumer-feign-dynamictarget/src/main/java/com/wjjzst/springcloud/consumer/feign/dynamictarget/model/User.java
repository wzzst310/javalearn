package com.wjjzst.springcloud.consumer.feign.dynamictarget.model;

import lombok.Data;

/**
 * @Author: Wjj
 * @Date: 2020/5/5 8:56 上午
 * @desc:
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
}
