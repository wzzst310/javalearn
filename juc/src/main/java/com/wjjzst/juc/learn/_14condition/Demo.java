package com.wjjzst.juc.learn._14condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Wjj
 * @Date: 2019/6/20 7:13
 * @desc:
 */
public class Demo {
    private int signal;
    Lock lock = new ReentrantLock();
    Condition a = lock.newCondition();
    Condition b = lock.newCondition();
    Condition c = lock.newCondition();

    public void a() {
        lock.lock();
        while (signal != 0) {
            try {
                a.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("a");
        signal++;
        b.signal();
        lock.unlock();
    }

    public void b() {
        lock.lock();
        while (signal != 1) {
            try {
                b.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("b");
        signal++;
        c.signal();
        lock.unlock();
    }

    public void c() {
        lock.lock();
        while (signal != 2) {
            try {
                c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("c");
        signal = 0;
        a.signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        A A = new A(demo);
        B B = new B(demo);
        C C = new C(demo);
        new Thread(A).start();
        new Thread(B).start();
        new Thread(C).start();
    }
}


class A implements Runnable {
    private Demo demo;

    public A(Demo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        while (true) {
            demo.a();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class B implements Runnable {
    private Demo demo;

    public B(Demo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        while (true) {
            demo.b();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class C implements Runnable {
    private Demo demo;

    public C(Demo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        while (true) {
            demo.c();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}