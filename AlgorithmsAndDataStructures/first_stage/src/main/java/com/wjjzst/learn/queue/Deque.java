package com.wjjzst.learn.queue;

import com.wjjzst.learn.linkedlist.LinkedList;

/**
 * @Author: Wjj
 * @Date: 2019-04-26 00:05
 */
public class Deque<E> {
    private LinkedList<E> list;

    /**
     *   // (进)尾 >> >> >> >> >> >> >> 头(出)
     */
    public Deque() {
        list = new LinkedList<>();
    }

    public int size() {
        return list.size();
    }

    public void enQueueFront(E element) {
        list.add(0, element);
    }

    public E deQueueFront() {
        return list.remove(0);
    }

    public void enQueueRear(E element) {
        list.add(element);
    }

    public E deQueueRear() {
        return list.remove(list.size() - 1);
    }

    public E front() {
        return list.get(0);
    }

    public E rear() {
        return list.get(list.size() - 1);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear(){
        list.clear();
    }
}
