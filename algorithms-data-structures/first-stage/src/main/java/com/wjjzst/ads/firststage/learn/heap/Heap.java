package com.wjjzst.ads.firststage.learn.heap;

import java.util.Collection;

/**
 * @Author: Wjj
 * @Date: 2019/5/27 23:54
 * @desc:
 */
public interface Heap<E> {
    int size();
    boolean isEmpty();
    void clear();
    void add(E element);
    E get();
    E remove();
    E replace(E element);
    void addAll(Collection<E> outEdges);
}
