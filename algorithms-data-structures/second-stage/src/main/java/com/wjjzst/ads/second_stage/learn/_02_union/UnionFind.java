package com.wjjzst.ads.second_stage.learn._02_union;

public abstract class UnionFind {
    protected int[] parents;  // 数组的索引存的是真实的值 而 数组的值反而是其他

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
    public abstract void union(int v1, int v2);

    /**
     * 查找v所属集合(根节点)
     *
     * @param v
     * @return
     */
    public abstract int find(int v);

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

    protected void rangeCheck(int v) {
        if (v < 0 || v >= parents.length) {
            throw new IllegalArgumentException("v is out of bounds");
        }
    }

}
