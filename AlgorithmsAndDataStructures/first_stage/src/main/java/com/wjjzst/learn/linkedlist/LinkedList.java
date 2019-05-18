package com.wjjzst.learn.linkedlist;

import com.wjjzst.learn.AbstractList;

public class LinkedList<E> extends AbstractList<E> {

    private Node<E> first;

    private Node<E> last;


    private class Node<E> {
        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        private E element;
        private Node<E> prev;
        private Node<E> next;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            if (prev != null) {
                sb.append(prev.element);
            } else {
                sb.append("null");
            }

            sb.append("_").append(element).append("_");

            if (next != null) {
                sb.append(next.element);
            } else {
                sb.append("null");
            }

            return sb.toString();
        }
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == size) { //往最后一个元素时
            Node<E> oldLast = last;
            last = new Node<>(element, last, null);
            if (oldLast == null) {   //链表为空时加的第一个元素
                first = last;
            } else {
                oldLast.next = last;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node node = new Node(element, prev, next);
            next.prev = node;
            if (prev == null) { //在第一个元素插入
                first.next = node;
            } else {
                prev.next = node;
            }
        }
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        if (prev == null) { //删首节点处理
            first = next;
        } else {
            prev.next = next;
        }
        if (last == null) { //删尾节点处理
            last = prev;
        } else {
            next.prev = prev;
        }
        size--;
        return node.element;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENE_NOE_FOUND;
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        if (index < size >> 1) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Size = ").append(size).append("\t[");
        Node<E> node = first;
//        while (node != null) {
//            if (!node.equals(first)) {
//                sb.append(", ");
//            }
//            sb.append(node.element);
//            node = node.next;
//        }
        for (int i = 0; i < size; i++) {
            if(i != 0){
                sb.append(", ");
            }
            sb.append(node);
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

}
