package com.wjjzst.juc.learn._9lock1ByMyself;

import java.util.concurrent.locks.Lock;

/**
 * @Author: Wjj
 * @Date: 2019/5/29 8:38
 * @desc:
 */
public class Sequence {

    private int value;
    // 所有的线程公用一把锁才能锁住
    Lock lock = new MyLock();

    public int getNext() {
        lock.lock();
        value++;
        lock.unlock();
        return value;
    }


    public static void main(String[] args) {
        Sequence s = new Sequence();
        // s.getNext();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                }
            }
        }, "线程一").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                }
            }
        }, "线程二").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                }
            }
        }, "线程三").start();
    }
}
