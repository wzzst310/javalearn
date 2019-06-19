package com.wjjzst.juc.learn._14condition;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Wjj
 * @Date: 2019/6/20 7:13
 * @desc:
 */
public class Demo1 {

    private int signal;

    public synchronized void a() {
        while (signal != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("a");
        signal++;
        notifyAll();
    }

    public synchronized void b() {
        while (signal != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("b");
        signal++;
        notifyAll();
    }

    public synchronized void c() {
        while (signal != 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("c");
        signal = 0;
        notifyAll();
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        A1 a1 = new A1(demo1);
        B1 b1 = new B1(demo1);
        C1 c1 = new C1(demo1);
        new Thread(a1).start();
        new Thread(b1).start();
        new Thread(c1).start();
    }
}


class A1 implements Runnable {
    private Demo1 demo1;

    public A1(Demo1 demo1) {
        this.demo1 = demo1;
    }

    @Override
    public void run() {
        while (true) {
            demo1.a();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class B1 implements Runnable {
    private Demo1 demo1;

    public B1(Demo1 demo1) {
        this.demo1 = demo1;
    }

    @Override
    public void run() {
        while (true) {
            demo1.b();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class C1 implements Runnable {
    private Demo1 demo1;

    public C1(Demo1 demo1) {
        this.demo1 = demo1;
    }

    @Override
    public void run() {
        while (true) {
            demo1.c();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}