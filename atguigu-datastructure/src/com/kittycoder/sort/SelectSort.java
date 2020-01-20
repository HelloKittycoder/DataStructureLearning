package com.kittycoder.sort;

import com.kittycoder.util.ColorStringUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by shucheng on 2020/1/9 9:21
 * 选择排序
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] a= {3, 9, -1, 10, -2};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(int[] arr) {
        int length = arr.length;

        for (int i = 0; i < length - 1; i++) {
            // 找出从索引i及以后的所有数字中的最小数字
            int minValue = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (minValue > arr[j]) {
                    minValue = arr[j];
                    minIndex = j;
                }
            }
            System.out.printf("交换arr[%d]=%d和arr[%d]=%d（最小值）\n",
                    i, arr[i], minIndex, minValue);
            // 交换第0个数字和最小数
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = minValue;
            }
            /*System.out.printf("i=%d 第%d趟排序后的结果：%s\n", i,
                    i + 1, Arrays.toString(arr));*/
            System.out.printf("i=%d 第%d趟排序后的结果：%s\n", i,
                    i + 1, ColorStringUtil.toIndexColorString(arr, i));
        }
    }

    // 测试选择排序的性能
    @Test
    public void testSelectSort() {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000 + 1);
        }

        long start = System.currentTimeMillis();
        sort(arr); // 此时注释掉方法内部的打印语句
        long end = System.currentTimeMillis();
        System.out.printf("排序所需时间为：%ds\n", (end - start) / 1000);
    }
}
