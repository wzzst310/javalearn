package com.wjjzst.juc.learn._27instructioReordering;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 重排序问题
 */
public class Demo {
    private int a;
    private boolean flag;

    public void writer() {
        // 线程重排序  a 和flag 互不影响 真正计算机执行的时候可能先执行flag = true 可能先执行a = 1
        a = 1;
        flag = true;
    }

    public void reader() {
        while (flag) {
            int b = a + 1;
            if (b == 1) {
                System.out.println("发生了原子性重排序");
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(128);
        pool.execute(new Runnable() {
            @Override
            public void run() {
                Demo demo = new Demo();
                demo.writer();
                demo.reader();
            }
        });
    }
}
