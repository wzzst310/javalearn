package com.wjjzst.springcloud.eureka.server.single.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author: Wjj
 * @create: 2020/4/24 3:32 下午
 * @Description
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // spring-boot-starter-security默认开启csrf校验 Client不能通过页面填表单校验 所以需要完全禁用 或者禁用忽略一部分
        // http.csrf().disable();
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}
