package com.wjjzst.juc.learn._13producterAndConsumer;

/**
 * @Author: Wjj
 * @Date: 2019/6/20 6:39
 * @desc:
 */
public class Main {
    public static void main(String[] args) {
        // Tmall tmall = new Tmall();
        Tmall2 tmall = new Tmall2();
        PushTarget p = new PushTarget(tmall);
        TakeTarget t = new TakeTarget(tmall);
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                tmall.size();
            }
        }).start();
    }
}
