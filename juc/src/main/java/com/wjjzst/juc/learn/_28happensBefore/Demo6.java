package com.wjjzst.juc.learn._28happensBefore;

public class Demo6 {
    private final Object obj;

    public Demo6() {
        this.obj = new Object();
    }

    private Demo6 demo;

    public void w() {
        demo = new Demo6();
    }

    public void r() {
        Demo6 d = demo;
    }
}
