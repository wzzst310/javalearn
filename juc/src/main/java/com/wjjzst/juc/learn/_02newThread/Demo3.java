package com.wjjzst.juc.learn._02newThread;

/**
 * @Author: Wjj
 * @Date: 2019/5/28 22:39
 * @desc: 匿名类创建
 */
public class Demo3 {
    public static void main(String[] args) {
        // 匿名类
        new Thread() {
            @Override
            public void run() {
                System.out.println("thread running...");
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread running...");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread running...");
            }
        }) {
            @Override
            public void run() {
                System.out.println("subThread running...");
            }
        }.start();
    }
}
