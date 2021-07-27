package com.kittycoder.algorithm.dijkstra;

import java.util.Arrays;

/**
 * Dijkstra算法解决最短路径问题（课程里的实现）
 * 老师讲的不够清晰，我另外看了 运筹学（第三版）清华大学出版社 P265
 * 讲的Dijkstra算法，手算了几遍，大致理解了整个过程
 * Created by shucheng on 2021/7/27 0:11
 */
public class DijkstraAlgorithm {
    public static final int N = 65535; // 表示两点不可连接

    public static void main(String[] args) {
        char[] vertexes = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int[][] weight = {{N,5,7,N,N,N,2},
                {5,N,N,9,N,N,3},
                {7,N,N,N,8,N,N},
                {N,9,N,N,N,4,N},
                {N,N,8,N,N,5,4},
                {N,N,N,4,5,N,6},
                {2,3,N,N,4,6,N}};
        DGraph dGraph = new DGraph(vertexes, weight);
        // 显示邻接矩阵
        // dGraph.showGraph();
        // 测试Dijkstra算法
        // 计算从G到其他各点的最短距离
        dGraph.dijkstra(6);
        // 显示计算结果
        dGraph.showResult();
        // 结果是：
        /**
                     A B C D E F G
         already_arr 1 1 1 1 1 1 1
         pre_visited 6 6 0 5 6 6 0
         dis         2 3 9 10 4 6 0（G到其他各点的最短距离）
         举例：根据pre_visited和dis可以看出，G到D的最短距离为10，最短路径为 G->F->D
         */
    }

    static class DGraph {
        private char[] vertexes; // 顶点数组
        private int[][] weight; // 邻接矩阵
        private VisitedVertex vv;

        public DGraph(char[] vertexes, int[][] weight) {
            this.vertexes = vertexes;
            this.weight = weight;
        }

        //显示结果
        public void showResult() {
            vv.show();
        }

        // 显示图
        public void showGraph() {
            for (int[] link : weight) {
                System.out.println(Arrays.toString(link));
            }
        }

        /**
         * Dijkstra算法
         * @param index 表示出发顶点对应的下标
         */
        public void dijkstra(int index) {
            vv = new VisitedVertex(vertexes.length, index);
            update(index); //更新index顶点到周围顶点的距离，以及更新周围顶点的前驱顶点
            for (int j = 1; j < vertexes.length; j++) {
                index = vv.getNextVertex(); // 选择并返回新的访问顶点
                update(index); //更新index顶点到周围顶点的距离，以及更新周围顶点的前驱顶点
            }
        }

        // 更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点,
        // lsc注：说的更准确些，更新index下标的节点到所有T标号点的距离，更新T标号节点的前驱顶点
        // T标号点（未计算出出发顶点到该顶点最短距离的点）
        private void update(int index) {
            int len = 0;
            for (int j = 0; j < weight[index].length; j++) {
                // len就是出发顶点到j顶点的距离 = 出发顶点到i顶点的距离+从index顶点到j顶点距离
                // lsc注：假如起点为s（隐含的前提：当前是沿着s->index->j的路径来走）
                len = vv.getDis(index) + weight[index][j];
                if (!vv.hasCaculated(j) && len < vv.getDis(j)) {
                    vv.updatePre(j, index); // 更新j顶点的前驱为index顶点
                    vv.updateDis(j, len); // 更新出发顶点到j顶点的距离
                }
            }
        }
    }

    // 已经访问过的顶点的相关数据
    static class VisitedVertex {
        // 记录各个顶点是否访问过 1表示访问过,0未访问,会动态更新
        // lsc注：结合其他算法书来看，这里其实指的是已经计算出最短路径的节点（或者说已经带有P标号的节点）
        public int[] already_arr;
        // 每个下标对应顶点的前一个顶点的下标（例：pre_vertex[3]=2，表示下标为3的顶点的前一个顶点是下标为2的顶点，
        // 即D的前一个顶点是C，换句话说，就是到达顶点D的最短路径里，其中最后一段是从D到C的部分）
        public int[] pre_vertex;
        // 记录出发顶点到指定索引的顶点的距离（如：假设出发顶点为G，dis[0]=5，表示G到A的距离为5，
        // 直到最后算法结束，dis里就是G到各点的最短距离；中间计算时，dis会不断变化）
        private int[] dis;

        /**
         * 数据初始化
         * @param length 表示顶点的个数
         * @param index 出发顶点对应的下标，比如G顶点，下标就是6
         */
        public VisitedVertex(int length, int index) {
            this.already_arr = new int[length];
            this.pre_vertex = new int[length];
            this.dis = new int[length];
            // 初始化dis数组
            Arrays.fill(dis, N);
            this.already_arr[index] = 1; // 设置出发顶点被计算过最短距离
            this.dis[index] = 0; // 设置出发顶点的到自身的距离为0
        }

        /**
         * 判断index顶点是否已经计算过最短距离
         * @param index
         * @return 如果计算过，则返回true，否则返回false
         */
        public boolean hasCaculated(int index) {
            return already_arr[index] == 1;
        }

        /**
         * 更新出发顶点到index顶点的距离
         * @param index
         * @param len
         */
        public void updateDis(int index, int len) {
            dis[index] = len;
        }

        /**
         * 将pre这个顶点的前驱顶点设置为index顶点
         * @param pre
         * @param index
         */
        public void updatePre(int pre, int index) {
            pre_vertex[pre] = index;
        }

        /**
         * 返回出发顶点到index顶点的距离
         * @param index
         * @return
         */
        public int getDis(int index) {
            return dis[index];
        }

        /**
         * 继续选择并返回新的访问顶点，比如这里的G访问完后，就是以A作为新的访问顶点（注意不是出发顶点）
         * @return
         */
        public int getNextVertex() {
            int min = N, index = 0;
            // lsc注：在所有没计算出最短距离的点中，找出到出发顶点的距离最小的点，将该点标记为计算出最短距离，作为新的访问顶点
            for (int i = 0; i < already_arr.length; i++) {
                if (already_arr[i] == 0 && dis[i] < min) {
                    min = dis[i];
                    index = i;
                }
            }
            // 将index顶点
            already_arr[index] = 1;
            return index;
        }

        //显示最后的结果
        //即将三个数组的情况输出
        public void show() {

            System.out.println("==========================");
            //输出already_arr
            System.out.println("already_arr");
            for(int i : already_arr) {
                System.out.print(i + " ");
            }
            System.out.println();

            //输出pre_visited
            System.out.println("pre_visited");
            for(int i : pre_vertex) {
                System.out.print(i + " ");
            }
            System.out.println();

            //输出dis
            System.out.println("dis");
            for(int i : dis) {
                System.out.print(i + " ");
            }
            System.out.println();
            //为了好看最后的最短距离，我们处理
            char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
            int count = 0;
            for (int i : dis) {
                if (i != N) {
                    System.out.print(vertex[count] + "("+i+") ");
                } else {
                    System.out.println("N " + "("+i+") ");
                }
                count++;
            }
            System.out.println();

        }

    }
}
