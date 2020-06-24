package com.wjjzst.ads.secondStage.learn._02_union;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: Wjj
 * @Date: 2020/6/24 2:55 下午
 * @desc:
 */
public class GenericUnionFind<V> {
    private Map<V, Node<V>> nodes = new HashMap<>();


    public void makeSet(V v) {
        if (nodes.containsKey(v)) {
            return;
        }
        nodes.put(v, new Node<>(v));
    }

    /**
     * 合并v1、v2所属集合
     *
     * @param v1
     * @param v2
     * @return
     */
    public void union(V v1, V v2) {
        Node<V> p1 = findNode(v1);
        Node<V> p2 = findNode(v2);
        if (p1 == null || p2 == null) {
            return;
        }
        if (Objects.equals(p1.value, p2.value)) {
            return;
        }
        // 树矮  的挂到 树高的下面
        if (p1.rank < p2.rank) {
            p1.parent = p2;
        } else if (p1.rank > p2.rank) {
            p2.parent = p1;
        } else {
            p1.parent = p2;
            p2.rank++;
        }
    }


    public V find(V v) {
        Node<V> node = findNode(v);
        return node == null ? null : node.value;
    }

    /**
     * 查找v所属集合(根节点)
     *
     * @param v
     * @return
     */
    public Node<V> findNode(V v) {
        Node<V> node = nodes.get(v);
        if (node == null) {
            return null;
        }
        while (!Objects.equals(node.value, node.parent.value)) {
            node.parent = node.parent.parent;
            node = node.parent;
        }
        return node;
    }

    /**
     * 检查v1、v2是否属于同一个集合
     *
     * @param v1
     * @param v2
     * @return
     */
    public boolean isSame(V v1, V v2) {
        return Objects.equals(find(v1), find(v2));
    }

    private static class Node<V> {
        public V value;
        public Node<V> parent = this;
        public int rank = 1;

        public Node(V value) {
            this.value = value;
        }
    }
}
