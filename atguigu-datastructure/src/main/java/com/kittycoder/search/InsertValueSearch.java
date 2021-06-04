package com.kittycoder.search;

/**
 * Created by shucheng on 2021/6/4 8:57
 * 插值查找
 *
 * 注意事项：
 * 1）对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找，速度较快
 * 2）关键字分布不均匀的情况下，该方法不一定比折半查找要好
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        // 生成测试数组
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = i + 1;
        }

        // int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};

        // 使用二分查找，如果找最边上的数字，比如说0，需要3次才能定位到要找的数字，效率较低
        /*int i = BinarySearch.binarySearch2(arr, 0, arr.length - 1, 1);
        System.out.println(i);*/

        int i = insertValueSearch(arr, 0, arr.length - 1, 10);
        System.out.println(i);
    }

    //编写插值查找算法
    //说明：插值查找算法，也要求数组是有序的
    /**
     *
     * @param arr 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 查找值
     * @return 如果找到，就返回对应的下标，如果没有找到，返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("Say Hello=== left--->" + left + "=== right--->" + right);
        //注意：findVal < arr[0]  和  findVal > arr[arr.length - 1] 必须需要
        //否则我们得到的 mid 可能越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        // 求出mid, 自适应
        int mid = left + (findVal - arr[left]) / (arr[right] - arr[left]) * (right - left);
        int midVal = arr[mid];

        if (findVal > midVal) { // 说明应该向右边递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 说明向左递归查找
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
