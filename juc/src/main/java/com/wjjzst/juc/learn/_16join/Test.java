package com.wjjzst.juc.learn._16join;

/**
 * @Author: Wjj
 * @Date: 2019/6/26 0:06
 * @desc:
 */
public class Test {
    private void b(A a) {
        a.a();
    }

    public static void main(String[] args) {
        Test t = new Test();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                A a = new A(1);
                t.b(a);
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("haha");
    }
}

class A {
    public A(int a) {
        this.a = a;
    }

    private int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    public boolean equals(Object obj) {
        return ((A) obj).getA() == a;
    }

    /*public synchronized void a() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(equals(new A(1)));
    }*/

    public synchronized void a() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(equals(new A(1)));
    }
}
