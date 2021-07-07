package com.kittycoder.datastructure.graph;

import java.util.*;

/**
 * 深度优先搜索 DFS（Depth First Search）
 * 广度优先搜索 BFS（Breadth First Search）
 * Created by shucheng on 2021/7/5 20:53
 */
public class Graph {

    private List<String> vertexList; // 存储顶点集合
    private int[][] edges; // 存储图对应的邻接矩阵
    private int numOfEdge; // 表示边的数目
    private boolean[] isVisited; // 记录已访问过的节点

    public Graph(int n) {
        // 初始化顶点集合和邻接矩阵
        vertexList = new ArrayList<>();
        edges = new int[n][n];
        numOfEdge = 0;
    }

    /**
     * 获取某个节点的第一个邻接节点的下标w
     * @param index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < getNumOfVertex(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // 根据前一个邻接节点的下标获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < getNumOfVertex(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // 深度优先遍历算法（优先访问子节点）
    private void dfs(boolean[] isVisited, int i) {
        System.out.print(getValueByIndex(i) + "->");
        // 标记节点v为已访问
        isVisited[i] = true;
        // 查找节点v的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1) {
            // 若w未被访问，对w进行深度优先遍历递归
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 如果w已被访问，则查找节点v的邻接节点w的下一个邻接节点
            w = getNextNeighbor(i, w);
        }
        // 如果w不存在，则从v的下一个节点继续（这个在下一次for循环进来，从dfs()方法过来的）
    }

    // 对dfs进行重载，遍历所有的节点，进行dfs操作
    public void dfs() {
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    // 广度优先遍历算法（优先访问邻接节点）
    private void bfs(boolean[] isVisited, int i) {
        int u; // 表示队列的头节点对应下标
        int w; // 邻接节点w
        // 队列，记录节点访问的顺序
        Queue<Integer> queue = new LinkedList<>();
        // 访问节点，输出节点信息
        System.out.print(getValueByIndex(i) + "->");
        // 标记节点v为已访问
        isVisited[i] = true;
        // 入队列（offer实际调的就是LinkedList#linkLast方法，和addLast类似）
        queue.offer(i);
        while (!queue.isEmpty()) {
            // 出队（poll实际调的就是LinkedList#unlinkFirst方法，和removeFirst类似）
            u = queue.poll();
            // 得到第一个邻接节点的下标w
            w = getFirstNeighbor(u);
            // 若节点u的邻接节点w存在，则循环执行以下步骤
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    // 标记节点w为已访问
                    isVisited[w] = true;
                    // 入队
                    queue.offer(w);
                }
                // 寻找u中位于w之后的邻接点
                w = getNextNeighbor(u, w);
            }
        }
    }

    // 遍历所有的节点，进行bfs操作
    public void bfs() {
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i <getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
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
