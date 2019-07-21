package com.wjjzst.juc.learn._25executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Wjj
 * @Date: 2019/7/21 19:31
 * @desc:
 */
public class Demo {
    public static void main(String[] args) {
        // 初始线程池大小  扩容  线程池最大线程最大数量 ThreadFactory 创建线程的工厂 自定义线程  RejectedExecutionHandler 饱和策略 线程都用完了
        // keepAliveTime 有些线程不要用到了 就需要缩容  缩容判定线程超过了keepAliveTime就终止线程 知道最小的线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 20, 10,
                //TimeUnit.DAYS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardOldestPolicy());//饱和了就丢弃处理
                TimeUnit.DAYS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());//饱和了就main线程处理 耦合策略
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < 100; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    count.getAndAdd(1);
                }
            });
        }
        threadPool.shutdown();// 结束 但是没完成的还是会执行
        // threadPool.shutdownNow();// 立即结束
        while (Thread.activeCount() > 2) {

        }
        System.out.println(count.get());
    }
}
