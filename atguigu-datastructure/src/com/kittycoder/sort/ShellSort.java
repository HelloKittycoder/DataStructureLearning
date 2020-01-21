package com.kittycoder.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by shucheng on 2020/1/20 17:31
 * 希尔排序
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {5, 7, 8, 3, 1, 2, 4, 6};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testShellSort() {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000 + 1);
        }

        long start = System.currentTimeMillis();
        shellSort(arr); // 此时注释掉方法内部的打印语句
        long end = System.currentTimeMillis();
        System.out.printf("排序所需时间为：%ds\n", (end - start));
    }

    // 希尔排序（参考https://blog.csdn.net/qq_39207948/article/details/80006224后，对插入排序稍作改进写的）
    // 这个用的就是移位法
    public static void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap >= 1; gap = gap / 2) {
            insertSort(arr, gap);
        }
    }

    // 插入排序（从索引k开始找）
    public static void insertSort(int[] arr, int gap) {
        int insertValue, insertIndex;
        for (int i = gap; i <= arr.length - 1; i++) {
            // 定义待插入的数
            insertValue = arr[i];
            insertIndex = i - gap; // 即arr[i]的前面这个数的下标

            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                /*System.out.printf("比较(insertValue)arr[%d]=%d和arr[%d]=%d\n",
                        i, insertValue, insertIndex, arr[insertIndex]);*/
                arr[insertIndex + gap] = arr[insertIndex];
                insertIndex = insertIndex - gap;
            }
            // (index+1)就是要操作的位置
            arr[insertIndex + gap] = insertValue;
            // System.out.printf("第%d轮排序后的结果：%s\n\n", i, Arrays.toString(arr));
        }
    }
}
