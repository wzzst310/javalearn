package com.wjjzst.ads.firststage.learn.queue;

/**
 * @Author: Wjj
 * @Date: 2019/6/2 10:13
 * @desc:
 */
public interface Queue<E> {

    int size();

    void enQueue(E element);

    E deQueue();

    E front();

    boolean isEmpty();

    void clear();
}
