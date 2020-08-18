package com.wjjzst.springcloud.consumer.feign.dynamictarget.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Wjj
 * @Date: 2020/5/5 10:22 上午
 * @desc:
 */
@Configuration
@EnableWebMvc
// public class ApplicationExceptionAdapter extends WebMvcConfigurerAdapter {
public class ApplicationExceptionAdapter implements WebMvcConfigurer {
    // 防止静态资源请求被DispatcherServlet  静态资源直接映射到实际路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public RouteTargeter getRouteTargeter() {
        return new RouteTargeter();
    }
}
