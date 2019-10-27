package com.wjjzst.juc.learn._28happensBefore;

/**
 * start规则
 */
public class Demo2 {
    public void a() {
        System.out.println("a");// 1 启动另一个线程的方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("b"); // 2
            }
        }).start();
    }
}
