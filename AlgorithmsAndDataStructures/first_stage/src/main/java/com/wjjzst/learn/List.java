package com.wjjzst.learn;

public interface List<E> {
    static final int ELEMENE_NOE_FOUND = -1;

    void add(E element);

    void add(int index, E element);

    E remove(int index);

    void remove(E element);

    void clear();

    int size();

    boolean isEmpty();

    boolean contains(E element);

    E get(int index);

    E set(int index, E element);

    int indexOf(E element);


}
