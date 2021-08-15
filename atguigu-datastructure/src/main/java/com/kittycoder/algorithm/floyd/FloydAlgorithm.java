package com.kittycoder.algorithm.floyd;

import java.util.Arrays;

/**
 * Floyd算法解决最短路径问题
 * Created by shucheng on 2021/8/8 19:24
 */
public class FloydAlgorithm {

    public static final int N = 65536;

    public static void main(String[] args) {
        int[][] distMatrix =
                {{0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0}};
        int[][] pathMatrix =
                {{0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3, 3, 3},
                {4, 4, 4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5, 5, 5},
                {6, 6, 6, 6, 6, 6, 6}};

        int vertexNum = distMatrix.length;
        for (int k = 0; k < vertexNum; k++) {
            for (int i = 0; i < vertexNum; i++) {
                for (int j = 0; j < vertexNum; j++) {
                    if (distMatrix[i][k] + distMatrix[k][j] < distMatrix[i][j]) {
                        distMatrix[i][j] = distMatrix[i][k] + distMatrix[k][j];
                        // pathMatrix[i][j] = pathMatrix[k][j];
                        // 上面是课程里的写法，和下面其实是一样的
                        pathMatrix[i][j] = k;
                    }
                }
            }
        }

        System.out.println("distMatrix:=====");
        char[] charArr = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int[] arr : distMatrix) {
            System.out.println(Arrays.toString(arr));
        }

        System.out.println("pathMatrix:=====");
        for (int i = 0; i < vertexNum; i++) {
            System.out.print("[");
            for (int j = 0; j < vertexNum; j++) {
                System.out.print(charArr[pathMatrix[i][j]]);
                if (j != vertexNum - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            System.out.println();
        }

        /*for (int[] arr : pathMatrix) {
            System.out.println(Arrays.toString(arr));
        }*/
    }
}
