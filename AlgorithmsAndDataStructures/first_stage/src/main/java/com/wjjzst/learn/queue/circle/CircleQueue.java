package com.wjjzst.learn.queue.circle;


/**
 * 数组方式实现单向循环队列
 *
 * @Author: Wjj
 * @Date: 2019-04-26 00:05
 */
public class CircleQueue<E> {
    private int front;
    private int size;
    private E[] elements;
    public static final int DEFAULE_CAPACIEY = 10;

    /**
     * // 队头 << << << << << << << 队尾
     */
    public CircleQueue() {
        elements = (E[]) new Object[DEFAULE_CAPACIEY];
    }

    public int size() {
        return size;
    }

    public void enQueue(E element) {
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size++;
    }


    public E deQueue() {
        E frontElement = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return frontElement;
    }

    public E front() {
        return elements[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int index(int index) {
        index += front;
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
        front = 0;
        elements = newElements;
        System.out.println("扩容为" + newCapacity);
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        front = 0;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("capcacity=").append(elements.length)
                .append(" size=").append(size)
                .append(" front=").append(front)
                .append(", [");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }


}
