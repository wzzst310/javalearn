package com.wjjzst.juc.learn._20semaphore;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Demo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        Demo d = new Demo();
        Semaphore semaphore = new Semaphore(10);
        while (true) {
            /*threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 执行完毕...");
                }
            });*/
            new Thread(new Runnable() {
                @Override
                public void run() {
                    d.method(semaphore);
                }
            }).start();
        }
    }

    private void method(Semaphore semaphore) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is run ...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
    }
}
