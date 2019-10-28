package com.kittycoder.sparsearray;

import org.junit.Test;

import static com.kittycoder.sparsearray.MySparseArray.*;

/**
 * Created by shucheng on 2019-10-28 下午 22:42
 */
public class TestMySparseArray {

    @Test
    public void test1() {
        /**
         |0  0  0  22  0  0  15|
         |0  11 0  0   0  17 0 |
         |0  0  0  -6  0  0  0 |
         |0  0  0  0   0  39 0 |
         |91 0  0  0   0  0  0 |
         |0  0  28 0   0  0  0 |
         经过转换后结果为：
         |行 列  值|
         0|6  7  8 |
         1|0  3  22|
         2|0  6  15|
         3|1  1  11|
         4|1  5  17|
         5|2  3  -6|
         6|3  5  39|
         7|4  0  91|
         8|5  2  28|
         */
        int[] a = {0, 0, 0, 22, 0, 0, 15,
                0, 11, 0, 0, 0, 17, 0,
                0, 0, 0, -6, 0, 0, 0,
                0, 0, 0, 0, 0, 39, 0,
                91, 0, 0, 0, 0, 0, 0,
                0, 0, 28, 0, 0, 0, 0};
        // 创建一个普通二维数组
        int[][] arr = buildArray(a, 6, 7);
        System.out.println("原始数组~~");
        printTwoDimArray(arr);
        System.out.println();

        // 将二维数组转换成稀疏数组
        int[][] sparseArray = convertToSparseArray(arr);
        System.out.println("转换成稀疏数组~~");
        printTwoDimArray(sparseArray);
        System.out.println();

        // 将稀疏数组转换成普通二维数组
        int[][] normalArray = convertToNormalArray(sparseArray);
        System.out.println("再转换成原始数组~~");
        printTwoDimArray(normalArray);
    }

    @Test
    public void testWriteArrayToFile() {
        int[] a = {0, 0, 0, 22, 0, 0, 15,
                0, 11, 0, 0, 0, 17, 0,
                0, 0, 0, -6, 0, 0, 0,
                0, 0, 0, 0, 0, 39, 0,
                91, 0, 0, 0, 0, 0, 0,
                0, 0, 28, 0, 0, 0, 0};
        // 创建一个普通二维数组
        int[][] arr = buildArray(a, 6, 7);
        writeArrayToFile(arr);
    }
}
