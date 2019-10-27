package com.wjjzst.juc.learn._23forkjoin;

import java.util.concurrent.*;

public class Demo extends RecursiveTask<Integer> {
    private int begin;
    private int end;

    public Demo(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    //拆分任务
    @Override
    protected Integer compute() {
        // System.out.println(Thread.currentThread().getName() + "......");
        int sum = 0;
        if (end - begin <= 2) {
            //计算
            for (int i = begin; i <= end; i++) {
                sum += i;
            }
        } else {
            //拆分任务
            Demo d1 = new Demo(begin, (begin + end) / 2);
            Demo d2 = new Demo((begin + end) / 2 + 1, end);

            //执行任务
            d1.fork();
            d2.fork();
            Integer a = d1.join();
            Integer b = d2.join();
            sum = a + b;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(8);
        Future<Integer> future = null;
        future = pool.submit(new Demo(1, 1000000000));
        System.out.println(".....");
        try {
            System.out.println("计算的值为: " + future.get());
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
