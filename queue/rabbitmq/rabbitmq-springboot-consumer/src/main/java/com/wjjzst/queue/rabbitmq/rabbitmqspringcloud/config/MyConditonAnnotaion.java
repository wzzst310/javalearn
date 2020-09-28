package com.wjjzst.queue.rabbitmq.rabbitmqspringcloud.config;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author: Wjj
 * @create: 2020/9/19 1:23 下午
 * @Description
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface MyConditonAnnotaion {
    String value() default "";

    String name() default "";

    String[] matchValues() default {};
}
