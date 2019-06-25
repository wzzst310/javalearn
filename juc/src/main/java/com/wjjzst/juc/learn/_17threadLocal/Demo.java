package com.wjjzst.juc.learn._17threadLocal;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Wjj
 * @Date: 2019/6/26 1:17
 * @desc: 每个线程都有一个自己的count数量 不管我怎么加锁都没用
 * 单个线程的局部变量 根当前线程绑定  ThreadLocal有一个ThreadLocalMap
 * ThreadLocalMap key是Thread.cucurrentThread  value是一个ThreadLocalMap
 * ThreadLocalMap.getEntry(ThreadLocal<Integer>的实例为key) 得到一个Entry(ThreadLocalMap中的类)
 * entry.value  所需要存储的值
 */
public class Demo {
    // 直接创建一个threadLocal的子类
    private ThreadLocal<Integer> count = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return new Integer(0);
        }
    };

    // synchronized没什么用
    public synchronized int getNext() {
        Integer value = count.get();
        value++;
        count.set(value);
        return value;
    }

    public static void main(String[] args) {
        Demo d = new Demo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " : " + d.getNext());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " : " + d.getNext());
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " : " + d.getNext());
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
