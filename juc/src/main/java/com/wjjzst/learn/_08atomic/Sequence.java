package com.wjjzst.learn._08atomic;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: Wjj
 * @Date: 2019/5/29 8:38
 * @desc: synchronized就是内置锁(互斥锁)
 * monitorenter
 * monitoroexit
 */
public class Sequence {
    // 原子性更新基本类型
    private AtomicInteger value = new AtomicInteger(0);
    // AtomicBoolean
    // AtomicLong;

    // 原子性更新数组
    private int[] s = {1,2,3,4,5};
    AtomicIntegerArray as = new AtomicIntegerArray(s);
    //as.
    // 原子性更新抽象类型
    // 原子性更新字段


    public int getNext() {
        s[2] = 10;
        as.addAndGet(2,10);
        // value.incrementAndGet(); // 自增并且获取 ++ value
        // value.getAndIncrement() // 获取并且自增 value++
        return value.addAndGet(10);
    }


    public static void main(String[] args) {
        Sequence s = new Sequence();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程一").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程二").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程三").start();
    }
}
