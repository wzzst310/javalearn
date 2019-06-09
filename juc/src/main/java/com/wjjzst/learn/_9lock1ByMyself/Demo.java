package com.wjjzst.learn._9lock1ByMyself;

public class Demo {
    MyLock lock = new MyLock();

    public void a() {
        lock.lock();
        System.out.println("a");
        b();  // 发生死锁
        lock.unlock();
    }

    public void b() {
        lock.lock();
        System.out.println("b");
        c();
        lock.unlock();
    }

    public void c() {
        lock.lock();
        System.out.println("c");
        lock.unlock();
    }

    public static void main(String[] args) {
        Demo d = new Demo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.a();
            }
        }).start();
    }
}
