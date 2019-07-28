package com.wjjzst.juc.learn._28happensBefore;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo1 {

    private Lock lock = new ReentrantLock();

    public void a() {
        lock.lock();
        System.out.println("...");
        lock.unlock();  //1 解锁
    }

    public void b() {
        lock.lock();    //2 加锁
        System.out.println("...");
        lock.unlock();
    }
}
