package com.wjjzst.juc.learn._02newThread;

/**
 * @Author: Wjj
 * @Date: 2019/5/28 22:23
 * @desc: 实现Runable接口,作为线程任务存在(线程所要执行的功能)
 */
public class Demo2 implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("thread running...");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Demo2());
        thread.start();
    }
}
