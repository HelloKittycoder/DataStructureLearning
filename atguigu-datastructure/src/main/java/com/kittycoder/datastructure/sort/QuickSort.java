package com.kittycoder.datastructure.sort;

import com.kittycoder.datastructure.recursion.RecursionUtil;
import com.kittycoder.datastructure.util.ColorStringUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * Created by shucheng on 2020/1/26 16:25
 * 以中间的数为基准数来进行快速排序（课程的实现）
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        System.out.println("排序前：" + Arrays.toString(arr));
        sort(arr, 0, arr.length - 1, 0);
        // sort2(arr, 0, arr.length - 1);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    // 会打印中间结果，方便调试
    public static void sort(int[] arr, int left, int right, int depth) {
        System.out.println(RecursionUtil.generateDepthString(depth) + "进入");
        int l = left; // 左下标
        int r = right; // 右下标
        // pivot 中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0; // 临时变量，作为交换时使用
        // while循环的目的是让比pivot值小的放左边
        // 比pivot值大的放到右边
        while (l < r) {
            // 在pivot的左边一直找，找到大于等于pivot值，才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            // 在pivot的右边一直找，找到小于等于pivot值，才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            // 如果l >= r说明pivot的左右两边的值，已经按照左边全部是
            // 小于等于pivot的值，右边全部是大于等于pivot值来排列了
            if (l >= r) {
                break;
            }

            System.out.printf("交换前：%s；", ColorStringUtil.indexColorString(arr, l, r));
            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            System.out.printf("交换后：%s\n", ColorStringUtil.indexColorString(arr, l, r));

            // 如果交换完后，发现这个arr[l] == pivot值 相等 r--，前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            // 如果交换完后，发现这个arr[r] == pivot值 相等 l++，后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        // 如果l == r，必须l++，r--，否则会出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        // 向左递归
        if (left < r) {
            System.out.printf("【左】left=%s,r=%s,%s(%s,%s)=%s\n", left, r, ColorStringUtil.indexColorString(arr, left, r),
                    left, r + 1, subarrayString(arr, left, r + 1));
            sort(arr, left, r, depth + 1);
        }
        // 向右递归
        if (right > l) {
            System.out.printf("【右】l=%s,right=%s,%s(%s,%s)=%s\n", l, right, ColorStringUtil.indexColorString(arr, l, right),
                    l, right + 1, subarrayString(arr, l, right + 1));
            sort(arr, l, right, depth + 1);
        }
        System.out.println(RecursionUtil.generateDepthString(depth) + "退出");
    }

    // 不会打印中间结果
    public static void sort2(int[] arr, int left, int right) {
        int l = left; // 左下标
        int r = right; // 右下标
        // pivot 中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0; // 临时变量，作为交换时使用
        // while循环的目的是让比pivot值小的放左边
        // 比pivot值大的放到右边
        while (l < r) {
            // 在pivot的左边一直找，找到大于等于pivot值，才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            // 在pivot的右边一直找，找到小于等于pivot值，才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            // 如果l >= r说明pivot的左右两边的值，已经按照左边全部是
            // 小于等于pivot的值，右边全部是大于等于pivot值来排列了
            if (l >= r) {
                break;
            }

            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 如果交换完后，发现这个arr[l] == pivot值 相等 r--，前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            // 如果交换完后，发现这个arr[r] == pivot值 相等 l++，后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        // 如果l == r，必须l++，r--，否则会出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        // 向左递归
        if (left < r) {
            sort2(arr, left, r);
        }
        // 向右递归
        if (right > l) {
            sort2(arr, l, right);
        }
    }

    public static String subarrayString(int[] arr, int start, int end) {
        return Arrays.toString(ArrayUtils.subarray(arr, start, end));
    }
}
