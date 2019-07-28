package com.wjjzst.juc.learn._28happensBefore;

/**
 * 写final域的重排序规则禁止把final域的写重排序到构造方法之外
 * 编译器 内存屏障
 * 编译器会在final域的写之后  在构造方法执行完毕之前, 插入一个内存屏障StoreStore, 保证处理器把final域的写操作在构造方法中执行
 * LoadLoad   load1  LoadLoad load2
 * StoreStore store1 StoreStore store2
 * LoadStore
 * StoreLoad
 * <p>
 * 在一个线程中,初次读对象引用和初次读该对象所包含的的final域,Java内存模型禁止处理器重排序这两个操作
 */
public class Demo5Final {

    private int a;

    private final int b;

    // private final int b = 10 ; 优先级最高

    /*private static final int b;
    static { 类加载的时候
        b= 10;
    }*/

    //代码块优先于构造方法
    {
        b = 10;
    }

    public Demo5Final() {
        // this.b = 10;
    }

    private Demo5Final demo;

    public void w() {
        demo = new Demo5Final();
    }

    public void r() {
        Demo5Final d = demo;
        int temp1 = d.a;
        int temp2 = d.b;
    }
}
