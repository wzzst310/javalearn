package com.wjjzst.learn._03threadPriority;

/**
 * @Author: Wjj
 * @Date: 2019/5/29 0:48
 * @desc: 线程优先级
 */
public class Demo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Target());
        Thread t2 = new Thread(new Target());
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();
    }
}
