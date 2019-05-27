package com.wjjzst.learn.heap;

import com.wjjzst.learn.common.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * @Author: Wjj
 * @Date: 2019/5/27 23:55
 * @desc:
 */
public class BinaryHeap<E> implements Heap<E>, BinaryTreeInfo {
    private E[] elements;
    private int size;
    private Comparator comparator;
    private static final int DEFAULT_CAPACITY = 10;


    public BinaryHeap() {
        this(null);
    }

    public BinaryHeap(Comparator comparator) {
        this.comparator = comparator;
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;
        siftUp(size - 1);
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E replace(E element) {
        return null;
    }

    private void siftUp(int index) { // 从这个元素开始上滤
        E element = elements[index];
        while (index > 0) {
            int parentIndex = (index - 1) >> 1;  //完全二叉树性质 float((n-1)/2)
            E parent = elements[parentIndex];
            // 小于父节点 就不执行了
            if (compare(element, parent) <= 0) {            // 大堆
                // if (compare(parent, element) <= 0) {     // 小堆
                break;
            }
            elements[index] = parent;
            index = parentIndex;
        }
        elements[index] = element;
    }

    private int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2) : ((Comparable) e1).compareTo(e2);
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) {
            return;
        }
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println("扩容为" + newCapacity);
    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        Integer index = (Integer) node;
        index = (index << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        Integer index = (Integer) node;
        index = (index << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        Integer index = (Integer) node;
        return elements[index];
    }

    @Override
    public String toString() {
        for (int i = 0; i < size; i++) {
            System.out.println(elements[i]);
        }
        return "";
    }
}
