package com.kittycoder.sparsearray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shucheng on 2019-10-27 下午 23:42
 * 自己根据课程里的思路写的稀疏数组
 */
public class MySparseArray {

    public static class Data {
        private int row;
        private int col;
        private int value;

        public Data() {
        }

        public Data(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    /**
     * 将一维数组转换成二维数组
     * @param arr
     * @param rows
     * @param cols
     * @return
     */
    public static int[][] buildArray(int[] arr, int rows, int cols) {
        if (rows*cols != arr.length) {
            throw new RuntimeException("数组与行列数不匹配");
        }
        int[][] resultArray = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultArray[i][j] = arr[i * cols + j];
            }
        }
        return resultArray;
    }

    /**
     * 将二维数组转换成稀疏数组
     * @param intarr
     */
    public static int[][] convertToSparseArray(int[][] intarr) {
        int rows = intarr.length;
        int cols = intarr[0].length;

        List<Data> dataList = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int val;
                if ((val = intarr[i][j]) != 0) {
                    dataList.add(new Data(i, j, val));
                }
            }
        }

        int[][] array = new int[dataList.size()+1][3];
        array[0] = new int[]{rows, cols, dataList.size()};
        Data data;
        for (int i = 1; i < dataList.size()+1; i++) {
            data = dataList.get(i-1);
            array[i][0] = data.row;
            array[i][1] = data.col;
            array[i][2] = data.value;
        }
        return array;
    }

    /**
     * 将稀疏数组转换成正常数组
     * @param sparsearray
     * @return
     */
    public static int[][] convertToNormalArray(int[][] sparsearray) {
        int[] headinfo = sparsearray[0];
        int rows = headinfo[0];
        int cols = headinfo[1];
        int notEqualZeroNums = headinfo[2];
        int[][] intarr = new int[rows][cols];

        for (int i = 1; i < notEqualZeroNums + 1; i++) {
            int[] rowdata = sparsearray[i];
            int row = rowdata[0];
            int col = rowdata[1];
            int value = rowdata[2];
            intarr[row][col] = value;
        }
        return intarr;
    }

    public static void main(String[] args) {
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
        // 将二维数组转换成稀疏数组
        int[][] sparseArray = convertToSparseArray(arr);
        // 将稀疏数组转换成普通二维数组
        int[][] normalArray = convertToNormalArray(sparseArray);
        System.out.println(normalArray);
    }
}
