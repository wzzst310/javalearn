package com.wjjzst.ads.firststage.learn.queue;

import com.wjjzst.ads.firststage.learn.linkedlist.LinkedList;

/**
 * @Author: Wjj
 * @Date: 2019-04-26 00:05
 */
public class BaseQueue<E> implements Queue<E> {
    private LinkedList<E> list;

    /**
     * // (进)尾 >> >> >> >> >> >> >> 头(出)
     */
    public BaseQueue() {
        list = new LinkedList<>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void enQueue(E element) {
        list.add(element);
    }

    @Override
    public E deQueue() {
        return list.remove(0);
    }

    @Override
    public E front() {
        return list.get(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }
}
