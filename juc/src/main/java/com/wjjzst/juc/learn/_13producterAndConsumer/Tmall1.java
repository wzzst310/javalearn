package com.wjjzst.juc.learn._13producterAndConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Wjj
 * @Date: 2019/6/20 6:35
 * @desc:
 */
public class Tmall1 extends Tmall{
    private int count;
    public final int MAX_COUNT = 10;
    private Lock lock = new ReentrantLock();
    private Condition p = lock.newCondition();
    private Condition t = lock.newCondition();

    public void push() {
        lock.lock();
        while (count >= MAX_COUNT) {
            try {
                System.out.println(Thread.currentThread().getName() + "库存数量达到上限,生产者停止生产");
                p.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        System.out.println(Thread.currentThread().getName() + "生产者生产,当前库存数量为" + count);
        t.signal();
        lock.unlock();
    }

    public void take() {
        lock.lock();
        while (count <= 0) {
            try {
                System.out.println(Thread.currentThread().getName() + "库存数量为零,消费者等待");
                t.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        System.out.println(Thread.currentThread().getName() + "消费者消费,当前库存数量为" + count);
        p.signal();
        lock.unlock();
    }

}
