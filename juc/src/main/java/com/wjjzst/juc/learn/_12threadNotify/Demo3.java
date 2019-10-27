package com.wjjzst.juc.learn._12threadNotify;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Wjj
 * @Date: 2019/6/19 12:33
 * @desc:
 */
public class Demo3 {

    private volatile int singal; // 线程之间可见

    public synchronized int getSingal() {
        System.out.println(Thread.currentThread().getName() + " 方法执行了...");
        /*try {
            // Thread.sleep(1000);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        if (singal != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 方法执行完毕...");
        return singal;
    }

    public synchronized void setSingal() {
        this.singal = 1;
        // notify();  // notify()会随机叫醒一个处于wait状态的线程
        notifyAll(); // notify()会叫醒所有处于wait状态的线程  争夺到时间片的线程只有一个
        System.out.println("叫醒线程之后休眠开始...");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 调用notifyAll之后要等synchronized 代码块完全执行完毕(synchronized所在的方法)  其他线程才能拿到锁
        // 并不是一调用notifyAll其他线程就立马能拿到锁
    }

    public static void main(String[] args) {
        Demo3 demo = new Demo3();
        Target1 t1 = new Target1(demo);
        Target2 t2 = new Target2(demo);
        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();
        //  四个线程同时进入了   调用wait()方法 会释放掉当前拿到的锁
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t1).start();
    }

}
