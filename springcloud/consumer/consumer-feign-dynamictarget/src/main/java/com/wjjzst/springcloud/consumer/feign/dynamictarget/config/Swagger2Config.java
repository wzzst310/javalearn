package com.wjjzst.springcloud.consumer.feign.dynamictarget.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: Wjj
 * @Date: 2020/5/5 9:33 上午
 * @desc:
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket CreateRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.wjjzst.springcloud.consumer.feign.dynamictarget.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("FeignClient动态选择问题").description("FeignClient动态选择问题")
                .contact(new Contact("wjj", "http://www.wjjzst.com", "wzzst310@163.com")).version("1.0").build();
    }

}
