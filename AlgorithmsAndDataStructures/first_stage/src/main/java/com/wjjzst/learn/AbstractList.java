package com.wjjzst.learn;

public abstract class AbstractList<E> implements List<E> {

    protected int size;

    @Override
    public void add(E element) {
        add(size, element);
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
    public boolean contains(E element) {
        return indexOf(element) != ELEMENE_NOE_FOUND;
    }

    @Override
    public void remove(E element) {
        remove(indexOf(element));
    }

    public void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBound(index);
        }
    }


    public boolean rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBound(index);
        }
        return true;
    }

    public void outOfBound(int index) {
        throw new ArrayIndexOutOfBoundsException("Size: " + size + " Index: " + index);
    }

}
