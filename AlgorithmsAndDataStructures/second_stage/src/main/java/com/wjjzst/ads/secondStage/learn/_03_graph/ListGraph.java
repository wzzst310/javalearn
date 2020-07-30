package com.wjjzst.ads.secondStage.learn._03_graph;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Wjj
 * @Date: 2020/7/30 10:49 下午
 * @desc:
 */
public class ListGraph<V, E> implements Graph<V, E> {
    private static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> inEdges = new HashSet<>();
        Set<Edge<V, E>> outEdges = new HashSet<>();

    }

    private static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;
    }

    @Override
    public int edgesSize() {
        return 0;
    }

    @Override
    public int vertices() {
        return 0;
    }

    @Override
    public void addVertex(V v) {

    }

    @Override
    public void addEdge(V from, V to) {

    }

    @Override
    public void addEdge(V from, V to, E weight) {

    }

    @Override
    public void removeVertex(V v) {

    }

    @Override
    public void removeEdge(V from, V to) {

    }
}
