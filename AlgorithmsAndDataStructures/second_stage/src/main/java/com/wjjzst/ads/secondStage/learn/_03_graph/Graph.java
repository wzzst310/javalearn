package com.wjjzst.ads.secondStage.learn._03_graph;

/**
 * @Author: Wjj
 * @Date: 2020/7/30 10:47 下午
 * @desc:
 */
public interface Graph<V, E> {
    int edgesSize();

    int vertices();

    void addVertex(V v);

    void addEdge(V from, V to);

    void addEdge(V from, V to, E weight);

    void removeVertex(V v);

    void removeEdge(V from, V to);

}
