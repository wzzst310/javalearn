package com.wjjzst.juc.learn._19cyclicBarrier;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Demo {
    Random random = new Random();

    public void meeting(CyclicBarrier barrier) {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(4));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 到达会议室,等待开会...");
        /*if (Thread.currentThread().getName().equals("Thread-11")) {
            throw new RuntimeException();
        }*/
       /*if (Thread.currentThread().getName().equals("Thread-7")) {
            Thread.currentThread().interrupt();
        }*/
        try {
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "  发言");
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        CyclicBarrier barrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() { //最后一个线程执行此代码
                System.out.println(Thread.currentThread().getName());
                System.out.println("好!我们开始开会...");
            }
        });
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.meeting(barrier);
                }
            }).start();
        }
    }
}
