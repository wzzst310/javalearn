package com.wjjzst.juc.learn._22future;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Demo {
    /**
     * Callable 和Runnable的区别
     * Runnable run方法是线程调用的,在run方法是异步执行的
     * Callable 不是异步执行的,是由Future的run方法调用的
     * FutureTask 三个主要方法 run()方法包含Callable方法) set()方法设置值并且唤醒其他线程(队列方式) get()方法 没拿到就进队列WaitNode  还有不同状态值
     */
    public static void main(String[] args) throws Exception {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("正在计算结果...");
                Thread.sleep(3000);
                return 1;
            }
        };

        FutureTask<Integer> task = new FutureTask<>(callable);
        Thread thread = new Thread(task);
        thread.start();
        // do something
        System.out.println("干点别的...");
        Integer result = task.get();
        System.out.println("拿到的结果为: " + result);
    }
}
