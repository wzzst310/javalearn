package com.wjjzst.juc.learn._22future;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Demo {
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
