package com.wjjzst.jvm.clazz;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Wjj
 * @Date: 2020/3/30 12:40 上午
 * @desc:
 */
public class VolatileAtomicTest {
    public static AtomicInteger race = new AtomicInteger(0);

    public static void increase() {
        race.incrementAndGet();
    }

    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) {
        for (Thread thread : new Thread[THREAD_COUNT]) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        increase();
                    }
                }
            });
            thread.start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(race);
    }
}
