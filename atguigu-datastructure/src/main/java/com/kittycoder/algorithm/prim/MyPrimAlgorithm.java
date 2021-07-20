package com.kittycoder.algorithm.prim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Prim算法解决修路问题（结合课程思路自己写的）
 * Created by shucheng on 2021/7/20 9:29
 */
public class MyPrimAlgorithm {
    public static void main(String[] args) {
        String[] vertexArr = {"A", "B", "C", "D", "E", "F", "G"};
        MyMGraph mGraph = new MyMGraph(vertexArr.length);
        // 插入顶点
        for (String vertex : vertexArr) {
            mGraph.insertVertex(vertex);
        }

        // 插入边
        mGraph.insertEdge(0, 1, 5);
        mGraph.insertEdge(0, 2, 7);
        mGraph.insertEdge(0, 6, 2);
        mGraph.insertEdge(1, 3, 9);
        mGraph.insertEdge(1, 6, 3);
        mGraph.insertEdge(2, 4, 8);
        mGraph.insertEdge(3, 5, 4);
        mGraph.insertEdge(4, 5, 5);
        mGraph.insertEdge(4, 6, 4);
        mGraph.insertEdge(5, 6, 6);

        mGraph.showGraph();

        mGraph.calculateMST();
        System.out.println("最小生成树为：" + mGraph.getMstEdgeList());
    }

    static class MyMGraph {
        // 存储顶点集合
        private List<String> vertexList;
        // 存储边对应的邻接矩阵（里面放的是两点的距离）
        private int[][] edges;
        // 表示边的数目
        private int numOfEdge;

        // 记录访问过的顶点的索引
        private List<Integer> visitedVertexIndex;
        // 记录最小生成树的所有边的集合
        private List<String> mstEdgeList;

        /**
         * 初始化MGraph
         * @param n 顶点数量
         */
        public MyMGraph(int n) {
            vertexList = new ArrayList<>();
            visitedVertexIndex = new ArrayList<>();
            mstEdgeList = new ArrayList<>();
            edges = new int[n][n];
            numOfEdge = 0;
        }

        // 计算最小生成树
        public void calculateMST() {
            visitedVertexIndex.add(0);

            while (visitedVertexIndex.size() < vertexList.size()) {
                int oneVertex = 0; // 已知的顶点
                int anotherVertex = 0;
                int minDistance = 100;
                for (int i : visitedVertexIndex) {
                    // 寻找能够和已知顶点构成最短边的顶点
                    for (int j = 0; j < edges.length; j++) {
                        if (notInList(j, visitedVertexIndex) && edges[i][j] != 0 && edges[i][j] < minDistance) {
                            minDistance = edges[i][j];
                            oneVertex = i;
                            anotherVertex = j;
                        }
                    }
                }
                // 将找到的顶点加到visitedIndex中，下一轮找顶点时，会从visitedIndex以外的顶点来找另一个顶点构成最短边
                visitedVertexIndex.add(anotherVertex);
                mstEdgeList.add(vertexList.get(oneVertex) + "->" + vertexList.get(anotherVertex));
            }
        }

        /**
         * 判断一个数是否不在另一个list中，如果不在，则返回true
         * @param a
         * @param list
         * @return
         */
        public boolean notInList(int a, List<Integer> list) {
            boolean flag = true;
            for (int i : list) {
                if (a == i) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }

        // 插入节点
        public void insertVertex(String vertex) {
            vertexList.add(vertex);
        }

        // 插入边
        public void insertEdge(int v1, int v2, int weight) {
            edges[v1][v2] = weight;
            edges[v2][v1] = weight;
            numOfEdge++;
        }

        public void showGraph() {
            for (int[] link : edges) {
                System.out.println(Arrays.toString(link));
            }
        }

        public List<String> getMstEdgeList() {
            return mstEdgeList;
        }
    }
}

