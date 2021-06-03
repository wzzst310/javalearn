package com.wjjzst.ads.secondstage.learn._03_graph;

import java.util.List;
import java.util.Set;
import com.wjjzst.ads.secondstage.learn._03_graph.Graph.EdgeInfo;

/**
 * @Author: Wjj
 * @Date: 2020/8/2 9:39 下午
 * @desc:
 */
public class GraphMain {
    private static Graph.WeightManager<Double> weightManager = new Graph.WeightManager<Double>() {
        @Override
        public int compare(Double w1, Double w2) {
            // return w1.compareTo(w2); //大顶堆
            return w2.compareTo(w1); // 小顶堆
        }

        @Override
        public Double add(Double w1, Double w2) {
            return w1 + w2;
        }
    };

    public static void main(String[] args) {
        // test1();
        // testBfs();
        // testDfs();
        // testTopologicalSort();
        testMst();
    }

    private static void test1() {
//        ListGraph<String, Integer> graph = new ListGraph<>();
//        graph.addEdge("V1", "V0", 9);
//        graph.addEdge("V1", "V2", 3);
//        graph.addEdge("V2", "V0", 2);
//        graph.addEdge("V2", "V3", 5);
//        graph.addEdge("V3", "V4", 1);
//        graph.addEdge("V0", "V4", 6);
//
//        graph.removeEdge("V0", "V4");
        // graph.removeVertex("V0");

//        graph.print();
    }

    private static void testMst() {
        Graph<Object, Double> graph = undirectedGraph(Data.MST_02);
        Set<EdgeInfo<Object, Double>> edgeInfos = graph.mst();
        for (EdgeInfo<Object, Double> edgeInfo : edgeInfos) {
            System.out.println(edgeInfo);
        }
    }

    private static void testTopologicalSort() {
        Graph<Object, Double> graph = directedGraph(Data.TOPO);
        List<Object> list = graph.topologicalSort();
        System.out.println(list);
    }

    private static void testBfs() {
        /*Graph<Object, Double> graph = undirectedGraph(Data.BFS_01);
        graph.bfs("A", v -> {
            System.out.println(v);
            return false;
        });*/
        Graph<Object, Double> graph = directedGraph(Data.BFS_02);
        graph.bfs(0, v -> {
            System.out.println(v);
            // return v.equals(2);
            return false;
        });
    }

    private static void testDfs() {
        /*Graph<Object, Double> graph = undirectedGraph(Data.DFS_01);
        graph.dfs(0, v -> {
            System.out.println(v);
            return false;
        });*/
        Graph<Object, Double> graph = directedGraph(Data.DFS_02);
        graph.dfs("d", v -> {
            System.out.println(v);
            return v.equals("e");
        });
    }

    /**
     * 有向图
     */
    private static Graph<Object, Double> directedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
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
     *
     * @param data
     * @return
     */
    private static Graph<Object, Double> undirectedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(weightManager);
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
