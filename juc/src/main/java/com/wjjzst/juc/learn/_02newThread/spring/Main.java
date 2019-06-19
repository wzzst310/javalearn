package com.wjjzst.juc.learn._02newThread.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: Wjj
 * @Date: 2019/5/28 23:45
 * @desc:
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        DemoService ds = ac.getBean(DemoService.class);
        ds.a();
        ds.b();
    }
}
