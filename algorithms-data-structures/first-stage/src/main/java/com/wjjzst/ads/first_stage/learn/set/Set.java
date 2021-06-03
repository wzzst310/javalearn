package com.wjjzst.ads.first_stage.learn.set;

/**
 * @Author: Wjj
 * @Date: 2019/5/19 19:55
 * @desc:
 */
public interface Set<E> {
    int size();

    boolean isEmpty();

    void clear();

    boolean contains(E element);

    void add(E element);

    void remove(E element);

    void traversal(Visitor<E> visitor);

    /*public static abstract class Visitor<E> {
        boolean stop;

        abstract boolean visit(E element);
    }*/
    @FunctionalInterface
    interface Visitor<E> {

        boolean stop = false;

        boolean visit(E element);
    }
}
