package com.wjjzst.learn.linkedlist.circle;

import com.wjjzst.learn.AbstractList;

public class CircleLinkedList<E> extends AbstractList<E> {

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
            last = new Node<>(element, last, first);
            if (oldLast == null) {   //链表为空时加的第一个元素
                first = last;
                //只有一个自己指向自己
                first.prev = first;
                first.next = first;
            } else {
                oldLast.next = last;
                first.prev = last;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node node = new Node(element, prev, next);
            next.prev = node;
            prev.next = node;
            if (next == first) {//在第一个元素插入
                first = node;
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
        if (size == 1) {
            first = last = null;
        } else {
            prev.next = next;
            next.prev = prev;
        }
        if (node == first) { //index ==0
            first = next;
        }
        if (node == last) { //index ==size -1
            last = prev;
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
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(node);
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

}
