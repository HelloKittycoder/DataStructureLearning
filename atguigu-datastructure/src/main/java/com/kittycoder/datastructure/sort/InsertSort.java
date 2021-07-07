package com.kittycoder.datastructure.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by shucheng on 2020/1/20 8:01
 * 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        // int[] arr = {2, 9, -1, 10, -2};
        int[] arr = {5, 4, 3, 2, 1};
        // sort(arr);
        sort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 课程中的写法
    // arr {2, 9, -1, 10, -2}
    public static void sort2(int[] arr) {
        int insertValue, insertIndex;
        for (int i = 1; i <= arr.length - 1; i++) {
            // 定义待插入的数
            insertValue = arr[i];
            insertIndex = i - 1; // 即arr[i]的前面这个数的下标

            /**
             * 找到insertIndex需要插入的位置
             * 说明：
             * 1. insertIndex>=0保证在insertValue找插入位置时，不越界
             * 2. insertValue<arr[insertIndex]待插入的数，表示还没有找到插入位置
             * 3. 就需要将arr[insertIndex]的数据后移
             */
            // System.out.printf("开始进行第%d轮排序：%s\n", i, ColorStringUtil.indexColorString(arr, i));
            // 如果要从大到小排序，只要把insertValue<arr[insertIndex]改成insertValue>arr[insertIndex]
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                /*System.out.printf("比较(insertValue)arr[%d]=%d和arr[%d]=%d\n",
                        i, insertValue, insertIndex, arr[insertIndex]);*/
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            // (index+1)就是要操作的位置
            /*if (insertIndex + 1 != i) { //这个判断其实可有可无*/
                arr[insertIndex + 1] = insertValue;
            /*}*/
            // System.out.printf("第%d轮排序后的结果：%s\n\n", i, Arrays.toString(arr));
        }
    }

    // 根据课程思路写的插入排序
    // 整体思路一样（只不过我的是先把位置找出来，然后再一次性移动；这里从时间上看，没有sort2性能好）
    // 课程里是边找边移动
    public static void sort(int[] arr) {
        int length = arr.length;

        int insertValue, insertIndex;
        for (int i = 0; i < length - 1; i++) {
            insertValue = arr[i + 1];
            insertIndex = i;

            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                /*System.out.printf("比较(insertValue)arr[%d]=%d和arr[%d]=%d\n",
                        i, insertValue, insertIndex, arr[insertIndex]);*/
                insertIndex--;
            }
            // 将数组arr中索引为(i+1)的元素插到索引(insertIndex + 1)之前
            insertBefore(arr, i + 1, insertIndex + 1);
        }
    }

    @Test
    public void testInsertBefore() {
        int[] arr = {5, 3, 2, 1, 4};
        insertBefore(arr, 3, 0);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 将指定索引的数字插到某个索引之前
     * @param arr
     * @param index
     * @param insertPoint
     */
    public static void insertBefore(int[] arr, int index, int insertPoint) {
        int temp = arr[index];
        for (int i = index - 1; i >= insertPoint; i--) {
            arr[i + 1] = arr[i];
        }
        arr[insertPoint] = temp;
    }
}
