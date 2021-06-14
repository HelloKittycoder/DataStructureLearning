package com.kittycoder.tree;

import java.util.Arrays;

/**
 * 堆排序（这里以大顶堆-升序为例）
 * Created by shucheng on 2021/6/14 22:19
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    public static void heapSort(int[] arr) {
        int temp = 0;
        // 分步完成
        adjustHeap(arr, 1, arr.length);
        System.out.println("第1次：" + Arrays.toString(arr)); // 4,9,8,5,6
        adjustHeap(arr, 0, arr.length);
        System.out.println("第2次：" + Arrays.toString(arr)); // 9,6,8,5,4

        // 完成最终代码
        // 1）将无序序列构成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        /**
         * 2）将堆顶元素与末尾元素交换，将最大元素“沉”到数组末端
         * 3）重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素
         * 反复执行交换+调整步骤，直到整个序列有序
         *
         * lsc注：为什么这里在执行adjustHeap时，传入的i是直接从0开始的，而不是从arr.length / 2 - 1开始的？
         * 答：没有必要。第1次需要从下往上，是因为那时二叉树还不一定是一个大顶堆；
         * 第2次时，因为只是将顶部和最后一个元素发生了对调，所以中间的数据大小关系没有变化，只是最顶部的被破坏了，所以
         * 只用从最顶部开始作调整
         */
        for (int j = arr.length - 1; j > 0; j--) {
            // 交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            // 调整
            adjustHeap(arr, 0, j);
        }
        System.out.println("数组：" + Arrays.toString(arr));
    }

    /**
     * 将一个数组（二叉树），调整成一个大顶堆
     * 功能： 完成 将 以 i 对应的非叶子结点的树调整成大顶堆
     * 举例  int arr[] = {4, 6, 8, 5, 9}; i = 1 => {4, 9, 8, 5, 6}
     * 如果我们再次调用  adjustHeap 传入的是 {4, 9, 8, 5, 6}；i = 0 => {9, 6, 8, 5, 4}
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素继续调整，length是在逐渐的减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i]; // 先取出当前元素的值，保存在临时变量
        // 开始调整
        // 说明
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) { // 说明左子节点的值小于右子节点的值
                k++; // k指向右子节点
            }
            if (arr[k] > temp) { // 如果子节点大于父节点
                arr[i] = arr[k]; // 把较大的值赋给当前节点
                i = k; // i指向k，继续循环比较
            } else {
                break;
            }
        }
        // 当for循环结束后，我们已经将以i为父节点的树的最大值，放在了顶部（局部）
        arr[i] = temp; // 将temp值放到调整后面的位置
    }
}
