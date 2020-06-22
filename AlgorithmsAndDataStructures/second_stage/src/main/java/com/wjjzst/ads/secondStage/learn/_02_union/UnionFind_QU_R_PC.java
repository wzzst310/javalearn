package com.wjjzst.ads.secondStage.learn._02_union;

/**
 * 路径优化思路
 * 1: 路径压缩
 */
public class UnionFind_QU_R_PC extends UnionFind_QU_R {

    public UnionFind_QU_R_PC(int capacity) {
        super(capacity);
    }

    /**
     * 使路径上的所有节点都指向根节点,从而降低树的高度
     * @param v
     * @return
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        if (v != parents[v]) {
            parents[v] = find(parents[v]);
        }
        return parents[v];
    }
}
