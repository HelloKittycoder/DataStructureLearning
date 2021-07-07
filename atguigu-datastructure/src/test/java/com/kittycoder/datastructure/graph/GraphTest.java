package com.kittycoder.datastructure.graph;

import org.junit.Test;
/**
 * Created by shucheng on 2021/7/6 22:31
 */
public class GraphTest {

    private Graph generateGraph() {
        String[] vertexArr = {"A", "B", "C", "D", "E"};

        // 创建图对象
        Graph graph = new Graph(vertexArr.length);
        // 循环添加顶点
        for (String vertex : vertexArr) {
            graph.insertVertex(vertex);
        }

        // 添加边
        graph.insertEdge(0, 1, 1); // A-B
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        return graph;
    }

    @Test
    public void testDfs() {
        Graph graph = generateGraph();
        graph.showGraph();
        System.out.println("深度优先");
        graph.dfs(); // A->B->C->D->E
    }

    @Test
    public void testBfs() {
        Graph graph = generateGraph();
        graph.showGraph();
        System.out.println("广度优先");
        graph.bfs(); // A->B->C->D->E
    }

    @Test
    public void testDiffDfsAndBfs() {
        String[] vertexArr = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Graph graph = new Graph(vertexArr.length);
        // 循环添加顶点
        for(String vertex: vertexArr) {
            graph.insertVertex(vertex);
        }

        // 添加边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        graph.showGraph();

        System.out.println("深度优先");
        graph.dfs(); // 1->2->4->8->5->3->6->7

        System.out.println("广度优先");
        graph.bfs(); // 1->2->3->4->5->6->7->8
    }
}