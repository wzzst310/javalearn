package com.wjjzst.juc.learn._12threadNotify;

/**
 * @Author: Wjj
 * @Date: 2019/6/19 12:34
 * @desc:
 */
public class Target1 implements Runnable {
    private Demo3 demo;

    public Target1(Demo3 demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        demo.setSingal();
    }
}
