package com.wjjzst.ads.secondStage.learn._03_graph;

import java.util.List;
import java.util.Set;

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
    void bfs(V begin, VertexVisitor<V> visitor);

    // 深度优先搜索 Depth First Search, SFS  类似前序遍历
    void dfs(V begin, VertexVisitor<V> visitor);

    // 拓扑排序 有向无环图 一直找入度为0的
    List<V> topologicalSort();

    Set<EdgeInfo<V, E>> mst();

    interface VertexVisitor<V> {
        boolean visit(V v);
    }

    class EdgeInfo<V, E> {
        V from;
        V to;
        E weight;

        public EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

}
