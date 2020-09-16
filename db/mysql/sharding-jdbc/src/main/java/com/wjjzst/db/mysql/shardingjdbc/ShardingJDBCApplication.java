package com.wjjzst.db.mysql.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: Wjj
 * @Date: 2020/9/17 1:03 上午
 * @desc:
 */
@MapperScan("com.wjjzst.db.mysql.shardingjdbc.mapper")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ShardingJDBCApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingJDBCApplication.class, args);
    }
}
