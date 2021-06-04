package com.kittycoder.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shucheng on 2021/6/3 22:29
 * 二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
        /*int[] arr = {1, 8, 89, 1000, 1234};
        // int resultIndex = binarySearch(arr, 0, arr.length - 1, 89);
        int resultIndex = binarySearch2(arr, 0, arr.length - 1, 89);
        System.out.println("resultIndex=" + resultIndex);*/

        int[] arr = {1, 8, 89, 1000, 1000, 1000, 1234};
        List<Integer> list = binarySearch3(arr, 0, arr.length - 1, 1000);
        System.out.println(list);
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
        // System.out.println("Say Hello=== left--->" + left + "=== right--->" + right);
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

    // 完成一个课后思考题：
    /**
     * 课后思考题：{1, 8, 89, 1000, 1000, 1234} 当一个有序数组中，
     * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的1000
     *
     * 思路分析
     * 1. 在找到mid 索引值，不要马上返回
     * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 4. 将Arraylist返回
     */
    public static List<Integer> binarySearch3(int[] arr, int left, int right, int findVal) {
        // 当 left > right 时，说明递归整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { // 向 右递归
            return binarySearch3(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 向左递归
            return binarySearch3(arr, left, mid - 1, findVal);
        } else {
//			 * 思路分析
//			 * 1. 在找到mid 索引值，不要马上返回
//			 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 4. 将Arraylist返回

            List<Integer> resIndexlist = new ArrayList<Integer>();
            //向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            int temp = mid - 1;
            while(true) {
                if (temp < 0 || arr[temp] != findVal) {//退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                temp -= 1; //temp左移
            }
            resIndexlist.add(mid);

            //向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            temp = mid + 1;
            while(true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {//退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                temp += 1; //temp右移
            }

            return resIndexlist;
        }
    }
}
