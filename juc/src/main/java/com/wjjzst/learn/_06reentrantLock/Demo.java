package com.wjjzst.learn._06reentrantLock;

/**
 * @Author: Wjj
 * @Date: 2019/6/4 23:15
 * @desc: 重入锁
 * 自旋锁 一直while true  （CPU时间片）
 *
 */
public class Demo {
    public synchronized void a() {
        System.out.println("a");
        //b(); //并不会出现死锁的问题 b方法会被执行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void b() {
        System.out.println("b");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        Demo demo1 = new Demo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.a();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //demo.b();
                demo1.b();
            }
        }).start();
    }
}
