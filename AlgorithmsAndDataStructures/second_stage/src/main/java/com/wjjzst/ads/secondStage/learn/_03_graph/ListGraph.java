package com.wjjzst.ads.secondStage.learn._03_graph;

import java.util.*;

/**
 * @Author: Wjj
 * @Date: 2020/7/30 10:49 下午
 * @desc:
 */
public class ListGraph<V, E> implements Graph<V, E> {
    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public int vertices() {
        return vertices.size();
    }

    @Override
    public void addVertex(V v) {
        if (!vertices.containsKey(v)) {
            vertices.put(v, new Vertex<>(v));
        }
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;
        // 删了重新来
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);

    }

    @Override
    public void removeVertex(V v) {
        Vertex<V, E> vertex = vertices.remove(v);
        if (vertex != null) {
            Iterator<Edge<V, E>> it = vertex.outEdges.iterator();
            while (it.hasNext()) {
                Edge<V, E> edge = it.next();
                // A点 out的edge(from) B点 in的edge(to)
                edge.to.inEdges.remove(edge);
                it.remove();
                edges.remove(edge);
            }
            it = vertex.inEdges.iterator();
            while (it.hasNext()) {
                Edge<V, E> edge = it.next();
                edge.from.outEdges.remove(edge);
                it.remove();
                edges.remove(edge);
            }
        }
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) return;
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) return;
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }

    @Override
    public void bfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(beginVertex);
        visitedVertices.add(beginVertex);
        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            System.out.println(vertex.value);
            // 由demo图中可知 如果是出来一个才算遍历过  那么第一次队列queue中压入 V0,V2 此时visitedVertices中只有V1
            // 第二次V2 poll出来之时  此时visitedVertices中只有V1和V2 此时会把V0再次压人到queue中
            // visitedVertices.add(vertex);
            // 故一定要在offer进队列的时候就当成已经遍历过了
            for (Edge<V, E> edge : vertex.outEdges) {
                if (!visitedVertices.contains(edge.to)) {
                    queue.offer(edge.to);
                    visitedVertices.add(edge.to);
                }
            }
        }

    }

    @Override
    public void dfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        dfs(beginVertex, new HashSet<>());
    }

    private void dfs(Vertex<V, E> vertex, Set<Vertex<V, E>> visitedVertices) {
        System.out.println(vertex.value);
        visitedVertices.add(vertex);
        for (Edge<V, E> edge : vertex.outEdges) {
            if (!visitedVertices.contains(edge.to)) {
                dfs(edge.to, visitedVertices);
            }
        }
    }


    public void print() {
        System.out.println("vertices==================================================");
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            System.out.println(v);
            System.out.println("in---------------------");
            System.out.println(vertex.inEdges);
            System.out.println("out---------------------");
            System.out.println(vertex.outEdges);
        });
        System.out.println("\n" + "edges==================================================");
        edges.forEach(System.out::println);
    }

    private static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> inEdges = new HashSet<>();
        Set<Edge<V, E>> outEdges = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Vertex<V, E> vertex = (Vertex<V, E>) obj;

            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }

        @Override
        public String toString() {
            return value == null ? "null" : value.toString();
        }
    }

    private static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge<V, E> edge = (Edge<V, E>) o;

            if (!Objects.equals(from, edge.from)) return false;
            return Objects.equals(to, edge.to);
        }


        @Override
        public int hashCode() {
            int result = from != null ? from.hashCode() : 0;
            result = 31 * result + (to != null ? to.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Edge {from=" + from + ", to=" + to + ", weight=" + weight + '}';
        }
    }
}
