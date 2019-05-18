package com.wjjzst.learn.tree;

import java.util.Comparator;
import java.util.List;

public class BSTree<E> extends BinaryTree<E> {
    private Comparator comparator;

    public BSTree(Comparator comparator) {
        this.comparator = comparator;
    }

    public BSTree() {

    }


    public void add(List<E> list) {
        for (E e : list) {
            add(e);
        }
    }

    public void add(E element) {
        elementNotNullCheck(element);
        // 根节点为空是
        if (root == null) {
            root = createNode(element, null);
            size++;
            afterAdd(root);//新添加节点之后处理
            return;
        }
        // 根节点不为空时候
        Node<E> parent = root; //找到父节点
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return;
            }
        }
        Node<E> newNode = createNode(element, parent);
        if (cmp < 0) {
            parent.left = newNode;
        } else if (cmp > 0) {
            parent.right = newNode;
        }
        size++;
        afterAdd(newNode); //新添加节点之后处理
    }

    protected void afterAdd(Node<E> node) {

    }


    public void remove(E element) {
        remove(node(element));
    }

    public void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        size--;
        // 度为2的节点
        if (node.hasTwoChildren()) {
            // 找到后继节点
            Node<E> successor = successor(node);
            // 后继节点的值覆盖原本度为2的节点
            node.element = successor.element;
            // 删除后继节点
            node = successor;
        }
        // 删除度为0,1的节点的子节点
        Node<E> replacement = node.left != null ? node.left : node.right;
        // 度为1的节点替代节点必定存在
        if (replacement != null) {
            // 取代节点的父节点指向原本节点的父节点
            replacement.parent = node.parent; // 根节点parent==null
            // 取决于当前节点是父节点的左右  取代节点才能插入上去
            // 度为1的节点是跟节点
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
            afterRemove(replacement);
        } else if (node.parent == null) {// 度为0的节点  根节点
            root = null;
            afterRemove(node);
        } else { // 度为0的节点 其他节点
            if (node == node.parent.left) {
                // node.parent.left = replacement; //此时replacement = null
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            afterRemove(node);
        }
        // 虽然没有节点指向node 但是node仍然指向他的父节点 node.parent = replacement.parent
        // node删掉了 node.parent高度就会改变就需要平衡
        // afterRemove(node); //删了replacement 改变的是node 需要调整的也是node
    }

    protected void afterRemove(Node<E> node) {

    }

    public Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) {
                return node;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        // 树里面没有找到要删除的节点
        return null;
    }

    public boolean contains(E element) {
        return node(element) != null;
    }


    private int compare(E e1, E e2) {
        // 如果有传进来有比较
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb, "");
        return sb.toString();
    }

    private void toString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) {
            return;
        }
        sb.append(prefix).append("[").append(node.element).append("]").append("\n");
        if (node.left != null) {
            toString(node.left, sb, prefix + "[L]");
        }
        if (node.right != null) {
            toString(node.right, sb, prefix + "[R]");
        }

    }
}
