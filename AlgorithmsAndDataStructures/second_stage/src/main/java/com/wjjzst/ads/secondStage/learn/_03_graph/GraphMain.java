package com.wjjzst.ads.secondStage.learn._03_graph;

/**
 * @Author: Wjj
 * @Date: 2020/8/2 9:39 下午
 * @desc:
 */
public class GraphMain {
    public static void main(String[] args) {
        // testBfs();
        testDfs();
    }

    private static void test1() {
        ListGraph<String, Integer> graph = new ListGraph<>();
        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V3", "V4", 1);
        graph.addEdge("V0", "V4", 6);

        // graph.removeEdge("V0", "V4");
        graph.removeVertex("V0");

        graph.print();
    }

    private static void testBfs() {
        // Graph<Object, Double> graph = undirectedGraph(Data.BFS_01);
        // graph.bfs("A");
        Graph<Object, Double> graph = directedGraph(Data.BFS_02);
        graph.bfs(0);
    }
    private static void testDfs() {
        // Graph<Object, Double> graph = undirectedGraph(Data.DFS_01);
        // graph.dfs(0);
        Graph<Object, Double> graph = directedGraph(Data.DFS_02);
        graph.dfs("d");
    }

    /**
     * 有向图
     */
    private static Graph<Object, Double> directedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>();
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }
        return graph;
    }

    /**
     * 无向图
     * @param data
     * @return
     */
    private static Graph<Object, Double> undirectedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>();
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }
        return graph;
    }

}
