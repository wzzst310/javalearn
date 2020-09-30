package com.wjjzst.db.mysql.shardingsphere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Wjj
 * @Date: 2020/9/17 1:03 上午
 * @desc:
 */
@MapperScan("com.wjjzst.db.mysql.shardingsphere.mapper")
@SpringBootApplication
public class ShardingSphereApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereApplication.class, args);
    }
}
