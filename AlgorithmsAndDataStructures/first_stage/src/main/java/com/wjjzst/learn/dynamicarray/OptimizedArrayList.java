package com.wjjzst.learn.dynamicarray;

import com.wjjzst.learn.AbstractList;

import java.util.ArrayList;

/**
 * @Author: Wjj
 * @Date: 2019-04-27 23:07
 */
public class OptimizedArrayList<E> extends AbstractList<E> {
    private E[] elements;
    private int front;


    public static final int DEFAULE_CAPACIEY = 10;


    public OptimizedArrayList(int capacity) {
        capacity = (capacity < DEFAULE_CAPACIEY) ? DEFAULE_CAPACIEY : capacity;
        elements = (E[]) new Object[capacity];

    }

    public OptimizedArrayList() {
        this(DEFAULE_CAPACIEY);
    }

    @Override
    public void add(int index, E element) {
        int realIndex = index(index);
        rangeCheckForAdd(realIndex);
        ensureCapacity(size + 1);
        if (index < size >> 1) {
            for (int i = 0; i < index; i++) {
                elements[index(i - 1)] = elements[index(i)];
            }
            front = index(-(index + 1));
        } else {
            for (int i = size; i > index; i--) {
                elements[index(i)] = elements[index(i - 1)];
            }
        }
        elements[realIndex] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        int realIndex = index(index);
        rangeCheck(realIndex);
        E element = elements[realIndex];
        for (int i = index; i < size - 1; i++) {
            elements[index(i)] = elements[index(i + 1)];
        }
        size--;
        return element;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        rangeCheck(index(index));
        return elements[index(index)];
    }

    @Override
    public E set(int index, E element) {
        int realIndex = index(index);
        rangeCheck(realIndex);
        E old = elements[realIndex];
        elements[realIndex] = element;
        return old;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[index(i)] == null) {
                    return index(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[index(i)])) {
                    return index(i);
                }
            }
        }
        return ELEMENE_NOE_FOUND;
    }

    private int index(int index) {
        index += front;
        // 负数就表示往左移
        if (index < 0) {  // front == 0  往左移一位 -1   就等于在最后往前挪多少位
            return index + elements.length;
        }
        return index - (index < elements.length ? 0 : elements.length);
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) {
            return;
        }
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[index(i)];
        }
        elements = newElements;
        front = 0;
        System.out.println("扩容为" + newCapacity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Size = ").append(size).append("\tfront = ").append(front).append("\t[");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(elements[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        OptimizedArrayList<Object> list = new OptimizedArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(0, 5);
        System.out.println(list);
        list.add(0, 6);
        System.out.println(list);

    }
}
