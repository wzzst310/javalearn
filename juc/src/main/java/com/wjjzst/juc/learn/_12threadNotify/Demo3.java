package com.wjjzst.juc.learn._12threadNotify;

/**
 * @Author: Wjj
 * @Date: 2019/6/19 12:33
 * @desc:
 */
public class Demo3 {

    private volatile int singal; // 线程之间可见

    public synchronized int getSingal() {
        System.out.println(Thread.currentThread().getName() + " 方法执行了...");
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
        notify();
    }

    public static void main(String[] args) {
        Demo3 demo = new Demo3();
        Target1 t1 = new Target1(demo);
        Target2 t2 = new Target2(demo);
        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();
    }

}
