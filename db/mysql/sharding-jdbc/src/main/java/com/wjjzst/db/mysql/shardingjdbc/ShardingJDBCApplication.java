package com.wjjzst.db.mysql.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Wjj
 * @Date: 2020/9/17 1:03 上午
 * @desc:
 */
@MapperScan("com.wjjzst.db.mysql.shardingjdbc.mapper")
@SpringBootApplication
public class ShardingJDBCApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingJDBCApplication.class, args);
    }
}
