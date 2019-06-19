package com.wjjzst.juc.learn._03threadPriority;

/**
 * @Author: Wjj
 * @Date: 2019/5/29 0:49
 * @desc:
 */
public class Target implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
