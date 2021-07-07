package com.kittycoder.datastructure.sort;

import com.kittycoder.datastructure.util.ColorStringUtil;

import java.util.Arrays;

/**
 * Created by shucheng on 2020/1/8 21:24
 * 冒泡排序
 * 时间复杂度 o(n^2)
 */
public class BubbleSort {

    public static void main(String[] args) {
        // 不会提前退出冒泡排序的情况
        /*int[] a = {3, 9, -1, 10, -2};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));*/

        // 会提前退出冒泡排序的情况
        // int[] a = {3, 9, -1, 10, 20};
        int[] a = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(a));
        sort2(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 根据课程思路写的冒泡排序
     * 说明：这里不返回数组的原因是，数组本身是引用类型；而调用该方法的地方肯定持有该数组的引用，
     * 所以没必要返回数组
     */
    public static void sort(int[] arr) {
        int temp;
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            // length - 1 - i，这里减去1就是把上一趟得到的最大数排除掉，那个数不需要再参与排序了
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                System.out.printf("i=%d j=%d 排序结果：%s\n", i, j,
                        Arrays.toString(arr));
            }
            /*System.out.printf("i=%d 第%d趟排序后的结果：%s\n", i,
                    i + 1, Arrays.toString(arr));*/
            System.out.printf("i=%d 第%d趟排序后的结果：%s\n", i,
                    i + 1, ColorStringUtil.fromIndexColorString(arr, length - 1 -i));
        }
    }

    // 优化：如果某趟排序中，没有发生一次交换，可以提前结束冒泡排序
    public static void sort2(int[] arr) {
        int temp;
        int length = arr.length;
        boolean flag = false;

        for (int i = 0; i < length - 1; i++) {
            // length - 1 - i，这里减去1就是把上一趟得到的最大数排除掉，那个数不需要再参与排序了
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
                /*System.out.printf("i=%d j=%d 排序结果：%s\n", i, j,
                        Arrays.toString(arr));*/
            }
            /*System.out.printf("i=%d 第%d趟排序后的结果：%s\n", i,
                    i + 1, ColorStringUtil.fromIndexColorString(arr, length - 1 - i));*/

            if (!flag) { // 在一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false; // 重置flag，进行下次判断
            }
        }
    }
}
