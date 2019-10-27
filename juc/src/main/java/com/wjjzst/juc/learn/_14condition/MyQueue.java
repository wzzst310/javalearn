package com.wjjzst.juc.learn._14condition;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Wjj
 * @Date: 2019/6/20 7:45
 * @desc:
 */
public class MyQueue<E> {
    private E[] elements;
    private int addIndex;
    private int removeIndex;
    private int queueSize;
    private Lock lock = new ReentrantLock();
    Condition addCondition = lock.newCondition();
    Condition removeCondition = lock.newCondition();

    public MyQueue(int count) {
        elements = (E[]) new Objects[count];
    }

    public void add(E e) {
        lock.lock();
        while (queueSize == elements.length) {
            try {
                addCondition.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        elements[addIndex] = e;
        if (++addIndex == elements.length) {
            addIndex = 0;
        }
        queueSize++;
        removeCondition.signal();
        lock.unlock();
    }

    public void remove() {
        lock.lock();
        while (queueSize == 0) {
            try {
                System.out.println(Thread.currentThread().getName() + "队列为空,不进行移除");
                removeCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        elements[removeIndex++] = null;
        if (++removeIndex == elements.length) {
            removeIndex = 0;
        }
        queueSize--;
        addCondition.signal();
        lock.unlock();
    }
}
