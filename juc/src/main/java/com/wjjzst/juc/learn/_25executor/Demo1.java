package com.wjjzst.juc.learn._25executor;

import java.util.concurrent.*;

/**
 * @Author: Wjj
 * @Date: 2019/7/21 21:03
 * @desc:
 */
public class Demo1 {
    public static void main(String[] args) {
        // 10个线程处理大量的任务
        // ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 0, TimeUnit.MICROSECONDS, new LinkedBlockingDeque<>());
        ExecutorService pool = Executors.newFixedThreadPool(10);
        // ExecutorService pool = Executors.newCachedThreadPool();  // 动态线程池 超过60秒就去掉空闲的线程
        // ExecutorService pool = Executors.newSingleThreadExecutor();  // 动态线程池 确保只有一个线程执行任务 一个线程挂了就会有一个备用的线程顶上去 每隔0.1秒钟执行一次
        // ScheduledExecutorService pool = Executors.newScheduledThreadPool(10); // 计划任务 有计划的去执行 corn 定时任务 加到延迟队列里面ScheduledThreadPoolExecutor.DelayedWorkQueue();
        // ExecutorService pool = Executors.newWorkStealingPool();//new ForkJoinPool()  Runnable 会包装成一个ForkjoinTask 最终执行


        ThreadFactory tf = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                return t;
            }
        };
        while (true) {
            Future<?> future = pool.submit(new Runnable() {
                @Override
                public void run() {

                }
            });
            try {
                Object o = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            /*Future<Integer> future = pool.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return null;
                }
            });*/
            /*pool.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            }, 5, TimeUnit.SECONDS);// 延迟5秒去执行*/
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
