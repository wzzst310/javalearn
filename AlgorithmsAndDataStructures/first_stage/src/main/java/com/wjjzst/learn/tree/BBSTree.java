package com.wjjzst.learn.tree;

import java.util.Comparator;

/**
 * @Author: Wjj
 * @Date: 2019/5/10 0:24
 * @desc: 平衡二叉搜索树
 */

public class BBSTree<E> extends BSTree<E> {
    public BBSTree() {
        this(null);
    }

    public BBSTree(
            Comparator<E> comparator) {
        super(comparator);
    }


    protected void rotate(
            Node<E> r, // 原来子树里面的根节点
            Node<E> a, Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f, Node<E> g) {
        // 让d成为根节点,并维护与原本根节点的父节点关系
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        // 处理a-b-c
        b.left = a;
        if (a != null) {
            a.parent = b;
        }
        b.right = c;
        if (c != null) {
            c.parent = b;
        }

        // 处理e-f-g
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        f.right = g;
        if (g != null) {
            g.parent = f;
        }

        // 处理b-d-f
        d.left = b;
        b.parent = d;
        d.right = f;
        f.parent = d;

    }

    //左旋转
    protected void rotateLeft(Node<E> grandparent) {
        Node<E> parent = grandparent.right;
        Node<E> child = parent.left;
        grandparent.right = child;
        parent.left = grandparent;
        afterRotate(grandparent, parent, child);
    }

    //右旋转
    protected void rotateRight(Node<E> grandparent) {
        Node<E> parent = grandparent.left;
        Node<E> child = parent.right;
        grandparent.left = child;
        parent.right = grandparent;
        afterRotate(grandparent, parent, child);
    }

    // 更新完之后做的事情
    protected void afterRotate(Node<E> grandparent, Node<E> parent, Node<E> child) {
        // 新子树根节点代替原子树根节点
        parent.parent = grandparent.parent;
        if (grandparent.isLeftChild()) {
            grandparent.parent.left = parent;
        } else if (grandparent.isRightChild()) {
            grandparent.parent.right = parent;
        } else {// 根节点情况
            root = parent;
        }
        // 更新child为parent
        if (child != null) {
            child.parent = grandparent;
        }
        // 更新grandparent的parent
        grandparent.parent = parent;

    }

}
