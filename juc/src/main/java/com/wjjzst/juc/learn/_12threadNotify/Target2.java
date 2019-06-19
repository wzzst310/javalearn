package com.wjjzst.juc.learn._12threadNotify;

/**
 * @Author: Wjj
 * @Date: 2019/6/19 12:35
 * @desc:
 */
public class Target2 implements Runnable {
    private Demo3 demo;

    public Target2(Demo3 demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        //System.out.println(Thread.currentThread().getName() + "执行了..." + demo.getSingal())  ;
        demo.getSingal();
    }
}
