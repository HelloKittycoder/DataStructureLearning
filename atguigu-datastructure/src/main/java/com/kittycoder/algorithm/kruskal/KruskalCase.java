package com.kittycoder.algorithm.kruskal;

import java.util.Arrays;

/**
 * Kruskal算法解决公交问题（课程里的实现）
 * 1.对所有边排序；
 * 2.从最小的边开始取，依次加到一个集合中
 * 加的过程中需要判断是否与已有边构成回路，若构成回路，则取次小边
 *
 * Created by shucheng on 2021/7/24 21:17
 */
public class KruskalCase {
    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};

        KGraph kGraph = new KGraph(vertexes, weight);
        // kGraph.showGraph();
        kGraph.kruskal();
    }

    static class KGraph {
        int vertexNum; //表示图的节点个数
        int edgeNum; // 表示有几条边
        char[] vertexes; //存放节点
        int[][] weight; //存放边，就是我们的邻接矩阵

        // 初始化图
        public KGraph(char[] vertexes, int[][] weight) {
            // 初始化顶点的个数
            this.vertexNum = vertexes.length;

            // 初始化顶点和边
            this.vertexes = new char[vertexNum];
            this.weight = new int[vertexNum][vertexNum];
            for (int i = 0; i < vertexNum; i++) {
                this.vertexes[i] = vertexes[i];
                for (int j = 0; j < vertexNum; j++) {
                    this.weight[i][j] = weight[i][j];
                }
            }

            // 统计边的条数
            for (int i = 0; i < vertexNum; i++) {
                for (int j = i + 1; j < vertexNum; j++) {
                    if (weight[i][j] != INF) {
                        edgeNum++;
                    }
                }
            }
        }

        // 显示图的邻接矩阵
        public void showGraph() {
            /*for (int[] link : this.weight) {
                System.out.println(Arrays.toString(link));
            }*/
            System.out.println("邻接矩阵为：\n");
            for (int i = 0; i < vertexNum; i++) {
                for (int j = 0; j < vertexNum; j++) {
                    System.out.printf("%12d", weight[i][j]);
                }
                System.out.println();
            }
        }

        public void kruskal() {
            int index = 0;
            // 用于保存“已有最小生成树”中的每个顶点在最小生成树中的终点
            int[] ends = new int[edgeNum];
            // 创建结果数组，保存最后的最小生成树
            Edge[] ret = new Edge[edgeNum];

            // 获取图中所有边的集合，共有12条边
            Edge[] edges = getEdges();

            System.out.println("图的边的集合=" + Arrays.toString(edges) + "，共" + edges.length + "条边");

            // 按照边的权值大小进行排序（从小到大）
            sortEdges(edges);
            /* 经过测试，发现打乱顺序，对判断是否形成回路没影响
            Edge edge0 = edges[0];
            edge0.start = 5;
            edge0.end = 4;*/

            // 遍历edges数组，将边添加到最小生成树时，判断准备加入的边是否形成了回路，如果没有，就加入ret，否则不能加入
            for (int i = 0; i < edgeNum; i++) {
                // 获取到第i条边的第1个顶点
                int p1 = edges[i].start;
                // 获取到第i条边的第2个顶点
                int p2 = edges[i].end;

                // 获取p1这个顶点在已有最小生成树中的终点
                int m = getEnd(ends, p1);
                // 获取p2这个顶点在已有最小生成树中的终点
                int n = getEnd(ends, p2);

                // 是否构成回路
                if (m != n) { // 没有构成回路
                    ends[m] = n; // 设置m在“已有最小生成树”中的终点
                    // System.out.println(edges[i] + "被加到ret中");
                    ret[index++] = edges[i]; // 有一条边加到ret数组
                }/* else {
                    System.out.println(edges[i] + "与" + Arrays.toString(ret) + "中的边构成回路");
                }*/
            }
            // 统计并打印最小生成树，输出ret
            System.out.println("最小生成树为：");
            for (int i = 0; i < index; i++) {
                System.out.println(ret[i]);
            }
        }

        /**
         * 获取图的所有边
         * @return
         */
        public Edge[] getEdges() {
            int index = 0;
            Edge[] edges = new Edge[edgeNum];
            for (int i = 0; i < vertexNum; i++) {
                for (int j = i + 1; j < vertexNum; j++) {
                    if (weight[i][j] != INF) {
                        edges[index++] = new Edge(vertexes, i, j, weight[i][j]);
                    }
                }
            }
            return edges;
        }

        /**
         * 对边按照weight升序排列
         */
        public void sortEdges(Edge[] edges) {
            // 使用冒泡排序
            for (int i = 0; i < edges.length - 1; i++) {
                for (int j = 0; j < edges.length - 1 - i; j++) {
                    if (edges[j].weight > edges[j + 1].weight) { // 交换
                        Edge tmp = edges[j];
                        edges[j] = edges[j + 1];
                        edges[j + 1] = tmp;
                    }
                }
            }

            // 也可以用jdk自带的数组排序
            /*Arrays.sort(edges, new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    // 按照weight升序排列
                    if (o1.weight < o2.weight) {
                        return -1;
                    } else if (o1.weight == o2.weight) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });*/
        }

        /**
         * 获取下标为i的顶点的终点，用于判断两个顶点的终点是否相同 ☆☆☆（这个部分非常关键，需要好好理解下）
         * https://stackoverflow.com/questions/29998332/detect-cycle-in-a-graph-using-kruskals-algorithm
         * lsc注：这部分还没理解为什么是这样？看网上都提到要用并查集，后续再看看
         *
         * @param ends 该数组记录了各个顶点对应的终点是哪个，ends数组是在遍历过程中逐步形成的
         * @param i
         * @return
         */
        private int getEnd(int[] ends, int i) {
            while (ends[i] != 0) {
                i = ends[i];
            }
            return i;
        }
    }

    static class Edge {
        private char[] vertexes; //存放节点
        int start;
        int end;
        int weight;

        public Edge(char[] vertexes, int start, int end, int weight) {
            this.vertexes = vertexes;
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{"+ vertexes[start] +
                    "-" + vertexes[end] +
                    "," + weight +
                    '}';
        }
    }
}
