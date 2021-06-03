package com.wjjzst.ads.second_stage.learn._02_union;

public class UnionFind_QF extends UnionFind {
    public UnionFind_QF(int capacity) {
        super(capacity);
    }


    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;
        // 让和v1值一样的都与v2的根节点值一样
        for (int i = 0; i < parents.length; i++) {
            if (p1 == find(parents[i])) {
                parents[i] = p2;
            }
        }
    }

    /**
     * 查找v所属集合(根节点)
     *
     * @param v
     * @return
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        return parents[v];
    }
}
