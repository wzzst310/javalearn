package com.wjjzst.ads.second_stage.learn._02_union;

/**
 * union优化思路
 * 2: 基于rank的优化, 矮的树 嫁接到 高的树
 */
public class UnionFind_QU_R extends UnionFind_QU {
    protected int[] ranks;

    public UnionFind_QU_R(int capacity) {
        super(capacity);
        ranks = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
            ranks[i] = 1;
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
        // 树矮  的挂到 树高的下面
        if (ranks[p1] < ranks[p2]) {
            parents[p1] = p2;
        } else if (ranks[p1] > ranks[p2]) {
            parents[p2] = p1;
        } else {
            parents[p1] = p2;
            ranks[p2] ++;
        }
    }
}
