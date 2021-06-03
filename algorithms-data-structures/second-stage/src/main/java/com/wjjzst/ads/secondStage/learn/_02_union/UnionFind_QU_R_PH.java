package com.wjjzst.ads.secondstage.learn._02_union;

/**
 * 路径优化思路
 * 路径压缩太绝对了 为了压缩浪费太多性能了
 */
public class UnionFind_QU_R_PH extends UnionFind_QU_R {

    public UnionFind_QU_R_PH(int capacity) {
        super(capacity);
    }

    /**
     * 使路径上的每隔一个节点就指向其祖父节点
     *
     * @param v
     * @return
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return v;
    }
}
