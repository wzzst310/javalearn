package com.wjjzst.learn._02newThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Wjj
 * @Date: 2019/5/28 23:14
 * @desc: 线程池实现多线程
 */
public class Demo6 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);//固定数量的线程池
        for (int i = 0; i < 100; i++) {  //10个线程随机执行
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
        threadPool.shutdown();
    }
}
