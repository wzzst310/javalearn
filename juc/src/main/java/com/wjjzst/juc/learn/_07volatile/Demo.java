package com.wjjzst.juc.learn._07volatile;

/**
 * @Author: Wjj
 * @Date: 2019/6/5 0:07
 * @desc: 保证可见性的前提
 * 多个线程拿到的同一把锁,否则是保证不了的
 */
public class Demo {
    public volatile int a = 1;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.a = a;
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.a = 10;
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(demo.a);
            }
        }).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("最终结果:" + demo.getA());
    }
}
