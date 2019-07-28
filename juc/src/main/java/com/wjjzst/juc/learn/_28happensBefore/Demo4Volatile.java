package com.wjjzst.juc.learn._28happensBefore;

public class Demo4Volatile {
    private int a;
    private volatile boolean flag;

    public void writer() {
        // 线程重排序  a 和flag 互不影响 真正计算机执行的时候可能先执行flag = true 可能先执行a = 1
        a = 1;
        flag = true;// 当写一个volatile变量时,Java内存模型会把线程对应的本地内存中的共享变量值刷新到主内存中
    }

    public void reader() {
        while (flag) {  // 当读一个volatile变量时,Java内存模型会把当前线程对应的本地内存中的共享变量置位无效,然后从主内存中读取共享变量
            int b = a + 1;
            System.out.println(b);

        }
    }
}
