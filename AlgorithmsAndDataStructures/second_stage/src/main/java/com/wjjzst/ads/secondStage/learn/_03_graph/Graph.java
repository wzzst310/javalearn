package com.wjjzst.ads.secondStage.learn._03_graph;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @Author: Wjj
 * @Date: 2020/7/30 10:47 下午
 * @desc:
 */
public abstract class Graph<V, E> {
    protected WeightManager<E> weightManager;

    public Graph() {
    }

    public Graph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
    }

    public abstract int edgesSize();

    public abstract int vertices();

    public abstract void addVertex(V v);

    public abstract void addEdge(V from, V to);

    public abstract void addEdge(V from, V to, E weight);

    public abstract void removeVertex(V v);

    public abstract void removeEdge(V from, V to);

    // 广度优先搜索  Breadth First Search, BFS 宽度优先搜索 横向优先搜索 类似层序遍历
    public abstract void bfs(V begin, VertexVisitor<V> visitor);

    // 深度优先搜索 Depth First Search, SFS  类似前序遍历
    public abstract void dfs(V begin, VertexVisitor<V> visitor);

    // 拓扑排序 有向无环图 一直找入度为0的
    public abstract List<V> topologicalSort();

    public abstract Set<EdgeInfo<V, E>> mst();

    public interface WeightManager<E> {
        int compare(E w1, E w2);

        Double add(Double w1, Double w2);
    }

    public interface VertexVisitor<V> {
        boolean visit(V v);
    }

    @Data
    public static class EdgeInfo<V, E> {
        private V from;
        private V to;
        private E weight;

        public EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

}
