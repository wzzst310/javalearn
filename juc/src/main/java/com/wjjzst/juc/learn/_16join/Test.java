package com.wjjzst.juc.learn._16join;

/**
 * @Author: Wjj
 * @Date: 2019/6/26 0:06
 * @desc:
 */
public class Test {
    private static void b(A a) {
        a.a();
    }

    public static void main(String[] args) {
        A a = new A(1);
        b(a);
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

    public synchronized void a() {
        System.out.println(equals(new A(1)));
    }
}
