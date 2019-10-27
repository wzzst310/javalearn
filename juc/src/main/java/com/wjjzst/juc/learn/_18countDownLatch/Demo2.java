package com.wjjzst.juc.learn._18countDownLatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Demo2 extends Demo {

    public Demo2(int line) {
        super(line);
    }

    public void calc(String line, int index, CountDownLatch latch) {
        super.calc(line, index);
        latch.countDown();
    }

    public static void main(String[] args) {
        List<String> contents = readFile();
        int lineCount = contents.size();
        CountDownLatch latch = new CountDownLatch(lineCount);
        Demo2 d = new Demo2(lineCount);
        for (int i = 0; i < lineCount; i++) {
            final int j = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    d.calc(contents.get(j), j, latch);
                }
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        d.sum();
    }

}
