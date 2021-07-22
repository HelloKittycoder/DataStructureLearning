package com.kittycoder.algorithm.prim;

import java.util.Arrays;

/**
 * Prim算法解决修路问题（课程里的实现，对课程里的代码稍作了调整）
 * Created by shucheng on 2021/7/22 22:06
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int[][] weight = {{10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}};
        int vertexNum = vertexes.length;

        //创建MGraph对象
        MGraph mGraph = new MGraph(vertexNum);
        // 设置节点和权值
        mGraph.setVertexesWeight(vertexes, weight);
        // 显示图的邻接矩阵
        mGraph.showGraph();
        // 测试Prim算法
        mGraph.prim(0);
    }

    static class MGraph {
        int vertexNum; //表示图的节点个数
        char[] vertexes; //存放节点
        int[][] weight; //存放边，就是我们的邻接矩阵

        public MGraph(int vertexNum) {
            this.vertexNum = vertexNum;
            vertexes = new char[vertexNum];
            weight = new int[vertexNum][vertexNum];
        }

        // 设置节点和权值
        public void setVertexesWeight(char[] vertexes, int[][] weight) {
            int i, j;
            for (i = 0; i < vertexNum; i++) {
                this.vertexes[i] = vertexes[i];
                for (j = 0; j < vertexNum; j++) {
                    this.weight[i][j] = weight[i][j];
                }
            }
        }

        // 显示图的邻接矩阵
        public void showGraph() {
            for (int[] link : this.weight) {
                System.out.println(Arrays.toString(link));
            }
        }

        /**
         * 编写prim算法，得到最小生成树
         * @param v 表示从图的第几个顶点开始生成 0->'A',1->'B',...
         */
        public void prim(int v) {
            // 标记节点是否被访问过
            int[] visited = new int[vertexNum];
            // visited[]默认元素的值都是0，表示没有访问过（java里下面这段可以不写）
            /*for (int i = 0; i < vertexNum; i++) {
                visited[i] = 0;
            }*/

            // 把传入的起始节点标记为已访问
            visited[v] = 1;
            // h1和h2 记录两个顶点的下标
            int h1 = -1, h2 = -1;
            //将 minWeight 初始成一个大数，后面在遍历过程中，会被替换
            int minWeight = 10000;
            // 因为有graph.verxes个顶点，Prim算法结束后，有 graph.verxes-1 条边
            for (int k = 1; k < vertexNum; k++) {
                // i、j循环是确定每次生成的子图（主要是确定节点j和visited中的某个节点能构成最短边）
                for (int i = 0; i < vertexNum; i++) { // i表示被访问过的节点
                    for (int j = 0; j < vertexNum; j++) { // j表示还没被访问过的节点
                        if (visited[i] == 1 && visited[j] == 0 && weight[i][j] < minWeight) {
                            // 替换minWeight（寻找已访问过的节点和未访问过的节点间权值最小的边）
                            minWeight = weight[i][j];
                            h1 = i;
                            h2 = j;
                        }
                    }
                }
                // 找到一条边权值是目前最小的
                System.out.println("边<" + vertexes[h1] + "," + vertexes[h2] + ">权值：" + minWeight);
                // 将当前这个节点标记为已访问
                visited[h2] = 1;
                // minWeight重置为 大数 10000
                minWeight = 10000;
            }
        }
    }
}

