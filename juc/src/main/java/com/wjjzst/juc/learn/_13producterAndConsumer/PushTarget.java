package com.wjjzst.juc.learn._13producterAndConsumer;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Wjj
 * @Date: 2019/6/20 6:37
 * @desc:
 */
public class PushTarget implements Runnable {
    private Tmall tmall;

    public PushTarget(Tmall tmall) {
        this.tmall = tmall;
    }

    @Override
    public void run() {
        while (true) {
            tmall.push();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
