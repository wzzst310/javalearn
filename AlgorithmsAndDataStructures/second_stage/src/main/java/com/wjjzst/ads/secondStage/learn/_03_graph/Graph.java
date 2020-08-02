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

    // 广度优先搜索  Breadth First Search, BFS 宽度优先搜索 横向优先搜索 类似层序遍历
    void bfs(V begin);

    // 深度优先搜索 Depth First Search, SFS  类似前序遍历
    void dfs(V begin);

}
