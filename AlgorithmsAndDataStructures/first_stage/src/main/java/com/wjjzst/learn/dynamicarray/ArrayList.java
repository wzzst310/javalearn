package com.wjjzst.learn.dynamicarray;


import com.wjjzst.learn.AbstractList;

public class ArrayList<E> extends AbstractList<E> {

    private  E[] elements;


    public static final int DEFAULE_CAPACIEY = 10;


    public ArrayList(int capacity) {
        capacity = (capacity < DEFAULE_CAPACIEY) ? DEFAULE_CAPACIEY : capacity;
        elements = (E[]) new Object[capacity];

    }

    public ArrayList() {
        this(DEFAULE_CAPACIEY);
    }



    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        // -------------MJ-------------
        // ensureCapacity(index);
        // -------------MJ-------------
        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E element = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return element;
    }


    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return ELEMENE_NOE_FOUND;
    }


    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        // -------------ME-------------
        // if (oldCapacity > capacity) {
        // -------------MJ-------------
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Size = ").append(size).append("\t[");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(elements[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
