package com.kittycoder.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shucheng on 2021/7/5 20:53
 */
public class Graph {

    private List<String> vertexList; // 存储顶点集合
    private int[][] edges; // 存储图对应的邻接矩阵
    private int numOfEdge; // 表示边的数目

    public static void main(String[] args) {
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

        graph.showGraph();
    }

    public Graph(int n) {
        // 初始化顶点集合和邻接矩阵
        vertexList = new ArrayList<>();
        edges = new int[n][n];
        numOfEdge = 0;
    }

    // 图中常用的方法
    // 返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    // 显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.err.println(Arrays.toString(link));
        }
    }

    // 得到边的数目
    public int getNumOfEdge() {
        return numOfEdge;
    }

    // 返回vertexList中下标i对应的节点 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    // 返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1 表示第一个顶点在vertexList的下标
     * @param v2 表示第二个顶点在vertexList的下标
     * @param weight 权重，因为这里是无向图，只取0（无连接关系）或1（有连接关系）
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdge++;
    }
}
