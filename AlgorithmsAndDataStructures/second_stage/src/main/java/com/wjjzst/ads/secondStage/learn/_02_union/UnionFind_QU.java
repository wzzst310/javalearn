package com.wjjzst.ads.secondStage.learn._02_union;

/**
 * union优化思路
 * 1: 基于size的优化, 元素少的树 嫁接到 元素多的数
 * 2: 基于rank的优化, 矮的树 嫁接到 高的树
 */
public class UnionFind_QU extends UnionFind {
    public UnionFind_QU(int capacity) {
        super(capacity);
    }

    /*
        让v1的根节点的与v2的根节点值一样
    */
    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;
        parents[p1] = p2;
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            v = parents[v];
        }
        return v;
    }
}
