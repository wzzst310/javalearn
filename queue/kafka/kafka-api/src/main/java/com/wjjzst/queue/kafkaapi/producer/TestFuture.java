package com.wjjzst.queue.kafkaapi.producer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: Wjj
 * @Date: 2020/5/25 1:48 上午
 * @desc:
 */
public class TestFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();

        Future<?> future = executor.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("i = " + i);
                }
            }
        });
        future.get();//阻塞

        System.out.println("==================================");

        executor.shutdown();

    }
}
