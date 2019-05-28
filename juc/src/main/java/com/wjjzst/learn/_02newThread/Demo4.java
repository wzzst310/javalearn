package com.wjjzst.learn._02newThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: Wjj
 * @Date: 2019/5/28 22:57
 * @desc: 带返回值的线程
 */
public class Demo4 implements Callable<String> {  // V表示返回的类型

    @Override
    public String call() throws Exception {
        System.out.println("进行计算....");
        Thread.sleep(2000);
        return String.valueOf(System.currentTimeMillis());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Demo4 demo4 = new Demo4();
        FutureTask<String> task = new FutureTask<>(demo4);
        Thread thread = new Thread(task);
        thread.start();
        System.out.println("主线程先干点别的");
        String s = task.get();
        System.out.println("线程执行完的结果:" + s);
    }
}
