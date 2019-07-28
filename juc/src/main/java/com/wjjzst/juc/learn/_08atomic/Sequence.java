package com.wjjzst.juc.learn._08atomic;

import java.util.concurrent.atomic.*;

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
    private LongAdder longAddr = new LongAdder();
    // DoubleAdder

    // 原子性更新数组
    private int[] s = {1, 2, 3, 4, 5};
    AtomicIntegerArray as = new AtomicIntegerArray(s);
    AtomicReference<User> user = new AtomicReference<>();
    // 更新一个类的Integer类型的字段
    AtomicIntegerFieldUpdater<User> age = AtomicIntegerFieldUpdater.newUpdater(User.class,"age");

    //as.
    // 原子性更新抽象类型
    // 原子性更新字段


    public int getNext() {
        s[2] = 10;
        as.addAndGet(2, 10);
        as.getAndIncrement(2);
        as.getAndAdd(2, 10);
        longAddr.increment();
        User u = new User();
        u.setAge(9);
        this.user.getAndSet(u);
        age.getAndIncrement(u);
        System.out.println(u);
        age.getAndIncrement(u);
        System.out.println(u);
        age.getAndIncrement(u);
        System.out.println(u);

        // 对User的实例SetandGet
        // value.incrementAndGet(); // 自增并且获取 ++ value
        // value.getAndIncrement() // 获取并且自增 value++
        return value.addAndGet(10);
    }


    public static void main(String[] args) {
        Sequence s = new Sequence();
        s.getNext();
        /*new Thread(new Runnable() {
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
        }, "线程三").start();*/
    }
}
