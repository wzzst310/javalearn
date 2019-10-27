package com.wjjzst.juc.learn._28happensBefore;

public class Demo {
    /**
     * 在java的内存模型中,如果一个操作执行的结果需要对另一个操作可见,那么这两个操作之间必然存在着happens-before关系
     * happens-before规则如下
     * 程序顺序规则   可能遇到重排序
     * 监视器锁规则   对于一个锁的解锁总是happens-before于之后对这个锁的加锁
     * volatile变量规则   对于一个volatile域的写,happens-before于任意后续对这个volatile域的读
     * 传递性     a happens-before b  b happens-before a   name a happens-before c
     * Start规则  a调用创建b线程的方法  那么a happens-before b
     * Join规则   a线程join b线程  那么b线程happens-before a
     */
}
