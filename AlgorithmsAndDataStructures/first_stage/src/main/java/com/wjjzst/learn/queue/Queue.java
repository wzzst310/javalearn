package com.wjjzst.learn.queue;

import com.wjjzst.learn.linkedlist.LinkedList;

/**
 * @Author: Wjj
 * @Date: 2019-04-26 00:05
 */
public class Queue<E> {
    private LinkedList<E> list;

    /**
     * // (进)尾 >> >> >> >> >> >> >> 头(出)
     */
    public Queue() {
        list = new LinkedList<>();
    }

    public int size() {
        return list.size();
    }

    public void enQueue(E element) {
        list.add(element);
    }

    public E deQueue() {
        return list.remove(0);
    }

    public E front() {
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
