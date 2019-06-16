package com.wjjzst.learn._10fairLock;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Wjj
 * @Date: 2019/6/16 19:42
 * @desc:
 */
public class Main {
    private int value;
    // private FairLock1 lock = new FairLock1();
    private ReentrantLock lock = new ReentrantLock(true);

    public int next() {
        lock.lock();
        try {
            return value++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    // System.out.println(Thread.currentThread().getName() + "启动");
                    System.out.println(Thread.currentThread().getName() + ":" + m.next());
                }
            }, "线程" + i);
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}
