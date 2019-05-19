package com.wjjzst.learn.set;

import com.wjjzst.learn.tree.BinaryTree;
import com.wjjzst.learn.tree.RBTree;

/**
 * @Author: Wjj
 * @Date: 2019/5/19 23:29
 * @desc:
 */
public class TreeSet<E> implements Set<E> {
    RBTree tree = new RBTree();

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    @Override
    public void add(E element) {
        tree.add(element);
    }

    @Override
    public void remove(E element) {
        tree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        tree.inorder(new BinaryTree.Visitor<E>() {
            @Override
            protected boolean visit(E element) {
                return visitor.visit(element);
            }
        });
    }
}
