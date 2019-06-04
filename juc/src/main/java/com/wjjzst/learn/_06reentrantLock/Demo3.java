package com.wjjzst.learn._06reentrantLock;

/**
 * @Author: Wjj
 * @Date: 2019/6/4 23:46
 * @desc: 死锁
 */
public class Demo3 {
    private Object obj1 = new Object();
    private Object obj2 = new Object();

    public void a() {
        synchronized (obj1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj2) {
                System.out.println("hello a");
            }
        }
    }

    public void b() {
        synchronized (obj2) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj1) {
                System.out.println("hello b");
            }
        }
    }

    public static void main(String[] args) {
        Demo3 demo3 = new Demo3();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo3.a();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo3.b();
            }
        }).start();
    }
}
