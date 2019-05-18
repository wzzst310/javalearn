package com.wjjzst.learn.stack;

import com.wjjzst.learn.linkedlist.LinkedList;

/**
 * @Author: Wjj
 * @Date: 2019-04-25 01:15
 */
public class Stack<E> {
    // 组合方式优于继承
    private LinkedList<E> list = new LinkedList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E element) {
        list.add(element);
    }

    public E pop() {
        return list.remove(list.size() - 1);
    }

    public E peek() {
        return list.get(list.size() - 1);
    }

    public void clear() {
        list.clear();
    }
}
