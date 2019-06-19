package com.wjjzst.ads.learn.heap;

import java.util.Comparator;

/**
 * @Author: Wjj
 * @Date: 2019/5/29 22:13
 * @desc:
 */
public abstract class AbstractHeap<E> implements Heap<E> {

    protected int size;
    protected Comparator comparator;

    public AbstractHeap(Comparator comparator) {
        this.comparator = comparator;
    }
    public AbstractHeap() {
        this(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    protected int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2) : ((Comparable) e1).compareTo(e2);
    }
}
