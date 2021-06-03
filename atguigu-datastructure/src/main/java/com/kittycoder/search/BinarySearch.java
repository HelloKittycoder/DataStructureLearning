package com.kittycoder.search;

/**
 * Created by shucheng on 2021/6/3 22:29
 * 二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 8, 89, 1000, 1234};
        // int resultIndex = binarySearch(arr, 0, arr.length - 1, 89);
        int resultIndex = binarySearch2(arr, 0, arr.length - 1, 89);
        System.out.println("resultIndex=" + resultIndex);
    }


    /**
     * 二分查找（递归版）自己按课程思路写的
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，如果没有找到，就返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if (left == right) {
            return -1;
        }

        if (findVal == arr[left]) {
            return left;
        } else if (findVal == arr[right]) {
            return right;
        } else {
            int tempMid = (left + right) / 2;
            if (findVal > arr[tempMid]) {
                return binarySearch(arr, tempMid + 1, right, findVal);
            } else if (findVal < arr[tempMid]) {
                return binarySearch(arr, left, tempMid - 1, findVal);
            } else {
                return tempMid;
            }
        }
    }

    /**
     * 二分查找（递归版）课程里的写法，很简洁
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，如果没有找到，就返回-1
     */
    public static int binarySearch2(int[] arr, int left, int right, int findVal) {
        // 当 left>right 时，说明递归整个数组后，没有找到目标值
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
