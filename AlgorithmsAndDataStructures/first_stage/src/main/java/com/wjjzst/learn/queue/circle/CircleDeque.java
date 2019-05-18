package com.wjjzst.learn.queue.circle;


/**
 * 数组方式实现单向循环队列
 *
 * @Author: Wjj
 * @Date: 2019-04-26 00:05
 */
public class CircleDeque<E> {
    private int front;
    private int size;
    private E[] elements;
    public static final int DEFAULE_CAPACIEY = 10;

    /**
     * // 队头 << << << << << << << 队尾
     */
    public CircleDeque() {
        elements = (E[]) new Object[DEFAULE_CAPACIEY];
    }

    public int size() {
        return size;
    }

    public void enQueueFront(E element) {
        ensureCapacity(size + 1);
        front = index(-1);
        elements[front] = element;
        size++;
    }

    public E deQueueFront() {
        E frontElement = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return frontElement;
    }

    public void enQueueRear(E element) {
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size++;
    }

    public E deQueueRear() {
        int rearIndex = index(size - 1);
        E rear = elements[rearIndex];
        elements[rearIndex] = null;
        size--;
        return rear;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        front = 0;
        size = 0;
    }

    public E front() {
        return elements[front];
    }

    public E rear() {
        return elements[index(size - 1)];
    }

    public boolean isEmpty() {
        return size == 0;
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
}
