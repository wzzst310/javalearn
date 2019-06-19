package com.wjjzst.juc.learn._05singleton;

/**
 * 饿汉式没有线程安全性问题
 */
public class EagerSingleton {
    private EagerSingleton() {

    }

    private static EagerSingleton instance = new EagerSingleton();

    public static EagerSingleton getInstance() {
        return instance;
    }

    // 多线程环境下
    // 必须有共享资源
    // 对资源进行非原子性操作
}
