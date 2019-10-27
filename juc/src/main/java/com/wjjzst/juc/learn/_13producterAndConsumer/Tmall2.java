package com.wjjzst.juc.learn._13producterAndConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Wjj
 * @Date: 2019/6/20 6:35
 * @desc:
 */
public class Tmall2 extends Tmall {


    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(MAX_COUNT);

    public void push() {
        //抛出异常
        //queue.add(1);
        //有返回值
        //queue.offer(1);
        try {
            // 阻塞
            queue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void take() {
        // 没有抛出异常
        // queue.remove();
        //有返回值
        //queue.poll(); 没有值返回空
        try {
            // 阻塞的
            queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void size() {
        while (true) {
            try {
                System.out.println("当前队列的长度为： " + queue.size());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
