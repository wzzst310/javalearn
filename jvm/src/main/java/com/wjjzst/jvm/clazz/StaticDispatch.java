package com.wjjzst.jvm.clazz;

/**
 * @Author: Wjj
 * @Date: 2020/3/24 9:47 下午
 * @desc: 方法静态分派演示
 */
public class StaticDispatch {
    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Woman extends Human {

    }

    public void sayHello(Human gay) {
        System.out.println("hello gay");
    }

    public void sayHello(Man gay) {
        System.out.println("hello,gentleman");
    }

    public void sayHello(Woman gay) {
        System.out.println("hello,lady");
    }

    public static void main(String[] args) {
        //Man man = new Man();
        //Woman woman = new Woman();
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sd = new StaticDispatch();
        sd.sayHello(man);
        sd.sayHello(woman);
    }
}
