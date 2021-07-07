package com.kittycoder.datastructure.sort;

import java.util.Arrays;

/**
 * Created by shucheng on 2020/1/27 17:33
 * 以最左边的数为基准数来进行快速排序（网上找的实现）
 */
public class QuickSort2 {

    public static void main(String[] args) {
        int[] arr = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        // sort(arr, 0, arr.length - 1);
        quickSort1(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    // 结合网上的思路（https://www.runoob.com/w3cnote/quick-sort.html）自己写的
    /**
     * 快速排序
     * @param arr 需要排序的数组
     * @param l 数组的左边
     * @param r 数组的右边
     */
    public static void sort(int[] arr, int l, int r) {
        if (l < r) {
            int baseNum = arr[l]; // 基准数
            int baseIndex = l; // 基准数的索引

            int i = l;
            int j = r;

            while (i < j) {
                // j递减，找到比基准数小的数，并交换
                for (; j > baseIndex; j--) {
                    if (arr[j] < baseNum) {
                        arr[baseIndex] = arr[j];
                        arr[j] = baseNum;
                        baseIndex = j;
                        /*System.out.printf("baseIndex=%s,j=%s,%s\n",
                                baseIndex, j, Arrays.toString(arr));*/
                        i++;
                        break;
                    }
                }

                // i递增，找到比基准数大的数，并交换
                for (; i < baseIndex; i++) {
                    if (arr[i] > baseNum) {
                        arr[baseIndex] = arr[i];
                        arr[i] = baseNum;
                        baseIndex = i;
                        /*System.out.printf("baseIndex=%s,j=%s,%s\n",
                                baseIndex, i, Arrays.toString(arr));*/
                        j--;
                        break;
                    }
                }
            }

            sort(arr, l, i - 1); // 左递归
            sort(arr, j + 1, r); // 右递归
        }
    }

    // 返回调整后基准数的位置
    public static int adjustArray(int arr[], int l, int r) {
        int i = l, j = r;
        int x = arr[l];
        while (i < j) {
            for (; i < j && arr[j] >= x; j--);
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }

            for (; i < j && arr[i] < x; i++);
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
        }
        arr[i] = x;
        return i;
    }

    public static void quickSort1(int arr[], int l, int r) {
        if (l < r) {
            int i = adjustArray(arr, l, r);
            quickSort1(arr, l, i - 1);
            quickSort1(arr, i + 1, r);
        }
    }
}
