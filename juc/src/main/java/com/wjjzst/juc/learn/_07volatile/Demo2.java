package com.wjjzst.juc.learn._07volatile;

/**
 * @Author: Wjj
 * @Date: 2019/6/5 0:20
 * @desc:     其他线程修改之后 当前线程立马能够知道
 * volatile 只能保证度和写的操作 在多个线程中的可见性 但不能保证(原子性的操作) 在多个线程之间的可见性
 * synchronize 能完全取代volatile 反之不行 volatile更轻量化(只有set和get的时候)
 *
 */
public class Demo2 {
    public volatile boolean run = false;

    public static void main(String[] args) {
        Demo2 demo2 = new Demo2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    System.err.println("执行了第" + i + "次");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                demo2.run = true;
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!demo2.run) {
                    // 不执行
                }
                System.err.println("线程2执行了");
            }
        }).start();
    }
}
