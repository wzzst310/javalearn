package com.wjjzst.juc.learn._13producterAndConsumer;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Wjj
 * @Date: 2019/6/20 6:38
 * @desc:
 */
public class TakeTarget implements Runnable {
    private Tmall tmall;

    public TakeTarget(Tmall tmall) {
        this.tmall = tmall;
    }

    @Override
    public void run() {
        while (true) {
            tmall.take();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
