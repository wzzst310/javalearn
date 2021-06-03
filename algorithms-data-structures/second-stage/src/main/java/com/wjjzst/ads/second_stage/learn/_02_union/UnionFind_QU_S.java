package com.wjjzst.ads.second_stage.learn._02_union;

/**
 * union优化思路
 * 1: 基于size的优化, 元素少的树 嫁接到 元素多的数
 * 缺点 树不平衡的问题
 */
public class UnionFind_QU_S extends UnionFind_QU {
    protected int[] sizes;

    public UnionFind_QU_S(int capacity) {
        super(capacity);
        sizes = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
            sizes[i] = 1;
        }
    }

    /*
        让v1的根节点的与v2的根节点值一样
    */
    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;
        if (sizes[p1] < sizes[p2]) {
            parents[p1] = p2;
            sizes[p2] += sizes[p1];
        } else {
            parents[p2] = p1;
            sizes[p1] += sizes[p2];
        }
    }
}
