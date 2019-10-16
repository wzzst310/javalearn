package com.wjjzst.ads.secondStage.learn._02_union;

public class UnionFind {
    private int[] parents;

    public UnionFind(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must be >=1");
        }
        parents = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

    }

    /**
     * 合并v1、v2所属集合
     *
     * @param v1
     * @param v2
     * @return
     */
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;
        for (int i = 0; i < parents.length; i++) {
            if (p1 == find(parents[i])) parents[i] = p2;
        }
    }

    /**
     * 查找v所属集合(根节点)
     *
     * @param v
     * @return
     */
    public int find(int v) {
        rangeCheck(v);
        return parents[v];
    }

    /**
     * 检查v1、v2是否属于同一个集合
     *
     * @param v1
     * @param v2
     * @return
     */
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    private void rangeCheck(int v) {
        if (v < 0 || v >= parents.length) {
            throw new IllegalArgumentException("v is out of bounds");
        }
    }

}
