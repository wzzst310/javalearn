package com.wjjzst.juc.learn._00others;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Wjj
 * @create: 2020/8/4 9:59 上午
 * @Description
 */
public class FixPoolDone {
    public static void main(String[] args) {

        int maxCore = Runtime.getRuntime().availableProcessors() * 2 + 1;
        int count = 90100;
        int batch = 1000;
        int batchSize = count / batch;
        int last = count - batch * batchSize;
        int poolSize = Math.min(maxCore, batchSize);
        ExecutorService pool = Executors.newFixedThreadPool(poolSize);
        CountDownLatch cdl = new CountDownLatch(poolSize);
        AtomicInteger ai = new AtomicInteger(count);
        AtomicInteger counter = new AtomicInteger(0);
        for (int i = 0; i < poolSize; i++) {
            pool.execute(() -> {
                while (ai.get() > 0) {
                    if ((ai.addAndGet(-1 * batch)) > 0) {
                        System.out.println("一批" + batch + " 个" + " 第" + counter.incrementAndGet() + "批");
                    }
                }
                cdl.countDown();
                System.out.println(cdl.getCount());
            });
        }
        try {
            cdl.await();
            pool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("剩余" + last + "个");
        System.out.println("end==============");
    }
}
