package com.wjjzst.jvm.clazz;

/**
 * @Author: Wjj
 * @Date: 2020/3/24 9:47 下午
 * @desc: 方法动态分派演示
 */
public class DynamicDispatch {
    static abstract class Human {
        protected abstract void sayHello();
    }
    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("woman say hello");
        }
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
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
}
