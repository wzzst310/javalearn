package com.wjjzst.ads.learn.queue;


import com.wjjzst.ads.learn.heap.BinaryHeap;

import java.util.Comparator;

/**
 * @Author: Wjj
 * @Date: 2019/6/2 10:05
 * @desc: 优先级队列 优先级最高的先出队 非FIFO(先进先出)  还要看优先级
 */
public class PriorityQueue<E> implements Queue<E> {
    BinaryHeap<E> heap;

    public PriorityQueue(Comparator<E> comparator) {
        heap = new BinaryHeap<E>(comparator);
    }

    public PriorityQueue() {
        this(null);

    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public void enQueue(E element) {
        heap.add(element);
    }

    @Override
    public E deQueue() {
        return heap.remove();
    }

    @Override
    public E front() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public void clear() {
        heap.clear();
    }
}
