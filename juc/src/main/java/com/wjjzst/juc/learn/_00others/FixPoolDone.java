package com.wjjzst.juc.learn._00others;

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

        int maxCore = Runtime.getRuntime().availableProcessors() * 2;
        int count = 100000;
        int batch = 1000;
        int batchSize = count / batch;
        int last = count - batchSize * batch;
        int lastflag = last == 0 ? 0 : 1;
        int poolSize = Math.max(maxCore, batchSize + lastflag);
        poolSize = 4;
        AtomicInteger ai = new AtomicInteger(count);
        ExecutorService pool = Executors.newFixedThreadPool(poolSize);
        AtomicInteger counter = new AtomicInteger(1);
        for (int i = 0; i < poolSize; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    while (ai.get() > 0) {
                        System.out.println("thread: " + Thread.currentThread().getName() + " value=" + ai.get() + " haha: " + counter.get());
                        /*try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                        counter.getAndIncrement();
                        ai.getAndAdd(-1);
                    }
                }
            });
        }
        while (ai.get() <= 0) {
            System.out.println("判断停止不停止");
            pool.shutdown();
        }
        // pool.shutdown();
        System.out.println("end==============");
    }
}
