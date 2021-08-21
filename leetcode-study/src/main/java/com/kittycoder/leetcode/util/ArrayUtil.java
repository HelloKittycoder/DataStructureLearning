package com.kittycoder.leetcode.util;

import java.util.Arrays;

/**
 * Created by shucheng on 2021/8/21 22:09
 */
public class ArrayUtil {

    /**
     * 判断数组是否相等，忽略顺序
     * 参考链接：https://codingdict.com/questions/110658
     * @param arr1
     * @param arr2
     * @return
     */
    public static boolean contentEquals(int[] arr1, int[] arr2) {
        int[] cloneArr1 = Arrays.copyOf(arr1, arr1.length);
        int[] cloneArr2 = Arrays.copyOf(arr2, arr2.length);
        Arrays.sort(cloneArr1);
        Arrays.sort(cloneArr2);
        return Arrays.equals(cloneArr1, cloneArr2);
    }
}
