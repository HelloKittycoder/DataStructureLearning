package com.kittycoder.sparsearray;

import java.io.*;

/**
 * Created by shucheng on 2019-10-27 下午 23:42
 * 自己根据课程里的思路写的稀疏数组
 */
public class MySparseArray {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

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

    /**
     * 将二维数组写入文件
     * 参考链接：https://zhidao.baidu.com/question/323942172.html
     * @param intarr
     */
    public static void writeArrayToFile(int[][] intarr) {
        StringBuilder sb = new StringBuilder();
        int rows = intarr.length;
        int cols = intarr[0].length;
        sb.append(rows + ",").append(cols + ",").append(LINE_SEPARATOR);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(intarr[i][j] + ",");
            }
            sb.append(LINE_SEPARATOR);
        }

        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter("arr.dat"));
            bw.write(sb.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中读取二维数组
     * 参考链接：https://zhidao.baidu.com/question/711019168836675925.html
     * @param file
     */
    public static int[][] readArrayFromFile(File file) {
        int[][] resultArr = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String content;
            content = br.readLine(); // 读取第一行
            String[] contentArr = content.split(",");
            int rows = Integer.parseInt(contentArr[0]);
            int cols = Integer.parseInt(contentArr[1]);
            resultArr = new int[rows][cols];

            int row = 0;
            // 如果readLine读出来是null，说明读到结尾了
            while ((content = br.readLine()) != null) {
                // System.out.println(content);
                contentArr = content.split(",");
                for (int col = 0; col < cols; col++) {
                    resultArr[row][col] = Integer.parseInt(contentArr[col]);
                }
                row++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultArr;
    }
}
