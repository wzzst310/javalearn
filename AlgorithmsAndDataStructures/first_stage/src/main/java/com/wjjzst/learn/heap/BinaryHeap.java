package com.wjjzst.learn.heap;

import com.wjjzst.learn.common.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.Objects;

/**
 * @Author: Wjj
 * @Date: 2019/5/27 23:55
 * @desc:
 */
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {

    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;


    public BinaryHeap() {
        this(null, null);
    }

    public BinaryHeap(Comparator comparator) {
        super(comparator);
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap(E[] elements, Comparator comparator) {
        super(comparator);
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            size = elements.length;
            int capacity = Math.max(DEFAULT_CAPACITY, elements.length);
            this.elements = (E[]) new Objects[capacity];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
            this.elements = elements;
        }
    }

    public BinaryHeap(E[] elements) {
        this(elements, null);
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
        emptyCheck();
        int lastIndex = --size;
        E root = elements[0];
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        siftDown(0);

        return root;
    }

    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    /**
     * 批量建堆
     */
    private void heapify() {
        // 自上而下的上滤  每次向最后插入元素上滤
        /*for (int i = 1; i < size; i++) {
            siftUp(i);
        }*/
        // 自下而上的下滤  左右子节点都建好堆后合并
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftDown(int index) {  //下滤
        E element = elements[index];
        // 小于第一个叶子的索引 (即非叶子节点的数量)
        // 必须保证index位置不是叶子节点
        int half = size >> 1;
        while (index < half) {
            // index 的节点有2中情况
            // 1. 只有左子节点
            // 2. 同时有左右子节点
            // 默认跟左子节点比较
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            int rightIndex = childIndex + 1;
            // 选出左右子节点最大的那个
            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                child = elements[childIndex = rightIndex];
            }
            if (compare(element, child) >= 0) {
                break;
            }

            // 讲子节点存放到index位置
            elements[index] = child;
            // 重新设置index
            index = childIndex;
        }
        elements[index] = element;
    }

    private void siftUp(int index) { // 从这个元素开始上滤
        E element = elements[index];
        while (index > 0) {
            int parentIndex = (index - 1) >> 1;  //完全二叉树性质 float((n-1)/2)
            E parent = elements[parentIndex];
            // 小于父节点 就不执行了
            if (compare(element, parent) <= 0) {            // 大堆
                // if (compare(parent, element) <= 0) {     // 小堆 重写compare方法可以调整
                break;
            }
            elements[index] = parent;
            index = parentIndex;
        }
        elements[index] = element;
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
        Integer index = ((int) node << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        Integer index = ((int) node << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int) node];
    }

    @Override
    public String toString() {
        for (int i = 0; i < size; i++) {
            System.out.println(elements[i]);
        }
        return "";
    }
}
