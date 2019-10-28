package com.kittycoder.sparsearray;

/**
 * Created by shucheng on 2019-10-27 下午 23:42
 * 自己根据课程里的思路写的稀疏数组
 */
public class MySparseArray {

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

        int size = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (intarr[i][j] != 0) {
                    size++;
                }
            }
        }

        int[][] sparsearray = new int[size+1][3];
        sparsearray[0] = new int[]{rows, cols, size};

        int count = 0; // count用于记录是第几个非0数据
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (intarr[i][j] != 0) {
                    count++;
                    sparsearray[count][0] = i;
                    sparsearray[count][1] = j;
                    sparsearray[count][2] = intarr[i][j];
                }
            }
        }
        return sparsearray;
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

    /**
     * 打印二维数组
     * @param intarr
     */
    public static void printTwoDimArray(int[][] intarr) {
        int rows = intarr.length;
        int cols = intarr[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%d\t", intarr[i][j]);
            }
            System.out.println();
        }
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
}
